package com.gv.midway.processor.customFieldsUpdateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.customFieldsUpdateDevice.request.CustomFieldsUpdateDeviceRequest;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePostProcessor;

public class KoreCustomFieldsUpdatePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreCustomFieldsUpdatePreProcessor.class
			.getName());
	
	Environment newEnv;

	public KoreCustomFieldsUpdatePreProcessor() {

	}

	public KoreCustomFieldsUpdatePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Start::KoreCustomFieldsUpdatePreProcessor");

		CustomFieldsUpdateDeviceRequest customFieldsUpdateDeviceRequest = exchange
				.getIn().getBody(CustomFieldsUpdateDeviceRequest.class);

		Message message = exchange.getIn();

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");

		message.setHeader("Authorization",
				"Basic Z3JhbnR2aWN0b3JhcGk6akx1Y1dMQ0JxakhQ");
		// message.setHeader("Authorization",
		// newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/modifyDeviceCustomInfo");
		message.setBody(customFieldsUpdateDeviceRequest);

		log.info("End::KoreCustomFieldsUpdatePreProcessor");
	}

}
