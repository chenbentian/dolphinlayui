/**
 *
 * @(#) SysApplicationDao.java
 * @Package com.bt.dolphin.system.menu.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.system.menu.vo.SysApplicationVo;

/**
 *  类描述：系统应用
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月4日 下午2:52:54   cbt-34201   Created.
 *           
 */
public interface SysApplicationDao {
	
	/**
	 * 
	 * 方法说明：获取所有应用
	 *
	 * Author：        cbt               
	 * Create Date：   2020年9月4日 下午3:06:29
	 * History:  2020年9月4日 下午3:06:29   cbt-34201   Created.
	 *
	 * @return
	 *
	 */
	public List<SysApplicationVo> getAllApplication();
	/**
	 * 
	 * 方法说明：根据应用名称获取应用
	 *
	 * Author：        cbt               
	 * Create Date：   2020年9月4日 下午3:08:37
	 * History:  2020年9月4日 下午3:08:37   cbt-34201   Created.
	 *
	 * @param appName
	 * @return
	 *
	 */
	public SysApplicationVo getAppByAppId(@Param("appId") String appId);
	
	public SysApplicationVo getAppIdByAppName(@Param("appName") String appName);
}
