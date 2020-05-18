package com.cbai.model.rongyin.pc.member.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
 

import com.cbai.common.huifu.QueryUtils;
import com.cbai.common.huifu.RequestParemters;
import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.service.BaseService;
import com.cbai.common.service.SessionContextService;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.CheckIDCard;
import com.cbai.common.utils.OptUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.utils.ValidationUtils;
import com.cbai.common.web.RequestUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;
import com.cbai.model.common.ibatis.entity.MemloginLogs;
import com.cbai.model.rongyin.ibatis.entity.Members; 
import com.cbai.model.rongyin.payment.huifu.vo.HuifuQueryBalanceBgVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuUserInfoBackVo;
import com.cbai.model.rongyin.pc.member.service.MemberService; 
import com.cbai.model.rongyin.pc.member.vo.MemberVO; 

@Controller
public class MemLoginAction {
	
	private BaseService baseService; 
	
	private SessionContextService sessionContextService;
	 
    private PropertyUtils propertyUtils; 
    
    private MemberService memberService;
    
    private QueryUtils queryUtils;
    
    
	/**
	 * 进入会员登录页
	 */
	@RequestMapping(value="/account/member/login", method=RequestMethod.GET)
	public String enter_login(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			
			if(tmp!=null){
				return "redirect:/member/center/";
			} 
		} 
		return "/front/member/login"; 
	}
	
	/**
	 * 会员登录
	 */
	@RequestMapping(value="/account/member/dologin", method=RequestMethod.POST)
	public void do_login(HttpServletRequest request,HttpServletResponse response,String uname,String pwd) throws Exception { 
		
		String  rs = "0";
		
		if(uname!=null && pwd!=null && ValidationUtils.Phone(uname)){
			
			Map param = new HashMap();
			
			param.put("uname", uname);
			
			param.put("pwd", OptUtil.getStringMD5(pwd));
    		
			MemberVO memberVO = baseService.queryObject("mem_login.memLogin", param);
			
			if(memberVO != null){  
				if(memberVO.getState().toString().equals("LOCKED")){ 
					
					rs = "-1" ;  
				}else{
					HttpSession session = request.getSession();
					
					if(memberVO.getRealname()!=null&&!memberVO.getRealname().equals("")){
						memberVO.setSurname(memberVO.getRealname().substring(0, 1)); 
					} 
					session.setAttribute("memberVO",memberVO);
					
					  //删除session里之前登录的信息 
				    if(sessionContextService.hasLogin(memberVO.getPhone())){ 
				    	
					  sessionContextService.forceLogoutMem(memberVO.getPhone());
					  
				    }
				    //把当前登录的信息加入onlineMap
				    sessionContextService.addSession(memberVO.getPhone(),session);
				    
				    /*MemloginLogs mloginLogs = new MemloginLogs(); 
					
					mloginLogs.setPhone(memberVO.getPhone());
					
					mloginLogs.setLgip(OptUtil.getIpAddr(request));
					
					mloginLogs.setIntime(new Date());
					
					mloginLogs.setSessionid(session.getId()); 
					
					baseService.save("mem_login_logs.insert", mloginLogs);*/
					
					if(memberVO.getUsrcustid()==null){
						rs = "-2";  
					}else{
						rs = "1";  
					} 
				} 
			}  
    	}
		
		JsonResultObject results = new JsonResultObject();  
    	
    	results.setCode(rs);
    	
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(results));   
		
	}
	/**
	 *  登录汇付
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/member/account/huifu/login", method=RequestMethod.GET)
	public String huifu_login(ModelMap model,HttpServletRequest request) throws Exception { 
	  
		HttpSession session = request.getSession(false);
		
		if(session != null){  
			
			Object tempMem = session.getAttribute("memberVO"); 
			
			if(tempMem != null){
				MemberVO memberVO = (MemberVO)tempMem;
				
				model.put("account", RequestParemters.userLoginParams(memberVO.getUsrcustid()));
				
				return "/front/huifu/huifuUserlogin";
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
		}else{
			return "redirect:/error/?msg=error.op"; 
		}
			
	}
	
	/**
	 * 用户登出方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String  rs = "0";
		
		HttpSession session = request.getSession(false);
		
		if(session != null){ 
			
			Object tempMem = session.getAttribute("memberVO"); 
			 
			if(tempMem != null){
				
				/*MemloginLogs mloginLogs = new MemloginLogs();
	 	
				mloginLogs.setSessionid(session.getId()); 
				
				mloginLogs.setOuttype(State.MANOUT); 
				
				mloginLogs.setOuttime(new Date());
	 
				baseService.update("mem_login_logs.updateOne" , mloginLogs);*/
	
				session.removeAttribute("memberVO");	
				
				sessionContextService.delSession(((MemberVO)tempMem).getPhone());
				
				rs = "1";
			}   
		} 
		JsonResultObject results = new JsonResultObject();  
    	
    	results.setCode(rs);
    	
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(results));   
	}
	
	/**
	 * 进入会员注册页
	 */
	@RequestMapping(value="/account/member/register", method=RequestMethod.GET)
	public String enter_register(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			
			if(tmp!=null){
				return "redirect:/member/center/";
			} 
		}  
	
		return "/front/member/register";
		
	}
	
	/**
	 * 会员注册
	 */
	@RequestMapping(value="/account/member/doreg", method=RequestMethod.POST)
	public void do_reg(HttpServletRequest request,HttpServletResponse response,String phone,String vcode,String pcode,String pwd,String inpwd,String inphone) throws Exception {
	 
		String  rs = "0";
    	
    	if(phone!=null && vcode!=null && pcode!=null && pwd!=null && inpwd!=null && ValidationUtils.Phone(phone) && !phoneIsExist(phone) && pwd.equals(inpwd)){
    		
    		HttpSession session = request.getSession(false);
        	
        	if(session!=null){
        		
        		String vcode1 = session.getAttribute("regPat") == null ?"":session.getAttribute("regPat").toString();
        		
        		if(vcode.equalsIgnoreCase(vcode1)){ 
        			
        			String pcode1 = session.getAttribute("reg_code") == null ?"":session.getAttribute("reg_code").toString();
        			
        			if((phone+"#"+pcode).equalsIgnoreCase(pcode1)){ 
        				
        				String dadMemid = null;
        				
        				//String dadname = null;
        				
        				String dadtype = null;
        				//邀请码
                		if(inphone!=null && !inphone.equals("")){ 
                			boolean mflag = phoneIsExist(inphone);
                			String uname = baseService.queryObject("mem_login.getSimpleUser", inphone);
                			if( mflag || uname!=null){
                				dadMemid = inphone; 
                				if(uname!=null){
                					dadtype = "1";
                					//dadname = uname;
                				}else if(mflag){
                					dadtype = "2";
                					//dadname = baseService.queryObject("mem_login.getSimpleMem", inphone);
                				}
                			}  
                		}else{
                			dadtype = "1";
                			dadMemid = baseService.queryObject("mem_login.getDefUser", 1);
                			//incode = 666666;
                		} 
                		Members members = new Members();
                		members.setMemnum(BusinessNumberUtil.gainOutBizNoNumber());
            	    	members.setPhone(phone);
            	    	members.setLogpwd(OptUtil.getStringMD5(pwd));
            	    	members.setIfbus(State.N);
            	    	//members.setCfrom(State.valueOf(cfrom)); 
            	    	 
            	    	members.setDadmemid(dadMemid);
            	    	//members.setDadname(dadname);
            	    	members.setDadtype(dadtype);
            	    	
            	    	members.setLpwderrorcount(0);
            	    	members.setPpwderrorcount(0);
            	    	//members.setIfloanman(State.Y); 
            	    	members.setState(State.NORMAL);
            	    	members.setIfbind(State.N);
            	    	members.setIffreeze(State.N);
            	    	members.setRegtime(new Date());
            	    	
            	    	Integer memberId = memberService.saveRegist(members,null);
            	    	
            	    	if(memberId>0){ 
            	        	MemberVO memberVO = new MemberVO();
            	    		if(session != null){
            	    			memberVO.setMemid(memberId);
            	    			memberVO.setMemnum(members.getMemnum());
            	    			memberVO.setPhone(phone);  
            	    			memberVO.setState(members.getState());
            	    			session.setAttribute("memberVO",memberVO);
            	    		}
            	    		rs = "1"; 
            	    	}
        				
        			}    
        		} 
        	} 
    	}  
    	JsonResultObject results = new JsonResultObject(); 
    	 
    	if(rs.equals("1")){
    		results.setMessage(propertyUtils.getPropertiesString("suc.reg"));
    	}else{ 
    		results.setMessage(propertyUtils.getPropertiesString("error.unknow")); 
    	}  
    	
    	results.setCode(rs);
    	
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(results)); 
		 
	}
	 
	/**
	 * 老用户绑定
	 */
	@RequestMapping(value="/account/member/bind", method=RequestMethod.POST)
	public void do_bind(HttpServletRequest request,HttpServletResponse response,String phone,String vcode,String pcode,String pwd,String inpwd,String idnum) throws Exception {
		
		String  rs = "0"; 
		
		String msg = "";
    	
    	if(phone!=null && vcode!=null && pcode!=null && pwd!=null && inpwd!=null && idnum!=null && CheckIDCard.isIdCardNo(idnum.toUpperCase()) && ValidationUtils.Phone(phone) && !phoneIsExist(phone) && !idnumIsExist(idnum) && pwd.equals(inpwd)){
    		
    		HttpSession session = request.getSession(false);
        	
        	if(session!=null){
        		
        		String vcode1 = session.getAttribute("regPat") == null ?"":session.getAttribute("regPat").toString();
        		
        		if(vcode.equalsIgnoreCase(vcode1)){ 
        			
        			String pcode1 = session.getAttribute("reg_code") == null ?"":session.getAttribute("reg_code").toString();
        			
        			if((phone+"#"+pcode).equalsIgnoreCase(pcode1)){ 
        				
        				JsonResultObject result = queryUtils.queryUserInfo(idnum);
        				
        				if(result.getCode().equals("1")){
        					
        					HuifuUserInfoBackVo backVO = (HuifuUserInfoBackVo) result.getReturnObj();
        					
        					if(backVO.getUsrStat().equals("A")){ 
        						rs = "-1"; 
        					    msg = "error.bind.await";
        					}else if(backVO.getUsrStat().equals("C")){
        						rs = "-2";
        						msg = "error.bind.close";
        					}else if(backVO.getUsrStat().equals("D")){
        						rs = "-3";
        						msg = "error.bind.del";
        					}else{
        						
        						JsonResultObject rs1 = queryUtils.queryBalance(backVO.getUsrCustId());
        						
        						if(rs1.getCode().equals("1")){
        							
        							HuifuQueryBalanceBgVo  backVO1 = (HuifuQueryBalanceBgVo) rs1.getReturnObj();
        							
        							Members members = new Members();
                            		members.setMemnum(backVO.getUsrId().substring(4,backVO.getUsrId().length()));
                        	    	members.setPhone(phone);
                        	    	members.setLogpwd(OptUtil.getStringMD5(pwd));
                        	    	members.setCfrom("OLD"); 
                        	    	
                        	    	members.setUsrcustid(backVO.getUsrCustId());
                        	    	members.setUsrcustname(backVO.getUsrId());
                        	    	members.setIdnum(idnum);
                        	    	members.setBirth(CheckIDCard.getBirth(idnum));
                        			members.setSex(CheckIDCard.getSex(idnum));
                        	    	members.setLpwderrorcount(0);
                        	    	members.setPpwderrorcount(0); 
                        	    	members.setState(State.NORMAL);
                        	    	members.setRegtime(new Date()); 
                        	    	
                        	    	Integer memberId = memberService.saveRegist(members,Long.valueOf(AmountUtils.changeY2F(backVO1.getAvlBal())));
                        	    	
                        	    	if(memberId>0){ 
                        	        	MemberVO memberVO = new MemberVO();
                        	    		if(session != null){
                        	    			memberVO.setMemid(memberId);
                        	    			memberVO.setMemnum(members.getMemnum());
                        	    			memberVO.setPhone(phone);  
                        	    			memberVO.setState(State.NORMAL);
                        	    			memberVO.setSex(State.valueOf(members.getSex()));
                        	    			session.setAttribute("memberVO",memberVO);
                        	    		}
                        	    		rs = "1"; 
                        	    	} 
        						} 
        					} 
        				}  
        			}    
        		} 
        	} 
    	}  
    	if(rs.equals("0")){
    		msg = "error.unknow"; 
    		
    	} 
    	JsonResultObject results = new JsonResultObject();  
    	
    	results.setCode(rs);
    	
    	results.setMessage(propertyUtils.getPropertiesString(msg));  
    	
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(results));  
		
	}
	
	/**
	 * 绑定成功,引导用户去从成功页
	 */
	@RequestMapping(value="/account/member/bind/suc/", method=RequestMethod.GET)
	public String to_bindsuc(HttpServletRequest request) throws Exception { 
		
		return "/front/member/bind_suc";
	}
	
	
	/**
	 * 注册成功,引导用户去开户页
	 */
	@RequestMapping(value="/account/member/open/", method=RequestMethod.GET)
	public String to_open(HttpServletRequest request) throws Exception { 
		
		return "/front/member/account_open";
	}
	
	/**
	 * 去汇付开户
	 */
	@RequestMapping(value="/account/member/doopen/", method=RequestMethod.GET)
	public String do_open(HttpServletRequest request,ModelMap model) throws Exception {
		
		HttpSession session = request.getSession(false);	
		
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			
			if(tmp!=null){
				
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				
				model.put("account", RequestParemters.userRegisterParams(propertyUtils.getPropertiesString("UserRegister_BgRetUrl"),
						propertyUtils.getPropertiesString("UserRegister_RetUrl"), memberVO.getMemnum()));
				
				return "/front/huifu/member_regester";
			}else{
				return "redirect:/error/?msg=error.op"; 
			} 
		}else{
			return "redirect:/error/?msg=error.op"; 
		} 
	}
	
	/**
	 * 检查开户状态
	 */
	@RequestMapping(value="/account/member/check_open/", method=RequestMethod.GET)
	public void chek_open(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String  rs = "0";
		
		HttpSession session = request.getSession(false);	
		
		if(session!=null){
			
			Object tmp = session.getAttribute("memberVO");
			
			if(tmp!=null){
				
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				
				MemberVO mem = baseService.queryObject("pc_mem.checkOpen", memberVO.getMemid()); 
				
				if(mem.getUsrcustid()!=null){
					
					memberVO.setUsrcustid(mem.getUsrcustid());  
					
					memberVO.setRealname(mem.getRealname().substring(0, 1));
					
					memberVO.setSex(mem.getSex());
					
					session.setAttribute("memberVO", memberVO);
					
					rs = "1"; 
				} 
			} 
		}  
		JsonResultObject results = new JsonResultObject();  
    	
    	results.setCode(rs);
    	
    	ResponseUtils.renderJson(response, JsonUtils.obj2json(results)); 
	}
	
	/**
	 * 验证电话号码是否存在
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/account/member/reg/checkPhone", method=RequestMethod.POST)
	public void checkRegPhone(HttpServletResponse response,String phone) throws Exception { 
		String flag = null;   
		if(!phoneIsExist(phone)){
			flag = "1";
		}else{
			flag = "0";
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject(flag,null))); 
	}
	
	/**
	 * 验证身份号码是否存在
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/account/member/reg/check_idnum", method=RequestMethod.POST)
	public void check_idnum(HttpServletResponse response,String idnum) throws Exception { 
		String flag = null;   
		if(!idnumIsExist(idnum)){
			flag = "1";
		}else{
			flag = "0";
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject(flag,null))); 
	}
	
	/** 
	 * 验证短信校验码是否正确 
	 */
    @RequestMapping(value="/account/member/reg/check_pcode/", method=RequestMethod.POST)
	public void check_regcode(HttpServletRequest request,HttpServletResponse response,String phone,String pcode) throws Exception { 
		
		JsonResultObject results = new JsonResultObject(); 
		
		results.setCode("0");
		
		if(phone!=null && pcode!=null && ValidationUtils.Phone(phone)){
			
			Object temp = request.getSession().getAttribute("reg_code");
    		
			if(temp !=  null ){
				String checkCode = temp.toString();
				
				if((phone+"#"+pcode).equalsIgnoreCase(checkCode)){
					results.setCode("1");
				} 
			}  
    	}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(results)); 	
	}
	
 
    private boolean phoneIsExist(String phone)throws Exception{
    	 
    	int count = baseService.queryObject("mem_login.phoneIsExist", phone);
    	
    	return count > 0;
    }
     
    private boolean idnumIsExist(String idnum)throws Exception{
   	 
    	int count = baseService.queryObject("mem_login.idnumIsExist", idnum);
    	
    	return count > 0;
    }



	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public void setSessionContextService(SessionContextService sessionContextService) {
		this.sessionContextService = sessionContextService;
	}

	public void setQueryUtils(QueryUtils queryUtils) {
		this.queryUtils = queryUtils;
	}
	
	

}
