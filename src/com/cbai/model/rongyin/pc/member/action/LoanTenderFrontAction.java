package com.cbai.model.rongyin.pc.member.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.service.BaseService;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.springmvc.FormToken;
import com.cbai.model.rongyin.pc.member.service.LoanTenderService;
import com.cbai.model.rongyin.pc.member.vo.MemberVO;


@Controller
@RequestMapping(value="member")
public class LoanTenderFrontAction {
	
	private PropertyUtils propertyUtils;
	
	private BaseService baseService; 

	private LoanTenderService loanTenderFrontService;
	
	
	/**
     * 主动投标 buy*
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "initiativeTender", method = RequestMethod.POST)
	public String initiativeTender(HttpServletRequest request,HttpServletResponse response, 
			String transAmt, Integer lnid, String token, ModelMap model) throws Exception{
		
    	HttpSession session = request.getSession(false);
		String usrCustId = null;
		
		if(session!=null){
			
			boolean b = isRepeatSubmit(request);//判断用户是否是重复提交
			
			
			if(b==true){
				System.out.println("请不要重复提交");
				return "redirect:/error/?msg=error.op";         
			}else{
				//判断标的是否开投了
				
				
				Object tmp = session.getAttribute("memberVO");
				if(tmp!=null){
					
					MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
					usrCustId = memberVO.getUsrcustid();
					
					if(usrCustId!=null){
						
				    	//直接在后台跳转
				    	//String redirectUrl = RequestParamterUtils.NET_GATE_URL + RequestParamterUtils.getUrlParamsByMap(params);
						//response.sendRedirect(redirectUrl);
						Map<String,String> params = loanTenderFrontService.buyInitiativeTender(memberVO.getMemid(), usrCustId, lnid, transAmt);
				    	if(params!=null){
							
				    		model.put("account", params);
				    		
				    		request.getSession().removeAttribute("token");//移除session中的token
					    	return "/front/huifu/initiativeTender";
					     
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
			
		}else{
			return "redirect:/error/?msg=error.op"; 
		} 
	}
    
    /**
     * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
     * @param request
     * @return 
     *         true 用户重复提交了表单 
     *         false 用户没有重复提交表单
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        
    	String client_token = request.getParameter("token");
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if(client_token==null){
            return true;
        }
        
        //取出存储在Session中的token
        String server_token = (String) request.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if(server_token==null){
            return true;
        }
        
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        
        return false;
    }
    
	@Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
    @Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
    
    @Autowired
	public void setLoanTenderFrontService(LoanTenderService loanTenderFrontService) {
		this.loanTenderFrontService = loanTenderFrontService;
	}
    
}
