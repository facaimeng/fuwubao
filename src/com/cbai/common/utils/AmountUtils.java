package com.cbai.common.utils;

import java.math.BigDecimal;    
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**  
 * com.util.AmountUtils  
 * @description  金额元分之间转换工具类  
 * @author zcm0708@sina.com  
 * @2012-2-7下午12:58:00  
 */    
public class AmountUtils {    
        
    /**金额为分的格式 */    
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";    
        
    /**   
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）  
     *   
     * @param amount  
     * @return  
     * @throws Exception   
     */    
    public static String changeF2Y(Long amount) throws Exception{    
        if(!amount.toString().matches(CURRENCY_FEN_REGEX)) {    
            throw new Exception("金额格式有误");    
        }    
            
        int flag = 0;    
        String amString = amount.toString();    
        if(amString.charAt(0)=='-'){    
            flag = 1;    
            amString = amString.substring(1);    
        }    
        StringBuffer result = new StringBuffer();    
        if(amString.length()==1){    
            result.append("0.0").append(amString);    
        }else if(amString.length() == 2){    
            result.append("0.").append(amString);    
        }else{    
            String intString = amString.substring(0,amString.length()-2);    
            for(int i=1; i<=intString.length();i++){    
                if( (i-1)%3 == 0 && i !=1){    
                    result.append(",");    
                }    
                result.append(intString.substring(intString.length()-i,intString.length()-i+1));    
            }    
            result.reverse().append(".").append(amString.substring(amString.length()-2));    
        }    
        if(flag == 1){    
            return "-"+result.toString();    
        }else{    
            return result.toString();    
        }    
    }    
        
    /**  
     * 将分为单位的转换为元 （除100）  
     *   
     * @param amount  
     * @return  
     * @throws Exception   
     */    
    public static String changeF2Y(String amount) throws Exception{    
        if(!amount.matches(CURRENCY_FEN_REGEX)) {    
            throw new Exception("金额格式有误");    
        }    
        
        // 保留两位小数  
        DecimalFormat formater = new DecimalFormat("0.00");  
        
        BigDecimal decimal = BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100));
        
        // 四舍五入  
        //formater.setRoundingMode(RoundingMode.HALF_UP);   // 5000008.89  
        formater.setRoundingMode(RoundingMode.HALF_DOWN);   // 5000008.89
        //formater.setRoundingMode(RoundingMode.HALF_EVEN);    
  
        // 格式化完成之后得出结果  
        String formatNum = formater.format(decimal);
        
        return formatNum;    
    }    
        
    /**   
     * 将元为单位的转换为分 （乘100）  
     *   
     * @param amount  
     * @return  
     */    
    public static String changeY2F(Long amount){    
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();    
    }    
        
    /**   
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额  
     *   
     * @param amount  
     * @return  
     */    
    public static String changeY2F(String amount){    
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额    
        int index = currency.indexOf(".");    
        int length = currency.length();    
        Long amLong = 0l;    
        if(index == -1){    
            amLong = Long.valueOf(currency+"00");    
        }else if(length - index >= 3){    
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));    
        }else if(length - index == 2){    
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);    
        }else{    
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");    
        }    
        return amLong.toString();    
    }    
    
    /**
     * 年化收益率转换成用来计算的利率
     * @param yearRate
     * @return
     */
    public static String yearRate(String yearRate){
    	
		BigDecimal step1 = new BigDecimal(yearRate);
		
		BigDecimal step2 = new BigDecimal("100");
		
		return  step1.divide(step2, 4, BigDecimal.ROUND_HALF_UP).toString();
		
    }
    
	/**
	 * 计算投资所得利息
	 * 利息公式: 利息=(实际天数/365)*年化收益*本金
	 * @param dates 实际天数
	 * @param rate 年化收益率
	 * @param money 本金
	 * @return
	 */
    public static String calIncome(int dates, String rate, String money){
		
		BigDecimal b1 = new BigDecimal(dates);
		BigDecimal b2 = new BigDecimal("365");
		
		BigDecimal b3 = new BigDecimal(rate);
		BigDecimal b4 = new BigDecimal(money);
		
		BigDecimal step1= b1.divide(b2, 8, BigDecimal.ROUND_DOWN);
		BigDecimal step2= b3.multiply(b4);
		
		BigDecimal  step3= step1.multiply(step2);
		
		//System.out.println("未省略值：" + step3);
		return  step3.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
    
    public static void main(String[] args) {    
//        try {    
//            System.out.println("结果："+changeF2Y("-000a00"));    
//        } catch(Exception e){    
//            System.out.println("----------->>>"+e.getMessage());    
////          return e.getErrorCode();    
//        }     
//      System.out.println("结果："+changeY2F("1.00000000001E10"));    
            
        System.out.println(AmountUtils.yearRate("12.78"));    
        /*try {  
            System.out.println(AmountUtils.changeF2Y("155500058"));  
            System.out.println(AmountUtils.changeF2Y("1000"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }*/  
//        System.out.println(Long.parseLong(AmountUtils.changeY2F("1000000000000000")));    
//        System.out.println(Integer.parseInt(AmountUtils.changeY2F("10000000")));    
//        System.out.println(Integer.MIN_VALUE);    
//        long a = 0;    
//        System.out.println(a);    
        
        
    }    
}  
