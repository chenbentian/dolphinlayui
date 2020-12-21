/**
 *
 * @(#) SysUserDao.java
 * @Package com.bt.dolphin.system.user.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.system.user.vo.SysUserCondition;
import com.bt.dolphin.system.user.vo.SysUserVo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月27日 下午5:15:51   cbt-34201   Created.
 *           
 */
public interface SysUserDao {
	/**
	 * 
	 * 方法说明：根据账号获取用户信息
	 *
	 * Author：        cbt               
	 * Create Date：   2020年8月27日 下午5:34:21
	 * History:  2020年8月27日 下午5:34:21   cbt-34201   Created.
	 *
	 * @param userNo
	 * @return
	 *
	 */
	public SysUserVo getUserByUserNo(@Param("userNo") String userNo);
	
	/**
	 * 
	 * 方法说明：列表查询
	 *
	 * Author：        cbt               
	 * Create Date：   2020年12月17日 下午6:32:30
	 * History:  2020年12月17日 下午6:32:30   cbt-34201   Created.
	 *
	 * @param condition
	 * @return
	 *
	 */
	public List<SysUserVo> queryUserList(SysUserCondition condition);


}
