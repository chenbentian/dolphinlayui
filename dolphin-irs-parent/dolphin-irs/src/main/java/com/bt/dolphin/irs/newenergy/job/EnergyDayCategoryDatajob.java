/**
 *
 * @(#) EnergyDayCategoryDatajob.java
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
 *  类描述：本月每日分类累计发电量（光伏，风电，生物质）
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月11日 上午11:00:40   cbt-34201   Created.
 *           
 */
@Component
public class EnergyDayCategoryDatajob implements ScheduledTaskJob{
	
	 @Autowired
	 NewEnergyService newEnergyService;
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			newEnergyService.energyDayCategoryData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
