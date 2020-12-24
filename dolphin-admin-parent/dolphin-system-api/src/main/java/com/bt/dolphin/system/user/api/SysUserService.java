/**
 *
 * @(#) UserService.java
 * @Package com.bt.dolphin.system.user.service
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.user.api;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.system.user.vo.SysUserCondition;
import com.bt.dolphin.system.user.vo.SysUserVo;

/**
 *  类描述：系统用户
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月7日 下午5:14:47   cbt-34201   Created.
 *           
 */
public interface SysUserService {
	
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
	public SysUserVo getUserByUserNo(String userNo);
	public SysUserVo getUserByUserId(String userId);
	
	public QueryResultObject queryUserList(SysUserCondition condition);
	
	public void saveSysUser(SysUserVo vo);
	public void deleteByUserId(String userId);

	
}
