package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;

public class Products extends BaseDTO{
    private Integer prid;

    private Integer prtid;
    
    private String title;

    private String pname;
    
    private String prtname;
    
    private String startbidmoneyStr;

    private String maxbidmoneyStr;
 
    private Long startbidmoney;

    private Long maxbidmoney;

    private Integer bidperiod;

    private Integer holdperiod;

    private String minreturnrate;

    private String maxreturnrate;

    private String returnrate;

    private Date buystart;

    private Date buyend;

    private Integer stockmoney;

    private Integer sales;

    private Integer exitday;

    private Integer outexitday;

    private String outexitrate;

    private String memo;

    private Integer sindex;

    private State state;
    
    private String stname;

    private String iftop;

    private Integer viewcount;

    private Date addtime; 
    
    public Products() { 
	}

    public Integer getPrid() {
        return prid;
    }

    public void setPrid(Integer prid) {
        this.prid = prid;
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

    public Long getStartbidmoney() {
        return startbidmoney;
    }

    public void setStartbidmoney(Long startbidmoney) {
        this.startbidmoney = startbidmoney;
    }

    public Long getMaxbidmoney() {
        return maxbidmoney;
    }

    public void setMaxbidmoney(Long maxbidmoney) {
        this.maxbidmoney = maxbidmoney;
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

    public Date getBuystart() {
        return buystart;
    }

    public void setBuystart(Date buystart) {
        this.buystart = buystart;
    }

    public Date getBuyend() {
        return buyend;
    }

    public void setBuyend(Date buyend) {
        this.buyend = buyend;
    }

    public Integer getStockmoney() {
        return stockmoney;
    }

    public void setStockmoney(Integer stockmoney) {
        this.stockmoney = stockmoney;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getExitday() {
        return exitday;
    }

    public void setExitday(Integer exitday) {
        this.exitday = exitday;
    }

    public Integer getOutexitday() {
        return outexitday;
    }

    public void setOutexitday(Integer outexitday) {
        this.outexitday = outexitday;
    }

    public String getOutexitrate() {
        return outexitrate;
    }

    public void setOutexitrate(String outexitrate) {
        this.outexitrate = outexitrate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getSindex() {
        return sindex;
    }

    public void setSindex(Integer sindex) {
        this.sindex = sindex;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getIftop() {
        return iftop;
    }

    public void setIftop(String iftop) {
        this.iftop = iftop;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStname() {
		return this.state.getName();
	}

	public String getStartbidmoneyStr() {
		return startbidmoneyStr;
	}

	public void setStartbidmoneyStr(String startbidmoneyStr) {
		this.startbidmoneyStr = startbidmoneyStr;
	}

	public String getMaxbidmoneyStr() {
		return maxbidmoneyStr;
	}

	public void setMaxbidmoneyStr(String maxbidmoneyStr) {
		this.maxbidmoneyStr = maxbidmoneyStr;
	}

	public String getPrtname() {
		return prtname;
	}

	public void setPrtname(String prtname) {
		this.prtname = prtname;
	}
	
	
    
    
}