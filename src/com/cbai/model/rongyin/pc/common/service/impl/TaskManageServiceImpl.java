package com.cbai.model.rongyin.pc.common.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.model.rongyin.ibatis.entity.Task;
import com.cbai.model.rongyin.pc.common.service.TaskManageService;

public class TaskManageServiceImpl implements TaskManageService{
	
	private IbatisBaseDao baseDao; 
	
	
	@Override
	public List<Task> getTaskList() throws SQLException {
		return baseDao.queryList("task_front.allTasks", null);
	}

	@Override
	public String getCronExpressionFromDB(Integer taskId) throws SQLException {
		return baseDao.queryObject("task_front.cronExpression", taskId);
	}

	@Autowired
	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}    
	
}
