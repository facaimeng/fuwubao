package com.cbai.model.rongyin.sys.loan.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.Members;
import com.cbai.model.rongyin.ibatis.entity.ProductOrders;
import com.cbai.model.rongyin.payment.huifu.vo.BorrowerDetails;
import com.cbai.model.rongyin.sys.loan.service.LoanTenderService;
import com.cbai.model.rongyin.sys.loan.vo.LoanSearchVO;

@Controller
@RequestMapping(value="/system/loan")
public class LoanTenderAction {
	
    private LoanTenderService loanTenderService;
	
	private PropertyUtils propertyUtils;
	
	private BaseService baseService;
	
	
    /**
     * 投标记录转债权 
     * 债权转债权
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "autoCreditAssign", method = RequestMethod.GET)
	public void autoCreditAssign(HttpServletResponse response,Integer mblid) throws Exception{
    	Map<String,Object> param = new HashMap<String, Object>();
    	param.put("mblid", mblid);
    	MemBidLogs membidLogs = baseService.queryObject("mem_bid_logs.getAll", param);
    	
    	Members member = new Members();
    	member.setMemid(1);
    	member.setUsrcustid("6000060008971786");
    	
    	loanTenderService.saveAutoCreditAssign(membidLogs, member);
    }
	
	
    /**
     * 自动投标
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "autoTender", method = RequestMethod.GET)
	public void autoTender(HttpServletResponse response,Integer lnid) throws Exception{
    	
    	//借款人信息
    	Members member = new Members();
    	member.setMemid(1);
    	member.setUsrcustid("6000060008971786");
    	
    	//标的信息
    	LoanSearchVO loanSearchVO = new LoanSearchVO();
    	loanSearchVO.setLnid(lnid);
    	LoanInfo loanInfo = baseService.queryObject("sys_loan.SYS-GET-LOANINFO", loanSearchVO);
    	
    	//订单信息
    	ProductOrders productOrder = new ProductOrders();
    	productOrder.setPoid(1);
    	productOrder.setPordernum("201804161534222256630");
    	
    	//借款金额
    	String transAmt = "1000.00";
    	
    	JsonResultObject result = loanTenderService.saveAutoTender(member, loanInfo, productOrder, transAmt);
    	
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(result));
		
	}
    
    
    
    /**
     * 主动投标
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "initiativeTender", method = RequestMethod.POST)
	public String initiativeTenderr(HttpServletResponse response,ModelMap model) throws Exception{
    	
    	//投标订单ID
		String ordId = BusinessNumberUtil.gainOutBizNoNumber();
    	String ordDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
    	String transAmt = "1000.00";
    	String maxTenderRate = "0.00"; //最大投资手续费率
    	
    	String usrCustId = "6000060008971278"; //投资人ID
    	List<BorrowerDetails>  borrowerDetailsList = new ArrayList<BorrowerDetails>();
    	borrowerDetailsList.add(borrowerDetailsList(transAmt));
    	
    	
    	//转换成接口能接受的借款人信息Json串
    	String borrowerDetails = JsonUtils.obj2json(borrowerDetailsList);
    	String isFreeze = "Y";
    	String freezeOrdId = BusinessNumberUtil.gainOutBizNoNumber();
    	String bgRetUrl = propertyUtils.getPropertiesString("AutoTender_BgRetUrl");
    	
    	Map<String,String> params = RequestParemters.initiativeTenderParams(ordId, ordDate, transAmt, usrCustId, maxTenderRate, borrowerDetails, isFreeze, freezeOrdId, bgRetUrl);
    	
    	model.put("account", params);
    	
    	//直接在后台跳转
    	//String redirectUrl = RequestParamterUtils.NET_GATE_URL + RequestParamterUtils.getUrlParamsByMap(params);
		//response.sendRedirect(redirectUrl);
    	
    	return "mobile/huifu/initiativeTender";
	}
    
    
	/**
	 * 每一笔借款资金对应的标的借款人信息
	 * @param transAmt
	 * @return
	 * @throws Exception
	 */
	private BorrowerDetails borrowerDetailsList(String transAmt) throws Exception{
    	
    	BorrowerDetails borrowerInfo = new BorrowerDetails();
    	borrowerInfo.setBorrowerAmt(transAmt); //借款金额
    	borrowerInfo.setBorrowerCustId("6000060008448781"); //借款人客户号
    	borrowerInfo.setBorrowerRate("0.00");  //借款手续费率
    	borrowerInfo.setProId("1804093037641610");  //标的的唯一性标识

    	return borrowerInfo;
	}
	
	
	@Autowired
	public void setLoanTenderService(LoanTenderService loanTenderService) {
		this.loanTenderService = loanTenderService;
	}

	@Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
}
