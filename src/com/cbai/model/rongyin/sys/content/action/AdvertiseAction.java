
package com.cbai.model.rongyin.sys.content.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random; 

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
import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.Advertise; 
import com.cbai.model.rongyin.ibatis.entity.ProjectAttach;
 
/**
 * 页面广告管理 
 *
 */
@Controller 
@RequestMapping(value="system")
public class AdvertiseAction { 
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
	
	
	@RequestMapping(value="/content/advertise/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request,String ntype) throws Exception { 
		
    	return "/admin/content/advertise/list_advertise";
		
	}
	
	@RequestMapping(value="/content/advertise/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,String t,Integer pageIndex,Integer limit) throws Exception {
		
		SysJsonResultObject result = new SysJsonResultObject();  
		
		Map<String, Object> map= new HashMap<String, Object>(); 
		
		map.put("adtype", t);
		
		Pagination page = baseService.queryPagination("advertise.getAll", map, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result)); 
		
	} 
	
 
	@RequestMapping(value="/content/advertise/enter_add/", method=RequestMethod.GET)
	public String enterAdd(HttpServletRequest request,String ntype) throws Exception{ 
				
		return "/admin/content/advertise/add_advertise";
	}
	 
	@RequestMapping(value="/content/advertise/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,Advertise advertise) throws Exception{ 
		
		advertise.setAddtime(new Date());
		
		advertise.setState(State.NORMAL);
		
		Map<String, Object> map= new HashMap<String, Object>(); 
		
		map.put("adtype", advertise.getAdtype());
		
		advertise.setSindex(baseService.getTotalRecordCnt("advertise.getAll", map)+1);
		
		baseService.save("advertise.insert", advertise);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));
	}
 
    @RequestMapping(value="/content/advertise/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{ 
		
		request.setAttribute("advertise", baseService.queryObject("advertise.getOne", id));
				
		return "/admin/content/advertise/edit_advertise";
	} 
 
    @RequestMapping(value="/content/advertise/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Advertise advertise) throws Exception{
		
		baseService.update("advertise.updateOne", advertise); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));
	}
	/**
	 * 锁定/解锁广告
	 * */
    @RequestMapping(value="/content/advertise/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		Advertise advertise = new Advertise();
			
		advertise.setAdid(id);
		
		advertise.setState(State.valueOf(state));
		
		baseService.update("advertise.updateOne", advertise);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));
	}
	
    
    @RequestMapping(value="/content/advertise/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,Advertise advertise) throws  Exception{
		
		if(advertise.getOldOrderNum().intValue()!=advertise.getNewOrderNum().intValue()){
			
			Map<String, String> sortMaps = new HashMap<String, String>();
			
			sortMaps.put("adtype", advertise.getAdtype());
			
			baseService.updateIndex("adid", "advertise", advertise.getAdid(), advertise.getOldOrderNum(),advertise.getNewOrderNum(),sortMaps);
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
    @RequestMapping(value="/content/advertise/del/", method=RequestMethod.GET)
	public void del(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception{
		
    	Advertise adv = baseService.queryObject("advertise.getOne", id);
    	 
		try {
			FileUtil.delFiles(request.getSession().getServletContext().getRealPath("/")+adv.getImgurl());	
		} catch (Exception e) {
			e.printStackTrace();
 
		}   
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("adtype", adv.getAdtype());
		
		baseService.deleteSort("advertise", id,map);  
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
  
}
