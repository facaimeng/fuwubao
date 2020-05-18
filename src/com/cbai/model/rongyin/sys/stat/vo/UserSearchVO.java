package com.cbai.model.rongyin.sys.stat.vo;

import java.util.Date;

import com.cbai.model.common.data.State;
import com.cbai.model.common.ibatis.entity.PerAccount;

public class UserSearchVO {

	private Integer userid;  
	
    private String lname;  
    
    private Integer ldead;  
    
    private Long lmoney;  

    private Date bidtime; 
    
    private String bidman;
    
    private Long bidmoney;
    
    private Long bidprofit;
    
    private String uname;
    
    private String uphone;
    
    private String rate1;
    
    private Long uprofit1;
    
    private String rate2;
    
    private Long uprofit2; 
    
    
     
	public UserSearchVO() { 
	}



	public Integer getUserid() {
		return userid;
	}



	public void setUserid(Integer userid) {
		this.userid = userid;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public Integer getLdead() {
		return ldead;
	}



	public void setLdead(Integer ldead) {
		this.ldead = ldead;
	}



	public Long getLmoney() {
		return lmoney;
	}



	public void setLmoney(Long lmoney) {
		this.lmoney = lmoney;
	}



	public Date getBidtime() {
		return bidtime;
	}



	public void setBidtime(Date bidtime) {
		this.bidtime = bidtime;
	}



	public String getBidman() {
		return bidman;
	}



	public void setBidman(String bidman) {
		this.bidman = bidman;
	}



	public Long getBidmoney() {
		return bidmoney;
	}



	public void setBidmoney(Long bidmoney) {
		this.bidmoney = bidmoney;
	}



	public Long getBidprofit() {
		return bidprofit;
	}



	public void setBidprofit(Long bidprofit) {
		this.bidprofit = bidprofit;
	}



	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public String getUphone() {
		return uphone;
	}



	public void setUphone(String uphone) {
		this.uphone = uphone;
	}



	public String getRate1() {
		return rate1;
	}



	public void setRate1(String rate1) {
		this.rate1 = rate1;
	}



	public Long getUprofit1() {
		return uprofit1;
	}



	public void setUprofit1(Long uprofit1) {
		this.uprofit1 = uprofit1;
	}



	public String getRate2() {
		return rate2;
	}



	public void setRate2(String rate2) {
		this.rate2 = rate2;
	}



	public Long getUprofit2() {
		return uprofit2;
	}



	public void setUprofit2(Long uprofit2) {
		this.uprofit2 = uprofit2;
	}
 
	
}
