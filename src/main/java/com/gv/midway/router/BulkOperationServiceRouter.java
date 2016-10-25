package com.gv.midway.router;

import org.apache.log4j.Logger;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class BulkOperationServiceRouter {

    private static final Logger LOGGER = Logger.getLogger(BulkOperationServiceRouter.class);

    public String bulkOperationDeviceSyncInDB(DeviceInformation deviceInformation) {

        LOGGER.info("************BulkOperation ROUTER****************************" + deviceInformation.toString());

        return "seda:bulkOperationDeviceSyncInDB";
    }
}