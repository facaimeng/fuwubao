package com.cbai.model.rongyin.sys.content.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
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
import com.cbai.model.rongyin.ibatis.entity.Articleptype; 
 
@Controller 
@RequestMapping(value="system")
public class ArticleptypeAction { 
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils;  
	
	@RequestMapping(value="/content/article/aptype/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request,String aptype) throws Exception {
    	
    	request.setAttribute("aptype", aptype.toUpperCase());
		
    	return "/admin/content/article/aptype/list_type"; 
	}
	
	
	@RequestMapping(value="/content/article/aptype/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,String aptype) throws Exception{ 
		
		Map nmap = new HashMap();
		
		nmap.put("aptype", aptype);
		
		Pagination page = baseService.queryPagination("article_ptype.getAll", nmap, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		SysJsonResultObject result = new SysJsonResultObject();  
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
	}
 
	/**
	 * 进入添加  
	 * */
	@RequestMapping(value="/content/article/aptype/enter_add/", method=RequestMethod.GET)
	public String enterAdd() { 
						
		return "/admin/content/article/aptype/add_type";
	}
	
	@RequestMapping(value="/content/article/aptype/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,Articleptype articleptype) throws Exception{
		
		articleptype.setAddtime(new Date());
		
		articleptype.setViewcount(0);
		
		Map nmap = new HashMap();
		
		nmap.put("aptype", articleptype.getAptype());
				 
		articleptype.setSindex(baseService.getTotalRecordCnt("article_ptype.getAll", nmap)+1);
 
		baseService.save("article_ptype.insert", articleptype);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	/**
	 * 进入修改 
	 * */
	@RequestMapping(value="/content/article/aptype/enter_edit/", method=RequestMethod.GET)
	public String enterEdit(HttpServletRequest request,Integer id) throws Exception{
		
		Articleptype articleptype = (Articleptype) baseService.queryObject("article_ptype.getOne", id); 
		
		request.setAttribute("articleptype", articleptype);
				
		return "/admin/content/article/aptype/edit_type";
	}
	
	/**
	 * 修改 
	 * */
	@RequestMapping(value="/content/article/aptype/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Articleptype articleptype) throws  Exception{
		
		baseService.update("article_ptype.updateOne", articleptype);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	/**
	 * 排序
	 * */
	@RequestMapping(value="/content/article/aptype/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,Articleptype articleptype) throws Exception{
		
		if(articleptype.getOldOrderNum().intValue()!=articleptype.getNewOrderNum().intValue()){
			
			Map<String, String> sortMaps = new HashMap<String, String>();
			
			sortMaps.put("aptype", articleptype.getAptype());
			
			baseService.updateIndex("aptid", "article_ptype", articleptype.getAptid(), articleptype.getOldOrderNum(),articleptype.getNewOrderNum(),sortMaps);
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}

	/**
	 * 删除 
	 * @throws IOException 
	 * */
	@RequestMapping(value="/content/article/aptype/del/", method=RequestMethod.GET)
	public void del(HttpServletResponse response,String aptype,Integer id) throws Exception{
		
		Map amap = new HashMap();
		
		amap.put("aptype", aptype);
  
		baseService.deleteSort("article_ptype",id, amap); 	
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}


	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
	
  
}
