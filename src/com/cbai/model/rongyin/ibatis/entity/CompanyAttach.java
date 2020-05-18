package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

public class CompanyAttach {
    private Integer cmattid;

    private Integer cmid;

    private String name;

    private String fsize;

    private Integer sindex;

    private String ext;

    private String furl;
    
    private String memo;

    private Date uptime;

    public Integer getCmattid() {
        return cmattid;
    }

    public void setCmattid(Integer cmattid) {
        this.cmattid = cmattid;
    }

    public Integer getCmid() {
        return cmid;
    }

    public void setCmid(Integer cmid) {
        this.cmid = cmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFsize() {
        return fsize;
    }

    public void setFsize(String fsize) {
        this.fsize = fsize;
    }

    public Integer getSindex() {
        return sindex;
    }

    public void setSindex(Integer sindex) {
        this.sindex = sindex;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    
    
}