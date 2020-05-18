package com.cbai.common.utils;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.AbstractCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

public class CaptchaServlet_1 extends HttpServlet {
	
	private static final long serialVersionUID = 4968328161261528097L;
	private static MyCaptchaService cs = null;

	@Override
	public void init() throws ServletException {
		super.init();
		// 可直接使用ConfigurableCaptchaService，然后修改配置
		cs = new MyCaptchaService();
	}

	@Override
	public void destroy() {
		cs = null;
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");
		
		HttpSession session = request.getSession(true);
		
		OutputStream os = response.getOutputStream();
		
		String width = request.getParameter("w");
		
		String height = request.getParameter("h");
		
		//String patchcaPath = this.getServletContext().getRealPath("\\")+"\\img\\patchca.png";
		
		//System.out.println("====>"+patchcaPath);
		
		//OutputStream fos = new FileOutputStream(patchcaPath);
		
		cs.setWidth(Integer.valueOf(width));
		
		cs.setHeight(Integer.valueOf(height));  
		
		RandomFontFactory fontFactory = new RandomFontFactory(); 
		
		fontFactory.setMaxSize(32);

		fontFactory.setMinSize(28);   
		
		cs.setFontFactory(fontFactory);  
		
		String patchca = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
		
		session.setAttribute(request.getParameter("p"), patchca);
		
		os.flush();
		os.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	
	/**
	 * 初始化验证码
	 * @author 
	 */
	private class MyCaptchaService extends AbstractCaptchaService {
		public MyCaptchaService() {
			// 文本内容
			wordFactory = new MyWordFactory();
			// 字体
			//fontFactory = new RandomFontFactory();
			// 效果
			textRenderer = new BestFitTextRenderer();
			// 背景
			backgroundFactory = new SingleColorBackgroundFactory();
			// 字体颜色
			colorFactory = new SingleColorFactory(new Color(25, 60, 170));

			//干扰线波纹
			filterFactory = new CurvesRippleFilterFactory(colorFactory);
			
			 //private static CurvesRippleFilterFactory crff = null;  //干扰线波纹  
		    //private static MarbleRippleFilterFactory mrff = null;  //大理石波纹  
		    //private static DoubleRippleFilterFactory drff = null;  //双波纹  
			// 图片长宽
			//width = 150;
			//height = 50;
		}
	}

	private class MyWordFactory extends RandomWordFactory {
		
		public MyWordFactory() {
			// 文本范围和长度
			characters = "abcdek23456789";
			minLength = 4;
			maxLength = 4;
		}
		
	}
}
