package com.cbai.common.service; 

import java.sql.SQLException;  
import java.util.List;
import java.util.Map;

import com.cbai.common.page.Pagination;

public interface BaseService { 
	
	
	/** 
	 * 插入
	 */
	public Object save(String sqlID, Object paramObject) throws SQLException;
	
	/**
	 * 
	 * 删除
	 */
	public int delete(String sqlID, Object paramObject) throws SQLException;
	
	/**
	 * 
	 * 有序删除
	 */
	public int deleteSort(String tableName, Object delParam,Object delSearchParam) throws SQLException;
 
	/**
	 * 
	 * 更新单条
	 */
	public int update(String sqlID, Object paramObject) throws SQLException;
	
	/**
	 * 
	 * 查询返回单个对象
	 */
	public <T>T queryObject(String sqlID, Object paramObject) throws SQLException;
	
	
 
	/**
	 * 
	 * 查询特定SQL语句的总条数
	 */
	public int getTotalRecordCnt(String sqlID, Object paramObject) throws SQLException;
 
	/**
	 * 
	 * 查询返回多条记录,不分页
	 */
	public <T1> List<T1> queryList(String sqlID, Object paramObject) throws SQLException;
	
	 /**
     * 分页查询,返回分页了的List对象 
     */
	public <T> List<T> queryPaginatedList(String sqlId, Object parameter, int skip,
            int pageSize) throws SQLException;
    
    /**
     * 分页查询,返回Pagination对象 
     */
    public Pagination queryPagination(String sqlId, Object parameter, int skip,
            int pageSize)throws SQLException;
	
	/**
	 * 
	 * 通用更新索引
	 * 
	 * idName:主键名
	 * tableName:表名
	 * idValue:主键值
	 * newIndex:新索引值
	 */
	public void updateIndex(String idName, String tableName,Integer idValue,Integer oldOrderNum,Integer newOrderNum,Map<String, String> ... sortMaps) throws SQLException;

}
