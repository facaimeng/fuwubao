package com.cbai.model.rongyin.pc.member.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuCashReqExtVo;
import com.cbai.model.rongyin.pc.member.service.MemberService;
import com.cbai.model.rongyin.pc.member.vo.MemberVO;

/**
 * 我的资金
 */
@Controller
public class MemFoundAction {

	private BaseService baseService; 
	
	private PropertyUtils propertyUtils;
	
	private MemberService memberService;
	
	/**
	 * 进入资金记录
	 * @throws Exception 
	 */
	@RequestMapping(value="/member/found/logs", method=RequestMethod.GET)
	public String enter_logs(HttpServletRequest request,Date start,Date end,String dt,String st,Integer p) throws Exception {
	
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
			
			Pagination page = baseService.queryPagination("pc_mem_account_logs.getList", nmap, p==null?0:p,6);
			
			request.setAttribute("page", page);  
			
			return "/front/member/mem_found_logs";
    	}else{
    		return "redirect:/error/?msg=error.op"; 
    	}  
	}
	
	/**
	 * 进入我的卡片
	 */
	@RequestMapping(value="/member/found/cards/", method=RequestMethod.GET)
	public String enter_cards(HttpServletRequest request) throws Exception {
		
        HttpSession session = request.getSession(false);
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				MemberVO memberVO = (MemberVO)tmp;
				String realname = memberVO.getRealname();
				if(realname.length()==2){
					request.setAttribute("mname", "*"+realname.substring(realname.length()-1, realname.length())); 
				}else if(memberVO.getRealname().length()==3){
					request.setAttribute("mname", "**"+realname.substring(realname.length()-1, realname.length())); 
				}else if(memberVO.getRealname().length()==4){
					request.setAttribute("mname", "***"+realname.substring(realname.length()-1, realname.length())); 
				} 
				request.setAttribute("clist", baseService.queryList("pc_mem_account.getCards", memberVO.getMemid())); 
				
				return "/front/member/mem_cards";
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
		}else{
			return "redirect:/error/?msg=error.op"; 
		} 
		
	}
	
	/**
	 * 进入汇付绑卡
	 */
	@RequestMapping(value="/member/found/cards/bind/", method=RequestMethod.GET)
	public String bind_cards(HttpServletRequest request, ModelMap model) throws Exception {
		
        HttpSession session = request.getSession(false);
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				MemberVO memberVO = (MemberVO)tmp;  
				
				Map<String,String> params = RequestParemters.userBindCardParams(memberVO.getUsrcustid(), memberVO.getMemid().toString(), propertyUtils.getPropertiesString("UserBindCard_BgRetUrl"));
				
				model.put("bindInfo", params);
				
				return "/front/huifu/huifuBangKa";
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
		}else{
			return "redirect:/error/?msg=error.op"; 
		} 
		
	}
	
	/**
	 * 进入充值
	 */
	@RequestMapping(value="/member/found/recharge", method=RequestMethod.GET)
	public String enter_recharge(HttpServletRequest request, ModelMap model) throws Exception {
		
        HttpSession session = request.getSession(false);
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				Long avamoney = baseService.queryObject("pc_mem_account.getAvlmoney", memberVO.getMemid());
				
				if(avamoney!=null){
				    model.put("avamoney", avamoney);
				}else{
					avamoney = 0L;
				}
				
				return "/front/member/mem_recharge";
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
		}else{
			return "redirect:/error/?msg=error.op"; 
		} 
		
	}
	
	/**
	 * 充值操作
	 */
	@RequestMapping(value="/member/found/do_recharge", method=RequestMethod.POST)
	public String do_recharge(HttpServletRequest request, String transAmt, ModelMap model) throws Exception { 
		
		HttpSession session = request.getSession(false);
		String usrCustId = null;
		
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				usrCustId = memberVO.getUsrcustid();
				
				if(usrCustId!=null){
					String ordId = BusinessNumberUtil.gainNumber();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					
					Map<String,String> params = RequestParemters.netSaveParams(usrCustId, ordId, sdf.format(new Date()), AmountUtils.changeF2Y(AmountUtils.changeY2F(transAmt)),
						propertyUtils.getPropertiesString("NetSave_BgRetUrl"));
					
					model.put("netSaveInfo", params);
					return "front/huifu/huifuChongzhi";
				}else{
					return "redirect:/error/?msg=error.op"; 
				}
			
			}else{
				return "redirect:/error/?msg=error.op"; 
			}
		}else{
			return "redirect:/error/?msg=error.op"; 
		}
	}
	
	
	
	/**
	 * 进入提现
	 */
	@RequestMapping(value="/member/found/cashout", method=RequestMethod.GET)
	public String enter_cashout(HttpServletRequest request, ModelMap model) throws Exception {
        
		HttpSession session = request.getSession(false);
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				Long avamoney = baseService.queryObject("pc_mem_account.getAvlmoney", memberVO.getMemid());
				
				if(avamoney!=null){
				    model.put("avamoney", avamoney);
				}else{
					avamoney = 0L;
				}
				
				return "/front/member/mem_cashout";
			}else{
				return "redirect:/error/?msg=error.op";
			}
		}else{
			return "redirect:/error/?msg=error.op"; 
		}
		
	}
	
	/**
	 * 提现操作
	 */
	@RequestMapping(value="/member/found/do_cashout", method=RequestMethod.POST)
	public String do_cashout(HttpServletRequest request,String transAmt, String cashChl, ModelMap model) throws Exception {
	    
		HttpSession session = request.getSession(false);
		
		String usrCustId = null;
		
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			if(tmp!=null){
				
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				usrCustId = memberVO.getUsrcustid();
				
				if(usrCustId!=null){
					
					//查询可用余额, 可用余额大于提现金额方可提现
					Long avlmoney = baseService.queryObject("pc_mem_account.getAvlmoney", memberVO.getMemid());
					
					if(avlmoney!=null){
						
						if(avlmoney > Long.valueOf(AmountUtils.changeY2F(transAmt))){

							String ordId = BusinessNumberUtil.gainNumber();
							List<HuifuCashReqExtVo> reqExtList = new ArrayList<HuifuCashReqExtVo>();
							
							if(cashChl.equals("GENERAL")){//一般取款
								HuifuCashReqExtVo reqExt = new HuifuCashReqExtVo();
								reqExt.setFeeObjFlag("M");
								reqExt.setFeeAcctId(RequestParemters.merCustId_ChildAcct);
								reqExt.setCashChl(cashChl);
								reqExt.setCashFeeDeductType("I");
								reqExtList.add(reqExt);
							}else{
								HuifuCashReqExtVo reqExt = new HuifuCashReqExtVo();
								reqExt.setFeeObjFlag("U");
								reqExt.setFeeAcctId(RequestParemters.merCustId_ChildAcct);
								reqExt.setCashChl(cashChl);
								reqExt.setCashFeeDeductType("I");
								reqExtList.add(reqExt);
							}
							
							String reqExtStr = JsonUtils.obj2json(reqExtList);
							Map<String,String> params = RequestParemters.cashParams(
									ordId, usrCustId, AmountUtils.changeF2Y(AmountUtils.changeY2F(transAmt)), reqExtStr,
									propertyUtils.getPropertiesString("CashOut_BgRetUrl"));
							
							//生成加密串之后,为了在提交页面正常显示Json串中的引号(")问题,处理做处理[注意签名之后再行处理]
							params.put("ReqExt", params.get("ReqExt").replaceAll("\"", "&quot;"));
							model.put("cashInfo", params);
							
							//增加提现记录,修改可用金额
							//memberService.saveCashOut(usrCustId, transAmt, ordId);
							
							return "front/huifu/huifuTixian";
						}else{
							return "redirect:/error/?msg=error.op"; 
						}
					}else{
						return "redirect:/error/?msg=error.op"; 
					}
				}else{
					return "redirect:/error/?msg=error.op"; 
				}
			}else{
				return "redirect:/error/?msg=error.op"; 
			}
		}else{
			return "redirect:/error/?msg=error.op"; 
		}
	}
	
	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	@Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
}
