package com.cbai.model.common.permission.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
  
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.OptUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;   
import com.cbai.model.common.ibatis.entity.PerRole;
import com.cbai.model.common.ibatis.entity.PerUsers; 
import com.cbai.model.common.ibatis.entity.PerUsersRoletRel;
import com.cbai.model.common.permission.service.PerUserService;
import com.cbai.model.common.permission.vo.PerUserSearchVO;

@Controller
@RequestMapping(value="system")
public class PerUsersMgAction {
	
	
	private BaseService baseService; 
	
	private PerUserService perUserService;
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/common/permission/users/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request) throws Exception {
    	
    	request.setAttribute("depList", baseService.queryList("per_department.getByState", State.NORMAL.toString()));
    	
    	return "/admin/sysset/permission/users/list_user";
		
	}
 
	@RequestMapping(value="/common/permission/users/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,PerUserSearchVO searchVO,Integer pageIndex,Integer limit) throws Exception { 
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("per_users.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
 	
	}
	
	@RequestMapping(value="/common/permission/users/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request) throws Exception{ 
		
		request.setAttribute("depList", baseService.queryList("per_department.getByState", State.NORMAL.toString()));
		
		Map map = new HashMap();
		
		map.put("fid", 1);
		
		map.put("state", State.NORMAL.toString());
		
		request.setAttribute("roleJson",JsonUtils.obj2json(baseService.queryList("per_role.getAll", map)));  
		  
		return "/admin/sysset/permission/users/add_user";
		
	}
	
	@RequestMapping(value="/common/permission/users/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,PerUsers puser) throws Exception{ 
		
		puser.setState(State.NORMAL); 
		
		puser.setPassword(OptUtil.getStringMD5(puser.getPassword()));
		
		puser.setAddtime(new Date());
		 
		if(puser.getRoleJson()==null||puser.getRoleJson().equals("")){
			
			baseService.save("per_users.insert", puser);
			
		}else{ 
			perUserService.savePerUser(puser);
		} 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));   
		
	}
	
	@RequestMapping(value="/common/permission/users/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("depList", baseService.queryList("per_department.getByState", State.NORMAL.toString()));
		
		request.setAttribute("puser", baseService.queryObject("per_users.getOne", id));
		
		Map map1 = new HashMap();
		
		map1.put("fid", 1); 
		
		map1.put("state", State.NORMAL.toString()); 
		
		List<PerRole> roleList =  baseService.queryList("per_role.getAll",map1); 
		
		map1.put("userid", id); 
		
		List<PerUsersRoletRel> urList =  baseService.queryList("per_users_role_rel.getHasRole",map1); 
		
		if(urList!=null && urList.size()>0){
			 
			for(PerRole role : roleList){
				
				for(PerUsersRoletRel rel : urList){
					
					if(role.getRoleid().intValue()==rel.getRoleid().intValue()){
						role.setIsSel("Y");
						break;
					} 
				} 
			} 
		} 
		request.setAttribute("roleJson",JsonUtils.obj2json(roleList)); 
		
		request.setAttribute("hasRoleJson",JsonUtils.obj2json(urList));
		  
		return "/admin/sysset/permission/users/edit_user";
		
	}
	
	@RequestMapping(value="/common/permission/users/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,PerUsers puser,String isRoleChange,String oldphone) throws Exception{
		
		String pwd = OptUtil.getStringMD5(puser.getPassword());
		
		if(!pwd.equals(OptUtil.getStringMD5("RY888888"))){
			puser.setPassword(pwd);
		}else{
			puser.setPassword(null);
		}
		perUserService.updatePerUser(puser,isRoleChange,oldphone); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/common/permission/users/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		PerUsers puser = new PerUsers();
			
		puser.setUserid(id);
		
		puser.setState(State.valueOf(state));
		
		baseService.update("per_users.updateOne", puser);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/common/permission/users/checkPhone", method=RequestMethod.POST)
	public void checkRegPhone(HttpServletResponse response,String phone) throws Exception { 
		String flag = null;   
		if(!phoneIsExist(phone)){
			flag = "1";
		}else{
			flag = "0";
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject(flag,null))); 
	}
	
	private boolean phoneIsExist(String phone)throws Exception{
   	 
    	int count = baseService.queryObject("per_users.phoneIsExist", phone);
    	
    	return count > 0;
    }
	
    @Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
    
    @Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

	public void setPerUserService(PerUserService perUserService) {
		this.perUserService = perUserService;
	} 


}
