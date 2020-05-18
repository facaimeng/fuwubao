package com.cbai.model.rongyin.api.invest.action;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;

@Controller 
public class InvestAction {
	
	
	private BaseService baseService; 
	 
    private PropertyUtils propertyUtils; 
    

    /**
	 * 进入投资列表页
	 */
    @RequestMapping(value="/page/invest/", method=RequestMethod.GET)
	public String page_invest() throws Exception {
		
    	return "/mobile/invest/invest_list";
		
	}
    
    /**
	 * 进入投资详情页
	 */
    @RequestMapping(value="/page/invest/detail/", method=RequestMethod.GET)
	public String page_invest(Integer id) throws Exception {
    	
    	if(id==null){
    		return "/mobile/error.jsp";
    	}
		
    	return "/mobile/invest/invest_detail";
		
	}
    
    /**
	 * 获得产品类别列表
	 * @param 
	 * @return list
	 * @throws Exception
	 */
    @RequestMapping(value="/api/product/type/", method=RequestMethod.GET)
	public void api_product_type(HttpServletResponse response) throws Exception {
    	
    	//state = normal
    	
	}
    
    /**
	 * 获得某类产品类别下的所有产品
	 * @param 
	 * @return list
	 * @throws Exception
	 */
    @RequestMapping(value="/api/product/list/", method=RequestMethod.GET)
	public void api_product_list(HttpServletResponse response,Integer id) throws Exception {
    	
    	if(id==null){
    		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString("error.op")))); 
    	}
    	
    	//state = normal
    	
	}
    
    /**
	 * 获得某个产品详情
	 * @param 
	 * @return detail
	 * @throws Exception
	 */
    @RequestMapping(value="/api/product/", method=RequestMethod.GET)
	public void api_product_detail(HttpServletResponse response,Integer id) throws Exception {
    	
    	if(id==null){
    		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("0",propertyUtils.getPropertiesString("error.op")))); 
    	}
    	
    	//state = normal
    	
	}
    
 
    
    public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

}
