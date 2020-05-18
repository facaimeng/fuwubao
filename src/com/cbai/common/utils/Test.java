package com.cbai.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tom
 */
public class Test {


	   public static void main(String []args){
	       
		   Calendar c = Calendar.getInstance();//获得一个日历的实例
	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      
	       Date date = null;
	       try{
	         date = sdf.parse("2018-04-30");//初始日期
	       }catch(Exception e){
             
	       }
	       c.setTime(date);//设置日历时间
	       c.add(Calendar.MONTH,1);//在日历的月份上增加6个月
	       System.out.println(sdf.format(c.getTime()));//得到6个月后的日期
	   }


}
