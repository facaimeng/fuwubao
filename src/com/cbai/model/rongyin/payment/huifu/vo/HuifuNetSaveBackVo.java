package com.cbai.model.rongyin.payment.huifu.vo;

public class HuifuNetSaveBackVo {
	
    private String CmdId;
    private String RespCode;
    private String RespDesc;
    private String SecRespCode; //二级应答返回码
    private String SecRespDesc;  //二级应答描述
    private String MerCustId; //商户客户号
    private String UsrCustId;
    private String OrdId;
    private String OrdDate;
    private String TransAmt;
    private String TrxId; //可选
    private String GateBusiId; //可选
    private String GateBankId; //可选
    private String FeeAmt;
    private String FeeCustId;
    private String FeeAcctId;
    private String RetUrl; //可选
    private String BgRetUrl;
    private String CardId;
    private String MerPriv;
    private String ChkValue;

	public HuifuNetSaveBackVo() { }
	
	public String getCmdId() {
		return CmdId;
	}
	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}
	public String getRespCode() {
		return RespCode;
	}
	public void setRespCode(String respCode) {
		RespCode = respCode;
	}
	public String getRespDesc() {
		return RespDesc;
	}
	public void setRespDesc(String respDesc) {
		RespDesc = respDesc;
	}
	public String getSecRespCode() {
		return SecRespCode;
	}
	public void setSecRespCode(String secRespCode) {
		SecRespCode = secRespCode;
	}
	public String getSecRespDesc() {
		return SecRespDesc;
	}
	public void setSecRespDesc(String secRespDesc) {
		SecRespDesc = secRespDesc;
	}
	public String getMerCustId() {
		return MerCustId;
	}
	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}
	public String getUsrCustId() {
		return UsrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}
	public String getOrdId() {
		return OrdId;
	}
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	public String getOrdDate() {
		return OrdDate;
	}
	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}
	public String getTransAmt() {
		return TransAmt;
	}
	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}
	public String getTrxId() {
		return TrxId;
	}
	public void setTrxId(String trxId) {
		TrxId = trxId;
	}
	public String getGateBusiId() {
		return GateBusiId;
	}
	public void setGateBusiId(String gateBusiId) {
		GateBusiId = gateBusiId;
	}
	public String getGateBankId() {
		return GateBankId;
	}
	public void setGateBankId(String gateBankId) {
		GateBankId = gateBankId;
	}
	public String getFeeAmt() {
		return FeeAmt;
	}
	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}
	public String getFeeCustId() {
		return FeeCustId;
	}
	public void setFeeCustId(String feeCustId) {
		FeeCustId = feeCustId;
	}
	public String getFeeAcctId() {
		return FeeAcctId;
	}
	public void setFeeAcctId(String feeAcctId) {
		FeeAcctId = feeAcctId;
	}
	public String getRetUrl() {
		return RetUrl;
	}
	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}
	public String getBgRetUrl() {
		return BgRetUrl;
	}
	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}
	public String getCardId() {
		return CardId;
	}
	public void setCardId(String cardId) {
		CardId = cardId;
	}
	public String getMerPriv() {
		return MerPriv;
	}
	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	public String getChkValue() {
		return ChkValue;
	}
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}

}
