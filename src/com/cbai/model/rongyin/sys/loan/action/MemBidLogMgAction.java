package com.cbai.model.rongyin.sys.loan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.sys.loan.service.LoanMgService;
import com.cbai.model.rongyin.sys.loan.vo.MemBidSearchVO;

@Controller
@RequestMapping(value="/system/bidlog")
public class MemBidLogMgAction {
	
	private BaseService baseService;
	
	private LoanMgService loanMgService;
	
	
	@RequestMapping(value="/enter_search/",method=RequestMethod.GET)
    public String enter_search(ModelMap model,Integer lnid) throws Exception{
		model.put("lnid", lnid);
		return "/admin/loans/list_mem_bidlogs";
    }
	
	@RequestMapping(value="/search/",method=RequestMethod.GET)
    public void search(HttpServletResponse response, Integer pageIndex, Integer limit, MemBidSearchVO memBidSearchVO, ModelMap model) throws Exception{
		SysJsonResultObject result = new SysJsonResultObject();
		
		//memBidSearchVO.setState("BIDDONE");
		Pagination page = baseService.queryPagination("mem_bid_logs.searchAll", memBidSearchVO, 0, 100000);

		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount());
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
    }
    
	/**
	 * 进入标的放款明细页面
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_fangkuan_history/",method=RequestMethod.GET)
    public String enter_fangkuan_history(ModelMap model, Integer lnid) throws Exception{
		model.put("lnid", lnid);
		return "/admin/loans/list_mem_bidlogs_fkhistory";
    }
	
	/**
	 * 放款明细数据加载
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param memBidSearchVO
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fangkuan_history/",method=RequestMethod.GET)
    public void fangkuan_history(HttpServletResponse response, Integer pageIndex, Integer limit, MemBidSearchVO memBidSearchVO,ModelMap model) throws Exception{
		SysJsonResultObject result = new SysJsonResultObject();
		
		//memBidSearchVO.setState("BIDDONE");
		Pagination page = baseService.queryPagination("mem_bid_logs.searchAll", memBidSearchVO, 0, 100000);

		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount());
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
    }
	
	/**
	 * 进入待放款明细页面
	 * @param model
	 * @param lnid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enter_fangkuan_search/",method=RequestMethod.GET)
    public String enter_loans_search(ModelMap model, Integer lnid) throws Exception{
		model.put("lnid", lnid);
		return "/admin/loans/list_mem_bidlogs_fkwaitting";
    }
	
	/**
	 * 待放款投资记录加载
	 * @param response
	 * @param pageIndex
	 * @param limit
	 * @param memBidSearchVO
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fangkuan_waitting/",method=RequestMethod.GET)
	public void fangkuan_waitting(HttpServletResponse response, Integer pageIndex, Integer lnid, Integer limit, ModelMap model) throws Exception{
		
		SysJsonResultObject result = new SysJsonResultObject();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("lnid", lnid);

		//memBidSearchVO.setState("");
		Pagination page = baseService.queryPagination("mem_bid_logs.waittingAll", params, 0, 100000);
		
		result.setRows(page.getList());
		result.setResults(page.getTotalCount());
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
	}
	
	/**
	 * 后台撤销投标操作
	 * @param response
	 * @param mblid
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/autoTenderCancel/",method=RequestMethod.GET)
	public void autoTenderCancel(HttpServletResponse response, Integer mblid, ModelMap model)throws Exception{
		
		JsonResultObject result;
		
		Map<String,Object> paramObject = new HashMap<String, Object>();
		paramObject.put("mblid", mblid);
		
		MemBidLogs memBidLogs = baseService.queryObject("mem_bid_logs.getAll", paramObject);
		
		if(memBidLogs!=null){
			
			//付款完成的状态才能撤销投标
			if(State.BIDDONE.equals(memBidLogs.getState()) && !State.BIDCANCLE.equals(memBidLogs.getState())){
				
				result = loanMgService.saveAutoTenderCancle(memBidLogs);
				ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
				
			}else{
				result = new JsonResultObject();
				result.setCode("0");
				result.setMessage("投标记录已撤单或者未达到测单条件!");
				ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
			}
			
		}else{
			result = new JsonResultObject();
			result.setCode("0");
			result.setMessage("投资记录不存在!");
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		}
	}
	
	
	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	@Autowired
	public void setLoanMgService(LoanMgService loanMgService) {
		this.loanMgService = loanMgService;
	}
	
}
