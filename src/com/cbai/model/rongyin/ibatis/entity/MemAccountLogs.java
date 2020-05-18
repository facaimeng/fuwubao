package com.cbai.model.rongyin.ibatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.cbai.model.common.data.State;

public class MemAccountLogs implements Serializable{
	
	private Integer recid;
	private State ltype;
	private Integer memid;
	private String usrcustid;
	private String title;
	private Long transmoney;
	private Long curbalance;
	private String ordid;
	private String trxid;
	private State state;
	private Date addtime;
	private Date optime;
	
	public MemAccountLogs() { }
	
	public Integer getRecid() {
		return recid;
	}
	public void setRecid(Integer recid) {
		this.recid = recid;
	}
	public State getLtype() {
		return ltype;
	}
	public void setLtype(State ltype) {
		this.ltype = ltype;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public String getUsrcustid() {
		return usrcustid;
	}
	public void setUsrcustid(String usrcustid) {
		this.usrcustid = usrcustid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getTransmoney() {
		return transmoney;
	}
	public void setTransmoney(Long transmoney) {
		this.transmoney = transmoney;
	}
	
	public Long getCurbalance() {
		return curbalance;
	}

	public void setCurbalance(Long curbalance) {
		this.curbalance = curbalance;
	}

	public String getOrdid() {
		return ordid;
	}
	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}
	public String getTrxid() {
		return trxid;
	}
	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}

	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getOptime() {
		return optime;
	}
	public void setOptime(Date optime) {
		this.optime = optime;
	}
}
