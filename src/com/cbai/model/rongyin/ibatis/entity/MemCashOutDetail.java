package com.cbai.model.rongyin.ibatis.entity;

import java.io.Serializable;

public class MemCashOutDetail implements Serializable{
	
	/**自增ID**/
	private Integer cdid;
	
	/**资金记录ID**/
	private Integer recid;
	
	/**取现渠道**/
	private String cashChl;
	
	/**手续费收取类型**/
	private String feetype;
	
	/**手续费收取对象 **/
	private String feeobjflag;
	
	/**手续费收取子账户 **/
	private String feeaccid;
	
	/**商户收取服务费金额 **/
	private String servfee;
	
	/**商户用来收取服务费的子账户号**/
	private String servfeeaccid;
	
	/**开户银行账号**/
	private String openaccid;
	
	public MemCashOutDetail() { }

	public Integer getCdid() {
		return cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}

	public Integer getRecid() {
		return recid;
	}

	public void setRecid(Integer recid) {
		this.recid = recid;
	}

	public String getCashChl() {
		return cashChl;
	}

	public void setCashChl(String cashChl) {
		this.cashChl = cashChl;
	}

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	public String getFeeobjflag() {
		return feeobjflag;
	}

	public void setFeeobjflag(String feeobjflag) {
		this.feeobjflag = feeobjflag;
	}

	public String getFeeaccid() {
		return feeaccid;
	}

	public void setFeeaccid(String feeaccid) {
		this.feeaccid = feeaccid;
	}

	public String getServfee() {
		return servfee;
	}

	public void setServfee(String servfee) {
		this.servfee = servfee;
	}

	public String getServfeeaccid() {
		return servfeeaccid;
	}

	public void setServfeeaccid(String servfeeaccid) {
		this.servfeeaccid = servfeeaccid;
	}

	public String getOpenaccid() {
		return openaccid;
	}

	public void setOpenaccid(String openaccid) {
		this.openaccid = openaccid;
	}
	
}
