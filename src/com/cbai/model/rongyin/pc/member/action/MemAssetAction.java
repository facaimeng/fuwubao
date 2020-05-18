package com.cbai.model.rongyin.pc.member.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.model.rongyin.pc.member.vo.MemberVO;

/**
 * 我的资产
 */
@Controller
public class MemAssetAction {

	private BaseService baseService; 
	
	/**
	 * 进入购买记录
	 */
	@RequestMapping(value="/member/asset/orders", method=RequestMethod.GET)
	public String enter_orders(HttpServletRequest request,Date start,Date end,String dt,String st,Integer p) throws Exception {
		
		HttpSession session = request.getSession(false);
    	
    	if(session!=null){
    		
    		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		
			st = (st == null || st.equals(""))?"0":st;
			
			Map<String,Object> nmap = new HashMap<String,Object>(); 
			
			nmap.put("memid", memberVO.getMemid());
			
			nmap.put("start", start);
			
			nmap.put("end", end);
			
			nmap.put("dt", dt);
			
			nmap.put("st", st);
			
			request.setAttribute("st", st);
			
			Pagination page = baseService.queryPagination("pc_mem_bid_logs.getList", nmap, p==null?0:p,6); 
			
			request.setAttribute("page", page);
			 
			return "/front/member/mem_order_list";
    	}else{
    		return "redirect:/error/?msg=error.op"; 
    	} 
	}
	
	/**
	 * 进入收益记录
	 */
	@RequestMapping(value="/member/asset/profits", method=RequestMethod.GET)
	public String enter_profits(HttpServletRequest request,Date start,Date end,String dt,String st,Integer p) throws Exception {
		
		HttpSession session = request.getSession(false);
    	
    	if(session!=null){
    		
    		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		
			st = (st == null || st.equals(""))?"0":st;
			
			Map<String,Object> nmap = new HashMap<String,Object>(); 
			
			nmap.put("memid", memberVO.getMemid());
			
			nmap.put("start", start);
			
			nmap.put("end", end);
			
			nmap.put("dt", dt);
			
			nmap.put("st", st);
			
			request.setAttribute("st", st);
			
			Pagination page = baseService.queryPagination("pc_mem_bid_logs.getList", nmap, p==null?0:p,6); 
			
			request.setAttribute("page", page);
			 
			return "/front/member/mem_profit_list";
    	}else{
    		return "redirect:/error/?msg=error.op"; 
    	}  
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	
	 
}
