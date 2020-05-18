package com.cbai.common.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;     
import java.util.GregorianCalendar; 

/**
 * @author ChiBai_Studio
 *
 */
public final class OptUtil {
	
	private static long code;
	
	private static final String SERIALCODEDICT = "E5FCDG3HQA4B1NOPIJ2RSTUV67MWX89KLYZ"; 
	
	public static String replaceName(String name){
		if(name ==null || ""==name){
			return "***";
		}
		
		StringBuilder resultBuilder = new StringBuilder();

	    String part1 = name.substring(0,1);
	    resultBuilder.append(part1);
	    if(name.length()>=3){
	    	resultBuilder.append("**");
	    }else{
	    	resultBuilder.append("*");
	    }
	    return resultBuilder.toString();
	}
	
	public static String replaceEmail(String src){
		if(""==src){
			return "*******";
		}
		
		StringBuilder resultBuilder = new StringBuilder();

	    String part1 = src.substring(0,2);
	    String part2 = src.substring(src.length()-2,src.length());
	    
	    resultBuilder.append(part1);
	    resultBuilder.append("****");
	    resultBuilder.append(part2); 
	    return resultBuilder.toString();
	}
	
	public static String replacePhone(String src){
		if(src == null || "".equals(src)){
			return "***********";
		}
		
		
		StringBuilder resultBuilder = new StringBuilder();

	    String part1 = src.substring(0,3);
	    String part2 = src.substring(src.length()-4,src.length());
	    
	    resultBuilder.append(part1);
	    resultBuilder.append("****");
	    resultBuilder.append(part2); 
	    return resultBuilder.toString();
	}
	
	public static String replaceCard(String src){
		if(""==src){
			return "**********";
		}
		
		StringBuilder resultBuilder = new StringBuilder();

	    String part1 = src.substring(0,4);
	    String part2 = src.substring(src.length()-4,src.length());
	    
	    resultBuilder.append(part1);
	    resultBuilder.append("**********");
	    resultBuilder.append(part2); 
	    return resultBuilder.toString();
	}
	
    public static synchronized String nextCode(String prefix) {
        code++;
        String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        long m = Long.parseLong((str)) * 10000;
        m += code;
        return prefix + m;
    }
    
