package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;
import java.util.List;

import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;
 

public class Articleptype extends BaseDTO{
	
	private Integer aptid;

    private String name;
    
    private String aptype;

    private Integer sindex;
    
    private String memo;
    
    private Integer viewcount;

    private Date addtime;
    
    private List atypeList;

 
    public Integer getAptid() {
		return aptid;
	}

	public void setAptid(Integer aptid) {
		this.aptid = aptid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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

	public String getAptype() {
		return aptype;
	}

	public void setAptype(String aptype) {
		this.aptype = aptype;
	}

	public List getAtypeList() {
		return atypeList;
	}

	public void setAtypeList(List atypeList) {
		this.atypeList = atypeList;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
