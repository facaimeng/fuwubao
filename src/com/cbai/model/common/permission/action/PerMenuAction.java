package com.cbai.model.common.permission.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import com.cbai.model.common.data.State;
import com.cbai.model.common.ibatis.entity.PerMenu;
import com.cbai.model.common.permission.service.PerMenuService;

@Controller
@RequestMapping(value="system")
public class PerMenuAction { 
	
	private PerMenuService perMenuService;
	
	private BaseService baseService;  
	
	private PropertyUtils propertyUtils; 
	
	List<PerMenu> menuList;
	//https://blog.csdn.net/gebitan505/article/details/78064297
	
	@RequestMapping(value="/common/permission/menu/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request,Integer menuid) throws Exception {
		
		if(menuid==null){
			menuid = 1;			
		}
		request.setAttribute("menuid", menuid);
		 
		//PerMenu perMenu = baseService.queryObject("per_menu.getNavOne", menuid);
		
		//getNav(menuid);
		
		//Collections.reverse(menuList); 
		
    	return "/admin/sysset/permission/menu/list_menu";
		
	}
	
	@RequestMapping(value="/common/permission/menu/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,Integer menuid) throws Exception{
		
	    //menuList = new ArrayList<PerMenu>(); 
	    
		Map map = new HashMap();
		
		map.put("fid", menuid);
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("per_menu.getAll", map, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));     
	}
	
	@RequestMapping(value="/common/permission/menu/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request,Integer menuid) throws Exception{ 
		
		menuList = new ArrayList<PerMenu>();
		
		getNav(menuid);
		
		Collections.reverse(menuList); 
		
		request.setAttribute("menuList", menuList);
		 
		return "/admin/sysset/permission/menu/add_menu";
		
	}
 
	
	@RequestMapping(value="/common/permission/menu/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,PerMenu perMenu) throws Exception{
		
		perMenu.setPath(perMenu.getPath()+perMenu.getName());
		
		perMenu.setAddtime(new Date());
		
		perMenu.setState(State.NORMAL);
		
		Map map = new HashMap();
		
		map.put("fid", perMenu.getFid());
 
		perMenu.setSindex(baseService.getTotalRecordCnt("per_menu.getAll",map)+1); 
		
		perMenu.setIsdir("N");
 
		perMenuService.savePerMenu(perMenu);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
	}
 
	

	@RequestMapping(value="/common/permission/menu/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("permenu", baseService.queryObject("per_menu.getOne", id));
		  
		return "/admin/sysset/permission/menu/edit_menu";
		
	}
	
	@RequestMapping(value="/common/permission/menu/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,PerMenu perMenu) throws Exception{
		
		if(!perMenu.getName().equals(perMenu.getOldname())){
			perMenuService.updatePerMenu(perMenu);
		}else{
			baseService.update("per_menu.updateOne", perMenu);
		}
		 
		
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
	}
	
	 
	@RequestMapping(value="/common/permission/menu/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,PerMenu perMenu) throws Exception{
		
		if(perMenu.getOldOrderNum().intValue()!=perMenu.getNewOrderNum().intValue()){
			
			Map<String, String> sortMaps = new HashMap<String, String>();
			
			sortMaps.put("fid", perMenu.getFid().toString());
			
			baseService.updateIndex("menuid", "per_Menu", perMenu.getMenuid(), perMenu.getOldOrderNum(),perMenu.getNewOrderNum(),sortMaps);
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
	}

	@RequestMapping(value="/common/permission/menu/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
 
		PerMenu perMenu = new PerMenu();
			
		perMenu.setMenuid(id);
		
		perMenu.setState(State.valueOf(state));

		baseService.update("per_menu.updateOne", perMenu);

		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
	}
	
	/**
	 * 获得导航栏
	 * @throws SQLException 
	 * @throws IOException 
	 * */
	public void getNav(Integer menuid) throws SQLException{
 
		PerMenu pm =  baseService.queryObject("per_menu.getNavOne", menuid);
		
		menuList.add(pm);
		
		if(pm.getFid().intValue()!=0){
			getNav(pm.getFid());
		} 	 
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

	public void setPerMenuService(PerMenuService perMenuService) {
		this.perMenuService = perMenuService;
	} 
	
	
}
