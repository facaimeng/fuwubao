package com.cbai.model.rongyin.pc.member.service;

import java.util.Map;

public interface LoanTenderService {
	
    public Map<String,String> buyInitiativeTender(Integer memid, String usrCustId, Integer lnid,  String transAmt) throws Exception;
    
}
