package com.cbai.model.rongyin.payment.huifu.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.huifu.SignUtils;
import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.CheckIDCard;
import com.cbai.common.utils.JsonUtil;
import com.cbai.common.utils.MD5Util;

import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.CompanyInfo;
import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.LoanRepayPlanDetail;
import com.cbai.model.rongyin.ibatis.entity.MemAccount;
import com.cbai.model.rongyin.ibatis.entity.MemAccountLogs;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.MemCards;
import com.cbai.model.rongyin.ibatis.entity.MemRepaymentDetail;
import com.cbai.model.rongyin.ibatis.entity.Members;
import com.cbai.model.rongyin.payment.huifu.service.HuifuCallBackService;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuAddBidInfoBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoCreditAssignBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoTenderBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoTenderCancleVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuCashOutBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuComRegisterBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuDirecTrfBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuInitiativeTenderBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuLoansBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuNetSaveBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuRepaymentBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuUserBindCardBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuUserRegisterBackVo;
import com.cbai.model.rongyin.pc.member.service.MemberService;
import com.cbai.model.rongyin.sys.loan.service.impl.LoanTenderServiceImpl;
import com.cbai.model.rongyin.sys.loan.vo.LoanSearchVO;

@Controller
@RequestMapping(value="hfcallback")
public class HuifuCallBackAction {

	private static final Logger log = LoggerFactory.getLogger(HuifuCallBackAction.class);
	
	private IbatisBaseDao baseDao;
	
	private HuifuCallBackService huifuCallBackService;
	
