package com.cbai.model.rongyin.sys.users.vo;

import com.cbai.model.common.data.State;

public class LoanmanSearchVO {   
	
	private String name;
	
	private String phone;
	
	private String idnum;
	
	private Integer lmtype;
	 
	private String usrcustid;
	
	private State state;

	public LoanmanSearchVO() { 
	}
  

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	} 
	
	public String getUsrcustid() {
		return usrcustid;
	}

	public void setUsrcustid(String usrcustid) {
		this.usrcustid = usrcustid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getIdnum() {
		return idnum;
	}


	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}


	public Integer getLmtype() {
		return lmtype;
	}


	public void setLmtype(Integer lmtype) {
		this.lmtype = lmtype;
	} 

	
}
