package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

public class MemCards {
    
	private Integer mbid;

    private Integer memid;

    private String bkcode; 
    
    private String bklogourl;
    
    private String bkname;
    
    private String cardnum;
    
    private Date addtime; 

	public MemCards() { 
	}

	public Integer getMbid() {
		return mbid;
	}

	public void setMbid(Integer mbid) {
		this.mbid = mbid;
	}

	public Integer getMemid() {
		return memid;
	}

	public void setMemid(Integer memid) {
		this.memid = memid;
	}

	public String getBkcode() {
		return bkcode;
	}

	public void setBkcode(String bkcode) {
		this.bkcode = bkcode;
	}

	public String getBkname() {
		return bkname;
	}

	public void setBkname(String bkname) {
		this.bkname = bkname;
	}

	public String getCardnum() {
		
		return  this.cardnum.substring(0,4)+"****"+this.cardnum.substring(this.cardnum.length()-4,this.cardnum.length());
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getBklogourl() {
		return bklogourl;
	}

	public void setBklogourl(String bklogourl) {
		this.bklogourl = bklogourl;
	}
 
    
}