package com.cbai.model.rongyin.sys.projects.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.cbai.model.common.permission.vo.PerUserVO;
import com.cbai.model.rongyin.ibatis.entity.CompanyInfo;
import com.cbai.model.rongyin.ibatis.entity.Members; 
import com.cbai.model.rongyin.ibatis.entity.Projects; 
import com.cbai.model.rongyin.sys.projects.vo.ProjectSearchVO;
import com.cbai.model.rongyin.sys.users.vo.CompanySearchVO;
import com.google.gson.reflect.TypeToken;

@Controller 
@RequestMapping(value="system")
public class MyProjectMgAction { 
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
  
    
    @RequestMapping(value="/projects/my/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/projects/my/list_project";
		
	} 
 
	@RequestMapping(value="/projects/my/search/", method=RequestMethod.GET)
	public void search(HttpServletRequest request,HttpServletResponse response,Integer pageIndex,Integer limit,ProjectSearchVO searchVO) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		PerUserVO perUserVO = (PerUserVO) session.getAttribute("userVO"); 
		
		searchVO.setOpuid(perUserVO.getUserid());
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("projects.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));  
		
	}
	
	@RequestMapping(value="/projects/my/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request) throws Exception{ 
		  
		return "/admin/projects/my/add_project";
		
	}
	
	@RequestMapping(value="/projects/my/add/", method=RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response,Projects project) throws Exception{ 
		
		HttpSession session = request.getSession(false);
		
		PerUserVO perUserVO = (PerUserVO) session.getAttribute("userVO"); 
		
		project.setOpuid(perUserVO.getUserid());
		
		project.setOpuname(perUserVO.getRealname());
		
		project.setAllprice(Long.valueOf(AmountUtils.changeY2F(project.getAllpriceStr()))*10000);
		
		project.setExprice(Long.valueOf(AmountUtils.changeY2F(project.getExpriceStr()))*10000);
		
		project.setAvgprice(Long.valueOf(AmountUtils.changeY2F(project.getAvgpriceStr()))*10000);
		   
		project.setPronum(BusinessNumberUtil.gainOutBizNoNumber());
		
		project.setState(State.NOCHECK);
		
		project.setAddtime(new Date()); 
		
		baseService.save("projects.insert", project);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/projects/my/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{  
		
		Projects  project = baseService.queryObject("projects.getOne", id);    
		
		request.setAttribute("project",project);   
		  
		return "/admin/projects/my/edit_project"; 
	}
	
	@RequestMapping(value="/projects/my/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Projects project) throws Exception{
		
		project.setAllprice(Long.valueOf(AmountUtils.changeY2F(project.getAllpriceStr()))*10000);
		
		project.setExprice(Long.valueOf(AmountUtils.changeY2F(project.getExpriceStr()))*10000);
		
		project.setAvgprice(Long.valueOf(AmountUtils.changeY2F(project.getAvgpriceStr()))*10000);
		
		baseService.update("projects.update", project);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		    
	} 
	
	@RequestMapping(value="/projects/my/detail/", method=RequestMethod.GET)
	public String detail(HttpServletRequest request,Integer id) throws Exception{
		
		Projects  project = baseService.queryObject("projects.getOne", id);      
		
		request.setAttribute("pattJson",JsonUtils.obj2json(baseService.queryList("project_attach.getAll", project.getProid()))); 
		
		request.setAttribute("pimgsJson",JsonUtils.obj2json(baseService.queryList("project_imgs.getAll", project.getProid()))); 
		
		request.setAttribute("project",project); 
		
		return "/admin/projects/my/detail_project"; 
	} 

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 
	

}
