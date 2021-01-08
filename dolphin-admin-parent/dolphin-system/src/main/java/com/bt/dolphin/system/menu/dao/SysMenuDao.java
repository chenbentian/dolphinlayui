/**
 *
 * @(#) SysMenuServiceDao.java
 * @Package com.bt.dolphin.system.menu.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.system.menu.vo.SysMenuVo;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年9月4日 下午2:54:44 cbt-34201 Created.
 * 
 */
public interface SysMenuDao {
	/**
	 * 
	 * 方法说明：根据应用id和菜单
	 *
	 * Author： cbt Create Date： 2020年9月7日 上午10:27:28 History: 2020年9月7日 上午10:27:28
	 * cbt-34201 Created.
	 *
	 * @param paramString
	 * @param paramList
	 * @return
	 *
	 */
	public List<SysMenuVo> getChildMenuByAppIds(@Param("parentId") String paramString,
			@Param("appIds") List<String> paramList);

	public List<SysMenuVo> getSubMenuByPId(@Param("parentId") String parentId);
}
