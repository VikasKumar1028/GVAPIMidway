package com.gv.midway.splitter;

import java.util.List;
import org.apache.log4j.Logger;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;


public class BulkOperationSplitter {
	
	private Logger log = Logger.getLogger(BulkOperationSplitter.class);

	 public List<DeviceInformation> split(List<DeviceInformation> deviceInformationList) {
	    	
	    	log.info("***************** BULK OPERATION SPLITTER*********************"+deviceInformationList.size());
	        return deviceInformationList;
	    }
}
