package com.cbai.model.rongyin.ibatis.entity;

import java.io.Serializable;

public class BidArrayLogs implements Serializable{
	
	private static final long serialVersionUID = 5711541328850413888L;
	
	private Integer baid;
    private Integer memid;
    private Integer lnid;
    
    private String status;
    private String orderId;
    private Long transAmt;
    
    private String bidtime;
    private String optime;
    
	public BidArrayLogs() { }
	
	public Integer getBaid() {
		return baid;
	}
	public void setBaid(Integer baid) {
		this.baid = baid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public Integer getLnid() {
		return lnid;
	}
	public void setLnid(Integer lnid) {
		this.lnid = lnid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(Long transAmt) {
		this.transAmt = transAmt;
	}
	public String getBidtime() {
		return bidtime;
	}
	public void setBidtime(String bidtime) {
		this.bidtime = bidtime;
	}
	public String getOptime() {
		return optime;
	}
	public void setOptime(String optime) {
		this.optime = optime;
	}
	
}
