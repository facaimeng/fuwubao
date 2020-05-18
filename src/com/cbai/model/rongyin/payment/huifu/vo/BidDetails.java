package com.cbai.model.rongyin.payment.huifu.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class BidDetails {
	
	@JsonProperty
	private List<BidDetailsListVo> BidDetails;
	
	@JsonIgnore
	public List<BidDetailsListVo> getBidDetails() {
		return BidDetails;
	}
	
	@JsonIgnore
	public void setBidDetails(List<BidDetailsListVo> bidDetails) {
		BidDetails = bidDetails;
	}
}
