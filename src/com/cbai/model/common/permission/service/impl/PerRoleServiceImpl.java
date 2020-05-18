package com.cbai.model.common.permission.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.utils.PropertyUtils;  
import com.cbai.model.common.ibatis.entity.PerMenuRoleRel;
import com.cbai.model.common.ibatis.entity.PerRole;
import com.cbai.model.common.permission.service.PerRoleService; 

public class PerRoleServiceImpl implements PerRoleService{
	
	private IbatisBaseDao baseDao;  
	

	@Override
	public void savePerRole(PerRole perRole) throws Exception {
		
		Integer roleid = (Integer) baseDao.insert("per_role.insert", perRole);
		
		//Integer menuIDS = perRole.getSelMenuIDS().split(regex);
		
		List<PerMenuRoleRel> mrrelList = new ArrayList<PerMenuRoleRel>();
		
		String[] menuIDS = perRole.getSelMenuIDS().split("#");
		
		for(String menuid : menuIDS){
			
			PerMenuRoleRel mrrel = new PerMenuRoleRel();
			
			mrrel.setRoleid(roleid);
			
			mrrel.setMenuid(Integer.valueOf(menuid)); 
			
			mrrelList.add(mrrel);
		} 
		
		baseDao.batchInsert("per_menu_role_rel.insert", mrrelList);
		 
	}

	 
	@Override
	public void updatePerRole(PerRole perRole) throws Exception {
		
		baseDao.update("per_role.updateOne", perRole);
		
		Map map = new HashMap();
		
		map.put("roleid", perRole.getRoleid());
		
		if(!perRole.getDelMenuIDS().equals("-1")){
			
			String[] delIDS = perRole.getDelMenuIDS().split(",");
			
			map.put("delIDS", delIDS);
			
			baseDao.delete("per_menu_role_rel.delByIDS", map);
			
		}
		
		if(!perRole.getAddMenuIDS().equals("-1")){
			
			String[] addIDS = perRole.getAddMenuIDS().split(",");
			
			List<PerMenuRoleRel> mrrelList = new ArrayList<PerMenuRoleRel>();
			
			for(String menuid : addIDS){
				
				PerMenuRoleRel mrrel = new PerMenuRoleRel();
				
				mrrel.setRoleid(perRole.getRoleid());
				
				mrrel.setMenuid(Integer.valueOf(menuid)); 
				
				mrrelList.add(mrrel);
			}
			baseDao.batchInsert("per_menu_role_rel.insert", mrrelList); 
			
		}
		
		//List<PerMenuRoleRel> mrList = baseDao.queryList("per_menu_role_rel.getRoleMenu", perRole.getRoleid());
		
		//baseDao.delete("per_menu_role_rel.delete", perRole.getRoleid());
		
		/*List<PerMenuRoleRel> mrrelList = new ArrayList<PerMenuRoleRel>();
		
		String[] menuIDS = perRole.getSelMenuIDS().split("#");
		
		for(String menuid : menuIDS){
			
			PerMenuRoleRel mrrel = new PerMenuRoleRel();
			
			mrrel.setRoleid(perRole.getRoleid());
			
			mrrel.setMenuid(Integer.valueOf(menuid)); 
			
			mrrelList.add(mrrel);
		}
		baseDao.batchInsert("per_menu_role_rel.insert", mrrelList); */
		
		//baseDao.update("per_role.updatePath", perRole);
	} 

	public IbatisBaseDao getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}



	

}
