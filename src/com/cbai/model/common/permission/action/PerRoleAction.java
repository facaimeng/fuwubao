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

import org.apache.commons.lang.StringUtils;
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
import com.cbai.model.common.ibatis.entity.PerMenuRoleRel;
import com.cbai.model.common.ibatis.entity.PerRole;
import com.cbai.model.common.permission.service.PerMenuService;
import com.cbai.model.common.permission.service.PerRoleService;

@Controller
@RequestMapping(value="system")
public class PerRoleAction { 
	
	private PerRoleService perRoleService;
	
	private BaseService baseService;  
	
	private PropertyUtils propertyUtils; 
	
	List<PerRole> roleList;
	//https://blog.csdn.net/gebitan505/article/details/78064297
	
	@RequestMapping(value="/common/permission/role/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request,Integer roleid) throws Exception {
		
		if(roleid==null){
			roleid = 1;			
		}
		request.setAttribute("roleid", roleid);
		 
		//PerMenu perMenu = baseService.queryObject("per_menu.getNavOne", menuid);
		
		//getNav(menuid);
		
		//Collections.reverse(menuList); 
		
    	return "/admin/sysset/permission/role/list_role";
		
	}
	
	@RequestMapping(value="/common/permission/role/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,Integer roleid) throws Exception{
		
		//roleList = new ArrayList<PerMenu>(); 
	    
		Map map = new HashMap();
		
		map.put("fid", roleid);
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("per_role.getAll", map, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));     
	}
	
	@RequestMapping(value="/common/permission/role/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request,Integer roleid) throws Exception{ 
		
		roleList = new ArrayList<PerRole>();
		
		getNav(roleid);
		
		Collections.reverse(roleList); 
		
		request.setAttribute("roleList", roleList);
		 
		return "/admin/sysset/permission/role/add_role";
		
	}
 
	
	@RequestMapping(value="/common/permission/role/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,PerRole perRole) throws Exception{
		
		perRole.setPath(perRole.getPath()+perRole.getName());
		
		perRole.setAddtime(new Date());
		
		perRole.setState(State.NORMAL); 
		
		//Map map = new HashMap();
		
		//map.put("fid", perRole.getFid());
 
		//perMenu.setSindex(baseService.getTotalRecordCnt("per_role.getAll",map)+1); 
 
		perRoleService.savePerRole(perRole);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
	}
 
	

	@RequestMapping(value="/common/permission/role/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("perrole", baseService.queryObject("per_role.getOne", id));
		
		request.setAttribute("hasMenusStr", StringUtils.join(baseService.queryList("per_menu_role_rel.getRoleMenuIDS", id), ","));
		  
		return "/admin/sysset/permission/role/edit_role";
		
	}
	
	@RequestMapping(value="/common/permission/role/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,PerRole perRole) throws Exception{
		
		if(!perRole.getAddMenuIDS().equals("-1")||!perRole.getDelMenuIDS().equals("-1")){
			perRoleService.updatePerRole(perRole);
		}else{
			baseService.update("per_menu.updateOne", perRole);
		} 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
	}
 

	@RequestMapping(value="/common/permission/role/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
 
		PerRole perRole = new PerRole();
			
		perRole.setRoleid(id);
		
		perRole.setState(State.valueOf(state));

		baseService.update("per_role.updateOne", perRole);

		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
	}
	
	/**
	 * 根据角色ID获得某角色已经勾选的菜单
	 * @throws SQLException 
	 * */
	@RequestMapping(value="/common/permission/role/get_has_menu/", method=RequestMethod.POST)
	public void get_has_menu(HttpServletResponse response,Integer menuFid,Integer roleid,String op) throws Exception{ 
		
		if(menuFid==null){
			menuFid = 1;			
		}
		
		PerMenu curMenu = baseService.queryObject("per_menu.getOne", menuFid);
		
		Map map1 = new HashMap();
		
		map1.put("fid", menuFid);
		
		map1.put("state", State.NORMAL.toString()); 
		
		List<PerMenu> perMenuList = baseService.queryList("per_menu.getForRole",map1);//查出某一菜单类别下的所有子,初始化的时候menuFid=0
		
		Map map2 = new HashMap();
		
		map2.put("roleid", roleid);
		
		map2.put("menuFid", menuFid);
		
		map2.put("state", State.NORMAL.toString());
 
		List<PerMenuRoleRel> hasMenuList = baseService.queryList("per_menu_role_rel.getHasMenu",map2);//查出某一角色所包含某一层级下的菜单项 
		
		StringBuilder jsonObject = new StringBuilder(""); 
		
		jsonObject.append("["); 
		
		for(PerMenu perMenu : perMenuList){
			
			jsonObject.append("{id:"+perMenu.getMenuid()+",");
			
			jsonObject.append("name:'"+perMenu.getName()+"'");
			
			if(perMenu.getIsdir().equals("Y")){
				jsonObject.append(",isParent:true");
			} 
			
			/*if(perMenu.getChildCount()!=null && perMenu.getChildCount().intValue()>0){
				jsonObject.append(",isParent:true");
			} */
			
			/*if(roleid!=0){
				jsonObject.append(",chkDisabled :true"); 
			}*/
			
			/*if(hasMenuList.size()>0){
				for(PerMenuRoleRel pmrl : hasMenuList){
					
					if(perMenu.getMenuid().intValue() == pmrl.getMenuid().intValue()){
						
						jsonObject.append(",checked :true"); 
						
						jsonObject.append(",chkDisabled :true"); 
					} else if(curMenu.getIsdir().equals("Y")){
						jsonObject.append(",chkDisabled :true"); 
					}
				}
			}else{
				jsonObject.append(",chkDisabled :true"); 
			}*/
			if(op!=null && op.equals("1")){
				jsonObject.append(",chkDisabled :true"); 
			}
			
			if(hasMenuList.size()>0){
				for(PerMenuRoleRel pmrl : hasMenuList){
					
					if(perMenu.getMenuid().intValue() == pmrl.getMenuid().intValue()){
						
						jsonObject.append(",checked :true");  
					}  
				}
			} 
			 
			jsonObject.append("},"); 
			 
		}
		jsonObject.deleteCharAt(jsonObject.length() - 1);
		
		jsonObject.append("]");  
		
		ResponseUtils.renderJson(response, jsonObject.toString());  
	
	}
	
	/**
	 * 获得导航栏
	 * @throws SQLException 
	 * @throws IOException 
	 * */
	public void getNav(Integer roleid) throws SQLException{
 
		PerRole pr =  baseService.queryObject("per_role.getNavOne", roleid);
		
		roleList.add(pr);
		
		if(pr.getFid().intValue()!=0){
			getNav(pr.getFid());
		} 	 
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

	public void setPerRoleService(PerRoleService perRoleService) {
		this.perRoleService = perRoleService;
	}

	 
	
}
