package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

public class MemRepaymentDetail {
	
	private Integer cpid;
	private Integer recid;
	private String ordid;
	private Date ordate;
	private String outcustid;
	private String subordid;
	private Date subordate;
	private Long principalamt;
	private Long interestamt;
	private Long fee;
	private Date addtime;
	
	public MemRepaymentDetail(){}
	
	public Integer getCpid() {
		return cpid;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	public Integer getRecid() {
		return recid;
	}
	public void setRecid(Integer recid) {
		this.recid = recid;
	}
	public String getOrdid() {
		return ordid;
	}
	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}
	public Date getOrdate() {
		return ordate;
	}
	public void setOrdate(Date ordate) {
		this.ordate = ordate;
	}
	public String getOutcustid() {
		return outcustid;
	}
	public void setOutcustid(String outcustid) {
		this.outcustid = outcustid;
	}
	public String getSubordid() {
		return subordid;
	}
	public void setSubordid(String subordid) {
		this.subordid = subordid;
	}
	public Date getSubordate() {
		return subordate;
	}
	public void setSubordate(Date subordate) {
		this.subordate = subordate;
	}
	public Long getPrincipalamt() {
		return principalamt;
	}
	public void setPrincipalamt(Long principalamt) {
		this.principalamt = principalamt;
	}
	public Long getInterestamt() {
		return interestamt;
	}
	public void setInterestamt(Long interestamt) {
		this.interestamt = interestamt;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}
