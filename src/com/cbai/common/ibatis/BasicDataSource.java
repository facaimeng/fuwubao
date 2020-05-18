package com.cbai.common.ibatis;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * 
 *Titile:���Դ<br>
 *Description:���Դ<br> 
 *Date: Mar 8, 2013<br> SWEET-LINE
 *Copyright (c) 2013 <br> 
 *
 *Author xiacj
 */
public class BasicDataSource extends org.apache.commons.dbcp.BasicDataSource{

	
	
	@Override
	public synchronized void setDefaultAutoCommit(boolean defaultAutoCommit) {
		
		super.setDefaultAutoCommit(defaultAutoCommit);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void setPassword(String password) {
		// TODO �û���������Ҫ���ܴ���
		super.setPassword(password);
	}

	@Override
	public synchronized void setUsername(String username) {
		// TODO  �û���������Ҫ���ܴ���
		super.setUsername(username);
	}
	
	
	public static void main(String[] args) throws SQLException{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.getConnection();
		
	}
	
}
