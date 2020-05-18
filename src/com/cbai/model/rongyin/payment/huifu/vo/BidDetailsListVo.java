package com.cbai.model.rongyin.payment.huifu.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class BidDetailsListVo {
	
	@JsonProperty
    private String BidOrdId;     //被转让的投标订单号
	
	@JsonProperty
    private String BidOrdDate;   //被转让的投标订单日期
	
	@JsonProperty
    private String BidCreditAmt; //转让金额 BidDetails中的转让金额总和等于CreditAmt
	
	@JsonProperty
	private List<BidDetailsListBorrowerDetailsVo>  BorrowerDetails;
	
	@JsonIgnore
	public String getBidOrdId() {
		return BidOrdId;
	}
	
	@JsonIgnore
	public void setBidOrdId(String bidOrdId) {
		BidOrdId = bidOrdId;
	}
	
	@JsonIgnore
	public String getBidOrdDate() {
		return BidOrdDate;
	}
	
	@JsonIgnore
	public void setBidOrdDate(String bidOrdDate) {
		BidOrdDate = bidOrdDate;
	}
	
	@JsonIgnore
	public String getBidCreditAmt() {
		return BidCreditAmt;
	}
	@JsonIgnore
	public void setBidCreditAmt(String bidCreditAmt) {
		BidCreditAmt = bidCreditAmt;
	}
	
	@JsonIgnore
	public List<BidDetailsListBorrowerDetailsVo> getBorrowerDetails() {
		return BorrowerDetails;
	}
	
	@JsonIgnore
	public void setBorrowerDetails(
			List<BidDetailsListBorrowerDetailsVo> borrowerDetails) {
		BorrowerDetails = borrowerDetails;
	}

}
