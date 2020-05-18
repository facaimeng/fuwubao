package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

public class ProjectAttach {
	
    private Integer prattid;

    private Integer proid;

    private String name;

    private String fsize;

    private Integer sindex;

    private String ext;

    private String furl;
    
    private String memo;

    private Date uptime;

    

    public ProjectAttach() { 
	}

	public Integer getPrattid() {
		return prattid;
	}

	public void setPrattid(Integer prattid) {
		this.prattid = prattid;
	}

	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
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