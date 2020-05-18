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
import com.cbai.common.utils.FileUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.rongyin.ibatis.entity.CompanyAttach;
import com.cbai.model.rongyin.ibatis.entity.News;
import com.cbai.model.rongyin.sys.content.vo.NewsSearchVO;
import com.cbai.model.rongyin.sys.users.vo.LoanmanSearchVO;
import com.cbai.model.common.data.State; 

@Controller 
@RequestMapping(value="system")
public class NewsAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils;  
    
    @RequestMapping(value="/content/news/enter_one/", method=RequestMethod.GET)
    public String enter_one(HttpServletRequest request,String ntype) throws Exception{ 
    	
    	NewsSearchVO searchVO = new NewsSearchVO();
    	
    	searchVO.setNtype(ntype.toUpperCase());
		
		request.setAttribute("news", baseService.queryObject("news.getAll", searchVO));
		
		return "/admin/content/news/news_one";
	}
    
    @RequestMapping(value="/content/news/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request,String ntype) throws Exception {
    	
    	request.setAttribute("ntype", ntype.toUpperCase());
		
    	return "/admin/content/news/list_news";
		
	}
     
    @RequestMapping(value="/content/news/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,NewsSearchVO searchVO) throws Exception {
		
		SysJsonResultObject result = new SysJsonResultObject();  
		
		Pagination page = baseService.queryPagination("news.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result)); 
		
	}
 
    @RequestMapping(value="/content/news/enter_add/", method=RequestMethod.GET)
	public String enterAdd(HttpServletRequest request,String ntype) throws SQLException{
		
		request.setAttribute("newstype", State.valueOf(ntype.toUpperCase())); 
				
		return "/admin/content/news/add_news";
	}
	
    @RequestMapping(value="/content/news/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,News news) throws Exception{
 
		if(news.getPubtime()==null){
			
			news.setPubtime(new Date());
		} 
		
		news.setViewcount(0);
		
		news.setIfindex("N");
		
		/*NewsSearchVO searchVO = new NewsSearchVO();
		
		searchVO.setNtype(news.getNtype().toString());
 
		news.setSindex(baseService.getTotalRecordCnt("news.getAll", searchVO)+1);*/ 
 
		baseService.save("news.insert", news); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	/**
	 * 进入修改
	 * @throws Exception 
	 * */
    @RequestMapping(value="/content/news/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{ 
		
		request.setAttribute("news", baseService.queryObject("news.getOne", id));
				
		return "/admin/content/news/edit_news";
	}
	
    @RequestMapping(value="/content/news/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,News news) throws Exception{
		
    	baseService.update("news.updateOne", news);
		
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	
	}
	
	 
	/**
	 * 排序
	 * */
    @RequestMapping(value="/content/news/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,News news,Integer id,Map<String,String> sortMap) throws Exception{
		
		if(news.getOldOrderNum().intValue()!=news.getNewOrderNum().intValue()){
			
			baseService.updateIndex("nid", "news", id, news.getOldOrderNum(),news.getNewOrderNum(),sortMap);
		}
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}


    @RequestMapping(value="/content/news/del/", method=RequestMethod.GET)
	public void del(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception, IOException{
		
		News news = baseService.queryObject("news.getOne", id);
	 	 
		try {
			FileUtil.delFiles(request.getSession().getServletContext().getRealPath("/")+news.getCoverurl());	
		} catch (Exception e) {
			e.printStackTrace();
 
		}  
		baseService.delete("news.delete", id);   
		
		//Map nmap = new HashMap();
		
		//nmap.put("ntype", news.getNtype());
		
		//baseService.deleteSort("news", id, nmap);  
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
    
    
	 
}
