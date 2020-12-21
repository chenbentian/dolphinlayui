/**
 *
 * @(#) SysParamDao.java
 * @Package com.bt.dolphin.system.param.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.param.dao;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.system.param.vo.SysParamVo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月1日 下午2:30:15   cbt-34201   Created.
 *           
 */
public interface SysParamDao {
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
	public SysParamVo getParamByParamCode(@Param("paramCode") String paramCode);
}
