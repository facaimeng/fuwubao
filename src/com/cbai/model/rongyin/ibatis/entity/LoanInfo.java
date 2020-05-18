package com.cbai.model.rongyin.ibatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.cbai.common.utils.AmountUtils;
import com.cbai.model.common.data.State;

public class LoanInfo implements Serializable{
	
	private static final long serialVersionUID = 2915843812058150120L;

	/**
	 * 主键Id,自增
	 */
	private Integer lnid;
	
	/**
	 * 项目ID
	 */
	private Integer proid;

	/**
	 * 项目编号
	 */
	private String pronum;
	
	/**
	 * 标的类型 1新手标, 2非新手标... 非汇付字段
	 */
	private Integer lntype;
	
	/**
	 * 担保机构ID
	 */
	private Integer cmid;
	
	/**
	 * 担保机构名称
	 */
	private String cname;
	
	/**
	 * 汇付返回标的编号
	 */
	private String hfproid;
	
	/**
	 * 标的是否定向放款
	 */
	private String ifdirect;
	
	/**
	 * 标的定向放款人类型:个人01;企业02
	 */
	private String directtype;
	
	/**
	 * 标的定向收款人汇付ID
	 */
	private String dusrcustid;
	
	/**
	 * 定向收款人姓名
	 */
	private String dman;
	
	/**
	 * 业务编号，给汇付
	 */
	private String lnnum;
	
	/**
	 * 标的名称
	 */
	private String name;
	
	/**
	 * 标的类别编号 01：信用等等
	 */
	private String bidtype;
	
	/**
	 * 标的类别名称
	 */
	private String bidtypename;
	
	/**
	 * 标的金额
	 */
	private Long loanmoney;
	
	/**
	 * 最低投资金额
	 */
	private Long minimoney;
	
	/**
	 * 可投金额，默认等于标的金额
	 */
	private Long avalmoney;
	
	/**
	 * 已还金额，默认0
	 */
	private Long repayedmoney;
	
	/**
	 * 已放款金额
	 */
	private Long payformoney;
	
	/**
	 * 已募集金额
	 */
	private Long collectmoney;
	
	/**
	 * 标年利率
	 */
	private String yearate;
	
	/**
	 * 应还总利息
	 */
	private Integer allinterest;
	
	/**
	 * 借款期限
	 */
	private Integer loandead;
	
	/**
	 * 借款期限单位：天，月，年
	 */
	private String  loandeadunit;
	
	/**
	 * 还款方式：01等等
	 */
	private String repaytype;
	
	/**
	 * 还款方式名称
	 */
	private String repaytypename;
	
	/**
	 * 还款次数
	 */
	private Integer repaytimes;
	
	/**
	 * 投标开始日期
	 */
	private Date startdate;
	
	/**
	 * 投标结束日期
	 */
	private Date enddate;
	
	/**
	 * 应还款日
	 */
	private Date repaydate;
	
	/**
	 * 放款款日
	 */
	private Date loantime;
	
	/**
	 * 最后还款日期
	 */
	private Date lastrepaydate;
	
	/**
	 * 本息保障 :01等等
	 */
	private String warrtype;
	
	/**
	 * 本息保障名称
	 */
	private String warrtypename;
	
	/**
	 * 标的产品编号 01：房贷类等等
	 */
	private String protype;
	
	/**
	 * 标的产品名称
	 */
	private String protypename;
	
	/**
	 * 风险控制方式编号
	 */
	private String risktype;
	
	/**
	 * 风险控制方式名称
	 */
	private String risktypename;
	
	/**
	 * 逾期是否垫资：1,2
	 */
	private String bidpayfor;
	
	/**
	 * 借款人类型：01,02
	 */
	private String lmantype;
	
	/**
	 * 借款人类型名称
	 */
	private String lmantypename;
	
	/**
	 * 借款人名称：个人名字
	 */
	private String lman;
	
	/**
	 * 借款人名称：个人主键id
	 */
	private Integer lmanuid;
	
	/**
	 * 借款人汇付ID
	 */
	private String lusrcustid;
	
	/**
	 * 借款人证件号：身份证或执照
	 */
	private String lcertnum;
	
	/**
	 * 借款人手机号
	 */
	private String lmanphone;
	
	/**
	 * 借款用途
	 */
	private String usefor;
	
	/**
	 * 审核状态
	 */
	private String auditstat;
	
	/**
	 * 审核状态描述
	 */
	private String auditdesc;
	
	/**
	 * 标的审核状态 等待审核--筹集中--已放款--已还款
	 */
	private State status;
	
	/**
	 * 标的审核状态
	 */
	private String checkmemo;
	
	/**
	 * 定向划拨订单号
	 */
	private String dirordid;
	
