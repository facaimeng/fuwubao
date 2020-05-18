package com.cbai.common.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cbai.common.json.JsonResultObject;

/**
 * 创世漫道短信发送工具类
 * 
 * @author 
 */
public class SmsUtils {
	
	private static final Logger log = LoggerFactory.getLogger(SmsUtils.class);
    
	private static final String SN = "DXX-ZCW-010-00002"; //平台编号 如：SDK-BBX-010-00001
	
	private static final String PWD = "25#59#e-";         //平台密码 如：3B5D3C427365F40C1D27682D78BB31E0
	
	//注册短信内容
	public static final String JIHUITISHI_TEMPLETE = "%s,%s:号码%s【志愿家】";
	
	/**
	 * 
	 * 短信群发(统一内容发送)功能
	 * 建议5000条以下
     * 
     * @param mobiles 用逗号隔开的短信接收者号码串 如：139***404,138***213…………….
     * @param content 短信内容
     * 
     * @return
	 * @throws UnsupportedEncodingException 
     */
	public static JsonResultObject send(String mobiles, String content) throws UnsupportedEncodingException {

		Client client=new Client(SN,PWD);
		
		JsonResultObject result = new JsonResultObject();
		
		String result_mt = client.mdSmsSend_u(mobiles, URLEncoder.encode(content,"utf8"), "", "", "");
		
		if(result_mt.startsWith("-")||result_mt.equals("")){ //发送短信，如果是以负号开头就是发送失败。
		    
			log.debug("发送失败！返回值为：{}请查看webservice返回值对照表",result_mt);
			result.setCode("0");
			result.setMessage(result_mt);
		    return result; 
		    
		}else{ //输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
			log.debug("发送成功，返回值为：{}",result_mt);
			
			result.setCode("1");
			result.setMessage(result_mt);
            return result;
            
		}
    
    }
	
	
	/**
	 * 将字符数组组成有指定符号分隔的字符串
	 * 
	 * @param phonenums
	 * @param separator
	 * @return
	 */
	private String phonestr(String[] phonenums, String separator){
		
		StringBuffer strbuf = new StringBuffer();
		
		boolean tags = false;
		
		for(int i = 0; i<phonenums.length; i++){
			
			if(tags){
				strbuf.append( separator );
			}
			
			strbuf.append( phonenums[i] );
			tags = true;
			
		}

		return strbuf.toString();
	}

	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//输入软件序列号和密码
		String sn="DXX-ZCW-010-00002";
		String pwd="25#59#e-";
		
		String mobiles="17783690242";
		String content=URLEncoder.encode("重庆,20171025124:66897【志愿家】", "utf8");	
		
		Client client=new Client(sn,pwd);
		
		String result_mt = client.mdSmsSend_u(mobiles, content, "", "", "");
		
		if(result_mt.startsWith("-")||result_mt.equals("")) //发送短信，如果是以负号开头就是发送失败。
		{
			System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			return;
		}else{//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
			System.out.print("发送成功，返回值为："+result_mt);
		}
		
	}
	
}
