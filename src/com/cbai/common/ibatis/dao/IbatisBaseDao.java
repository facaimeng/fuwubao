package com.cbai.common.ibatis.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.cbai.common.page.Pagination;

/**
 * Ibatis 基础Dao接口
 * @author Administrator
 *
 */
public interface IbatisBaseDao {
	
	 /**
     * 插入单个对象
     * @param sqlId
     * @param parameter
     * @return
     * @throws SQLException
     */
    public Object insert(String sqlId, Object parameter) throws SQLException;
    
    /**
     * 批量插入 数组对象
     * @param sqlId
     * @param paraObjects
     * @return
     * @throws SQLException
     */
    public int batchInsert(String sqlId, Object[] paraObjects)
            throws SQLException;

    /**
     * 批量插入 集合对象
     * @param sqlId
     * @param paraCollection
     * @return
     * @throws SQLException
     */
    public int batchInsert(String sqlId, Collection paraCollection)
            throws SQLException;
    
    /**
     * 删除单个对象
     * @param sqlId
     * @param parameter
     * @return
     * @throws SQLException
     */
    public int delete(String sqlId, Object parameter) throws SQLException;
    
    /**
     * 编辑单个对象
     * @param sqlId
     * @param parameter
     * @return
     * @throws SQLException
     */
    public int update(String sqlId, Object parameter) throws SQLException;
 
    /**
     * 批量更新 数组对象
     * @param sqlId
     * @param paraObjects
     * @return
     * @throws SQLException
     */
    public int batchUpdate(String sqlId, Object[] paraObjects)
            throws SQLException;
    
    /**
     * 批量更新 集合对象
     * @param sqlId
     * @param paraObjects
     * @return
     * @throws SQLException
     */
    public int batchUpdate(String sqlId, Collection paraCollection)
            throws SQLException;
    
	/**
	 * 查询单个对象
	 * 
	 * @param sqlId
	 * @param parameter
	 * @return
	 * @throws SQLException
	 */
	public <T> T queryObject(String sqlId, Object parameter)
            throws SQLException;

	/**
	 * 不分页列表查询
	 * 
	 * @param sqlId
	 * @param parameter
	 * @return
	 * @throws SQLException
	 */
    public <T> List<T> queryList(String sqlId, Object parameter) throws SQLException;
 

    /**
     * 分页查询,返回分页了的List对象
     * @param sqlId
     * @param parameter
     * @param skip
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public <T> List<T> queryPaginatedList(String sqlId, Object parameter, int skip,
            int pageSize) throws SQLException;
    
    /**
     * 分页查询,返回Pagination对象
     * @param sqlId
     * @param parameter
     * @param skip
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public Pagination queryPagination(String sqlId, Object parameter, int skip,
            int pageSize)throws SQLException;
    
    /**
     * 查询特定SQL语句的总条数
     * @param sqlId
     * @param parameter
     * @return
     */
    public int getTotalRecordCnt(String sqlId, Object parameter)throws SQLException;
}