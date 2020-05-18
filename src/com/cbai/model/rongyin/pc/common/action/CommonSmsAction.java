package com.cbai.model.rongyin.pc.common.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.OptUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.utils.SmsUtils;
import com.cbai.common.utils.ValidationUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.ibatis.entity.Smslogs;
  

/**
 * 短信发送接口类
 * 
 * @author
 */
@Controller 
public class CommonSmsAction { 
	
	private BaseService baseService; 
    
	private PropertyUtils propertyUtils;
	 
	
	/**
	 *PC注册短信验证码发送接口
	 * 
	 * @param phone 接收校验码的手机号码
	 * 
	 * @return {"code":"0","message":"错误提示信息!"}
	 * 	       {"code":"1","message":"成功提示信息!"}
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="/sms/reg/", method=RequestMethod.POST)
	public void register(HttpServletRequest request,HttpServletResponse response,String phone,String vcode) throws Exception{
		
		JsonResultObject results = new JsonResultObject(); 
		
		results.setCode("0");
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
    		
    		String validateCode = session.getAttribute("regPat") == null ?"":session.getAttribute("regPat").toString();
    		
    		if(!validateCode.equalsIgnoreCase(vcode)){  
    			
    			results.setCode("-1");
    			
    			results.setMessage(propertyUtils.getPropertiesString("error.valicode"));
    			
    			ResponseUtils.renderJson(response, JsonUtils.obj2json(results));  
    			
    			return;
    		} 
    	} 
		
		if(phone==null || !ValidationUtils.Phone(phone)){ 
			
			results.setCode("-2");
			
			results.setMessage(propertyUtils.getPropertiesString("error.phone"));
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(results)); 
			
			return;
		}

		//判断手机号码是否已经注册若已注册不能发送短信
		if(isRegist(phone)){
			
			results.setCode("-2");
			
			results.setMessage(propertyUtils.getPropertiesString("error.phone.hased"));
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(results)); 
			
			return;
		}
	
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		param.put("type", "REG_CODE");
		
        //发送短信之前先统计当日已经发注册送短信条数
        Integer smsCount = Integer.valueOf(baseService.queryObject("smslogs.getCountByType", param).toString()); 
        
        if(smsCount.intValue() < 6){
        	
        	//随机生成的4位短信验证码  
    		String code = OptUtil.randomNum(4);
    		
    		String rscode = SmsUtils.sendSms(phone, code);
    		
    		if("OK".equals(rscode)){ //若短信发送成功
                
    			Smslogs smslogs = new Smslogs();
            	
            	smslogs.setPhone(phone);// 手机号码  
            	
            	smslogs.setVcode(code);// 短信验证码  
            	
            	smslogs.setType("REG_CODE");
            	
            	smslogs.setSendtime(new Date());// 短信发送时间  

            	baseService.save("smslogs.insert", smslogs); 
            	
            	// 短信验证码存入session(session的默认失效时间30分钟)
 
            	session.setAttribute("reg_code", phone+"#"+code);
            	 
    			results.setCode("1");
        		results.setMessage(propertyUtils.getPropertiesString("suc.op")); 
    		
    		}else{
    			//重新包装短信验证码发送错误的信息
        		results.setMessage(propertyUtils.getPropertiesString("error.unknow"));
    		} 
       
        }else{  
        	
        	results.setMessage(propertyUtils.getPropertiesString("error.op.many")); 
        
        }
        ResponseUtils.renderJson(response, JsonUtils.obj2json(results));
        
        return;
    }
	
	
	/**
	 * APP 端找回密码短信验证码发送接口
	 * 
	 * @param phone 接收校验码的手机号码
	 * 
	 * @return {"code":"0","message":"错误提示信息!"}
	 * 	       {"code":"1","message":"成功提示信息!"}
	 * 
	 * @throws Exception
	 */
	/*public String forget() throws Exception{
		
		MsgDTO results = new MsgDTO();
		
		if(phone==null || "".equals(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.sendmsg.nophone"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;
		}
		
		if(!OptUtil.isMobile(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.sendmsg.formatphone"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;
		}

		//判断手机号码是否已经注册若未注册不能发送短信
		if(!isRegist(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.forget.noregist"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;	
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		param.put("type", "FORGETPWDCODE");
		//发送短信之前先统计当日已经发送短信条数
        Integer smsCount = Integer.valueOf(baseService.queryForObject("smslogs.getCountByType", param).toString()); 
        
        if(smsCount.intValue() < 3){
        	
        	//随机生成的6位短信验证码  
    		String vcode = OptUtil.randomNum();
    		
    		results = SmsUtils.send(phone, String.format(SmsUtils.RESET_PASSWORD, vcode));
    		
    		if("1".equals(results.getCode())){ //若短信发送成功
                
    			Smslogs smslogs = new Smslogs();
            	
            	smslogs.setPhone(phone);// 手机号码  
            	
            	smslogs.setVcode(vcode);// 短信验证码  
            	
            	smslogs.setType("FORGETPWDCODE");
            	
            	smslogs.setSendtime(new Date());// 短信发送时间  

            	baseService.save("smslogs.insert", smslogs); 
            	
            	// 短信验证码存入session(session的默认失效时间30分钟)
            	request.getSession().setAttribute("forget_code", vcode);

                //重新包装短信验证码发送错误的信息
        		results.setMessage(propertyUtils.getPropertiesString("suc.sendmsg"));
    		
    		}else{
    			
    			//重新包装短信验证码发送错误的信息
        		results.setMessage(propertyUtils.getPropertiesString("sms.code"+results.getMessage()));
    		
    		}
    		
    		//接口返回值
    		setHeaderJson(response, JsonUtil.toJson(results));
       
        }else{  
        	
        	setHeaderJson(response,createMessgae("0",propertyUtils.getPropertiesString("error.sendmsg.out"))); 
        
        }

		return null;
    }
	*/
	/**
	 * APP端 用户修改密码短信验证码接口
	 * 
	 * @param phone 接收校验码的手机号码
	 * 
	 * @return {"code":"0","message":"错误提示信息!"}
	 * 	       {"code":"1","message":"成功提示信息!"}
	 * 
	 * @throws Exception
	 */
/*	public String resetpwd() throws Exception{
		
		MsgDTO results = new MsgDTO();
		
		if(phone==null || "".equals(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.sendmsg.nophone"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;
		}
		
		if(!OptUtil.isMobile(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.sendmsg.formatphone"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		param.put("type", "RESETPWDCODE");
		
		//发送短信之前先统计当日已经发送短信条数
        Integer smsCount = Integer.valueOf(baseService.queryForObject("smslogs.getCountByType", param).toString()); 
        
        if(smsCount.intValue() < 3){
        	
        	//随机生成的6位短信验证码  
    		String vcode = OptUtil.randomNum();
    		
    		results = SmsUtils.send(phone, String.format(SmsUtils.RESET_PASSWORD, vcode));
    		
    		if("1".equals(results.getCode())){ //若短信发送成功
                
    			Smslogs smslogs = new Smslogs();
            	
            	smslogs.setPhone(phone);// 手机号码  
            	
            	smslogs.setVcode(vcode);// 短信验证码  
            	
            	smslogs.setType("RESETPWDCODE");
            	
            	smslogs.setSendtime(new Date());// 短信发送时间  

            	baseService.save("smslogs.insert", smslogs); 
            	
            	// 短信验证码存入session(session的默认失效时间30分钟)
            	request.getSession().setAttribute("resetpwd_code", phone+"#"+vcode);

                //重新包装短信验证码发送错误的信息
        		results.setMessage(propertyUtils.getPropertiesString("suc.sendmsg"));
    		
    		}else{
    			//重新包装短信验证码发送错误的信息
        		results.setMessage(propertyUtils.getPropertiesString("sms.code"+results.getMessage()));
    		}
    		
    		//接口返回值
    		setHeaderJson(response, JsonUtil.toJson(results));
       
        }else{  	
        	setHeaderJson(response,createMessgae("0",propertyUtils.getPropertiesString("error.sendmsg.out"))); 
        }

		return null;
	}*/
	

