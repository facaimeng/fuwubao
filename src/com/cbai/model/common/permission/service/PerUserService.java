package com.cbai.model.common.permission.service;
 
import com.cbai.model.common.ibatis.entity.PerUsers;
 
public interface PerUserService {
	 
	 public void savePerUser(PerUsers perUser) throws Exception; 
	
	 public void updatePerUser(PerUsers perUser,String isRoleChange,String oldphone) throws Exception; 

}