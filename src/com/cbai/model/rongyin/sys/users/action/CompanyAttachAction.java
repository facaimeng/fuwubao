package com.cbai.model.rongyin.sys.users.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.FileUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.utils.UpAndDownUtil;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.CompanyAttach;   

@Controller 
@RequestMapping(value="system")
public class CompanyAttachAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    @RequestMapping(value="/users/company/attaches/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/users/company/attaches/list_attach";
		
	}
 
	@RequestMapping(value="/users/company/attaches/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,Integer id) throws Exception {
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("company_attach.getAll", id, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
		
	} 
	@RequestMapping(value="/users/company/attaches/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("catt", baseService.queryObject("company_attach.getOne", id)); 
		  
		return "/admin/users/company/attaches/edit_attach";
		
	}
	
	@RequestMapping(value="/users/company/attaches/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,CompanyAttach catt) throws Exception{
		
		baseService.update("company_attach.update", catt);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		
	}
	
	@RequestMapping(value="/users/company/attaches/del/", method=RequestMethod.GET)
	public void del(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception {
		
		CompanyAttach catt = baseService.queryObject("company_attach.getOne", id);
 	 
		try {
			FileUtil.delFiles(request.getSession().getServletContext().getRealPath("/")+catt.getFurl());	
		} catch (Exception e) {
			e.printStackTrace();
 
		}  
		baseService.delete("company_attach.delete", id);  
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
	}
	 
 
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 


}
