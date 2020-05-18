package com.cbai.model.rongyin.sys.loan.service.impl;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cbai.common.huifu.HttpClientUtil;
import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.huifu.SignUtils;
import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.JsonUtil;
import com.cbai.common.utils.MD5Util;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.model.common.data.State;

import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.Members;
import com.cbai.model.rongyin.ibatis.entity.ProductOrders;
import com.cbai.model.rongyin.payment.huifu.vo.BidDetails;
import com.cbai.model.rongyin.payment.huifu.vo.BidDetailsListBorrowerDetailsVo;
import com.cbai.model.rongyin.payment.huifu.vo.BidDetailsListVo;
import com.cbai.model.rongyin.payment.huifu.vo.BorrowerDetails;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoCreditAssignBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuInitiativeTenderBackVo;

import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoTenderBackVo;
import com.cbai.model.rongyin.sys.loan.service.LoanTenderService;

public class LoanTenderServiceImpl implements LoanTenderService{
	
	private static final Logger log = LoggerFactory.getLogger(LoanTenderServiceImpl.class);
	
	private PropertyUtils propertyUtils;
	
	private HttpClientUtil httpClientUtil;
	
	private IbatisBaseDao baseDao;
	
	@Override
	public JsonResultObject saveAutoCreditAssign(MemBidLogs membidLogs,Members member) throws Exception {
		
		JsonResultObject result = new JsonResultObject();
		
		String transAmt = AmountUtils.changeF2Y(String.valueOf(membidLogs.getDebtmoney()));
		
		//构建bidDetails值
		List<BidDetailsListVo> bidDetailsList = new ArrayList<BidDetailsListVo>();
		BidDetailsListVo bidDetailsListVo = new BidDetailsListVo();
		bidDetailsListVo.setBidOrdId(membidLogs.getBidordernum());    //投标或者债权订单号
		bidDetailsListVo.setBidOrdDate(membidLogs.getBidorderdate()); //投标或者债权订单对应的日期
		bidDetailsListVo.setBidCreditAmt(transAmt); //本次转让的金额
		bidDetailsList.add(bidDetailsListVo);
		
		//构建BorrowerDetails值
		List<BidDetailsListBorrowerDetailsVo> borrowerDetailsList = new ArrayList<BidDetailsListBorrowerDetailsVo>();
		BidDetailsListBorrowerDetailsVo BorrowerDetailsVo = new BidDetailsListBorrowerDetailsVo();
		BorrowerDetailsVo.setBorrowerCreditAmt(transAmt);
		BorrowerDetailsVo.setBorrowerCustId(membidLogs.getLusrcustid()); //标的借款人信息
		BorrowerDetailsVo.setPrinAmt(AmountUtils.changeF2Y(String.valueOf(membidLogs.getRepaymoney()))); //这个已还本金是债权的已还本金, 都是前一轮债权的已还本金
		BorrowerDetailsVo.setProId(membidLogs.getLnnum());
		borrowerDetailsList.add(BorrowerDetailsVo);
		bidDetailsListVo.setBorrowerDetails(borrowerDetailsList);
		
		BidDetails bidDetails = new BidDetails();
		bidDetails.setBidDetails(bidDetailsList);
		
		//生成债权订单ID
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
    	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());	
    	String bgRetUrl = propertyUtils.getPropertiesString("AutoCreditAssign_BgRetUrl");
    	
		Map<String,String> params = RequestParemters.autoCreditAssignParams(
				membidLogs.getUsrcustid(), //债权转让转让人客户号(投标记录中的投标人)
				transAmt, 
				transAmt, 
				JsonUtils.obj2json(bidDetails), 
				member.getUsrcustid(),
				ordId, 
				ordDate, 
				bgRetUrl);
		
		//生成投资记录，状态为未处理
    	MemBidLogs creditAssign = new MemBidLogs();
    	creditAssign.setBidordernum(ordId);          //平台生成的业务编号
    	creditAssign.setBidorderdate(ordDate);

    	creditAssign.setDadid(membidLogs.getMblid());
    	creditAssign.setDadordernum(membidLogs.getBidordernum());
    	
    	creditAssign.setLnid(membidLogs.getLnid());
    	creditAssign.setLnnum(membidLogs.getLnnum());//标的编号
    	creditAssign.setLusrcustid(membidLogs.getLusrcustid());
        
    	creditAssign.setPoid(membidLogs.getPoid()); //产品订单ID
    	creditAssign.setPordernum(membidLogs.getPordernum());//产品订单业务编号
        
    	creditAssign.setMemid(member.getMemid());
    	creditAssign.setUsrcustid(member.getUsrcustid());
        
    	creditAssign.setBidmoney(Long.valueOf(AmountUtils.changeY2F(transAmt)));
    	creditAssign.setRepaymoney(0L);
    	creditAssign.setDebtmoney(0L);
        
    	creditAssign.setState(State.DOING);
    	creditAssign.setBidtime(new Date());
        
