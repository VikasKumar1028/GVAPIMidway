package com.gv.midway.splitter;

import java.util.List;
import org.apache.log4j.Logger;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class BulkOperationSplitter {

   private static final Logger LOGGER = Logger.getLogger(BulkOperationSplitter.class);

    public List<DeviceInformation> split(List<DeviceInformation> deviceInformationList) {

        LOGGER.debug("***************** BULK OPERATION SPLITTER*********************" + deviceInformationList.size());
        return deviceInformationList;
    }
}
