package com.cbai.model.rongyin.sys.loan.service.impl;

import java.net.URLDecoder;
import java.sql.SQLException;
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
import com.cbai.common.utils.DateUtils;
import com.cbai.common.utils.JsonUtil;
import com.cbai.common.utils.MD5Util;
import com.cbai.common.utils.PropertyUtils;

import com.cbai.model.common.data.State;

import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.LoanMan;
import com.cbai.model.rongyin.ibatis.entity.LoanRepayPlan;
import com.cbai.model.rongyin.ibatis.entity.LoanRepayPlanDetail;
import com.cbai.model.rongyin.ibatis.entity.MemAccount;
import com.cbai.model.rongyin.ibatis.entity.MemAccountLogs;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.MemRepaymentDetail;
import com.cbai.model.rongyin.ibatis.entity.Projects;

import com.cbai.model.rongyin.payment.huifu.vo.HuifuAddBidInfoBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoTenderCancleVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuDirecTrfBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuLoansBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuRepaymentBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.LoansReqExt;

import com.cbai.model.rongyin.sys.loan.action.LoanMgAction;
import com.cbai.model.rongyin.sys.loan.service.LoanMgService;
import com.cbai.model.rongyin.sys.loan.vo.LoanRepayPlanDetailVO;
import com.cbai.model.rongyin.sys.loan.vo.LoanSearchVO;
import com.cbai.model.rongyin.sys.loan.vo.MemBidSearchVO;
import com.cbai.model.rongyin.sys.users.vo.LoanmanSearchVO;


public class LoanMgServiceImpl implements LoanMgService{
	
	
	private static final Logger log = LoggerFactory.getLogger(LoanMgServiceImpl.class);
	
	
	private IbatisBaseDao baseDao;
	private HttpClientUtil httpClientUtil;
	private PropertyUtils propertyUtils;
	
	@Override
	public List<Projects> projectsList(Map<String, Object> params)
			throws SQLException {
		return baseDao.queryList("projects.SYS-GET-PROJECTS", params);
	}
	
	@Override
	public List<LoanMan> loanManList(LoanmanSearchVO loanmanSearchVO)
			throws SQLException {
		return baseDao.queryList("loanman.getAll", loanmanSearchVO);
	}
	
    @Override
	public boolean isHfLnidRepeat(String hflnid) throws SQLException {
    	LoanSearchVO searchVO = new LoanSearchVO();
    	searchVO.setLnnum(hflnid);
    	
    	LoanInfo loanInfo = baseDao.queryObject("sys_loan.SYS-GET-LOANINFO", searchVO);
    	return loanInfo != null;
	}
    
    /**
     * 保存标的信息
     * 
     * @param loanInfo标的信息
     * @param projetcs标的所使用到的资产信息
     * 
     */
	@Override
	public void saveLoan(LoanInfo loanInfo, Projects projetcs)
			throws SQLException {
		baseDao.insert("sys_loan.SYS-ADD-LOANINFO", loanInfo);
		if(projetcs!=null){
		    baseDao.update("projects.SYS-UPDATE-PROJECTS", projetcs);
		}
	}
    
    /**
     * 修改标的信息
     * 
     * @param loanInfo 标的对象
     * @param oldProject 标的所使用的资产信息
     */
	@Override
	public void updateLoan(LoanInfo loanInfo, Projects oldProject)
			throws SQLException {
		baseDao.update("sys_loan.SYS-UPDATE-LOANINFO", loanInfo);
		if(oldProject!=null){
		    baseDao.update("projects.SYS-UPDATE-PROJECTS", oldProject);
		}
	}
	
