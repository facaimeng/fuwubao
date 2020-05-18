package com.cbai.model.rongyin.sys.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.service.BaseService;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.utils.UpAndDownUtil;
import com.cbai.model.rongyin.ibatis.entity.CompanyAttach;
import com.cbai.model.rongyin.ibatis.entity.ProjectAttach;


@Controller 
@RequestMapping(value="system")
public class AdminDownAction {
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils;
	
	@RequestMapping(value="/common/download/company_attach/", method=RequestMethod.GET)
	public void company_attach(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception  {
		
		CompanyAttach catt = baseService.queryObject("company_attach.getOne", id);
		
		UpAndDownUtil.downLoad(request.getSession().getServletContext().getRealPath("/")+catt.getFurl(), catt.getName()+"."+catt.getExt(), response);
		
	}
	
	@RequestMapping(value="/common/download/project_attach/", method=RequestMethod.GET)
	public void project_attach(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception  {
		
		ProjectAttach patt = baseService.queryObject("project_attach.getOne", id);
		
		UpAndDownUtil.downLoad(request.getSession().getServletContext().getRealPath("/")+patt.getFurl(), patt.getName()+"."+patt.getExt(), response);
		
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}
	
	

}
