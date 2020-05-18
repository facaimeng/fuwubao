package com.cbai.model.rongyin.sys.users.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.CompanyInfo; 
import com.cbai.model.rongyin.sys.users.vo.CompanySearchVO;

@Controller 
@RequestMapping(value="system")
public class CompanyMgAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/users/company/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/users/company/list_company";
		
	}
 
	@RequestMapping(value="/users/company/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,CompanySearchVO searchVO) throws Exception {
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("company_info.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));  
		
	}
	
	@RequestMapping(value="/users/company/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request,String t) throws Exception{
		
		request.setAttribute("ctype", State.valueOf(t.toUpperCase()));
		  
		return "/admin/users/company/add_company";
		
	}
	
	@RequestMapping(value="/users/company/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,CompanyInfo cominfo) throws Exception{
		
		cominfo.setCmnum(BusinessNumberUtil.gainOutBizNoNumber());
		
		cominfo.setState(State.NORMAL);
		
		cominfo.setAddtime(new Date());
		
		baseService.save("company_info.insert", cominfo);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
		
	}
	 
	
	@RequestMapping(value="/users/company/huifu/regester/", method=RequestMethod.GET)
	public String huifu_regester(ModelMap model,Integer id) throws Exception{
		
		CompanyInfo cominfo = baseService.queryObject("company_info.getSimpleOne", id);
		
		String isGT = "Y";
		
		if(cominfo.getCtype().toString().equals("COM")){ 
		    isGT = "N";
		}
		
		model.put("account", RequestParemters.companyRegisterParams(propertyUtils.getPropertiesString("CompanyRegister_BgRetUrl"),
				cominfo.getCmnum(), cominfo.getLicnum(),isGT));

		return "/admin/huifu/company_regester";
		 
	}
	
	
	@RequestMapping(value="/users/company/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("cominfo", baseService.queryObject("company_info.getOne", id)); 
		  
		return "/admin/users/company/edit_company";
		
	}
	
	@RequestMapping(value="/users/company/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,CompanyInfo cominfo) throws Exception{
		
		baseService.update("company_info.update", cominfo);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		
	}
	
	@RequestMapping(value="/users/company/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		CompanyInfo com = new CompanyInfo();
			
		com.setCmid(id);
		
		com.setState(State.valueOf(state));
		
		baseService.update("company_info.update", com);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
 
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 

}
