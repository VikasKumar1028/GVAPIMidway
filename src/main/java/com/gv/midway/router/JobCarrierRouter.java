package com.gv.midway.router;

import com.gv.midway.exception.InvalidParameterException;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.utility.CommonUtil;

public class JobCarrierRouter {

    private static final Logger LOGGER = Logger.getLogger(JobCarrierRouter.class);

    public String routeCarrierJob(DeviceInformation deviceInformation) throws InvalidParameterException {

        LOGGER.debug("Inside the Carrier Job Router");

        final String derivedCarrierName = CommonUtil.getDerivedCarrierName(deviceInformation.getBs_carrier());

        if (IConstant.BSCARRIER_SERVICE_KORE.equals(derivedCarrierName)) {
            return "seda:processKoreJob";
        } else if (IConstant.BSCARRIER_SERVICE_VERIZON.equals(derivedCarrierName)) {
            return "seda:processVerizonJob";
        } else {
            return "";
        }
    }
}