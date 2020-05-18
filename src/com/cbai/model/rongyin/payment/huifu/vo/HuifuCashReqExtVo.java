package com.cbai.model.rongyin.payment.huifu.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class HuifuCashReqExtVo {
	
	@JsonProperty
	private String FeeObjFlag;
	@JsonProperty
	private String FeeAcctId;
	@JsonProperty
	private String CashChl;
	@JsonProperty
	private String CashFeeDeductType;

	public HuifuCashReqExtVo() { }
	
	@JsonIgnore
	public String getFeeObjFlag() {
		return FeeObjFlag;
	}
	@JsonIgnore
	public void setFeeObjFlag(String feeObjFlag) {
		FeeObjFlag = feeObjFlag;
	}
	@JsonIgnore
	public String getFeeAcctId() {
		return FeeAcctId;
	}
	@JsonIgnore
	public void setFeeAcctId(String feeAcctId) {
		FeeAcctId = feeAcctId;
	}
	@JsonIgnore
	public String getCashChl() {
		return CashChl;
	}
	@JsonIgnore
	public void setCashChl(String cashChl) {
		CashChl = cashChl;
	}
	@JsonIgnore
	public String getCashFeeDeductType() {
		return CashFeeDeductType;
	}
	@JsonIgnore
	public void setCashFeeDeductType(String cashFeeDeductType) {
		CashFeeDeductType = cashFeeDeductType;
	}

}
