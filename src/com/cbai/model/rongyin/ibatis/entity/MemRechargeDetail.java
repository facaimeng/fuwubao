package com.cbai.model.rongyin.ibatis.entity;

import java.io.Serializable;

public class MemRechargeDetail implements Serializable{
	
	private Integer rdid;
	
	/**资金变动ID**/
	private Integer recid;
	
	/**手续费**/
	private String feeamt;
	
	/**手续费扣款商户号**/
	private String feecustid;
	
	/**手续费扣款商户子账户号**/
	private String feeaccid;
	
	/**支付网关代号 **/
	private String paygate;
	
	/**银行代号 **/
	private String banknum;
	
	public MemRechargeDetail() {  }

	public Integer getRdid() {
		return rdid;
	}

	public void setRdid(Integer rdid) {
		this.rdid = rdid;
	}

	public Integer getRecid() {
		return recid;
	}

	public void setRecid(Integer recid) {
		this.recid = recid;
	}

	public String getFeeamt() {
		return feeamt;
	}

	public void setFeeamt(String feeamt) {
		this.feeamt = feeamt;
	}

	public String getFeecustid() {
		return feecustid;
	}

	public void setFeecustid(String feecustid) {
		this.feecustid = feecustid;
	}

	public String getFeeaccid() {
		return feeaccid;
	}

	public void setFeeaccid(String feeaccid) {
		this.feeaccid = feeaccid;
	}

	public String getPaygate() {
		return paygate;
	}

	public void setPaygate(String paygate) {
		this.paygate = paygate;
	}

	public String getBanknum() {
		return banknum;
	}

	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}
	
}
