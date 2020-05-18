package com.cbai.model.rongyin.pc.member.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.huifu.QueryUtils;
import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.model.rongyin.pc.member.vo.MemberVO;

@Controller
@RequestMapping(value="member")
public class MemShenglibaoAction {
	
	private QueryUtils queryUtils;
	
	private PropertyUtils propertyUtils;
	
	@RequestMapping(value="/fss/fsstrans", method=RequestMethod.GET)
	public String fssTrans(HttpServletRequest request, ModelMap model) throws Exception{
		
        HttpSession session = request.getSession(false);
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				MemberVO memberVO = (MemberVO)tmp;  
				
				String ordId = BusinessNumberUtil.gainNumber();
		    	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		    	
				String bgRetUrl = propertyUtils.getPropertiesString("FssTrans_BgRetUrl");
				
				Map<String,String> params = RequestParemters.fssTransParams(memberVO.getUsrcustid(), ordId, ordDate, bgRetUrl);
				
				model.put("fssTrans", params);
				
				return "/front/huifu/huifuShenglibao";
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
		}else{
			return "redirect:/error/?msg=error.op"; 
		} 
		
		
	}
	
	@RequestMapping(value="/fss/queryfss", method=RequestMethod.GET)
	public String queryFss(HttpServletRequest request, ModelMap model) throws Exception{
		
        HttpSession session = request.getSession(false);
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				
				MemberVO memberVO = (MemberVO)tmp;
				
				//产品信息
				JsonResultObject fssObject = queryUtils.queryFss();
				
				model.put("fssInfo", fssObject.getReturnObj());
				
				
				//用户信息
		        JsonResultObject fssAcctsObject = queryUtils.queryFssAccts(memberVO.getUsrcustid());
				
				model.put("fssAcctsInfo", fssAcctsObject.getReturnObj());
				
				return "/front/member/mem_fss";
				
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
		}else{
			return "redirect:/error/?msg=error.op"; 
		} 
		
    }

	
	
	
	@Autowired
	public void setQueryUtils(QueryUtils queryUtils) {
		this.queryUtils = queryUtils;
	}

	@Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
}
