package com.cbai.model.common.ibatis.entity;
 

public class PerRoleAccountRel {
	
    private Integer raccid;

    private Integer roleid;

    private Integer accid;

	public PerRoleAccountRel() { 
		
	}

	public Integer getRaccid() {
		return raccid;
	}

	public void setRaccid(Integer raccid) {
		this.raccid = raccid;
	}
 

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getAccid() {
		return accid;
	}

	public void setAccid(Integer accid) {
		this.accid = accid;
	}

    
}