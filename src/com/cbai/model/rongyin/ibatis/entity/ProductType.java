package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;
import java.util.List;

import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;
 
 
public class ProductType extends BaseDTO{
	
    private Integer prtid;  

    private String name;

    private State state;
    
    private String stname;
    
    private Integer sindex; 

    private String memo;  
  

    public ProductType() {
	 
	}  

	public Integer getPrtid() {
        return prtid;
    }

    public void setPrtid(Integer prtid) {
        this.prtid = prtid;
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
}