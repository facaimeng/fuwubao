package com.cbai.model.rongyin.sys.stat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.web.ResponseUtils;
import com.cbai.model.rongyin.sys.stat.vo.UserSearchVO;
 

/*
 * 员工统计
 * 
 * */
@Controller 
@RequestMapping(value="system")
public class UsersStatAction {
	
	private BaseService baseService; 

	@RequestMapping(value="/stat/userSale/enter_search/", method=RequestMethod.GET)
	public String enter_search(HttpServletRequest request) throws Exception{
		
		return "/admin/stat/user_sale";
		
	}
	
	@RequestMapping(value="/stat/userSale/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,UserSearchVO searchVO) {
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page;
		try {
			page = baseService.queryPagination("sys_stat.userSale", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit); 
			
			result.setRows(page.getList());
			
			result.setResults(page.getTotalCount()); 
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
}
