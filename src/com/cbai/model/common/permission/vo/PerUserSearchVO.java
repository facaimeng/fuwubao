package com.cbai.model.common.permission.vo;

import com.cbai.model.common.data.State;

public class PerUserSearchVO {
	
	private Integer pdid;
	
    private String realname;
    
    private String sex;
    
    private String phone;
    
    private String idnum;
    
    private String state;
     
	public PerUserSearchVO() { 
	}

	public Integer getPdid() {
		return pdid;
	}

	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
    
    

}
