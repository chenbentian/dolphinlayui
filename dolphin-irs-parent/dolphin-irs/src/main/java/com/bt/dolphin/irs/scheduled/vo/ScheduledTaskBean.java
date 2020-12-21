/**
 *
 * @(#) ScheduledTaskBean.java
 * @Package com.bt.dolphin.irs.scheduled.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.scheduled.vo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月10日 下午12:25:00   cbt-34201   Created.
 *           
 */
public class ScheduledTaskBean {
	/**
     * 任务key值 唯一
     */
    private String taskKey;
    /**
     * 任务描述
     */
    private String taskDesc;
    /**
     * 任务表达式
     */
    private String taskCron;

    /**
     * 程序初始化是否启动 1 是 0 否
     */
    private String initStartFlag;

    /**
     * 当前是否已启动
     */
    private boolean startFlag;

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskCron() {
		return taskCron;
	}

	public void setTaskCron(String taskCron) {
		this.taskCron = taskCron;
	}

	
	public String getInitStartFlag() {
		return initStartFlag;
	}

	public void setInitStartFlag(String initStartFlag) {
		this.initStartFlag = initStartFlag;
	}

	public boolean isStartFlag() {
		return startFlag;
	}

	public void setStartFlag(boolean startFlag) {
		this.startFlag = startFlag;
	}
    
    
}
