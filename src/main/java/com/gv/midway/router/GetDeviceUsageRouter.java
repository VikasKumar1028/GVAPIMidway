package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class GetDeviceUsageRouter {

	private Logger log = Logger.getLogger(GetDeviceUsageRouter.class);

	public String getDeviceUsageHistory(DeviceInformation deviceInfo) {
		log.info("************Device Usage Information ROUTER*************"
				+ deviceInfo.toString());

		if (deviceInfo.getBs_carrier().equalsIgnoreCase(IConstant.BSCARRIER_VERIZON))
			return "seda:getDeviceUsageInformationForVerizon";
		else if (deviceInfo.getBs_carrier().equalsIgnoreCase(IConstant.BSCARRIER_KORE))
			return "seda:getDeviceUsageInformationForKore";

		return null;
	}
}
