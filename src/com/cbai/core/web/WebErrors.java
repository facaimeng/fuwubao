package com.cbai.core.web;

import java.io.Serializable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;

public class WebErrors extends com.cbai.core.web.CoreErrors {
	
	public WebErrors() { }

	public WebErrors(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * 通过HttpServletRequest创建WebErrors
	 * 
	 * @param request
	 *            从request中获得MessageSource和Locale，如果存在的话。
	 * @return 如果LocaleResolver存在则返回国际化WebErrors
	 */
	public static WebErrors create(HttpServletRequest request) {
		return new WebErrors(request);
	}
	
	/**
	 * 构造器
	 * 
	 * @param messageSource
	 * @param locale
	 */
	public WebErrors(MessageSource messageSource, Locale locale) {
		super(messageSource, locale);
	}
    
	/**
	 * 非站点内数据
	 * 
	 * @param clazz
	 * @param id
	 */
	public void notInSite(Class<?> clazz, Serializable id) {
		addErrorCode("error.notInSite", clazz.getSimpleName(), id);
	}
	
	/**
	 * email正则表达式
	 */
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+$");
	
	/**
	 * username正则表达式
	 */
	public static final Pattern USERNAME_PATTERN = Pattern.compile("^[0-9a-zA-Z\\u4e00-\\u9fa5\\.\\-@_]+$");
	
	public static final Pattern ENGISHNAME_PATTERN = Pattern.compile("^[0-9a-zA-Z\\-\\s_]+$");
	

	/*****************************************/
	/*************通用的错误判断方法*************/
	/*****************************************/
	public void noPermission(Class<?> clazz, Serializable id) {
		addErrorCode("error.noPermission", clazz.getSimpleName(), id);
	}
	
	public boolean ifNull(Object object, String field) {
		
		if (null == object) {
			addErrorCode("error.required", field);
            return true;
        }
		
        if ((object instanceof String)){
        	if( "".equals(((String)object).trim()) ){
        		addErrorCode("error.required", field);
        		return true;
        	}
        }

        return false;
        
	}
	
	
	public boolean ifEmpty(Object[] o, String field) {
		if (o == null || o.length <= 0) {
			addErrorCode("error.required", field);
			return true;
		} else {
			return false;
		}
	}

	public boolean ifBlank(String s, String field) {
		
		if (StringUtils.isBlank(s)) {
			addErrorCode("error.required", field);
			return true;
		}
		
		return false;
	}

	public boolean ifMaxLength(String s, String field, int maxLength) {
		if (s != null && s.length() > maxLength) {
			addErrorCode("error.maxLength", field, maxLength);
			return true;
		}
		return false;
	}

	public boolean ifOutOfLength(String s, String field, int minLength,
			int maxLength) {
		if (s == null) {
			addErrorCode("error.required", field);
			return true;
		}
		int len = s.length();
		if (len < minLength || len > maxLength) {
			addErrorCode("error.outOfLength", field, minLength, maxLength);
			return true;
		}
		return false;
	}

	public boolean ifNotEmail(String email, String field) {
		Matcher m = EMAIL_PATTERN.matcher(email);
		if (!m.matches()) {
			addErrorCode("error.email", field);
			return true;
		}
		return false;
	}

	public boolean ifNotUsername(String username, String field, int minLength,
			int maxLength) {
		if (ifOutOfLength(username, field, minLength, maxLength)) {
			return true;
		}
		Matcher m = USERNAME_PATTERN.matcher(username);
		if (!m.matches()) {
			addErrorCode("error.username", field);
			return true;
		}
		return false;
	}

	public boolean ifNotExist(Object o, Class<?> clazz, Serializable id) {
		if (o == null) {
			addErrorCode("error.notExist", clazz.getSimpleName(), id);
			return true;
		} else {
			return false;
		}
	}
	
}
