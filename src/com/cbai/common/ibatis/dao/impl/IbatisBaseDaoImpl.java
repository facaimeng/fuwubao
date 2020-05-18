package com.cbai.common.ibatis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.common.page.Pagination;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

@SuppressWarnings("unchecked")
public class IbatisBaseDaoImpl implements IbatisBaseDao {
	
	private SqlMapClient sqlMapClient; 
	 
	@Override
	public Object insert(String sqlId,Object parameter) throws SQLException{
		return sqlMapClient.insert(sqlId, parameter);
	}
	
	@Override
    public int batchInsert(String sqlId, Object[] paraObjects)
            throws SQLException {

        if (paraObjects == null || paraObjects.length == 0) {
            return 0;
        } 
        
        sqlMapClient.startBatch();
        
        for (Object o : paraObjects) {
        	sqlMapClient.insert(sqlId, o);
        }
        
        return sqlMapClient.executeBatch();
    }
    
    @Override
    public int batchInsert(String sqlId, Collection paraCollection)
            throws SQLException {
        if (paraCollection == null || paraCollection.size() == 0) {
            return 0;
        }

        Object[] paraObjects = new Object[paraCollection.size()];
        paraObjects = paraCollection.toArray(paraObjects);
        return batchInsert(sqlId, paraObjects);
    }

    @Override
    public int delete(String sqlId, Object parameter) throws SQLException {
        return sqlMapClient.delete(sqlId, parameter);
    }
    

    @Override
    public int update(String sqlId, Object parameter) throws SQLException {
        return sqlMapClient.update(sqlId, parameter);
    }
    
    @Override
    public int batchUpdate(String sqlId, Object[] paraObjects)
            throws SQLException {
        if (paraObjects == null || paraObjects.length == 0) {
            return 0;
        } 
        sqlMapClient.startBatch();
        for (Object o : paraObjects) {
        	sqlMapClient.update(sqlId, o);
        }
        return sqlMapClient.executeBatch();
    }
 
    @Override
    public int batchUpdate(String sqlId, Collection paraCollection)
            throws SQLException {
        if (paraCollection == null || paraCollection.size() == 0) {
            return 0;
        } 
        Object[] paraObjects = new Object[paraCollection.size()];
        paraObjects = paraCollection.toArray(paraObjects);
        return batchUpdate(sqlId, paraObjects);
    }
    
    public Object queryObject(String sqlId, Object parameter)
            throws SQLException {
        return sqlMapClient.queryForObject(sqlId, parameter);
    }

    @Override
    public List<?> queryList(String sqlId, Object parameter) throws SQLException {
        return sqlMapClient.queryForList(sqlId, parameter);
    } 
    
    @Override
    public List<?> queryPaginatedList(String sqlId, Object parameter, int skip,
            int pageSize) throws SQLException {
    	return sqlMapClient.queryForList(sqlId, parameter, skip, pageSize);
    }
    
    @Override
    public Pagination queryPagination(String sqlId, Object parameter, int skip,
            int pageSize)throws SQLException {
    	
    	int totalCount = getTotalRecordCnt(sqlId, parameter);

    	Pagination pagintion = new Pagination(skip, pageSize, totalCount);
    	
		if (totalCount < 1) {
			pagintion.setList(new ArrayList());
			return pagintion;
		}
		
		pagintion.setList(queryPaginatedList(sqlId, parameter, skip, pageSize));
    	
		return pagintion;
    }

    @Override
    public int getTotalRecordCnt(String sqlId, Object parameter){
        
    	 SqlMapClientImpl clientImpl = (SqlMapClientImpl) sqlMapClient;
        MappedStatement mappedStatement = clientImpl.getMappedStatement(sqlId);

        Sql sql = mappedStatement.getSql();

        StatementScope statementScope = new StatementScope(new SessionScope());
        mappedStatement.initRequest(statementScope);

        String strSql = sql.getSql(statementScope, parameter);
        // select * from sys_log where accName=? and beginDate=?
        strSql = "select count(1) from (" + strSql + ") tmp";

        Object[] vals = null;
        if (parameter != null) {
            vals = sql.getParameterMap(statementScope, parameter)
                    .getParameterObjectValues(statementScope, parameter);
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int cnt = 0;

        try {
            conn = clientImpl.getDataSource().getConnection();
            ps = conn.prepareStatement(strSql);
            if (vals != null && vals.length > 0) {
                for (int i = 0; i < vals.length; i++) {
                    ps.setObject(i + 1, vals[i]);
                }
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                cnt = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return cnt;
    }
    
    
    @Autowired
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
    
}