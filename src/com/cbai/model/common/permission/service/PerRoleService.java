package com.cbai.model.common.permission.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map; 

import com.cbai.model.common.ibatis.entity.PerRole;

public interface PerRoleService {
	 
	 public void savePerRole(PerRole perRole) throws Exception; 
	
	 public void updatePerRole(PerRole perRole) throws Exception; 

}
