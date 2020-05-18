package com.cbai.common.json;

public class JsonResultObject {
	
	private String code="0";
    
	private String message;
	
	private String bizcode; //跳转码,不同值跳转到不同页面 -100:表示跳转到登录页面
	
	private Object returnObj;
	
    public JsonResultObject() { } 

	public JsonResultObject(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public JsonResultObject(String code, String message,Object returnObj) {
		this.code = code;
		this.message = message;
		this.returnObj = returnObj;
	}
	
	public JsonResultObject(String code, String message, String bizcode) {
		this.code = code;
		this.message = message;
		this.bizcode = bizcode; 
	}
	
	public JsonResultObject(String code, String message, String bizcode, Object returnObj) {
		this.code = code;
		this.message = message;
		this.bizcode = bizcode;
		this.returnObj = returnObj;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getReturnObj() {
		return returnObj;
	}

	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}

	public String getBizcode() {
		return bizcode;
	}

	public void setBizcode(String bizcode) {
		this.bizcode = bizcode;
	}
}
