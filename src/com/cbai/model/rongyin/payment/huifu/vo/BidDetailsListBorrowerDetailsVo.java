package com.cbai.model.rongyin.payment.huifu.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class BidDetailsListBorrowerDetailsVo {
	
	@JsonProperty
    private String BorrowerCustId;  //借款人客户号
	
	@JsonProperty
    private String BorrowerCreditAmt;  //明细转让金额BorrowerDetails中的明细转让金额的总和等于BidCreditAmt
	
	@JsonProperty
    private String PrinAmt; //已还款金额(接手的时候被接手的这个东东还已还的钱)
	
	@JsonProperty
    private String ProId;  //标的编号
	
	@JsonIgnore
	public String getBorrowerCustId() {
		return BorrowerCustId;
	}
	@JsonIgnore
	public void setBorrowerCustId(String borrowerCustId) {
		BorrowerCustId = borrowerCustId;
	}
	@JsonIgnore
	public String getBorrowerCreditAmt() {
		return BorrowerCreditAmt;
	}
	@JsonIgnore
	public void setBorrowerCreditAmt(String borrowerCreditAmt) {
		BorrowerCreditAmt = borrowerCreditAmt;
	}
	@JsonIgnore
	public String getPrinAmt() {
		return PrinAmt;
	}
	@JsonIgnore
	public void setPrinAmt(String prinAmt) {
		PrinAmt = prinAmt;
	}
	@JsonIgnore
	public String getProId() {
		return ProId;
	}
	@JsonIgnore
	public void setProId(String proId) {
		ProId = proId;
	}
	
}
