package com.cbai.common.filter;

import java.io.IOException;

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
 
public class AdminFilter implements Filter {
	private ServletContext context = null;

	public void init(FilterConfig filterConfig) throws ServletException {	
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();	
 
		Object adminAccount = session.getAttribute("userVO");
		
		if( adminAccount == null ){
			
			res.sendRedirect("/account/fkadin/");
		
		}else{
			
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	
	}
}


