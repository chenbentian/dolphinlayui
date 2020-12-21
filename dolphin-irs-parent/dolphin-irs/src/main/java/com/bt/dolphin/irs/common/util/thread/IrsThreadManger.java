package com.bt.dolphin.irs.common.util.thread;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *  类描述：接口线程池
 * 
 *  @author:  cbt
 *  @version  $Id: Exp$ 
 *
 *  History:  2018年7月4日 下午6:39:38   Liner   Created.
 *           
 */
public class IrsThreadManger {
	public static ThreadPoolExecutor threadPoolExecutor = null;
	static{
		ThreadPoolManager threadPoolManager = new ThreadPoolManager();
		threadPoolExecutor = threadPoolManager.createThreadPool();
	}
}
