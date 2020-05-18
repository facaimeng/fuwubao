package com.cbai.model.rongyin.pc.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.model.rongyin.pc.member.vo.MemberVO;


/**
 * 我的账户
 */
@Controller
public class MemAccountAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
	
    /**
	 * 进入账户总览
	 */
	@RequestMapping(value={"/member/account/overview","/member/center"}, method=RequestMethod.GET)
	public String enter_overview(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession(false);
    	
    	if(session!=null){
    		
    		Object tmp = session.getAttribute("memberVO");
			
			if(tmp!=null){
				
				MemberVO memberVO = (MemberVO)tmp;
				
				Map param = new HashMap();
	    		
	    		param.put("memid", memberVO.getMemid()); 
	    		
	    		request.setAttribute("avlMoney", baseService.queryObject("pc_mem_account.getAvlmoney", memberVO.getMemid())); 
	    		
	    		param.put("st", 1); 
	    	
	    		request.setAttribute("waitCapital", baseService.queryObject("pc_mem_account.getCapital", param));
	    		
	    		param.put("st", 2); 
	    		
	    		request.setAttribute("freezeCapital", baseService.queryObject("pc_mem_account.getCapital", param));

	    		//request.setAttribute("allCapital", baseService.queryObject("pc_mem_account.getCapital", param)); 
	    		
	    		request.setAttribute("waitProfit", baseService.queryObject("pc_mem_account.getWaitProfit", param)); 
	    		 
	    		request.setAttribute("allProfit", baseService.queryObject("pc_mem_account.getAllProfit", param));
     		
	    		//request.setAttribute("recentList", baseService.queryPaginatedList("pc_mem_bid_logs.getList", param,0,6));
     		
	    		return "/front/member/mem_account";
				
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
    		
    	}else{
			return "redirect:/error/?msg=error.op"; 
		} 
		
		
		
	}
	
	/**
	 * 进入安全中心
	 */
	@RequestMapping(value="/member/account/security", method=RequestMethod.GET)
	public String enter_security() throws Exception {
	
		return "/front/member/mem_safe";
		
	}
	
	/**
	 * 进入推荐好友
	 */
	@RequestMapping(value="/member/account/invite", method=RequestMethod.GET)
	public String enter_invite(HttpServletRequest request,Integer p) throws Exception {
		
		HttpSession session = request.getSession(false);
    	
    	if(session!=null){
    		
    		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    		
    		Pagination page = baseService.queryPagination("pc_mem.getInvite", memberVO.getPhone(), p==null?0:p,6); 
			
			request.setAttribute("page", page);
    		
    		return "/front/member/mem_invite";
    	}else{
    		return "redirect:/error/?msg=error.op"; 
    	} 
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
	

}
