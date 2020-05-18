package com.cbai.model.rongyin.pc.member.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.service.BaseService;
import com.cbai.common.utils.TokenProccessor;
import com.cbai.common.utils.UpAndDownUtil; 
import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.ProjectAttach;
import com.cbai.model.rongyin.pc.member.vo.LoanBidLogsVO;
import com.cbai.model.rongyin.pc.member.vo.MemberVO;
import com.cbai.model.rongyin.pc.web.vo.LoanFrontVo;


/**
 * 会员投资项目查询
 */
@Controller
@RequestMapping(value="member")
public class InvestAction {
 
	private BaseService baseService; 
	
	@RequestMapping(value={"invest"}, method=RequestMethod.GET)
	public String invest_index(HttpServletRequest request, ModelMap model)throws Exception {

    	Map<String,Object> parameter = new HashMap<String, Object>();
    	parameter.put("st", 1);
    	parameter.put("lntype", 1);
    	
    	
    	List<LoanFrontVo> loanList = baseService.queryPaginatedList("loan_front.getAll", parameter, 0, 6);
	    model.put("loanList", loanList);
	    
	    
	    parameter.put("lntype", 2);
	    List<LoanFrontVo> xinrenLoanList = baseService.queryPaginatedList("loan_front.getAll", parameter, 0, 1);
	    model.put("xinloanList", xinrenLoanList);
	     
		return "/front/member/invest_index";
	}
	
    
    @RequestMapping(value={"/invest/class_{st:\\d*}"}, method=RequestMethod.GET)
	public String invest_list(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer st,Integer pageNumber, Integer pageSize, ModelMap model) throws Exception  {
		
    	//初始化内容列表分页信息
		if(pageNumber==null || "".equals(pageNumber)){
			pageNumber = 1;
		}
		
		if(pageSize==null || "".equals(pageSize)){
			pageSize = 9;
		}

    	Map<String,Object> parameter = new HashMap<String, Object>();
    	parameter.put("st", st);
    	
    	List<LoanFrontVo> loanList = baseService.queryPaginatedList("loan_front.getAll", parameter, (pageNumber-1) * pageSize, pageSize);
    	if(st == 1){
    		
    		List<LoanFrontVo> xinrenLoanList = new ArrayList<LoanFrontVo>();
    		List<LoanFrontVo> loanLists = new ArrayList<LoanFrontVo>();
    	    
    		for (int i = 0; i < loanList.size(); i++) {
    	    	LoanFrontVo temp = loanList.get(i);
    	    	
    	    	if(temp.getLntype() == 1){
    	    		loanLists.add(temp);
    	    	}
    	    	
    	    	if(temp.getLntype() == 2){
    	    		xinrenLoanList.add(temp);
    	    	}
    	    	
			}
    	    
    	    model.put("xinloanList", xinrenLoanList);
    	    
    	    model.put("loanList", loanLists);
    	    
    	}else{
    		model.put("loanList", loanList);
    	}
    	return "/front/member/invest_list";
    }
    
    
    @RequestMapping(value="/invest/detail/id_{id:\\d*}", method=RequestMethod.GET)
	public String invest_detail(HttpServletRequest request,HttpServletResponse response,
			@PathVariable Integer id, ModelMap model) throws Exception  {
    	
    	
    	Map<String, Object> paramObject = new HashMap<String, Object>();
    	paramObject.put("lnid", id);
    	
    	LoanFrontVo loanFrontVo = baseService.queryObject("loan_front.loanDetail", paramObject);
    	model.put("loan", loanFrontVo);
    	
    	//查询用户可用余额
    	HttpSession session = request.getSession(false);
		if(session!=null){
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				Long avamoney = baseService.queryObject("pc_mem_account.getAvlmoney", memberVO.getMemid());
				
				if(avamoney!=null){
				    model.put("avlmoney", avamoney);
				}else{
					avamoney = 0L;
				}
				
				
				Map<String,Object> bidParam = new HashMap<String, Object>();
				bidParam.put("lnid", id);
				//该标的的有效投资记录
				List<LoanBidLogsVO> bidLogs = baseService.queryList("pc_mem_bid_logs.detailBidlogs", bidParam);
				
				
				String token = TokenProccessor.getInstance().makeToken();//创建令牌
				
				request.getSession().setAttribute("token", token); //在服务器使用session保存token(令牌)
				
				model.put("bidLogs", bidLogs);
				
				return "/front/member/invest_detail";
			
			}else{
				return "redirect:/error/?msg=error.op";
			}
		}else{
			return "redirect:/error/?msg=error.op"; 
		}
        
    }
    
    @RequestMapping(value="/invest/attach/scan/id_{id:\\d*}", method=RequestMethod.GET)
	public void company_attach(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception  {
    	ProjectAttach patt = baseService.queryObject("project_attach.getOne", id);
		UpAndDownUtil.downLoad(request.getSession().getServletContext().getRealPath("/")+patt.getFurl(), patt.getName()+"."+patt.getExt(), response);
	}
    
    @Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

}
