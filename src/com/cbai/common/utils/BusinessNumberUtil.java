/**
 * www.yiji.com Inc.
 * Copyright (c) 2012 All Rights Reserved.
 */
package com.cbai.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *                       
 * @Filename BusinessNumberUtil.java
 *
 * @Description 
 *
 * @Version 1.0
 *
 * @Author zjialin
 *
 * @Email zjialin@yiji.com
 *       
 * @History
 *<li>Author: zjialin</li>
 *<li>Date: 2013-5-8</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class BusinessNumberUtil {
	
	public static String gainNumber() {
		StringBuffer number = new StringBuffer();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		number.append(simpleDateFormat.format(new Date()));
		int a = (int) (Math.random() * 1000);
		if (a < 10 && a > 0) {
			a = a * 100;
		} else if (a >= 10 && a < 100) {
			a = a * 10;
		}
		number.append(a == 0 ? "000" : a);
		return number.toString();
	}
	
	public static String gainOutBizNoNumber() {
		StringBuffer number = new StringBuffer();
		//String outBizNo = "0987";//AppConstantsUtil.getOutBizNumber();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("mmssSSS");
		Date nowDate = new Date();
		number.append(simpleDateFormat.format(nowDate)).append(simpleTimeFormat.format(nowDate));
		int a = (int) (Math.random() * 1000);
		String aString = String.valueOf(a);
		while (aString.length() < 3) {
			aString = "0" + aString;
		}
		number.append(aString);
		return number.toString();
	}
	
	public static String gainNumber8() {
		StringBuffer number = new StringBuffer();
		long millis = Calendar.getInstance().getTimeInMillis();
		String millisSeconds = String.valueOf(millis);
		String lastSevenSeconds = millisSeconds.substring(millisSeconds.length() - 5);
		
		int a = (int) (Math.random() * 1000);
		if (a < 10 && a > 0) {
			a = a * 100;
		} else if (a >= 10 && a < 100) {
			a = a * 10;
		}
		String flag = a == 0 ? "000" : String.valueOf(a);
		String tradeNo = number.append(lastSevenSeconds).append(flag).toString();
		
		return tradeNo;
	}
	
	public static String orderNumber(){
		//当前日期
		Date date = new Date();   
		Calendar cal = Calendar.getInstance();   
		cal.setTime(date);  
		
		//当前年份后两位
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(cal.getTime());
		
		//当前日期所在年份的所在天数
		long day= cal.get(Calendar.DAY_OF_YEAR);
		String strDay = String.format("%03d", day);//若所在天数不足三位数则补零
		//拆分年份
		char [] y = yearLast.toCharArray();
		//拆分天数
		char [] d = strDay.toCharArray();
		
		String orderNo 	= String.valueOf(y[0])+String.valueOf(d[1])+String.valueOf(y[1])+String.valueOf(d[2])+String.valueOf(d[0]);
		
		return orderNo;
	}
	
	public static void main(String[] args) {
		
		//System.out.println(BusinessNumberUtil.gainNumber());
		System.out.println(BusinessNumberUtil.gainOutBizNoNumber());
		//for (int i = 0; i < 10; i++) {
			System.out.println(BusinessNumberUtil.orderNumber());
		//}
		
	}
	
}
