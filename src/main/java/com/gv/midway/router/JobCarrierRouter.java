package com.gv.midway.router;

import com.gv.midway.exception.InvalidParameterException;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.utility.CommonUtil;

public class JobCarrierRouter {

   private static final Logger LOGGER = Logger.getLogger(JobCarrierRouter.class);

    public String routeCarrierJob(DeviceInformation deviceInformation) throws InvalidParameterException {

        LOGGER.info("Inside the Carrier Job Router");
        if (IConstant.BSCARRIER_SERVICE_KORE.equals(CommonUtil
                .getDerivedCarrierName(deviceInformation.getBs_carrier()))) {
            return "seda:processKoreJob";

        } else if (IConstant.BSCARRIER_SERVICE_VERIZON.equals(CommonUtil
                .getDerivedCarrierName(deviceInformation.getBs_carrier()))) {
            return "seda:processVerizonJob";
        } else {
            return "";
        }
    }

}
