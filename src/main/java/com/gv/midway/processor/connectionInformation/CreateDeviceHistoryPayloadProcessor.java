package com.gv.midway.processor.connectionInformation;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionHistory;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.processor.activateDevice.VerizonActivateDevicePreProcessor;
import com.gv.midway.utility.CommonUtil;

public class CreateDeviceHistoryPayloadProcessor implements Processor {

	Logger log = Logger.getLogger(CreateDeviceHistoryPayloadProcessor.class
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
		/*ConnectionInformationRequest request = new ConnectionInformationRequest();*/
		ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
		DeviceId device = new DeviceId();
		device.setId(deviceInfo.getDeviceIds()[0].getId());
		device.setKind(deviceInfo.getDeviceIds()[0].getKind());
		dataArea.setDeviceId(device);

		exchange.setProperty("DeviceId", device);
		
		dataArea.setLatest(dateFormat.format(cal.getTime()));
		cal.add(Calendar.HOUR, -1);
		dataArea.setEarliest(dateFormat.format(cal.getTime()));



		ObjectMapper objectMapper = new ObjectMapper();

		String strRequestBody = objectMapper
				.writeValueAsString(dataArea);

		exchange.getIn().setBody(strRequestBody);
		
		exchange.getIn().setBody(strRequestBody);
		
		System.out.println(" request Body -- >>  " + exchange.getIn().getBody());
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
		
		System.out.println("check endpoint -- >> " + exchange.getFromEndpoint().toString());
		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
	/*	if(exchange.getFromEndpoint().toString()
		.matches("(.*)DeviceConnectionInformation(.*)")) {
		message.setHeader(Exchange.HTTP_PATH, "/devices/connections/actions/listHistory");
		} else if(exchange.getFromEndpoint().toString()
				.matches("(.*)DeviceUsage	Information(.*)")){
			message.setHeader(Exchange.HTTP_PATH, "/devices/actions/list"); 	}*/
		//message.setHeader(Exchange.HTTP_PATH, "/devices/connections/actions/listHistory");
		message.setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list");
	
		
	}

}