        //生成投标记录,等待后续处理结果进行处理
        baseDao.insert("mem_bid_logs.insert", creditAssign);
        
		//向汇付发起标请求
    	log.debug("标的发布请求参数：{}", params);	
		String huifuResult = httpClientUtil.doPost(params);
        log.debug("接口请求返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
		
			HuifuAutoCreditAssignBackVo huifuAutoCreditAssignBackVo = JsonUtil.fromJson(huifuResult, HuifuAutoCreditAssignBackVo.class);
			
			if(huifuAutoCreditAssignBackVo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getCmdId())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getRespCode())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getMerCustId())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getSellCustId())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getCreditAmt())).append(
				        StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getCreditDealAmt())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getFee())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getBuyCustId())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getOrdId())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getOrdDate())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getRetUrl())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getBgRetUrl())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getMerPriv())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getRespExt())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getLcId())).append(
						StringUtils.trimToEmpty(huifuAutoCreditAssignBackVo.getTotalLcAmt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(MD5Util.getMD5_32(plainStr), huifuAutoCreditAssignBackVo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK) {
					if("000".equals(huifuAutoCreditAssignBackVo.getRespCode())){
						//自动投标完成
						log.debug("-------------债权转让完成,等待异步通知!----------------");
						
						
						result.setCode("1");
						result.setMessage("债权转让完成!");
						return result;
					}else{
						result.setCode("0");
						result.setMessage(URLDecoder.decode(huifuAutoCreditAssignBackVo.getRespDesc(),"UTF-8"));
						return result;
					}
					
				}else{
					result.setCode("0");
					result.setMessage("签名错误");
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("债权转让接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("债权转让接口返回数据异常!");
			return result;
		}
		
	}
	
	
	/**
	 * 自动投标
	 * 
	 * @param memid  购买者ID
	 * @param usrcustid  购买者汇付ID
	 * @param lnid  标的ID
	 * @param lnnum  标的汇付编号
	 * @param lusrcustid  借款人汇付ID
	 * 
	 * @param poid 产品订单ID
	 * @param pordernum 产品订单编号
	 * 
	 * @param transAmt  本次交易的具体金额
	 * @return
	 */
	@Override
	public JsonResultObject saveAutoTender(Members member,
			LoanInfo loanInfo, ProductOrders productOrder, String transAmt) throws Exception {
		
		JsonResultObject result = new JsonResultObject();
    	
		//投标订单ID
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
    	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());	
    	
    	//最大投资手续费率
    	String maxTenderRate = "0.00"; 
    	
    	List<BorrowerDetails>  borrowerDetailsList = new ArrayList<BorrowerDetails>();
    	BorrowerDetails borrowerDetails = new BorrowerDetails();
    	borrowerDetails.setBorrowerAmt(transAmt); //借款金额
    	borrowerDetails.setBorrowerCustId(loanInfo.getLusrcustid()); //借款人客户号
    	borrowerDetails.setBorrowerRate(maxTenderRate);  //借款手续费率
    	borrowerDetails.setProId(loanInfo.getLnnum());  //标的的唯一性标识
    	borrowerDetailsList.add(borrowerDetails);
    	
    	//转换成接口能接受的借款人信息Json串
    	String borrowerDetailsJsonStr = JsonUtils.obj2json(borrowerDetailsList);
    	
    	String isFreeze = "Y";
    	String freezeOrdId = BusinessNumberUtil.gainOutBizNoNumber();
    	String bgRetUrl = propertyUtils.getPropertiesString("AutoTender_BgRetUrl");
    	
    	Map<String,String> params = RequestParemters.autoTenderParams(ordId, ordDate, transAmt, member.getUsrcustid(), maxTenderRate, borrowerDetailsJsonStr, isFreeze, freezeOrdId, bgRetUrl);
    	
    	//生成投资记录，状态为未处理
    	MemBidLogs memBidLogs = new MemBidLogs();
    	memBidLogs.setBidordernum(ordId);  //平台生成的业务编号
    	memBidLogs.setBidorderdate(ordDate);
    	memBidLogs.setFreezeordernum(freezeOrdId);  //平台生成冻结的业务编号

        memBidLogs.setLnid(loanInfo.getLnid());
        memBidLogs.setLnnum(borrowerDetails.getProId());//标的编号
        memBidLogs.setLusrcustid(loanInfo.getLusrcustid());
        
        memBidLogs.setPoid(productOrder.getPoid()); //产品订单ID
        memBidLogs.setPordernum(productOrder.getPordernum());//产品订单业务编号
        
        memBidLogs.setMemid(member.getMemid());
        memBidLogs.setUsrcustid(member.getUsrcustid());
        
        memBidLogs.setBidmoney(Long.valueOf(AmountUtils.changeY2F(transAmt)));
        memBidLogs.setRepaymoney(0L);
        memBidLogs.setDebtmoney(0L);
        
        memBidLogs.setState(State.BIDDING);
        memBidLogs.setBidtime(new Date());
        
        //生成投标记录,等待后续处理结果进行处理
        baseDao.insert("mem_bid_logs.insert", memBidLogs);
        
        //修改产品订单可投金额
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("poid", productOrder.getPoid());
        paramMap.put("transAmt", -Long.valueOf(AmountUtils.changeY2F(transAmt)));
        baseDao.update("product_orders.updateAvlmoney", paramMap);
        
        //修改标的可投金额 = 原有金额 - 本次投标金额
		Map<String,Object> loanMoneyParam = new HashMap<String, Object>();
		loanMoneyParam.put("transAmt",-Long.valueOf(AmountUtils.changeY2F(transAmt)));
		loanMoneyParam.put("lnid", memBidLogs.getLnid());
		baseDao.update("sys_loan.SYS-UPDATE-AVALMONEY", loanMoneyParam);
        
    	//向汇付发起标请求
    	log.debug("标的发布请求参数：{}", params);	
		String huifuResult = httpClientUtil.doPost(params);
        log.debug("接口请求返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
		
			HuifuAutoTenderBackVo huifuAutoTenderBackVo = JsonUtil.fromJson(huifuResult, HuifuAutoTenderBackVo.class);
			if(huifuAutoTenderBackVo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getCmdId())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getRespCode())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getMerCustId())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getOrdId())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getOrdDate())).append(
				        StringUtils.trimToEmpty(huifuAutoTenderBackVo.getTransAmt())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getUsrCustId())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getTrxId())).append(
				        
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getIsFreeze())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getFreezeOrdId())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getFreezeTrxId())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getRetUrl())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getBgRetUrl())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getMerPriv())).append(
						StringUtils.trimToEmpty(huifuAutoTenderBackVo.getResqExt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, huifuAutoTenderBackVo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(huifuAutoTenderBackVo.getRespCode())) {
					//自动投标完成
					log.debug("-------------自动投标完成,等待异步通知!----------------");
					result.setCode("1");
					result.setMessage("自动投标完成!");
					return result;
				}else{
					result.setCode("0");
					result.setMessage(URLDecoder.decode(huifuAutoTenderBackVo.getRespDesc(),"UTF-8"));
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("自动投标接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("自动投标接口返回数据异常!");
			return result;
		}
	}
	
	
	
	@Override
	public JsonResultObject saveInitiativeTender(String usrCustId) throws Exception {
		
		JsonResultObject result = new JsonResultObject();
    	
		//投标订单ID
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
    	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());	
    	String transAmt = "1000.00";
    	String maxTenderRate = "0.00"; //最大投资手续费率
    	
    	List<BorrowerDetails>  borrowerDetailsList = new ArrayList<BorrowerDetails>();
    	//borrowerDetailsList.add(borrowerDetailsList(transAmt));
    	//转换成接口能接受的借款人信息Json串
    	String borrowerDetails = JsonUtils.obj2json(borrowerDetailsList);
    	
    	String isFreeze = "Y";
    	String freezeOrdId = BusinessNumberUtil.gainOutBizNoNumber();
    	String bgRetUrl = propertyUtils.getPropertiesString("InitiativeTender_BgRetUrl");
	
    	Map<String,String> params = RequestParemters.initiativeTenderParams(ordId, ordDate, transAmt, usrCustId, maxTenderRate, borrowerDetails, isFreeze, freezeOrdId, bgRetUrl);
    	
    	log.debug("标的发布请求参数：{}", params);	
		//向汇付发起标请求
		String huifuResult = httpClientUtil.doPost(params);
        log.debug("接口请求返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
		
			HuifuInitiativeTenderBackVo huifuInitiativeTenderBackVo = JsonUtil.fromJson(huifuResult, HuifuInitiativeTenderBackVo.class);
			if(huifuInitiativeTenderBackVo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getCmdId())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getRespCode())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getMerCustId())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getOrdId())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getOrdDate())).append(
				        StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getTransAmt())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getUsrCustId())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getTrxId())).append(
				        
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getIsFreeze())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getFreezeOrdId())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getFreezeTrxId())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getRetUrl())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getBgRetUrl())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getMerPriv())).append(
						StringUtils.trimToEmpty(huifuInitiativeTenderBackVo.getResqExt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, huifuInitiativeTenderBackVo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(huifuInitiativeTenderBackVo.getRespCode())) {
					//自动投标完成
					log.debug("-------------自动投标完成!!!!!!!----------------");
					
					
					result.setCode("1");
					result.setMessage("自动投标完成!");
					return result;
				}else{
					result.setCode("0");
					result.setMessage(URLDecoder.decode(huifuInitiativeTenderBackVo.getRespDesc(),"UTF-8"));
					return result;
				}	
				
			}else{
				result.setCode("0");
				result.setMessage("自动投标接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("自动投标接口返回数据异常!");
			return result;
		}
	}
	
	@Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

	@Autowired
	public void setHttpClientUtil(HttpClientUtil httpClientUtil) {
		this.httpClientUtil = httpClientUtil;
	}

	@Autowired
	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