	/**
	 * 生成指定长度随机文件名
	 * @param string
	 * @return
	 */
	public static String ConfirmId(int sLen) {
	   String date = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar
	     .getInstance().getTime());
	   String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   StringBuffer temp = new StringBuffer();
	   temp.append(date);
	   for (int i = 0; i < sLen; i++) {
	    int p = (int) (Math.random() * 37);
	    if (p > 25) {
	     p = 25;
	    }
	    temp.append(base.substring(p, p + 1));
	   }
	   return temp.toString();
	}


	/**
	 * Generate ID 20 length
//	 */
	public static final String GenerateId(String modelName) {
		DecimalFormat df = new DecimalFormat("00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		StringBuffer stringBuffer = new StringBuffer("");
		try {
			Date nowTime = new Date(System.currentTimeMillis());
			stringBuffer.append(modelName);
			stringBuffer.append(sdf.format(nowTime).toLowerCase());
			return stringBuffer.toString();
		} catch (Exception e) { 
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 手机号验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) { 
		if(str==null||str.equals("")){
			return false;
		}
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[1][3,4,5,8,7][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}
	/**
	 * 邮箱验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isEmail(String str) { 
		if(str==null||str.equals("")){
			return false;
		}
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}
	
	/**
	 * 电话号码验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) { 
		Pattern p1 = null,p2 = null;
		Matcher m = null;
		boolean b = false;  
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
		if(str.length() >9)
		{	m = p1.matcher(str);
 		    b = m.matches();  
		}else{
			m = p2.matcher(str);
 			b = m.matches(); 
		}  
		return b;
	}
	
	/**
	  将java.util.Date 格式转换为字符串格式'yyyy-MM-dd<br>
	  如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'<br>
	  @param time Date 日期<br>
	  @return String 字符串<br>
	 */
	public static String dateToString(Date time){
	    
		SimpleDateFormat formatter;
	    
	    formatter = new SimpleDateFormat ("yyyyMMdd");
	    
	    String ctime = formatter.format(time);

	    return ctime;
	}

	/**
	 * 取系统时间，返回日期对象型(yyyy-MM-dd)
	 * parmeter null
	 * @return Date
	 */
	public static final Date GetDateMMObject() {
		try {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			String sDate = sdf.format(new Date());
			Date date = sdf.parse(sDate);
			return date;
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算两日期相差的天数
	 * parmeter null
	 * @return Date
	 */
	public static final long getDays(Date d1,Date d2) {
        long day = 0;   
        try {   
        
        	day = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);   
        
        } catch (Exception e) {   
        	
        	e.printStackTrace();

        }   
        return day ;   
	}
	
	//得到流的MD5
	public static String getStreamMD5(InputStream inputs) {    

		  MessageDigest digest = null;    
		           
		  byte buffer[] = new byte[1024];    
		     
		  int len;    
		  
		  try {    
		       
		    digest = MessageDigest.getInstance("MD5");    
		       
		    while ((len = inputs.read(buffer, 0, 1024)) != -1) {    
		       
		    digest.update(buffer, 0, len);    
		     
		  }    
		      
		    inputs.close();    
		     
		  } catch (Exception e) {    
		     
		    e.printStackTrace();    
		       
		    return null;    
		     
		  }    
		  
		  BigInteger bigInt = new BigInteger(1, digest.digest());    
		    
		  return bigInt.toString(16);    
		  
		}
	//得到字符串MD5
	public final static String getStringMD5(String s){ 
		
		try { 
			byte[] strTemp = s.getBytes(); 
			
			MessageDigest digest = MessageDigest.getInstance("MD5"); 
			
			digest.update(strTemp); 
			
			BigInteger bigInt = new BigInteger(1, digest.digest());   

			return new String(bigInt.toString(16)); 
		} 
		catch (Exception e){ 
			return null; 
		} 
	}
	/**
	 * 取系统时间，返回 XXXX年XX月的字符串.初衷：用于生成含有年份月份的文件夹名称(文件上传) 
	 * parmeter null
	 * @return String
	 */
	public static final String GetYearAndMonthObject() {
		try {
			SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
			String sDate = sdf.format(new Date());
			String[] s = sDate.split("-");
			sDate = s[0] + "年" + s[1] + "月";			
			return sDate;
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}
	}

	
	/**
	 * @分离逗号分隔的数据(JAVA方法)
	 * @parmeter null
	 * @return ArrayList
	 */
	public static final ArrayList StringToObject(String str) {
		ArrayList<String> arraylist = new ArrayList<String>();
		try {
			StringTokenizer st = new StringTokenizer(str, ",");
			while (st.hasMoreTokens()) {
				arraylist.add(st.nextToken());
			}
			return arraylist;
		} catch (NumberFormatException ee) {
			ee.printStackTrace();
			return null;
		}
	}

	/**
	 * @去掉字符串内的所有空隔
	 * @parmeter String
	 * @return String
	 */
	public static final String isClearSpace(String str) {
		String[] arraylist = str.split(" ");//将就字符串序列转换为数组
		String newString = "";//新字符串
		for (String simple:arraylist) {
			newString += simple;
		}
		return newString;
	}
	/**
	 * 传入一字符串返回日期对象yyyy-MM-dd
	 * @param str
	 * @return Date
	 */
	public static final Date ToDate(String g) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(g);  
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	public static final Date ToDateHour(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(string); // 从字符串产生 Date 需要 try/catch
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	public static final String encodeToUTF(String str)
	{
		String  newStr = "";
		try {
			newStr = URLDecoder.decode(str,"UTF-8");
		 } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //对得到的参数进行解码	
	   return newStr;
	}

	/**
	    * 计算字符串的字节长度(字母数字计1，汉字及标点计2)
	    *
	    */
	    public static int byteLength(String string){
	     int count = 0;
	     for(int i=0;i<string.length();i++){
	         if(Integer.toHexString(string.charAt(i)).length()==4){
	             count += 2;
	            }else{
	             count++;
	            }
	          }
	     return count;
	     }
	 /**
	　　* 按指定长度，省略字符串部分字符
	　　* @para String 字符串
	　　* @para length 保留字符串长度
	　　* @return 省略后的字符串
	　　*/
public static String onSubString(String string,int length)
{
    StringBuffer sb = new StringBuffer();
    if(byteLength(string)>length){
        int count = 0;
        for(int i=0;i<string.length();i++){
          char temp = string.charAt(i);
          if(Integer.toHexString(temp).length()==4){
              count += 2;
              }else{
                  count++;
              }
          if(count<length-3){
                sb.append(temp);
               }
          if(count==length-3){
               sb.append(temp);
                 break;
               }
          if(count>length-3){
                  sb.append(" ");
                  break;
              }
           }
          sb.append("... ...");
        }else{
         sb.append(string);
         }
       return sb.toString();
}
/***
 * 替换掉字符串中的空格符
 * @param str
 * @return
 */
public static String delenull(String str){
	//注意哦这里呢，必须要定义一个String来接收，要不然不成功！！
	String kk=str.replaceAll(" ", "");
	return kk;
}
/**
 * 该方法过虑掉所有<标签>也就是过虑掉所有的网页上的标签
 * @param str
 * @return
 */
public static String rePlaceString(String str){
	java.util.regex.Pattern pp=java.util.regex.Pattern.compile("\\<(.*?)\\>");
	 Matcher m = pp.matcher(str);
	 List testSpKuo = new ArrayList();
	 while(m.find()){
		 String tempp="";
		 //构建标签
		 tempp="<"+m.group(1)+">";
		 testSpKuo.add(tempp);
	 }
	 //把刚刚取到内容替换
	  for(Object tt:testSpKuo){
	     String tempp="";
	     tempp = str.replace(tt.toString()," ");
	     str = tempp;
	    }
	return delenull(replaceNbsp(str));
}
/**
 * 替换掉网页上的&nbsp;标记
 * @param str
 * @return
 */
public static String replaceNbsp(String str)
{
	java.util.regex.Pattern p = java.util.regex.Pattern.compile("(&nbsp;)");
    Matcher m = p.matcher(str);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
        m.appendReplacement(sb, "");
    }
    m.appendTail(sb);
   return sb.toString();
}

/**
 * 组装URI
 * @param str
 * @return
 */
public static String getComeFromURL(HttpServletRequest request){
	
	StringBuffer sb = new StringBuffer();
	
	sb.append(request.getRequestURI());
			
	sb.append("?");
					
	sb.append(request.getQueryString());
			
	//System.out.println(sb.toString());
	
	return sb.toString();
}


/**
 * 分离逗号分隔的数据(JAVA方法)
 * @parmeter null
 * return ArrayList
 */
public static final ArrayList stringToObject(String str) {
	ArrayList<String> arraylist = new ArrayList<String>();
	try {
		StringTokenizer st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			arraylist.add(st.nextToken());
		}
		return arraylist;
	} catch (NumberFormatException ee) {
		ee.printStackTrace();
		return null;
	}
}
/**
 * 生成订单号,订单号的组成:两位年份两位月份两位日期+(当天订单总数+1),如果订单总数的长度不够8位,前面补零,如:09120200000001
 * @return
 */
public static String buildOrderid(String pre,long count) {
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	StringBuilder sb = new StringBuilder(pre+dateFormat.format(date));

	sb.append(fillZero(2, String.valueOf(count + 1)));

	return sb.toString();
}
/**
 * 补零
 * @param length 补零后的长度
 * @param source 需要补零的长符串
 * @return
 */
public static String fillZero(int length, String source) {//7
	StringBuilder result = new StringBuilder(source);
	for(int i=result.length(); i<length ; i++){
		result.insert(0, '0');
	}
	return result.toString();
}
/**  
 *   
 * @param nowdate 日期字符串  
 * @param day  相对天数，为正数表示之后，为负数表示之前  
 * @return 指定日期字符串n天之前或者之后的日期  
 */  
public static Date getBeforeAfterDate(String nowdate, int day) {   
    
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
    
	java.sql.Date olddate = null;   
    
	try {   
       
		df.setLenient(false);   
       
		olddate = new java.sql.Date(df.parse(nowdate).getTime());   
    
	} catch (ParseException e) { 
		
        throw new RuntimeException("日期转换错误");
        
    }   
    
	Calendar cal = new GregorianCalendar();   
    
	cal.setTime(olddate);   

    int Year = cal.get(Calendar.YEAR);   
    
    int Month = cal.get(Calendar.MONTH);   
    
    int Day = cal.get(Calendar.DAY_OF_MONTH);   

    int NewDay = Day + day;   

    cal.set(Calendar.YEAR, Year);   
    
    cal.set(Calendar.MONTH, Month);   
    
    cal.set(Calendar.DAY_OF_MONTH, NewDay);   

    return new Date(cal.getTimeInMillis());   
}  
/**
 * 由Email得到其对应的网址
 * @param emailAddress 邮件地址
 * @return
 */
public static String getUrlByEmail(String emailAddress)
{
	String xin="http://mail.";
	
	int i,j=emailAddress.length();
	
	for(i=emailAddress.length();--i>=0;)
	{
		if(emailAddress.charAt(i)=='@')
		{
			i=i+1;
			break;
		  }  
		
	}
	
	for(int k=i;k<j;k++){
		
		xin=xin+emailAddress.charAt(k);			
	}
	
	return xin;
}
//得到一段时间有多少个指定的星期几
public static int getWeekdayCount(Date start,Date end,int[] weekdays) {   
	
	if(weekdays==null||weekdays.length==0)
		return 0;
	
	Calendar calendar = Calendar.getInstance();
	
	calendar.setTime(start);
	
	long diff = end.getTime()-start.getTime();
	
	long totalDays = diff/(1000*24*60*60)+1;//计算有多少天,把最后一天算进去,如10-1到10-9,是8+1=9天
	
	int count = (int)totalDays / 7; //   共几周 
	
	int overCount = (int)totalDays % 7;//多出的星期天数

	if(overCount>0){
		
		int day = calendar.get(Calendar.DAY_OF_WEEK)-1;//得到开始那天是星期几,按中国方式算,星期2几是2		
		
		if(day==0)
			day = 7;
		
		List<Integer> overList = new ArrayList<Integer>();
		
		int overDayCount = (day - 1) + overCount;//得到最后一天是星期几,如果>7,说明剩下的天过星期日
		
		int overDay = overDayCount -7;//从结束的日期开始加上剩余的天是否过星期日

		if(overDay>=0){
			for(int i=day;i<=7;i++){
				overList.add(i);
			}
			for(int i=1;i<=overDay;i++){
				overList.add(i);
			}			
		}else{
			for(int i=day;i<=overDayCount;i++){
				overList.add(i);
			}
		}
		for(int i = 0;i<overList.size();i++){
			System.out.println(overList.get(i));
		}
		for (int i = 0;i<overList.size();i++){//   是否在剩余的日期里出现 		
			for(int j=0;j<weekdays.length;j++){
				if(weekdays[j]==overList.get(i)){
					count++;
				}
			}	
		}
	}		
	return count;	
}


public static List<Map> getRateday(Date start,Date end,long totalDays,int[] weekdays,String price) {  
		
	if(weekdays==null||weekdays.length==0)
		return null;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
	Calendar calendar = Calendar.getInstance();
	
	calendar.setTime(start);
	
	//long diff = end.getTime()-start.getTime();
	
	//long totalDays = diff/(1000*24*60*60)+1;//计算有多少天,把最后一天算进去,如10-1到10-9,是8+1=9天
	
	Date tempDate = start;
	
	int count = 0;
	
	for(int i = 0;i<totalDays;i++){
		
		Map<String ,String> map = new HashMap<String ,String>();
		
		List<Date> sameDateList = new ArrayList<Date>();

		calendar.add(Calendar.DATE, i); 
		
		for(int j=0;j<weekdays.length;j++){
			if(weekdays[j]==calendar.DAY_OF_WEEK){
				if(calendar.getTime().compareTo(tempDate)==0){
					
					sameDateList.add(calendar.getTime());
					
					tempDate = calendar.getTime();
				}
				count++;
			}
		}
		if(sameDateList.size()==1){
			
		}else if(sameDateList.size()>1){
			
		}
		
	}
	
	List<Map> dateList = new ArrayList<Map>();
	
	
	return dateList;
	
}
/**
 * 过滤网页标签
 * @param inputString 输入含有html标签的字符串
 * @return
 */
public static String html2Text(String inputString) {   
    String htmlStr = inputString; //含html标签的字符串   
    String textStr ="";   
    java.util.regex.Pattern p_script;   
    java.util.regex.Matcher m_script;   
    java.util.regex.Pattern p_style;   
    java.util.regex.Matcher m_style;   
    java.util.regex.Pattern p_html;   
    java.util.regex.Matcher m_html;   
         
    try {   
    	
      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }   
      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }   
      String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式   
      String regEx_nbsp = "&nbsp;"; //定义HTML标签的正则表达式
      
      p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);   
      m_script = p_script.matcher(htmlStr);   
      htmlStr = m_script.replaceAll(""); //过滤script标签   
  
      p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);   
      m_style = p_style.matcher(htmlStr);   
      htmlStr = m_style.replaceAll(""); //过滤style标签   
          
      p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);   
      m_html = p_html.matcher(htmlStr);   
      htmlStr = m_html.replaceAll(""); //过滤html标签  
      
      
      p_html = Pattern.compile(regEx_nbsp,Pattern.CASE_INSENSITIVE);   
      m_html = p_html.matcher(htmlStr);   
      htmlStr = m_html.replaceAll(""); //过滤html标签  
      
      textStr = htmlStr;   
         
     }catch(Exception e) {   
        
    	 System.err.println("Html2Text: " + e.getMessage());   
     
     }   
         
     return textStr;//返回文本字符串
     
}

