package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;

public class KoreActivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreActivateDevicePreProcessor.class
			.getName());

	Environment newEnv;

	public KoreActivateDevicePreProcessor() {

	}

	public KoreActivateDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {

		log.info("Start:KoreActivateDevicePreProcessor.java");

		
		ActivateDeviceRequest activateDeviceRequest = exchange.getIn()
				.getBody(ActivateDeviceRequest.class);
		System.out.println("activateDeviceRequest"+activateDeviceRequest.toString());
		
		//String deviceIds = activateDeviceRequest.getDataArea().getDeviceId()[0].getId();
		String deviceIds ="89148000000800139708";
		String eAPCode =activateDeviceRequest.getDataArea().geteAPCode();

		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceNumber", deviceIds);
		obj.put("EAPCode", eAPCode);	

		Message message = exchange.getIn();

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/activateDevice");
		message.setBody(obj);
		
		log.info("End:KoreActivateDevicePreProcessor.java");

	}

}
