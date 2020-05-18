package com.cbai.model.common.permission.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.json.JsonUtils;
import com.cbai.common.utils.PropertyUtils;   
import com.cbai.model.common.ibatis.entity.PerRole;
import com.cbai.model.common.ibatis.entity.PerUsers; 
import com.cbai.model.common.ibatis.entity.PerUsersRoletRel;
import com.cbai.model.common.permission.service.PerUserService;

public class PerUserServiceImpl implements PerUserService{
	
	private IbatisBaseDao baseDao;  
	

	@Override
	public void savePerUser(PerUsers perUser) throws Exception {
		
		Integer userid = (Integer) baseDao.insert("per_users.insert", perUser);
		
		//Integer menuIDS = perRole.getSelMenuIDS().split(regex);
		
		List<PerRole> prList = (List<PerRole>) JsonUtils.json2list(perUser.getRoleJson(), PerRole.class);
		
		List<PerUsersRoletRel> prurList = new ArrayList<PerUsersRoletRel>(); 
		
		for(PerRole role : prList){
			
			PerUsersRoletRel purl = new PerUsersRoletRel();
			
			purl.setUserid(userid); 
			
			purl.setRoleid(role.getRoleid());
			 
			prurList.add(purl);
		} 
		
		baseDao.batchInsert("per_users_role_rel.insert", prurList);
		 
	}

	 
	@Override
	public void updatePerUser(PerUsers perUser,String isRoleChange,String oldphone) throws Exception {
		
		baseDao.update("per_users.updateOne", perUser); 
		
		if(!perUser.getPhone().equals("oldphone")){
			
			Map  map = new HashMap();
			
			map.put("phone", perUser.getPhone());
			
			map.put("oldphone", oldphone);
			
			map.put("mytype", "1");
			
			baseDao.update("members.upDad", map);
		}
		
		if(!isRoleChange.equals("N")){
			baseDao.delete("per_users_role_rel.delete", perUser.getUserid());
			
			if(perUser.getRoleJson()!=null && !perUser.getRoleJson().equals("")){
				
				List<PerRole> prList = (List<PerRole>) JsonUtils.json2list(perUser.getRoleJson(), PerRole.class);
				
				List<PerUsersRoletRel> prurList = new ArrayList<PerUsersRoletRel>(); 
				
				for(PerRole role : prList){
					
					PerUsersRoletRel purl = new PerUsersRoletRel();
					
					purl.setUserid(perUser.getUserid()); 
					
					purl.setRoleid(role.getRoleid());
					 
					prurList.add(purl);
				} 
				baseDao.batchInsert("per_users_role_rel.insert", prurList); 
			} 
		}  
	} 

	public IbatisBaseDao getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	} 

}
