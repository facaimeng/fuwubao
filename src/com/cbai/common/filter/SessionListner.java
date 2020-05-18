package com.cbai.common.filter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener; 

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;  

import com.cbai.common.service.BaseService;
import com.cbai.common.service.SessionContextService;
import com.cbai.model.common.permission.vo.PerUserVO;
import com.cbai.model.rongyin.pc.member.vo.MemberVO; 

public class SessionListner implements HttpSessionListener, HttpSessionAttributeListener{ 

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		/*if(event.getName().equals("memberVO")){ 
			
			MemberVO memberVO = (MemberVO)event.getValue();
			
			Map<String,MemberVO> onlineMap = (HashMap<String,MemberVO>) event.getSession().getServletContext().getAttribute("onlineMap");
			
			onlineMap.put(event.getSession().getId(), memberVO);
			
			event.getSession().getServletContext().setAttribute("onlineMap", onlineMap);
			sessionContextService.addSession(event.getSession());  
		}*/
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		//System.out.println("1111========="); 
		// TODO Auto-generated method stub
		/*if(event.getName().equals("memberVO")){ 
			  
			Map<String,MemberVO> onlineMap = (HashMap<String,MemberVO>) event.getSession().getServletContext().getAttribute("onlineMap");
			
			onlineMap.remove(event.getSession().getId());
			
			event.getSession().getServletContext().setAttribute("onlineMap", onlineMap);  
		} */
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) { 
		
		HttpSession session = event.getSession();
		
		if(session!=null){
			
			Object temp = session.getAttribute("memberVO");
			
			if(temp!=null){
				
				ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());  
				
				SessionContextService sessionContextService =  (SessionContextService) application.getBean("sessionContextService");  
				
				sessionContextService.delSession(((MemberVO)temp).getPhone());
				
				/*MemloginLogs mloginLogs = new MemloginLogs();
			 	
				mloginLogs.setSessionid(session.getId()); 
				
				System.out.println("2222========="+session.getId()); 
				
				mloginLogs.setOuttype(State.AUTOOUT); 
				
				mloginLogs.setOuttime(new Date());
				
				BaseService baseService = (BaseService) application.getBean("baseService");  
				 
				try {
					baseService.delete("mem_login_logs.delete" , session.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			} 
			temp = session.getAttribute("userVO");
		    if(temp!=null){
				
				ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());  
				
				SessionContextService sessionContextService =  (SessionContextService) application.getBean("sessionContextService");  
				
				sessionContextService.delSessionSys(((PerUserVO)temp).getPhone()); 
			} 
		} 
		// TODO Auto-generated method stub
	/*	if(((HttpSessionBindingEvent) event).getName().equals("memberVO")){ 
			
			Map<String,MemberVO> onlineMap = (HashMap<String,MemberVO>) event.getSession().getServletContext().getAttribute("onlineMap");
			
			onlineMap.remove(event.getSession().getId());
			
			event.getSession().getServletContext().setAttribute("onlineMap", onlineMap);	
        }*/
	} 

}
