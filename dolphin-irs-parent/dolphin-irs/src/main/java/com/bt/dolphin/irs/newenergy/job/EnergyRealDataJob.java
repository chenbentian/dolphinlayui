/**
 *
 * @(#) EnergyRealData.java
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
 *  类描述：实时推送发电量，补贴资金总额
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月10日 下午2:08:34   cbt-34201   Created.
 *           
 */
@Component
public class EnergyRealDataJob implements ScheduledTaskJob{
	
	 @Autowired
	 NewEnergyService newEnergyService;
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			newEnergyService.energyRealData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
