package com.gv.midway.processor.usageDevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;

public class RetrieveDeviceUsageHistoryPreProcessor implements Processor {
	Logger log = Logger.getLogger(RetrieveDeviceUsageHistoryPreProcessor.class
			.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin::RetrieveDeviceUsageHistoryPreProcessor");
		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		
		UsageInformationRequest proxyRequest = (UsageInformationRequest) exchange
				.getIn().getBody();

		UsageInformationRequestDataArea businessRequest =new UsageInformationRequestDataArea();

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

		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");

		message.setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list");

		exchange.setPattern(ExchangePattern.InOut);

		log.info("End::RetrieveDeviceUsageHistoryPreProcessor");
	}
}
