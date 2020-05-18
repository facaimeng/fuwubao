package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.utils.AmountUtils;
import com.cbai.model.common.data.State;


public class MemBidLogs {
    
	private Integer mblid;

    private String bidordernum;

    private String bidorderdate;
    
    private Integer dadid;

    private String dadordernum;

    private Integer poid;

    private String pordernum;

    private Integer lnid;

    private String lnnum;

    private String lusrcustid;

    private Integer memid;

    private String usrcustid;

    private Integer holdmemid;

    private String holdusrcustid;

    private Long bidmoney;

    private Long repaymoney;

    private Integer transperiod;

    private Long debtmoney;

    private Long transmoney;

    private String bidtrxid;

    private String freezeordernum;

    private String freezetrxid;

    private State state;

    private Date bidtime;

    private Date optime;

    private Date paytime;

    private Date oppaytime;

    //******增加字段************************************
    private String realname;
    
    private String lnname;
    
    private String stname;
    
    private Long profit;
    
    public Integer getMblid() {
        return mblid;
    }

    public void setMblid(Integer mblid) {
        this.mblid = mblid;
    }

    public String getBidordernum() {
        return bidordernum;
    }

    public void setBidordernum(String bidordernum) {
        this.bidordernum = bidordernum;
    }

	public String getBidorderdate() {
		return bidorderdate;
	}

	public void setBidorderdate(String bidorderdate) {
		this.bidorderdate = bidorderdate;
	}

	public Integer getDadid() {
        return dadid;
    }

    public void setDadid(Integer dadid) {
        this.dadid = dadid;
    }

    public String getDadordernum() {
        return dadordernum;
    }

    public void setDadordernum(String dadordernum) {
        this.dadordernum = dadordernum;
    }

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

    public Integer getLnid() {
        return lnid;
    }

    public void setLnid(Integer lnid) {
        this.lnid = lnid;
    }

    public String getLnnum() {
        return lnnum;
    }

    public void setLnnum(String lnnum) {
        this.lnnum = lnnum;
    }

    public String getLusrcustid() {
        return lusrcustid;
    }

    public void setLusrcustid(String lusrcustid) {
        this.lusrcustid = lusrcustid;
    }

    public Integer getMemid() {
        return memid;
    }

    public void setMemid(Integer memid) {
        this.memid = memid;
    }

    public String getUsrcustid() {
        return usrcustid;
    }

    public void setUsrcustid(String usrcustid) {
        this.usrcustid = usrcustid;
    }

    public Integer getHoldmemid() {
        return holdmemid;
    }

    public void setHoldmemid(Integer holdmemid) {
        this.holdmemid = holdmemid;
    }

    public String getHoldusrcustid() {
        return holdusrcustid;
    }

    public void setHoldusrcustid(String holdusrcustid) {
        this.holdusrcustid = holdusrcustid;
    }

    public Long getBidmoney() {
        return bidmoney;
    }

    public void setBidmoney(Long bidmoney) {
        this.bidmoney = bidmoney;
    }

    public Long getRepaymoney() {
        return repaymoney;
    }

    public void setRepaymoney(Long repaymoney) {
        this.repaymoney = repaymoney;
    }

    public Integer getTransperiod() {
        return transperiod;
    }

    public void setTransperiod(Integer transperiod) {
        this.transperiod = transperiod;
    }

    public Long getDebtmoney() {
        return debtmoney;
    }

    public void setDebtmoney(Long debtmoney) {
        this.debtmoney = debtmoney;
    }

    public Long getTransmoney() {
        return transmoney;
    }

    public void setTransmoney(Long transmoney) {
        this.transmoney = transmoney;
    }

    public String getBidtrxid() {
        return bidtrxid;
    }

    public void setBidtrxid(String bidtrxid) {
        this.bidtrxid = bidtrxid;
    }

    public String getFreezeordernum() {
        return freezeordernum;
    }

    public void setFreezeordernum(String freezeordernum) {
        this.freezeordernum = freezeordernum;
    }

    public String getFreezetrxid() {
        return freezetrxid;
    }

    public void setFreezetrxid(String freezetrxid) {
        this.freezetrxid = freezetrxid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getBidtime() {
        return bidtime;
    }

    public void setBidtime(Date bidtime) {
        this.bidtime = bidtime;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Date getOppaytime() {
        return oppaytime;
    }

    public void setOppaytime(Date oppaytime) {
        this.oppaytime = oppaytime;
    }
    
    public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public String getLnname() {
		return lnname;
	}

	public void setLnname(String lnname) {
		this.lnname = lnname;
	}

	public String getBidmoneyLabel() throws Exception{
		if(this.bidmoney!=null && !"".equals(this.bidmoney)){
		    return AmountUtils.changeF2Y(this.bidmoney);
		}else{
			return "0.00";
		}
	}
	public String getRepaymoneyLabel() throws Exception{
		if(this.repaymoney!=null && !"".equals(this.repaymoney)){
		    return AmountUtils.changeF2Y(this.repaymoney);
		}else{
			return "0.00";
		}
	}

	public String getStname() {
		if(this.state!=null){
		    return this.state.getName();
		}else{
			return State.BIDDONE.getName();
		}
	}

	public Long getProfit() {
		return profit;
	}

	public void setProfit(Long profit) {
		this.profit = profit;
	}
	
}