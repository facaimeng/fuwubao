package com.cbai.model.rongyin.payment.huifu.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class LoansReqExt {
	
	//@JsonProperty
	//private String LoansVocherAmt;
	
	@JsonProperty
	private String ProId;
	
	public LoansReqExt() { }
	
	//@JsonIgnore
	//public String getLoansVocherAmt() {
	//	return LoansVocherAmt;
	//}
	//@JsonIgnore
	//public void setLoansVocherAmt(String loansVocherAmt) {
	//	LoansVocherAmt = loansVocherAmt;
	//}
	@JsonIgnore
	public String getProId() {
		return ProId;
	}
	@JsonIgnore
	public void setProId(String proId) {
		ProId = proId;
	}
	
	
}
