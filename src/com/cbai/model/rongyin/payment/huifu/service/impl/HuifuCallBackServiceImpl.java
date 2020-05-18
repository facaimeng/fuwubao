package com.cbai.model.rongyin.payment.huifu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cbai.model.common.data.State;

import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.utils.AmountUtils;

import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.MemAccount;
import com.cbai.model.rongyin.ibatis.entity.MemAccountLogs;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.MemCashOutDetail;
import com.cbai.model.rongyin.ibatis.entity.MemRechargeDetail;
import com.cbai.model.rongyin.ibatis.entity.Members;

import com.cbai.model.rongyin.payment.huifu.service.HuifuCallBackService;

import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoTenderCancleVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuCashOutBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuInitiativeTenderBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuLoansBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuNetSaveBackVo;

import com.cbai.model.rongyin.pc.web.vo.LoanFrontVo;
import com.cbai.model.rongyin.sys.loan.vo.LoanSearchVO;

public class HuifuCallBackServiceImpl implements HuifuCallBackService{
	
	
	private IbatisBaseDao baseDao;
	
	@Override
	public void saveNetSaveCallback(HuifuNetSaveBackVo huifuNetSaveBackVo) throws Exception {
		
			Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("usrcustid", huifuNetSaveBackVo.getUsrCustId());

			//用户充值成功!
			Members member = baseDao.queryObject("pc_mem.getAll", parameter);
			if(member != null){
				
				Map<String,Object> accountParam = new HashMap<String,Object>();
				accountParam.put("memid", member.getMemid());
				//回调为用户充值增加金额
				MemAccount memAccount= baseDao.queryObject("pc_mem_account.select", accountParam);
				
				if(memAccount!=null){
					memAccount.setAllassets(memAccount.getAllassets() + Long.valueOf(AmountUtils.changeY2F(huifuNetSaveBackVo.getTransAmt())));
					memAccount.setAvlmoney(memAccount.getAvlmoney() + Long.valueOf(AmountUtils.changeY2F(huifuNetSaveBackVo.getTransAmt())));
					memAccount.setLastcaltime(new Date());
					
					baseDao.update("pc_mem_account.update", memAccount);
				}
				
				//插入资金记录表
                MemAccountLogs accountLogs = new MemAccountLogs();
				accountLogs.setMemid(member.getMemid());
				accountLogs.setUsrcustid(member.getUsrcustid());
				accountLogs.setLtype(State.MAT1);
				accountLogs.setTitle("汇付充值");
				accountLogs.setTransmoney(Long.valueOf(AmountUtils.changeY2F(huifuNetSaveBackVo.getTransAmt())));
				accountLogs.setOrdid(huifuNetSaveBackVo.getOrdId());
				accountLogs.setState(State.DONE);
				accountLogs.setTrxid(huifuNetSaveBackVo.getTrxId());
			    
				
				//充值完成后更新当前可用余额
				accountLogs.setCurbalance(memAccount.getAvlmoney() + Long.valueOf(AmountUtils.changeY2F(huifuNetSaveBackVo.getTransAmt())));
				
				
				accountLogs.setAddtime(new Date());
				accountLogs.setOptime(new Date());
				Integer recide = (Integer)baseDao.insert("pc_mem_account_logs.insert", accountLogs);
				
				//插入充值明细表
				MemRechargeDetail logDetail = new MemRechargeDetail();
				logDetail.setRecid(recide);
				logDetail.setFeeamt(huifuNetSaveBackVo.getFeeAmt());
				logDetail.setFeecustid(huifuNetSaveBackVo.getFeeCustId());
				logDetail.setFeeaccid(huifuNetSaveBackVo.getFeeAcctId());
				logDetail.setPaygate(huifuNetSaveBackVo.getGateBusiId());
				logDetail.setBanknum(huifuNetSaveBackVo.getGateBankId());
				baseDao.insert("pc_mem_recharge_logs.insert", logDetail);
			}
	}
	
	
	@Override
	public void saveCashOutCallback(HuifuCashOutBackVo huifuCashOutBackVo)
			throws Exception {
		
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("usrcustid", huifuCashOutBackVo.getUsrCustId());

		//用户提现成功!
		Members member = baseDao.queryObject("pc_mem.getAll", parameter);

		if(member != null){
			
			Map<String,Object> accountParam = new HashMap<String,Object>();
			accountParam.put("memid", member.getMemid());
			accountParam.put("ltype", State.MAT2.toString());
			
			//提现的时候减少用户可用金额
			MemAccount memAccount= baseDao.queryObject("pc_mem_account.select", accountParam);
			
			if(memAccount!=null){
				memAccount.setAllassets(memAccount.getAllassets() - Long.valueOf(AmountUtils.changeY2F(huifuCashOutBackVo.getTransAmt())));
				memAccount.setAvlmoney(memAccount.getAvlmoney() - Long.valueOf(AmountUtils.changeY2F(huifuCashOutBackVo.getTransAmt())));
				memAccount.setLastcaltime(new Date());
				
				baseDao.update("pc_mem_account.update", memAccount);
			}
			
			Map<String,Object> logParam = new HashMap<String, Object>();
			logParam.put("ordid", huifuCashOutBackVo.getOrdId());
			
			MemAccountLogs accountLogs = baseDao.queryObject("pc_mem_account_logs.select", logParam);
			if(accountLogs==null){
				//插入资金记录表
		        MemAccountLogs newAccountLogs = new MemAccountLogs();
		        newAccountLogs.setMemid(member.getMemid());
		        newAccountLogs.setUsrcustid(member.getUsrcustid());
		        newAccountLogs.setLtype(State.MAT2);
		        newAccountLogs.setTitle("账户提现");
		        newAccountLogs.setTransmoney(Long.valueOf(AmountUtils.changeY2F(huifuCashOutBackVo.getTransAmt())));
		        newAccountLogs.setOrdid(huifuCashOutBackVo.getOrdId());
		        
		        
		        Date currentDate = new Date();
		        //充值完成后更新当前可用余额
		        newAccountLogs.setCurbalance(memAccount.getAvlmoney() - Long.valueOf(AmountUtils.changeY2F(huifuCashOutBackVo.getTransAmt())));
		        newAccountLogs.setState(State.DONE);
		        newAccountLogs.setAddtime(currentDate);
		        newAccountLogs.setOptime(currentDate);
		        
		        
				Integer recid = (Integer)baseDao.insert("pc_mem_account_logs.insert", newAccountLogs);
			    
				//插入提现明细表
				MemCashOutDetail cashOutDetail = new MemCashOutDetail();
				cashOutDetail.setRecid(recid);
				cashOutDetail.setCashChl(huifuCashOutBackVo.getMerPriv());
				cashOutDetail.setFeeaccid(huifuCashOutBackVo.getFeeAcctId());
				cashOutDetail.setFeeobjflag(huifuCashOutBackVo.getFeeCustId());
				//cashOutDetail.setFeetype(huifuCashOutBackVo.getFeeAcctId());
				cashOutDetail.setOpenaccid(huifuCashOutBackVo.getOpenAcctId());
				cashOutDetail.setServfee(AmountUtils.changeY2F(huifuCashOutBackVo.getServFee()));
				cashOutDetail.setServfeeaccid(huifuCashOutBackVo.getServFeeAcctId());
				baseDao.insert("pc_mem_cashout_logs.insert", cashOutDetail);
			}
		}
	}

	
	@Override
	public void buyInitiativeTender(
			HuifuInitiativeTenderBackVo huifuInitiativeTenderBackVo)
			throws Exception {
		
		//插入标的投资记录表
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("bidordernum", huifuInitiativeTenderBackVo.getOrdId());
		param.put("usrcustid", huifuInitiativeTenderBackVo.getUsrCustId());
		
		MemBidLogs memBidLogs = baseDao.queryObject("pc_mem_bid_logs.getAll", param);
		if(memBidLogs!=null && State.BIDDING.equals(memBidLogs.getState())){
			
			memBidLogs.setOptime(new Date());
			//投标成功的时候修改投标记录为已完成
			if("000".equals(huifuInitiativeTenderBackVo.getRespCode())){
				
				memBidLogs.setBidtrxid(huifuInitiativeTenderBackVo.getTrxId());
				memBidLogs.setFreezetrxid(huifuInitiativeTenderBackVo.getFreezeTrxId());
				memBidLogs.setState(State.BIDDONE);
				baseDao.update("pc_mem_bid_logs.updateState", memBidLogs);	
				
				//修改用户可投金额
		        Map<String,Object> memberParam = new HashMap<String,Object>();
		        memberParam.put("usrcustid", huifuInitiativeTenderBackVo.getUsrCustId());
		        Members member = baseDao.queryObject("pc_mem.getAll", memberParam);
				
		        //投标成功的时候减少用户可用金额
				String transAmt = huifuInitiativeTenderBackVo.getTransAmt();
				Map<String,Object> accountParam = new HashMap<String, Object>();
				accountParam.put("memid", member.getMemid());
				accountParam.put("ltype", State.MAT3.toString());
				
				MemAccount memAccount= baseDao.queryObject("pc_mem_account.select", accountParam);
				if(memAccount!=null){
					memAccount.setAllassets(memAccount.getAllassets() - Long.valueOf(AmountUtils.changeY2F(transAmt)));
					memAccount.setAvlmoney(memAccount.getAvlmoney() - Long.valueOf(AmountUtils.changeY2F(transAmt)));
					memAccount.setLastcaltime(new Date());
					
					baseDao.update("pc_mem_account.update", memAccount);
				}
				
				
				//插入资金记录表
		        MemAccountLogs newAccountLogs = new MemAccountLogs();
		        newAccountLogs.setMemid(member.getMemid());
		        newAccountLogs.setUsrcustid(member.getUsrcustid());
		        newAccountLogs.setLtype(State.MAT3);
		        newAccountLogs.setTitle("产品购买");
		        newAccountLogs.setTransmoney(Long.valueOf(AmountUtils.changeY2F(transAmt)));
		        newAccountLogs.setOrdid(huifuInitiativeTenderBackVo.getOrdId());
		        
		        Date currentDate = new Date();
		        //充值完成后更新当前可用余额
		        newAccountLogs.setCurbalance(memAccount.getAvlmoney() - Long.valueOf(AmountUtils.changeY2F(transAmt)));
		        newAccountLogs.setState(State.DONE);
		        newAccountLogs.setAddtime(currentDate);
		        newAccountLogs.setOptime(currentDate);
		        
				Integer recid = (Integer)baseDao.insert("pc_mem_account_logs.insert", newAccountLogs);
				//投标detail信息插入此处省略(加工时)
				
				
				//修改标的可投金额
				param.put("lnid", memBidLogs.getLnid());
		    	LoanFrontVo loanFrontVo = baseDao.queryObject("loan_front.investInfo", param);
				loanFrontVo.setAvalmoney( loanFrontVo.getAvalmoney() - Long.valueOf(AmountUtils.changeY2F(transAmt)) );
				
				//修改标的已募集金额
				baseDao.update("loan_front.updateAvlmoney", loanFrontVo);
				
				loanFrontVo.setCollectmoney(loanFrontVo.getCollectmoney() + Long.valueOf(AmountUtils.changeY2F(transAmt)));
				//修改标的已募集金额
				baseDao.update("loan_front.updateCollectmoney", loanFrontVo);
				
			}else{
				
				//将投标记录置为投标失败
				memBidLogs.setState(State.FAIL);
				baseDao.update("pc_mem_bid_logs.updateState", memBidLogs);
				
			}
		}
	}
	
	
	@Override
	public void saveAutoTenderCancel(HuifuAutoTenderCancleVo huifuAutoTenderCancleVo) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("bidordernum", huifuAutoTenderCancleVo.getOrdId());
		param.put("state", State.BIDDONE.toString());
		
