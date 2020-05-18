package com.cbai.model.rongyin.sys.loan.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.huifu.QueryUtils;
import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.JsonUtil;
import com.cbai.common.web.ResponseUtils;
import com.cbai.common.web.springmvc.FormToken;

import com.cbai.core.web.WebErrors;

import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.CompanyInfo;
import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.LoanMan;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.Projects;

import com.cbai.model.rongyin.sys.loan.service.LoanMgService;
import com.cbai.model.rongyin.sys.loan.vo.LoanRepayPlanDetailVO;
import com.cbai.model.rongyin.sys.loan.vo.LoanRepayPlanVO;
import com.cbai.model.rongyin.sys.loan.vo.LoanSearchVO;
import com.cbai.model.rongyin.sys.users.vo.CompanySearchVO;
import com.cbai.model.rongyin.sys.users.vo.LoanmanSearchVO;

@Controller
@RequestMapping(value="/system/loan")
public class LoanMgAction {
	
	private static final Logger log = LoggerFactory.getLogger(LoanMgAction.class);
	
	public static final SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat sdf14 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat sdf15 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	private BaseService baseService;
	private LoanMgService loanMgService;
	private QueryUtils queryUtils;
	
	/**
	 * 满标停投
	 * @param response
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/fullStop",method=RequestMethod.GET)
	public void fullStop(HttpServletResponse response, Integer lnid) throws Exception{
		
		JsonResultObject result =  loanMgService.saveReplayPlan(lnid);
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result)); 
		//response.sendRedirect("/system/loan/enter_search/?status=CHECKPASS");
		
	}
	
	/**
	 * 流标停投
	 * @param response
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/lossStop",method=RequestMethod.GET)
	public void lossStop(HttpServletResponse response, Integer lnid) throws Exception{
		
		JsonResultObject result = new JsonResultObject();
		
		Map<String,Object> paramObject = new HashMap<String, Object>();
		paramObject.put("lnid", lnid);
		paramObject.put("state", State.BIDDONE);
		
		List<MemBidLogs> bidLogsList = baseService.queryList("pc_mem_bid_logs.allValidBids", paramObject);
		if(bidLogsList!=null && bidLogsList.size()>0){
			result.setCode("0");
			result.setMessage("有在投用户无法流标!");
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result)); 
		}else{
			//设置标的为已完成标的
		    LoanInfo tempInfo = new LoanInfo();
		    tempInfo.setLnid(lnid);
		    tempInfo.setStatus(State.LOSSED);
		    baseService.update("sys_loan.SYS-UPDATE-LOANINFO", tempInfo);
			
		    result.setCode("1");
			result.setMessage("流标完成!");
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result)); 
		}
		
	}
	
	/**
	 * 进入待放款标的页面
	 * @param directtype
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_waitloans_search/",method=RequestMethod.GET)
    public String enter_waitloans_search(String status, ModelMap model) throws Exception{
		model.put("status", status==null||"".equals(status)?"WAITLOAN":status);
    	return "/admin/loans/list_loan_fangkuan";
    }
	
	/**
	 * 查询待放款标的信息
	 * @param directtype
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/waitloans_search/",method=RequestMethod.GET)
    public void waitloans_search(HttpServletResponse response, Integer pageIndex, Integer limit, LoanSearchVO learchVO) throws Exception{
		
		if(learchVO!=null){
			if(learchVO.getMinmoney()!=null && !"".equals(learchVO.getMinmoney())){
				learchVO.setMinmoney(learchVO.getMinmoney() * 100);
			}
			if(learchVO.getMaxmoney()!=null && !"".equals(learchVO.getMaxmoney())){
				learchVO.setMaxmoney(learchVO.getMaxmoney() * 100);
			}
		}
		
		SysJsonResultObject result = new SysJsonResultObject();
		Pagination page = baseService.queryPagination("sys_loan.SYS-GET-LOANINFO", learchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		result.setResults(page.getTotalCount());
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		
    }
	
	/**
	 * 执行放款
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loans/",method=RequestMethod.GET)
    public void noDirecTrf(HttpServletResponse response, Integer mblid, Integer lnid) throws Exception{
		
		JsonResultObject result = loanMgService.saveLoans(mblid, lnid);
		
		if(result!=null){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		}else{
			result = new JsonResultObject();
			result.setCode("0");
			result.setMessage("放款失败!");
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		}
		
	}
	
	
	/**
	 * 查询账户余额
	 * @param response
	 * @param usrCustId
	 * @throws Exception
	 */
	@RequestMapping(value="/search_balance/",method=RequestMethod.GET)
    public void search_banlance(HttpServletResponse response, String usrCustId) throws Exception{
		
		JsonResultObject result = queryUtils.queryBalance(usrCustId);
		if(result!=null){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		}else{
			result = new JsonResultObject();
			result.setCode("0");
			result.setMessage("余额查询失败");
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		}
    }
	
	
	/**
	 * 进入标的列表页面
	 * @param chstate
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_search/",method=RequestMethod.GET)
    public String enter_search(String status, ModelMap model) throws Exception{
		model.put("status", status==null||"".equals(status)?"NOCHECK":status);
    	return "/admin/loans/list_loan";
    }
	
	
	/**
	 * 加载标的列表数据
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param learchVO
	 * @throws Exception
	 */
	@RequestMapping(value="/search/",method=RequestMethod.GET)
    public void search(HttpServletResponse response, Integer pageIndex, Integer limit, LoanSearchVO learchVO) throws Exception{
		
		
		if(learchVO!=null){
			if(learchVO.getMinmoney()!=null && !"".equals(learchVO.getMinmoney())){
				learchVO.setMinmoney(learchVO.getMinmoney() * 100);
			}
			if(learchVO.getMaxmoney()!=null && !"".equals(learchVO.getMaxmoney())){
				learchVO.setMaxmoney(learchVO.getMaxmoney() * 100);
			}
		}
		
		SysJsonResultObject result = new SysJsonResultObject();
		Pagination page = baseService.queryPagination("sys_loan.SYS-GET-LOANINFO", learchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		result.setRows(page.getList());
		result.setResults(page.getTotalCount());
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
    }
	
	
	/**
	 * 进入标的信息发布页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_add/",method=RequestMethod.GET)
    public String enter_add(ModelMap model) throws Exception{
		//查出项目列表中未使用且正常的项目信息
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("state", State.NORMAL);
		
		List<Projects> projects = loanMgService.projectsList(params);
		
		model.put("projects", projects);
		
		model.put("projects_json", JsonUtil.toJson(projects));
		
		CompanySearchVO searchVO = new CompanySearchVO();
		searchVO.setT("WAN");
		searchVO.setState(State.NORMAL);
		model.put("wanlist", baseService.queryList("company_info.getAll", searchVO));
		
		LoanmanSearchVO loanmanVo = new LoanmanSearchVO();
		loanmanVo.setState(State.NORMAL);
		List<LoanMan> loanmans = loanMgService.loanManList(loanmanVo);
		model.put("loanmans", loanmans);
		model.put("loanmans_json", JsonUtil.toJson(loanmans));
		
    	return "/admin/loans/add_loan";
    }
	
	
	/**
	 * 进入标的信息详情页面
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_detail/",method=RequestMethod.GET)
    public String enter_detail(ModelMap model,Integer lnid) throws Exception{
		LoanSearchVO loanVo = new LoanSearchVO();
		loanVo.setLnid(lnid);
		
		model.put("loan", baseService.queryObject("sys_loan.SYS-GET-LOANINFO", loanVo));
    	return "/admin/loans/detail_loan";
    }

	
	/**
	 * 进入标的信息审核页面
	 * @param response
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_check/",method=RequestMethod.GET)
    public String enter_check(HttpServletResponse response, ModelMap model, Integer lnid) throws Exception{
		LoanSearchVO loanVo = new LoanSearchVO();
		loanVo.setLnid(lnid);
		
		model.put("loan", baseService.queryObject("sys_loan.SYS-GET-LOANINFO", loanVo));
    	return "/admin/loans/check_loan";
    }
	
	
	/**
	 * 删除标的信息
	 * @param response
	 * @param model
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/del/",method=RequestMethod.GET)
    public void del(HttpServletResponse response, ModelMap model, Integer lnid) throws Exception{
		JsonResultObject result = new JsonResultObject();
		LoanSearchVO loanVo = new LoanSearchVO();
		loanVo.setLnid(lnid);
		
		LoanInfo loanInfo = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", loanVo);
		
		if(loanInfo != null){
			
			if(loanInfo.getStatus().equals(State.NOCHECK) || loanInfo.getStatus().equals(State.UNPASS)){
				//1、删除标的
				baseService.delete("sys_loan.SYS-DEL-LOANINFO", lnid);
				result.setCode("1");
				result.setMessage("标的删除成功!");
				ResponseUtils.renderJson(response, JsonUtil.toJson(result));
				return;
			}else{
				result.setCode("0");
				result.setMessage("标的信息已发布无法删除!");
				ResponseUtils.renderJson(response, JsonUtil.toJson(result));
				return;
			}
			
		}else{
			result.setCode("0");
			result.setMessage("标的信息不存在!");
			ResponseUtils.renderJson(response, JsonUtil.toJson(result));
			return;
		}
		
    }
	
	
	/**
	 * 审核标的信息,审核通过即为汇付发标成功!
	 * @param response
	 * @param model
	 * @param lnid
	 * @param op
	 * @param checkmemo
	 * @throws Exception
	 */
	@RequestMapping(value="/check/",method=RequestMethod.POST)
    public void check(HttpServletResponse response, ModelMap model, Integer lnid, String op, String checkmemo) throws Exception{
		
		JsonResultObject result = new JsonResultObject();
		LoanSearchVO searchVO = new LoanSearchVO();
		searchVO.setLnid(lnid);
		LoanInfo loanInfo = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", searchVO);
		if(loanInfo != null){
			if("N".equals(op)){
				LoanInfo checkLoanInfo = new LoanInfo();
				checkLoanInfo.setLnid(lnid);
				checkLoanInfo.setStatus(State.UNPASS);
				checkLoanInfo.setCheckmemo(checkmemo);
				
				//修改状态为未通过，不做其他处理
				baseService.update("sys_loan.SYS-UPDATE-LOANINFO", checkLoanInfo);
				
				result.setCode("1");
				result.setMessage("标的审核未通过!");
				ResponseUtils.renderJson(response, JsonUtil.toJson(result));
				return;
			}else if("Y".equals(op)){
				
				loanInfo.setStatus(State.NORMAL);
				loanInfo.setCheckmemo(checkmemo);
				JsonResultObject checkPassResult = loanMgService.updateCheckPass(loanInfo);
				
				ResponseUtils.renderJson(response, JsonUtil.toJson(checkPassResult));
				return;
			}
		}else{
			result.setCode("0");
			result.setMessage("标的信息不存在!");
			ResponseUtils.renderJson(response, JsonUtil.toJson(result));
			return;
		}
    }
	
	
	/**
	 * 进入标的信息修改页面
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_edit/",method=RequestMethod.GET)
    public String enter_edit(ModelMap model, Integer lnid) throws Exception{
		LoanSearchVO loanVo = new LoanSearchVO();
		loanVo.setLnid(lnid);
		
		LoanInfo loanInfo = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", loanVo);
	
		model.put("loan", loanInfo);
		
		//查出项目列表中未使用且正常的项目信息
		Map<String,Object> params = new HashMap<String, Object>();
		//params.put("state", State.NORMAL.toString());
		
		List<Projects> projects = loanMgService.projectsList(params);
		model.put("projects", projects);
		model.put("projects_json", JsonUtil.toJson(projects));
		
		
		CompanySearchVO searchVO = new CompanySearchVO();
		searchVO.setT("WAN");
		model.put("wanlist", baseService.queryList("company_info.getAll", searchVO));
		
		
		LoanmanSearchVO loanmanVo = new LoanmanSearchVO();
		loanmanVo.setState(State.NORMAL);
		List<LoanMan> loanmans = loanMgService.loanManList(loanmanVo);
		model.put("loanmans", loanmans);
		model.put("loanmans_json", JsonUtil.toJson(loanmans));
		
    	return "/admin/loans/edit_loan";
    }
	
	@RequestMapping(value="/loadDirectMan/",method=RequestMethod.GET)
	public void loadDirectMan(HttpServletResponse response, String directtype, String dusrcustid) throws Exception{
		JsonResultObject result = new JsonResultObject();
		
		String dman = baseService.queryObject("loanman.loanManRealname", dusrcustid);
		
		if(dman!= null && !"".equals(dman)){
			result.setCode("1");
			result.setMessage(dman);
		}else{
			result.setCode("0");
			result.setMessage("定向收款人不存在!");
		}
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result)); 
	}
	
	
	/**
	 * 进入到定向划款记录列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_directrflogs_search/",method=RequestMethod.GET)
    public String enter_directrflogs_search(ModelMap model) throws Exception{
		return "/admin/loans/list_loan_directrflogs";
    }
	
	
	/**
	 * 查询可定向划款记录列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/directrflogs_search/",method=RequestMethod.GET)
    public void directrflogs_search(HttpServletResponse response, Integer pageIndex, Integer limit, LoanSearchVO learchVO)throws Exception{
		SysJsonResultObject result = new SysJsonResultObject();
		learchVO.setDirstate("DONE");
		Pagination page = baseService.queryPagination("sys_loan.SYS-GET-LOANINFO", learchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		result.setResults(page.getTotalCount()); 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
    }
	
	
	/**
	 * 进入可定向标的列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_directrf_search/",method=RequestMethod.GET)
    public String enter_directrf_search(ModelMap model) throws Exception{
		return "/admin/loans/list_loan_directrf";
    }
	
	
	/**
	 * 加载可执行定向转账的标的信息(放款完成,筹款完成)
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param learchVO
	 * @throws Exception
	 */
	@RequestMapping(value="/directrf_search/",method=RequestMethod.GET)
    public void directrf_search(HttpServletResponse response, Integer pageIndex, Integer limit, LoanSearchVO learchVO) throws Exception{
		SysJsonResultObject result = new SysJsonResultObject();
		Pagination page = baseService.queryPagination("sys_loan.SYS-GET-DIRECTLOANS", learchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		result.setResults(page.getTotalCount()); 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
	}
	
	
	/**
	 * 跳转进入汇付转账授权页面
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/direcTrfAuth/",method=RequestMethod.GET)
    public String direcTrfAuth(ModelMap model,Integer lnid) throws Exception{
		LoanSearchVO searchVo = new LoanSearchVO();
		searchVo.setLnid(lnid);
		LoanInfo loan = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", searchVo);
	   	
	   	if(loan!=null){
	   		Map<String,String> params = RequestParemters.direcTrfAuthParams(loan.getLusrcustid(), loan.getDusrcustid(), AmountUtils.changeF2Y(String.valueOf(loan.getLoanmoney())));
	   		model.put("account", params);
	   		return "/admin/huifu/direcTrfAuth";
	   		
	   	}else{
	   		model.put("message", "标的信息不存在!");
	   		return "/admin/message";
	   	}
    }
	
	
	/**
	 * 执行资金定向操作
	 * @param model
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/direcTrf/",method=RequestMethod.POST)
    public void direcTrf(HttpServletResponse response, ModelMap model, Integer lnid) throws Exception{
		
		JsonResultObject result;
		LoanSearchVO searchVo = new LoanSearchVO();
		searchVo.setLnid(lnid);
		LoanInfo loanInfo = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", searchVo);
	   	
	   	if(loanInfo!=null){
	   	    //调用接口查询被执行定向人员是否已经授权
	   		result = queryUtils.queryDirecTrfAuth(loanInfo.getLusrcustid());
	   		if(result!=null){
	   			
	   			if("1".equals(result.getCode())){
	   				Long authMoney = Long.valueOf(AmountUtils.changeY2F(result.getMessage()));
	   				
	   				if(authMoney >= loanInfo.getLoanmoney()){
	   					
	   					JsonResultObject result0 = new JsonResultObject();
	   					//调用接口执行资金定向操作
	   					result0 = loanMgService.saveDirecTrf(loanInfo);
	   					
	   					ResponseUtils.renderJson(response, JsonUtils.obj2json(result0));
	   				}else{
	   					result.setCode("0");
	   			   		result.setMessage("授权金额小于本次转账金额!");
	   			   		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
	   				}

	   			}else{
	   				ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
	   			}
	   			
	   		}else{
	   			result = new JsonResultObject();
		   		result.setCode("0");
		   		result.setMessage("查询账号授权失败!");
		   		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
	   		}
	   	}else{
	   		result = new JsonResultObject();
	   		result.setCode("0");
	   		result.setMessage("标的信息不存在!");
	   		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
	   	}
		
    }
	
	
	/**
	 * 进入待还款管理页面
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_repay_waitting/",method=RequestMethod.GET)
    public String enter_repay_waitting(ModelMap model,String status) throws Exception{
		model.put("status", status==null||"".equals(status)?"REPAYWAIT":status);
    	return "/admin/loans/list_loan_repay_waitting";
    }
	
	
	/**
	 * 进入还款历史管理页面
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_repay_history/",method=RequestMethod.GET)
    public String enter_repay_history(ModelMap model,String status) throws Exception{
		model.put("status", status==null||"".equals(status)?"REPAYWAIT":status);
    	return "/admin/loans/list_loan_repay_history";
    }
	
	/**
	 * 加载还款管理页面列表数据
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/replay_search/",method=RequestMethod.GET)
    public void replay_search(HttpServletResponse response, String status, Integer pageIndex, Integer limit) throws Exception{
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		Map<String,Object> param  = new HashMap<String,Object>();
		param.put("status", status);
		
		Pagination page = baseService.queryPagination("sys_loan_repayplan.SYS-GET-LOANREPAY-PLAN", param, pageIndex==null?0:pageIndex, limit==null?15:limit);
		result.setRows(page.getList());
		result.setResults(page.getTotalCount());
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		
    }
	
	/**
	 * 进入还款表单页
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_replay_form/",method=RequestMethod.GET)
    public String enter_replay_form(Integer rpid, ModelMap model) throws Exception{
		
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("rpid", rpid);
        
		LoanRepayPlanVO loanRepayPlanVO = baseService.queryObject("sys_loan_repayplan.SYS-GET-LOANREPAY-PLAN", param);
		model.put("repayPlan", loanRepayPlanVO);
		
		LoanSearchVO loanVo = new LoanSearchVO();
		loanVo.setLnid(loanRepayPlanVO.getLnid());
		
		LoanInfo loan = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", loanVo);
		model.put("loan", loan);
		
		CompanyInfo company =  baseService.queryObject("company_info.getOne", loan.getCmid());
		model.put("company", company);
		model.put("currentDay", new Date());
		
		model.put("rpid", rpid);
		return "/admin/loans/loan_repay_form";
    }
	
	
	/**
	 * 进入还款表单页
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_replay_detail/",method=RequestMethod.GET)
    public String enter_replay_detail(Integer rpid, ModelMap model) throws Exception{
        /*
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("rpid", rpid);
        
		LoanRepayPlanVO loanRepayPlanVO = baseService.queryObject("sys_loan_repayplan.SYS-GET-LOANREPAY-PLAN", param);
		model.put("repayPlan", loanRepayPlanVO);
		
		LoanSearchVO loanVo = new LoanSearchVO();
		loanVo.setLnid(loanRepayPlanVO.getLnid());
		
		LoanInfo loan = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", loanVo);
		model.put("loan", loan);
		
		CompanyInfo company =  baseService.queryObject("company_info.getOne", loan.getCmid());
		model.put("company", company);
		
		model.put("currentDay", new Date());
		*/
		
		model.put("rpid", rpid);
		return "/admin/loans/list_loan_repay_detail";
    }
	
	
	/**
	 * 待还款详情列表数据
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/replay_detail_waitting/",method=RequestMethod.GET)
    public void replay_detail_waitting(HttpServletResponse response, Integer rpid, ModelMap model) throws Exception{

		SysJsonResultObject result = new SysJsonResultObject();
		Map<String,Object> bidLogsParam = new HashMap<String, Object>();
		bidLogsParam.put("rpid", rpid);
		
        Pagination page = baseService.queryPagination("loan_repayplan_detail.SYS-GET-WAITREPAY", bidLogsParam, 0, 50000);
				
		result.setRows(page.getList());
		result.setResults(page.getTotalCount()); 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
    }
	
	
	/**
	 * 待还款详情列表数据
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param lnid
	 * @throws Exception
	 */
	@RequestMapping(value="/replay_detail_history/",method=RequestMethod.GET)
    public void replay_detail_history(HttpServletResponse response, Integer rpid, ModelMap model) throws Exception{

		SysJsonResultObject result = new SysJsonResultObject();
		Map<String,Object> bidLogsParam = new HashMap<String, Object>();
		bidLogsParam.put("rpid", rpid);
		
        Pagination page = baseService.queryPagination("loan_repayplan_detail.SYS-GET-HISTORYREPAY", bidLogsParam, 0, 50000);
				
		result.setRows(page.getList());
		result.setResults(page.getTotalCount()); 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
    }
	
	
	/**
	 * 执行还款
	 * @param response
	 * @param lrpdid 还款明细Id
	 * @param rpid 还款计划Id
	 * @param lusrcustid 扣款人Id
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/sub_repay/",method=RequestMethod.GET)
    public void sub_repay(HttpServletResponse response, Integer lrpdid, String outCustId, ModelMap model) throws Exception{
		
		JsonResultObject result = new JsonResultObject();
        LoanRepayPlanDetailVO repayPlanDetail = baseService.queryObject("loan_repayplan_detail.SYS-WAITREPAY-DETAIL", lrpdid);
        
        if(repayPlanDetail!=null){
	        if( repayPlanDetail.getRealrpmoney() > 0){
	        	result.setCode("0");
				result.setMessage("该还款计划已还清!");
				ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
	        }else{
	        	//执行还款
	        	result = loanMgService.saveRepayment(repayPlanDetail, outCustId, lrpdid);
	        }
		}else{
			result.setCode("0");
			result.setMessage("待还款明细不存在!");
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		}
        
    }
	
	
	/**
	 * 添加标的信息
	 * @throws Exception
	 */
	@FormToken
	@RequestMapping(value="/add/",method=RequestMethod.POST)
    public void add(HttpServletRequest request,HttpServletResponse response,
			String bidName, String bidType,String borrTotAmt,String yearRate,String retInterest,String lastRetDate,
			String bidStartDate,String bidEndDate,String loanPeriod,String retType,String retDate,String guarantType,
			String bidProdType,String riskCtlType,String borrType,
			String borrCustId,String borrName,String borrBusiCode,String borrCertType,String borrCertId,String borrMobiPhone,String borrPurpose,
			
			String proid, String loandeadunit,String waninfo,Integer repaytimes,
			String directtype,String dusrcustid,String ifdirect, String dman, Integer borrManId, Integer lntype, String minimoney, String charges) throws Exception{
		
		
		JsonResultObject result = new JsonResultObject();
		String hfLnId = BusinessNumberUtil.gainOutBizNoNumber();
		
		//后台验证必填项
		WebErrors errors = validateSubmit(request, proid, bidName, bidType,borrTotAmt, yearRate, retInterest, lastRetDate, bidStartDate, 
				bidEndDate, loanPeriod, retType, retDate, bidProdType, borrType, borrCustId, borrName, borrMobiPhone, borrPurpose);
		
		if (!errors.hasErrors()) {
			
			//检测 proId 是否存在
			if(loanMgService.isHfLnidRepeat(hfLnId)){
				result.setCode("0");
				result.setMessage("订单号重复,请重新提交!");
				ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
				return;
			}
			
			LoanInfo loanInfo = new LoanInfo();
			
			//检测资产id是否在系统中,且是否已经使用过了
			if("01".equals(borrType)){
			    //检测这个人在不在系统,能不能借钱
				//borrCustId个人的Id
				loanInfo.setLcertnum(borrCertId);
			}else{
				//检测这个企业在不在系统，能不能借钱
				//borrCustId企业的Id
				loanInfo.setLcertnum(borrBusiCode);
			}
			
			//标的发布成功写入本地标的信息库
			//设置标的信息
			loanInfo.setProid(Integer.valueOf(proid.split("#")[0]));
			loanInfo.setPronum(proid.split("#")[1]);
			loanInfo.setLntype(lntype);
			
			//担保机构ID
			loanInfo.setCmid(Integer.valueOf(waninfo.split("#")[0]));
			//担保机构名称
			loanInfo.setCname(waninfo.split("#")[1]);
			
			//loanInfo.setHfproid(hfProId);
			loanInfo.setLnnum(hfLnId);
			loanInfo.setName(bidName);
			
			loanInfo.setIfdirect(ifdirect);
			loanInfo.setDirecttype(directtype);
			loanInfo.setDusrcustid(dusrcustid);
			loanInfo.setDman(dman);
			
			loanInfo.setBidtype(bidType.split("#")[0]);
			loanInfo.setBidtypename(bidType.split("#")[1]);
			
			//标的金额
			loanInfo.setLoanmoney(Long.valueOf(AmountUtils.changeY2F(borrTotAmt)));
			//可投金额
			loanInfo.setAvalmoney(Long.valueOf(AmountUtils.changeY2F(borrTotAmt)));
			loanInfo.setMinimoney(Long.valueOf(AmountUtils.changeY2F(minimoney)));
			
			loanInfo.setPayformoney(0L);
			//已还金额
			loanInfo.setRepayedmoney(0L);
			//已筹金额
			loanInfo.setCollectmoney(0L);
			
			//BigDecimal t1 = new BigDecimal(yearRate);
			//BigDecimal t2 = new BigDecimal("100");
			//年利率
			//loanInfo.setYearate(t1.divide(t2, 3, BigDecimal.ROUND_HALF_UP).toString());
			loanInfo.setYearate(yearRate);
			
			//应还总利息,本系统无利息所以此处用1分作为数据填充
			loanInfo.setAllinterest(1);
			//借款期限
			loanInfo.setLoandead(Integer.valueOf(loanPeriod));
			
			//接口期限单位:天月年
			loanInfo.setLoandeadunit("天");
			
			//还款方式
			loanInfo.setRepaytype(retType.split("#")[0]);
			loanInfo.setRepaytypename(retType.split("#")[1]);
			//还款次数
			loanInfo.setRepaytimes(repaytimes);
			
			//开始投标时间
			loanInfo.setStartdate(sdf15.parse(bidStartDate));
			//结束投标时间
			loanInfo.setEnddate(sdf15.parse(bidEndDate));
			
			//应还日期   2018/10/31
			//loanInfo.setRepaydate(sdf8.parse(retDate));
			//最后还款日期  2018/10/31
			//loanInfo.setLastrepaydate(sdf8.parse(lastRetDate));
			
			//本息保证
			loanInfo.setWarrtype(guarantType.split("#")[0]);
			loanInfo.setWarrtypename(guarantType.split("#")[1]);
			
			//标的产品编号
			loanInfo.setProtype(bidProdType.split("#")[0]);
			loanInfo.setProtypename(bidProdType.split("#")[1]);
			
			//风控方式编号
			loanInfo.setRisktype(riskCtlType.split("#")[0]);
			loanInfo.setRisktypename(riskCtlType.split("#")[1]);
			
			//逾期是否垫资
			loanInfo.setBidpayfor("2");
			
			//借款人类型
			loanInfo.setLmantype(borrType);
			loanInfo.setLmantypename(("01".equals(borrType))?"个人":"企业");
			loanInfo.setLman(borrName);
			loanInfo.setLmanuid(borrManId);
			
			//借款人汇付ID
			loanInfo.setLusrcustid(borrCustId);
			
			//借款人证件号
			//loanInfo.setLcertnum(borrCertId);
			loanInfo.setLmanphone(borrMobiPhone);
			
			//接口用途
			loanInfo.setUsefor(borrPurpose);
			//设置标的未审核
			loanInfo.setStatus(State.NOCHECK);
			loanInfo.setPubtime(new Date());

			loanInfo.setCharges(charges);
			/*
			Projects projects = new Projects();
			projects.setProid(Integer.valueOf(Integer.valueOf(proid.split("#")[0])));
			projects.setState(State.USED);
			*/
			
			//保存标的信息
			loanMgService.saveLoan(loanInfo, null);
			
			result.setCode("1");
		    result.setMessage("发布完成等待审核!");
		    ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
			return;
			
		}else{
			result.setCode("0");
		    result.setMessage(errors.getErrors().get(0));
	
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
			return;
		}
    }
	
	
	/**
	 * 修改标的信息
	 * @throws Exception
	 */
	@RequestMapping(value="/edit/",method=RequestMethod.POST)
	public void edit(HttpServletRequest request,HttpServletResponse response,
			String bidName, String bidType,String borrTotAmt,String yearRate,String retInterest,String lastRetDate,
			String bidStartDate,String bidEndDate,String loanPeriod,String retType,String retDate,String guarantType,
			String bidProdType,String riskCtlType,String borrType,
			String borrCustId,String borrName,Integer borrManId,String borrBusiCode,String borrCertType,String borrCertId,String borrMobiPhone,String borrPurpose,
			
			String proid, String loandeadunit, Integer oldProid, Integer lnid, 
			String directtype,String dusrcustid,String ifdirect, String dman, Integer lntype, String minimoney, String charges) throws Exception{
		
		JsonResultObject result = new JsonResultObject();
		
		//后台验证必填项
		WebErrors errors = validateSubmit(request, proid, bidName, bidType,borrTotAmt, yearRate, retInterest,lastRetDate, bidStartDate, 
				bidEndDate, loanPeriod, retType, retDate, bidProdType, borrType, borrCustId, borrName, borrMobiPhone, borrPurpose);
		
		if (!errors.hasErrors()) {
			
			//检测资产id是否在系统中,且是否已经使用过了
			LoanInfo loanInfo = new LoanInfo();
			if("01".equals(borrType)){
			    //检测这个人在不在系统,能不能借钱
				//borrCustId个人的Id
				loanInfo.setLcertnum(borrCertId);
			}else{
				//检测这个企业在不在系统，能不能借钱
				//borrCustId企业的Id
				loanInfo.setLcertnum(borrBusiCode);
			}
			
			//标的发布成功写入本地标的信息库
			//设置标的信息
			loanInfo.setLnid(lnid);
			
			loanInfo.setProid(Integer.valueOf(proid.split("#")[0]));
			loanInfo.setPronum(proid.split("#")[1]);
			loanInfo.setLntype(lntype);
			
			loanInfo.setName(bidName);
			loanInfo.setIfdirect(ifdirect);
			loanInfo.setDirecttype(directtype);
			loanInfo.setDusrcustid(dusrcustid);
			loanInfo.setDman(dman);
			
			loanInfo.setBidtype(bidType.split("#")[0]);
			loanInfo.setBidtypename(bidType.split("#")[1]);
			
			//标的金额
			loanInfo.setLoanmoney(Long.valueOf(AmountUtils.changeY2F(borrTotAmt)));
			//可投金额
			loanInfo.setAvalmoney(Long.valueOf(AmountUtils.changeY2F(borrTotAmt)));
			loanInfo.setMinimoney(Long.valueOf(AmountUtils.changeY2F(minimoney)));
			
			//已还金额
			loanInfo.setRepayedmoney(0L);
			
			//BigDecimal t1 = new BigDecimal(yearRate);
			//BigDecimal t2 = new BigDecimal("100");
			//年利率
			//loanInfo.setYearate(t1.divide(t2, 3, BigDecimal.ROUND_HALF_UP).toString());
			loanInfo.setYearate(yearRate);
			//应还总利息
			loanInfo.setAllinterest(1);
			
			//借款期限
			loanInfo.setLoandead(Integer.valueOf(loanPeriod));
			
			//接口期限单位:天月年
			loanInfo.setLoandeadunit("天");
			
			//还款方式
			loanInfo.setRepaytype(retType.split("#")[0]);
			loanInfo.setRepaytypename(retType.split("#")[1]);
			
			//开始投标时间
			loanInfo.setStartdate(sdf15.parse(bidStartDate));
			//结束投标时间
			loanInfo.setEnddate(sdf15.parse(bidEndDate));
			
			//应还日期
			//loanInfo.setRepaydate(sdf8.parse(retDate));
			//最后还款日期
			//loanInfo.setLastrepaydate(sdf8.parse(lastRetDate));
			
			//本息保证
			//loanInfo.setWarrtype(guarantType.split("#")[0]);
			//loanInfo.setWarrtypename(guarantType.split("#")[1]);
			
			//标的产品编号
			loanInfo.setProtype(bidProdType.split("#")[0]);
			loanInfo.setProtypename(bidProdType.split("#")[1]);
			
			//风控方式编号
			//loanInfo.setRisktype(riskCtlType.split("#")[0]);
			//loanInfo.setRisktypename(riskCtlType.split("#")[1]);
			
			//逾期是否垫资
			loanInfo.setBidpayfor("2");
			
			//借款人类型
			loanInfo.setLmantype(borrType);
			loanInfo.setLmantypename(("01".equals(borrType))?"个人":"企业");
			
			loanInfo.setLman(borrName);
			loanInfo.setLmanuid(borrManId);
			
			//借款人汇付ID
			loanInfo.setLusrcustid(borrCustId);
			
			//借款人证件号
			//loanInfo.setLcertnum(borrCertId);
			loanInfo.setLmanphone(borrMobiPhone);
			
			//接口用途
			loanInfo.setUsefor(borrPurpose);
			
			loanInfo.setCharges(charges);
			
			//如果项目id发生改变,则将原来的projects释放
			if(oldProid == Integer.valueOf(proid.split("#")[0])){
				//编辑标的信息
				baseService.update("sys_loan.SYS-UPDATE-LOANINFO", loanInfo);
			}else{
				Projects oldProject = new Projects();
				oldProject.setProid(oldProid);
				oldProject.setState(State.NORMAL);
				loanMgService.updateLoan(loanInfo, null);
			}
			
			result.setCode("1");
		    result.setMessage("编辑完成等待审核!");
		    ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
			return;
			
		}else{
			result.setCode("0");
		    result.setMessage(errors.getErrors().get(0));
	
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
			return;
		}
		
	}
	
	
	private WebErrors validateSubmit(HttpServletRequest request, String proid, String bidName, String bidType,
			String borrTotAmt, String yearRate, String retInterest,String lastRetDate, String bidStartDate, 
			String bidEndDate, String loanPeriod, String retType, String retDate, String bidProdType, 
			String borrType, String borrCustId, String borrName, String borrMobiPhone, String borrPurpose) {

		WebErrors errors = WebErrors.create(request);
		
		if(errors.ifNull(proid, "资产信息")){
			return errors;
		}
		
		if(errors.ifNull(bidName, "标的名称")){
			return errors;
		}
		
		if(errors.ifNull(bidType, "标的类型")){
			return errors;
		}
		
		if(errors.ifNull(borrTotAmt, "标的金额")){
			return errors;
		}
		
		if(errors.ifNull(yearRate, "年化收益")){
			return errors;
		}
		
		if(errors.ifNull(retInterest, "应还款总利息")){
			return errors;
		}
		
		if(errors.ifNull(lastRetDate, "最后还款日期")){
			return errors;
		}
		
		if(errors.ifNull(bidStartDate, "计划投标开始日期")){
			return errors;
		}
		
		if(errors.ifNull(bidEndDate, "计划投标截止日期")){
			return errors;
		}
		
		if(errors.ifNull(loanPeriod, "借款期限")){
			return errors;
		}
		
		if(errors.ifNull(retType, "还款方式")){
			return errors;
		}
		
		if(errors.ifNull(retDate, "应还款日期")){
			return errors;
		}
		
		if(errors.ifNull(bidProdType, "标的产品类型")){
			return errors;
		}
		
		if(errors.ifNull(borrType, "借款人类型")){
			return errors;
		}
		
		if(errors.ifNull(borrCustId, "借款人ID")){
			return errors;
		}
		
		if(errors.ifNull(borrName, "借款人名称")){
			return errors;
		}
		
		if(errors.ifNull(borrMobiPhone, "借款人手机号码")){
			return errors;
		}
		
		if(errors.ifNull(borrPurpose, "借款用途")){
			return errors;
		}
		
		return errors;
	}
	
	
	@Autowired
	public void setLoanMgService(LoanMgService loanMgService) {
		this.loanMgService = loanMgService;
	}
	
	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	@Autowired
	public void setQueryUtils(QueryUtils queryUtils) {
		this.queryUtils = queryUtils;
	}
	
}
