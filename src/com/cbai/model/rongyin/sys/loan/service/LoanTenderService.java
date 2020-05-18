package com.cbai.model.rongyin.sys.loan.service;

import com.cbai.common.json.JsonResultObject;
import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.Members;
import com.cbai.model.rongyin.ibatis.entity.ProductOrders;

public interface LoanTenderService {
	
	/**
	 * 自动投标
	 * @param member 购买者信息
	 * @param loanInfo 标的信息
	 * @param productOrder 产品订单信息
	 * @param transAmt 交易金额
	 * @return
	 * @throws Exception
	 */
    public JsonResultObject saveAutoTender(Members member,
			LoanInfo loanInfo, ProductOrders productOrder, String transAmt) throws Exception;
    
    /**
     * 手动投标(未测试)
     * @param usrCustId
     * @return
     * @throws Exception
     */
    public JsonResultObject saveInitiativeTender(String usrCustId) throws Exception;
    
    /**
     * 债权转让
     * @param  membidLogs 投标记录(债权)信息
     * @param  member 购买者信息
     * @return
     * @throws Exception
     */
    public JsonResultObject saveAutoCreditAssign(MemBidLogs membidLogs,Members member) throws Exception;
}
