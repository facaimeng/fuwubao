package com.cbai.common.service;   

import javax.servlet.http.HttpSession;
 
public interface SessionContextService {     
	
	public boolean hasLogin(String phone);
	    
	public void addSession(String phone,HttpSession session);    
	    
	/*
	 * sessionListener失效时用
	 * */
	public void delSession(String ukey);
	    
	public HttpSession getSession(String session_id);
	
	/*
	 * 登录方法用
	 * */
	public void forceLogoutMem(String phone) throws Exception; 
	
	
	public boolean hasLoginSys(String phone);
    
	public void addSessionSys(String phone,HttpSession session);    
	    
 
	public void delSessionSys(String ukey);
	    
	public HttpSession getSessionSys(String session_id);
 
	public void forceLogoutSys(String phone) throws Exception; 

}
