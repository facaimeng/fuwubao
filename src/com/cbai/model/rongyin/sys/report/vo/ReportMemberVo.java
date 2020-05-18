package com.cbai.model.rongyin.sys.report.vo;

import java.util.Date;

import com.cbai.model.common.data.State;

public class ReportMemberVo {
    
	/** 汇付ID **/
	private String usrcustid;
	
	/** 姓名 **/
    private String realname;
    
	/** 性别 **/
    private String sex;
    
	/** 生日 **/
    private Date birth;
    
	/** 手机号 **/
    private String phone;
    
    /** 身份证 **/
    private String idnum;
    
    /** 可用余额  **/
    private String accinfo;
    
    /** 在投金额 **/
    private String bidingmoney;
    
    /** 总投金额 **/
    private String allbidmoney;
    
    /** 是否绑卡**/
    private String ifbind;
    
    /** 是否冻结**/
    private String iffreeze;
    
    /** 账号状态**/
    private String stname;
    
    /**一级推荐人**/
    private String grandRealname;
    
    /**一级推荐人手机**/
    private String grandPhone;
    
    /**二级推荐人**/
    private String dadRealname;
    
    /**二级推荐人手机**/
    private String dadPhone;

    /**开户时间**/
    private String authtime;
    
    /**注册时间**/
    private String regtime;
    
	public ReportMemberVo() { }

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
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

	public String getAccinfo() {
		return accinfo;
	}

	public void setAccinfo(String accinfo) {
		this.accinfo = accinfo;
	}

	public String getBidingmoney() {
		return bidingmoney;
	}

	public void setBidingmoney(String bidingmoney) {
		this.bidingmoney = bidingmoney;
	}

	public String getAllbidmoney() {
		return allbidmoney;
	}

	public void setAllbidmoney(String allbidmoney) {
		this.allbidmoney = allbidmoney;
	}

	public String getIfbind() {
		return ifbind;
	}

	public void setIfbind(String ifbind) {
		this.ifbind = ifbind;
	}

	public String getIffreeze() {
		return iffreeze;
	}

	public void setIffreeze(String iffreeze) {
		this.iffreeze = iffreeze;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public String getGrandRealname() {
		return grandRealname;
	}

	public void setGrandRealname(String grandRealname) {
		this.grandRealname = grandRealname;
	}

	public String getGrandPhone() {
		return grandPhone;
	}

	public void setGrandPhone(String grandPhone) {
		this.grandPhone = grandPhone;
	}

	public String getDadRealname() {
		return dadRealname;
	}

	public void setDadRealname(String dadRealname) {
		this.dadRealname = dadRealname;
	}

	public String getDadPhone() {
		return dadPhone;
	}

	public void setDadPhone(String dadPhone) {
		this.dadPhone = dadPhone;
	}

	public String getAuthtime() {
		return authtime;
	}

	public void setAuthtime(String authtime) {
		this.authtime = authtime;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

    
}
