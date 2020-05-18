package com.cbai.model.rongyin.sys.loan.vo;

import java.util.Date;

import com.cbai.common.utils.AmountUtils;
import com.cbai.model.common.data.State;

public class LoanRepayPlanDetailVO {
    
	private Integer lrpdid;
	
	/**还款计划ID**/
	private Integer rpid;
	
	/**标的ID**/
	private Integer lnid;
	
	/**标的编号**/
	private String lnnum;
	
	/**当前期数**/
    private Integer curperiod;
	
	/**投标记录**/
    private Integer mblid;
    
	/**投标订单编号**/
    private String bidordernum;
    
    /**标的名称**/
    private String name;
    
    /**投标订单编号**/
    private String bidorderdate;
    
    /**投资金额**/
    private Long bidmoney;
    
    /**实际还款金额**/
    private Long realrpmoney;
    
    private String rpordernum;
    
    /**实际还款日期**/
    private Date realrptime;
    
    /**预期收益金额**/
    private Long profit;
    
    private Long rpmoney;
    
    /**放款时间**/
    private Date bidpaytime;
    
    /**(计息日期)**/
    private Date loantime;
   
    /**计划还款时间**/
    private Date pretime;
    
    /**本次还款计划计息天数**/
    private Integer curdates;
    
    /**投资人系统ID(还款时收钱的人)**/
    private Integer holdmemid;
    
    /**投资人汇付ID(还款时收钱的人)**/
    private String holdusrcustid;
    
    /**投资人姓名**/
    private String lman;
    
    /**还款状态**/
    private State state;
    
    /**还款状态显示**/
    private String stname;
    
    /**标的年化收益**/
    private String yearate;
    
    private String realrpmoneyLabel;
    
	public LoanRepayPlanDetailVO() {  }

	public Integer getLrpdid() {
		return lrpdid;
	}

	public void setLrpdid(Integer lrpdid) {
		this.lrpdid = lrpdid;
	}

	public Integer getRpid() {
		return rpid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBidorderdate() {
		return bidorderdate;
	}

	public void setBidorderdate(String bidorderdate) {
		this.bidorderdate = bidorderdate;
	}

	public Long getBidmoney() {
		return bidmoney;
	}

	public void setBidmoney(Long bidmoney) {
		this.bidmoney = bidmoney;
	}

	public Date getBidpaytime() {
		return bidpaytime;
	}

	public void setBidpaytime(Date bidpaytime) {
		this.bidpaytime = bidpaytime;
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

	public String getLman() {
		return lman;
	}

	public void setLman(String lman) {
		this.lman = lman;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		if(this.state!=null){
			this.stname = this.state.getName();
		}
	}

	public String getStname() {
		return stname;
	}

	public Long getProfit() {
		return profit;
	}

	public void setProfit(Long profit) {
		this.profit = profit;
	}
    
	public String getRepaymoney() throws Exception{
		Long bidmoney = (this.bidmoney == null?0L:this.bidmoney);
		Long profit = (this.profit == null?0L:this.profit);
		
		Long longMoney = bidmoney + profit;
		return AmountUtils.changeF2Y(String.valueOf(longMoney));
	}

	public void setRealrpmoney(Long realrpmoney) {
		this.realrpmoney = realrpmoney;
	}
	
	public Long getRealrpmoney() {
		return realrpmoney;
	}
	
	public String getRealrpmoneyLabel() throws Exception {
		if(this.realrpmoney != null){
			return AmountUtils.changeF2Y(String.valueOf(realrpmoney));
		}else{
			return "0.00";
		}
	}
	
	public Date getRealrptime() {
		return realrptime;
	}

	public void setRealrptime(Date realrptime) {
		this.realrptime = realrptime;
	}

	public Date getLoantime() {
		return loantime;
	}

	public void setLoantime(Date loantime) {
		this.loantime = loantime;
	}

	public Date getPretime() {
		return pretime;
	}

	public void setPretime(Date pretime) {
		this.pretime = pretime;
	}

	public Integer getCurdates() {
		return curdates;
	}

	public void setCurdates(Integer curdates) {
		this.curdates = curdates;
	}

	public String getYearate() {
		return yearate;
	}

	public void setYearate(String yearate) {
		this.yearate = yearate;
	}

	public String getRpordernum() {
		return rpordernum;
	}

	public void setRpordernum(String rpordernum) {
		this.rpordernum = rpordernum;
	}

	public Long getRpmoney() {
		return rpmoney;
	}

	public void setRpmoney(Long rpmoney) {
		this.rpmoney = rpmoney;
	}
	
}
