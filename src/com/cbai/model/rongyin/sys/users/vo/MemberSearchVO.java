package com.cbai.model.rongyin.sys.users.vo;

import java.util.Date;

import com.cbai.model.common.data.State;

public class MemberSearchVO {  
	
	private String ifbind;
	
	private String ifloanman;
	
	private String iffreeze;
	
	private String ifbus;
	
	private String realname;
	
	private String phone;
	
	private String idnum;
	 
	private String usrcustid;
	
	private State state;
	
	private Date birthStart;
	
	private Date birthEnd;
	
	private Date regStart;
	
	private Date regEnd;
	
	private Date openStart;
	
	private Date openEnd;
	
	private Date calcDead; 

	public MemberSearchVO() { 
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

	public String getIfloanman() {
		return ifloanman;
	}

	public void setIfloanman(String ifloanman) {
		this.ifloanman = ifloanman;
	}

	public String getIffreeze() {
		return iffreeze;
	}

	public void setIffreeze(String iffreeze) {
		this.iffreeze = iffreeze;
	}


	public String getIfbind() {
		return ifbind;
	}


	public void setIfbind(String ifbind) {
		this.ifbind = ifbind;
	}


	public String getIdnum() {
		return idnum;
	}


	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}


	public Date getBirthStart() {
		return birthStart;
	}


	public void setBirthStart(Date birthStart) {
		this.birthStart = birthStart;
	}


	public Date getBirthEnd() {
		return birthEnd;
	}


	public void setBirthEnd(Date birthEnd) {
		this.birthEnd = birthEnd;
	}


	public Date getRegStart() {
		return regStart;
	}


	public void setRegStart(Date regStart) {
		this.regStart = regStart;
	}


	public Date getRegEnd() {
		return regEnd;
	}


	public void setRegEnd(Date regEnd) {
		this.regEnd = regEnd;
	}


	public Date getOpenStart() {
		return openStart;
	}


	public void setOpenStart(Date openStart) {
		this.openStart = openStart;
	}


	public Date getOpenEnd() {
		return openEnd;
	}


	public void setOpenEnd(Date openEnd) {
		this.openEnd = openEnd;
	}


	public Date getCalcDead() {
		return calcDead;
	}


	public void setCalcDead(Date calcDead) {
		this.calcDead = calcDead;
	}


	public String getIfbus() {
		return ifbus;
	}


	public void setIfbus(String ifbus) {
		this.ifbus = ifbus;
	}
	
	
	
	

}
