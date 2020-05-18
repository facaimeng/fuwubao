package com.cbai.model.rongyin.sys.projects.action;

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
import com.cbai.model.rongyin.ibatis.entity.ProjectImgs;

@Controller 
@RequestMapping(value="system")
public class ProjectImgsMgAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    @RequestMapping(value="/projects/imgs/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/projects/imgs/list_imgs";
		
	}
 
	@RequestMapping(value="/projects/imgs/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,Integer id) throws Exception {
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("project_imgs.getAll", id, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
		
	} 
	@RequestMapping(value="/projects/imgs/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("patt", baseService.queryObject("project_imgs.getOne", id)); 
		  
		return "/admin/projects/imgs/edit_imgs";
		
	}
	
	@RequestMapping(value="/projects/imgs/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,ProjectImgs patt) throws Exception{
		
		baseService.update("project_imgs.update", patt);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		
	}
	
	@RequestMapping(value="/projects/imgs/del/", method=RequestMethod.GET)
	public void del(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception {
		
		ProjectImgs patt = baseService.queryObject("project_imgs.getOne", id);
 	 
		try {
			FileUtil.delFiles(request.getSession().getServletContext().getRealPath("/")+patt.getFurl());	
		} catch (Exception e) {
			e.printStackTrace();
 
		}  
		baseService.delete("project_imgs.delete", id);  
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
	}
	 
 
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 


}
