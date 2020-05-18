package com.cbai.model.common.ibatis.entity;
 

public class PerUsersRoletRel {
	
    private Integer urrid;

    private Integer roleid;

    private Integer userid;

	public PerUsersRoletRel() { 
		
	}

	public Integer getUrrid() {
		return urrid;
	}

	public void setUrrid(Integer urrid) {
		this.urrid = urrid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
 
}