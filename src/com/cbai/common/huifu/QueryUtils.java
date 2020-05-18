package com.cbai.common.huifu;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.utils.JsonUtil;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuQueryBalanceBgVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuQueryDirecTrfAuthBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuQueryFssAcctsBackVO;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuQueryFssBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuQueryTenderPlanBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuUserInfoBackVo;

/**
 * 汇付通用查询类
 * @author Administrator
 *
 */
public class QueryUtils {
	
	private static final Logger log = LoggerFactory.getLogger(QueryUtils.class);
	
	private HttpClientUtil httpClientUtil;
	
	public JsonResultObject queryCardInfo(String usrCustId, String cardId) throws Exception{
		
		log.debug("queryCardInfo method!");
		JsonResultObject result = new JsonResultObject();
		Map<String,String> params = RequestParemters.queryCardInfoParams(usrCustId, cardId);
		
		
		//向汇付发起请求信息
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("查询返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			
			/*HuifuQueryDirecTrfAuthBackVo backinfo = JsonUtil.fromJson(huifuResult, HuifuQueryDirecTrfAuthBackVo.class);
			if(backinfo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(backinfo.getCmdId())).append(
						StringUtils.trimToEmpty(backinfo.getRespCode())).append(
						StringUtils.trimToEmpty(backinfo.getMerCustId())).append(
						StringUtils.trimToEmpty(backinfo.getUsrCustId())).append(
						StringUtils.trimToEmpty(backinfo.getInUsrCustId())).append(
						StringUtils.trimToEmpty(backinfo.getAuthAmt())).append(
						StringUtils.trimToEmpty(backinfo.getIsAuth())).append(
				        StringUtils.trimToEmpty(backinfo.getRespExt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, backinfo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(backinfo.getRespCode())) {
					result.setCode("1");
					result.setMessage((backinfo.getAuthAmt()==null || "".equals(backinfo.getAuthAmt()))?"0.00": backinfo.getAuthAmt());
					result.setReturnObj(backinfo);
					return result;
				}else{
					result.setCode("0");
					result.setMessage(URLDecoder.decode(backinfo.getRespDesc(),"UTF-8"));
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("标的接口返回数据格式错误!");
				return result;
			}*/
			return null;
			
		}else{
			result.setCode("0");
			result.setMessage("余额查询接口返回数据格式错误!");
			return result;
		}
	}
	
	
	
