/**
 *
 * @(#) Test.java
 * @Package com.bt.dolphin.irs.newenergy.job
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.newenergy.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bt.dolphin.irs.config.scheduled.ScheduledTaskJob;
import com.bt.dolphin.irs.newenergy.service.NewEnergyService;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月10日 下午2:11:05   cbt-34201   Created.
 *           
 */
@Component
public class GetTest implements ScheduledTaskJob{

	 @Autowired
	 NewEnergyService newEnergyService;
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String tt;
		try {
			tt = newEnergyService.getTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
