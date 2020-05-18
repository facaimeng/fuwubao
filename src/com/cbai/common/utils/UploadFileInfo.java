package com.cbai.common.utils;

import java.text.DecimalFormat;
/**
*
* @author laolv
* 
*/

public class UploadFileInfo {
	//public final static int ERROR_EXTNOTALLOWED  = 0;
	
	//public final static int ERROR_UPLOADINGEXCEPTION  = 1;
	
	private boolean upSuccess; //上传是否成功标志
	private String  fileSize;
	private String  fileURL;//上传文件的url
	private String  fileExt;
	private int  upError;
	private String  fileName;//上传文件名
	
	private String  acqtype;//采集类型
	
	private Integer phsid;
	
	
	public Integer getPhsid() {
		return phsid;
	}
	public void setPhsid(Integer phsid) {
		this.phsid = phsid;
	}
	public String getAcqtype() {
		return acqtype;
	}
	public void setAcqtype(String acqtype) {
		this.acqtype = acqtype;
	}
	public String getFileExt() {
		return fileExt.toLowerCase();
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
	
	public boolean isUpSuccess() {
		return upSuccess;
	}
	public void setUpSuccess(boolean upSuccess) {
		this.upSuccess = upSuccess;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * 根据文件的大小(单位为btye)，得到字符串的表示形式
	 * @param upfileSize
	 */
	public void setFileSize(long upfileSize) {
		
		DecimalFormat format = new DecimalFormat("0.00");   
		
		if(upfileSize>=1024*1024)
        {
         float fs1=(((float)upfileSize)/1024f/1024f); 
               
         this.fileSize = format.format(fs1)+"M";
             
        }
        else if(upfileSize>11 && upfileSize<=1024*1024)
        {
        	float  fs2=((float)upfileSize)/1024;
        	this.fileSize = format.format(fs2)+"KB";
            
        }
        else if(upfileSize>0&&upfileSize<11)
        {
        	this.fileSize=String.valueOf(upfileSize)+"Byte";
           
        }
	}
	public int getUpError() {
		return upError;
	}
	public void setUpError(int upError) {
		this.upError = upError;
	}


}
