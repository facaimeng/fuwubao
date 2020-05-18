package com.cbai.model.rongyin.sys.users.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 

import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State; 
import com.cbai.model.rongyin.ibatis.entity.LoanMan; 
import com.cbai.model.rongyin.pc.member.vo.MemberVO;
import com.cbai.model.rongyin.sys.users.vo.LoanmanSearchVO;

@Controller 
@RequestMapping(value="system")
public class LoanManMgAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/users/loanman/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/users/loanman/list_loanman";
		
	}
 
	@RequestMapping(value="/users/loanman/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,LoanmanSearchVO searchVO) throws Exception {
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("loanman.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount());
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		
	}
	
	@RequestMapping(value="/users/loanman/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request,String t) throws Exception{
		return "/admin/users/loanman/add_loanman";
	}
	
	@RequestMapping(value="/users/loanman/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,LoanMan loanman) throws Exception{
 
		loanman.setState(State.NORMAL);
		
		loanman.setAddtime(new Date());
		
		baseService.save("loanman.insert", loanman);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
		
	}
	  
	
	@RequestMapping(value="/users/loanman/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("loanman", baseService.queryObject("loanman.getOne", id)); 
		  
		return "/admin/users/loanman/edit_loanman";
		
	}
	
	@RequestMapping(value="/users/loanman/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,LoanMan loanman) throws Exception{
		
		baseService.update("loanman.update", loanman);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		
	}
	
	@RequestMapping(value="/users/loanman/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		LoanMan loanman = new LoanMan();
			
		loanman.setLmid(id);
		
		loanman.setState(State.valueOf(state));
		
		baseService.update("loanman.update", loanman);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/users/loanman/torechange/", method=RequestMethod.GET)
	public String toRechange(String usrcustId, String transAmt, ModelMap model) throws Exception{
		model.put("usrcustId", usrcustId);
		model.put("transAmt", transAmt);
		return "/admin/users/loanman/rechange_loanman";
	}
	
	@RequestMapping(value="/users/loanman/rechange/", method=RequestMethod.POST)
	public String lmanRechange(String usrCustid, String transAmt, ModelMap model) throws Exception{
		
		if(usrCustid!=null){
			String ordId = BusinessNumberUtil.gainNumber();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			Map<String,String> params = RequestParemters.netSaveParams(usrCustid, ordId, sdf.format(new Date()), AmountUtils.changeF2Y(AmountUtils.changeY2F(transAmt)),
				propertyUtils.getPropertiesString("LoanMan_NetSave_BgRetUrl"));
			
			model.put("netSaveInfo", params);
			return "/admin/users/loanman/huifu_rechange_loanman";
		}else{
			return "redirect:/error/?msg=error.op"; 
		}
		
	}
	
	
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 

}
