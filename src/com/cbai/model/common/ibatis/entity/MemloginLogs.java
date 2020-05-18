package com.cbai.model.common.ibatis.entity;

import java.util.Date;

import com.cbai.model.common.data.State;
 

public class MemloginLogs { 
	
	private Integer mllid;  
	
	private String memid; 
 
    private String phone; 
    
    private String openid; 

    private String sessionid; 
    
    private String lgaddr;
    
    private String lgip; 
    
    private Date intime; 
    
    private State outtype;  
    
    private Date outtime; 

	public MemloginLogs() { 
		
		
	}

	 

	public Integer getMllid() {
		return mllid;
	}



	public void setMllid(Integer mllid) {
		this.mllid = mllid;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	 
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public State getOuttype() {
		return outtype;
	}

	public void setOuttype(State outtype) {
		this.outtype = outtype;
	}
 

	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	} 

	public String getLgip() {
		return lgip;
	}

	public void setLgip(String lgip) {
		this.lgip = lgip;
	}

	public String getLgaddr() {
		return lgaddr;
	}

	public void setLgaddr(String lgaddr) {
		this.lgaddr = lgaddr;
	}



	public String getMemid() {
		return memid;
	}



	public void setMemid(String memid) {
		this.memid = memid;
	}



	public String getOpenid() {
		return openid;
	}



	public void setOpenid(String openid) {
		this.openid = openid;
	} 
	
	
}