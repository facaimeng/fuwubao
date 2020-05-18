package com.cbai.model.rongyin.sys.products.action;

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
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;  
import com.cbai.model.rongyin.ibatis.entity.ProductType;

@Controller
public class ProducttypeMgAction {
	
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/products/type/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
		
    	return "/admin/products/type/list_type";
		
	}
 
	@RequestMapping(value="/products/type/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit) throws Exception {  
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("product_type.getAll", null, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
 	
	}
	
	@RequestMapping(value="/products/type/enter_add/", method=RequestMethod.GET)
	public String enter_add() throws Exception{ 
		  
		return "/admin/products/type/add_type";
		
	}
	
	@RequestMapping(value="/products/type/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,ProductType prot) throws Exception{ 
		
		prot.setState(State.NORMAL);
		
		prot.setSindex(baseService.getTotalRecordCnt("product_type.getAll", null)+1);
		
		baseService.save("product_type.insert", prot);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));   
		
	}
	
	@RequestMapping(value="/products/type/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("prot", baseService.queryObject("product_type.getOne", id));
		  
		return "/admin/products/type/edit_type";
		
	}
	
	@RequestMapping(value="/products/type/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,ProductType prot) throws Exception{
		   
		baseService.update("product_type.update", prot);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/products/type/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		ProductType prot = new ProductType();
			
		prot.setPrtid(id);
		
		prot.setState(State.valueOf(state));
		
		baseService.update("product_type.update", prot);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/products/type/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,ProductType prot) throws Exception{
		
		if(prot.getOldOrderNum().intValue()!=prot.getNewOrderNum().intValue()){
			
			baseService.updateIndex("prtid", "product_type", prot.getPrtid(), prot.getOldOrderNum(),prot.getNewOrderNum());
		} 
		
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
