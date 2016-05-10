package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;

public class KoreDeviceInformationPreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceInformationPreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Begin:KoreDeviceInformationPreProcessor");
		
		
		// wrap it in a Subject
		DeviceInformationRequest request = (DeviceInformationRequest) exchange
				.getIn().getBody(DeviceInformationRequest.class);
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, request.getDataArea().getNetSuiteId());
		String deviceId=request.getDataArea().getDeviceId().getId();
		
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceNumber", deviceId);

		Message message = exchange.getIn();
		
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				"Basic Z3JhbnR2aWN0b3JhcGk6akx1Y1dMQ0JxakhQ");
		message.setHeader(Exchange.HTTP_PATH, "/json/queryDevice");

		message.setBody(obj);
		log.info("Start:KoreDeviceInformationPreProcessor");

	}

}
