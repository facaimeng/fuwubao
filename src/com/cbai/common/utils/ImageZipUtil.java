package com.cbai.common.utils;
import java.awt.Graphics;
import java.awt.Image;   
import java.awt.image.BufferedImage;   
import java.io.File;   
import java.io.FileInputStream;
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.io.InputStream;   
  
import javax.imageio.ImageIO;   
  
import com.sun.image.codec.jpeg.JPEGCodec;   
import com.sun.image.codec.jpeg.JPEGEncodeParam;   
import com.sun.image.codec.jpeg.JPEGImageEncoder;   
  
/** 
 * 压缩图片
 * 
 *  author:cbai
 *  
 */
public class ImageZipUtil {   
  
    /**  
     * 指定宽和高压缩 
     * 
     */  
    public static String zipImageFile(InputStream fs, String[] zipInfo,float quality, String savePath,String waterMarkerPath) {   
        if (fs == null) {   
            return null;   
        }   
        String newImage = null;   
        
        BufferedImage bfImage = null;
        
        FileOutputStream out = null;
        
        JPEGImageEncoder encoder = null;
        
        JPEGEncodeParam jep = null;
 
        try {   
            /** 对服务器上的临时文件进行处理 */  
        	Image srcImage = ImageIO.read(fs);
  
        	for(int i=0;i<zipInfo.length;i++){
        		
        		String[] currentInfo = zipInfo[i].split("X");
        		
        		int width = Integer.parseInt(currentInfo[0]);
        		
        		int height = Integer.parseInt(currentInfo[1]);
        		
        		/** 宽,高设定 */  
            	bfImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);   
            	
            	Graphics graphics = bfImage.getGraphics();
            	
        		graphics.drawImage(srcImage.getScaledInstance(width,height,Image.SCALE_SMOOTH), 0, 0, null);
        		
        		bfImage.flush();
                        	
                /** 压缩后的文件名 */  
                newImage = savePath + "_" + width+"X"+height+".jpg";
                
                if(waterMarkerPath!=null){
                	                	
                	FileInputStream waterMarkFis = new FileInputStream(waterMarkerPath+width+"X40.jpg");
                	
            		Image watermark_image = ImageIO.read(waterMarkFis); 
            		
            		waterMarkFis.close();
 
             		graphics.drawImage(watermark_image, 0, 150,width, 40, null, null);
            		
             		graphics.dispose();
             		
             		watermark_image.flush();              	
                }
 
                /** 压缩之后临时存放位置 */  
                out = new FileOutputStream(newImage);   
                
                encoder = JPEGCodec.createJPEGEncoder(out);   
                
                jep = JPEGCodec.getDefaultJPEGEncodeParam(bfImage);  
                               
                /** 压缩质量 */  
                jep.setQuality(quality, true);   
                
                encoder.encode(bfImage, jep);  
                
                out.close();                                  
        	}
        }catch (IOException e) {   
           
        	e.printStackTrace();   
        }   
        return newImage;   
    } 
    
    /**  
     * 只指定宽自动按比例计算高,或者只指定高自动按比例计算宽,然后压缩
     * 
     */  
    public static String zipImageByOneSide(InputStream fs, String[] zipInfo,float quality, String savePath,String waterMarkerPath) {   
        if (fs == null) {   
            return null;   
        }   
        String newImage = null;   
        
        BufferedImage bfImage = null;
        
        FileOutputStream out = null;
        
        JPEGImageEncoder encoder = null;
        
        JPEGEncodeParam jep = null;
 
        try {   
            /** 对服务器上的临时文件进行处理 */  
        	Image srcImage = ImageIO.read(fs);
        	
        	Integer width = srcImage.getWidth(null);
    		
        	Integer height = srcImage.getHeight(null);
  
        	for(int i=0;i<zipInfo.length;i++){
        		
        		String[] currentInfo = zipInfo[i].split("X");
        		
        		int temp_width = Integer.parseInt(currentInfo[0]);
        		
        		int temp_height = Integer.parseInt(currentInfo[1]);
        		
        		if(width>temp_width || height>temp_height){
        			if(temp_width!=0){//指定宽算高
            			
            			width = temp_width;
            			
            			height = Float.valueOf(((float)height/width)* temp_width).intValue();
            			
            		}else if(temp_height!=0){//指定高算宽
            			
            			width = Float.valueOf(((float)width/height)* temp_height).intValue();
     
            			height = temp_height;
            		}
        		} 

        		/** 宽,高设定 */  
            	bfImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);   
            	
            	Graphics graphics = bfImage.getGraphics();
            	
        		graphics.drawImage(srcImage.getScaledInstance(width,height,Image.SCALE_SMOOTH), 0, 0, null);
        		
        		bfImage.flush();
                        	
                /** 压缩后的文件名 */  
                newImage = savePath + "_" + width+"X"+height+".jpg";
                
                if(waterMarkerPath!=null){
                	                	
                	FileInputStream waterMarkFis = new FileInputStream(waterMarkerPath+width+"X40.jpg");
                	
            		Image watermark_image = ImageIO.read(waterMarkFis); 
            		
            		waterMarkFis.close();
 
             		graphics.drawImage(watermark_image, 0, 150,width, 40, null, null);
            		
             		graphics.dispose();
             		
             		watermark_image.flush();              	
                }
 
                /** 压缩之后临时存放位置 */  
                out = new FileOutputStream(newImage);   
                
                encoder = JPEGCodec.createJPEGEncoder(out);   
                
                jep = JPEGCodec.getDefaultJPEGEncodeParam(bfImage);  
                               
                /** 压缩质量 */  
                jep.setQuality(quality, true);   
                
                encoder.encode(bfImage, jep);  
                
                out.close();                                  
        	}
        }catch (IOException e) {   
           
        	e.printStackTrace();   
        }   
        return newImage;   
    }  
    
    /**  
     * 指定图片压缩的范围，如100X50,得到上传图片的宽和高，若上传图片的w和h都小于100X500,则不压,若上传图片的w/h有一边大于100/50,以W为基准压缩,如上传的图片w/h<100/50,则以H为基准压缩
     * 
     */  
    public static String zipImageAuto(InputStream fs, String[] zipInfo,float quality, String savePath,String waterMarkerPath) {   
        if (fs == null) {   
            return null;   
        }   
        String newImage = null;   
        
        BufferedImage bfImage = null;
        
        FileOutputStream out = null;
        
        JPEGImageEncoder encoder = null;
        
        JPEGEncodeParam jep = null;
 
        try {   
            /** 对服务器上的临时文件进行处理 */  
        	Image srcImage = ImageIO.read(fs);
        	
        	int width = srcImage.getWidth(null);//上传的图片宽
    		
    		int height = srcImage.getHeight(null);//上传的图片高
  
        	for(int i=0;i<zipInfo.length;i++){
        		
        		String[] currentInfo = zipInfo[i].split("X");
        		
        		int temp_width = Integer.parseInt(currentInfo[0]);
        		
        		int temp_height = Integer.parseInt(currentInfo[1]); 
        		
        		if(width > temp_width || height > temp_height){ 
        			
            		float source_scale = (float)width/height;//上传图片比例
            		
            		float cut_scale = (float)temp_width/temp_height;//指定比例
        			
        			if(source_scale == cut_scale){
            			
            			width = temp_width;
            			
            			height = temp_height; 
            			
            		}else if(source_scale < cut_scale){ 
           			   
            			width = Float.valueOf(source_scale * temp_height).intValue();
            		 	
            			height = temp_height;
     		
            		}else if(source_scale > cut_scale){ 
           			   
            			height = Float.valueOf(temp_width / source_scale).intValue();
            			
            			width = temp_width; 
            		}  
        		} 
        		
        		/** 宽,高设定 */  
            	bfImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);   
            	
            	Graphics graphics = bfImage.getGraphics();
            	
        		graphics.drawImage(srcImage.getScaledInstance(width,height,Image.SCALE_SMOOTH), 0, 0, null);
        		
        		bfImage.flush();
                        	
                /** 压缩后的文件名 */  
                newImage = savePath + "_" + temp_width+"X"+temp_height+".jpg";
                
                if(waterMarkerPath!=null){
                	                	
                	FileInputStream waterMarkFis = new FileInputStream(waterMarkerPath+width+"X40.jpg");
                	
            		Image watermark_image = ImageIO.read(waterMarkFis); 
            		
            		waterMarkFis.close();
 
             		graphics.drawImage(watermark_image, 0, 150,width, 40, null, null);
            		
             		graphics.dispose();
             		
             		watermark_image.flush();              	
                }
 
                /** 压缩之后临时存放位置 */  
                out = new FileOutputStream(newImage);   
                
                encoder = JPEGCodec.createJPEGEncoder(out);   
                
                jep = JPEGCodec.getDefaultJPEGEncodeParam(bfImage);  
                               
                /** 压缩质量 */  
                jep.setQuality(quality, true);   
                
                encoder.encode(bfImage, jep);  
                
                out.close();                                  
        	}
        }catch (IOException e) {   
           
        	e.printStackTrace();   
        }   
        return newImage;   
    }   
    
    /**  
     *  直接给图片加水印
     * 
     */ 
    public static void srcImageFile(InputStream fs, String savePath,String waterMarkerPath) {   
        if (fs == null) {   
            return ;   
        }           
        BufferedImage bfImage = null;
        
        FileOutputStream out = null;
        
        JPEGImageEncoder encoder = null;
        
        JPEGEncodeParam jep = null;
 
        try {   
            /** 对服务器上的临时文件进行处理 */  
        	Image srcImage = ImageIO.read(fs);
 		
    		int width = srcImage.getWidth(null);
    		
    		int height = srcImage.getHeight(null);
    		
    		/** 宽,高设定 */  
        	bfImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
        	
        	Graphics graphics = bfImage.getGraphics();
        	
    		graphics.drawImage(srcImage.getScaledInstance(width,height,Image.SCALE_SMOOTH), 0, 0, null);
    		
    		bfImage.flush();
      
    		FileInputStream waterMarkFis = new FileInputStream(waterMarkerPath+width+"X50.jpg");
        	
    		Image watermark_image = ImageIO.read(waterMarkFis); 
    		
    		waterMarkFis.close();

     		graphics.drawImage(watermark_image, 0, 551,width, 50, null, null);
    		
     		graphics.dispose();
     		
     		watermark_image.flush(); 

            /** 压缩之后临时存放位置 */  
            out = new FileOutputStream(savePath);   
            
            encoder = JPEGCodec.createJPEGEncoder(out);   
            
            jep = JPEGCodec.getDefaultJPEGEncodeParam(bfImage);  
                           
            /** 压缩质量 */  
            jep.setQuality(1f, true);   
            
            encoder.encode(bfImage, jep);  
            
            out.close();     
        }catch (IOException e) {   
           
        	e.printStackTrace();   
        }   
    }   

}