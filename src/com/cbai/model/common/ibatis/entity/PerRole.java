package com.cbai.model.common.ibatis.entity;

import java.util.Date;

import com.cbai.model.common.data.State;
 

public class PerRole {
	
    private Integer roleid;

    private Integer fid;

    private String name; 
    
    private String oldname;
    
    private String path;

    private State state;
    
    private String stname;

    private String memo;

    private Date addtime;
    
    private String selMenuIDS; 
    
    private String addMenuIDS; 
    
    private String delMenuIDS; 
    
    private Integer childCount;
    
    private String isSel;
    
     
    public PerRole() { 	}

	public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getStname() {
		return this.state.getName();
	}

	public String getSelMenuIDS() {
		return selMenuIDS;
	}

	public void setSelMenuIDS(String selMenuIDS) {
		this.selMenuIDS = selMenuIDS;
	}

	public String getIsSel() {
		return isSel;
	}

	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}

	public String getAddMenuIDS() {
		return addMenuIDS;
	}

	public void setAddMenuIDS(String addMenuIDS) {
		this.addMenuIDS = addMenuIDS;
	}

	public String getDelMenuIDS() {
		return delMenuIDS;
	}

	public void setDelMenuIDS(String delMenuIDS) {
		this.delMenuIDS = delMenuIDS;
	} 
	
}