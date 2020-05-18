package com.cbai.model.common.permission.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.service.BaseService;
import com.cbai.common.service.SessionContextService;
import com.cbai.common.utils.OptUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;
import com.cbai.model.common.ibatis.entity.PerMenu;
import com.cbai.model.common.permission.vo.PerUserVO;  
 
 
@Controller  
public class AccountAction{

	private BaseService baseService; 
 
    private PropertyUtils propertyUtils; 
    
    private SessionContextService sessionContextService;
    
    
	/**
	 * 进入登录页
	 */
	@RequestMapping(value="/account/fkadin", method=RequestMethod.GET)
	public String login(HttpServletRequest request) throws Exception {
		
		String  rs = "0";
		
		HttpSession session = request.getSession(false);
		
		if(session != null){ 
			
			Object tempUser = session.getAttribute("userVO"); 
			 
			if(tempUser != null){
				return "redirect:/system/home/";
			}
		}
	
		return "/admin/admin_login";
		
	}
	
	/**
	 * 后台登陆
	 */
	@RequestMapping(value="/account/system/dologin", method=RequestMethod.POST)
	public void dologin(HttpServletRequest request,HttpServletResponse response,String uname,String pwd) throws Exception {
		
		JsonResultObject result = new JsonResultObject(); 
		
		String code = "0";
		
		String msg = null;

		if (uname != null && !"".equals(uname.trim())&& pwd != null && !"".equals(pwd.trim())) {
			
			Map<String,String> param = new HashMap<String,String>();
			param.put("phone", uname);
			param.put("password", OptUtil.getStringMD5(pwd));
			
			PerUserVO perUserVO = baseService.queryObject("per_login.adminLogin", param);
			
			if(perUserVO !=null){
				
				if(perUserVO.getState().equals("LOCKED")){
					
					result.setCode("0");
					
					result.setMessage(propertyUtils.getPropertiesString("error.login.locked")); 
					
					ResponseUtils.renderJson(response, JsonUtils.obj2json(result)); 
				} 
				 
				Map param1 = new HashMap();
				
				param1.put("userid", perUserVO.getUserid());
				
				param1.put("lastlogin", new Date());
				
				baseService.update("per_login.upLastlogin", param1);//更新最后登陆时间
				 
				HttpSession session = request.getSession();
				
				session.setAttribute("userVO",perUserVO);
				
				  //删除session里之前登录的信息 
			    if(sessionContextService.hasLoginSys(perUserVO.getPhone())){ 
			    	
				  sessionContextService.forceLogoutSys(perUserVO.getPhone());
				  
			    }
			    //把当前登录的信息加入onlineMap
			    sessionContextService.addSessionSys(perUserVO.getPhone(),session);
				
				code = "1";
				
				msg = "suc.op";
			
			}else{
				msg = "error.login.nameorpwd"; 
			}
		}else{
			msg = "error.login.noinfo"; 
		}  
		
		result.setCode(code);
		
		result.setMessage(propertyUtils.getPropertiesString(msg)); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
		
	}
	
	/**
	 * 管理员退出登录
	 */
	@RequestMapping(value="/system/logout", method=RequestMethod.GET)
	public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String  rs = "0";
		
		HttpSession session = request.getSession(false);
		
		if(session != null){ 
			
			Object tempUser = session.getAttribute("userVO"); 
			 
			if(tempUser != null){
				
				/*SysloginLogs loginLogs = new SysloginLogs();
	 	
				loginLogs.setSessionid(session.getId()); 
				
				loginLogs.setOuttype(State.MANOUT); 
				
				loginLogs.setOuttime(new Date());
	 
				baseService.update("sys_login_logs.updateOne" , loginLogs);*/
	
				session.removeAttribute("userVO");	
				
				sessionContextService.delSessionSys(((PerUserVO)tempUser).getPhone());
				
				rs = "1";
			}   
		} 
		JsonResultObject results = new JsonResultObject();  
    	
    	results.setCode(rs);
    	
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(results));   
	}
	
	
	
 

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
    @Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setSessionContextService(SessionContextService sessionContextService) {
		this.sessionContextService = sessionContextService;
	}
    
    
	
}