	/**
	 * 定向划拨状态
	 */
	private State dirstate;
	
	/**
	 * 定向划拨请求时间
	 */
	private Date dirtime;
	
	/**
	 * 定向划拨请求处理时间
	 */
	private Date diroptime;
	
	private String charges;
	
	/**
	 * 标的发布时间
	 */
	private Date pubtime;
	
	private String loanMoneyLabel = "0.00";
	private String avalMoneyLabel = "0.00";
	private String repayedMoneyLabel = "0.00";
	private String miniMoneyLabel = "0.00";
	private String payformoneyLabel = "0.00";
	
	private String collectmoneyLabel = "0.00";
	
	public LoanInfo() { }

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

	public String getPronum() {
		return pronum;
	}

	public void setPronum(String pronum) {
		this.pronum = pronum;
	}

	public Integer getLntype() {
		return lntype;
	}

	public void setLntype(Integer lntype) {
		this.lntype = lntype;
	}

	public Integer getCmid() {
		return cmid;
	}

	public void setCmid(Integer cmid) {
		this.cmid = cmid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getHfproid() {
		return hfproid;
	}

	public void setHfproid(String hfproid) {
		this.hfproid = hfproid;
	}

	public String getIfdirect() {
		return ifdirect;
	}

	public void setIfdirect(String ifdirect) {
		this.ifdirect = ifdirect;
	}

	public String getDirecttype() {
		return directtype;
	}

	public void setDirecttype(String directtype) {
		this.directtype = directtype;
	}

	public String getDusrcustid() {
		return dusrcustid;
	}

	public void setDusrcustid(String dusrcustid) {
		this.dusrcustid = dusrcustid;
	}

	public String getDman() {
		return dman;
	}

	public void setDman(String dman) {
		this.dman = dman;
	}

	public String getLnnum() {
		return lnnum;
	}

	public void setLnnum(String lnnum) {
		this.lnnum = lnnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBidtype() {
		return bidtype;
	}

	public void setBidtype(String bidtype) {
		this.bidtype = bidtype;
	}

	public String getBidtypename() {
		return bidtypename;
	}

	public void setBidtypename(String bidtypename) {
		this.bidtypename = bidtypename;
	}

	public Long getLoanmoney() {
		return loanmoney;
	}

	public void setLoanmoney(Long loanmoney) throws Exception{
		this.loanmoney = loanmoney;
		if(this.loanmoney != null){
			this.loanMoneyLabel = AmountUtils.changeF2Y(String.valueOf(this.loanmoney));
		}
	}

	public Long getMinimoney() {
		return minimoney;
		
	}

	public void setMinimoney(Long minimoney) throws Exception{
		this.minimoney = minimoney;
		if(this.minimoney != null){
			this.miniMoneyLabel = AmountUtils.changeF2Y(String.valueOf(this.minimoney));
		}	
	}

	public Long getAvalmoney() {
		return avalmoney;
	}

	public void setAvalmoney(Long avalmoney) throws Exception{
		this.avalmoney = avalmoney;
		if(this.avalmoney != null){
			this.avalMoneyLabel = AmountUtils.changeF2Y(String.valueOf(this.avalmoney));
		}
	}

	public Long getPayformoney() {
		return payformoney;
	}

	public void setPayformoney(Long payformoney) throws Exception{
		this.payformoney = payformoney;
		
		if(this.payformoney != null){
			this.payformoneyLabel = AmountUtils.changeF2Y(String.valueOf(this.payformoney));
		}
	}

	public Long getRepayedmoney() {
		return repayedmoney;
	}

	public void setRepayedmoney(Long repayedmoney) throws Exception{
		this.repayedmoney = repayedmoney;
		if(this.repayedmoney != null){
			this.repayedMoneyLabel = AmountUtils.changeF2Y(String.valueOf(this.repayedmoney));
		}
	}
	
	
	public Long getCollectmoney(){
		return collectmoney;
	}

	public void setCollectmoney(Long collectmoney) throws Exception{
		this.collectmoney = collectmoney;
		if(this.collectmoney != null){
			this.collectmoneyLabel = AmountUtils.changeF2Y(String.valueOf(this.collectmoney));
		}
	}

	public String getCollectmoneyLabel() {
		return collectmoneyLabel;
	}

	public String getYearate() {
		return yearate;
	}

	public void setYearate(String yearate) {
		this.yearate = yearate;
	}

	public Integer getAllinterest() {
		return allinterest;
	}

	public void setAllinterest(Integer allinterest) {
		this.allinterest = allinterest;
	}

	public Integer getLoandead() {
		return loandead;
	}

	public void setLoandead(Integer loandead) {
		this.loandead = loandead;
	}

	public String getLoandeadunit() {
		return loandeadunit;
	}

	public void setLoandeadunit(String loandeadunit) {
		this.loandeadunit = loandeadunit;
	}

	public String getRepaytype() {
		return repaytype;
	}

	public void setRepaytype(String repaytype) {
		this.repaytype = repaytype;
	}

	public String getRepaytypename() {
		return repaytypename;
	}

	public void setRepaytypename(String repaytypename) {
		this.repaytypename = repaytypename;
	}

	public Integer getRepaytimes() {
		return repaytimes;
	}

	public void setRepaytimes(Integer repaytimes) {
		this.repaytimes = repaytimes;
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

	public Date getLoantime() {
		return loantime;
	}

	public void setLoantime(Date loantime) {
		this.loantime = loantime;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getRepaydate() {
		return repaydate;
	}

	public void setRepaydate(Date repaydate) {
		this.repaydate = repaydate;
	}

	public Date getLastrepaydate() {
		return lastrepaydate;
	}

	public void setLastrepaydate(Date lastrepaydate) {
		this.lastrepaydate = lastrepaydate;
	}

	public String getWarrtype() {
		return warrtype;
	}

	public void setWarrtype(String warrtype) {
		this.warrtype = warrtype;
	}

	public String getWarrtypename() {
		return warrtypename;
	}

	public void setWarrtypename(String warrtypename) {
		this.warrtypename = warrtypename;
	}

	public String getProtype() {
		return protype;
	}

	public void setProtype(String protype) {
		this.protype = protype;
	}

	public String getProtypename() {
		return protypename;
	}

	public void setProtypename(String protypename) {
		this.protypename = protypename;
	}

	public String getRisktype() {
		return risktype;
	}

	public void setRisktype(String risktype) {
		this.risktype = risktype;
	}

	public String getRisktypename() {
		return risktypename;
	}

	public void setRisktypename(String risktypename) {
		this.risktypename = risktypename;
	}

	public String getBidpayfor() {
		return bidpayfor;
	}

	public void setBidpayfor(String bidpayfor) {
		this.bidpayfor = bidpayfor;
	}

	public String getLmantype() {
		return lmantype;
	}

	public void setLmantype(String lmantype) {
		this.lmantype = lmantype;
	}

	public String getLmantypename() {
		return lmantypename;
	}

	public void setLmantypename(String lmantypename) {
		this.lmantypename = lmantypename;
	}

	public String getLman() {
		return lman;
	}

	public void setLman(String lman) {
		this.lman = lman;
	}

	public Integer getLmanuid() {
		return lmanuid;
	}

	public void setLmanuid(Integer lmanuid) {
		this.lmanuid = lmanuid;
	}

	public String getLusrcustid() {
		return lusrcustid;
	}

	public void setLusrcustid(String lusrcustid) {
		this.lusrcustid = lusrcustid;
	}

	public String getLcertnum() {
		return lcertnum;
	}

	public void setLcertnum(String lcertnum) {
		this.lcertnum = lcertnum;
	}

	public String getLmanphone() {
		return lmanphone;
	}

	public void setLmanphone(String lmanphone) {
		this.lmanphone = lmanphone;
	}

	public String getUsefor() {
		return usefor;
	}

	public void setUsefor(String usefor) {
		this.usefor = usefor;
	}

	public String getAuditstat() {
		return auditstat;
	}

	public void setAuditstat(String auditstat) {
		this.auditstat = auditstat;
	}

	public String getAuditdesc() {
		return auditdesc;
	}

	public void setAuditdesc(String auditdesc) {
		this.auditdesc = auditdesc;
	}

	public State getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status;
	}

	public String getCheckmemo() {
		return checkmemo;
	}

	public void setCheckmemo(String checkmemo) {
		this.checkmemo = checkmemo;
	}

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	public String getDirordid() {
		return dirordid;
	}

	public void setDirordid(String dirordid) {
		this.dirordid = dirordid;
	}

	public State getDirstate() {
		return dirstate;
	}

	public void setDirstate(State dirstate) {
		this.dirstate = dirstate;
	}

	public Date getDirtime() {
		return dirtime;
	}

	public void setDirtime(Date dirtime) {
		this.dirtime = dirtime;
	}

	public Date getDiroptime() {
		return diroptime;
	}

	public void setDiroptime(Date diroptime) {
		this.diroptime = diroptime;
	}

	public String getLoanMoneyLabel() {
		return loanMoneyLabel;
	}

	public String getAvalMoneyLabel() {
		return avalMoneyLabel;
	}

	public String getRepayedMoneyLabel() {
		return repayedMoneyLabel;
	}

	public String getPayformoneyLabel() {
		return payformoneyLabel;
	}

	public String getMiniMoneyLabel() {
		return miniMoneyLabel;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}
}
