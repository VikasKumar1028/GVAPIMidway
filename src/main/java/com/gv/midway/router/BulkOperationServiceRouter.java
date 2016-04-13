package com.gv.midway.router;

import org.apache.log4j.Logger;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;


public class BulkOperationServiceRouter {
	
	private Logger log = Logger.getLogger(BulkOperationServiceRouter.class);

	public String bulkOperationDeviceSyncInDB(DeviceInformation deviceInformation) {
		log.info("************BulkOperation ROUTER****************************" + deviceInformation.toString());

		return "seda:bulkOperationDeviceSyncInDB";
		
	}
}
