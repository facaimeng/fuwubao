package com.cbai.model.rongyin.pc.member.vo;

import java.util.Date;

public class LoanBidLogsVO {
	
    private String phone;
    
    private Long bidmoney;
    
    private Date bidtime;
    
	public LoanBidLogsVO() { }
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getBidmoney() {
		return bidmoney;
	}
	public void setBidmoney(Long bidmoney) {
		this.bidmoney = bidmoney;
	}
	public Date getBidtime() {
		return bidtime;
	}
	public void setBidtime(Date bidtime) {
		this.bidtime = bidtime;
	}
    
    
}
