/**
 *
 * @(#) EnergyYearAreaDataJob.java
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
 *  类描述：本年各区发电量
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月11日 下午8:41:25   cbt-34201   Created.
 *           
 */
@Component
public class EnergyYearAreaDataJob implements ScheduledTaskJob{
	
	 @Autowired
	 NewEnergyService newEnergyService;
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			newEnergyService.energyYearAreaData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
