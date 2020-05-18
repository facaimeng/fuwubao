package com.cbai.model.rongyin.payment.huifu.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * 投标借款人信息
 * @author Administrator
 */
public class BorrowerDetails {
	
	/**借款人客户号**/
	@JsonProperty
    private String BorrowerCustId;
    
    /**借款金额**/
	@JsonProperty
    private String BorrowerAmt;
    
    /**借款手续费率**/
	@JsonProperty
    private String BorrowerRate;
    
    /**标的ID**/
	@JsonProperty
    private String ProId;

	public BorrowerDetails() { }

	@JsonIgnore
	public String getBorrowerCustId() {
		return BorrowerCustId;
	}
	@JsonIgnore
	public void setBorrowerCustId(String borrowerCustId) {
		BorrowerCustId = borrowerCustId;
	}
	@JsonIgnore
	public String getBorrowerAmt() {
		return BorrowerAmt;
	}
	@JsonIgnore
	public void setBorrowerAmt(String borrowerAmt) {
		BorrowerAmt = borrowerAmt;
	}
	@JsonIgnore
	public String getBorrowerRate() {
		return BorrowerRate;
	}
	@JsonIgnore
	public void setBorrowerRate(String borrowerRate) {
		BorrowerRate = borrowerRate;
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
