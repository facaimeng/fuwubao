package com.cbai.model.rongyin.pc.common.service;

import java.sql.SQLException;
import java.util.List;

import com.cbai.model.rongyin.ibatis.entity.Task;

public interface TaskManageService {
	
    public List<Task> getTaskList() throws SQLException;
    
    public String getCronExpressionFromDB(Integer taskId) throws SQLException;
    
}
