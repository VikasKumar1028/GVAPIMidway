package com.gv.midway.processor.jobScheduler;

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
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonDeviceUsageHistoryPreProcessor implements
		Processor {
	Logger log = Logger
			.getLogger(VerizonDeviceUsageHistoryPreProcessor.class
					.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Calendar cal = Calendar.getInstance();

		DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
				.getBody();
		/*
		 * ConnectionInformationRequest request = new
		 * ConnectionInformationRequest();
		 */
		ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
		DeviceId device = new DeviceId();
		
		//Fetching Recommended device Identifiers
		DeviceId recommendedDeviceId=CommonUtil.getRecommendedDeviceIdentifier(deviceInfo.getDeviceIds());
				
		device.setId(recommendedDeviceId.getId());
		device.setKind(recommendedDeviceId.getKind());
		dataArea.setDeviceId(device);

		exchange.setProperty("DeviceId", device);
		exchange.setProperty("CarrierName", deviceInfo.getBs_carrier());
		exchange.setProperty("NetSuiteId", deviceInfo.getNetSuiteId());
		exchange.setProperty("ServicePlan", deviceInfo.getCurrentServicePlan());
/*		exchange.setProperty("BillDay",
				deviceInfo.getBs_plan().getBill_day());
		exchange.setProperty("DataAmt",
				deviceInfo.getBs_plan().getData_amt());*/

		dataArea.setLatest(dateFormat.format(cal.getTime()));
		cal.add(Calendar.HOUR, IConstant.DURATION);
		dataArea.setEarliest(dateFormat.format(cal.getTime()));

		ObjectMapper objectMapper = new ObjectMapper();

		String strRequestBody = objectMapper.writeValueAsString(dataArea);

		exchange.getIn().setBody(strRequestBody);

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

	}
}
