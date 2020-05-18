/*
 * CopyRight @2014
 */
package com.cbai.common.huifu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chinapnr.Base64;
import chinapnr.SecureLink;


/**
 * 签名工具类
 * @author admin
 *
 */
public class SignUtils implements Serializable {
	
	protected static final Logger			  logger   = LoggerFactory.getLogger(SignUtils.class);
	
	/** serialVersionUID */
    private static final long  serialVersionUID        = 3640874934537168392L;

    /** MD5签名类型 **/
    public static final String SIGN_TYPE_MD5           = "M";

    /** RSA签名类型 **/
    public static final String SIGN_TYPE_RSA           = "R";

    /** RSA验证签名成功结果 **/
    public static final int    RAS_VERIFY_SIGN_SUCCESS = 0;

    /** 商户公钥文件地址 **/
    public static final String MER_PUB_KEY_PATH        = "com/cbai/security/PgPubk_dev.key";//测试
    //public static final String MER_PUB_KEY_PATH        = "security/PgPubk_online.key";//正式

    /** 商户私钥文件地址 **/
    public static final String MER_PRI_KEY_PATH        = "com/cbai/security/MerPrK_dev.key";//测试
    //public static final String MER_PRI_KEY_PATH        = "security/MerPrK_online.key";//正式
    
    /** **/
    public static final String MER_CUST_ID			   = "530353";//测试
    //public static final String MER_CUST_ID			 = "830301";//正式
    
    /**
     * RSA方式加签
     * 
     * @param custId
     * @param forEncryptionStr
     * @param charset
     * @return
     * @throws Exception 
     */
    public static String encryptByRSA(String forEncryptionStr) throws Exception {
    	
        SecureLink sl = new SecureLink();
        String urlPath = SignUtils.class.getResource("/").getPath();
        
        String keyPath = urlPath + MER_PRI_KEY_PATH;
        //logger.info("秘钥路径：" + keyPath);
        int result = sl.SignMsg(MER_CUST_ID, keyPath, forEncryptionStr);
        if (result < 0) {
            // 打印日志 
            throw new Exception();
        }
        
        return sl.getChkValue();
        
    }
    
    /**
     * 
     * @param forEncryptionStr
     * @param chkValue
     * @return
     * @throws Exception
     */
    public static boolean verifyByRSA(String forEncryptionStr, String chkValue) throws Exception {
        try {
        	String urlPath = SignUtils.class.getResource("/").getPath();
            String keyPath = urlPath + MER_PUB_KEY_PATH;
            
            int verifySignResult = new SecureLink().VeriSignMsg(keyPath, forEncryptionStr, chkValue);
            
            return verifySignResult == RAS_VERIFY_SIGN_SUCCESS;
        } catch (Exception e) {
            // 打印日志
            throw new Exception();
        }
    }
    
    /**
     * 获取银行名称
     * @param bankCode 银行编码
     * @return 银行名称
     */
    public static String getBankName(String bankCode) {
    	Map<String, String> bankCodeMap = new HashMap<String, String>();
    	bankCodeMap.put("ICBC", "中国工商银行");
    	bankCodeMap.put("ABC", "中国农业银行");
    	bankCodeMap.put("CMB", "招商银行");
    	bankCodeMap.put("CCB", "中国建设银行");
    	bankCodeMap.put("BCCB", "北京银行");
    	bankCodeMap.put("BJRCB", "北京农村商业银行");
    	bankCodeMap.put("BOC", "中国银行");
    	bankCodeMap.put("BOCOM", "交通银行");
    	bankCodeMap.put("CMBC", "民生银行");
    	bankCodeMap.put("BOS", "上海银行");
    	bankCodeMap.put("CBHB", "渤海银行");
    	bankCodeMap.put("CEB", "光大银行");
    	bankCodeMap.put("CIB", "兴业银行");
    	bankCodeMap.put("CITIC", "中信银行");
    	bankCodeMap.put("CZB", "浙商银行");
    	bankCodeMap.put("GDB", "广东发展银行银行");
    	bankCodeMap.put("HKBEA", "东亚银行");
    	bankCodeMap.put("HXB", "华夏银行");
    	bankCodeMap.put("HZCB", "杭州银行");
    	bankCodeMap.put("NJCB", "南京银行");
    	bankCodeMap.put("PINGAN", "平安银行");
    	bankCodeMap.put("PSBC", "邮政储蓄银行");
    	bankCodeMap.put("SDB", "深圳发展银行");
    	bankCodeMap.put("SPDB", "浦东发展银行");
    	bankCodeMap.put("SRCB", "上海农村商业银行");
    	return bankCodeMap.get(bankCode);
    }
    
}
