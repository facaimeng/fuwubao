package com.cbai.model.rongyin.sys.content.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cbai.model.rongyin.ibatis.entity.Articletype;
 
@Controller 
@RequestMapping(value="system")
public class ArticletypeAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
	
	@RequestMapping(value="/content/article/atype/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request,Integer aptid) throws Exception { 
		
		request.setAttribute("aptypeOne", baseService.queryObject("article_ptype.getOne", aptid));
		
    	return "/admin/content/article/atype/list_type"; 
	}
	
	@RequestMapping(value="/content/article/atype/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,String aptid) throws Exception{ 
		
		Map amap = new HashMap();
		
		amap.put("aptid", aptid);
		
		Pagination page = baseService.queryPagination("article_type.getAll", amap, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		SysJsonResultObject result = new SysJsonResultObject();  
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
	}
  
	@RequestMapping(value="/content/article/atype/enter_add/", method=RequestMethod.GET)
	public String enterAdd(HttpServletRequest request,Integer aptid) throws Exception { 
		
		request.setAttribute("aptypeOne", baseService.queryObject("article_ptype.getOne", aptid));
						
		return "/admin/content/article/atype/add_type";
	}
	
	@RequestMapping(value="/content/article/atype/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,Articletype articletype) throws Exception{
		
		articletype.setAddtime(new Date());
		
		articletype.setViewcount(0);
		
		Map nmap = new HashMap();
		
		nmap.put("aptid", articletype.getAptid());
				 
		articletype.setSindex(baseService.getTotalRecordCnt("article_type.getAll", nmap)+1);
 
		baseService.save("article_type.insert", articletype);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	 
	@RequestMapping(value="/content/article/atype/enter_edit/", method=RequestMethod.GET)
	public String enterEdit(HttpServletRequest request,Integer id) throws Exception{
		
		Articletype articletype = (Articletype) baseService.queryObject("article_type.getOne", id); 
		
		request.setAttribute("articletype", articletype);
		
		request.setAttribute("aptypeOne", baseService.queryObject("article_ptype.getOne", articletype.getAptid()));
				
		return "/admin/content/article/atype/edit_type";
	}
	
	/**
	 * 修改 
	 * */
	@RequestMapping(value="/content/article/atype/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Articletype articletype) throws  Exception{
		
		baseService.update("article_type.updateOne", articletype);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	/**
	 * 排序
	 * */
	@RequestMapping(value="/content/article/atype/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,Articletype articletype) throws Exception{
		
		if(articletype.getOldOrderNum().intValue()!=articletype.getNewOrderNum().intValue()){
			
			Map sortMaps = new HashMap ();
			
			sortMaps.put("aptid", articletype.getAptid().toString());
			
			baseService.updateIndex("atid", "article_type", articletype.getAtid(), articletype.getOldOrderNum(),articletype.getNewOrderNum(),sortMaps);
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}

	/**
	 * 删除 
	 * @throws IOException 
	 * */
	@RequestMapping(value="/content/article/atype/del/", method=RequestMethod.GET)
	public void del(HttpServletResponse response,String aptid,Integer id) throws Exception{
		
		Map amap = new HashMap();
		
		amap.put("aptid", aptid);
  
		baseService.deleteSort("article_type",id, amap); 	
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}


	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
}
