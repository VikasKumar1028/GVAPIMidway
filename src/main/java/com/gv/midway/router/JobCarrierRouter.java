package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.utility.CommonUtil;

public class JobCarrierRouter {

	private Logger log = Logger.getLogger(JobCarrierRouter.class);

	public String routeCarrierJob(DeviceInformation deviceInformation) {

		if ("KORE".equals(CommonUtil.getDerivedCarrierName(deviceInformation
				.getBs_carrier()))) {
			return "seda:processKoreJob";

		} else if ("VERIZON".equals(CommonUtil
				.getDerivedCarrierName(deviceInformation.getBs_carrier()))) {
			return "seda:processVerizonJob";
		} else {
			return "";
		}

	}

}
