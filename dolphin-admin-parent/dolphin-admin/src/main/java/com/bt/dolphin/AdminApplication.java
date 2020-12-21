/**
 *
 * @(#) AdminApplication.java
 * @Package com.bt.dolphin.admin
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月7日 上午11:11:47   cbt-34201   Created.
 *           
 */
//@SpringBootApplication(scanBasePackages={"com.bt.dolphin.admin","com.bt.dolphin.common"})
@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
