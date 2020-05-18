package com.cbai.common.utils;  
  
  
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;  
import java.util.Set;
  
  
/** 
 * 邀请码工具类 
 * @author cx 
 * 
 */  
@SuppressWarnings("all")  
public class Sdd {  
      
    private Sdd(){  
          
    }  
    /** 
     * 序列化编码字典 
     * 从左往右 在没有出现扰码前，都为正常序列化编码 
     */   
    
    private static final String SERIALCODEDICT = "E5FCDG3HQA4B1NOPIJ2RSTUV67MWX89KLYZ"; 
  
    /** 
     * 根据ID生成  
     * @param id ID 
     * @return 随机码 
     */  
    public static String toCode(int id,int length) { 
    	
    	StringBuilder sb = new StringBuilder();
    	
    	int num = id; 
	    while ( num > 0) {
	    	int mod = num % 35;
	        num = (num - mod) / 35;
	        sb.insert(0,SERIALCODEDICT.charAt(mod)); 
	    } 
		for(int i=sb.length(); i<length ; i++){
			sb.insert(0, '0');
		}  
        return sb.toString();  
    }  
    
    /** 
     * 根据邀请码生成主键 
     * @param code 
     * @return 主键 
     */  
    public static int deCode(String code) {  
    	
    	int p = code.lastIndexOf("0");  
    	if(p!=-1){
    		code = code.substring(p+1, code.length());
    	}
    	//System.out.println(code);
    	int len = code.length(); 
    	
    	StringBuffer sb = new StringBuffer(code);
    	
    	code = sb.reverse().toString();
        int num = 0;
        for (int i=0; i < len; i++) {
        	num += SERIALCODEDICT.lastIndexOf(code.charAt(i))*Math.pow(35, i); 
        }
        return num; 
    }
  
    
    public static void main(String[] args)  {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		
    	System.out.println(sdf.format(new Date()));
    
    	
    }
}  