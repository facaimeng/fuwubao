package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.utils.AmountUtils;
import com.cbai.model.common.data.State;

public class LoanRepayPlan {
    
	private Integer rpid;

	private Integer curperiod; //还款期数
	
	private Integer curdates;  //当期计划天数
	
	private Integer lnid; //标的编号
	
	private String lnnum;
	
	private String paytype; //付款方式
	
    private String lmantype;

	private String lman;
	
	private String lusrcustid;
	
	private Date loantime;
	
	private Date pretime;
	
	private Date realtime;
	
	private Long rpmoney;
	
	private Integer rpcount;
	
	private Integer realrpcount;
	
	private Long realrpmoney;
	
	private String rpmoneyLabel;
	
	private State state;
	
	private String statename;
	
	private String memo;
	
	private Integer opuid;
	
	private Date optime;
	
	public LoanRepayPlan() { }

	public Integer getRpid() {
		return rpid;
	}

	public void setRpid(Integer rpid) {
		this.rpid = rpid;
	}

	public Integer getCurperiod() {
		return curperiod;
	}

	public void setCurperiod(Integer curperiod) {
		this.curperiod = curperiod;
	}

	public Integer getCurdates() {
		return curdates;
	}

	public void setCurdates(Integer curdates) {
		this.curdates = curdates;
	}

	public Integer getLnid() {
		return lnid;
	}

	public void setLnid(Integer lnid) {
		this.lnid = lnid;
	}

	public String getLnnum() {
		return lnnum;
	}

	public void setLnnum(String lnnum) {
		this.lnnum = lnnum;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getLmantype() {
		return lmantype;
	}

	public void setLmantype(String lmantype) {
		this.lmantype = lmantype;
	}

	public String getLman() {
		return lman;
	}

	public void setLman(String lman) {
		this.lman = lman;
	}

	public String getLusrcustid() {
		return lusrcustid;
	}

	public void setLusrcustid(String lusrcustid) {
		this.lusrcustid = lusrcustid;
	}

	public Date getPretime() {
		return pretime;
	}

	public void setPretime(Date pretime) {
		this.pretime = pretime;
	}

	public Date getRealtime() {
		return realtime;
	}

	public void setRealtime(Date realtime) {
		this.realtime = realtime;
	}

	public Long getRpmoney() {
		return rpmoney;
	}

	public void setRpmoney(Long rpmoney) throws Exception{
		this.rpmoney = rpmoney;
		
		if(this.rpmoney != null){
			this.rpmoneyLabel = AmountUtils.changeF2Y(String.valueOf(this.rpmoney));
		}
	}
	
	public Integer getRpcount() {
		return rpcount;
	}

	public void setRpcount(Integer rpcount) {
		this.rpcount = rpcount;
	}

	public Integer getRealrpcount() {
		return realrpcount;
	}

	public void setRealrpcount(Integer realrpcount) {
		this.realrpcount = realrpcount;
	}

	public String getRpmoneyLabel() {
		return rpmoneyLabel;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		if(this.state!=null){
			this.statename = this.state.getName();
		}
		
	}

	public String getStatename() {
		return statename;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getOpuid() {
		return opuid;
	}

	public void setOpuid(Integer opuid) {
		this.opuid = opuid;
	}

	public Date getOptime() {
		return optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

	public Date getLoantime() {
		return loantime;
	}

	public void setLoantime(Date loantime) {
		this.loantime = loantime;
	}

	public Long getRealrpmoney() {
		return realrpmoney;
	}

	public void setRealrpmoney(Long realrpmoney) {
		this.realrpmoney = realrpmoney;
	}
	
}
