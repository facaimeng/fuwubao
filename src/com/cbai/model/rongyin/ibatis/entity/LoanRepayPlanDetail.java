package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

public class LoanRepayPlanDetail {
	
    private Integer lrpdid;
    
    private Integer curperiod;
    
    private String rpordernum;
    
    private Integer lnid;
    
	private Integer rpid;
	
    private Integer mblid;
    
    private String bidordernum;
    
    private Integer holdmemid;
    
    private String holdusrcustid;
    
	private Long rpmoney;
	
	private Long realrpmoney;
	
	private Date rptime;
	
	private Date realrptime;
	
	private String memo;
	
	public LoanRepayPlanDetail() { }

	public Integer getLrpdid() {
		return lrpdid;
	}

	public void setLrpdid(Integer lrpdid) {
		this.lrpdid = lrpdid;
	}

	public Integer getCurperiod() {
		return curperiod;
	}

	public void setCurperiod(Integer curperiod) {
		this.curperiod = curperiod;
	}

	public String getRpordernum() {
		return rpordernum;
	}

	public void setRpordernum(String rpordernum) {
		this.rpordernum = rpordernum;
	}

	public Integer getLnid() {
		return lnid;
	}

	public void setLnid(Integer lnid) {
		this.lnid = lnid;
	}

	public Integer getRpid() {
		return rpid;
	}

	public void setRpid(Integer rpid) {
		this.rpid = rpid;
	}

	public Integer getMblid() {
		return mblid;
	}

	public void setMblid(Integer mblid) {
		this.mblid = mblid;
	}

	public String getBidordernum() {
		return bidordernum;
	}

	public void setBidordernum(String bidordernum) {
		this.bidordernum = bidordernum;
	}

	public Integer getHoldmemid() {
		return holdmemid;
	}

	public void setHoldmemid(Integer holdmemid) {
		this.holdmemid = holdmemid;
	}

	public String getHoldusrcustid() {
		return holdusrcustid;
	}

	public void setHoldusrcustid(String holdusrcustid) {
		this.holdusrcustid = holdusrcustid;
	}

	public Long getRpmoney() {
		return rpmoney;
	}

	public void setRpmoney(Long rpmoney) {
		this.rpmoney = rpmoney;
	}

	public Long getRealrpmoney() {
		return realrpmoney;
	}

	public void setRealrpmoney(Long realrpmoney) {
		this.realrpmoney = realrpmoney;
	}

	public Date getRptime() {
		return rptime;
	}

	public void setRptime(Date rptime) {
		this.rptime = rptime;
	}

	public Date getRealrptime() {
		return realrptime;
	}

	public void setRealrptime(Date realrptime) {
		this.realrptime = realrptime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
