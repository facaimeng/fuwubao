package com.cbai.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.web.ResponseUtils;  
import com.cbai.model.rongyin.pc.member.vo.MemberVO;
 


public class MemberFilter implements Filter{
	
	public void init(FilterConfig filterConfig) throws ServletException {	
		
	}
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		  
		HttpServletRequest request = (HttpServletRequest) req;
		
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();	
 
		Object tmp = session.getAttribute("memberVO");
		
		if( tmp == null ){
			 
			//referer:判断来源，requestType判断请求类型
			String requestType = request.getHeader("X-Requested-With"); 
			 
			//ajax请求（1.请求超时）
		    if (requestType != null && requestType.equals("XMLHttpRequest")) {  
			    
			    ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0","error.outtime"))); 
		    }else{
		    	
		    	response.sendRedirect("/account/member/login/"); 
		    } 
		}else{
			MemberVO memVO = (MemberVO)tmp;
			
			if(memVO.getState().equals("LOCKED")){
				response.sendRedirect("/error/?msg=error.locked");
			}else if(memVO.getUsrcustid()==null){
				response.sendRedirect("/account/member/open/");
			}else{
				chain.doFilter(request, response);
			} 
		} 
	}

	public void destroy() {
	
	}
}
