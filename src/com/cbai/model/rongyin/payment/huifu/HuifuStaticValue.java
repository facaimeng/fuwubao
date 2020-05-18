package com.cbai.model.rongyin.payment.huifu;

import java.util.HashMap;
import java.util.Map;

public class HuifuStaticValue {
	
	private static final Map<String,String> bidTypeMap = new HashMap<String, String>(){{
		put("BT_01","信用");
		put("BT_02","抵押");
		put("BT_03","债权转让");
		put("BT_99","其他");}};
	
	private static final Map<String,String> repaytypeMap = new HashMap<String, String>(){{
		put("BT_01","一次还本付息");
		put("BT_02","等额本金");
		put("BT_03","等额本息");
		put("BT_04","按期付息到期还本");
		put("BT_99","其他");}};
	
}
