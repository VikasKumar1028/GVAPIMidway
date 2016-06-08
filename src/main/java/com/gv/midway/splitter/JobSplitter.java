package com.gv.midway.splitter;

import java.util.List;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class JobSplitter {

	private Logger log = Logger.getLogger(JobSplitter.class);

	public List<DeviceInformation> split(
			List<DeviceInformation> deviceInformationList) {

		log.info("JobSplitter&&&&&&&&&&&&&&&&&&&&&&");
		
		return deviceInformationList;
	}

}
