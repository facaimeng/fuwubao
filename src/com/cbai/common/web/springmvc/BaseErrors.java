package com.cbai.common.web.springmvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * WEB错误信息
 * 
 * 可以通过MessageSource实现国际化。
 */
public abstract class BaseErrors {

	/**
	 * 默认构造器
	 */
	public BaseErrors() { }
	
	/**
	 * 通过HttpServletRequest创建WebErrors
	 * 
	 * @param request
	 *      从request中获得MessageSource和Locale，如果存在的话。
	 */
	public BaseErrors(HttpServletRequest request) {
		
		WebApplicationContext webApplicationContext = RequestContextUtils.getWebApplicationContext(request);
		
		if (webApplicationContext != null) {
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
			
			Locale locale;
			if (localeResolver != null) {
				locale = localeResolver.resolveLocale(request);
				this.messageSource = webApplicationContext;
				this.locale = locale;
			}
			
		}
	}

	/**
	 * 构造器
	 * 
	 * @param messageSource
	 * @param locale
	 */
	public BaseErrors(MessageSource messageSource, Locale locale) {
		this.messageSource = messageSource;
		this.locale = locale;
	}

	/**
	 * 从资源文件获得对应的消息内容
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	public String getMessage(String code, Object... args) {
		if (messageSource == null) {
			throw new IllegalStateException("MessageSource cannot be null.");
		}
		return messageSource.getMessage(code, args, locale);
	}
    
	/**
	 * 添加错误代码
	 * 
	 * @param code
	 *            错误代码
	 * @param args
	 *            错误参数
	 * @see org.springframework.context.MessageSource#getMessage
	 */
	public void addErrorCode(String code, Object... args) {
		getErrors().add(getMessage(code, args));
	}

	/**
	 * 添加错误代码
	 * 
	 * @param code
	 *            错误代码
	 * @see org.springframework.context.MessageSource#getMessage
	 */
	public void addErrorCode(String code) {
		getErrors().add(getMessage(code));
	}

	/**
	 * 添加错误字符串
	 * 
	 * @param error
	 */
	public void addErrorString(String error) {
		getErrors().add(error);
	}

	/**
	 * 添加错误，根据MessageSource是否存在，自动判断为code还是string。
	 * 
	 * @param error
	 */
	public void addError(String error) {
		// if messageSource exist
		if (messageSource != null) {
			error = messageSource.getMessage(error, null, error, locale);
		}
		getErrors().add(error);
	}

	/**
	 * 是否存在错误
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		return errors != null && errors.size() > 0;
	}

	/**
	 * 错误数量
	 * 
	 * @return
	 */
	public int getCount() {
		return errors == null ? 0 : errors.size();
	}

	/**
	 * 错误列表
	 * 
	 * @return
	 */
	public List<String> getErrors() {
		if (errors == null) {
			errors = new ArrayList<String>();
		}
		return errors;
	}

	
	
	/**
	 * 将错误信息保存至ModelMap
	 * 
	 * @param model
	 */
	public void toModel(Map<String, Object> model) {
		Assert.notNull(model);
		if (!hasErrors()) {
			throw new IllegalStateException("no errors found!");
		}
		model.put(getErrorAttrName(), getErrors());
	}
	
	/**
	 * 将错误信息保存至ModelMap，并返回错误页面。
	 * 
	 * @param model
	 * @return 错误页面地址
	 * @see org.springframework.ui.ModelMap
	 */
	public String showErrorPage(ModelMap model) {
		toModel(model);
		return getErrorPage();
	}

	private MessageSource messageSource;
	private Locale locale;
	private List<String> errors;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * 获得本地化信息
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * 设置本地化信息
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * 获得错误页面的地址
	 * 
	 * @return
	 */
	protected abstract String getErrorPage();

	/**
	 * 获得错误参数名称
	 * 
	 * @return
	 */
	protected abstract String getErrorAttrName();
}
