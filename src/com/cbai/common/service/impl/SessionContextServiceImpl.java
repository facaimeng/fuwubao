package com.cbai.common.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.cbai.common.service.BaseService;
import com.cbai.common.service.SessionContextService;
import com.cbai.model.common.data.State;
import com.cbai.model.common.ibatis.entity.MemloginLogs; 
 

public class SessionContextServiceImpl implements SessionContextService{ 
	
	private BaseService baseService;
	
	private HashMap onlineMap;   
	
	private HashMap onlineSysMap;  
	    
	public SessionContextServiceImpl() {    
		onlineMap = new HashMap();  
		onlineSysMap = new HashMap();  
	}       
	
	public boolean hasLogin(String phone){
		
		if(onlineMap.containsKey(phone)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasLoginSys(String phone){
		
		if(onlineSysMap.containsKey(phone)){
			return true;
		}else{
			return false;
		}
	}
	    
	public synchronized void addSession(String phone,HttpSession session) {    
		onlineMap.put(phone, session);  
	} 
	
	public synchronized void addSessionSys(String phone,HttpSession session) {    
		onlineSysMap.put(phone, session);  
	}
	    
	/*
	 * sessionListener失效时用
	 * */
	public synchronized void delSession(String ukey) {    
		onlineMap.remove(ukey);  
		/*if(session != null){
			 // 删除单一登录中记录的变量
			if(session.getAttribute("memberVO")!=null){
				
				MemberVO memberVO =  (MemberVO)session.getAttribute("memberVO");
				
				onlineMap.remove(memberVO.getNickname());     
			} 
		} */
	} 
	
	public synchronized void delSessionSys(String ukey) {    
		onlineSysMap.remove(ukey);   
	} 
	    
	public synchronized HttpSession getSession(String session_id) {    
		 
		return (HttpSession) onlineMap.get(session_id);    
	}
	
	public synchronized HttpSession getSessionSys(String session_id) {    
		 
		return (HttpSession) onlineSysMap.get(session_id);    
	}
 
	
	/*
	 * 登录方法用
	 * */
	public void forceLogoutMem(String phone) throws SQLException { 
		
		// 删除单一登录中记录的变量
		HttpSession hs = (HttpSession) onlineMap.get(phone);
		
    	onlineMap.remove(phone);
    	
        Enumeration e = hs.getAttributeNames();
        
        while (e.hasMoreElements()) {
        	
            String sessionName = (String) e.nextElement();
            // 清空session
            hs.removeAttribute(sessionName);
        } 
        
        /*
         * MemloginLogs mloginLogs = new MemloginLogs();
	 	
		mloginLogs.setSessionid(hs.getId()); 
		
		mloginLogs.setOuttype(State.KOUT); 
		
		mloginLogs.setOuttime(new Date());

		baseService.update("wx_mem_login.upLoginLog" , mloginLogs);
		 
        baseService.delete("mem_login_logs.delete", hs.getId());
        */
         
	}
	 
	public void forceLogoutSys(String phone) throws Exception {
		
		HttpSession hs = (HttpSession) onlineSysMap.get(phone);
		
		onlineSysMap.remove(phone);
    	
        Enumeration e = hs.getAttributeNames();
        
        while (e.hasMoreElements()) {
        	
            String sessionName = (String) e.nextElement();
            // 清空session
            hs.removeAttribute(sessionName);
        }  
	}

	public HashMap getOnlineMap() {
		return onlineMap;
	}

	public void setOnlineMap(HashMap onlineMap) {
		this.onlineMap = onlineMap;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setOnlineSysMap(HashMap onlineSysMap) {
		this.onlineSysMap = onlineSysMap;
	}

	public HashMap getOnlineSysMap() {
		return onlineSysMap;
	} 

	
}
