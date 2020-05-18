package com.cbai.model.common.ibatis.entity;

import java.util.Date;
import java.util.List;
 
import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;
 

public class PerMenu extends BaseDTO{
	
    private Integer menuid;

    private Integer fid;

    private String name;
    
    private String oldname;

    private String actionurl;
    
    private String path;

    private State state;
    
    private String stname;

    private Integer sindex;

    private String memo;
    
    private String tag;
    
    private String isdir;
    
    private Integer childCount;

    private Date addtime;
    
    private String icon;
    
    private List<PerMenu> sonList;
 
    public PerMenu() {
		 
	}
 
	public Integer getMenuid() {
		return menuid;
	}



	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
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

    public String getActionurl() {
        return actionurl;
    }

    public void setActionurl(String actionurl) {
        this.actionurl = actionurl;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getSindex() {
        return sindex;
    }

    public void setSindex(Integer sindex) {
        this.sindex = sindex;
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
	
	public String getStname() {
		return this.state.getName();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	public String getIsdir() {
		return isdir;
	}

	public void setIsdir(String isdir) {
		this.isdir = isdir;
	}

	public List<PerMenu> getSonList() {
		return sonList;
	}

	public void setSonList(List<PerMenu> sonList) {
		this.sonList = sonList;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}