package com.cbai.model.common.permission.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;  
import com.cbai.model.common.ibatis.entity.PerDepartment;

@Controller
@RequestMapping(value="system")
public class PerDepMgAction {
	
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/common/permission/department/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/sysset/permission/department/list_dep";
		
	}
 
	@RequestMapping(value="/common/permission/department/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit) throws Exception { 
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("per_department.getAll", null, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
 	
	}
	
	@RequestMapping(value="/common/permission/department/enter_add/", method=RequestMethod.GET)
	public String enter_add() throws Exception{ 
		  
		return "/admin/sysset/permission/department/add_dep";
		
	}
	
	@RequestMapping(value="/common/permission/department/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,PerDepartment pdep) throws Exception{ 
		
		pdep.setState(State.NORMAL);
		
		baseService.save("per_department.insert", pdep);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));   
		
	}
	
	@RequestMapping(value="/common/permission/department/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("pdep", baseService.queryObject("per_department.getOne", id));
		  
		return "/admin/sysset/permission/department/edit_dep";
		
	}
	
	@RequestMapping(value="/common/permission/department/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,PerDepartment pdep) throws Exception{
		   
		baseService.update("per_department.update", pdep);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/common/permission/department/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		PerDepartment pdep = new PerDepartment();
			
		pdep.setPdid(id);
		
		pdep.setState(State.valueOf(state));
		
		baseService.update("per_department.update", pdep);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
    @Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
    
    @Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 


}
