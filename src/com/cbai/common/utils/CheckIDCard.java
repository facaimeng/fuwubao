package com.cbai.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 校验身份证 
 * 
 */
public final class CheckIDCard {

	public static void main(String[] args) {
		
		CheckIDCard a = new CheckIDCard();
		System.out.println(a.getBirth("51113219860105231X"));
		System.out.println(a.getSex("450321198710136016"));
		
	}

	public static boolean isIdCardNo(String num) {

		String idNumber = num;
		
		int intStrLen = num.length();

		// initialize
		if ((intStrLen != 15) && (intStrLen != 18)) {
			return false;
		}

		int[] factorArr = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
				5, 8, 4, 2, 1 };

		String[] varArray = new String[intStrLen];

		int lngProduct = 0;

		char[] parityBit = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5',
				'4', '3', '2' };

		String intCheckDigit;

		// check and set value
		for (int i = 0; i < intStrLen-1; i++) {
			
			varArray[i] =  String.valueOf(idNumber.charAt(i));

			// 处理17以外的位置上的数值
			if ((Integer.parseInt(varArray[i]) < 0 || Integer.parseInt(varArray[i]) > 9) && (i != 17)) {
				return false;
			} else if (i < 17) {
				varArray[i] = String.valueOf(Integer.parseInt(varArray[i]) * factorArr[i]);
			}

		}
		
		varArray[17] =  String.valueOf(idNumber.charAt(17));

		if (intStrLen == 18) {
			// check date
			String date8 = idNumber.substring(6, 14);
			if (isDate8(date8) == false) {
				return false;
			}

			// calculate the sum of the products
			for (int i = 0; i < 17; i++) {
				lngProduct = lngProduct + Integer.parseInt(varArray[i]);
			}

			// calculate the check digit
			intCheckDigit =String.valueOf(parityBit[lngProduct % 11]);

			// check last digit
			if (!String.valueOf(varArray[17]).equals(intCheckDigit)) {
				return false;
			}

		} else { // length is 15

			// check date
			String date6 = idNumber.substring(6, 12);
			if (isDate6(date6) == false) {
				return false;
			}

		}
		return true;

	}

	private static boolean isDate6(String sDate){
		
		if (!Pattern.compile("^[0-9]{8}$").matcher(sDate).find()) { 
			return false; 
		}

	    int year, month;
	   
	    year = Integer.valueOf(sDate.substring(0, 4));
	    
	    month = Integer.valueOf(sDate.substring(4, 6));
	    
	    if (year < 1700 || year > 2500) {
	    	return false;
	    }
	    
	    if (month < 1 || month > 12) {
	    	return false;
	    }
	    
	    return true;
	}

	private static boolean isDate8(String sDate) {
		
		if (!Pattern.compile("^[0-9]{8}$").matcher(sDate).find()) {
			return false; 
		}
		
		int year, month, day;
		year = Integer.valueOf(sDate.substring(0, 4));
		month = Integer.valueOf(sDate.substring(4, 6));
		day = Integer.valueOf(sDate.substring(6, 8));
		int iaMonthDays[] = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		
		if (year < 1700 || year > 2500) {
			return false;
		}
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
			iaMonthDays[1]=29;
		}
		if (month < 1 || month > 12) {
			return false;
		}
		if (day < 1 || day > iaMonthDays[month - 1]) {
			return false;
		}
		
	    return true;
	}
	
	public static Date getBirth(String idnum){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		int birthYearSpan = 4;   
        if(idnum.length() == 15) {  
            birthYearSpan = 2;  
        }  
        String year = (birthYearSpan == 2 ? "19" : "") + idnum.substring(6, 6 + birthYearSpan);  
        String month = idnum.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);  
        String day = idnum.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);  
        String birthday = year + '-' + month + '-' + day;  
        try {
			return sdf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
	
	public static String getSex(String idnum){
		
		int idxSexStart = 16;    
        if(idnum.length() == 15) {  
            idxSexStart = 14;   
        }  
        String idxSexStr = idnum.substring(idxSexStart, idxSexStart + 1);  
        int idxSex = Integer.parseInt(idxSexStr) % 2;  
        return (idxSex == 1) ? "M" : "F";  
	}
}
