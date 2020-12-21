/**
 *
 * @(#) ScheduledTaskDao.java
 * @Package com.bt.dolphin.irs.scheduled.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.scheduled.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.irs.scheduled.vo.ScheduledTaskBean;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年8月10日 下午12:25:40 cbt-34201 Created.
 * 
 */
public interface ScheduledTaskDao {
	/**
	 * 根据key 获取 任务信息
	 */
	public ScheduledTaskBean getByKey(@Param("taskKey") String taskKey);

    /**
     * 获取程序初始化需要自启的任务信息
     */
	public List<ScheduledTaskBean> getAllNeedStartTask();

    /**
     * 获取所有任务
     */
	public List<ScheduledTaskBean> getAllTask();
}
