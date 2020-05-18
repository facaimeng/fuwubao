package com.cbai.model.rongyin.sys.projects.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
  

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
import com.cbai.model.rongyin.ibatis.entity.CompanyInfo;
import com.cbai.model.rongyin.ibatis.entity.Members; 
import com.cbai.model.rongyin.ibatis.entity.Projects; 
import com.cbai.model.rongyin.sys.projects.vo.ProjectSearchVO;
import com.cbai.model.rongyin.sys.users.vo.CompanySearchVO;
import com.google.gson.reflect.TypeToken;

@Controller 
@RequestMapping(value="system")
public class ProjectMgAction { 
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/projects/enter_uncheck_search/", method=RequestMethod.GET)
	public String enter_uncheck_search() throws Exception {
		
    	return "/admin/projects/list_uncheck_project";
		
	} 
    
    @RequestMapping(value="/projects/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/projects/list_project";
		
	} 
 
	@RequestMapping(value="/projects/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,ProjectSearchVO searchVO) throws Exception {
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("projects.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));  
		
	}
 
	@RequestMapping(value="/projects/{op:detail|enter_check}/", method=RequestMethod.GET)
	public String detail(@PathVariable String op,HttpServletRequest request,Integer id) throws Exception{
		
		Projects  project = baseService.queryObject("projects.getOne", id);      
		
		request.setAttribute("pattJson",JsonUtils.obj2json(baseService.queryList("project_attach.getAll", project.getProid()))); 
		
		request.setAttribute("pimgsJson",JsonUtils.obj2json(baseService.queryList("project_imgs.getAll", project.getProid()))); 
		
		request.setAttribute("project",project); 
		
		if(op.equals("detail")){
			return "/admin/projects/detail_project"; 
		}else{
			return "/admin/projects/check_project"; 
		} 
	}
	
	@RequestMapping(value="/projects/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Projects project) throws Exception{
		
		baseService.update("projects.update", project);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		    
	} 
	
	
	@RequestMapping(value="/projects/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		Projects project = new Projects();
			
		project.setProid(id);
		
		project.setState(State.valueOf(state));
		
		baseService.update("projects.update", project);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
 

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 
	

}
