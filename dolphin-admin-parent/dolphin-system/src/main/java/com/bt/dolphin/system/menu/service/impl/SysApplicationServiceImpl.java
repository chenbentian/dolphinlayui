/**
 *
 * @(#) SysApplicationServiceImpl.java
 * @Package com.bt.dolphin.system.menu.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.system.menu.api.SysApplicationService;
import com.bt.dolphin.system.menu.dao.SysApplicationDao;
import com.bt.dolphin.system.menu.vo.SysApplicationVo;

/**
 *  类描述：系统应用
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月4日 下午2:52:21   cbt-34201   Created.
 *           
 */
@Service
@Transactional
public class SysApplicationServiceImpl implements SysApplicationService{
	@Autowired
	private SysApplicationDao sysApplicationDao;

	@Override
	public List<SysApplicationVo> getAllApplication() {
		// TODO Auto-generated method stub
		return sysApplicationDao.getAllApplication();
	}

	@Override
	public SysApplicationVo getAppByAppId(String appId) {
		// TODO Auto-generated method stub
		return sysApplicationDao.getAppByAppId(appId);
	}

	@Override
	public String getAppIdByAppName(String appName) {
		// TODO Auto-generated method stub
		return sysApplicationDao.getAppIdByAppName(appName);
	}
	
}
