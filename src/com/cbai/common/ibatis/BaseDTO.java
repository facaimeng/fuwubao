package com.cbai.common.ibatis;

import java.util.Date;

public class BaseDTO {
	
	 private Date createstart;
	 
	 private Date createend;
	 
	 private Date pubstart;
	 
	 private Date pubend;
	 
	 private Integer oldOrderNum;
	 
	 private Integer newOrderNum;
	     
	 private String ptitle;
	    
	 private String pdescription;
	    
	 private String pkeywords;
	 

	public Integer getOldOrderNum() {
		return oldOrderNum;
	}

	public void setOldOrderNum(Integer oldOrderNum) {
		this.oldOrderNum = oldOrderNum;
	}

	public Integer getNewOrderNum() {
		return newOrderNum;
	}

	public void setNewOrderNum(Integer newOrderNum) {
		this.newOrderNum = newOrderNum;
	}

	public Date getCreatestart() {
		return createstart;
	}

	public void setCreatestart(Date createstart) {
		this.createstart = createstart;
	}

	public Date getCreateend() {
		return createend;
	}

	public void setCreateend(Date createend) {
		this.createend = createend;
	}

	public Date getPubstart() {
		return pubstart;
	}

	public void setPubstart(Date pubstart) {
		this.pubstart = pubstart;
	}

	public Date getPubend() {
		return pubend;
	}

	public void setPubend(Date pubend) {
		this.pubend = pubend;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPdescription() {
		return pdescription;
	}

	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}

	public String getPkeywords() {
		return pkeywords;
	}

	public void setPkeywords(String pkeywords) {
		this.pkeywords = pkeywords;
	}
 
}
