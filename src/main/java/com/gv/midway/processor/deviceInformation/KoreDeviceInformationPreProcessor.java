package com.gv.midway.processor.deviceInformation;

import com.gv.midway.utility.MessageStuffer;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;

public class KoreDeviceInformationPreProcessor implements Processor {
	private static final Logger LOGGER = Logger.getLogger(KoreDeviceInformationPreProcessor.class.getName());

	private Environment newEnv;

	public KoreDeviceInformationPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.debug("Begin:KoreDeviceInformationPreProcessor");

		final Message message = exchange.getIn();
		final DeviceInformationRequest request = message.getBody(DeviceInformationRequest.class);

		final String deviceId = request.getDataArea().getDeviceId().getId();
		final net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceNumber", deviceId);

		MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/queryDevice", obj);

		exchange.setProperty(IConstant.KORE_SIM_NUMBER, deviceId);
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, request.getDataArea().getNetSuiteId());

		LOGGER.debug("End:KoreDeviceInformationPreProcessor");
	}
}