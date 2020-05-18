package com.cbai.model.common.data;

public enum State { 
	 
	NEWS{public String getName(){return "融银动态";}},
	
		NOTICE{public String getName(){return "最新公告";}},
		
		OURNEWS{public String getName(){return "公司动态";}},
		
		WEALTH_CLASS{public String getName(){return "财富学堂";}},
		
		RONGYIN_POINT{public String getName(){return "融银之声";}},
		
	SERVICE{public String getName(){return "产品服务";}},
	
		WEALTH_MANAGEMENT{public String getName(){return "财富管理";}},
		
		ASSETS_MANAGEMENT{public String getName(){return "资产管理";}},
		
		ONLINE_FINANCE{public String getName(){return "互联网金融";}},
	
	ABOUTUS{public String getName(){return "关于融银";}},
	
		INTRO{public String getName(){return "关于我们";}},
	
		CONTACT{public String getName(){return "联系信息";}},
	
		FAQ{public String getName(){return "帮助中心";}},
	
	SIGLE{public String getName(){return "各类单篇";}},
	 
	MAT1{public String getName(){return "账户充值";}},
	
	MAT2{public String getName(){return "账户提现";}},
	
	MAT3{public String getName(){return "购买产品";}},
	
	MAT4{public String getName(){return "产品退出";}},
	
	MAT5{public String getName(){return "撤销投标";}},
	
	
	NORMAL{public String getName(){return "正常";}},
 
	LOCKED{public String getName(){return "锁定";}},
	
	NODONE{public String getName(){return "未完成";}},
	
	
	NOCHECK{public String getName(){return "未审核";}},

	PASS{public String getName(){return "已通过";}},
	
	UNPASS{public String getName(){return "未通过";}},
	
	LOSSED{public String getName(){return "已流标";}},
	
	
	RPT1{public String getName(){return "先息后本";}},
	
	RPT2{public String getName(){return "到期本息";}},
	
	RPT3{public String getName(){return "等额等息";}}, 
	
	
	RPM1{public String getName(){return "利息";}},
	
	RPM2{public String getName(){return "本息";}},
	
	
	BIDDING{public String getName(){return "投标处理中";}},
	
	BIDDONE{public String getName(){return "投标成功";}},
	
	BIDFAIL{public String getName(){return "投标失败";}},
	
	BIDCANCLE{public String getName(){return "投标已撤销";}},
	
	
	FKDING{public String getName(){return "放款处理中";}},
	
	FKDONE{public String getName(){return "放款完成";}},
	
	FKFAIL{public String getName(){return "放款失败";}},
	
	FINISH{public String getName(){return "满标停投";}},
	
	
	UNREP{public String getName(){return "未还清";}},
	
	ISREP{public String getName(){return "已还清";}},

	
	DOING{public String getName(){return "处理中";}},
	
	FAIL{public String getName(){return "失败";}},
	
	DONE{public String getName(){return "已完成";}},
	
	
	USED{public String getName(){return "已使用";}},

	M{public String getName(){return "先生";}},
 
	F{public String getName(){return "女士";}},
	
	O{public String getName(){return "未知";}},
 
	Y{public String getName(){return "是";}},
 
	N{public String getName(){return "否";}},
	
	COM{public String getName(){return "普通企业";}},
	 
	WAN{public String getName(){return "担保企业";}},
	
	AUTOOUT{public String getName(){return "自动退出";}},
	 
	MANOUT{public String getName(){return "手动退出";}},
	
	KOUT{public String getName(){return "被踢退出";}};
 
	public abstract String getName();

}
