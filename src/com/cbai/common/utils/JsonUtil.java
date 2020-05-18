package com.cbai.common.utils; 

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtil {
	
	private JsonUtil(){}
	
    /**
     * 对象转换成json字符串
     * @param obj 
     * @return 
     */
    public static String toJson(Object obj) {
    	
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        return gson.toJson(obj);
        
    }
    /**
     * 对象转换成json字符串
     * gson实体转json时当字段值为空时也保留该字符串
     * @param obj
     * @return
     */
    public static String toJsonAndNull(Object obj) {
    	
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(obj);
        
    }
    

    /**
     * json字符串转成对象
     * @param str  
     * @param type
     * @return 
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     * @param str  
     * @param type 
     * @return 
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }
    
    public static final Map<String, Object> getDefaultAjaxJson() {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("statusCode", 200);
		json.put("message", "操作成功！");
		//json.put("navTabId", "");
		//json.put("dialogId", "");
		//json.put("rel", "");
		//json.put("callbackType", "closeCurrent");
		//json.put("forwardUrl", "");
		return json;
	}
 
}