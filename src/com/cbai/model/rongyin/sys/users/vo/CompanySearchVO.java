package com.cbai.model.rongyin.sys.users.vo;

import com.cbai.model.common.data.State;

public class CompanySearchVO {
	
	private String t;
	
	private String name;
	
	private String ifopen;
	
	private String dutyman;
	 
	private String usrcustid;
	
	private State state;

	public CompanySearchVO() { 
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getIfopen() {
		return ifopen;
	}

	public void setIfopen(String ifopen) {
		this.ifopen = ifopen;
	}

	public String getDutyman() {
		return dutyman;
	}

	public void setDutyman(String dutyman) {
		this.dutyman = dutyman;
	}

	public String getUsrcustid() {
		return usrcustid;
	}

	public void setUsrcustid(String usrcustid) {
		this.usrcustid = usrcustid;
	}
	
	
	

}
