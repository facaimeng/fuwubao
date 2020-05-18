package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.utils.OptUtil;
import com.cbai.model.common.data.State;

public class LoanMan {
    
	private Integer lmid;  
	
	private String lmtype;  

    private String phone;

    private String name; 

    private String idnum; 
    
    private String address;
    
    private String urgentman;

    private String usrcustid;

    private String usrcustname;  

    private State state;
    
    private String stname;    

    private Date addtime; 
    
	public LoanMan() { 
	}

	public Integer getLmid() {
		return lmid;
	}

	public void setLmid(Integer lmid) {
		this.lmid = lmid;
	}

	public String getLmtype() {
		return lmtype;
	}

	public void setLmtype(String lmtype) {
		this.lmtype = lmtype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrgentman() {
		return urgentman;
	}

	public void setUrgentman(String urgentman) {
		this.urgentman = urgentman;
	}

	public String getUsrcustid() {
		return usrcustid;
	}

	public void setUsrcustid(String usrcustid) {
		this.usrcustid = usrcustid;
	}

	public String getUsrcustname() {
		return usrcustname;
	}

	public void setUsrcustname(String usrcustname) {
		this.usrcustname = usrcustname;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getStname() {
		return this.state.getName();
	} 

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	} 
}