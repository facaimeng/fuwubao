package com.cbai.common.web.springmvc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.web.ResponseUtils;

/**
 * 一个用户 相同url 同时提交 相同数据 验证 主要通过 session中保存到的url 和 请求参数。如果和上次相同，则是重复提交表单
 * 
 * @author Administrator
 */
public class FormTokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			
			FormToken annotation = method.getAnnotation(FormToken.class);
			if (annotation != null) {
				if (repeatDataValidator(request)){// 如果重复相同数据
					JsonResultObject result = new JsonResultObject();
					result.setCode("0");
					result.setMessage("切勿重复提交数据!");
					ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
					return false;
				}else{
					return true;
				}
			}
			
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
		
	}

	/**
	 * 验证同一个url数据是否相同提交 ,相同返回true
	 * 
	 * @param httpServletRequest
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public boolean repeatDataValidator(HttpServletRequest httpServletRequest) throws JsonGenerationException, JsonMappingException, IOException {
		
		//String params=JsonMapper.toJsonString(httpServletRequest.getParameterMap());  
		//他那个工具类继承了ObjectMapper父类，可以不用他的，直接new ObjectMapper().writeValueAsString(httpServletRequest.getParameterMap()); 就可以了。
        
		String params = new ObjectMapper().writeValueAsString(httpServletRequest.getParameterMap());
		
		String url = httpServletRequest.getRequestURI();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(url, params);
		String nowUrlParams = map.toString();//

		Object preUrlParams = httpServletRequest.getSession().getAttribute("repeatData");
		if (preUrlParams == null){// 如果上一个数据为null,表示还没有访问页面
			
			httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
			return false;
		
		} else {// 否则，已经访问过页面
			
			if (preUrlParams.toString().equals(nowUrlParams)){// 如果上次url+数据和本次url+数据相同，则表示重复添加数据
				System.out.println("防重复拦截器!");
				return true;
			} else {// 如果上次 url+数据 和本次url加数据不同，则不是重复提交
				httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
				return false;
			}
			
		}
	}
	
	
}