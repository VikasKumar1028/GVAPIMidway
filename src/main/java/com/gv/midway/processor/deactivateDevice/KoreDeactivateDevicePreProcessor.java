package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.processor.deviceInformation.KoreDeviceInformationPreProcessor;

public class KoreDeactivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceInformationPreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:KoreDeactivateDevicePreProcessor");
		Message message = exchange.getIn();

		DeactivateDeviceRequest deactivateDeviceRequest = exchange.getIn()
				.getBody(DeactivateDeviceRequest.class);
		String deviceId = deactivateDeviceRequest.getDataArea().getDeviceIds()[0].getId();
		Boolean flagScravalue = deactivateDeviceRequest.getDataArea()
				.getFlagScrap();
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceNumber", deviceId);
		obj.put("flagScrap", flagScravalue);

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				"Basic Z3JhbnR2aWN0b3JhcGk6akx1Y1dMQ0JxakhQ");
		message.setHeader(Exchange.HTTP_PATH, "/json/deactivateDevice");

		message.setBody(deactivateDeviceRequest);
		
		log.info("End:KoreDeactivateDevicePreProcessor");
	}
}
