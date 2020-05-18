package com.cbai.model.rongyin.ibatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.cbai.common.utils.AmountUtils;
import com.cbai.model.common.data.State;

public class Projects implements Serializable{ 
	
	private Integer proid; 
	
	private String prtype;
	
	private String pronum;
	
	private String lname;
	  
	private String address;
	
	private String area;
	
	private String htype;
	
	private String decorate; 
	
	private String protype;
	
	private String allpriceStr;
	
	private String expriceStr;
	
	private String avgpriceStr;
	
	private Long allprice;
	
	private Long exprice;
	
	private Long avgprice;
	
	private String coordinate;
	
	private String coverurl;
	 
	/**项目备注*/
	private String memo;
	
	private String ckmemo;
	/**未审核UNCHECK 已审核 CHECKED*/
	private State state;
	
	private String stname;
	
	private Integer lusetid;
	
	/**借款用途*/
	private String usefor;
	
	/**添加时间*/
	private Date addtime;
	
	private Date checktime;
	/**录入人员ID*/
	private Integer opuid;  
	
	private String opuname;
	
	private String opmanJson; 
	
	
	
	
	public Projects() { }
	
	public Integer getProid() {
		return proid;
	}
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	public String getPronum() {
		return pronum;
	}
	public void setPronum(String pronum) {
		this.pronum = pronum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHtype() {
		return htype;
	}

	public void setHtype(String htype) {
		this.htype = htype;
	}

	public String getDecorate() {
		return decorate;
	}

	public void setDecorate(String decorate) {
		this.decorate = decorate;
	}

	public String getProtype() {
		return protype;
	}

	public void setProtype(String protype) {
		this.protype = protype;
	}
 

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCkmemo() {
		return ckmemo;
	}

	public void setCkmemo(String ckmemo) {
		this.ckmemo = ckmemo;
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

	public Integer getLusetid() {
		return lusetid;
	}

	public void setLusetid(Integer lusetid) {
		this.lusetid = lusetid;
	}

	public String getUsefor() {
		return usefor;
	}

	public void setUsefor(String usefor) {
		this.usefor = usefor;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getOpuid() {
		return opuid;
	}

	public void setOpuid(Integer opuid) {
		this.opuid = opuid;
	}

	public String getOpmanJson() {
		return opmanJson;
	}

	public void setOpmanJson(String opmanJson) {
		this.opmanJson = opmanJson;
	}

	public Long getAllprice() {
		return allprice;
	}

	public void setAllprice(Long allprice) {
		this.allprice = allprice;
	}

	public Long getExprice() {
		return exprice;
	}

	public void setExprice(Long exprice) {
		this.exprice = exprice;
	}

	public String getAllpriceStr() {
		return allpriceStr;
	}

	public void setAllpriceStr(String allpriceStr) {
		this.allpriceStr = allpriceStr;
	}

	public String getExpriceStr() {
		return expriceStr;
	}

	public void setExpriceStr(String expriceStr) {
		this.expriceStr = expriceStr;
	}

	public String getCoverurl() {
		return coverurl;
	}

	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPrtype() {
		return prtype;
	}

	public void setPrtype(String prtype) {
		this.prtype = prtype;
	}

	public String getAvgpriceStr() {
		return avgpriceStr;
	}

	public void setAvgpriceStr(String avgpriceStr) {
		this.avgpriceStr = avgpriceStr;
	}

	public Long getAvgprice() {
		return avgprice;
	}

	public void setAvgprice(Long avgprice) {
		this.avgprice = avgprice;
	}

	public String getOpuname() {
		return opuname;
	}

	public void setOpuname(String opuname) {
		this.opuname = opuname;
	}

	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	} 
	
}
