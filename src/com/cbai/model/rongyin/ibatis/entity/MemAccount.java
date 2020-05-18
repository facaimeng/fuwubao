package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

public class MemAccount {
    
	private Integer maccid;

    private Integer memid;

    private Long allassets;

    private Long ydayrepay;

    private Long allrepay;

    private Long avlmoney;

    private Long avlprofit;

    private Long allinmoney;

    private Long alloutmoney;

    private Long allinprofit;
    
    private Long alloutprofit;
    
    private Date lastcaltime;

    public Integer getMaccid() {
        return maccid;
    }

    public void setMaccid(Integer maccid) {
        this.maccid = maccid;
    }

    public Integer getMemid() {
        return memid;
    }

    public void setMemid(Integer memid) {
        this.memid = memid;
    }

    public Long getAllassets() {
        return allassets;
    }

    public void setAllassets(Long allassets) {
        this.allassets = allassets;
    }

    public Long getYdayrepay() {
        return ydayrepay;
    }

    public void setYdayrepay(Long ydayrepay) {
        this.ydayrepay = ydayrepay;
    }

    public Long getAllrepay() {
        return allrepay;
    }

    public void setAllrepay(Long allrepay) {
        this.allrepay = allrepay;
    }

    public Long getAvlmoney() {
        return avlmoney;
    }

    public void setAvlmoney(Long avlmoney) {
        this.avlmoney = avlmoney;
    }

    public Long getAvlprofit() {
        return avlprofit;
    }

    public void setAvlprofit(Long avlprofit) {
        this.avlprofit = avlprofit;
    }

    public Long getAllinmoney() {
        return allinmoney;
    }

    public void setAllinmoney(Long allinmoney) {
        this.allinmoney = allinmoney;
    }

    public Long getAlloutmoney() {
        return alloutmoney;
    }

    public void setAlloutmoney(Long alloutmoney) {
        this.alloutmoney = alloutmoney;
    }

    public Long getAllinprofit() {
		return allinprofit;
	}

	public void setAllinprofit(Long allinprofit) {
		this.allinprofit = allinprofit;
	}

	public Long getAlloutprofit() {
		return alloutprofit;
	}

	public void setAlloutprofit(Long alloutprofit) {
		this.alloutprofit = alloutprofit;
	}

	public Date getLastcaltime() {
        return lastcaltime;
    }

    public void setLastcaltime(Date lastcaltime) {
        this.lastcaltime = lastcaltime;
    }
}