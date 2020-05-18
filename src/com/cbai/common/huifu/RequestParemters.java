package com.cbai.common.huifu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cbai.common.json.JsonUtils;
import com.cbai.common.utils.MD5Util;
import com.cbai.model.rongyin.payment.huifu.vo.DivDetails;

public class RequestParemters {
	
	public static final String version_10 = "10";
	
	public static final String version_20 = "20";
	
	public static final String version_30 = "30";
	
	public static final String merCustId = "6000060000484195";
	
	public static final String merCustId_ChildAcct = "MDT000001";
	
	
	/**
	 * 生利宝交易接口参数
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> fssTransParams(String usrCustId,String ordId,String ordDate, String bgRetUrl) throws Exception{
		
		String cmdId = "FssTrans";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId);
		params.put("OrdId", ordId);//订单编号
		params.put("OrdDate", ordDate);//订单日期
		params.put("BgRetUrl", bgRetUrl);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
		StringUtils.trimToEmpty(cmdId)).append(
		StringUtils.trimToEmpty(merCustId)).append(
		StringUtils.trimToEmpty(usrCustId)).append(
		StringUtils.trimToEmpty(ordId)).append(
		StringUtils.trimToEmpty(ordDate)).append(
		StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	
	/**
	 * 生利宝产品信息查询接口
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> queryFssBgParams() throws Exception{
		
        String cmdId = "QueryFss";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
		StringUtils.trimToEmpty(cmdId)).append(
		StringUtils.trimToEmpty(merCustId));
		
		String plainStr = buffer.toString();
		
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 生利宝账户信息查询接口
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> queryFssAcctsBgParams(String usrCustId) throws Exception{
		
		String cmdId = "QueryFssAccts";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
		StringUtils.trimToEmpty(cmdId)).append(
		StringUtils.trimToEmpty(merCustId)).append(
		StringUtils.trimToEmpty(usrCustId));
		
		String plainStr = buffer.toString();
		
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
    
	
	/**
	 * 用户逐个还款接口参数
	 * @param usrCustId
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> repaymentParams(String proId, String ordId, String ordDate,String outCustId,
		String subOrdId,String subOrdDate,String principalAmt,String interestAmt, String fee,
		String inCustId,String bgRetUrl,String lrpdid)throws Exception{
		
		String cmdId = "Repayment";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_30);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
	
		params.put("ProId", proId);//标的编号
		params.put("OrdId", ordId);//订单编号
		params.put("OrdDate", ordDate);//订单日期
		params.put("OutCustId", outCustId);//出账客户号
		
		params.put("SubOrdId", subOrdId);//投标订单号
		params.put("SubOrdDate", subOrdDate);//投标订单日期
		
		params.put("PrincipalAmt", principalAmt);//本次还款本金
		params.put("InterestAmt", interestAmt);//本次还利息
		params.put("Fee", fee);//投标订单日期
		
		params.put("InCustId", inCustId);//入账客户号
		params.put("BgRetUrl", bgRetUrl);//投标订单日期
		params.put("MerPriv", lrpdid);//投标订单日期
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_30)).append(
		StringUtils.trimToEmpty(cmdId)).append(
		StringUtils.trimToEmpty(merCustId)).append(
		StringUtils.trimToEmpty(proId)).append(
		StringUtils.trimToEmpty(ordId)).append(
		StringUtils.trimToEmpty(ordDate)).append(
		StringUtils.trimToEmpty(outCustId)).append(
		StringUtils.trimToEmpty(subOrdId)).append(
		StringUtils.trimToEmpty(subOrdDate)).append(
		StringUtils.trimToEmpty(principalAmt)).append(
		StringUtils.trimToEmpty(interestAmt)).append(
		StringUtils.trimToEmpty(fee)).append(
		StringUtils.trimToEmpty(inCustId)).append(
		StringUtils.trimToEmpty(bgRetUrl)).append(
		StringUtils.trimToEmpty(lrpdid));
		
		String plainStr = buffer.toString();
		
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(MD5Util.getMD5_32(plainStr)));
		return params;
	}
	
	
	/**
	 * 用户提现接口参数
	 * @param usrCustId
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> cashParams(String ordId, String usrCustId, String transAmt, String reqExt, String bgRetUrl)throws Exception{
		String cmdId = "Cash";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		
		params.put("OrdId", ordId);//订单编号
		params.put("UsrCustId", usrCustId);//用户客户号
		params.put("TransAmt", transAmt);//交易金额
		
		params.put("BgRetUrl", bgRetUrl);//后台处理地址
		params.put("ReqExt", reqExt);//不同提现方式不同的扣费端
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_20)).append(
		    StringUtils.trimToEmpty(cmdId)).append(
		    StringUtils.trimToEmpty(merCustId)).append(
		    StringUtils.trimToEmpty(ordId)).append(
		    StringUtils.trimToEmpty(usrCustId)).append(
		    StringUtils.trimToEmpty(transAmt)).append(
		    StringUtils.trimToEmpty(bgRetUrl)).append(
			StringUtils.trimToEmpty(reqExt));
		
		String plainStr = buffer.toString();
		
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 批量还款请求接口参数
	 * @param outCustId 出账客户号
	 * @param batchId 还款批次号
	 * @param merOrdDate
	 * @param bgRetUrl
	 * @param proId
	 * @param inDetails
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> batchRepaymentParams(String outCustId, String batchId, String merOrdDate, String bgRetUrl, String inDetails)throws Exception{
		
		String cmdId = "BatchRepayment";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		
		params.put("OutCustId", outCustId); //出账客户号
		params.put("BatchId", batchId);     //还款批次号
		params.put("MerOrdDate", merOrdDate);  //批量还款订单日期
		
		params.put("BgRetUrl", bgRetUrl);   
		//params.put("ProId", proId);   
		params.put("InDetails", inDetails);  //还款账户串

		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_20)).append(
			StringUtils.trimToEmpty(cmdId)).append(
			StringUtils.trimToEmpty(merCustId)).append(
			StringUtils.trimToEmpty(outCustId)).append(	
			StringUtils.trimToEmpty(batchId)).append(	
			StringUtils.trimToEmpty(merOrdDate)).append(	
			StringUtils.trimToEmpty(bgRetUrl)).append(	
			//StringUtils.trimToEmpty(proId)).append(
			StringUtils.trimToEmpty(inDetails));

		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(MD5Util.getMD5_32(plainStr)));
		return params;
	}
	
	
	/**
	 * 用户绑定银行卡查询接口参数
	 * @param usrCustId
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> queryCardInfoParams(String usrCustId, String cardId)throws Exception{
		String cmdId = "QueryCardInfo";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId);//用户客户号
		params.put("CardId", cardId);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
		StringUtils.trimToEmpty(cmdId)).append(
		StringUtils.trimToEmpty(merCustId)).append(
		StringUtils.trimToEmpty(usrCustId)).append(
		StringUtils.trimToEmpty(cardId));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 债权转让接口参数
	 * @param sellCustId
	 * @param creditAmt
	 * @param creditDealAmt
	 * @param bidDetails
	 * @param buyCustId
	 * @param ordId
	 * @param ordDate
	 * @param bgRetUrl
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> autoCreditAssignParams(String sellCustId,String creditAmt, String creditDealAmt, 
			String bidDetails,String buyCustId, String ordId, String ordDate, String bgRetUrl)throws Exception{
		
		String cmdId = "AutoCreditAssign";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_30);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("SellCustId", sellCustId);  //债权转让转让人客户号(投标记录中的投标人)
		params.put("CreditAmt", creditAmt);    //债权转让转出的本金
		params.put("CreditDealAmt", creditDealAmt);  //债权转让承接人付给转让人的金额
		params.put("BidDetails", bidDetails);  //债权转让明细Json串
		params.put("Fee", "0.00"); 
		params.put("BuyCustId", buyCustId); //债权转让承接人客户号
		params.put("OrdId", ordId);         //本次订单号
		params.put("OrdDate", ordDate);     //订单日期
		params.put("BgRetUrl", bgRetUrl);   //异步通知地址
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				StringUtils.trimToEmpty(version_30)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(	
				StringUtils.trimToEmpty(sellCustId)).append(
				StringUtils.trimToEmpty(creditAmt)).append(
				StringUtils.trimToEmpty(creditDealAmt)).append(
				StringUtils.trimToEmpty(bidDetails)).append(
				StringUtils.trimToEmpty("0.00")).append(
				StringUtils.trimToEmpty(buyCustId)).append(
				StringUtils.trimToEmpty(ordId)).append(
				StringUtils.trimToEmpty(ordDate)).append(
				StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(MD5Util.getMD5_32(plainStr)));
		return params;
	}
	
	/**
	 * 执行定向转账请求参数
	 * @param ordId
	 * @param usrCustId
	 * @param inUsrCustId
	 * @param transAmt
	 * @param bgRetUrl
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> direcTrfParams(String ordId, String usrCustId, String inUsrCustId, String transAmt, String bgRetUrl)throws Exception{
		String cmdId = "DirecTrf";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("OrdId", ordId);      //借款人ID
		params.put("UsrCustId", usrCustId);      //用户客户号
		params.put("InUsrCustId", inUsrCustId);
		params.put("TransAmt", transAmt);
		params.put("BgRetUrl", bgRetUrl);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
		StringUtils.trimToEmpty(cmdId)).append(
		StringUtils.trimToEmpty(merCustId)).append(
		StringUtils.trimToEmpty(ordId)).append(
		StringUtils.trimToEmpty(usrCustId)).append(
		StringUtils.trimToEmpty(inUsrCustId)).append(
		StringUtils.trimToEmpty(transAmt)).append(
		StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 定向转账授权查询接口参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> queryDirecTrfAuthParams(String usrCustId)throws Exception{
		String cmdId = "QueryDirecTrfAuth";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId);      //借款人ID

		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(usrCustId));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 定向转账授权接口参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> direcTrfAuthParams(String usrCustId, String inUsrCustId, String authAmt)throws Exception{
		
		String cmdId = "DirecTrfAuth";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		
		params.put("UsrCustId", usrCustId);      //借款人ID
		params.put("InUsrCustId", inUsrCustId);  //被入账人ID
		params.put("AuthAmt", authAmt); //授权金额

		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
				StringUtils.trimToEmpty(inUsrCustId)).append(
				StringUtils.trimToEmpty(authAmt));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
    
	
	/**
	 * 放款请求参数,有手续费即：free > 0.00
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> loansParams(String ordId, String ordDate,String outCustId, String transAmt, String free,
			String subOrdId,String subOrdDate,String inCustId,String unFreezeOrdId,String freezeTrxId, String bgRetUrl,String reqExt)throws Exception{
		
		String cmdId = "Loans";
		
		List<DivDetails> divDetailsList = new ArrayList<DivDetails>();
        DivDetails divDetails = new DivDetails();
        divDetails.setDivCustId(merCustId);
        divDetails.setDivAcctId(merCustId_ChildAcct);
        divDetails.setDivAmt(free);
        divDetailsList.add(divDetails);
        
        String divDetailsStr = JsonUtils.obj2json(divDetailsList);
        
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);  
		params.put("CmdId", cmdId);  
		params.put("MerCustId", merCustId); 
		params.put("OrdId", ordId);
		params.put("OrdDate", ordDate);
		params.put("OutCustId", outCustId);   //出账客户号，由汇付生成，用户的唯一性标识
		params.put("TransAmt", transAmt);
		params.put("Fee", free);  //Fee 字段填 0.00， DivDetails 字段请填空
		params.put("SubOrdId", subOrdId);  //投标订单号
		params.put("SubOrdDate", subOrdDate); //投标日期
		params.put("InCustId", inCustId); //借款人ID
		params.put("DivDetails", divDetailsStr); //向入款客户号 InCustId 收取
		params.put("FeeObjFlag", "I"); //向入款客户号 InCustId 收取
		params.put("IsDefault", "N"); //放款后资金流向:是否默认
		params.put("IsUnFreeze", "Y"); //
		params.put("UnFreezeOrdId",unFreezeOrdId); //新生成的解冻ID
		params.put("FreezeTrxId",freezeTrxId); //投标的时候返回的:8 位本平台日期+10 位系统流水号 
		params.put("BgRetUrl",bgRetUrl);
		params.put("ReqExt",reqExt);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_20)).append(
			StringUtils.trimToEmpty(cmdId)).append(
			StringUtils.trimToEmpty(merCustId)).append(
            StringUtils.trimToEmpty(ordId)).append(
            StringUtils.trimToEmpty(ordDate)).append(
            StringUtils.trimToEmpty(outCustId)).append(
            StringUtils.trimToEmpty(transAmt)).append(
            StringUtils.trimToEmpty(free)).append(
            StringUtils.trimToEmpty(subOrdId)).append(
            StringUtils.trimToEmpty(subOrdDate)).append(
			StringUtils.trimToEmpty(inCustId)).append(
			StringUtils.trimToEmpty(divDetailsStr)).append(
			StringUtils.trimToEmpty("I")).append(
			StringUtils.trimToEmpty("N")).append(
			StringUtils.trimToEmpty("Y")).append(
			StringUtils.trimToEmpty(unFreezeOrdId)).append(
			StringUtils.trimToEmpty(freezeTrxId)).append(
			StringUtils.trimToEmpty(bgRetUrl)).append(
			StringUtils.trimToEmpty(reqExt));
        
		String plainStr = buffer.toString();

		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	/**
	 * 放款请求参数,没有手续费即：free == 0.00
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> loansParamsNoFee(String ordId, String ordDate,String outCustId, String transAmt, String free,
			String subOrdId,String subOrdDate,String inCustId,String unFreezeOrdId,String freezeTrxId, String bgRetUrl,String reqExt)throws Exception{
		
		String cmdId = "Loans";
        
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);  
		params.put("CmdId", cmdId);  
		params.put("MerCustId", merCustId); 
		params.put("OrdId", ordId);
		params.put("OrdDate", ordDate);
		params.put("OutCustId", outCustId);   //出账客户号，由汇付生成，用户的唯一性标识
		params.put("TransAmt", transAmt);
		params.put("Fee", free);  //Fee 字段填 0.00， DivDetails 字段请填空
		params.put("SubOrdId", subOrdId);  //投标订单号
		params.put("SubOrdDate", subOrdDate); //投标日期
		params.put("InCustId", inCustId); //借款人ID
		params.put("IsDefault", "N"); //放款后资金流向:是否默认
		params.put("IsUnFreeze", "Y"); //
		params.put("UnFreezeOrdId",unFreezeOrdId); //新生成的解冻ID
		params.put("FreezeTrxId",freezeTrxId); //投标的时候返回的:8 位本平台日期+10 位系统流水号 
		params.put("BgRetUrl",bgRetUrl);
		params.put("ReqExt",reqExt);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_20)).append(
			StringUtils.trimToEmpty(cmdId)).append(
			StringUtils.trimToEmpty(merCustId)).append(
            StringUtils.trimToEmpty(ordId)).append(
            StringUtils.trimToEmpty(ordDate)).append(
            StringUtils.trimToEmpty(outCustId)).append(
            StringUtils.trimToEmpty(transAmt)).append(
            StringUtils.trimToEmpty(free)).append(
            StringUtils.trimToEmpty(subOrdId)).append(
            StringUtils.trimToEmpty(subOrdDate)).append(
			StringUtils.trimToEmpty(inCustId)).append(
			StringUtils.trimToEmpty("N")).append(
			StringUtils.trimToEmpty("Y")).append(
			StringUtils.trimToEmpty(unFreezeOrdId)).append(
			StringUtils.trimToEmpty(freezeTrxId)).append(
			StringUtils.trimToEmpty(bgRetUrl)).append(
			StringUtils.trimToEmpty(reqExt));
        
		String plainStr = buffer.toString();

		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 投标撤销接口请求参数(主动)
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> tenderCancleParams(String ordId,String ordDate,String transAmt,String usrCustId,String isUnFreeze,
			String unFreezeOrdId, String bgRetUrl)throws Exception{
		
		String cmdId = "TenderCancle";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		
		params.put("OrdId", ordId); //投标时的订单号
		params.put("OrdDate", ordDate);
		params.put("TransAmt", transAmt);
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		params.put("IsUnFreeze", isUnFreeze);
		params.put("UnFreezeOrdId", unFreezeOrdId);
		params.put("BgRetUrl", bgRetUrl);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_20)).append(
		        StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(ordId)).append(
				StringUtils.trimToEmpty(ordDate)).append(
				StringUtils.trimToEmpty(transAmt)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
				StringUtils.trimToEmpty(isUnFreeze)).append(
				StringUtils.trimToEmpty(unFreezeOrdId)).append(
				StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 投标撤销接口请求参数(后台)
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> autoTenderCancleParams(String ordId,String ordDate,String transAmt,String usrCustId,String isUnFreeze, String unFreezeOrdId, String freezeTrxId, String bgRetUrl)throws Exception{
		
		String cmdId = "AutoTenderCancle";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_30);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("OrdId", ordId); //投标时的订单号
		params.put("OrdDate", ordDate);
		params.put("TransAmt", transAmt);
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		params.put("IsUnFreeze", isUnFreeze);
		params.put("UnFreezeOrdId", unFreezeOrdId);
		params.put("FreezeTrxId", freezeTrxId);
		params.put("BgRetUrl", bgRetUrl);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_30)).append(
		        StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(ordId)).append(
				StringUtils.trimToEmpty(ordDate)).append(
				StringUtils.trimToEmpty(transAmt)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
				StringUtils.trimToEmpty(isUnFreeze)).append(
				StringUtils.trimToEmpty(unFreezeOrdId)).append(
				StringUtils.trimToEmpty(freezeTrxId)).append(
				StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(MD5Util.getMD5_32(plainStr)));
		return params;
	}

	
	/**
	 * 主动投标请求参数
	 * 
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> initiativeTenderParams(String ordId,String ordDate,String transAmt, String usrCustId,
			String maxTenderRate, String borrowerDetails, String isFreeze, String freezeOrdId,String bgRetUrl)throws Exception{
		
		String cmdId = "InitiativeTender";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("OrdId", ordId); //订单号
		params.put("OrdDate", ordDate); //订单日期 格式为 YYYYMMDD
		params.put("TransAmt", transAmt); //交易金额
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		
		params.put("MaxTenderRate", maxTenderRate); //最大投资手续费率 (放款接口的手续费fee不能超过最大投资手续费率（MaxTenderRate）乘以投资金额)
		params.put("BorrowerDetails", borrowerDetails); //支持传送多个借款人信息，使用json数组 格式传送;
		params.put("IsFreeze",isFreeze);
		params.put("FreezeOrdId", freezeOrdId);
		params.put("BgRetUrl", bgRetUrl);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_20)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(ordId)).append(
				StringUtils.trimToEmpty(ordDate)).append(
				StringUtils.trimToEmpty(transAmt)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
				StringUtils.trimToEmpty(maxTenderRate)).append(
				StringUtils.trimToEmpty(borrowerDetails)).append(
				StringUtils.trimToEmpty(isFreeze)).append(
				StringUtils.trimToEmpty(freezeOrdId)).append(
				StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 自动投标请求参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> autoTenderParams(String ordId,String ordDate,String transAmt, String usrCustId,
			String maxTenderRate, String borrowerDetails, String isFreeze, String freezeOrdId,String bgRetUrl)throws Exception{
		
		String cmdId = "AutoTender";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("OrdId", ordId); //订单号
		params.put("OrdDate", ordDate); //订单日期 格式为 YYYYMMDD
		params.put("TransAmt", transAmt); //交易金额
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		params.put("MaxTenderRate", maxTenderRate); //最大投资手续费率 (放款接口的手续费fee不能超过最大投资手续费率（MaxTenderRate）乘以投资金额)
		
		params.put("BorrowerDetails", borrowerDetails); //支持传送多个借款人信息，使用json数组 格式传送;
		params.put("IsFreeze",isFreeze);
		params.put("FreezeOrdId", freezeOrdId);
		params.put("BgRetUrl", bgRetUrl);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_20)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(ordId)).append(
				StringUtils.trimToEmpty(ordDate)).append(
				StringUtils.trimToEmpty(transAmt)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
				StringUtils.trimToEmpty(maxTenderRate)).append(
				StringUtils.trimToEmpty(borrowerDetails)).append(
				StringUtils.trimToEmpty(isFreeze)).append(
				StringUtils.trimToEmpty(freezeOrdId)).append(
				StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 查询用户是否开启自动投资请求参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> queryTenderPlanParams(String usrCustId)throws Exception{
		String cmdId = "QueryTenderPlan";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId);  //用户客户号,由汇付生成，用户的唯一性标识
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(usrCustId));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 关闭自动投资请求参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> autoTenderPlanCloseParams(String usrCustId, String retUrl)throws Exception{
		String cmdId = "AutoTenderPlanClose";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		params.put("RetUrl", retUrl); //用户客户号,由汇付生成，用户的唯一性标识
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
				StringUtils.trimToEmpty(retUrl));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 开启自动投资请求参数
	 * @param usrCustId
	 * @param tenderPlanType
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> autoTenderPlanParams(String usrCustId,String tenderPlanType,String transAmt,String retUrl)throws Exception{
		String cmdId = "AutoTenderPlan";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		params.put("TenderPlanType", tenderPlanType); //投标计划类型,P--部分授权;W--完全授权
		params.put("RetUrl", retUrl); //投标计划类型,P--部分授权;W--完全授权

		if("P".equals(tenderPlanType)){
			params.put("TransAmt", transAmt);
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
		        StringUtils.trimToEmpty(tenderPlanType)).append(
				StringUtils.trimToEmpty(retUrl));
		
		if("P".equals(tenderPlanType)){
			buffer.append(StringUtils.trimToEmpty(transAmt));
		}
		
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 用户充值参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> netSaveParams(String usrCustId, String ordId, String ordDate, String transAmt, String bgRetUrl)throws Exception{
		
		String cmdId = "NetSave";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		
		params.put("OrdId", ordId);  //变长 30 位的由商户的系统生成，必须保证唯一，请使用纯数字
		params.put("OrdDate", ordDate);  //
		params.put("TransAmt", transAmt);  //
		params.put("BgRetUrl", bgRetUrl);  //通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID 字样的字符串
        
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10))
		      .append(StringUtils.trimToEmpty(cmdId))
		      .append(StringUtils.trimToEmpty(merCustId))
		      .append(StringUtils.trimToEmpty(usrCustId))
		      .append(StringUtils.trimToEmpty(ordId))
		      .append(StringUtils.trimToEmpty(ordDate))
		      .append(StringUtils.trimToEmpty(transAmt))
		      .append(StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		//System.out.println("加密串:" + plainStr);
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		
		return params;
	}
	
	
	/**
	 * 用户余额参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> queryBalanceBgParams(String usrCustId)throws Exception{
		String cmdId = "QueryBalanceBg";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(usrCustId));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
	/**
	 * 用户登录参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> userLoginParams(String usrCustId)throws Exception{
		String cmdId = "UserLogin";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId); //用户客户号,由汇付生成，用户的唯一性标识
		
		return params;
	}
	
	/**
	 * 用户绑定银行卡参数
	 * @param usrCustId
	 * @param bgRetUrl
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> userBindCardParams(String usrCustId,String UsrId, String bgRetUrl)throws Exception{
		
		String cmdId = "UserBindCard";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrCustId", usrCustId);  
		params.put("BgRetUrl", bgRetUrl);
		params.put("MerPriv", UsrId);  
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(usrCustId)).append(
			    StringUtils.trimToEmpty(bgRetUrl)).append(
				StringUtils.trimToEmpty(UsrId));
		
		String plainStr = buffer.toString();
		
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		
		return params;
	}
	
	/**
	 * 标的录入参数
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> addBidInfoParams(String proId,String bidName, String bidType,String borrTotAmt,String yearRate,String retInterest,String lastRetDate,
			String bidStartDate,String bidEndDate,String loanPeriod,String retType,String retDate,String bidProdType,String borrType,String borrCustId,String borrBusiCode,
			String borrCertType,String borrCertId,String borrName,String borrMobiPhone,String borrPurpose,String charSet,String bgRetUrl) throws Exception{
		
		String cmdId = "AddBidInfo";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_20);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("ProId", proId);
		params.put("BidName", bidName);
		params.put("BidType", bidType);
		params.put("BorrTotAmt", borrTotAmt);
		params.put("YearRate", yearRate);
		params.put("RetInterest", retInterest);
		params.put("LastRetDate", lastRetDate);
		params.put("BidStartDate", bidStartDate);
		params.put("BidEndDate", bidEndDate);
		params.put("LoanPeriod", loanPeriod);
		params.put("RetType", retType);
		params.put("RetDate", retDate);
		params.put("BidProdType", bidProdType);
		params.put("BorrType", borrType);
		params.put("BorrCustId", borrCustId);
		params.put("BorrName", borrName);  //必须传但是不签名
		
		if("01".equals(borrType)){
			params.put("BorrCertType",borrCertType);
			params.put("BorrCertId",borrCertId);
		}else if("02".equals(borrType)){
			params.put("BorrBusiCode",borrBusiCode);
		}
		
		params.put("BorrMobiPhone", borrMobiPhone);
		params.put("BorrPurpose", borrPurpose);  //需要但是不签名
		params.put("CharSet", charSet);
		params.put("BgRetUrl", bgRetUrl);	
		
		
		//组装数据做签名
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				StringUtils.trimToEmpty(version_20)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(	
				StringUtils.trimToEmpty(proId)).append(
				//StringUtils.trimToEmpty(bidName)).append(
				StringUtils.trimToEmpty(bidType)).append(
						
				StringUtils.trimToEmpty(borrTotAmt)).append(
				StringUtils.trimToEmpty(yearRate)).append(
				StringUtils.trimToEmpty(retInterest)).append(
				StringUtils.trimToEmpty(lastRetDate)).append(
						
				StringUtils.trimToEmpty(bidStartDate)).append(
				StringUtils.trimToEmpty(bidEndDate)).append(
				StringUtils.trimToEmpty(retType)).append(
		        StringUtils.trimToEmpty(retDate)).append(
		        		
		        StringUtils.trimToEmpty(bidProdType)).append(
				StringUtils.trimToEmpty(borrType)).append(
				StringUtils.trimToEmpty(borrCustId));
		
		if("01".equals(borrType)){
		    buffer.append(StringUtils.trimToEmpty(borrCertType)).append(
				StringUtils.trimToEmpty(borrCertId));
		}else if("02".equals(borrType)){
		    buffer.append(StringUtils.trimToEmpty(borrBusiCode));
		}
				
		buffer.append(StringUtils.trimToEmpty(borrMobiPhone)).append(
				StringUtils.trimToEmpty(charSet)).append(
				StringUtils.trimToEmpty(bgRetUrl));
		
		String plainStr = buffer.toString();
		
		//先对此明文串做md5加密,再将md5加密后的密文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(MD5Util.getMD5_32(plainStr)));
		
		return params;
	}


	/**
	 * 用户注册参数
	 * 
	 * @param bgRetUrl 商户后台应答地址
	 * @param retUrl 页面返回 URL
	 * @param usrId 用户号
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> userRegisterParams(String bgRetUrl, String retUrl, String usrId) throws Exception {
		
		String cmdId = "UserRegister";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("BgRetUrl", bgRetUrl);
		//params.put("RetUrl", retUrl);
		params.put("UsrId", usrId);
		
		params.put("MerPriv", usrId);

		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(bgRetUrl)).append(
				//StringUtils.trimToEmpty(retUrl)).append(
				StringUtils.trimToEmpty(usrId)).append(
				StringUtils.trimToEmpty(usrId)); //MerPriv值原样传递给汇付

		String plainStr = buffer.toString();

		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));

		return params;
	}
	
	
	/**
	 * 企业开户参数
	 * 
	 * @param bgRetUrl 商户后台应答地址
	 * @param retUrl 页面返回 URL
	 * @param usrId 用户号
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> companyRegisterParams(String bgRetUrl,String UsrId, String busiCode,String guarType) throws Exception {
		
		String cmdId = "CorpRegister";
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("UsrId", UsrId);
		params.put("BusiCode", busiCode);
		params.put("MerPriv", UsrId);
		params.put("GuarType", guarType);
		params.put("BgRetUrl", bgRetUrl); 
		
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(UsrId)).append(
				StringUtils.trimToEmpty(busiCode)).append(
			    StringUtils.trimToEmpty(UsrId)).append(
			    StringUtils.trimToEmpty(guarType)).append(
				StringUtils.trimToEmpty(bgRetUrl)); 
		
		params.put("ChkValue", SignUtils.encryptByRSA(buffer.toString()));
		return params;
	}
	
	/**
	 * 用户信息查询接口参数
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> queryUserInfoParams(String idnum)throws Exception{
		String cmdId = "QueryUsrInfo";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", version_10);
		params.put("CmdId", cmdId);
		params.put("MerCustId", merCustId);
		params.put("CertId", idnum);      //借款人ID

		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version_10)).append(
				StringUtils.trimToEmpty(cmdId)).append(
				StringUtils.trimToEmpty(merCustId)).append(
				StringUtils.trimToEmpty(idnum));
		
		String plainStr = buffer.toString();
		//先对此明文做汇付的RSA加签
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		return params;
	}
	
	
}