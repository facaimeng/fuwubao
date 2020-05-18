package com.cbai.model.rongyin.sys.products.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.common.data.State;   
import com.cbai.model.rongyin.ibatis.entity.Products;
import com.cbai.model.rongyin.sys.products.vo.ProductSearchVO;

@Controller
public class ProductMgAction {
	
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    
    
    @RequestMapping(value="/products/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request) throws Exception {
    	
    	request.setAttribute("protList", baseService.queryList("product_type.getByState", State.NORMAL.toString()));
		
    	return "/admin/products/list_product";
		
	}
 
	@RequestMapping(value="/products/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,ProductSearchVO searchVO,Integer pageIndex,Integer limit) throws Exception { 
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page = baseService.queryPagination("products.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit);
		
		result.setRows(page.getList());
		
		result.setResults(page.getTotalCount()); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
 	
	}
	
	@RequestMapping(value="/products/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request) throws Exception{ 
		 
		request.setAttribute("protList", baseService.queryList("product_type.getByState", State.NORMAL.toString()));
		  
		return "/admin/products/add_product";
		
	}
	
	@RequestMapping(value="/products/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,Products pro) throws Exception{ 
		
		pro.setStartbidmoney(Long.valueOf(AmountUtils.changeY2F(pro.getStartbidmoneyStr())));
		
		pro.setMaxbidmoney(Long.valueOf(AmountUtils.changeY2F(pro.getMaxbidmoneyStr()))); 
		
		pro.setViewcount(0);
		
		pro.setStockmoney(0);
		
		pro.setSales(0);
		
		pro.setIftop(State.N.toString());
		
		pro.setState(State.NORMAL);
		
		pro.setSindex(Integer.valueOf(baseService.queryObject("products.getCount", pro.getPrtid()).toString())+1);
		
		pro.setAddtime(new Date());
		
		baseService.save("products.insert", pro);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));   
		
	}
	
	@RequestMapping(value="/products/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{ 
		
		request.setAttribute("pro", baseService.queryObject("products.getOne", id));
		  
		return "/admin/products/edit_product";
		
	}
	
	@RequestMapping(value="/products/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Products pro) throws Exception{
		
		pro.setStartbidmoney(Long.valueOf(AmountUtils.changeY2F(pro.getStartbidmoneyStr())));
		
		pro.setMaxbidmoney(Long.valueOf(AmountUtils.changeY2F(pro.getMaxbidmoneyStr()))); 
		   
		baseService.update("products.update", pro);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/products/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		Products pro = new Products();
			
		pro.setPrid(id);
		
		pro.setState(State.valueOf(state));
		
		baseService.update("products.update", pro);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/products/set_top/", method=RequestMethod.POST)
	public void set_top(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String iftop = null;
		
		if(op.equals("top")){
			
			iftop = "Y"; 
			
		}else if(op.equals("untop")){
			
			iftop = "N";	 
		}
		Products pro = new Products();
			
		pro.setPrid(id);
		
		pro.setIftop(iftop); 
		
		baseService.update("products.update", pro);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/products/sort/", method=RequestMethod.POST)
	public void sort(HttpServletResponse response,Products pro) throws Exception{
		
		if(pro.getOldOrderNum().intValue()!=pro.getNewOrderNum().intValue()){
			
			Map<String, String> sortMaps = new HashMap<String, String>();
			
			sortMaps.put("prtid", pro.getPrtid().toString());
			
			baseService.updateIndex("prid", "products", pro.getPrid(), pro.getOldOrderNum(),pro.getNewOrderNum(),sortMaps);
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
