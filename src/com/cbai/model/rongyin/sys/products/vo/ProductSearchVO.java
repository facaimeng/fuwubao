package com.cbai.model.rongyin.sys.products.vo;

import com.cbai.model.common.data.State;

public class ProductSearchVO {
	
	private Integer prtid;
	
	private String pname;
	
	private Integer bidperiod; 
	
	private String state;

	public ProductSearchVO() { 
	}

	public Integer getPrtid() {
		return prtid;
	}

	public void setPrtid(Integer prtid) {
		this.prtid = prtid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getBidperiod() {
		return bidperiod;
	}

	public void setBidperiod(Integer bidperiod) {
		this.bidperiod = bidperiod;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	 
}
