package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;

public class KoreDeviceInformationPreProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(KoreDeviceInformationPreProcessor.class.getName());

	Environment newEnv;

	public KoreDeviceInformationPreProcessor() {
		// Empty Constructor
	}

	public KoreDeviceInformationPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:KoreDeviceInformationPreProcessor");

		// wrap it in a Subject
		DeviceInformationRequest request = (DeviceInformationRequest) exchange
				.getIn().getBody(DeviceInformationRequest.class);
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, request
				.getDataArea().getNetSuiteId());

		String deviceId = request.getDataArea().getDeviceId().getId();
		exchange.setProperty(IConstant.KORE_SIM_NUMBER, deviceId);
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceNumber", deviceId);

		Message message = exchange.getIn();

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/queryDevice");

		message.setBody(obj);
		LOGGER.info("End:KoreDeviceInformationPreProcessor");

	}

}
