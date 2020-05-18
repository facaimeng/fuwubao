package com.cbai.model.rongyin.pc.web.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cbai.common.utils.AmountUtils;

public class LoanFrontVo {
	
	/**标的编号**/
	private Integer lnid;
	
	private Integer proid;
	
	/**标的类型**/
	private Integer lntype;
	
	/**标的名称**/
    private String name;
    
    /**开始投标时间**/
    private Date startdate;
    
    /**结束时间**/
    private Date enddate;
    
    
    /**标的房源信名称**/
    private String address;
    
    /**标的房源信封面图片**/
    private String coverurl;
    
    /**标的总金额**/
    private Long loanmoney;
    
    /**标的总金额**/
    private Long collectmoney;
    
    /**起投金额**/
    private Long minimoney;
    
    /**剩余可投金额**/
    private Long avalmoney;
    
    /**年化收益**/
    private String yearate;
    
    /**投资周期**/
    private Integer loandead;
    
    /**资产总额**/
    private Long allprice;

    /**市场平均售价**/
    private Long avgprice;
    
    /**预期交易金额**/
    private Long exprice;
    
    /**每千元预期利息**/
    private Long earnings;
    
    /**借款人汇付ID**/
    private String lusrcustid;
    
    /**标的编号**/
    private String lnnum;
    
    private String area;
    
    private String protype;
    
    private Integer prtype;
    
    private String htype;
    
    private String decorate;
    
    private String coordinate;
    
    private String memo;
    
    
    public List<ProjectImgsVo> projectImages = new ArrayList<ProjectImgsVo>();
    public List<ProjectImgsVo> projectAttchs = new ArrayList<ProjectImgsVo>();
    
    
	public LoanFrontVo() { }

	public Integer getLnid() {
		return lnid;
	}

	public void setLnid(Integer lnid) {
		this.lnid = lnid;
	}
	
	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public Integer getLntype() {
		return lntype;
	}

	public void setLntype(Integer lntype) {
		this.lntype = lntype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoverurl() {
		return coverurl;
	}

	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}

	public Long getLoanmoney() {
		return loanmoney;
	}

	public void setLoanmoney(Long loanmoney) {
		this.loanmoney = loanmoney;
	}

	public Long getCollectmoney() {
		return collectmoney;
	}

	public void setCollectmoney(Long collectmoney) {
		this.collectmoney = collectmoney;
	}

	public Long getMinimoney() {
		return minimoney;
	}

	public void setMinimoney(Long minimoney) {
		this.minimoney = minimoney;
	}

	public Long getAvalmoney() {
		return avalmoney;
	}

	public void setAvalmoney(Long avalmoney) {
		this.avalmoney = avalmoney;
	}

	public String getYearate() {
		return yearate;
	}
	
	public void setLoandead(Integer loandead) {
		this.loandead = loandead;
	}
	
	public Integer getLoandead() {
		return loandead;
	}
	
	public void setYearate(String yearate) {
		this.yearate = yearate;
	}
	
	public Date getStartdate() {
		return startdate;
	}
    
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Long getAllprice() {
		return allprice;
	}

	public void setAllprice(Long allprice) {
		this.allprice = allprice;
	}

	public Long getAvgprice() {
		return avgprice;
	}

	public void setAvgprice(Long avgprice) {
		this.avgprice = avgprice;
	}

	public Long getExprice() {
		return exprice;
	}

	public void setExprice(Long exprice) {
		this.exprice = exprice;
	}
	
	public String getLusrcustid() {
		return lusrcustid;
	}

	public void setLusrcustid(String lusrcustid) {
		this.lusrcustid = lusrcustid;
	}

	public String getLnnum() {
		return lnnum;
	}

	public void setLnnum(String lnnum) {
		this.lnnum = lnnum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProtype() {
		return protype;
	}

	public void setProtype(String protype) {
		this.protype = protype;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getHtype() {
		return htype;
	}

	public void setHtype(String htype) {
		this.htype = htype;
	}

	public Integer getPrtype() {
		return prtype;
	}

	public void setPrtype(Integer prtype) {
		this.prtype = prtype;
	}

	public String getDecorate() {
		return decorate;
	}

	public void setDecorate(String decorate) {
		this.decorate = decorate;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public Long getEarnings(){
		
		if(this.yearate!=null){
			//分为单位的1000元收益
			String yearRae = AmountUtils.calIncome(this.getLoandead(), AmountUtils.yearRate(this.yearate), "1000");
			
			BigDecimal dec1 = new BigDecimal(yearRae);
			BigDecimal dec2 = new BigDecimal("100");
			this.earnings = dec1.multiply(dec2).longValue();
		}
		
		return earnings;
	}

    
    public List<ProjectImgsVo> getProjectImages() {
		return projectImages;
	}

	public void setProjectImages(List<ProjectImgsVo> projectImages) {
		this.projectImages = projectImages;
	}

	public List<ProjectImgsVo> getProjectAttchs() {
		return projectAttchs;
	}

	public void setProjectAttchs(List<ProjectImgsVo> projectAttchs) {
		this.projectAttchs = projectAttchs;
	}

}
