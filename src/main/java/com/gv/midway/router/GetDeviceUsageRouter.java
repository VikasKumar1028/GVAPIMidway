package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class GetDeviceUsageRouter {

    private static final Logger LOGGER = Logger.getLogger(GetDeviceUsageRouter.class);

    public String getDeviceUsageHistory(DeviceInformation deviceInfo) {

        LOGGER.debug("************Device Usage Information ROUTER*************" + deviceInfo.toString());

        if (deviceInfo.getBs_carrier().equalsIgnoreCase(IConstant.BSCARRIER_SERVICE_VERIZON))
            return "seda:getDeviceUsageInformationForVerizon";
        else if (deviceInfo.getBs_carrier().equalsIgnoreCase(IConstant.BSCARRIER_SERVICE_KORE))
            return "seda:getDeviceUsageInformationForKore";
        else
            return null;
    }
}