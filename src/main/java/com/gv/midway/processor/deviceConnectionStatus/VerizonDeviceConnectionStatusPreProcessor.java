package com.gv.midway.processor.deviceConnectionStatus;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;

public class VerizonDeviceConnectionStatusPreProcessor implements Processor {

	Logger log = Logger
			.getLogger(VerizonDeviceConnectionStatusPreProcessor.class
					.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonDeviceConnectionStatusPreProcessor");
		System.out.println("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		System.out.println("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		ConnectionInformationRequestDataArea businessRequest = new ConnectionInformationRequestDataArea();
		ConnectionInformationRequest proxyRequest = (ConnectionInformationRequest) exchange
				.getIn().getBody();
		businessRequest.setEarliest(proxyRequest.getDataArea().getEarliest());
		businessRequest.setLatest(proxyRequest.getDataArea().getLatest());
		DeviceId deviceId = new DeviceId();
		deviceId.setId(proxyRequest.getDataArea().getDeviceId().getId());
		deviceId.setKind(proxyRequest.getDataArea().getDeviceId().getKind());
		businessRequest.setDeviceId(deviceId);

		ObjectMapper objectMapper = new ObjectMapper();

		String strRequestBody = objectMapper
				.writeValueAsString(businessRequest);

		exchange.getIn().setBody(strRequestBody);
		
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
			  message.setHeader("VZ-M2M-Token",
			  "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
			  message.setHeader("Authorization",
			  "Bearer 89ba225e1438e95bd05c3cc288d3591");

			/*message.setHeader("VZ-M2M-Token", sessionToken);
			message.setHeader("Authorization", "Bearer " + authorizationToken);*/
			message.setHeader(Exchange.CONTENT_TYPE, "application/json");
			message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
			message.setHeader(Exchange.HTTP_METHOD, "POST");
			message.setHeader(Exchange.HTTP_PATH,
					"/devices/connections/actions/listHistory");
		
	}

}
