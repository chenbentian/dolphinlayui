/**
 *
 * @(#) DataProperties.java
 * @Package com.bt.dolphin.irs.newenergy.job
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.newenergy.job;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  类描述：节能中心 驾驶舱接口配置
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月10日 下午6:30:37   cbt-34201   Created.
 *           
 */
@Component
@ConfigurationProperties(prefix = "zhongzly")
public class ZhongzlyProperties {
	
	public String publicKey;
	public String restUrl;
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getRestUrl() {
		return restUrl;
	}
	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}
	
	
	
}
