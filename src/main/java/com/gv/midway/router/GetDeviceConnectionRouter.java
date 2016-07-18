package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class GetDeviceConnectionRouter {

     private static final Logger LOGGER = Logger.getLogger(GetDeviceConnectionRouter.class);

    public String getDeviceConnectionHistory(DeviceInformation deviceInfo) {
        LOGGER.info("************Device Connection Information ROUTER*************"
                + deviceInfo.toString());

        if (deviceInfo.getBs_carrier().equalsIgnoreCase(
                IConstant.BSCARRIER_SERVICE_VERIZON))
            return "seda:getDeviceConnectionInformation";
        else
            return null;

    }
}
