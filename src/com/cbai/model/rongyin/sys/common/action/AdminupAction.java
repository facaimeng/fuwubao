package com.cbai.model.rongyin.sys.common.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.OptUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.utils.UpAndDownUtil;
import com.cbai.common.web.ResponseUtils;
import com.cbai.common.web.springmvc.RealPathResolver;
import com.cbai.model.rongyin.ibatis.entity.Advertise;
import com.cbai.model.rongyin.ibatis.entity.BankInfo;
import com.cbai.model.rongyin.ibatis.entity.CompanyAttach;
import com.cbai.model.rongyin.ibatis.entity.News;
import com.cbai.model.rongyin.ibatis.entity.ProjectAttach;
import com.cbai.model.rongyin.ibatis.entity.ProjectImgs;
import com.cbai.model.rongyin.ibatis.entity.Projects;
 
@Controller 
@RequestMapping(value="system")
public class AdminupAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
  
	@RequestMapping(value="/common/upload/company_attach/", method=RequestMethod.POST)
	public void company_attach(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		 
		String savePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\upload\\";
		
		UpAndDownUtil udp = new UpAndDownUtil();
		
		udp.setUpMaxSize(50*1024*1024);
 	
		udp.setTempFileMax(4*1024);
		
		udp.setTempFilePath(savePath+"temp");
		
		int intFlag=udp.initialize(request);
		
		String id = udp.getFormParaMap().get("id");
		
		String msg = null;
 
		if(intFlag==-1||intFlag==0){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString(udp.getInitFlag()))));   
		}else{
 
			Iterator iterator = udp.getUpFileList().iterator(); 
			
			FileItem fiterm = (FileItem)iterator.next();
			
			Long fileSize = fiterm.getSize();
			
			String itermName = udp.getItenmFileName(fiterm);
			
			String ext = udp.getUpFileExt(itermName);
			
			String saveName = BusinessNumberUtil.gainOutBizNoNumber()+"."+ext;
			
			savePath += "users\\company\\"+saveName;
			 
			fiterm.write(new File(savePath));
			 
			CompanyAttach catt = new CompanyAttach();
			
			catt.setCmid(Integer.valueOf(id));
			
			catt.setName(itermName.substring(0,itermName.lastIndexOf(".")).toLowerCase());
 
			catt.setFsize(OptUtil.getFileSize(fileSize)); 
			
			catt.setExt(ext);
			
			catt.setFurl("/WEB-INF/upload/users/company/"+saveName);
			
			catt.setUptime(new Date()); 
 		
			baseService.save("company_attach.insert", catt);
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
		}		 		
	}
	
	/**
	 *银行LOGO
	 */
	@RequestMapping(value="/common/upload/bank_logo/", method=RequestMethod.POST) 
	public void bank_logo(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		 
		String savePath = request.getSession().getServletContext().getRealPath("/")+"resources\\upload\\";
		
		UpAndDownUtil udp = new UpAndDownUtil();
		
		udp.setUpMaxSize(500*1024);
		
		udp.setAllowFile("png");
 	
		udp.setTempFileMax(4*1024);
		
		udp.setTempFilePath(savePath+"temp");
		
		int intFlag=udp.initialize(request);
		
		String id = udp.getFormParaMap().get("id");
		
		String msg = null;
 
		if(intFlag==-1||intFlag==0){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString(udp.getInitFlag()))));   
		}else{
 
			Iterator iterator = udp.getUpFileList().iterator(); 
			
			FileItem fiterm = (FileItem)iterator.next();
			
			Long fileSize = fiterm.getSize();
			
			String itermName = udp.getItenmFileName(fiterm);
			
			String ext = udp.getUpFileExt(itermName);
			
			String saveName = BusinessNumberUtil.gainOutBizNoNumber()+".png";
			
			savePath += "bank\\"+saveName;
			 
			fiterm.write(new File(savePath));
			 
			BankInfo bk = new BankInfo();
			
			bk.setBkid(Integer.valueOf(id)); 
			
			bk.setBklogourl("/resources/upload/bank/"+saveName); 
 		
			baseService.update("bank_info.update", bk);
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
		}		 		
	}
	
	/**
	 *新闻封面
	 */
	@RequestMapping(value="/common/upload/news_cover/", method=RequestMethod.POST) 
	public void news_cover(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		 
		String savePath = request.getSession().getServletContext().getRealPath("/")+"resources\\upload\\";
		
		UpAndDownUtil udp = new UpAndDownUtil();
		
		udp.setUpMaxSize(500*1024);
		
		udp.setAllowFile("jpg");
 	
		udp.setTempFileMax(4*1024);
		
		udp.setTempFilePath(savePath+"temp");
		
		int intFlag=udp.initialize(request);
		
		String id = udp.getFormParaMap().get("id");
		
		String msg = null;
 
		if(intFlag==-1||intFlag==0){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString(udp.getInitFlag()))));   
		}else{
 
			Iterator iterator = udp.getUpFileList().iterator(); 
			
			FileItem fiterm = (FileItem)iterator.next();
			
			Long fileSize = fiterm.getSize();
			
			String itermName = udp.getItenmFileName(fiterm);
			
			String ext = udp.getUpFileExt(itermName);
			
			String saveName = BusinessNumberUtil.gainOutBizNoNumber()+".jpg";
			
			savePath += "news\\"+saveName;
			 
			fiterm.write(new File(savePath));
			 
			News news = new News();
			
			news.setNid(Integer.valueOf(id)); 
			
			news.setCoverurl("/resources/upload/news/"+saveName); 
 		
			baseService.update("news.updateOne", news);
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
		}		 		
	}
	
	/**
	 *房源封面
	 */
	@RequestMapping(value="/common/upload/project_cover/", method=RequestMethod.POST) 
	public void project_cover(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		 
		String savePath = request.getSession().getServletContext().getRealPath("/")+"resources\\upload\\";
		
		UpAndDownUtil udp = new UpAndDownUtil();
		
		udp.setUpMaxSize(500*1024);
		
		udp.setAllowFile("jpg");
 	
		udp.setTempFileMax(4*1024);
		
		udp.setTempFilePath(savePath+"temp");
		
		int intFlag=udp.initialize(request);
		
		String id = udp.getFormParaMap().get("id");
		
		String msg = null;
 
		if(intFlag==-1||intFlag==0){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString(udp.getInitFlag()))));   
		}else{
 
			Iterator iterator = udp.getUpFileList().iterator(); 
			
			FileItem fiterm = (FileItem)iterator.next();
			
			Long fileSize = fiterm.getSize();
			
			String itermName = udp.getItenmFileName(fiterm);
			
			String ext = udp.getUpFileExt(itermName);
			
			String saveName = BusinessNumberUtil.gainOutBizNoNumber()+".jpg";
			
			savePath += "projects\\"+saveName;
			 
			fiterm.write(new File(savePath));
			 
			Projects pro = new Projects();
			
			pro.setProid(Integer.valueOf(id)); 
			
			pro.setCoverurl("/resources/upload/projects/"+saveName); 
 		
			baseService.update("projects.update", pro);
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
		}		 		
	}
	
	@RequestMapping(value="/common/upload/project_imgs/", method=RequestMethod.POST)
	public void project_imgs(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		 
		String savePath = request.getSession().getServletContext().getRealPath("/")+"resources\\upload\\";
		
		UpAndDownUtil udp = new UpAndDownUtil();
		
		udp.setUpMaxSize(1*1024*1024);
		
		udp.setAllowFile("jpg");
 	
		udp.setTempFileMax(4*1024);
		
		udp.setTempFilePath(savePath+"temp");
		
		int intFlag=udp.initialize(request);
		
		String id = udp.getFormParaMap().get("id");
		
		String msg = null;
 
		if(intFlag==-1||intFlag==0){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString(udp.getInitFlag()))));   
		}else{
 
			Iterator iterator = udp.getUpFileList().iterator(); 
			
			FileItem fiterm = (FileItem)iterator.next();
			
			Long fileSize = fiterm.getSize();
			
			String itermName = udp.getItenmFileName(fiterm);
			
			String ext = udp.getUpFileExt(itermName);
			
			String saveName = BusinessNumberUtil.gainOutBizNoNumber()+".jpg";
			
			savePath += "projects\\"+saveName;
			 
			fiterm.write(new File(savePath));
			 
			ProjectImgs pimg = new ProjectImgs();
			
			pimg.setProid(Integer.valueOf(id));
			
			pimg.setName(itermName.substring(0,itermName.lastIndexOf(".")).toLowerCase());
 
			pimg.setFsize(OptUtil.getFileSize(fileSize)); 
			
			pimg.setExt(ext);
			
			pimg.setFurl("/resources/upload/projects/"+saveName);
			
			pimg.setUptime(new Date()); 
 		
			baseService.save("project_imgs.insert", pimg);
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
		}		 		
	}
	
	@RequestMapping(value="/common/upload/project_attach/", method=RequestMethod.POST)
	public void project_attach(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		 
		String savePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\upload\\";
		
		UpAndDownUtil udp = new UpAndDownUtil();
		
		udp.setUpMaxSize(10*1024*1024);
		
		udp.setAllowFile("pdf");
 	
		udp.setTempFileMax(4*1024);
		
		udp.setTempFilePath(savePath+"temp");
		
		int intFlag=udp.initialize(request);
		
		String id = udp.getFormParaMap().get("id");
		
		String msg = null;
 
		if(intFlag==-1||intFlag==0){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString(udp.getInitFlag()))));   
		}else{
 
			Iterator iterator = udp.getUpFileList().iterator(); 
			
			FileItem fiterm = (FileItem)iterator.next();
			
			Long fileSize = fiterm.getSize();
			
			String itermName = udp.getItenmFileName(fiterm);
			
			String ext = udp.getUpFileExt(itermName);
			
			String saveName = BusinessNumberUtil.gainOutBizNoNumber()+"."+ext;
			
			savePath += "projects\\"+saveName;
			 
			fiterm.write(new File(savePath));
			 
			ProjectAttach patt = new ProjectAttach();
			
			patt.setProid(Integer.valueOf(id));
			
			patt.setName(itermName.substring(0,itermName.lastIndexOf(".")).toLowerCase());
 
			patt.setFsize(OptUtil.getFileSize(fileSize)); 
			
			patt.setExt(ext);
			
			patt.setFurl("/WEB-INF/upload/projects/"+saveName);
			
			patt.setUptime(new Date()); 
 		
			baseService.save("project_attach.insert", patt);
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
		}		 		
	}
	
	/**
	 *首页滚图
	 */
	@RequestMapping(value="/common/upload/advertise_img/", method=RequestMethod.POST) 
	public void advertise_img(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		 
		String savePath = request.getSession().getServletContext().getRealPath("/")+"resources\\upload\\";
		
		UpAndDownUtil udp = new UpAndDownUtil();
		
		udp.setUpMaxSize(1*1024*1024);
		
		udp.setAllowFile("jpg");
 	
		udp.setTempFileMax(4*1024);
		
		udp.setTempFilePath(savePath+"temp");
		
		int intFlag=udp.initialize(request);
		
		String id = udp.getFormParaMap().get("id");
		
		String msg = null;
 
		if(intFlag==-1||intFlag==0){
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString(udp.getInitFlag()))));   
		}else{
 
			Iterator iterator = udp.getUpFileList().iterator(); 
			
			FileItem fiterm = (FileItem)iterator.next();
			
			Long fileSize = fiterm.getSize();
			
			String itermName = udp.getItenmFileName(fiterm);
			
			String ext = udp.getUpFileExt(itermName);
			
			String saveName = BusinessNumberUtil.gainOutBizNoNumber()+".jpg";
			
			savePath += "adv\\"+saveName;
			 
			fiterm.write(new File(savePath));
			 
			Advertise adv = new Advertise();
			
			adv.setAdid(Integer.valueOf(id)); 
			
			adv.setImgurl("/resources/upload/adv/"+saveName); 
 		
			baseService.update("advertise.updateOne", adv);
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));  
		}		 		
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 
	 
	
}
