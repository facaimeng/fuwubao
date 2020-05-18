package com.cbai.common.huifu;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class RequestParamterUtils {
	
	/** 第三方支付网关 */
	public static final String NET_GATE_URL = "http://mertest.chinapnr.com/muser/publicRequests?";//测试
	
	
	/** 
     * 将url参数转换成map 
     * @param param aa=11&bb=22&cc=33 
     * @return 
     */  
    public static Map<String, Object> getUrlParams(String param) {  
        Map<String, Object> map = new HashMap<String, Object>(0);  
        if (StringUtils.isBlank(param)) {  
            return map;  
        }  
        String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
                map.put(p[0], p[1]);  
            }  
        }  
        return map;  
    }  
  
    /** 
     * 将map转换成url 
     * @param map 
     * @return 
     */  
    public static String getUrlParamsByMap(Map<String, String> map) {  
        if (map == null) {  
            return "";  
        }  
        StringBuffer sb = new StringBuffer();  
        for (Map.Entry<String, String> entry : map.entrySet()) {  
            sb.append(entry.getKey() + "=" + entry.getValue());  
            sb.append("&");  
        }  
        String s = sb.toString();  
        if (s.endsWith("&")) {  
            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
        }  
        return s;  
    }
	
}
