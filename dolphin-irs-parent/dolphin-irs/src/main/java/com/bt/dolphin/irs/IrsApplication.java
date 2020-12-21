/**
 *
 * @(#) IrsApplication.java
 * @Package com.bt.dolphin.irs
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月9日 上午12:00:48   cbt-34201   Created.
 *           
 */
@SpringBootApplication(scanBasePackages={"com.bt.dolphin.irs"},exclude={DataSourceAutoConfiguration.class})
public class IrsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(IrsApplication.class, args);
	}
}
