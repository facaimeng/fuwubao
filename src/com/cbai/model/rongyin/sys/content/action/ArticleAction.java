package com.cbai.model.rongyin.sys.content.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
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
import com.cbai.model.rongyin.ibatis.entity.Article;
import com.cbai.model.rongyin.ibatis.entity.Articletype;
import com.cbai.model.rongyin.sys.content.vo.NewsSearchVO;
   
  
@Controller 
@RequestMapping(value="system")
public class ArticleAction { 
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    @RequestMapping(value="/content/article/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request,Integer aptid,Integer atid) throws Exception { 
    	
    	request.setAttribute("atype", baseService.queryObject("article_type.getOne", atid));
		
		request.setAttribute("aptypeOne", baseService.queryObject("article_ptype.getOne", aptid));
    	
    	return "/admin/content/article/list_article"; 
	}
	
    @RequestMapping(value="/content/article/search/", method=RequestMethod.GET)
	public void search(HttpServletRequest request,HttpServletResponse response,Integer pageIndex,Integer limit,Integer aptid,Integer atid,String searchField ,String searchKeyword) throws Exception{
		
		Map amap = new HashMap();
		
		amap.put("atid", atid);
		
		if(searchField!=null&&!searchField.equals("atid")){
			amap.put(searchField, searchKeyword); 
		}
 
		Pagination page = baseService.queryPagination("article.getAll", amap, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		SysJsonResultObject result = new SysJsonResultObject();  
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));    
	}
	
    @RequestMapping(value="/content/article/enter_add/", method=RequestMethod.GET)
	public String enterAdd(HttpServletRequest request,Integer aptid,Integer atid) throws Exception { 
    	
    	request.setAttribute("atype", baseService.queryObject("article_type.getOne", atid));
		
		request.setAttribute("aptypeOne", baseService.queryObject("article_ptype.getOne", aptid));
						
		return "/admin/content/article/add_article";
	}
	
	@RequestMapping(value="/content/article/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,Article article) throws Exception{
		
		article.setPubtime(new Date());
		
		article.setViewcount(0);
		
		Map nmap = new HashMap();
		
		nmap.put("atid", article.getAtid());
				 
		article.setSindex(baseService.getTotalRecordCnt("article.getAll", nmap)+1);
 
		baseService.save("article.insert", article);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	 
	@RequestMapping(value="/content/article/enter_edit/", method=RequestMethod.GET)
	public String enterEdit(HttpServletRequest request,Integer id,Integer aptid,Integer atid) throws Exception{
		
		Article article = (Article) baseService.queryObject("article.getOne", id); 
		
		request.setAttribute("article", article);
		
		request.setAttribute("atype", baseService.queryObject("article_type.getOne", atid));
		
		request.setAttribute("aptypeOne", baseService.queryObject("article_ptype.getOne", aptid));
				
		return "/admin/content/article/edit_article";
	}
	
	/**
	 * 修改 
	 * */
	@RequestMapping(value="/content/article/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Article article) throws  Exception{
		
		baseService.update("article.updateOne", article);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	/**
	 * 排序
	 * */
	@RequestMapping(value="/content/article/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,Article article) throws Exception{
		
		if(article.getOldOrderNum().intValue()!=article.getNewOrderNum().intValue()){
			
			Map sortMaps = new HashMap ();
			
			sortMaps.put("atid", article.getAtid().toString());
			
			baseService.updateIndex("aid", "article", article.getAid(), article.getOldOrderNum(),article.getNewOrderNum(),sortMaps);
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}

	/**
	 * 删除 
	 * @throws IOException 
	 * */
	@RequestMapping(value="/content/article/del/", method=RequestMethod.GET)
	public void del(HttpServletResponse response,Integer id,Integer atid) throws Exception{
		
		Map amap = new HashMap();
		
		amap.put("atid", atid);
  
		baseService.deleteSort("article",id, amap); 	
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}


	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
 
	
}
