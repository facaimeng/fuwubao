package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;
import java.util.List; 

import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;

public class Articletype extends BaseDTO{
	
    private Integer atid;
    
    private Integer aptid;  
    
    private String atype;

    private String atname;
    
    private String memo;
 
    private Integer sindex;
    
    private Integer viewcount;

    private Date addtime; 

    public Integer getAtid() {
        return atid;
    }

    public void setAtid(Integer atid) {
        this.atid = atid;
    }

    public String getAtname() {
        return atname;
    }

    public void setAtname(String atname) {
        this.atname = atname;
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

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public Integer getAptid() {
		return aptid;
	}

	public void setAptid(Integer aptid) {
		this.aptid = aptid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
 
}