package com.cbai.model.rongyin.pc.member.vo;

import java.util.Date;

import com.cbai.model.common.data.State;


public class MemBidLogsVO {
    
	private Integer mblid;
	
	private String loanname;
	
	private Date bidtime; 
	
	private Long bidmoney;
	
	private Long profit;
	
	private Date repaydate;  

    private State state;
 
    private String stname;
     
	public MemBidLogsVO() {  }
	
	public Integer getMblid() {
		return mblid;
	} 
	
	public void setMblid(Integer mblid) {
		this.mblid = mblid;
	}

	public String getLoanname() {
		return loanname;
	}

	public void setLoanname(String loanname) {
		this.loanname = loanname;
	}

	public Date getBidtime() {
		return bidtime;
	}

	public void setBidtime(Date bidtime) {
		this.bidtime = bidtime;
	}

	public Long getBidmoney() {
		return bidmoney;
	}

	public void setBidmoney(Long bidmoney) {
		this.bidmoney = bidmoney;
	}

	public Long getProfit() {
		return profit;
	}
	
	public void setProfit(Long profit) {
		this.profit = profit;
	}

	public Date getRepaydate() {
		return repaydate;
	}

	public void setRepaydate(Date repaydate) {
		this.repaydate = repaydate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getStname() {
		if(this.state!=null){
		    return this.state.getName();
		}else{
			return State.BIDDONE.getName();
		}
	}
	
}