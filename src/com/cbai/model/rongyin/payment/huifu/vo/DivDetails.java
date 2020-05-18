package com.cbai.model.rongyin.payment.huifu.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class DivDetails {
	
	@JsonProperty
	private String DivCustId;
	
	@JsonProperty
    private String DivAcctId;
	
	@JsonProperty
    private String DivAmt;
	
	@JsonIgnore
	public String getDivCustId() {
		return DivCustId;
	}
	@JsonIgnore
	public void setDivCustId(String divCustId) {
		DivCustId = divCustId;
	}
	@JsonIgnore
	public String getDivAcctId() {
		return DivAcctId;
	}
	@JsonIgnore
	public void setDivAcctId(String divAcctId) {
		DivAcctId = divAcctId;
	}
	@JsonIgnore
	public String getDivAmt() {
		return DivAmt;
	}
	@JsonIgnore
	public void setDivAmt(String divAmt) {
		DivAmt = divAmt;
	}
}
