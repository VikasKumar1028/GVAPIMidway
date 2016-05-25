package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class GetDeviceConnectionRouter {

	private Logger log = Logger.getLogger(GetDeviceConnectionRouter.class);

	public String getDeviceConnectionHistory(DeviceInformation deviceInfo) {
		log.info("************Device Connection Information ROUTER*************"
				+ deviceInfo.toString());

		return "seda:getDeviceHistoryInformation";

	}
}