	/**
	 * @param lusrcustid 标的借款人
	 * @return
	 * @throws Exception
	 */
	public JsonResultObject queryDirecTrfAuth(String lusrcustid) throws Exception{
		
		log.debug("queryDirecTrfAuth method!");
		JsonResultObject result = new JsonResultObject();
		
		Map<String,String> params = RequestParemters.queryDirecTrfAuthParams(lusrcustid);
		
		//向汇付发起请求信息
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("查询返回值：{}", huifuResult);
		
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			
			HuifuQueryDirecTrfAuthBackVo backinfo = JsonUtil.fromJson(huifuResult, HuifuQueryDirecTrfAuthBackVo.class);
			if(backinfo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(backinfo.getCmdId())).append(
						StringUtils.trimToEmpty(backinfo.getRespCode())).append(
						StringUtils.trimToEmpty(backinfo.getMerCustId())).append(
						StringUtils.trimToEmpty(backinfo.getUsrCustId())).append(
						StringUtils.trimToEmpty(backinfo.getInUsrCustId())).append(
						StringUtils.trimToEmpty(backinfo.getAuthAmt())).append(
						StringUtils.trimToEmpty(backinfo.getIsAuth())).append(
				        StringUtils.trimToEmpty(backinfo.getRespExt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, backinfo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(backinfo.getRespCode())) {
					result.setCode("1");
					result.setMessage((backinfo.getAuthAmt()==null || "".equals(backinfo.getAuthAmt()))?"0.00": backinfo.getAuthAmt());
					result.setReturnObj(backinfo);
					return result;
				}else{
					result.setCode("0");
					result.setMessage(URLDecoder.decode(backinfo.getRespDesc(),"UTF-8"));
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("标的接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("余额查询接口返回数据格式错误!");
			return result;
		}
	}
	
	/**
	 * 用户信息查询
	 * @param lusrcustid @
	 * @return
	 * @throws Exception
	 */
	public JsonResultObject queryUserInfo(String idnum) throws Exception {
		
		log.debug("queryUserInfo method!");
		Map<String,String> params = RequestParemters.queryUserInfoParams(idnum);
		
		JsonResultObject result = new JsonResultObject();
		
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("查询返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			
			HuifuUserInfoBackVo backinfo = JsonUtil.fromJson(huifuResult, HuifuUserInfoBackVo.class);
			if(backinfo!=null){
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(backinfo.getCmdId())).append(
						StringUtils.trimToEmpty(backinfo.getRespCode())).append(
						StringUtils.trimToEmpty(backinfo.getMerCustId())).append(
						StringUtils.trimToEmpty(backinfo.getUsrCustId())).append(
						StringUtils.trimToEmpty(backinfo.getUsrId())).append(
						StringUtils.trimToEmpty(backinfo.getCertId())).append(
						StringUtils.trimToEmpty(backinfo.getUsrStat())).append(
				        StringUtils.trimToEmpty(backinfo.getRespExt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, backinfo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(backinfo.getRespCode())) {
					
					result.setCode("1");
					result.setMessage("OK");
					result.setReturnObj(backinfo);
					return result;
					
				}else{
					result.setCode("0");
					result.setMessage(URLDecoder.decode(backinfo.getRespDesc(),"UTF-8"));
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("QueryTenderPlan返回数据无法转成对应的对象!");
				return result; 
			}
			
		}else{
			result.setCode("0");
			result.setMessage("QueryTenderPlan无返回数据!");
			return result;  
		}
		
	}
	
	/**
	 * 用户是否开启自动投资功能
	 * @param lusrcustid @
	 * @return
	 * @throws Exception
	 */
	public boolean queryTenderPlan(String lusrcustid) throws Exception {
		
		log.debug("queryTenderPlanParams method!");
		Map<String,String> params = RequestParemters.queryTenderPlanParams(lusrcustid);
		
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("查询返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			
			HuifuQueryTenderPlanBackVo backinfo = JsonUtil.fromJson(huifuResult, HuifuQueryTenderPlanBackVo.class);
			if(backinfo!=null){
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(backinfo.getCmdId())).append(
						StringUtils.trimToEmpty(backinfo.getRespCode())).append(
						StringUtils.trimToEmpty(backinfo.getMerCustId())).append(
						StringUtils.trimToEmpty(backinfo.getUsrCustId())).append(
						StringUtils.trimToEmpty(backinfo.getTransStat()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, backinfo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(backinfo.getRespCode())) {
					
					return "N".equals(backinfo.getTransStat());
					
				}else{
					log.debug("QueryTenderPlan签名验证失败!");
					return false;
				}
				
			}else{
				log.debug("QueryTenderPlan返回数据无法转成对应的对象!");
				return false;
			}
			
		}else{
			log.debug("QueryTenderPlan无返回数据!");
			return false;
		}
		
	}
	
	/**
	 * 汇付用户余额查询
	 * @param lusrcustid @
	 * @return
	 * @throws Exception
	 */
	public JsonResultObject queryBalance(String lusrcustid) throws Exception {
		
		log.debug("queryBalance method!");
		JsonResultObject result = new JsonResultObject();
		
		Map<String,String> params = RequestParemters.queryBalanceBgParams(lusrcustid);
		
		//向汇付发起请求信息
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("账户余额查询返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			
			HuifuQueryBalanceBgVo backinfo = JsonUtil.fromJson(huifuResult, HuifuQueryBalanceBgVo.class);
			if(backinfo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(backinfo.getCmdId())).append(
						StringUtils.trimToEmpty(backinfo.getRespCode())).append(
						StringUtils.trimToEmpty(backinfo.getMerCustId())).append(
						StringUtils.trimToEmpty(backinfo.getUsrCustId())).append(
						StringUtils.trimToEmpty(backinfo.getAvlBal())).append(
						StringUtils.trimToEmpty(backinfo.getAcctBal())).append(
				        StringUtils.trimToEmpty(backinfo.getFrzBal()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, backinfo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(backinfo.getRespCode())) {
					
					result.setCode("1");
					result.setMessage(backinfo.getAvlBal());
					result.setReturnObj(backinfo);
					return result;
				
				}else{
					
					result.setCode("0");
					result.setMessage(URLDecoder.decode(backinfo.getRespDesc(),"UTF-8"));
					return result;
					
				}
				
			}else{
				result.setCode("0");
				result.setMessage("标的接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("余额查询接口返回数据格式错误!");
			return result;
		}
		
	}
	
	
	/**
	 * 生利宝产品信息查询接口
	 * @param lusrcustid @
	 * @return
	 * @throws Exception
	 */
	public JsonResultObject queryFss() throws Exception {
		
		log.debug("queryFss method!");
		JsonResultObject result = new JsonResultObject();
		Map<String,String> params = RequestParemters.queryFssBgParams();
		
		//向汇付发起请求信息
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("生利宝产品信息查询返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			
			HuifuQueryFssBackVo backinfo = JsonUtil.fromJson(huifuResult, HuifuQueryFssBackVo.class);
			if(backinfo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(backinfo.getCmdId())).append(
						StringUtils.trimToEmpty(backinfo.getRespCode())).append(
						StringUtils.trimToEmpty(backinfo.getMerCustId())).append(
						StringUtils.trimToEmpty(backinfo.getAnnuRate())).append(
						StringUtils.trimToEmpty(backinfo.getPrdRate())).append(
				        StringUtils.trimToEmpty(backinfo.getRespExt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, backinfo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(backinfo.getRespCode())) {
					
					result.setCode("1");
					result.setMessage("生利宝产品信息查询成功!");
					result.setReturnObj(backinfo);
					return result;
				
				}else{
					
					result.setCode("0");
					result.setMessage(URLDecoder.decode(backinfo.getRespDesc(),"UTF-8"));
					return result;
					
				}
				
			}else{
				result.setCode("0");
				result.setMessage("生利宝产品信息查询接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("生利宝产品信息查询接口返回数据格式错误!");
			return result;
		}
		
	}
	
	
	/**
	 * 生利宝账户信息查询接口
	 * @param usrCustId
	 * @return
	 * @throws Exception
	 */
	public JsonResultObject queryFssAccts(String usrCustId) throws Exception {
		
		log.debug("queryFssAccts method!");
		JsonResultObject result = new JsonResultObject();
		Map<String,String> params = RequestParemters.queryFssAcctsBgParams(usrCustId);
		
		//向汇付发起请求信息
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("生利宝账户信息查询接口返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			
			HuifuQueryFssAcctsBackVO backinfo = JsonUtil.fromJson(huifuResult, HuifuQueryFssAcctsBackVO.class);
			if(backinfo!=null){
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(backinfo.getCmdId())).append(
				StringUtils.trimToEmpty(backinfo.getRespCode())).append(
				StringUtils.trimToEmpty(backinfo.getMerCustId())).append(
				StringUtils.trimToEmpty(backinfo.getUsrCustId())).append(
				StringUtils.trimToEmpty(backinfo.getTotalAsset())).append(
				StringUtils.trimToEmpty(backinfo.getTotalProfit())).append(
				StringUtils.trimToEmpty(backinfo.getRespExt()));
				
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, backinfo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(backinfo.getRespCode())) {
					
					result.setCode("1");
					result.setMessage("生利宝账户信息查询成功!");
					result.setReturnObj(backinfo);
					return result;
					
				}else{
					
					result.setCode("0");
					result.setMessage(URLDecoder.decode(backinfo.getRespDesc(),"UTF-8"));
					return result;
					
				}
				
			}else{
				result.setCode("0");
				result.setMessage("生利宝账户信息查询接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("生利宝账户信息查询接口返回数据格式错误!");
			return result;
		}
		
	}
	
	
	@Autowired
	public void setHttpClientUtil(HttpClientUtil httpClientUtil) {
		this.httpClientUtil = httpClientUtil;
	}
}