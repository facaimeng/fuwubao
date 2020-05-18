package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.model.common.data.State;

public class ProductOrders {
    
	private Integer poid;

    private String pordernum;

    private Integer proid;

    private Integer bidperiod;

    private Integer holdperiod;

    private String minreturnrate;

    private String maxreturnrate;

    private String returnrate;

    private Integer memid;

    private Long payment;

    private Long avlmoney;

    private State state;
    
    private String stname;

    private Long outprofit;

    private Date calcprofitdate;

    private Date lastprofitdate;

    private Date addtime;

    public Integer getPoid() {
        return poid;
    }

    public void setPoid(Integer poid) {
        this.poid = poid;
    }

    public String getPordernum() {
        return pordernum;
    }

    public void setPordernum(String pordernum) {
        this.pordernum = pordernum;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public Integer getBidperiod() {
        return bidperiod;
    }

    public void setBidperiod(Integer bidperiod) {
        this.bidperiod = bidperiod;
    }

    public Integer getHoldperiod() {
        return holdperiod;
    }

    public void setHoldperiod(Integer holdperiod) {
        this.holdperiod = holdperiod;
    }

    public String getMinreturnrate() {
        return minreturnrate;
    }

    public void setMinreturnrate(String minreturnrate) {
        this.minreturnrate = minreturnrate;
    }

    public String getMaxreturnrate() {
        return maxreturnrate;
    }

    public void setMaxreturnrate(String maxreturnrate) {
        this.maxreturnrate = maxreturnrate;
    }

    public String getReturnrate() {
        return returnrate;
    }

    public void setReturnrate(String returnrate) {
        this.returnrate = returnrate;
    }

    public Integer getMemid() {
        return memid;
    }

    public void setMemid(Integer memid) {
        this.memid = memid;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Long getAvlmoney() {
        return avlmoney;
    }

    public void setAvlmoney(Long avlmoney) {
        this.avlmoney = avlmoney;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getOutprofit() {
        return outprofit;
    }

    public void setOutprofit(Long outprofit) {
        this.outprofit = outprofit;
    }

    public Date getCalcprofitdate() {
        return calcprofitdate;
    }

    public void setCalcprofitdate(Date calcprofitdate) {
        this.calcprofitdate = calcprofitdate;
    }

    public Date getLastprofitdate() {
        return lastprofitdate;
    }

    public void setLastprofitdate(Date lastprofitdate) {
        this.lastprofitdate = lastprofitdate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public String getStname() {
		return this.state.getName();
	}
    
    
}