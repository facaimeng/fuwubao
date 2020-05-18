package com.cbai.model.rongyin.payment.huifu.vo;

public class HuifuQueryFssBackVo {
	
    private String  CmdId;
    
    private String RespCode;
    
    private String RespDesc;
    
    private String MerCustId;
    
    private String PrdRate;
    
    private String AnnuRate;
    
    private String RespExt;
    
    private String ChkValue;

    
	public HuifuQueryFssBackVo() {
		super();
	}
	
    
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

	public String getPrdRate() {
		return PrdRate;
	}

	public void setPrdRate(String prdRate) {
		PrdRate = prdRate;
	}

	public String getAnnuRate() {
		return AnnuRate;
	}

	public void setAnnuRate(String annuRate) {
		AnnuRate = annuRate;
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
