package com.cbai.model.common.ibatis.entity;

import java.util.Date;
import java.util.List;

import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;
 
 
public class PerDepartment {
	
    private Integer pdid;  

    private String name;

    private State state;
    
    private String stname;
    
    private Integer sindex; 

    private String memo;  
  

    public PerDepartment() {
	 
	}  

	 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    } 

	public Integer getSindex() {
		return sindex;
	}

	public void setSindex(Integer sindex) {
		this.sindex = sindex;
	}

	public String getStname() {
		return this.state.getName();
	}



	public Integer getPdid() {
		return pdid;
	}



	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	} 
	
	
}