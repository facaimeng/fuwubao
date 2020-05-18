package com.cbai.model.rongyin.sys.users.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbai.common.json.JsonResultObject;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.json.SysJsonResultObject;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.cbai.common.utils.AmountUtils;
import com.cbai.common.utils.BusinessNumberUtil;
import com.cbai.common.utils.CheckIDCard;
import com.cbai.common.utils.OptUtil;
import com.cbai.common.utils.PropertyUtils;
import com.cbai.common.web.ResponseUtils;

import com.cbai.model.common.data.State; 
import com.cbai.model.rongyin.ibatis.entity.Members;
import com.cbai.model.rongyin.pc.member.service.MemberService;
import com.cbai.model.rongyin.sys.users.vo.MemberSearchVO;


@Controller
@RequestMapping(value="system")
public class MemberMgAction {
	
	
	private BaseService baseService; 
	
	private MemberService memberService;
	 
    private PropertyUtils propertyUtils; 
    
    @RequestMapping(value="/users/member/enter_search/", method=RequestMethod.GET)
	public String enter_search() throws Exception {
    	return "/admin/users/member/list_member";
	}
 
	@RequestMapping(value="/users/member/search/", method=RequestMethod.GET)
	public void search(HttpServletResponse response,Integer pageIndex,Integer limit,MemberSearchVO searchVO) {
		
		SysJsonResultObject result = new SysJsonResultObject(); 
		
		Pagination page;
		try {
			page = baseService.queryPagination("members.getAll", searchVO, pageIndex==null?0:pageIndex, limit==null?15:limit); 
			
			result.setRows(page.getList());
			
			result.setResults(page.getTotalCount()); 
			
			ResponseUtils.renderJson(response, JsonUtils.obj2json(result));   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 
	
	@RequestMapping(value="/users/member/enter_add/", method=RequestMethod.GET)
	public String enter_add(HttpServletRequest request) throws Exception{
		  
		return "/admin/users/member/add_member";
		
	}
	
	@RequestMapping(value="/users/member/add/", method=RequestMethod.POST)
	public void add(HttpServletResponse response,Members members,String avlMoneyStr) throws Exception{
		
		members.setMemnum(BusinessNumberUtil.gainOutBizNoNumber());
		
		members.setLogpwd(OptUtil.getStringMD5(members.getLogpwd()));
		
		members.setState(State.NORMAL); 
		
		if(members.getRegtime()==null){
			
			members.setRegtime(new Date());
		}
		if(members.getAuthtime()==null){
			
			members.setAuthtime(new Date());
		}  
		/*if(members.getDadtype().equals("-1")){
			members.setDadtype("1");
			members.setDadmemid(baseService.queryObject("mem_login.getDefUser", 1).toString());
		}*/
		if(members.getIdnum()!=null && !members.getIdnum().equals("")){
			
			members.setBirth(CheckIDCard.getBirth(members.getIdnum()));
			
			members.setSex(CheckIDCard.getSex(members.getIdnum()));
			
		}else{
			members.setIdnum(null);
		}
		
		if(members.getUsrcustid()!=null && members.getUsrcustid().equals("")){
			
			members.setUsrcustid(null); 
			
		}

		Integer memberId = memberService.saveRegist(members,Long.valueOf(AmountUtils.changeY2F(avlMoneyStr)));
		
		String rs = "1"; 
		
		if(memberId<=0){
			rs = "0";
		}
		JsonResultObject results = new JsonResultObject(); 
		
		results.setCode(rs);
		
		if(rs.equals("1")){
    		results.setMessage(propertyUtils.getPropertiesString("suc.op"));
    	}else{ 
    		results.setMessage(propertyUtils.getPropertiesString("error.unknow")); 
    	} 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(results)); 
		
	} 
	
	@RequestMapping(value="/users/member/detail/", method=RequestMethod.GET)
	public String detail(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("member", baseService.queryObject("members.getOne", id)); 
		
		request.setAttribute("memAccount", baseService.queryObject("mem_account.getOne", id)); 
		  
		return "/admin/users/member/detail_member";
		
	} 
	
	@RequestMapping(value="/users/member/enter_edit/", method=RequestMethod.GET)
	public String enter_edit(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("member", baseService.queryObject("members.getOne", id)); 
		
		request.setAttribute("memAccount", baseService.queryObject("mem_account.getOne", id)); 
		  
		return "/admin/users/member/edit_member";
		
	}
	
	@RequestMapping(value="/users/member/edit/", method=RequestMethod.POST)
	public void edit(HttpServletResponse response,Members member,String oldphone,String avlMoneyStr) throws Exception{
		 
		member.setAvlmoney(Long.valueOf(AmountUtils.changeY2F(avlMoneyStr)));
		
		memberService.updateMemberSys(member,oldphone); 
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		
	}
	
	@RequestMapping(value="/users/member/lock/", method=RequestMethod.POST)
	public void lock(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String state = null;
		
		if(op.equals("lock")){
			
			state = "LOCKED"; 
			
		}else if(op.equals("unlock")){
			
			state = "NORMAL";	 
		}
		Members member = new Members();
			
		member.setMemid(id);
		
		member.setState(State.valueOf(state));
		
		baseService.update("members.update", member);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/users/member/freeze/", method=RequestMethod.POST)
	public void freeze(HttpServletResponse response,String op,Integer id) throws Exception{
		
		String iffreeze = null;
		
		if(op.equals("freeze")){
			
			iffreeze = "Y"; 
			
		}else if(op.equals("unfreeze")){
			
			iffreeze = "N";	 
		}
		Members member = new Members();
			
		member.setMemid(id);
		
		member.setIffreeze(State.valueOf(iffreeze));
		
		baseService.update("members.update", member);
 
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op")))); 
	}
	
	@RequestMapping(value="/users/member/enter_openloan/", method=RequestMethod.GET)
	public String enter_openloan(HttpServletRequest request,Integer id) throws Exception{
		
		request.setAttribute("member", baseService.queryObject("members.getOne", id)); 
		  
		return "/admin/users/member/open_loan";
		
	}
	
	@RequestMapping(value="/users/member/openloan/", method=RequestMethod.POST)
	public void openloan(HttpServletResponse response,Members member) throws Exception{
		
		member.setIfloanman(State.Y);
		
		baseService.update("members.update", member);
		
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject("1",propertyUtils.getPropertiesString("suc.op"))));    
		
	}
	
 
	@RequestMapping(value="/users/member/check_uphone", method=RequestMethod.POST)
	public void check_idnum(HttpServletResponse response,String phone) throws Exception { 
		String flag = null;   
		if(!userIsExist(phone)){
			flag = "1";
		}else{
			flag = "0";
		}
		ResponseUtils.renderJson(response, JsonUtils.obj2json(new JsonResultObject(flag,null))); 
	}
	
	public boolean userIsExist(String phone) throws Exception{
		
		Integer count = baseService.queryObject("mem_login.userIsExist", phone);
		
		return count > 0;
	}
 
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setPropertyUtils(PropertyUtils propertyUtils) {
		this.propertyUtils = propertyUtils;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	} 
	
	

}
