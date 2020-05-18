package com.cbai.common.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author laolv
 * 
 */

public class FileUtil {
	
	public static  int delFlag=0;
	
	public static  boolean creatTypeFiles(String filePath) throws FileNotFoundException{
	
		File newfile=new File(filePath);
		
		// FileOutputStream fileOutput = new FileOutputStream(newfile);
		
		if(newfile.exists())	
			return false;
		else 
			return newfile.mkdirs();
		
	}
	
	/**
	 * 删除指定文件
	 * @param filepath
	 * @throws IOException
	 */
	public static  boolean delFiles(String filePath) throws IOException{   
		File delFile = new File(filePath);  
		if(delFile.exists() && delFile.isDirectory()){  
		   if(delFile.listFiles().length==0){
			   if(delFile.delete())
				   FileUtil.delFlag=1;
		    }else{
		        File delFileArr[]=delFile.listFiles();   
		       int i =delFile.listFiles().length;   
		        for(int j=0;j<i;j++){   
		            if(delFileArr[j].isDirectory()){   
		            	delFiles(delFileArr[j].getAbsolutePath());
		            }
		            else if(delFileArr[j].delete())
		            	FileUtil.delFlag=1;
		            	
		          }
		        }
		    }else if(delFile.delete())
		    		FileUtil.delFlag=1;
		
		if(FileUtil.delFlag!=0)
			return true;			
		else 
			return false;
		 }
	
	/**
	 * 删除符合规则的文件
	 * @param filepath
	 * @throws IOException
	 */
	public static  boolean delFilesByRule(String filePath,String patten) throws IOException{   

		File dir = new File(filePath);
		
		if(!dir.isDirectory()) {
		   return false;
		}
		File[] files = dir.listFiles(new fileFilter(patten));
		
		if(files == null) return false;
		  
		for(File file : files) {
			file.delete();
		}
		
		return true;
	}
	
	
	/**
	 * 重命名文件
	 * @param filepath
	 * @throws IOException
	 */
	
	public static  boolean renameFiles(String filePath,String newName) throws IOException{
		
		File editFile = new File(filePath);
		//System.out.println(filePath);
		File newNameFile=null;
		if(editFile.exists()&&editFile.isDirectory())
			{
			newNameFile=new File(editFile.getParent()+File.separator+newName); 	
			}
		else{
			String fileName=editFile.getName();
			if(newName.indexOf(".")!=-1){
				
				
				newNameFile=new File(editFile.getParent()+File.separator+newName);
			
			}
			else{
				String suffix=fileName.substring(fileName.indexOf("."));
				newNameFile=new File(editFile.getParent()+File.separator+newName+suffix); 
				//System.out.println(newNameFile.getAbsolutePath());
			}
			
		}
		if(newNameFile.exists())
			return false;
		
		else 
			return editFile.renameTo(newNameFile); 
	}
	
	/**
	 * 移动文件
	 * @param filepath
	 * @throws IOException
	 */
	
	public static  boolean moveToFile(String fromPath,String toPath) throws IOException{
		
		File fromFile = new File(fromPath);
		
		File toFile = new File(toPath);
		
		if(!fromFile.exists()||!toFile.exists())
			return false;
		
		if(copyFiles(fromPath,toPath))
			if(fromFile.delete())
				return true;
		
		return false;	
	}



/**
 * 复制文件
 * @param filepath
 * @throws IOException
 */

public static  boolean copyFiles(String fromFilePath,String toFilePath) throws IOException{
	

	
	File fromFile = new File(fromFilePath);
	File inToFile = new File(toFilePath+File.separator+fromFile.getName());
	
	if(fromFile.isFile()){
						
		 inToFile = new File(toFilePath+File.separator+fromFile.getName());
		
		 FileInputStream fileInput = new FileInputStream(fromFile); 			
		 
		 FileOutputStream fileOutput = new FileOutputStream(inToFile);
		 
		 if(!WriteFile(fileInput,fileOutput))
			 return false;
			 
		  
	}else {
				
		File fromFileArray[]=fromFile.listFiles();
		
		for(int i=0;i<fromFileArray.length;i++){			
			
			if(fromFileArray[i].isDirectory()){
				inToFile.mkdirs();
				copyFiles(fromFileArray[i].getAbsolutePath(),toFilePath+File.separator+fromFileArray[i].getName().toString());
			}
			else{
				 FileInputStream fileInput = new FileInputStream(fromFileArray[i]); 
				 
				 File inToFile1 = new File(toFilePath+File.separator+fromFileArray[i].getName());
				 
				 FileOutputStream fileOutput = new FileOutputStream(inToFile1);
				 
				 if(!WriteFile(fileInput,fileOutput))
					 return false;
			}
		}
	}		
	 return true;
}


/**
 * 写文件
 * @param filepath
 * @throws IOException
 */

public static  boolean WriteFile(FileInputStream inStream,FileOutputStream outStream) throws IOException{
	int bytesRead = 0;   
    byte[] buffer = new byte[8192]; 
    
    while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {   
        // 将文件写入服务器   
    	outStream.write(buffer, 0, bytesRead);   
    }   
    outStream.close();   
    inStream.close();   
    return true;  		
	  
}
public static  boolean WriteFile(InputStream inStream,OutputStream outStream) throws IOException{
	int bytesRead = 0;   
    byte[] buffer = new byte[8192]; 
    
    while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {   
        // 将文件写入服务器   
    	outStream.write(buffer, 0, bytesRead);   
    }   
    outStream.close();   
    inStream.close();   
    return true;  		
	  
}

//判断文件是否存在
public static boolean exists(String fullPath) {
	return exists(new File(fullPath));
}

public static boolean exists(File file) {
	
	return file.exists();
}



public static void main(String[] args) throws Exception{
	
	//FileUtil.delFiles("E:\\service6\\webapps\\vol\\upload\\attach\\dynamic\\13\\IMG_47_SMALL.jpg");
	String[] arr = "1".split("#");
	System.out.println(arr.length);

	//System.out.println(308*0.2f+248*(750f/300f)*0.8f);
	
	/*String fn = "201109281450022H1OW7_70X49.jpg";
	
	System.out.println(fn.matches("201109281450H1OW7_.+"));*/
}

}
class fileFilter implements FileFilter{
	 private String patten;
	 public fileFilter(String patten) {
		 this.patten = patten;
		// TODO Auto-generated constructor stub
	 }
	public boolean accept(File acp){
		if(acp!=null && acp.isFile() && acp.getName().matches(this.patten)){
		    return true;
		}else{
		    return false;
		}
	}
}