package com.cbai.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
 

public class UpAndDownUtil {
	/**
	 *
	 * @author laolv
	 * 
	 */
 
	
	private long upMaxSize;//设置单次上传文件的大小，单位字节
		
	private  String tempFilePath;//缓存文件夹
	
	private  int tempFileMax = 4*1024;	//缓存文件夹大小
			
	private  String encoding = "UTF-8";//对页面<Form>中File表单域中的数据进行编码
	
	private String allowFile[]=null;//允许上传的文件类型字符数组如{txt,pdf,jpg}
	
	private String allowFileString;
	
	private String fileSavePath=null;
	
	private String defaltSaveName = null;
	
	private String zipSavePath = null;
	
	private String waterMarkerPath = null;
	
	private String initFlag = null;
	
	private boolean ifSaveSrc = true;
	
	private Map<String, String> formParaMap = null;//页面<Form>中除File表单域外其他表单域的Map集合<name,value>
	
	private List  upFileList = null;
	
	private String[] zipInfo = null;
		
	private HttpServletRequest request = null; 
	
	public UploadFileInfo saveAs(){
		
		Iterator iterator = this.upFileList.iterator();  

		FileItem fileItem =(FileItem)iterator.next();
		
		//得到fileItem中文件名
		String itemFileName = this.getItenmFileName(fileItem);
				
		//得到文件的后缀
		String itemFileExt = this.getUpFileExt(itemFileName);;

		UploadFileInfo ufi=new UploadFileInfo();	
		
		if(this.defaltSaveName!=null){
			itemFileExt = this.defaltSaveName;
		}		
	    try {
	    	if(this.zipInfo!=null){
				ImageZipUtil.zipImageFile(fileItem.getInputStream(), this.zipInfo,1f, this.zipSavePath,this.waterMarkerPath);
			}
	    	if(this.ifSaveSrc){
	    		if(this.waterMarkerPath!=null){
	    		
	    			ImageZipUtil.srcImageFile(fileItem.getInputStream(), this.fileSavePath+"."+itemFileExt, this.waterMarkerPath);
	    			
	    		}else{
	    			fileItem.write(new File(this.fileSavePath+"."+itemFileExt));
	    		}	    		
	    	}			
			
			ufi.setFileName(itemFileName.substring(0,itemFileName.lastIndexOf(".")).toLowerCase());
			
			ufi.setFileExt(itemFileExt);
			//ufi.setFileURL(fileURL);//存放在数据库中的文件url则为保存路径，如c"\\test，不加文件名
			ufi.setFileSize(fileItem.getSize());
			ufi.setUpSuccess(true);
			
			return ufi;						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ufi.setUpSuccess(false);
			return ufi;
		}   
	}

	public static boolean downLoad(String fileURL ,String fileName,HttpServletResponse response) throws IOException{
		
        java.io.BufferedInputStream bis = null;   
        java.io.BufferedOutputStream bos = null;   
       
        try {   
            long fileLength = new File(fileURL).length();   
  
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"),"ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
  
            bis = new BufferedInputStream(new FileInputStream(fileURL));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            

            byte[] buff = new byte[2048];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }    

        } catch (Exception e) {   
        	String simplename = e.getClass().getSimpleName();       
        	if("ClientAbortException".equals(simplename)){         
           }else     
            e.printStackTrace(); 
            return false;
        } finally {   
            if (bis != null)   
                bis.close();   
            if (bos != null)   
                bos.close();  
        }   

