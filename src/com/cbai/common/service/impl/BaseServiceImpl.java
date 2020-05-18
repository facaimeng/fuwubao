package com.cbai.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;  
 
import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.page.Pagination;
import com.cbai.common.service.BaseService;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
 

public class BaseServiceImpl implements BaseService{
	
	private IbatisBaseDao baseDao;
	
	private SqlMapClient sqlMapClient; 
	 
	
	/**
	 * 
	 * 插入
	 */
	@Override
	public Object save(String sqlID, Object paramObject) throws SQLException{
		
		return baseDao.insert(sqlID, paramObject);
		  
	}
	
	/**
	 * 
	 * 删除
	 */
	@Override
	public int delete(String sqlID, Object paramObject) throws SQLException{
		
		  
		return baseDao.delete(sqlID, paramObject);
		  
	}
	
	/**
	 * 
	 * 有序删除
	 */
	@Override
	public int deleteSort(String tableName, Object delParam,Object delSearchParam) throws SQLException{
		
		int delCount ; 

		delCount = baseDao.delete(tableName+".delete", delParam);
		
		List<Map<String,Integer>> delSearchList = baseDao.queryList(tableName+".delSearch", delSearchParam);
		
		int length = delSearchList.size();
		
		int i = 0;
		
		for(Map<String,Integer> newObj : delSearchList){
			
			newObj.put("sindex", length-i);
			
			i++; 
		}
		baseDao.batchUpdate(tableName+".delUpdate", delSearchList);
		  
		return delCount;
		  
	}
 
	/**
	 * 
	 * 更新
	 */
	@Override
	public int update(String sqlID, Object paramObject) throws SQLException{
		
		  
		return baseDao.update(sqlID, paramObject);
		  
	} 
	
	/**
	 * 
	 * 查询返回单个对象
	 */
	@Override
	public Object queryObject(String sqlId, Object parameter)throws SQLException {
		return baseDao.queryObject(sqlId, parameter);
	}
	 
	
	/**
	 * 
	 * 查询返回多条记录,不分页
	 */
	@Override
	public <T1> List<T1> queryList(String sqlID, Object paramObject) throws SQLException{
 
		return baseDao.queryList(sqlID, paramObject);
 	  
	}   

	 /**
     * 分页查询,返回分页了的List对象 
     */
	@Override 
    public List<?> queryPaginatedList(String sqlId, Object parameter, int skip,
            int pageSize) throws SQLException {
    	return baseDao.queryPaginatedList(sqlId, parameter, skip, pageSize);
    }
  
	
	/**
     * 分页查询,返回Pagination对象 
     */
    @Override
    public Pagination queryPagination(String sqlId, Object parameter, int skip,
            int pageSize)throws SQLException {
    	
    	return baseDao.queryPagination(sqlId, parameter, skip, pageSize);
    } 
	  
	/**
	 * 
	 * 查询特定SQL语句的总条数
	 */
	public int getTotalRecordCnt(String sqlID, Object paramObject) throws SQLException{
		 
		return baseDao.getTotalRecordCnt(sqlID, paramObject);
		  
	}
	
	/**
	 * 
	 * 通用更新索引
	 * 
	 * idName:主键名
	 * tableName:表名
	 * idValue:主键值
	 * newIndex:新索引值
	 */
	public void updateIndex(String idName, String tableName,Integer idValue,Integer oldOrderNum,Integer newOrderNum,Map<String, String> ... sortMaps) throws SQLException{
			SqlMapClientImpl clientImpl = (SqlMapClientImpl) sqlMapClient;
		
			String conditionSQL = "";
		
			if(sortMaps!=null&&sortMaps.length==1){
				
				Map<String, String> sortMap = sortMaps[0];
				
				for(Map.Entry<String, String> entry : sortMap.entrySet()){ 

					conditionSQL = conditionSQL + " and " + entry.getKey() + "='" +entry.getValue()+ "'"; 
		        }  
			} 
			
			String getOldIndexSQL  = "select sindex from " + tableName + " where " + idName + " =?";
		
			String upSelfSQL  = "update " + tableName + " set sindex = ? where " + idName + " = ?";
			 
			String moreThanHQL  = "update " + tableName + " set sindex = sindex + ? where sindex >= ? and sindex < ?";
			
			String lessThanHQL  = "update " + tableName + " set sindex = sindex + ? where sindex >  ? and sindex <= ?";
	 
	        Connection conn = null;
	        
	        PreparedStatement ps = null;
	        
	        PreparedStatement ps1 = null;
	        
	        PreparedStatement ps2 = null;
	        
	        ResultSet rs = null;

	        int oldIndex = 0;
	        
	        int newIndex = 0;

	        try {
	            conn = clientImpl.getDataSource().getConnection();
	            
	            ps = conn.prepareStatement(getOldIndexSQL);
	            
	            ps.setObject(1, idValue);
 
	            rs = ps.executeQuery();
	            
	            if (rs.next()) {
	            	oldIndex = rs.getInt(1);
	            }
	            
	          //  conn.setAutoCommit(false);
	            
	            int length = Math.abs(oldOrderNum-newOrderNum); 
	            
	            if(oldOrderNum < newOrderNum){//往前移
	            	
	            	newIndex = oldIndex-length;
   	
	            	ps1 = conn.prepareStatement(moreThanHQL+conditionSQL);
	            	
	            	ps1.setObject(1, 1);
	            	
	            	ps1.setObject(2, newIndex);
	            	
	            	ps1.setObject(3, oldIndex);
	            	
	            	ps1.executeUpdate();

	            }else if(oldOrderNum > newOrderNum){//往后移动
	            	
	            	newIndex = oldIndex+length;

	            	ps1 = conn.prepareStatement(lessThanHQL+conditionSQL);
	            	
	            	ps1.setObject(1, -1);
	            	
	            	ps1.setObject(2,oldIndex);
	            	
	            	ps1.setObject(3,newIndex);
	            	
	            	ps1.executeUpdate();	            	
	            }
	            //update ipos_news set sindex = sindex + -1 where sindex >  2 and sindex <= 3 and ntype='helps'
            	ps2 = conn.prepareStatement(upSelfSQL);
            	
            	ps2.setObject(1, newIndex);
            	
            	ps2.setObject(2, idValue);
            	
            	ps2.executeUpdate();
   	
            	//conn.commit();
         
	        }finally {
	            if (rs != null) {
	            	rs.close();
	            }

	            if (ps != null) {
	            	ps.close();
	            }
	            if (ps1 != null) {
	            	ps1.close();
	            }
	            if (ps2 != null) {
	            	ps2.close();
	            }

	            if (conn != null) {
	            	conn.close();
	            }
	        }	  
	}

	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
 

	

}
