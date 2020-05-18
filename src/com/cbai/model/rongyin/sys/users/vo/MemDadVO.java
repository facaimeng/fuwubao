package com.cbai.model.rongyin.sys.users.vo;

import com.cbai.model.common.data.State;

public class MemDadVO { 
	
	private String iffreeze;
	
	private String mytype;
	
	private String dadtype;
	
	private String dadmemid;
	
	private String realname;
	
	private String phone;
	 
	private String usrcustid;
	
	private State state;
	
	private MemDadVO dadinfo;

	public MemDadVO() { 
	}

	public String getIffreeze() {
		return iffreeze;
	}

	public void setIffreeze(String iffreeze) {
		this.iffreeze = iffreeze;
	}

	public String getMytype() {
		return mytype;
	}

	public void setMytype(String mytype) {
		this.mytype = mytype;
	}

	public String getDadtype() {
		return dadtype;
	}

	public void setDadtype(String dadtype) {
		this.dadtype = dadtype;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsrcustid() {
		return usrcustid;
	}

	public void setUsrcustid(String usrcustid) {
		this.usrcustid = usrcustid;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getDadmemid() {
		return dadmemid;
	}

	public void setDadmemid(String dadmemid) {
		this.dadmemid = dadmemid;
	}

	public MemDadVO getDadinfo() {
		return dadinfo;
	}

	public void setDadinfo(MemDadVO dadinfo) {
		this.dadinfo = dadinfo;
	} 

}