		 return true;
	}

	public int initialize(MultipartFile upfile){
		
		int flag = 0; 
		
		if(upfile.isEmpty()){
			
			this.initFlag = "error.up_nofile"; 
			
		}else if (upfile.getSize() > this.upMaxSize){
			
			this.initFlag = "error.up_size"; 
            
		}else if(!this.checkUpFileType(upfile.getContentType())){	
			
    		this.initFlag = "error.up_ext"; 
		}else{
			flag = 1;
		}
		
		return flag;  
	}
	/**
	 * 初始化，将请求中的表单信息拆封，表单中每个元素由commons-fileupload封装成一个FileItem对象，里面有该元素各类信息，如name,value,而对于file文件域，FileItem对象则包含各类如上传文件名，大小，文件流等
	 * 将为file域的FileItem对象放到upFileList中,其余的普通表单域则按名值对放入formParaMap中
	 * @para String filePath 文件存放路径 
	 * @return upinfoList 每个上传文件的文件信息UploadFileInfo对象的集合
	 */
	public int initialize(HttpServletRequest request){
		
		int fileCount=0;//表单中上传的文件个数
		
		formParaMap=new HashMap();
		
		upFileList=new ArrayList();
		
		this.request=request;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();   
		
	
		factory.setSizeThreshold(this.tempFileMax);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘   
		
		if(this.tempFilePath!=null){
			factory.setRepository(new File(this.tempFilePath)) ;   
			
		}else{
			File tempfile = new File(System.getProperty("java.io.tmpdir"));  //如果没有设置临时缓存目录，则采用系统临时文件目录作为上传的临时目录
			factory.setRepository(tempfile) ;   
		}
			
		// 用以上工厂实例化上传组件  设置最大上传尺寸 ,默认为5M 
		ServletFileUpload sfupload = new ServletFileUpload(factory); 
		
		sfupload.setSizeMax(upMaxSize); 
		
		 //设置表单中的普通字段及file域name属性的字符集编码,fileItem.getFieldName()按此编码获得name属性值
		sfupload.setHeaderEncoding(encoding);

		
		
		// System.out.println(filePath);

		 List itemList = null;   
	        try {   
	        	itemList = sfupload.parseRequest(request);   
	        } catch (FileUploadException e) { 
	        	//遍历抛出的异常，看是否为上传大小的异常，若是，则设置上传标志，并返回
	            if (e instanceof SizeLimitExceededException) {   
	            	
	            	//request.setAttribute("uploadFlag","超出文件大小限制!");
	            	this.initFlag = "error.up_size";
	            	fileCount=-1;
	                return fileCount;
	            }   
	            e.printStackTrace();   
	        }
	        
		 			   
   
            Iterator itemIter = itemList.iterator();   
            
           // System.out.println(fileList.isEmpty());
            //遍历itemIter，将每个元素取出，根据是否为file域，再次封装到不同集合中
            while(itemIter.hasNext()){   
   
                FileItem fileItem = (FileItem)itemIter.next();   
            	//若当前fileItem对象是封装的是普通表单域，则将它的name与value取出，放到formParaMap中
                if(fileItem.isFormField()){               	
                	String filedName=fileItem.getFieldName();
                	String filedValue=null;
					try {
					//将当前普通表单域的fileItem对象中保存的主体内容,即value值作为一个字符串返回
						filedValue = fileItem.getString("UTF-8");
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						//request.setAttribute("uploadFlag", "页面字符编码出错!");
						this.initFlag = "error.up_encoding";
						return -1;
					}
					//将当前普通表单域的名值对放到formParaMap中
            		this.formParaMap.put(filedName, filedValue);
            	    
                }else{       
                	//System.out.println("ItenmFileName------>"+this.getItenmFileName(fileItem));
             
        	        if (fileItem.getName() == "") 
        	        	
        	        	continue;
 
        			//若不在允许的类型之内，返回			
        			if(!this.checkUpFileType(this.getUpFileExt(fileItem.getName()))){			
         	    		this.initFlag = "error.up_ext";
        	    		return 0;
        			}        			
                	this.upFileList.add(fileItem);
                	
                	fileCount ++ ;               	       	     
                }                                           		
                	             	    
                }  
            
            if(fileCount==0){
            	//request.setAttribute("uploadFlag","请选择上传文件!");
            	this.initFlag = "error.up_nofile";
            }
            
            return fileCount;  
	}
	


	public String getInitFlag() {
		return initFlag;
	}

	/**
	 * 检查上传文件类型
	 * @para String upFileExt 上传文件后缀名
	 * @return boolean
	 */
	
	public boolean checkUpFileType(String upFileExt) {
		if(this.allowFile==null){
			return true;
		}
		//遍历允许上传的文件类型字符数组,看是上传的文件是否匹配
		for(int i=0;i<this.allowFile.length;i++){
			if(allowFile[i].equalsIgnoreCase(upFileExt))
				return true;
		}
		return false;
		
	}


	public void setUpMaxSize(long upMaxSize) {
		this.upMaxSize = upMaxSize;
	}

	/**
	 * 设置上传时允许的文件类型
	 *  @para String allowFile 上传允许的文件类型，allowFile="?,?"
	 * @return 
	 */
	public void setAllowFile(String allowFile) {
		this.allowFile = allowFile.split(",");
		//this.allowFileString=allowFile;
	}

	/**
	 * 得到file域中，上传文件的名字
	 *  @para FileItem item file域的FileItem对象
	 * @return  itemFileName 上传文件的名字
	 */

	public String getItenmFileName(FileItem item) {
		
		String path = item.getName(); //得到file域中上传文件的全名,如上传的是c:\test\a.txt,则 path=c:\\test\\a.txt
    	
		//从最后一次出现\\的下一字符开始截取，即为上传文件的名字
    	String itemFileName = path.substring(path.lastIndexOf("\\") + 1);   
    	
		return itemFileName;
	}

	/**
	 * 根据文件名得到其后缀不包括.
	 *  @para String itemFileName 文件名
	 * @return  后缀
	 */

	public String getUpFileExt(String itemFileName) {
		
		
    	   // 得到文件的扩展名(无扩展名时将得到全名)   
    	return itemFileName.substring(itemFileName.lastIndexOf(".")+1 ).toLowerCase();
	}


	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public Map<String, String> getFormParaMap() {
		return formParaMap;
	}



	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}
	public void setTempFileMax(int tempFileMax) {
		this.tempFileMax = tempFileMax;
	}


	public String getUpFileMD5() throws IOException {	
		InputStream inputs = ((FileItem)this.upFileList.iterator().next()).getInputStream();				
		return OptUtil.getStreamMD5(inputs);
	}

	public void setDefaltSaveName(String defaltSaveName) {
		this.defaltSaveName = defaltSaveName;
	}

	public void setZipSavePath(String zipSavePath) {
		this.zipSavePath = zipSavePath;
	}
 
	public void setZipInfo(String[] zipInfo) {
		this.zipInfo = zipInfo;
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public void setIfSaveSrc(boolean ifSaveSrc) {
		this.ifSaveSrc = ifSaveSrc;
	}

	public List getUpFileList() {
		return upFileList;
	}

	public String getWaterMarkerPath() {
		return waterMarkerPath;
	}

	public void setWaterMarkerPath(String waterMarkerPath) {
		this.waterMarkerPath = waterMarkerPath;
	}

	   
}
