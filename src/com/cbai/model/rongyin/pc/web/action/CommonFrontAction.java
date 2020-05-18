package com.cbai.model.rongyin.pc.web.action;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;  

import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;

import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.Articleptype;
import com.cbai.model.rongyin.ibatis.entity.Articletype;
import com.cbai.model.rongyin.ibatis.entity.News;

/**
 * 前台查询
 */
@Controller 
public class CommonFrontAction { 
	
	private BaseService baseService; 	
	
	/**
	 * 首页内容查询
	 * @return
	 * @throws Exception
	 */ 
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception{
		 
		Map<String, Object> map= new HashMap<String, Object>(); 
		
		map.put("ntype", State.RONGYIN_POINT);
		request.setAttribute("rpList", baseService.queryPaginatedList("common_front.getNewsIndex", map, 0, 4));
		
		map.put("ntype", State.OURNEWS);
		request.setAttribute("ournewsList", baseService.queryPaginatedList("common_front.getNewsIndex", map, 0, 5));
		
		map.put("ntype", State.NOTICE);
		request.setAttribute("noticeList", baseService.queryPaginatedList("common_front.getNewsIndex", map, 0, 5));
		
		map.put("ntype", State.WEALTH_CLASS);
		request.setAttribute("wcList", baseService.queryPaginatedList("common_front.getNewsIndex", map, 0, 5)); 
		
		map.put("adtype", "1");
		  
		request.setAttribute("adList", baseService.queryList("advertise.getFront", map));
		
		request.setAttribute("from", "index");
		
		return "/front/web/index";
	} 
 
	
	/**
	 * 会员二级首页
	 * @return
	 * @throws Exception
	 */ 
	@RequestMapping(value={"/recommend"}, method = RequestMethod.GET)
	public String mem_recommend(HttpServletRequest request) throws Exception{
		return "/front/member/index";
	}
	
	@RequestMapping(value={"{from:news|service|aboutus}","/member/{from:security}"}, method=RequestMethod.GET)
	public String toOne(@PathVariable String from)throws Exception {
		String dispath="";
		if(from.equals("news")){
			dispath="forward:/news/notice/";
		}else if(from.equals("service")){
			dispath="forward:/service/online_finance/";
		}else if(from.equals("aboutus")){
			dispath="forward:/aboutus/intro/";
		}else if(from.equals("security")){
			dispath="/front/member/safe";
		}
		return dispath;
	}  
	
	/**
	 * 文章查询,可查出列表和独立单篇文章
	 * 
	 */ 
	@RequestMapping(value={"/{from:service}/{ntype:online_finance|assets_management|wealth_management}","/{from:news}/{ntype:notice|ournews|wealth_class|rongyin_point}","/{from:aboutus}/{ntype:intro|contact}"}, method=RequestMethod.GET)
	public String newsSearch(HttpServletRequest request,@PathVariable String from,@PathVariable String ntype,Integer p,Integer l,Integer y)throws Exception {
		
		String dispath = null;
		 
		Map<String,Object> nmap = new HashMap<String,Object>();
		
		nmap.put("ntype", ntype.toUpperCase());
 
		if(from.equals("news")){
			 
			List<String> years = baseService.queryPaginatedList("common_front.getNewsYears", nmap,0, 4);
			
			if(years!=null && years.size()>0){
				String year = null;
				
				if(y==null){
					year = years.get(0);
				}else{
					year = y.toString();
				}
				
				if(years.size()<4){
					int len = 4-years.size();
					int last = Integer.valueOf(years.get(years.size()-1));
					
					List<String> yearsNo = new ArrayList<String>();
					
					for(int i=1;i<=len;i++){
						yearsNo.add(String.valueOf(last-i)); 
					} 
					request.setAttribute("yearsNo", yearsNo);
				}else{
					nmap.put("byear", years.get(years.size()-1));
				}
				
				nmap.put("year", year);
				
				Pagination page = baseService.queryPagination("common_front.getNewsAll", nmap, p==null?0:p, l==null?15:l); 
				request.setAttribute("page", page);
				request.setAttribute("years", years);
				request.setAttribute("y", year); 
				
			} 
			
			dispath = "/front/web/news_list"; 
			
		}else{ 

			request.setAttribute("commonOne", baseService.queryObject("common_front.getNewsAll", nmap));
			
			if(ntype.equals("contact")){
				dispath = "/front/web/contact";
			}else if(ntype.equals("intro")){
				dispath = "/front/web/aboutus";
			}else if(from.equals("service")){
				dispath = "/front/web/service";
			}else{
				dispath = "/front/web/news_one";	
			} 
		} 
		request.setAttribute("newstype", State.valueOf(ntype.toUpperCase()));
		
		request.setAttribute("froms", State.valueOf(from.toUpperCase()));
 
		return dispath;
 
	}
	
