package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.DeviceInformation;
import com.gv.midway.pojo.DeviceInformationResponse;
import com.gv.midway.pojo.DeviceInformationResponseDataArea;

public class KoreDeviceInformationProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceInformationProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:KoreDeviceInformationProcessor");
		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();
		DeviceInformation[] deviceInformationArray = new DeviceInformation[1];

		String[] lstExtFeatures = {};
		deviceInformation.setLstExtFeatures(lstExtFeatures);

		deviceInformationArray[0] = deviceInformation;
		deviceInformationResponseDataArea.setDevices(deviceInformationArray);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
	}
}
