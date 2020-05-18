package com.cbai.common.web.springmvc;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 日期编辑器
 * 
 * 根据日期字符串长度判断是长日期还是短日期。只支持yyyy-MM-dd，yyyy-MM-dd HH:mm:ss两种格式。
 * 扩展支持yyyy,yyyy-MM日期格式
 */
public class DateTypeEditor extends PropertyEditorSupport {
	public static final DateFormat DF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat DF_MID = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DF_YEAR = new SimpleDateFormat("yyyy");
	public static final DateFormat DF_MONTH = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 短类型日期长度
	 */
	public static final int Long_DATE = 19;
	
	public static final int SHORT_DATE = 10;
	
	public static final int YEAR_DATE = 4;
	
	public static final int MONTH_DATE = 7;
	
	public static final int MID_DATE = 16;

	public void setAsText(String text) throws IllegalArgumentException {
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		try {
			if(text.indexOf("-")==-1){
				setValue(new Date(Long.valueOf(text)));
			}else if (text.length() <= YEAR_DATE) {
				//setValue(new java.sql.Timestamp(DF_YEAR.parse(text).getTime()));
				setValue(DF_YEAR.parse(text));
			}else  if (text.length() <= MONTH_DATE) {
				//setValue(new java.sql.Timestamp(DF_MONTH.parse(text).getTime()));
				setValue(DF_MONTH.parse(text));
			}else if (text.length() <= SHORT_DATE) {
				//setValue(new java.sql.Timestamp(DF_SHORT.parse(text).getTime()));
				setValue(DF_SHORT.parse(text));
			}else if (text.length() <= MID_DATE) {
				//setValue(new java.sql.Timestamp(DF_SHORT.parse(text).getTime()));
				setValue(DF_MID.parse(text));
			} else {
				setValue(DF_LONG.parse(text));
				//setValue(new java.sql.Timestamp(DF_LONG.parse(text).getTime()));
			} 
		} catch (ParseException ex) {
			IllegalArgumentException iae = new IllegalArgumentException(
					"Could not parse date: " + ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DF_LONG.format(value) : "");
	}
}
