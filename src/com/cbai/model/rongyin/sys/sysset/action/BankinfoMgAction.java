package com.cbai.model.rongyin.sys.sysset.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.FileUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State; 
import com.cbai.model.rongyin.ibatis.entity.BankInfo; 

@Controller
@RequestMapping(value="system")
public class BankinfoMgAction {
	
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/sysset/bankinfo/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/sysset/bankinfo/list_bank";
		
	}
 
	@RequestMapping(value="/sysset/bankinfo/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit) throws Exception { 
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("bank_info.getAll", null, pageIndex==null?1:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
 	
	}
	
	@RequestMapping(value="/sysset/bankinfo/enter_add/", method=RequestMethod.GET)
	public String enter_add() throws Exception{ 
		  
		return "/admin/sysset/bankinfo/add_bank";
		
	}
	
	@RequestMapping(value="/sysset/bankinfo/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,BankInfo bk) throws Exception{ 
		
		bk.setState(State.NORMAL);
		
		baseService.save("bank_info.insert", bk);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));   
		
	}
	
	@RequestMapping(value="/sysset/bankinfo/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("pcl", baseService.queryObject("bank_info.getOne", id));
		  
		return "/admin/sysset/bankinfo/edit_bank";
		
	}
	
	@RequestMapping(value="/sysset/bankinfo/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,BankInfo bk) throws Exception{
		   
		baseService.update("bank_info.update", bk);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/sysset/bankinfo/del/", method=RequestMethod.GET)
	public void del(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception, IOException{
		
			
		BankInfo bk = baseService.queryObject("bank_info.getOne", id);
		 	 
		try {
			FileUtil.delFiles(request.getSession().getServletContext().getRealPath("/")+bk.getBklogourl());	
		} catch (Exception e) {
			e.printStackTrace();
 
		}  
		baseService.delete("bank_info.delete", id);   
		
		//Map nmap = new HashMap();
		
		//nmap.put("ntype", news.getNtype());
		
		//baseService.deleteSort("news", id, nmap);  
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}

	
    @Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
    
    @Autowired
	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	} 


}
