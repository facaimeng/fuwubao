package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;

public class News extends BaseDTO{
    
	private Integer nid;

    private State ntype;
    
    private String ifindex; 

    private String title;
    
    private String source;
    
    private String author;

    private Integer sindex;
 
    private Integer viewcount;

    private Date pubtime;

    private String content;
    
    private String wxcontent; 
	
	private String coverurl;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public State getNtype() {
        return ntype;
    }

    public void setNtype(State ntype) {
        this.ntype = ntype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSindex() {
        return sindex;
    }

    public void setSindex(Integer sindex) {
        this.sindex = sindex;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
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

	public String getWxcontent() {
		return wxcontent;
	}

	public void setWxcontent(String wxcontent) {
		this.wxcontent = wxcontent;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	} 

	public String getCoverurl() {
		return coverurl;
	}

	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	 
 
}