package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.ibatis.BaseDTO;
 

public class Article extends BaseDTO{
	
    private Integer aid;

    private Integer atid;
 
    private String title;

    private Integer viewcount;

    private Integer sindex;
    
    private String ifindex;

    private Date pubtime;

    private String content;
    

    public Article() {
		 
	}

    public Integer getAid() {
        return aid;
    }


	public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAtid() {
        return atid;
    }

    public void setAtid(Integer atid) {
        this.atid = atid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Integer getSindex() {
        return sindex;
    }

    public void setSindex(Integer sindex) {
        this.sindex = sindex;
    }

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getIfindex() {
		return ifindex;
	}

	public void setIfindex(String ifindex) {
		this.ifindex = ifindex;
	}
 
    
}