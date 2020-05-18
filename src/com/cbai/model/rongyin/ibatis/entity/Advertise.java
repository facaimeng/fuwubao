package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.ibatis.BaseDTO;
import com.cbai.model.common.data.State;

public class Advertise extends BaseDTO{
	
	private Integer adid;
 	
	private String adtitle;
	
	private String adtype;
	
	private String adurl;
	
	private String imgurl;
	
	private State state;
	
	private String stname;
	
	private Integer sindex;
	
	private Date addtime;
 
	public Advertise() {}

	public Integer getAdid() {
		return adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}
 
	public String getAdtitle() {
		return adtitle;
	}

	public void setAdtitle(String adtitle) {
		this.adtitle = adtitle;
	}

	public String getAdurl() {
		return adurl;
	}

	public void setAdurl(String adurl) {
		this.adurl = adurl;
	}
 
	public Integer getSindex() {
		return sindex;
	}

	public void setSindex(Integer sindex) {
		this.sindex = sindex;
	}
 
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getAdtype() {
		return adtype;
	}

	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getStname() {
		return this.state.getName();
	} 
}
