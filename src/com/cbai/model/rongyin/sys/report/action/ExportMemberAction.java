package com.cbai.model.rongyin.sys.report.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.poi.BeanToMap;
import com.cbai.common.poi.ExportExcel;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.web.CookieUtils;
import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.Members;
import com.cbai.model.rongyin.sys.report.vo.ReportMemberVo;
import com.cbai.model.rongyin.sys.users.vo.MemberSearchVO;

@Controller 
@RequestMapping(value="system")
public class ExportMemberAction {
	
	private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";
	
	private BaseService baseService; 
	
    @RequestMapping(value="/report/member/", method=RequestMethod.POST)
	public void member_info(HttpServletRequest request, HttpServletResponse response, String exportType, MemberSearchVO searchVO) throws Exception {
		
    	//获得数据
    	List<Members> membersList = baseService.queryList("members.getAll", searchVO);
		
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
    	//转为报表对象
    	if(membersList!=null&&membersList.size() > 0 ){
    		for(Members member:membersList){
    			 ReportMemberVo mvo= new ReportMemberVo();
    			 mvo.setUsrcustid(member.getUsrcustid());
    			 mvo.setRealname(member.getRealname());
    			 
    			 if(member.getSex()!=null){
    			 mvo.setSex(State.valueOf(member.getSex()).getName());
    			 }
    			 mvo.setBirth(member.getBirth());
    			 mvo.setPhone(member.getPhone());
    			 mvo.setIdnum(member.getIdnum());
    			 mvo.setAccinfo(AmountUtils.changeF2Y(String.valueOf(member.getAccinfo().getAvlmoney()==null?0L:member.getAccinfo().getAvlmoney())));
    			 mvo.setBidingmoney(AmountUtils.changeF2Y(member.getAccinfo().getBidingmoney()==null?0L:member.getAccinfo().getBidingmoney()));
    			 mvo.setAllbidmoney(AmountUtils.changeF2Y(member.getAccinfo().getAllbidmoney()==null?0L:member.getAccinfo().getBidingmoney()));
    			 
    			 if(member.getIfbind()!=null){
    			 mvo.setIfbind(member.getIfbind().getName());
    			 }
    			 if(member.getIffreeze()!=null){
    			 mvo.setIffreeze(member.getIffreeze().getName());
    			 }
    			 if(member.getDadinfo()!=null && member.getDadinfo().getDadinfo()!=null){
	    			 mvo.setGrandRealname(member.getDadinfo().getDadinfo().getRealname());
	    			 mvo.setGrandPhone(member.getDadinfo().getDadinfo().getPhone());
    			 }
    			 
    			 if(member.getDadinfo()!=null){
    				 mvo.setDadRealname(member.getDadinfo().getRealname());
    				 mvo.setDadPhone(member.getDadinfo().getPhone());
    			 }
    			 
    			 SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			 
    			 if(member.getAuthtime()!=null){
    			 mvo.setAuthtime(sdff.format(member.getAuthtime()));
    			 }
    			 
    			 if(member.getRegtime()!=null){
    			 mvo.setRegtime(sdff.format(member.getRegtime()));
    			 }
    			 
    			 mvo.setStname(member.getStname());

    			 BeanToMap<ReportMemberVo> b = new BeanToMap<ReportMemberVo>();
    			 list.add(b.getMap(mvo));
    		}
    	}
    	
		ExportExcel<List<Map<String, Object>>> exportExcel = new ExportExcel<List<Map<String, Object>>>();
		
		StringBuffer filename = new StringBuffer();
		filename.append("会员信息");
		filename.append(System.currentTimeMillis());
		
		if(StringUtils.isEmpty(exportType)) {
			filename.append(EXPORT_XLSX_FILE_SUFFIX);
		} else {
			filename.append(exportType);
		}
		
		OutputStream out = null;
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.toString().getBytes("UTF-8"), "ISO8859-1"));
			
			//Add by Nicole 20180413
		    Cookie status = CookieUtils.getCookie(request, "updateStatus");
		    if(status!=null){
		    	CookieUtils.addCookie(request, response, "updateStatus", "success", 600, "/");
		    }else{
		    	CookieUtils.addCookie(request, response, "updateStatus", "success", 600, "/");
		    }

			out = response.getOutputStream();
			exportExcel.exportXSExcelByColumn("Sheet", 
					new String[] {"汇付Id", "姓名", "性别", "生日", "手机号码", "身份证", "可用余额", "在投金额","总投金额","是否绑卡","是否冻结","一级推荐人","一级推荐人手机","二级推荐人","二级推荐人手机","开户时间","注册时间","状态"},
					new String[] {"usrcustid","realname","sex", "birth", "phone", "idnum", "accinfo", "bidingmoney", "allbidmoney","ifbind","iffreeze","grandRealname","grandPhone","dadRealname","dadPhone","authtime","regtime","stname"},
					list, out ,null);
			
			
            response.flushBuffer();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}

    @Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	} 
    
}
