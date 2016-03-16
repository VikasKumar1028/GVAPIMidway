package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;

public class KoreDeviceInformationPreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceInformationPreProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:KoreDeviceInformationProcessor");
		// wrap it in a Subject
		DeviceInformationRequest request = (DeviceInformationRequest) exchange.getIn().getBody();
		String deviceId = request.getDataArea().getDeviceId().getId();

		// String json = "{\"deviceNumber\":\""+deviceId+"\"}";
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceNumber", deviceId);
		
		Message message = exchange.getIn();
		// message .setHeader("VZ-M2M-Token",
		// "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization", "Basic Z3JhbnR2aWN0b3JhcGk6akx1Y1dMQ0JxakhQ");
		message.setHeader(Exchange.HTTP_PATH, "/json/queryDevice");

		message.setBody(obj);

		System.out.println("----exchange_Body-----------" + message.toString());

	}

	public static void main(String[] args) {
		String json = "{\"deviceNumber\":\"" + 1 + "\"}";
		System.out.println(json);
	}
}
