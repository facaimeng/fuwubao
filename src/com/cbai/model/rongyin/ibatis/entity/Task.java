package com.cbai.model.rongyin.ibatis.entity;

import java.io.Serializable;

public class Task implements Serializable{
	
	private static final long serialVersionUID = 3461024252069147480L;

	protected Integer taskId;
	
	/**任务执行代码**/
	protected String taskCode;
	
	/**任务类型(1首页静态化、2栏目页静态化、3内容页静态化、4采集、5分发)'**/
	protected String taskType;
	
	/**任务名称**/
	protected String taskName;
	
	/**任务类**/
	protected String jobClass;
	
	/**执行周期分类(1非表达式 2 cron表达式)**/
	protected Integer execycle;
	
	/**每月的哪天**/
	protected Integer dayOfMonth;
	
	/**周几**/
	protected Integer dayOfWeek;
	
	/**小时**/
	protected Integer hour;
    
	/**分钟**/
    protected Integer minute;
    
    /**间隔小时**/
	protected Integer intervalHour;
	
	/**间隔分钟**/
	protected Integer intervalMinute;
	
	/**1分钟、2小时、3日、4周、5月**/
	protected Integer taskIntervalUnit;
	
	/**规则表达式**/
	protected String cronExpression;
	
	/**是否启用**/
	protected Boolean isEnable;
	
	/**任务说明**/
	protected String taskRemark;
	
	/**创建者**/
	protected Integer userId;
	
	/**创建时间**/
	protected String createTime;

	public Task() { }

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public Integer getExecycle() {
		return execycle;
	}

	public void setExecycle(Integer execycle) {
		this.execycle = execycle;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getIntervalHour() {
		return intervalHour;
	}

	public void setIntervalHour(Integer intervalHour) {
		this.intervalHour = intervalHour;
	}

	public Integer getIntervalMinute() {
		return intervalMinute;
	}

	public void setIntervalMinute(Integer intervalMinute) {
		this.intervalMinute = intervalMinute;
	}

	public Integer getTaskIntervalUnit() {
		return taskIntervalUnit;
	}

	public void setTaskIntervalUnit(Integer taskIntervalUnit) {
		this.taskIntervalUnit = taskIntervalUnit;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getTaskRemark() {
		return taskRemark;
	}

	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}