	/**
	 * 列表文章单篇查询
	 */ 
	@RequestMapping(value={"/{from:news}/{ntype:notice|ournews|wealth_class|rongyin_point}/id_{id:\\d*}"}, method=RequestMethod.GET)
	public String newsContent(HttpServletRequest request,@PathVariable String from,@PathVariable String ntype,@PathVariable Integer id)throws Exception {
		
		String dispath = null;
 
		News news = (News) baseService.queryObject("common_front.getNewsOne", id);
		
		baseService.update("common_front.upNewsViewCount", id);
		
		Map<String,Object> nmap = new HashMap<String,Object>();
		
		nmap.put("nid", news.getNid());
		
		nmap.put("ntype", ntype.toUpperCase());
		
		request.setAttribute("pre", baseService.queryObject("common_front.getNewsPre", nmap));
		
		request.setAttribute("next", baseService.queryObject("common_front.getNewsNext", nmap)); 
		
		request.setAttribute("newstype", State.valueOf(ntype.toUpperCase())); 
		
		request.setAttribute("froms", State.valueOf(from.toUpperCase()));
		
		request.setAttribute("news", news);

		if(ntype.equals("resource")){
			dispath = "RESOURCEONE";
		}else{
			dispath = "/front/web/news_detail";	
		}
 
		return dispath;
	}
	
	@RequestMapping(value={"/content/sigle/{ntype:member|web}/id_{id:\\d*}"}, method=RequestMethod.GET)
	public String content_sigle(HttpServletRequest request,@PathVariable String ntype,@PathVariable Integer id)throws Exception { 
		
		News news = (News) baseService.queryObject("common_front.getNewsOne", id);
		
		baseService.update("common_front.upNewsViewCount", id);
		
		Map<String,Object> nmap = new HashMap<String,Object>();
		
		nmap.put("nid", news.getNid()); 
		
		request.setAttribute("news", news); 
		
		if(ntype.equals("member")){
			
			return "/front/member/content_sigle";
			
		}else{
			return "/front/web/content_sigle";
		}  
	}
	
	@RequestMapping(value={"/{aptype:faq}"}, method=RequestMethod.GET)
	public String getFaq(HttpServletRequest request,@PathVariable String aptype)throws Exception { 
		
		return "forward:/" + aptype + "/cata_0/class_0";
	}
	
	@RequestMapping(value={"/{aptype:faq}/cata_{aptid:\\d*}"}, method=RequestMethod.GET)
	public String getFaq1(HttpServletRequest request,@PathVariable String aptype,@PathVariable Integer aptid)throws Exception { 
		
		return "forward:/" + aptype + "/cata_" + aptid + "/class_0";
	}
	
	
	/**
	 * 查询自定义文章父类别ArticleType下所有子类别，若不指定aptid,则查询出sindex最大
	 */
	@RequestMapping(value={"/{aptype:faq}/cata_{aptid:\\d*}/class_{atid:\\d*}"}, method=RequestMethod.GET)
	public String getAllArticlepType(HttpServletRequest request,@PathVariable String aptype,@PathVariable Integer aptid,@PathVariable Integer atid,Integer p,Integer l)throws Exception {
		
		Map apmap = new HashMap();
		
		apmap.put("aptype", aptype.toUpperCase());
		
		List<Articleptype> aptList = baseService.queryList("article_ptype.getDefault", apmap); //页面左边产品类型列表
		
		request.setAttribute("aptList", aptList);
		
	    Articletype articletype = null; 
		
	    Articleptype articleptype = null;
		
		if(aptid==0){//得到sindex最大的父类 
			
			if(aptList!=null&&aptList.size()>0){
				articleptype = aptList.get(0);
			} 
		}else{
			articleptype = (Articleptype) baseService.queryObject("article_ptype.getFrontOne", aptid); 
		}
		request.setAttribute("articleptype", articleptype);
		
		if(atid==0){
			 
			Map map1 = new HashMap();
			
			map1.put("aptid", articleptype.getAptid());
			
			articletype = (Articletype) baseService.queryObject("article_type.getDefault", map1);
 
 		}else{
 			articletype = (Articletype) baseService.queryObject("article_type.getFrontOne", atid);
 
		} 
  
		if(articletype!=null){
			
			Map amap = new HashMap();
			
			amap.put("atid", articletype.getAtid()); 
			
			Pagination page = baseService.queryPagination("article.getAll", amap, p==null?0:p, l==null?15:l); 
			
			request.setAttribute("page", page);
			
			request.setAttribute("articletype", articletype);
			
			baseService.update("article_type.updateViewCount", articletype.getAtid());
		} 
 			
		return "/front/member/help_list";
	}
	 

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	 
}
