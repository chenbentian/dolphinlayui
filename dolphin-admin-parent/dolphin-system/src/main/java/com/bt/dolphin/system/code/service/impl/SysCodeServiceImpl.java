/**
 *
 * @(#) SysCodeServiceImpl.java
 * @Package com.bt.dolphin.system.code.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.system.code.api.SysCodeService;
import com.bt.dolphin.system.code.dao.SysCodeDao;
import com.bt.dolphin.system.code.vo.CodeVo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月21日 下午4:14:57   cbt-34201   Created.
 *           
 */
@Service
public class SysCodeServiceImpl implements SysCodeService{
	
	@Autowired
	private SysCodeDao sysCodeDao;
	
	@Override
	public List<CodeVo> getStandardCodes(String codeType) {
		// TODO Auto-generated method stub
		return sysCodeDao.getStandardCodes(codeType);
	}

	@Override
	public CodeVo getStandardCodes(String codeType, String codeValue) {
		// TODO Auto-generated method stub
		return sysCodeDao.getStandardCodes(codeType, codeValue);
	}

}
