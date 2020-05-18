package com.cbai.model.common.permission.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.utils.PropertyUtils; 
import com.cbai.model.common.ibatis.entity.PerMenu;
import com.cbai.model.common.permission.service.PerMenuService; 

public class PerMenuServiceImpl implements PerMenuService{
	
	private IbatisBaseDao baseDao;  
	
	@Override
	public void savePerMenu(PerMenu perMenu) throws Exception { 
		 
		baseDao.insert("per_menu.insert", perMenu);
		
		baseDao.update("per_menu.updateIsdir", perMenu.getFid()); 
		
	}

	 
	@Override
	public void updatePerMenu(PerMenu perMenu) throws Exception {
		
		baseDao.update("per_menu.updateOne", perMenu);
		
		baseDao.update("per_menu.updatePath", perMenu);
	} 

	public IbatisBaseDao getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	


	

}
