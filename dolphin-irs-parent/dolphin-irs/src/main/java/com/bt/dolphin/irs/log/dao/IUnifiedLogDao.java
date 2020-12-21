package com.bt.dolphin.irs.log.dao;

import java.util.Map;

/**
 *  类描述：统一的接口日志
 * 
 *  @author: 
 *  @version  $Id: Exp$ 
 *
 *  History:  2018年9月29日 上午11:39:34      Created.
 *           
 */
public interface IUnifiedLogDao {
	
	/**
	 * 
	 * 方法说明：保存接口日志
	 *
	 * Author：                        
	 * Create Date：   2018年9月29日 上午11:42:46
	 * History:  2018年9月29日 上午11:42:46      Created.
	 *
	 * @param log
	 *
	 */
	public void saveLog(Map log);

}
