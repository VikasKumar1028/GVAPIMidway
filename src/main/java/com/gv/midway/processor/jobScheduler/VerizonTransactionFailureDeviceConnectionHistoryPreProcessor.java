package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.verizon.DeviceId;

public class VerizonTransactionFailureDeviceConnectionHistoryPreProcessor
		implements Processor {

	Logger log = Logger
			.getLogger(VerizonTransactionFailureDeviceConnectionHistoryPreProcessor.class
					.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		log.info("Begin:VerizonTransactionFailureDeviceConnectionHistoryPreProcessor");


		DeviceConnection deviceInfo = (DeviceConnection) exchange.getIn()
				.getBody();
	
		ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
		DeviceId device = new DeviceId();

		// Fetching Recommended device Identifiers

		device.setId(deviceInfo.getDeviceId().getId());
		device.setKind(deviceInfo.getDeviceId().getKind());
		dataArea.setDeviceId(device);

		exchange.setProperty("DeviceId", device);
		exchange.setProperty("CarrierName", deviceInfo.getCarrierName());
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());



		dataArea.setLatest(exchange.getProperty("jobEndTime").toString());
		dataArea.setEarliest(exchange.getProperty("jobStartTime").toString());

		ObjectMapper objectMapper = new ObjectMapper();

		String strRequestBody = objectMapper.writeValueAsString(dataArea);

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
		message.setHeader(Exchange.HTTP_PATH,
				"/devices/connections/actions/listHistory");

		exchange.setPattern(ExchangePattern.InOut);
		log.info("End:VerizonTransactionFailureDeviceConnectionHistoryPreProcessor");
	}

}
