package com.cbai.model.common.permission.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cbai.model.common.ibatis.entity.PerMenu; 

public interface PerMenuService {
	 
	 public void savePerMenu(PerMenu perMenu) throws Exception; 
	
	 public void updatePerMenu(PerMenu perMenu) throws Exception; 

}
