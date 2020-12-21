/**
 *
 * @(#) UserSessionListener.java
 * @Package com.bt.dolphin.shiro.listener
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月1日 上午9:52:59   cbt-34201   Created.
 *           
 */
public class UserSessionListener implements SessionListener{
	
	   @Override
	    public void onStart(Session session) {//会话创建时触发
	        System.out.println("会话创建：" + session.getId());
	    }
	    @Override
	    public void onExpiration(Session session) {//会话过期时触发
	        System.out.println("会话过期：" + session.getId());
	    }
	    @Override
	    public void onStop(Session session) {//退出/会话过期时触发
	        System.out.println("会话停止：" + session.getId());
	    }  
}
