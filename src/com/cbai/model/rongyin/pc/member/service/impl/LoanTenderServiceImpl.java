package com.cbai.model.rongyin.pc.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.json.JsonUtils;

import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.PropertyUtils;

import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.payment.huifu.vo.BorrowerDetails;
import com.cbai.model.rongyin.pc.member.service.LoanTenderService;
import com.cbai.model.rongyin.pc.web.vo.LoanFrontVo;

public class LoanTenderServiceImpl implements LoanTenderService{
	
	private IbatisBaseDao baseDao;  
	
	private PropertyUtils propertyUtils;
	
	@Override
	public Map<String,String> buyInitiativeTender(Integer memid, String usrCustId, Integer lnid,  String transAmt) throws Exception {
		
		//投标订单ID
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
    	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
    	
    	String maxTenderRate = "0.50"; //最大投资手续费率
    	Map<String,Object> param = new HashMap<String, Object>();
    	param.put("lnid", lnid);
    	
    	LoanFrontVo loanFrontVo = baseDao.queryObject("loan_front.investInfo", param);
    	//判断标的是否存在
    	if(loanFrontVo==null){
    		return null;
    	}
    	
    	//投资金额是否大于标的可投金额
    	if(Long.valueOf(AmountUtils.changeY2F(transAmt)) > loanFrontVo.getAvalmoney()){
    		return null;
    	}
    	
    	//投资金额是否大于用户可支配金额
    	Long avlmoney = baseDao.queryObject("pc_mem_account.getAvlmoney", memid);
		if(Long.valueOf(AmountUtils.changeY2F(transAmt)) > avlmoney){
			return null;
		}
    	
    	List<BorrowerDetails> borrowerDetailsList = new ArrayList<BorrowerDetails>();
    	borrowerDetailsList.add(borrowerDetailsList(AmountUtils.changeF2Y(AmountUtils.changeY2F(transAmt)), loanFrontVo));
        
    	//转换成接口能接受的借款人信息Json串
    	String borrowerDetails = JsonUtils.obj2json(borrowerDetailsList);
    	String isFreeze = "Y";
    	
    	String freezeOrdId = BusinessNumberUtil.gainOutBizNoNumber();
    	String bgRetUrl = propertyUtils.getPropertiesString("InitiativeTender_BgRetUrl");
    	
    	Map<String,String> params = RequestParemters.initiativeTenderParams(ordId, ordDate, 
    			AmountUtils.changeF2Y(AmountUtils.changeY2F(transAmt)), usrCustId, maxTenderRate, borrowerDetails, isFreeze, freezeOrdId, bgRetUrl);
    	
    	
    	/////////////////////////////////////////////
		//生成投资记录，状态为未处理
    	MemBidLogs memBidLogs = new MemBidLogs();
    	memBidLogs.setBidordernum(ordId);  //平台生成的业务编号
    	memBidLogs.setBidorderdate(ordDate);
    	memBidLogs.setFreezeordernum(freezeOrdId);  //平台生成冻结的业务编号
    	
        memBidLogs.setLnid(loanFrontVo.getLnid());
        memBidLogs.setLnnum(loanFrontVo.getLnnum());//标的编号
        memBidLogs.setLusrcustid(loanFrontVo.getLusrcustid());
        
        memBidLogs.setPoid(loanFrontVo.getProid()); //房源ID
        //memBidLogs.setPordernum(loanFrontVo.getp);//房源编号
        
        memBidLogs.setMemid(memid);
        memBidLogs.setUsrcustid(usrCustId);
        
        memBidLogs.setBidmoney(Long.valueOf(AmountUtils.changeY2F(transAmt)));
        memBidLogs.setRepaymoney(0L);
        memBidLogs.setDebtmoney(0L);
        
        
        String lilv = AmountUtils.yearRate(loanFrontVo.getYearate());
        String yuqiShouyi = AmountUtils.calIncome(loanFrontVo.getLoandead(), lilv, transAmt);
    	//预计收益
        memBidLogs.setProfit(Long.valueOf( AmountUtils.changeY2F(yuqiShouyi) ));
        
        memBidLogs.setState(State.BIDDING);
        memBidLogs.setBidtime(new Date());
        //生成投标记录,等待后续处理结果进行处理
        baseDao.insert("mem_bid_logs.insert", memBidLogs);

    	return params;
	}
    
	
	/**
	 * 每一笔借款资金对应的标的借款人信息
	 * @param transAmt
	 * @return
	 * @throws Exception
	 */
	private BorrowerDetails borrowerDetailsList(String transAmt, LoanFrontVo loanFrontVo) throws Exception{
    	BorrowerDetails borrowerInfo = new BorrowerDetails();
    	borrowerInfo.setBorrowerAmt(transAmt); //借款金额

    	borrowerInfo.setBorrowerCustId(loanFrontVo.getLusrcustid()); //借款人客户号
    	borrowerInfo.setBorrowerRate("0.60"); //借款手续费率,用于限制还款时的还款金额
    	borrowerInfo.setProId(loanFrontVo.getLnnum()); //标的的唯一性标识
    	
    	return borrowerInfo;
	}
	
	
	@Autowired
	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
}
