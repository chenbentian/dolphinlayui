/**
 *
 * @(#) SysParamServiceImpl.java
 * @Package com.bt.dolphin.system.param.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.param.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.system.param.api.SysParamService;
import com.bt.dolphin.system.param.dao.SysParamDao;
import com.bt.dolphin.system.param.vo.SysParamVo;

/**
 *  类描述：系统参数
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月1日 下午2:20:30   cbt-34201   Created.
 *           
 */
@Service
public class SysParamServiceImpl implements SysParamService{
	@Autowired
	private SysParamDao sysParamDao;
	
	/**
	 * 
	 * 方法说明：根据系统参数获取参数对象
	 *
	 * Author：        cbt               
	 * Create Date：   2020年9月1日 下午2:41:29
	 * History:  2020年9月1日 下午2:41:29   cbt-34201   Created.
	 *
	 * @param paramCode
	 * @return
	 *
	 */
	@Override
	public SysParamVo getParamByParamCode(String paramCode) {
		return sysParamDao.getParamByParamCode(paramCode);
	}
}
