package com.cbai.model.rongyin.payment.huifu.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class InDetails {
	
	@JsonProperty
	private String OrdId;
	
	@JsonProperty
	private String SubOrdId;
	
	@JsonProperty
	private String IncustId;
	
	@JsonProperty
	private String InAcctId;
	
	@JsonProperty
	private String TransAmt;
	
	@JsonProperty
	private String Dzborrcust;
	
	@JsonProperty
	private String FeeObjFlag;
	
	@JsonProperty
	private String Fee;
	
	@JsonProperty
	private List<DivDetails> DivDetails;
	
	@JsonProperty
	private String ChkValue;
	
	
	public InDetails() { }
		
	@JsonIgnore
	public String getOrdId() {
		return OrdId;
	}
	@JsonIgnore
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	@JsonIgnore
	public String getSubOrdId() {
		return SubOrdId;
	}
	@JsonIgnore
	public void setSubOrdId(String subOrdId) {
		SubOrdId = subOrdId;
	}
	@JsonIgnore
	public String getIncustId() {
		return IncustId;
	}
	@JsonIgnore
	public void setIncustId(String incustId) {
		IncustId = incustId;
	}
	@JsonIgnore
	public String getInAcctId() {
		return InAcctId;
	}
	@JsonIgnore
	public void setInAcctId(String inAcctId) {
		InAcctId = inAcctId;
	}
	@JsonIgnore
	public String getTransAmt() {
		return TransAmt;
	}
	@JsonIgnore
	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}
	@JsonIgnore
	public String getDzborrcust() {
		return Dzborrcust;
	}
	@JsonIgnore
	public void setDzborrcust(String dzborrcust) {
		Dzborrcust = dzborrcust;
	}
	@JsonIgnore
	public String getFeeObjFlag() {
		return FeeObjFlag;
	}
	@JsonIgnore
	public void setFeeObjFlag(String feeObjFlag) {
		FeeObjFlag = feeObjFlag;
	}
	@JsonIgnore
	public String getFee() {
		return Fee;
	}
	@JsonIgnore
	public void setFee(String fee) {
		Fee = fee;
	}
	@JsonIgnore
	public List<DivDetails> getDivDetails() {
		return DivDetails;
	}
	@JsonIgnore
	public void setDivDetails(List<DivDetails> divDetails) {
		DivDetails = divDetails;
	}
	@JsonIgnore
	public String getChkValue() {
		return ChkValue;
	}
	@JsonIgnore
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}

}