	/**
	 * 修改手机号码短信接口
	 * 
	 * @param phone 新手机号码
	 * 
	 * 
	 * @return {"code":"0","message":"错误提示信息!"}
	 * 	       {"code":"1","message":"成功提示信息!"}
	 * 
	 * @throws Exception
	 */
	/*public String change()  throws Exception{
		
		MsgDTO results = new MsgDTO();
		
		if(phone==null || "".equals(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.sendmsg.nophone"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;
		}
		
		if(!OptUtil.isMobile(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.sendmsg.formatphone"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;
		}

		//判断手机号码是否已经注册若已注册不能发送短信
		if(isRegist(phone)){
			results.setCode("0");
			results.setMessage(propertyUtils.getPropertiesString("error.sendmsg.hasphone"));
			
			setHeaderJson(response, JsonUtil.toJson(results));
			return null;	
		}
		
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		param.put("type", "CHANGEPHONECODE");
		
		//发送短信之前先统计当日已经发送短信条数
        Integer smsCount = Integer.valueOf(baseService.queryForObject("smslogs.getCountByType", param).toString()); 

        if(smsCount.intValue() < 3){
        	
        	//随机生成的6位短信验证码  
    		String vcode = OptUtil.randomNum();
    		
    		results = SmsUtils.send(phone, String.format(SmsUtils.RESET_PHONE, vcode));
    		
    		if("1".equals(results.getCode())){ //若短信发送成功
                
    			Smslogs smslogs = new Smslogs();
            	
            	smslogs.setPhone(phone);// 手机号码  
            	
            	smslogs.setVcode(vcode);// 短信验证码  
                
            	smslogs.setType("CHANGEPHONECODE");
            	
            	smslogs.setSendtime(new Date());// 短信发送时间  

            	baseService.save("smslogs.insert", smslogs); 
            	                                   
            	//短信验证码存入session(session的默认失效时间30分钟)
            	request.getSession().setAttribute("change_code", phone+"#"+vcode);
            	                                   
            	//HttpSession session = request.getSession();
            	//session.setAttribute("app_regcode", vcode);
            	
                //重新包装短信验证码发送错误的信息
        		results.setMessage(propertyUtils.getPropertiesString("suc.sendmsg"));
    		
    		}else{
    			
    			//重新包装短信验证码发送错误的信息
        		results.setMessage(propertyUtils.getPropertiesString("sms.code"+results.getMessage()));
    		
    		}
    		
    		//接口返回值
    		setHeaderJson(response, JsonUtil.toJson(results));
       
        }else{  
        	
        	setHeaderJson(response,createMessgae("0",propertyUtils.getPropertiesString("error.sendmsg.out"))); 
        
        }

		return null;
	}
	*/
	public boolean isRegist(String phone) throws Exception{
		
		int count = baseService.queryObject("mem_login.phoneIsExist", phone);
		
		return count > 0;
	}


	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 
	
}
