package com.cbai.common.huifu.httpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import chinapnr.Base64;

public class HttpClientHandler {

    /** 汇付HOST **/
    public static final String HTTP_HOST = "http://mertest.chinapnr.com/muser/publicRequests";

    /**
     * MAP类型数组转换成buildUrlEncodedFormEntity类型
     * @throws UnsupportedEncodingException 
     * 
     */
    public static UrlEncodedFormEntity buildUrlEncodedFormEntity(Map<String, String> params) throws UnsupportedEncodingException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        UrlEncodedFormEntity formEntity = null;
        formEntity = new UrlEncodedFormEntity(nvps,"UTF-8");
        return formEntity;
    }
    
    public static String getBase64Encode(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        try {
            byte[] bt = str.getBytes("UTF-8");
            str = String.valueOf(Base64.encode(bt));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getBase64Decode(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        char[] ch = str.toCharArray();
        byte[] bt = Base64.decode(ch);
        try {
            str = new String(bt,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    public static void main(String[] args) {
    	try {
			System.out.println(URLDecoder.decode("%E5%80%9F%E6%AC%BE%E4%BA%BA%E4%BF%A1%E6%81%AF%E9%94%99%E8%AF%AF","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
	}
}