/**
 * 输入一个时间计算有多少岁了
 * @param birthDay
 * @return
 * @throws Exception
 */
public static int getAge(String birthday) {
	Date birthDay = ToDate(birthday);
    
	Calendar cal = Calendar.getInstance();

    if (cal.before(birthDay)) {
        throw new IllegalArgumentException("出生时间大于当前时间!");
    }

    int yearNow = cal.get(Calendar.YEAR);
    int monthNow = cal.get(Calendar.MONTH) + 1;//注意此处，如果不加1的话计算结果是错误的
    int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
    cal.setTime(birthDay);

    int yearBirth = cal.get(Calendar.YEAR);
    int monthBirth = cal.get(Calendar.MONTH);
    int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

    int age = yearNow - yearBirth;

    if (monthNow <= monthBirth) {
        if (monthNow == monthBirth) {
            //monthNow==monthBirth
            if (dayOfMonthNow < dayOfMonthBirth) {
                age--;
            } else {
                //do nothing
            }
        } else {
            //monthNow>monthBirth
            age--;
        }
    } else {
        //monthNow<monthBirth
        //donothing
    }

    return age;
}

/**
 * 根据文件的大小(单位为btye)，得到字符串的表示形式
 * @param upfileSize
 */
public static String getFileSize(long upfileSize) {
	
	DecimalFormat format = new DecimalFormat("0.00");   
	
	if(upfileSize>=1024*1024){
     float fs1=(((float)upfileSize)/1024f/1024f); 
           
     return format.format(fs1)+"M";
         
    }else if(upfileSize>11 && upfileSize<=1024*1024){
    	float  fs2=((float)upfileSize)/1024;
    	return format.format(fs2)+"KB";
        
    }else if(upfileSize>0&&upfileSize<11){
    	return String.valueOf(upfileSize)+"Byte";     
    }else{
    	return null;
    }
}
public static String randomNum(int len){
	Random random = new Random();
	String result="";
	for(int i=0;i<len;i++){
	    result+=random.nextInt(10);
	}
	return result;
}
public static String randomNum16(){
	Random random = new Random();
	String result="";
	for(int i=0;i<16;i++){
	    result+=random.nextInt(10);
	}
	return result;
}
/**
 * 生成随即密码
 * @param pwd_len 生成的密码的总长度
 * @return  密码的字符串
 */
