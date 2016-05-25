package com.gv.midway.splitter;

import java.util.List;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class DeviceInformationSplitter {

	private Logger log = Logger.getLogger(DeviceInformationSplitter.class);

	public List<DeviceInformation> split(List<DeviceInformation> deviceList) {

		log.info("*****************SPLITTER*********************"
				+ deviceList.size());
		return deviceList;
	}
}
