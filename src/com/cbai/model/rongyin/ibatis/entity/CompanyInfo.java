package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.model.common.data.State;

public class CompanyInfo {
	
    private Integer cmid;

    private String cmnum;

    private String usrcustid;

    private State ctype;

    private String name;

    private String orgnum;

    private String taxnum;

    private String licnum;

    private String regaddress;

    private String realaddress;

    private String contactphone;

    private Date foundate;

    private String busperiod;

    private String dutyman;

    private String dutyidnum;

    private String dutyphone;

    private String bkabbr;

    private String bkname;

    private String bkaddress;

    private String bkcardnum;

    private String contactman;

    private String contactidnum;
    
    private String ofenmanphone;

    private String regcapital;

    private String mainbus;

    private String busstate;

    private String memo;
    
    private State state;
    
    private String stname;

    private Date addtime;

    private String auditStat;

    private String auditDesc;
     

    public CompanyInfo() { 
    	
	}

	public Integer getCmid() {
        return cmid;
    }

    public void setCmid(Integer cmid) {
        this.cmid = cmid;
    }

    public String getCmnum() {
        return cmnum;
    }

    public void setCmnum(String cmnum) {
        this.cmnum = cmnum;
    }

    public String getUsrcustid() {
        return usrcustid;
    }

    public void setUsrcustid(String usrcustid) {
        this.usrcustid = usrcustid;
    }

    public State getCtype() {
        return ctype;
    }

    public void setCtype(State ctype) {
        this.ctype = ctype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgnum() {
        return orgnum;
    }

    public void setOrgnum(String orgnum) {
        this.orgnum = orgnum;
    }

    public String getTaxnum() {
        return taxnum;
    }

    public void setTaxnum(String taxnum) {
        this.taxnum = taxnum;
    }

    public String getLicnum() {
        return licnum;
    }

    public void setLicnum(String licnum) {
        this.licnum = licnum;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress;
    }

    public String getRealaddress() {
        return realaddress;
    }

    public void setRealaddress(String realaddress) {
        this.realaddress = realaddress;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public Date getFoundate() {
        return foundate;
    }

    public void setFoundate(Date foundate) {
        this.foundate = foundate;
    }

    public String getBusperiod() {
        return busperiod;
    }

    public void setBusperiod(String busperiod) {
        this.busperiod = busperiod;
    }

    public String getDutyman() {
        return dutyman;
    }

    public void setDutyman(String dutyman) {
        this.dutyman = dutyman;
    }

    public String getDutyidnum() {
        return dutyidnum;
    }

    public void setDutyidnum(String dutyidnum) {
        this.dutyidnum = dutyidnum;
    }

    public String getDutyphone() {
        return dutyphone;
    }

    public void setDutyphone(String dutyphone) {
        this.dutyphone = dutyphone;
    }

    public String getBkabbr() {
        return bkabbr;
    }

    public void setBkabbr(String bkabbr) {
        this.bkabbr = bkabbr;
    }

    public String getBkname() {
        return bkname;
    }

    public void setBkname(String bkname) {
        this.bkname = bkname;
    }

    public String getBkaddress() {
        return bkaddress;
    }

    public void setBkaddress(String bkaddress) {
        this.bkaddress = bkaddress;
    }

    public String getBkcardnum() {
        return bkcardnum;
    }

    public void setBkcardnum(String bkcardnum) {
        this.bkcardnum = bkcardnum;
    }

    public String getContactman() {
        return contactman;
    }

    public void setContactman(String contactman) {
        this.contactman = contactman;
    }

    public String getContactidnum() {
        return contactidnum;
    }

    public void setContactidnum(String contactidnum) {
        this.contactidnum = contactidnum;
    }

    public String getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(String regcapital) {
        this.regcapital = regcapital;
    }

    public String getMainbus() {
        return mainbus;
    }

    public void setMainbus(String mainbus) {
        this.mainbus = mainbus;
    }

    public String getBusstate() {
        return busstate;
    }

    public void setBusstate(String busstate) {
        this.busstate = busstate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAuditStat() {
        return auditStat;
    }

    public void setAuditStat(String auditStat) {
        this.auditStat = auditStat;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
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

	public String getOfenmanphone() {
		return ofenmanphone;
	}

	public void setOfenmanphone(String ofenmanphone) {
		this.ofenmanphone = ofenmanphone;
	} 
    
}