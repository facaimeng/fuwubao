package com.cbai.model.common.permission.vo;

import java.util.Date; 

import com.cbai.model.common.data.State;

public class PerUserVO {
	
	private Integer userid;

    private String phone; 
    
    private String realname; 

    private State state; 
    
    private String hasMenuJson;

    private Date lastlogin;  

	public PerUserVO() { 
		
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getHasMenuJson() {
		return hasMenuJson;
	}

	public void setHasMenuJson(String hasMenuJson) {
		this.hasMenuJson = hasMenuJson;
	}
 
	
}
