package com.cbai.model.common.permission.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.json.JsonUtils;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.model.common.data.State;
import com.cbai.model.common.ibatis.entity.PerMenu;
import com.cbai.model.common.ibatis.entity.PerRole;
import com.cbai.model.common.ibatis.entity.PerUsersRoletRel;
import com.cbai.model.common.permission.vo.PerUserVO;

@Controller 
@RequestMapping(value="system")
public class PerCommonAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
	 
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession(false);
		
		PerUserVO userVO = (PerUserVO) session.getAttribute("userVO");
		
		if(userVO.getHasMenuJson()==null){
			
			Map map = new HashMap(); 
			
			map.put("state", State.NORMAL); 
			 
			// 原始的数据
		    List<PerMenu> rootList = baseService.queryList("per_menu.getAll", map);
		    
		  //得到登录用户拥有的角色包含的所有菜单
			List<Integer> hasMenuIDS = baseService.queryList("per_menu_role_rel.getBetween", userVO.getUserid());
			
			HashSet h = new HashSet(hasMenuIDS);
			
			hasMenuIDS.clear();
			
			hasMenuIDS.addAll(h);
			
			List<PerMenu> hasRootList = new ArrayList();
			
			if(hasMenuIDS!=null && hasMenuIDS.size()>0){
				for (PerMenu menu : rootList) {
					if(hasMenuIDS.contains(menu.getMenuid())){
						hasRootList.add(menu);
					} 
			    }
			} 
			rootList.clear();
		    
		    List<PerMenu> allList = new ArrayList();
		    
		    // 先找到所有的一级菜单
		    for(PerMenu menu : hasRootList){
		    	
		    	if (menu.getFid().intValue() == 1) {
		    		allList.add(menu);
		        } 
		    } 
		    
		    // 为一级菜单设置子菜单，getChild是递归调用的
		    for (PerMenu menu : allList) {
		        menu.setSonList(getChild(menu.getMenuid(), hasRootList));
		    }
		    userVO.setHasMenuJson(JsonUtils.obj2json(allList));
		    
		    session.setAttribute("userVO", userVO);
			
		} 
		
		return "/admin/admin_index";
    } 
	
	@RequestMapping(value="/tourl", method=RequestMethod.GET)
	public String tourl(HttpServletRequest request,Integer id) throws Exception{
		
		HttpSession session = request.getSession(false);
		
		PerUserVO user = (PerUserVO) session.getAttribute("userVO"); 
		
		String regex1 = "\\{\"menuid\":"+id+",[\\s\\S]*?\\}";
		
		Pattern pattern1 = Pattern.compile(regex1);
		
		Matcher matcher1 = pattern1.matcher(user.getHasMenuJson());
		
		while (matcher1.find()) {
		    if(matcher1.group(0)!=null){
		    	if(matcher1.group(0).contains("sonList")){
		    		
		    		String regex = "\"menuid\":"+id+",[\\s\\S]*?\\\"sonList\\\":([\\s\\S]*?\\])";
		    		
		    		Pattern pattern = Pattern.compile(regex);
		    		
		    		Matcher matcher = pattern.matcher(user.getHasMenuJson());
		    		
		    		while (matcher.find()) {
		    		    if(matcher.group(1)!=null){
		    		    	
		    		    	List<PerMenu> mlist = JsonUtils.json2list(matcher.group(1), PerMenu.class);
		    		    	
		    		    	request.setAttribute("mlist", mlist); 
		    		    }
		    		} 
		    	}  
		    }
		} 
		
		PerMenu menu = baseService.queryObject("per_menu.getOne", id);
		
		menu.setPath(menu.getPath().substring(6, menu.getPath().length()).replaceAll("/", " &gt; "));
		
		request.setAttribute("menu", menu);
		 
		return "forward:"+menu.getActionurl();
	}
	
	/**
	 * 递归查找子菜单
	 * 
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	private List<PerMenu> getChild(Integer menuid, List<PerMenu> rootList) {
	    // 子菜单
	    List<PerMenu> childList = new ArrayList<PerMenu>();
	    for (PerMenu menu : rootList) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (menu.getFid().intValue() == menuid.intValue()) {
	        	childList.add(menu);
	        }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (PerMenu menu : childList) {// Isdir==Y子菜单还有子菜单
	    	if (menu.getIsdir().equals("Y")) {
	            // 递归
	            menu.setSonList(getChild(menu.getMenuid(), rootList));
	        } 
	    } // 递归退出条件
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	} 
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
	

}
