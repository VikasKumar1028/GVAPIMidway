package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;

public class KoreDeactivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeactivateDevicePreProcessor.class
			.getName());

	Environment newEnv;

	public KoreDeactivateDevicePreProcessor() {

	}

	public KoreDeactivateDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {

		log.info("Start:KoreDeactivateDevicePreProcessor");
		log.info("Authorization::"
				+ newEnv.getProperty(IConstant.KORE_AUTHENTICATION));

		Message message = exchange.getIn();

		DeactivateDeviceRequest deactivateDeviceRequest = exchange.getIn()
				.getBody(DeactivateDeviceRequest.class);
		String deviceId = deactivateDeviceRequest.getDataArea().getDeviceIds()[0]
				.getId();
		Boolean flagScravalue = deactivateDeviceRequest.getDataArea()
				.getFlagScrap();
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceNumber", deviceId);
		obj.put("flagScrap", flagScravalue);

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/deactivateDevice");
		message.setBody(deactivateDeviceRequest);

		log.info("End:KoreDeactivateDevicePreProcessor");
	}
}