	private MemberService memberService;
	
	
	////////////////////////////////////////////////////////////汇付前台回调接口////////////////////////////////////////////////////////////////////////////////////////////
    /***************************************************************************************************************************************************/
	////////////////////////////////////////////////////////////汇付前台回调接口////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 会员开户回调页面接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="page/userRegister.do",method={RequestMethod.POST, RequestMethod.GET})
	public String userRegisterCallbackPage() throws Exception{
		return "mobile/huifu/userRegisterResults";
	}
	
	
	/**
     * 会员开户回调后台接口
     * @return
     * @throws Exception
     */
	@RequestMapping(value="userRegister.do",method={RequestMethod.POST, RequestMethod.GET})
	public void userRegisterCallback(HttpServletResponse response,@ModelAttribute HuifuUserRegisterBackVo huifuUserRegisterBackVo) throws Exception{
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuUserRegisterBackVo.getCmdId())).append(
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getRespCode())).append(
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getMerCustId())).append(	
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getUsrId())).append(
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getUsrCustId())).append(
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getBgRetUrl())).append(
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getTrxId())).append(
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getRetUrl())).append(
				StringUtils.trimToEmpty(huifuUserRegisterBackVo.getMerPriv()));
		
		String plainStr = buffer.toString();
		boolean isOK = SignUtils.verifyByRSA(plainStr, huifuUserRegisterBackVo.getChkValue());
		if(isOK && "000".equals(huifuUserRegisterBackVo.getRespCode())){
			
			Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("memnum", huifuUserRegisterBackVo.getMerPriv());
			//用户开户成功!
			Members member = new Members();
			
			member.setMemnum(huifuUserRegisterBackVo.getMerPriv());
			member.setBirth(CheckIDCard.getBirth(huifuUserRegisterBackVo.getIdNo()));
			member.setSex(CheckIDCard.getSex(huifuUserRegisterBackVo.getIdNo()));
			member.setUsrcustid(huifuUserRegisterBackVo.getUsrCustId());
			member.setUsrcustname(huifuUserRegisterBackVo.getUsrId());
			member.setRealname(huifuUserRegisterBackVo.getUsrName());
			member.setIdnum(huifuUserRegisterBackVo.getIdNo());   
			
			
			//认证时间
			member.setAuthtime(new Date());
			baseDao.update("pc_mem.update", member);
			
			
			response.getWriter().write("RECV_ORD_ID");
		}else{
			response.getWriter().write("");
		}
	}
	
	/**
	 * 会员充值回调页面接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="page/netSave.do",method={RequestMethod.POST, RequestMethod.GET})
	public String netSaveCallbackPage() throws Exception{
		return "mobile/huifu/netSaveResults";
	}
	
	/**
	 * 会员充值回调接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="netSave.do",method={RequestMethod.POST, RequestMethod.GET})
	public void netSaveCallback(HttpServletResponse response, @ModelAttribute HuifuNetSaveBackVo huifuNetSaveBackVo) throws Exception{
		if(huifuNetSaveBackVo!=null){
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getCmdId()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getRespCode()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getMerCustId()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getUsrCustId()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getOrdId()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getOrdDate()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getTransAmt()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getTrxId()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getRetUrl()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getBgRetUrl()))
			      .append(StringUtils.trimToEmpty(huifuNetSaveBackVo.getMerPriv()));
			
			
			String plainStr = buffer.toString();
			boolean isOK = SignUtils.verifyByRSA(plainStr, huifuNetSaveBackVo.getChkValue());
			
			// 签名验证成功,且返回码为000才通知汇付交易完成
			if (isOK && "000".equals(huifuNetSaveBackVo.getRespCode())) {
				
				Map<String,Object> logParam = new HashMap<String, Object>();
				logParam.put("ordid", huifuNetSaveBackVo.getOrdId());
				logParam.put("ltype", State.MAT1.toString());
				
				MemAccountLogs memAccountLogs = baseDao.queryObject("pc_mem_account_logs.select", logParam);
				if(memAccountLogs!=null){
					
					//应答我已经收到这个信息了
					response.getWriter().write("RECV_ORD_ID");
					
				}else{
					
					huifuCallBackService.saveNetSaveCallback(huifuNetSaveBackVo);
					//应答我已经收到这个信息了
					response.getWriter().write("RECV_ORD_ID");
					
				}
			
			}else{
				response.getWriter().write("");
			}
		}
		
	}
	
	/**
	 * 会员提现回调页面接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="page/cashOut.do",method={RequestMethod.POST, RequestMethod.GET})
	public String cashOutPage() throws Exception{
		return "frong/huifu/cashOutResults";
	}
	
	/**
	 * 会员提现回调处理接口
	 * @param response
	 * @param huifuDirecTrfBackVo
	 * @throws Exception
	 */
	@RequestMapping(value="cashOut.do",method={RequestMethod.POST, RequestMethod.GET})
	public void cashOut(HttpServletResponse response, @ModelAttribute HuifuCashOutBackVo huifuCashOutBackVo) throws Exception{
		
		if(huifuCashOutBackVo!=null){
			StringBuffer buffer = new StringBuffer();
			
			buffer.append(StringUtils.trimToEmpty(huifuCashOutBackVo.getCmdId())).append(
					StringUtils.trimToEmpty(huifuCashOutBackVo.getRespCode())).append(
					StringUtils.trimToEmpty(huifuCashOutBackVo.getMerCustId())).append(
					StringUtils.trimToEmpty(huifuCashOutBackVo.getOrdId())).append(
					StringUtils.trimToEmpty(huifuCashOutBackVo.getUsrCustId())).append(
					StringUtils.trimToEmpty(huifuCashOutBackVo.getTransAmt())).append(
					StringUtils.trimToEmpty(huifuCashOutBackVo.getOpenAcctId())).append(		
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getOpenBankId())).append(
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getFeeAmt())).append(
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getFeeCustId())).append(	
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getFeeAcctId())).append(
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getServFee())).append(
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getServFeeAcctId())).append(
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getRetUrl())).append(
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getBgRetUrl())).append(
			        StringUtils.trimToEmpty(huifuCashOutBackVo.getMerPriv())).append(
					StringUtils.trimToEmpty(huifuCashOutBackVo.getRespExt()));
			
			String plainStr = buffer.toString();
			boolean isOK = SignUtils.verifyByRSA(plainStr, huifuCashOutBackVo.getChkValue());
			
			if (isOK) {
				
				// 查询失败或者 解签 失败 返回null
				if (isOK && "000".equals(huifuCashOutBackVo.getRespCode())) {

					//System.out.println("签名返回成功!");
					huifuCallBackService.saveCashOutCallback(huifuCashOutBackVo);
					
					//用户主动投资完成告诉汇付可以
					response.getWriter().write("RECV_ORD_ID");
					
				}else{
					response.getWriter().write("RECV_ORD_ID");
				}
				
			}else{
				response.getWriter().write("");
			}
		
		}
	}
	
	/**
	 * 用户主动投资回调接口
	 * @param huifuUserBindCardBackVo
	 * @throws Exception
	 */
	@RequestMapping(value="initiativeTender.do",method={RequestMethod.POST, RequestMethod.GET})
	public void initiativeTenderCallback(HttpServletResponse response, @ModelAttribute HuifuInitiativeTenderBackVo huifuInitiativeTenderBackVo) throws Exception{
		// TODO method stub
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

			huifuCallBackService.buyInitiativeTender(huifuInitiativeTenderBackVo);
			
			//用户主动投资完成告诉汇付可以
			response.getWriter().write("RECV_ORD_ID");
			
		}else{
			
			response.getWriter().write("");
		}
		
	}
	
	
	/**
	 * 胜利宝交易接口回到函数
	 * @throws Exception
	 */
	public void fssTransCallback() throws Exception{
		
	}
	
	
	/////////////////////////////////////////////////////汇付后台回调接口//////////////////////////////////////////////////////////////////////////////////////
    /***************************************************************************************************************************************************/
	/////////////////////////////////////////////////////汇付后台回调接口//////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 发标回调接口
	 * @param response
	 * @param huifuAddBidInfo
	 * @throws Exception
	 */
	@RequestMapping(value="addBidInfo.do",method={RequestMethod.POST, RequestMethod.GET})
	public void addBidInfoCallback(HttpServletResponse response,@ModelAttribute HuifuAddBidInfoBackVo huifuAddBidInfo) throws Exception{
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuAddBidInfo.getCmdId())).append(
				StringUtils.trimToEmpty(huifuAddBidInfo.getRespCode())).append(
				StringUtils.trimToEmpty(huifuAddBidInfo.getMerCustId())).append(	
				StringUtils.trimToEmpty(huifuAddBidInfo.getProId())).append(
				StringUtils.trimToEmpty(huifuAddBidInfo.getAuditStat())).append(
				StringUtils.trimToEmpty(huifuAddBidInfo.getBgRetUrl())).append(
				StringUtils.trimToEmpty(huifuAddBidInfo.getMerPriv())).append(
				StringUtils.trimToEmpty(huifuAddBidInfo.getRespExt()));
		
		String plainStr = buffer.toString();
		boolean isOK = SignUtils.verifyByRSA(MD5Util.getMD5_32(plainStr), huifuAddBidInfo.getChkValue());
		if (isOK && "000".equals(huifuAddBidInfo.getRespCode())) {
			//标的发布成功!
			response.getWriter().write("RECV_ORD_ID");
		}else{
			response.getWriter().write("");
		}
		
	}
	
	
	/**
	 * 后台撤销投标接口
	 * @param response
	 * @param huifuAddBidInfo
	 * @throws Exception
	 */
	@RequestMapping(value="autoTenderCancel.do",method={RequestMethod.POST, RequestMethod.GET})
	public void autoTenderCancel(HttpServletResponse response, @ModelAttribute HuifuAutoTenderCancleVo huifuAutoTenderCancleVo) throws Exception{
		
		if(huifuAutoTenderCancleVo!=null){
			
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getCmdId())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getRespCode())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getMerCustId())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getOrdId())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getOrdDate())).append(
			        StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getTransAmt())).append(
			        StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getUsrCustId())).append(
			        
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getIsUnFreeze())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getUnFreezeOrdId())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getFreezeTrxId())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getBgRetUrl())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getMerPriv())).append(
					StringUtils.trimToEmpty(huifuAutoTenderCancleVo.getRespExt()));
			
			String plainStr = buffer.toString();
			boolean isOK = SignUtils.verifyByRSA(MD5Util.getMD5_32(URLDecoder.decode(plainStr,"UTF-8")), huifuAutoTenderCancleVo.getChkValue());
			
			//查询失败或者 解签 失败 返回null
			if (isOK) {
				
				//撤销投标成功!!
				if("000".equals(huifuAutoTenderCancleVo.getRespCode())){
					huifuCallBackService.saveAutoTenderCancel(huifuAutoTenderCancleVo);
				}else{
					//删除刚刚
					log.debug("撤销投标失败失败!错误码:{}",huifuAutoTenderCancleVo.getRespCode());
				}
				
			}else{
				log.debug("签名验证失败!");
			}
			
		}else{
			//删除刚刚
			log.debug("返回数据为空!");
		}
		
	}
	
	/**
	 * 放款回调接口
	 * @param response
	 * @param huifuAddBidInfo
	 * @throws Exception
	 */
	@RequestMapping(value="loans.do",method={RequestMethod.POST, RequestMethod.GET})
	public void loansCallback(HttpServletResponse response, @ModelAttribute HuifuLoansBackVo huifuLoansBackVo) throws Exception{
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getCmdId())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getRespCode())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getMerCustId())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getOrdId())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getOrdDate())).append(	
				StringUtils.trimToEmpty(huifuLoansBackVo.getOutCustId())).append(		
		        StringUtils.trimToEmpty(huifuLoansBackVo.getOutAcctId())).append(
		        StringUtils.trimToEmpty(huifuLoansBackVo.getTransAmt())).append(		
		        StringUtils.trimToEmpty(huifuLoansBackVo.getFee())).append(		
				StringUtils.trimToEmpty(huifuLoansBackVo.getInCustId())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getInAcctId())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getSubOrdId())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getSubOrdDate())).append(
				
				StringUtils.trimToEmpty(huifuLoansBackVo.getFeeObjFlag())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getIsDefault())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getIsUnFreeze())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getUnFreezeOrdId())).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getFreezeTrxId())).append(
				StringUtils.trimToEmpty(URLDecoder.decode(huifuLoansBackVo.getBgRetUrl(),"UTF-8"))).append(
				StringUtils.trimToEmpty(huifuLoansBackVo.getMerPriv())).append(
				StringUtils.trimToEmpty(URLDecoder.decode(huifuLoansBackVo.getRespExt(),"UTF-8")));
		
		String plainStr = buffer.toString();
		boolean isOK = SignUtils.verifyByRSA(plainStr, huifuLoansBackVo.getChkValue());
		
		if (isOK) {
			//放款成功!
			if("000".equals(huifuLoansBackVo.getRespCode())){
				huifuCallBackService.saveLoans(huifuLoansBackVo);
				//告知汇付放款完成
				response.getWriter().write("RECV_ORD_ID");
			}else{
				
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("bidordernum", huifuLoansBackVo.getSubOrdId());
				
				MemBidLogs memBidLogs = baseDao.queryObject("mem_bid_logs.getAll", params);
				if(memBidLogs!=null){
					memBidLogs.setState(State.BIDFAIL);
					memBidLogs.setOppaytime(new Date());
					baseDao.update("mem_bid_logs.update", memBidLogs);
				}
				response.getWriter().write("RECV_ORD_ID");
			}
		}else{
			response.getWriter().write("");
		}
	}
	
	
	/**
	 * 还款回调接口
	 * @param response
	 * @param huifuAddBidInfo
	 * @throws Exception
	 */
    @RequestMapping(value="rePayment.do",method={RequestMethod.POST, RequestMethod.GET})
	public void rePaymentCallback(HttpServletResponse response, @ModelAttribute HuifuRepaymentBackVo huifuRepaymentBackVo) throws Exception{
		
    	if(huifuRepaymentBackVo!=null){
			StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(huifuRepaymentBackVo.getCmdId())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getRespCode())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getMerCustId())).append(	
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getProId())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getOrdId())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getOrdDate())).append(	
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getOutCustId())).append(		
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getSubOrdId())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getSubOrdDate())).append(
				    StringUtils.trimToEmpty(huifuRepaymentBackVo.getOutAcctId())).append(
				    StringUtils.trimToEmpty(huifuRepaymentBackVo.getPrincipalAmt())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getInterestAmt())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getFee())).append(	
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getInCustId())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getInAcctId())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getFeeObjFlag())).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getDzObject())).append(
					StringUtils.trimToEmpty(URLDecoder.decode(huifuRepaymentBackVo.getBgRetUrl(),"UTF-8"))).append(
					StringUtils.trimToEmpty(huifuRepaymentBackVo.getMerPriv())).append(
					StringUtils.trimToEmpty(URLDecoder.decode(huifuRepaymentBackVo.getRespExt(),"UTF-8")));
			
			//StringUtils.trimToEmpty(huifuLoansBackVo.getRespExt()));
			String plainStr = buffer.toString();
			boolean isOK = SignUtils.verifyByRSA(MD5Util.getMD5_32(plainStr), huifuRepaymentBackVo.getChkValue());
			
			// 查询失败或者 解签 失败 返回null
			if (isOK) {
				if("000".equals(huifuRepaymentBackVo.getRespCode())){
					response.getWriter().write("RECV_ORD_ID");
				}else{
					response.getWriter().write("");
				}
			}
    	}
	}
	
	
	/**
	 * 定向划拨回调接口
	 * @param response
	 * @param huifuDirecTrfBackVo
	 * @throws Exception
	 */
	@RequestMapping(value="direcTrf.do",method={RequestMethod.POST, RequestMethod.GET})
	public void direcTrfCallback(HttpServletResponse response, @ModelAttribute HuifuDirecTrfBackVo huifuDirecTrfBackVo) throws Exception{
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuDirecTrfBackVo.getCmdId())).append(
				StringUtils.trimToEmpty(huifuDirecTrfBackVo.getRespCode())).append(
				StringUtils.trimToEmpty(huifuDirecTrfBackVo.getMerCustId())).append(
				StringUtils.trimToEmpty(huifuDirecTrfBackVo.getOrdId())).append(
				StringUtils.trimToEmpty(huifuDirecTrfBackVo.getUsrCustId())).append(
				StringUtils.trimToEmpty(huifuDirecTrfBackVo.getInUsrCustId())).append(
				StringUtils.trimToEmpty(huifuDirecTrfBackVo.getTransAmt())).append(
		        StringUtils.trimToEmpty(huifuDirecTrfBackVo.getRetUrl())).append(
		        StringUtils.trimToEmpty(huifuDirecTrfBackVo.getBgRetUrl())).append(
		        StringUtils.trimToEmpty(huifuDirecTrfBackVo.getMerPriv())).append(
				StringUtils.trimToEmpty(huifuDirecTrfBackVo.getRespExt()));
		
		String plainStr = buffer.toString();
		
		boolean isOK = SignUtils.verifyByRSA(plainStr, huifuDirecTrfBackVo.getChkValue());
		if (isOK) {
			
			LoanInfo loanInfo = new LoanInfo();
			loanInfo.setDirordid(huifuDirecTrfBackVo.getOrdId());
			
			//定向划款成功!
			if("000".equals(huifuDirecTrfBackVo.getRespCode())){
				
				loanInfo.setDirstate(State.DONE);
				loanInfo.setDiroptime(new Date());
				baseDao.update("sys_loan.SYS-UPDATE-BYDIRORDID", loanInfo);
				//告知汇付放款完成
				response.getWriter().write("RECV_ORD_ID");
				
			}else{
				loanInfo.setDirstate(null);
				loanInfo.setDirtime(null);
				baseDao.update("sys_loan.SYS-UPDATE-BYDIRORDID", loanInfo);
			}
			
		}else{
			response.getWriter().write("");
		}
		
	}
	
	
	/**
	 * 用户自动投标回调接口
	 * @param huifuUserBindCardBackVo
	 * @throws Exception
	 */
	@RequestMapping(value="autoTender.do",method={RequestMethod.POST, RequestMethod.GET})
	public void autoTenderCallback(HttpServletResponse response, @ModelAttribute HuifuAutoTenderBackVo huifuAutoTenderBackVo) throws Exception{
		
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
		if (isOK) {
			
			//用户自动投标完成告诉汇付可以了
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("bidordernum", huifuAutoTenderBackVo.getOrdId());
			param.put("usrcustid", huifuAutoTenderBackVo.getUsrCustId());
			
			MemBidLogs memBidLogs = baseDao.queryObject("mem_bid_logs.getAll", param);
			
			if(memBidLogs!=null){
				memBidLogs.setOptime(new Date());
				
				//投标成功的时候修改投标记录为已完成
				if("000".equals(huifuAutoTenderBackVo.getRespCode())){
					
					memBidLogs.setBidtrxid(huifuAutoTenderBackVo.getTrxId());
					memBidLogs.setFreezetrxid(huifuAutoTenderBackVo.getFreezeTrxId());
					memBidLogs.setState(State.BIDDONE);
					
					baseDao.update("mem_bid_logs.updateState", memBidLogs);	
					
				}else{
					
					//投标不成功，把投资记录修改成未成功
					memBidLogs.setState(State.BIDFAIL);
					baseDao.update("mem_bid_logs.updateState", memBidLogs);
					
					//将标的可投金额还原到本次投标前
					Map<String,Object> loanMoneyParam = new HashMap<String, Object>();
					loanMoneyParam.put("transAmt",AmountUtils.changeY2F(huifuAutoTenderBackVo.getTransAmt()));
					loanMoneyParam.put("lnid", memBidLogs.getLnid());
					baseDao.update("sys_loan.SYS-UPDATE-AVALMONEY", loanMoneyParam);
					
					//将订单可用金额还原到本次投标前
			        Map<String,Object> paramMap = new HashMap<String, Object>();
			        paramMap.put("poid", memBidLogs.getPoid());
			        paramMap.put("transAmt", Long.valueOf(AmountUtils.changeY2F(huifuAutoTenderBackVo.getTransAmt())));
			        baseDao.update("product_orders.updateAvlmoney", paramMap);
				}
			}
			
			response.getWriter().write("RECV_ORD_ID");
		}else{
			response.getWriter().write("");
		}
		
	}
    
	
	
	/**
	 * 会员开启自动投资功能页面接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="page/autoTenderPlan.do",method={RequestMethod.POST, RequestMethod.GET})
	public String autoTenderPlanCallbackPage() throws Exception{
		return "mobile/huifu/autoPlanResults";
	}
	
	/**
	 * 会员关闭自动投资功能页面接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="page/autoTenderPlanClose.do",method={RequestMethod.POST, RequestMethod.GET})
	public String autoTenderPlanCloseCallbackPage() throws Exception{
		return "mobile/huifu/autoPlanCloseResults";
	}
    

	/**
	 * 用户绑卡回调接口
	 * @param huifuUserBindCardBackVo
	 * @throws Exception
	 */
	@RequestMapping(value="userBindCard.do",method={RequestMethod.POST, RequestMethod.GET})
	public void addBidInfoCallback(HttpServletResponse response, @ModelAttribute HuifuUserBindCardBackVo huifuUserBindCardBackVo) throws Exception{
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuUserBindCardBackVo.getCmdId())).append(
				StringUtils.trimToEmpty(huifuUserBindCardBackVo.getRespCode())).append(
				StringUtils.trimToEmpty(huifuUserBindCardBackVo.getMerCustId())).append(
			    StringUtils.trimToEmpty(huifuUserBindCardBackVo.getOpenAcctId())).append(
				StringUtils.trimToEmpty(huifuUserBindCardBackVo.getOpenBankId())).append(
				StringUtils.trimToEmpty(huifuUserBindCardBackVo.getUsrCustId())).append(
				StringUtils.trimToEmpty(huifuUserBindCardBackVo.getTrxId())).append(
				StringUtils.trimToEmpty(huifuUserBindCardBackVo.getBgRetUrl())).append(
				StringUtils.trimToEmpty(huifuUserBindCardBackVo.getMerPriv()));
		
		String plainStr = buffer.toString();
		boolean isOK = SignUtils.verifyByRSA(plainStr, huifuUserBindCardBackVo.getChkValue());
		
		// 查询失败或者 解签 失败 返回null
		if (isOK && "000".equals(huifuUserBindCardBackVo.getRespCode())) {
			
			MemCards cards = new MemCards();
			
			cards.setMemid(Integer.valueOf(huifuUserBindCardBackVo.getMerPriv()));
			
			cards.setBkcode(huifuUserBindCardBackVo.getOpenBankId());
			
			cards.setCardnum(huifuUserBindCardBackVo.getOpenAcctId());
			
			cards.setAddtime(new Date());
			
			memberService.saveCard(cards);
			
			//绑卡成功!
			response.getWriter().write("RECV_ORD_ID");
		}else{
			response.getWriter().write("");
		}
	}
	
	
	@RequestMapping(value="companyRegister.do",method={RequestMethod.POST, RequestMethod.GET})
	public void companyRegisterCallback(HttpServletResponse response, @ModelAttribute HuifuComRegisterBackVo huifuComRegisterBackVo) throws Exception{
		
		/*StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuComRegisterBackVo.getCmdId())).append(
				StringUtils.trimToEmpty(huifuComRegisterBackVo.getRespCode())).append(
				StringUtils.trimToEmpty(huifuComRegisterBackVo.getMerCustId())).append(	
				StringUtils.trimToEmpty(huifuComRegisterBackVo.getUsrId())).append(
			    StringUtils.trimToEmpty(huifuComRegisterBackVo.getUsrName())).append(
				StringUtils.trimToEmpty(huifuComRegisterBackVo.getUsrCustId())).append(
			    StringUtils.trimToEmpty(huifuComRegisterBackVo.getAuditStat())).append(
			    StringUtils.trimToEmpty(huifuComRegisterBackVo.getTrxId())).append(	
			    StringUtils.trimToEmpty(huifuComRegisterBackVo.getTrxId())).append(
			    StringUtils.trimToEmpty(huifuComRegisterBackVo.getOpenBankId())).append(
			    StringUtils.trimToEmpty(huifuComRegisterBackVo.getCardId())).append( 
				StringUtils.trimToEmpty(huifuComRegisterBackVo.getBgRetUrl())).append( 
				StringUtils.trimToEmpty(huifuComRegisterBackVo.getRespExt()));
		
		String plainStr = buffer.toString();
		boolean isOK = SignUtils.verifyByRSA(plainStr, huifuComRegisterBackVo.getChkValue());*/
		CompanyInfo cominfo = new CompanyInfo();
		 
		cominfo.setCmnum(huifuComRegisterBackVo.getMerPriv());
		
		cominfo.setAuditStat(huifuComRegisterBackVo.getAuditStat());
		
		if(huifuComRegisterBackVo.getAuditStat().equals("Y")){
			 
			cominfo.setUsrcustid(huifuComRegisterBackVo.getUsrCustId());
			
			cominfo.setBkabbr(huifuComRegisterBackVo.getOpenBankId());
			
			cominfo.setBkcardnum(huifuComRegisterBackVo.getCardId()); 
		} 
		baseDao.update("company_info.updateFromHuifu", cominfo);
		
		response.getWriter().write("RECV_ORD_ID");
		return;
	}
	
	@RequestMapping(value="autoCreditAssign.do",method={RequestMethod.POST, RequestMethod.GET})
	public void autoCreditAssign(HttpServletResponse response, @ModelAttribute HuifuAutoCreditAssignBackVo huifuAutoCreditAssignBackVo) throws Exception{
		
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
			
			//用户自动投标完成告诉汇付可以了
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("bidordernum", huifuAutoCreditAssignBackVo.getOrdId());
			param.put("usrcustid", huifuAutoCreditAssignBackVo.getBuyCustId());
			
			MemBidLogs memBidLogs = baseDao.queryObject("mem_bid_logs.getAll", param);
			if(memBidLogs!=null){
				
				memBidLogs.setOptime(new Date());
				
				if("000".equals(huifuAutoCreditAssignBackVo.getRespCode())){
					
					//债权转让成功的时候
					memBidLogs.setState(State.BIDDONE);
					baseDao.update("mem_bid_logs.updateState", memBidLogs);
					response.getWriter().write("RECV_ORD_ID");
					
				}else{
					
					//债权转让不成功，把投资记录修改成未成功
					memBidLogs.setState(State.BIDFAIL);
					baseDao.update("mem_bid_logs.updateState", memBidLogs);
					
					//将标的可投金额还原到本次投标前
					Map<String,Object> loanMoneyParam = new HashMap<String, Object>();
					loanMoneyParam.put("transAmt",AmountUtils.changeY2F(huifuAutoCreditAssignBackVo.getCreditAmt()));
					loanMoneyParam.put("lnid", memBidLogs.getLnid());
					baseDao.update("sys_loan.SYS-UPDATE-AVALMONEY", loanMoneyParam);
					
					//将订单可用金额还原到本次投标前
			        Map<String,Object> paramMap = new HashMap<String, Object>();
			        paramMap.put("poid", memBidLogs.getPoid());
			        paramMap.put("transAmt", Long.valueOf(AmountUtils.changeY2F(huifuAutoCreditAssignBackVo.getCreditAmt())));
			        baseDao.update("product_orders.updateAvlmoney", paramMap);
			        response.getWriter().write("");
				}
				
			}
		}
	}
	
	@Autowired
	public void setHuifuCallBackService(HuifuCallBackService huifuCallBackService) {
		this.huifuCallBackService = huifuCallBackService;
	}
	
	
	@Autowired
	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	} 
}