    /**
     * 审核标的信息通过,通过的同时将标的信息调用接口完成汇付正式标的
     * 
     * @param loanInfo 标的对象
     */
	@Override
	public JsonResultObject updateCheckPass(LoanInfo loanInfo) throws SQLException, Exception{
		
		JsonResultObject result = new JsonResultObject();
		
		String bgRetUrl = propertyUtils.getPropertiesString("AddBidInfo_BgRetUrl");
		String charSet = "UTF-8";

		
		//更新审核状态
		LoanInfo updateCheckInfo = new LoanInfo();
		updateCheckInfo.setLnid(loanInfo.getLnid());
		updateCheckInfo.setStatus(loanInfo.getStatus());
		updateCheckInfo.setCheckmemo(loanInfo.getCheckmemo());
		
		
		//构建汇付请求参数(参数已固定)
		Map<String,String> params = RequestParemters.addBidInfoParams(
				loanInfo.getLnnum(), 
				loanInfo.getName(), 
				loanInfo.getBidtype(), 
				AmountUtils.changeF2Y(String.valueOf(loanInfo.getLoanmoney())), 
				loanInfo.getYearate(), 
				AmountUtils.changeF2Y(String.valueOf(loanInfo.getAllinterest())), 
				LoanMgAction.sdf8.format(DateUtils.getDateAfter(loanInfo.getEnddate(), 15)),  //lastRetDate 最后还款日期,格式yyyymmdd
				LoanMgAction.sdf14.format(loanInfo.getStartdate()),  //计划投标开始日期,格式 yyyyMMddHHmmss
				LoanMgAction.sdf14.format(loanInfo.getEnddate()),  // 计划投标截止日期,格式 yyyyMMddHHmmss
				loanInfo.getLoandead() + loanInfo.getLoandeadunit(), //借款期限,例如：XX 天、 XX 月、 XX 年
				loanInfo.getRepaytype(),  //还款方式,01：一次还本付息,02：等额本金,03：等额本息,04：按期付息到期还本,99：其他
				LoanMgAction.sdf8.format(DateUtils.getDateAfter(loanInfo.getEnddate(), 5)),  //应还款日期,格式 yyyymmdd
				loanInfo.getProtype(),//标的产品类型 
				loanInfo.getLmantype(), 
				loanInfo.getLusrcustid(),
				loanInfo.getLcertnum(),  //借款企业营业执照编号,借款人类型为企业时为必填
				"00",  //借款人证件类型00：身份证（暂只支持身份证）借款人类型为“ 01：个人”时为必须参数
				loanInfo.getLcertnum(),
				loanInfo.getLman(),
				loanInfo.getLmanphone(),
				loanInfo.getUsefor(),
				charSet,
				bgRetUrl);
		
		//向汇付发起标的录入信息
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("发标接口请求返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			HuifuAddBidInfoBackVo backinfo = JsonUtil.fromJson(huifuResult, HuifuAddBidInfoBackVo.class);
			if(backinfo!=null){
				if("000".equals(backinfo.getRespCode())){
					//更新标的状态
					baseDao.update("sys_loan.SYS-UPDATE-LOANINFO", updateCheckInfo);

					result.setCode("1");
					result.setMessage(URLDecoder.decode(backinfo.getRespDesc(),"UTF-8"));
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
			result.setMessage("标的接口返回数据格式错误!");
			return result;
		}
	}
	
	@Override
	public JsonResultObject saveDirecTrf(LoanInfo loanInfo) throws Exception {
        JsonResultObject result = new JsonResultObject();
		
		//定向转账订单ID
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();	
    	String transAmt = AmountUtils.changeF2Y(String.valueOf(loanInfo.getLoanmoney()));
    	String bgRetUrl = propertyUtils.getPropertiesString("DirecTrf_BgRetUrl");

    	Map<String,String> params = RequestParemters.direcTrfParams(ordId, 
    			loanInfo.getLusrcustid(), 
    			loanInfo.getDusrcustid(), 
    			transAmt, 
    			bgRetUrl);
    	
    	//修改标的信息定向划款信息
    	LoanInfo updateInfo = new LoanInfo();
    	updateInfo.setLnid(loanInfo.getLnid());
    	updateInfo.setDirordid(ordId);
    	updateInfo.setDirstate(State.DOING);
    	updateInfo.setDirtime(new Date());
    	baseDao.update("sys_loan.SYS-UPDATE-LOANINFO",  updateInfo);
    	
    	//向汇付发起请求
		String huifuResult = httpClientUtil.doPost(params);
        log.debug("接口请求返回值：{}", huifuResult);
        
        if(huifuResult!=null && !"".equals(huifuResult)){
		
        	HuifuDirecTrfBackVo huifuDirecTrfBackVo = JsonUtil.fromJson(huifuResult, HuifuDirecTrfBackVo.class);
			if(huifuDirecTrfBackVo!=null){
				
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
				
				// 查询失败或者 解签 失败 返回null
				if (isOK) {
					
					if("000".equals(huifuDirecTrfBackVo.getRespCode())){
						result.setCode("1");
						result.setMessage("定向划款完成!");
						return result;
					}else{
						result.setCode("0");
						result.setMessage(URLDecoder.decode(huifuDirecTrfBackVo.getRespDesc(),"UTF-8"));
						return result;
					}
					
				}else{
					//删除刚刚
					result.setCode("0");
					result.setMessage("签名验证失败!");
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("定向划款接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("定向划款接口返回数据异常!");
			return result;
		}
	}
    
	
	/**
	 * 满标停投操作：
	 * 计算出标的应该有几条还款计划,每期多少天,每期应还金额
	 * 生成对应的每期还款计划下边的还款详情记录(就是每期还款计划中每个投资者应还本金,利息等)
	 * 
	 * @param lnid标的编号
	 */
	@Override
	public JsonResultObject saveReplayPlan(Integer lnid) throws Exception {
		
		JsonResultObject result = new JsonResultObject();
		
		LoanSearchVO loanSearchVO = new LoanSearchVO();
		loanSearchVO.setLnid(lnid);
		LoanInfo loanInfo = baseDao.queryObject("sys_loan.SYS-GET-LOANINFO", loanSearchVO);
		
		//还款期数
		Long qishu = Long.valueOf(loanInfo.getRepaytimes());
		
		//根据期数构建还款计划还款次数
		Map<String,Long> planMap = new HashMap<String, Long>();
	    for(int i=1; i <= qishu; i++){
	     	planMap.put("p_"+i, 0L);
	    }

	    //根据期数构建还款计划每期的天数
	    Map<String,Integer> dateMap = new HashMap<String, Integer>();
	    if(qishu==1){
    		dateMap.put("d_"+1, loanInfo.getLoandead());
    	}else{
    		for(int i=1; i <= qishu; i++){
    			int dateYushu = loanInfo.getLoandead() % 30; //最后一期余数
    			
    			//30的整数倍天
    			if(dateYushu == 0){
    				dateMap.put("d_" + i, 30);
    	        }else{
    	        	if(i==1){
    	        		dateMap.put("d_" + i, (30 + dateYushu) );
    	        	}else{
    	        		dateMap.put("d_" + i, 30);
    	        	}
    	        }
    	    }
    	}
	    
	    MemBidSearchVO memBidSearch = new MemBidSearchVO();
		memBidSearch.setLnid(lnid);
		memBidSearch.setState(State.BIDDONE.toString());
		
		//获取标的 的投资记录
	    List<MemBidLogs> bigLogList = baseDao.queryList("mem_bid_logs.searchAll", memBidSearch);
	    if(bigLogList!=null &&bigLogList.size()>0){
	    
		    //还款详情
		    Map<String,List<LoanRepayPlanDetail>> planDetailMap = new HashMap<String, List<LoanRepayPlanDetail>>();
		    for(int i=1; i <= qishu; i++){
		    	planDetailMap.put("d_"+i, new ArrayList<LoanRepayPlanDetail>());
		    }
		    
		    for(MemBidLogs memBidLog:bigLogList){
	
		    	Long bidMoney = memBidLog.getBidmoney(); //投资金额	
		        Long repayMoney = 0L; //每期还款额
		        Long yushu = bidMoney % qishu; //最后一期余数
	
		        if(yushu == 0L){
		        	repayMoney = bidMoney / qishu;
		        }else{
		        	repayMoney = (bidMoney-yushu) / qishu;
		        }
		        
		        //List<LoanRepayPlanDetail> detailList = new ArrayList<LoanRepayPlanDetail>();  //还款详情
		    	for(int i=1;i<=qishu; i++){
		    		
		    		LoanRepayPlanDetail detail = new LoanRepayPlanDetail();
		    		detail.setCurperiod(i); //设置期数
		    		detail.setLnid(lnid);
		    		
		    		if(qishu==1 && yushu>0L){
		          		repayMoney = repayMoney + yushu;
		          	}
		          	
		          	//利息公式: 利息=(实际天数/365)*年化收益*本金
		    		String lixiYuan = AmountUtils.calIncome(dateMap.get("d_" + i), AmountUtils.yearRate(loanInfo.getYearate()), AmountUtils.changeF2Y(String.valueOf(repayMoney)));
		    		Long liXi = Long.valueOf( AmountUtils.changeY2F(lixiYuan) );
	
		    		//真正还款的金额=本金+本金在这段时间产生的利息
		    		repayMoney = repayMoney + liXi;
		    		detail.setRpmoney(repayMoney);
		    		detail.setRealrpmoney(0L);
		    		detail.setMblid(memBidLog.getMblid());
		    		detail.setBidordernum(memBidLog.getBidordernum());
		    		detail.setHoldmemid(memBidLog.getMemid());//收款人平台id
		    		detail.setHoldusrcustid(memBidLog.getUsrcustid());//收款人汇付编号
		    		
		          	planMap.put("p_"+i, planMap.get("p_"+i) + repayMoney);
		          	
		          	//放到详情列表中
		          	planDetailMap.get("d_" + i).add(detail);
		          	//planDetailMap.put("d_" + i, planDetailMap.get("d_" + i));
		    	}
		    }
		    
		    for(int i=1; i<=qishu; i++){
		    	
		    	LoanRepayPlan plan = new LoanRepayPlan();
		    	//本次开发只有一次还本付息,qishu只会是1, 所以直接指定一次还款日期 多次还款是可修改
		     	//plan.setPretime(DateUtils.getDateAfter(new Date(),loanInfo.getLoandead()));
		    	
		     	plan.setCurperiod(i);
		     	plan.setLnid(loanInfo.getLnid());
		     	plan.setLnnum(loanInfo.getLnnum());
		     	
		     	//plan.setLman(loanInfo.getLman());
		     	//plan.setLmantype(loanInfo.getLmantype());
		     	//plan.setLusrcustid(loanInfo.getLusrcustid()); //借款人信息
		     	plan.setState(State.UNREP);
		     	plan.setRpmoney(planMap.get("p_"+i));
		     	plan.setCurdates(dateMap.get("d_" + i));
		     	plan.setRealrpmoney(0L);
		     	
		     	plan.setRealrpcount(0); //实际还款条数
		     	List<LoanRepayPlanDetail> detailList = planDetailMap.get("d_"+i);
                
		     	//待还款条目数
		     	plan.setRpcount(detailList==null?0:detailList.size());
		     	
		     	//planList.add(plan);
		     	Integer rpid = (Integer) baseDao.insert("sys_loan_repayplan.SYS-ADD-LOANREPAY-PLAN", plan);

		     	for(LoanRepayPlanDetail detail: detailList){ 
		     	    detail.setRpid(rpid);
		        }
		     	
		        baseDao.batchInsert("loan_repayplan_detail.SYS-ADD-REPALYDETAIL",detailList);
		    }
		    
		    //设置标的 为已完成标的
		    LoanInfo tempInfo = new LoanInfo();
		    tempInfo.setLnid(lnid);
		    tempInfo.setStatus(State.FINISH);
		    baseDao.update("sys_loan.SYS-UPDATE-LOANINFO", tempInfo); 
		    
		    result.setCode("1");
	    	result.setMessage("标的已满标!");
	    	return result;
	    }else{
		    //设置标的 为已完成标的
		    LoanInfo tempInfo = new LoanInfo();
		    tempInfo.setLnid(lnid);
		    tempInfo.setStatus(State.FINISH);
		    baseDao.update("sys_loan.SYS-UPDATE-LOANINFO", tempInfo); 
	    	
		    result.setCode("1");
	    	result.setMessage("标的已满标!");
	    	return result;
	    }

	} 
	
	
	/**
	 * 后台撤销投标操作
	 * 
	 * @param memBidLogs 用户投标记录对象
	 */
	public JsonResultObject saveAutoTenderCancle(MemBidLogs memBidLogs)throws Exception {
		
		JsonResultObject result = new JsonResultObject();

		String unFreezeOrdId = BusinessNumberUtil.gainOutBizNoNumber();
		
    	String bgRetUrl = propertyUtils.getPropertiesString("AutoTenderCancel_BgRetUrl");
    	
		//组装参数发起请求
		Map<String,String> params = RequestParemters.autoTenderCancleParams(memBidLogs.getBidordernum(), memBidLogs.getBidorderdate(), AmountUtils.changeF2Y(String.valueOf(memBidLogs.getBidmoney())), 
			memBidLogs.getUsrcustid(), "Y",unFreezeOrdId, memBidLogs.getFreezetrxid(), bgRetUrl);
		
		//向汇付发起请求
		String huifuResult = httpClientUtil.doPost(params);
		if(huifuResult!=null && !"".equals(huifuResult)){
		
			HuifuAutoTenderCancleVo huifuAutoTenderCancleVo = JsonUtil.fromJson(huifuResult, HuifuAutoTenderCancleVo.class);
			
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
						
						if(memBidLogs.getBidordernum().equals(huifuAutoTenderCancleVo.getOrdId())){
							
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
								//退标后余额
								memAccountLogs.setCurbalance( memAccount.getAvlmoney() + Long.valueOf( AmountUtils.changeY2F(huifuAutoTenderCancleVo.getTransAmt())) );
								
								Date currentDate = new Date();
								memAccountLogs.setAddtime(currentDate);
								memAccountLogs.setOptime(currentDate);
								
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
						
						result.setCode("1");
						result.setMessage("撤销投资成功!");
						return result;
					}else{
						result.setCode("0");
						result.setMessage("撤销失败!" + huifuAutoTenderCancleVo.getRespCode());
						return result;
					}
					
				}else{
					//删除刚刚
					result.setCode("0");
					result.setMessage("签名验证失败!");
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("撤销投资接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("撤销投资接口返回数据异常!");
			return result;
		}	
	}
	
	
	/**
	 * 标的放款处理
	 * @param mblid 用户投标ID
	 * @param lnid 标的编号
	 */
	@Override
	public JsonResultObject saveLoans(Integer mblid, Integer lnid) throws Exception {
		
		JsonResultObject result = new JsonResultObject();
		
		//通过投标ID查询出该投标记录  -- 然后调用放款接口
		MemBidSearchVO searchVo = new MemBidSearchVO();
		searchVo.setMblid(mblid);
		
		MemBidLogs memBidLogs = baseDao.queryObject("mem_bid_logs.searchAll", searchVo);
		if(memBidLogs != null){
            
			//只要不是放款成功的都可以再次放款
			if(!memBidLogs.getState().equals(State.FKDONE)){
				
				memBidLogs.setPaytime(new Date());
	            memBidLogs.setState(State.FKDING); //放款成功后 修改对应的状态: FKDONE 
	            
	            //修改投标记录放款时间
	            baseDao.update("mem_bid_logs.update", memBidLogs);
	            
	            //通过接口放款
	            //result = loans(memBidLogs);
	            
	            //放款订单ID
	    		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
	        	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());	
	        	String unFreezeOrdId = BusinessNumberUtil.gainOutBizNoNumber();
	        	
	        	//放款异步回调处理接口,处理内容与此处处理类容一致
	        	String bgRetUrl = propertyUtils.getPropertiesString("Loans_BgRetUrl");
	    		
	        	LoansReqExt reqExt = new LoansReqExt();
	        	reqExt.setProId(memBidLogs.getLnnum());
	        	
	        	LoanSearchVO searchVO = new LoanSearchVO();
	        	searchVO.setLnid(memBidLogs.getLnid());
	        	
	        	LoanInfo loan = baseDao.queryObject("sys_loan.SYS-GET-LOANINFO", searchVO);
	        	
	        	if(loan == null){
	    			//删除刚刚
	    			result.setCode("0");
	    			result.setMessage("标的信息不存在!");
	    			return result;
	        	}
	        	
	        	int cal_dates = loan.getLoandead().intValue();
	        	String cal_rate = AmountUtils.yearRate(loan.getCharges());
	        	String cal_money = AmountUtils.changeF2Y(String.valueOf(memBidLogs.getBidmoney()));
	        	
	            String fee = AmountUtils.calIncome(cal_dates, cal_rate, cal_money);
	            Map<String,String> params = null;
	            if("0.00".equals(fee)){
	            	//没有平台服务费
	            	params = RequestParemters.loansParamsNoFee(
 	        			ordId,
 	        			ordDate,
 	    				memBidLogs.getUsrcustid(),
 	    				AmountUtils.changeF2Y(String.valueOf(memBidLogs.getBidmoney())),   //投资金额
 	    				fee.toString(),   //手续费
 	    				memBidLogs.getBidordernum(),
 	    				new SimpleDateFormat("yyyyMMdd").format(memBidLogs.getBidtime()),
 	    				memBidLogs.getLusrcustid(),
 	    				unFreezeOrdId,
 	    				memBidLogs.getFreezetrxid(),
 	    				bgRetUrl,
 	    				JsonUtils.obj2json(reqExt));
	            }else{
	                //有平台服务费
	        	    params = RequestParemters.loansParams(
	        			ordId,
	        			ordDate,
	    				memBidLogs.getUsrcustid(),
	    				AmountUtils.changeF2Y(String.valueOf(memBidLogs.getBidmoney())),   //投资金额
	    				fee.toString(),   //手续费
	    				memBidLogs.getBidordernum(),
	    				new SimpleDateFormat("yyyyMMdd").format(memBidLogs.getBidtime()),
	    				memBidLogs.getLusrcustid(),
	    				unFreezeOrdId,
	    				memBidLogs.getFreezetrxid(),
	    				bgRetUrl,
	    				JsonUtils.obj2json(reqExt));
	            }
	        	
	        	//向汇付发起请求
	    		String huifuResult = httpClientUtil.doPost(params);
	    		if(huifuResult!=null && !"".equals(huifuResult)){
	    		
	            	HuifuLoansBackVo huifuLoansBackVo = JsonUtil.fromJson(huifuResult, HuifuLoansBackVo.class);
	    			if(huifuLoansBackVo!=null){
	    				
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
	    				
	    				//StringUtils.trimToEmpty(huifuLoansBackVo.getRespExt()));
	    				String plainStr = buffer.toString();
	    				boolean isOK = SignUtils.verifyByRSA(plainStr, huifuLoansBackVo.getChkValue());
	    				
	    				// 查询失败或者 解签 失败 返回null
	    				if (isOK) {
	    					
	    					if("000".equals(huifuLoansBackVo.getRespCode())){
	    			            
	    						memBidLogs.setOppaytime(new Date());
	    			            memBidLogs.setState(State.FKDONE); //放款成功后将此状态改回: FKDONE
	    			            
	    			            //修改投标记录放款时间
	    			            baseDao.update("mem_bid_logs.update", memBidLogs);
	    			    		
	    			            //第一次执行放款的时候,将修该标的的所有还款计划的计息日期修改为当前日期,预计还款日期更具期数和天数对应修改
	    			    		//获得标的放款时间
	    			    		String loantime = baseDao.queryObject("sys_loan.SYS-GET-LOANTIME", lnid);
	    			    		Date currentDate = new Date();
	    			    		
	    			    		if(loantime==null){
	    			    			
	    			    			LoanInfo loanInfo = new LoanInfo();
	    			    			loanInfo.setLoantime(currentDate);
	    			    			loanInfo.setLnid(lnid);
	    			    			
	    			    			if(loan.getRepaydate()==null){
		    			    			loanInfo.setRepaydate(DateUtils.getDateAfter(currentDate, loan.getLoandead()));
		    			    			loanInfo.setLastrepaydate(DateUtils.getDateAfter(currentDate, loan.getLoandead()));
	    			    			}
	    			    			
	    			    			baseDao.update("sys_loan.SYS-UPDATE-LOANINFO", loanInfo);
	    			    			
	    			    			//得到标的下的所有还款计划
	    			    			List<LoanRepayPlan> replayPlanList = baseDao.queryList("sys_loan_repayplan.SYS-GET-ALLLOANREPAYS", lnid);
	    			    			
	    			    			//List<LoanRepayPlan> replayPlanDateList = new ArrayList<LoanRepayPlan>();
	    			    			//if(replayPlanList!=null && replayPlanList.size()>0){
	    			    			//	for(LoanRepayPlan plan : replayPlanDateList){
	    			    					
	    			    			//		plan.setLoantime(currentDate); //计息日今天
	    			    			//		plan.setPretime(DateUtils.getDateAfter(currentDate, plan.getCurdates())); //预计还款日期
	    			    					
	    			    			//		replayPlanDateList.add(plan);		
	    			    			//	}
	    			    			//}
	    			    			
	    			    			//因为系统是一次还本付息,故一个标的只有只有一期计划所以这样处理,否则按照前面注释掉的多条计划来处理
	    			    			if(replayPlanList!=null && replayPlanList.size() > 0){
	    			    				LoanRepayPlan plan = replayPlanList.get(0);
	    			    				if(plan!=null){
	    			    					LoanRepayPlan tempPlan = new LoanRepayPlan();
	    			    					tempPlan.setRpid(plan.getRpid());
	    			    					tempPlan.setLoantime(currentDate); //计息日今天
	    			    					
	    			    					//该投标记录对应的应还款日期
	    			    					tempPlan.setPretime(DateUtils.getDateAfter(currentDate, plan.getCurdates())); //预计还款日期
	    			    					
	    			    					//修改还款计划的 计息日期
	    			    					baseDao.update("sys_loan_repayplan.SYS-UPDATE-LOANREPAY-PLAN", tempPlan);
	    			    				}
	    			    			}
	    			    			
	    			    			//批量修改还款详情中对应的计息日期
	    			    		}
	    			    		
	    			    		//标的放款金额 = 已放款金额 + 本次放款金额
	    						Map<String,Object> param = new HashMap<String, Object>();
	    						param.put("lnid", memBidLogs.getLnid());
	    						param.put("transAmt", AmountUtils.changeY2F(huifuLoansBackVo.getTransAmt()));
	    						baseDao.update("sys_loan.SYS-UPDATE-PAYFORMONEY", param);
	    			            
	    						//放款完成后, 修改还款计划和计划详情中的 预期还款时间
	    				     	//plan.setPretime(DateUtils.getDateAfter(new Date(),loanInfo.getLoandead()));
	    						
	    						result.setCode("1");
	    						result.setMessage("放款成功!");
	    						return result;
	    						
	    					}else{
	    						
	    			            memBidLogs.setOppaytime(new Date());
	    			            memBidLogs.setState(State.FKFAIL); //放款失败后将此状态改回: BIDDONE
	    			            //修改投标记录放款时间
	    			            baseDao.update("mem_bid_logs.update", memBidLogs);
	    			            
	    						//删除刚刚
	    						result.setCode("0");
	    						result.setMessage(URLDecoder.decode(huifuLoansBackVo.getRespDesc(),"UTF-8"));
	    						return result;
	    					}
	    					
	    				}else{
	    					
	    					//验签失败,说明放款也失败了,状态改回来其它都不变
    			            memBidLogs.setOppaytime(new Date());
    			            memBidLogs.setState(State.FKFAIL); //放款失败后将此状态改回: BIDDONE
    			            //修改投标记录放款时间
    			            baseDao.update("mem_bid_logs.update", memBidLogs);
	    					
	    					//删除刚刚
	    					result.setCode("0");
	    					result.setMessage("签名验证失败!");
	    					return result;
	    				}
	    				
	    			}else{
	    				result.setCode("0");
	    				result.setMessage("放款接口返回数据格式错误!");
	    				return result;
	    			}
	    			
	    		}else{
	    			result.setCode("0");
	    			result.setMessage("放款接口返回数据异常!");
	    			return result;
	    		}
	            
			}else{
				result = new JsonResultObject();
				result.setCode("0");
				result.setMessage("已放款,无法重复放款!");
			}
            
		}else{
			result = new JsonResultObject();
			result.setCode("0");
			result.setMessage("投标记录不存在,无法放款!");
		}
		
		return result;
	}

	
	/**
	 * 调用接口执行放款
	 * @param memBidLogs
	 * @return
	 * @throws Exception
	 */
	private JsonResultObject loans(MemBidLogs memBidLogs) throws Exception{
		
		JsonResultObject result = new JsonResultObject();
		
		//放款订单ID
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
    	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());	
    	String unFreezeOrdId = BusinessNumberUtil.gainOutBizNoNumber();
    	
    	//放款异步回调处理接口,处理内容与此处处理类容一致
    	String bgRetUrl = propertyUtils.getPropertiesString("Loans_BgRetUrl");
		
    	LoansReqExt reqExt = new LoansReqExt();
    	reqExt.setProId(memBidLogs.getLnnum());
    	
    	LoanSearchVO searchVO = new LoanSearchVO();
    	searchVO.setLnid(memBidLogs.getLnid());
    	
    	LoanInfo loan = baseDao.queryObject("sys_loan.SYS-GET-LOANINFO", searchVO);
    	
    	if(loan == null){
			//删除刚刚
			result.setCode("0");
			result.setMessage("标的信息不存在!");
			return result;
    	}
    	
    	int cal_dates = loan.getLoandead().intValue();
    	String cal_rate = AmountUtils.yearRate(loan.getCharges());
    	String cal_money = AmountUtils.changeF2Y(String.valueOf(memBidLogs.getBidmoney()));
    	
        String fee = AmountUtils.calIncome(cal_dates, cal_rate, cal_money);
        
    	Map<String,String> params = RequestParemters.loansParams(
    			ordId,
    			ordDate,
				memBidLogs.getUsrcustid(),
				AmountUtils.changeF2Y(String.valueOf(memBidLogs.getBidmoney())),   //投资金额
				fee.toString(),   //手续费
				memBidLogs.getBidordernum(),
				new SimpleDateFormat("yyyyMMdd").format(memBidLogs.getBidtime()),
				memBidLogs.getLusrcustid(),
				unFreezeOrdId,
				memBidLogs.getFreezetrxid(),
				bgRetUrl,
				JsonUtils.obj2json(reqExt));
    	
    	//向汇付发起请求
		String huifuResult = httpClientUtil.doPost(params);
		if(huifuResult!=null && !"".equals(huifuResult)){
		
        	HuifuLoansBackVo huifuLoansBackVo = JsonUtil.fromJson(huifuResult, HuifuLoansBackVo.class);
			if(huifuLoansBackVo!=null){
				
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
				
				//StringUtils.trimToEmpty(huifuLoansBackVo.getRespExt()));
				String plainStr = buffer.toString();
				boolean isOK = SignUtils.verifyByRSA(plainStr, huifuLoansBackVo.getChkValue());
				
				// 查询失败或者 解签 失败 返回null
				if (isOK) {
					
					if("000".equals(huifuLoansBackVo.getRespCode())){
			            memBidLogs.setOppaytime(new Date());
			            memBidLogs.setState(State.FKDONE); //放款成功后将此状态改回: FKDONE
			            
			            //修改投标记录放款时间
			            baseDao.update("mem_bid_logs.update", memBidLogs);
			            
						
			            //标的放款金额 = 已放款金额 + 本次放款金额
						Map<String,Object> param = new HashMap<String, Object>();
						param.put("lnid", memBidLogs.getLnid());
						param.put("transAmt", AmountUtils.changeY2F(huifuLoansBackVo.getTransAmt()));
						baseDao.update("sys_loan.SYS-UPDATE-PAYFORMONEY", param);
			            
						
						//放款完成后, 修改还款计划和计划详情中的 预期还款时间
				     	//plan.setPretime(DateUtils.getDateAfter(new Date(),loanInfo.getLoandead()));
						
						//修改标的预期还款时间
						result.setCode("1");
						result.setMessage("放款成功!");
						return result;
						
					}else{
						
			            memBidLogs.setOppaytime(new Date());
			            memBidLogs.setState(State.FKFAIL); //放款失败后将此状态改回: BIDDONE
			            //修改投标记录放款时间
			            baseDao.update("mem_bid_logs.update", memBidLogs);
			            
						//删除刚刚
						result.setCode("0");
						result.setMessage(URLDecoder.decode(huifuLoansBackVo.getRespDesc(),"UTF-8"));
						return result;
					}
					
				}else{
					//删除刚刚
					result.setCode("0");
					result.setMessage("签名验证失败!");
					return result;
				}
				
			}else{
				result.setCode("0");
				result.setMessage("放款接口返回数据格式错误!");
				return result;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("放款接口返回数据异常!");
			return result;
		}
	}
	
	/**
	 * 还款操作
	 * @param lnnum 标的编号
	 * @param outCustId 出款人汇付编号
	 * @param lrpdid 还款详情记录ID号
	 */
	@Override
	public JsonResultObject saveRepayment(LoanRepayPlanDetailVO repayPlanDetail, String outCustId, Integer lrpdid) throws Exception {
		
		JsonResultObject result = new JsonResultObject();
		
		String bgRetUrl = propertyUtils.getPropertiesString("Repayment_BgRetUrl");
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
		String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String fee = "0.00";
		
		Date loantime = repayPlanDetail.getLoantime(); //计息日期
		Date pretime = repayPlanDetail.getPretime(); //计划还款日期
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
		//计息日期
		Date loan_date = sdf.parse(sdf.format(loantime));
		
		//计划还款日期
		Date pre_date = sdf.parse(sdf.format(pretime)); 
		
		//真实还款日期
		Date currentDateTime = new Date();
		Date repay_date = sdf.parse(sdf.format(currentDateTime));
		Date repay_date_time = sdf.parse(sdf.format(currentDateTime));
		
		String repay_Lixi = "0.00";  //还款利息
		if(repay_date.before(pre_date)){ 
		    //真实还款时间小于预计还款时间：按日计息
		    //真实投资时长:多少天
		    int realDates = DateUtils.getDaysBetweenDate(loan_date, repay_date);
		    
		    String transAmt = AmountUtils.changeF2Y(String.valueOf(repayPlanDetail.getBidmoney()));
		    
		    String loanRate = AmountUtils.yearRate(repayPlanDetail.getYearate());
			
		    //真实获得的利息
		    repay_Lixi = AmountUtils.calIncome(realDates, loanRate, transAmt);
		}else{
		    //真实还款时间大于等于预计还款时间：按标的天数计息(也就是还款计划时的利息)
			repay_Lixi = AmountUtils.changeF2Y(String.valueOf(repayPlanDetail.getProfit()));
		}
		
		String repay_Benjin = AmountUtils.changeF2Y(String.valueOf(repayPlanDetail.getBidmoney()));
		
		//构建汇付请求参数(参数已固定)
		Map<String,String> params = RequestParemters.repaymentParams(repayPlanDetail.getLnnum(), ordId, ordDate, outCustId, repayPlanDetail.getBidordernum(), 
		    repayPlanDetail.getBidorderdate(), repay_Benjin, repay_Lixi, fee, repayPlanDetail.getHoldusrcustid(), bgRetUrl,String.valueOf(lrpdid));
		
		//向汇付发起还款请求信息
		String huifuResult = httpClientUtil.doPost(params);
		log.debug("发标接口请求返回值：{}", huifuResult);
		
		if(huifuResult!=null && !"".equals(huifuResult)){
			HuifuRepaymentBackVo huifuRepaymentBackVo = JsonUtil.fromJson(huifuResult, HuifuRepaymentBackVo.class);
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
						//本次交易金额
						Long transAmt = Long.valueOf(AmountUtils.changeY2F(huifuRepaymentBackVo.getPrincipalAmt())) + Long.valueOf(AmountUtils.changeY2F(huifuRepaymentBackVo.getInterestAmt()));
						
						String realtime = baseDao.queryObject("sys_loan_repayplan.SYS-GET-REPLAYREALTIME", repayPlanDetail.getRpid());
						if(realtime==null){
							LoanRepayPlan realTimePlan = new LoanRepayPlan();
							realTimePlan.setRpid(repayPlanDetail.getRpid());
							realTimePlan.setRealtime(repay_date_time);
							baseDao.update("sys_loan_repayplan.SYS-UPDATE-LOANREPAY-PLAN", realTimePlan);
						}
						
						//更新还款计划详情中的还款订单号,真实还款金额,真实还款时间
						LoanRepayPlanDetail detail = new LoanRepayPlanDetail();
						detail.setRpordernum(ordId);
						detail.setRealrpmoney(repayPlanDetail.getBidmoney() + repayPlanDetail.getProfit());
						detail.setRealrptime(repay_date);
						detail.setLrpdid(lrpdid);
						baseDao.update("loan_repayplan_detail.SYS-UPDATE-REPALYDETAIL", detail);
						
						//更新还款计划的已还款金额
						Map<String,Object> planMoneyMap = new HashMap<String, Object>();
						planMoneyMap.put("rpid", repayPlanDetail.getRpid());
						planMoneyMap.put("realrpmoney", transAmt);//真实还款金额
						baseDao.update("loan_repayplan_detail.SYS-REPAYPLAN-REALMONEY", planMoneyMap);
						
						//更新标的的已还款金额
						Map<String,Object> loanMoneyMap = new HashMap<String, Object>();
						loanMoneyMap.put("lnid", repayPlanDetail.getLnid());
						loanMoneyMap.put("repayedmoney", transAmt);//真实还款金额
						baseDao.update("loan_repayplan_detail.SYS-LOAN-REALMONEY", loanMoneyMap);
						
						
						Map<String,Object> accountParam = new HashMap<String,Object>();
						accountParam.put("memid", repayPlanDetail.getHoldmemid());
						//回调为用户充值增加金额
						MemAccount memAccount= baseDao.queryObject("pc_mem_account.select", accountParam);
						
						//更新会员账户金额
						Map<String,Object> memberMoneyMap = new HashMap<String, Object>();
						memberMoneyMap.put("memid", repayPlanDetail.getHoldmemid());
						memberMoneyMap.put("allassets", memAccount.getAllassets() + transAmt); //总资产增加
						memberMoneyMap.put("avlmoney", memAccount.getAvlmoney() + transAmt);  //可用余额增加
						baseDao.update("loan_repayplan_detail.SYS-MEMACCOUNT-REALMONEY", memberMoneyMap);
						
						//增加会员资金记录
		                MemAccountLogs accountLogs = new MemAccountLogs();
						accountLogs.setMemid(repayPlanDetail.getHoldmemid());
						accountLogs.setUsrcustid(repayPlanDetail.getHoldusrcustid());
						accountLogs.setLtype(State.MAT4);
						accountLogs.setTitle("项目回款");
						accountLogs.setTransmoney( repayPlanDetail.getBidmoney() + repayPlanDetail.getProfit() );
						accountLogs.setOrdid(ordId);
						accountLogs.setState(State.DONE);
						
						//充值完成后更新当前可用余额
						accountLogs.setCurbalance(memAccount.getAvlmoney() + Long.valueOf(AmountUtils.changeY2F(transAmt)));
						
						accountLogs.setAddtime(repay_date);
						accountLogs.setOptime(repay_date);
						Integer recid = (Integer)baseDao.insert("pc_mem_account_logs.insert", accountLogs);
						
						//增加会员资金记录明细
						MemRepaymentDetail repaymentDetail = new MemRepaymentDetail();
						repaymentDetail.setRecid(recid);
						repaymentDetail.setOrdid(huifuRepaymentBackVo.getOrdId());
						SimpleDateFormat sdf_back = new SimpleDateFormat("yyyyMMdd");
						repaymentDetail.setOrdate(sdf_back.parse(huifuRepaymentBackVo.getOrdDate()));
						repaymentDetail.setOutcustid(huifuRepaymentBackVo.getOutCustId());
						repaymentDetail.setSubordid(huifuRepaymentBackVo.getSubOrdId());
						repaymentDetail.setSubordate(sdf_back.parse(huifuRepaymentBackVo.getSubOrdDate()));
						repaymentDetail.setPrincipalamt(Long.valueOf(AmountUtils.changeY2F(huifuRepaymentBackVo.getPrincipalAmt())));
						repaymentDetail.setInterestamt(Long.valueOf(AmountUtils.changeY2F(huifuRepaymentBackVo.getInterestAmt())));
						repaymentDetail.setFee(Long.valueOf(AmountUtils.changeY2F(fee)));
						repaymentDetail.setAddtime(new Date());
						baseDao.insert("loan_repayplan_detail.SYS-ADD-MEMREPAYMENT-LOGDETAIL", repaymentDetail);
						
						result.setCode("1");
						result.setMessage(URLDecoder.decode(huifuRepaymentBackVo.getRespDesc(),"UTF-8"));
						return result;
					}else{
						//还款失败不改东西
						result.setCode("0");
						result.setMessage(URLDecoder.decode(huifuRepaymentBackVo.getRespDesc(),"UTF-8"));
						return result;
					}
					
				}else{
					result.setCode("0");
					result.setMessage("还款返回签名验证失败!");
					return result;
				}
			}else{
				result.setCode("0");
				result.setMessage("标的接口返回数据格式错误!");
				return result;
			}
		}else{
			result.setCode("0");
			result.setMessage("标的接口返回数据格式错误!");
			return result;
		}
    
	}
    
    @Autowired
	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Autowired
	public void setHttpClientUtil(HttpClientUtil httpClientUtil) {
		this.httpClientUtil = httpClientUtil;
	}
	
	@Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

}
