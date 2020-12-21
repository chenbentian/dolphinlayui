/**
 *
 * @(#) UserServiceImpl.java
 * @Package com.bt.dolphin.system.user.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.common.util.ResultUtils;
import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.system.user.api.SysUserService;
import com.bt.dolphin.system.user.dao.SysUserDao;
import com.bt.dolphin.system.user.vo.SysUserCondition;
import com.bt.dolphin.system.user.vo.SysUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *  类描述：系统用户
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月7日 下午5:15:10   cbt-34201   Created.
 *           
 */
@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserDao sysUserDao;
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
	@Override
	public SysUserVo getUserByUserNo(String userNo) {
		return sysUserDao.getUserByUserNo(userNo);
	}
	
	@Override
	public QueryResultObject queryUserList(SysUserCondition condition){
		int page = condition.getPageBegin()/condition.getRows()+1;
		PageHelper.startPage(page, condition.getRows());
		List<SysUserVo> result = new ArrayList<SysUserVo>();
		result = sysUserDao.queryUserList(condition);
		PageInfo<SysUserVo> pageInfo = new PageInfo<SysUserVo>(result);
		List<SysUserVo> list = pageInfo.getList();
		return ResultUtils.wrappQueryResult(list, (int) pageInfo.getTotal());

	}

}