		MemBidLogs memBidLogs = baseDao.queryObject("mem_bid_logs.getAll", param);
		if(memBidLogs != null){
			
			//标的状态为撤销状态
			MemBidLogs cancleBidLog = new MemBidLogs();
			cancleBidLog.setMblid(memBidLogs.getMblid());
			cancleBidLog.setState(State.BIDCANCLE);
			baseDao.update("mem_bid_logs.update", cancleBidLog);
			
			//用户可用金额返回
			MemAccount memAccount = baseDao.queryObject("mem_account.getOne", memBidLogs.getMemid());
			if(memAccount!=null){
				
				memAccount.setAvlmoney( memAccount.getAvlmoney() + Long.valueOf( AmountUtils.changeY2F(huifuAutoTenderCancleVo.getTransAmt())) );
				baseDao.update("mem_account.update", memAccount);
				
				//插入一条资金记录
				MemAccountLogs memAccountLogs = new MemAccountLogs();
				memAccountLogs.setLtype(State.MAT5);
				memAccountLogs.setMemid(memBidLogs.getMemid());
				memAccountLogs.setUsrcustid(memBidLogs.getUsrcustid());
				memAccountLogs.setTitle("撤销投标");
				memAccountLogs.setTransmoney(Long.valueOf( AmountUtils.changeY2F(huifuAutoTenderCancleVo.getTransAmt()) ));
				memAccountLogs.setOrdid(memBidLogs.getBidordernum());
				memAccountLogs.setState(State.DONE);
				memAccountLogs.setAddtime(new Date());
				memAccountLogs.setOptime(new Date());
				memAccountLogs.setTrxid(huifuAutoTenderCancleVo.getFreezeTrxId());
				baseDao.insert("pc_mem_account_logs.insert", memAccountLogs);
				
				//撤标详情
			}
			
			//标的可投金额返回
			LoanSearchVO loanSearchVo = new LoanSearchVO();
			loanSearchVo.setLnid(memBidLogs.getLnid());
			LoanInfo loanInfo = baseDao.queryObject("sys_loan.SYS-GET-LOANINFO", loanSearchVo);
			if(loanInfo!=null){
				loanInfo.setAvalmoney(loanInfo.getAvalmoney() + Long.valueOf( AmountUtils.changeY2F(huifuAutoTenderCancleVo.getTransAmt())) );
				loanInfo.setCollectmoney(loanInfo.getCollectmoney() - Long.valueOf( AmountUtils.changeY2F(huifuAutoTenderCancleVo.getTransAmt())) );
				baseDao.update("sys_loan.SYS-UPDATE-CANCLE", loanInfo);
			}
		}
	}
	
	@Override
	public void saveLoans(HuifuLoansBackVo huifuLoansBackVo) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("bidordernum", huifuLoansBackVo.getSubOrdId());
		
		MemBidLogs memBidLogs = baseDao.queryObject("mem_bid_logs.getAll", params);
		if(memBidLogs!=null){
			if(!memBidLogs.getState().equals(State.FKDONE)){
				memBidLogs.setState(State.FKDONE);
				memBidLogs.setOppaytime(new Date());
				baseDao.update("mem_bid_logs.update", memBidLogs);
				
				//标的放款金额 = 已放款金额 + 本次放款金额
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("lnid", memBidLogs.getLnid());
				param.put("transAmt", AmountUtils.changeY2F(huifuLoansBackVo.getTransAmt()));
				
				baseDao.update("sys_loan.SYS-UPDATE-PAYFORMONEY", param);
			}
		}
		
	}
	
	
	@Autowired
	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
}
