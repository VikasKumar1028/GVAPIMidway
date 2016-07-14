package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.verizon.DeviceId;

public class VerizonDeviceInformationPreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonDeviceInformationPreProcessor.class
			.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin:VerizonDeviceInformationPreProcessor");

		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		DeviceInformationRequest req = (DeviceInformationRequest) exchange
				.getIn().getBody();
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, req.getDataArea()
				.getNetSuiteId());
		DeviceId deviceId = req.getDataArea().getDeviceId();

		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceId", deviceId);

		exchange.getIn().setBody(obj);
		Message message = exchange.getIn();

		String sessionToken = "";
		String authorizationToken = "";

		if (exchange.getProperty(IConstant.VZ_SEESION_TOKEN) != null
				&& exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
			sessionToken = exchange.getProperty(IConstant.VZ_SEESION_TOKEN)
					.toString();
			authorizationToken = exchange.getProperty(
					IConstant.VZ_AUTHORIZATION_TOKEN).toString();
		}

		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/list");

	}

}
