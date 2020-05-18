package com.cbai.model.rongyin.sys.loan.vo;

import java.util.Date;

import com.cbai.common.utils.AmountUtils;
import com.cbai.model.common.data.State;

public class LoanRepayPlanVO {
   
	/**标的ID**/
	private Integer lnid;
	
	/**标的汇付编号**/
    private String lnnum;
    
	/**还款计划ID**/
    private Integer rpid;
    
    /**借款人名称**/
    private String lman;
    
    /**借款人用户名**/
    private String phone;
    
    /**标的信息**/
    private String name;
    
    /**当前期数**/
    private Integer curperiod;
    
    /**总期数**/
    private Integer repaytimes;
    
    /**还款类型**/
    private String repaytypename;
    
    /**计划还款时间**/
    private Date pretime;
    
    /**实际还款时间**/
    private Date realtime;
    
    /**实际还款金额**/
    private Long realrpmoney;
    
    /**计划还款金额**/
    private Long rpmoney;
    
    private String rpmoneyLabel;
    
    /**状态**/
    private State state;
    
    private String stateLabel;
    
    
	public LoanRepayPlanVO() {  }
	
    
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


	public Integer getRpid() {
		return rpid;
	}

	public void setRpid(Integer rpid) {
		this.rpid = rpid;
	}

	public String getLman() {
		return lman;
	}

	public void setLman(String lman) {
		this.lman = lman;
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

	public Integer getCurperiod() {
		return curperiod;
	}

	public void setCurperiod(Integer curperiod) {
		this.curperiod = curperiod;
	}

	public Integer getRepaytimes() {
		return repaytimes;
	}

	public void setRepaytimes(Integer repaytimes) {
		this.repaytimes = repaytimes;
	}

	public String getRepaytypename() {
		return repaytypename;
	}

	public void setRepaytypename(String repaytypename) {
		this.repaytypename = repaytypename;
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
		if(this.rpmoney!=null){
			this.rpmoneyLabel = AmountUtils.changeF2Y(this.rpmoney);
		}
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
			this.stateLabel = this.state.getName();
		}
	}

	public String getStateLabel() {
		return stateLabel;
	}


	public Long getRealrpmoney() {
		return realrpmoney;
	}


	public void setRealrpmoney(Long realrpmoney) {
		this.realrpmoney = realrpmoney;
	}
	
}
