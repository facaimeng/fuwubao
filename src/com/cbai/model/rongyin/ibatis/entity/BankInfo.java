package com.cbai.model.rongyin.ibatis.entity;

import com.cbai.model.common.data.State;

public class BankInfo {
	
    private Integer bkid;

    private String name;

    private String bkcode;

    private String bklogourl;

    private Integer sindex;

    private String ifmem;

    private String ifcom;
    
    private String memo;

    private State state;
    
    private String stname;

     

    public Integer getBkid() {
		return bkid;
	}

	public void setBkid(Integer bkid) {
		this.bkid = bkid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBkcode() {
        return bkcode;
    }

    public void setBkcode(String bkcode) {
        this.bkcode = bkcode;
    }

    public String getBklogourl() {
        return bklogourl;
    }

    public void setBklogourl(String bklogourl) {
        this.bklogourl = bklogourl;
    }

    public Integer getSindex() {
        return sindex;
    }

    public void setSindex(Integer sindex) {
        this.sindex = sindex;
    }

    public String getIfmem() {
        return ifmem;
    }

    public void setIfmem(String ifmem) {
        this.ifmem = ifmem;
    }

    public String getIfcom() {
        return ifcom;
    }

    public void setIfcom(String ifcom) {
        this.ifcom = ifcom;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    
    
}