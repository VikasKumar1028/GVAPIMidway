package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class GetDeviceUsageRouter {

	private Logger log = Logger.getLogger(GetDeviceUsageRouter.class);

	public String getDeviceUsageHistory(DeviceInformation deviceInfo) {
		log.info("************Device Usage Information ROUTER*************"
				+ deviceInfo.toString());

		return "seda:getDeviceUsageInformation";

	}
}
