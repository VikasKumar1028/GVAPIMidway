package com.gv.midway.processor.deviceConnectionStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponseDataArea;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;

public class VerizonDeviceConnectionStatusPostProcessor implements Processor {

	Logger log = Logger
			.getLogger(VerizonDeviceConnectionStatusPostProcessor.class
					.getName());

	Environment newEnv;

	public VerizonDeviceConnectionStatusPostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonDeviceConnectionStatusPostProcessor");
		
		ConnectionStatusResponse businessResponse = new ConnectionStatusResponse();
		ConnectionStatusResponseDataArea connectionStatusResponseDataArea = new ConnectionStatusResponseDataArea();
		Header responseheader = new Header();
		Response response = new Response();

		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));

		Date date = new Date();

		System.out
				.println("exchange.getIn().getBody().toString()***************************************"
						+ exchange.getIn().getBody().toString());

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			Map map = exchange.getIn().getBody(Map.class);
			ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			ConnectionInformationResponse connectionResponse = mapper
					.convertValue(map, ConnectionInformationResponse.class);

			String deviceStatus = null;
			int totalConnectionHistory = connectionResponse
					.getConnectionHistory().length;
			if (totalConnectionHistory > 0) {
				totalConnectionHistory = totalConnectionHistory - 1;
				int totalConnectionHistoryEvents = connectionResponse
						.getConnectionHistory()[totalConnectionHistory]
						.getConnectionEventAttributes().length;
				totalConnectionHistoryEvents = totalConnectionHistoryEvents - 1;
				for (int i = 0; i < totalConnectionHistoryEvents; i++) {
					if (connectionResponse.getConnectionHistory()[totalConnectionHistory]
							.getConnectionEventAttributes()[i].getKey()
							.equalsIgnoreCase("Event")) {
						deviceStatus = connectionResponse
								.getConnectionHistory()[totalConnectionHistory]
								.getConnectionEventAttributes()[i].getValue();
						break;
					}

				}
			}
				
				String status = null;
				if(deviceStatus == null){
					status = IConstant.NO_RECORD;
				}else if (deviceStatus.equalsIgnoreCase(IConstant.EVENT_START)) {
					status = IConstant.DEVICE_IN_SESSION;
				} else if (deviceStatus.equalsIgnoreCase(IConstant.EVENT_STOP)) {
					status = IConstant.DEVICE_NOT_IN_SESSION;
				} 
				
				log.info("RequestID::" + exchange.getIn().getBody().toString());
				response.setResponseCode(IResponse.SUCCESS_CODE);
				response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
				response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_CONNECTION_STATUS);
				connectionStatusResponseDataArea.setConnectionStatus(status);

			} else {

				response.setResponseCode(400);
				response.setResponseStatus(IResponse.ERROR_MESSAGE);
				response.setResponseDescription(exchange.getIn().getBody()
						.toString());

			}

		
		
		
		responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());

		/*responseheader.setTimestamp(exchange.getProperty(
				IConstant.DATE_FORMAT).toString());*/

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());
		
		businessResponse.setHeader(responseheader);
		businessResponse.setResponse(response);

		businessResponse.setDataArea(connectionStatusResponseDataArea);

		exchange.getIn().setBody(businessResponse);

		log.info("End:VerizonDeviceConnectionStatusPostProcessor");

	}

}
