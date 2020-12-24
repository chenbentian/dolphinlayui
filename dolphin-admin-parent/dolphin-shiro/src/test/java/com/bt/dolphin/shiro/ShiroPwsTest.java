/**
 *
 * @(#) ShiroPwsTest.java
 * @Package com.bt.dolphin.shiro
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月21日 上午11:36:50   cbt-34201   Created.
 *           
 */
public class ShiroPwsTest {
	 public static void main(String[] args) {
		 String algorithmName = "SHA-256";
		 String username = "admin";
		 String password = "123456";
		 String salt1 = username;
		// String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		 String salt2 = "iVYAs5";
		 int hashIterations = 1024;
		 SimpleHash hash = new SimpleHash(algorithmName, password,salt2, hashIterations);
		 String encodedPassword = hash.toHex();
		 System.out.println("==encodedPassword:"+encodedPassword);
		 
		 String tt = ShiroUtil.encrypt(password, salt2);
		 System.out.println("tt ====:"+tt);
		 
	 }
		 
}
