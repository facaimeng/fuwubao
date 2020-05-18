package com.cbai.model.rongyin.payment.huifu.vo;

public class HuifuUserInfoBackVo {
	
	private String CmdId;
	private String RespCode;
	private String RespDesc;
	private String MerCustId; 
	private String UsrCustId;
	private String UsrId;
	private String CertId;
	private String UsrStat;   
	private String RespExt;
	private String ChkValue;
	
	public HuifuUserInfoBackVo() { }
	
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
	public String getMerCustId() {
		return MerCustId;
	}
	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}
	public String getUsrId() {
		return UsrId;
	}
	public void setUsrId(String usrId) {
		UsrId = usrId;
	}
	public String getUsrCustId() {
		return UsrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getCertId() {
		return CertId;
	}

	public void setCertId(String certId) {
		CertId = certId;
	}

	public String getUsrStat() {
		return UsrStat;
	}

	public void setUsrStat(String usrStat) {
		UsrStat = usrStat;
	}

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
	 
}