public static String genRandomNum(int pwd_len){
 //35是因为数组是从0开始的，26个字母+10个数字
 final int  maxNum = 36;
 int i;  //生成的随机数
 int count = 0; //生成的密码的长度
 char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
   'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
   'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
 
 StringBuffer pwd = new StringBuffer("");
 Random r = new Random();
 while(count < pwd_len){
  //生成随机数，取绝对值，防止生成负数，
  
  i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1
  
  if (i >= 0 && i < str.length) {
   pwd.append(str[i]);
   count ++;
  }
 }
 
 return pwd.toString();
} 

/** 
 * 根据ID生成  
 * @param id ID 
 * @return 随机码 
 */  
public static String toInCode(int id,int length) { 
	
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
public static int deInCode(String code) {  
	
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

/**
 *获得请求的ip地址
 * @param upfileSize
 */
public static String getIpAddr(HttpServletRequest request) {      
    String ip = request.getHeader("x-forwarded-for");      
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
       ip = request.getHeader("Proxy-Client-IP");      
   }      
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
       ip = request.getHeader("WL-Proxy-Client-IP");      
    }      
   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
        ip = request.getRemoteAddr();      
   }      
   return ip;      
}
public static void main(String[] args) throws UnsupportedEncodingException {
	
	/*Date s = ToDate("2011-12-31");
	Date e = ToDate("2011-11-20");
	Calendar calendar = Calendar.getInstance();
	
	calendar.setTime(e);
	System.out.println(calendar.MONTH);
	calendar.setTime(s); 
	System.out.println(calendar.MONTH);
	

	String str = "<img dsfsd/>";
	
	java.util.regex.Pattern imgPatten = Pattern.compile("<\\s*img\\s+([^>]*)\\s*>",Pattern.CASE_INSENSITIVE);
	
	java.util.regex.Matcher m = imgPatten.matcher(str);

	
	System.out.println(m.matches());*/
//String ss = "quiet#ggquiet#gg";
	//System.out.print(getStringMD5("ss"));
	
/*	Calendar c1 = Calendar.getInstance();
 
	c1.setTime(new Date());
	
	System.out.println(c1.get(Calendar.MONTH)+1);*/
	//String str = "重庆医药平台门户网功能.doc";
	String ss = "张三";
	System.out.println(ss.length());
	//System.out.println(ss.substring(0,4)+"****"+ss.substring(ss.length()-4,ss.length()));
	//System.out.println(OptUtil.getStringMD5("12345"));
}
}

