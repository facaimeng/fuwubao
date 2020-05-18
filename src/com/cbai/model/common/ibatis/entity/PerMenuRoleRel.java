package com.cbai.model.common.ibatis.entity;
 

public class PerMenuRoleRel {
    
	private Integer mrid;

    private Integer menuid;

    private Integer roleid;

    public PerMenuRoleRel() { }

	public Integer getMrid() {
        return mrid;
    }

    public void setMrid(Integer mrid) {
        this.mrid = mrid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}