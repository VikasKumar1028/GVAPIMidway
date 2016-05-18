package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.DeviceSession;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponseDataArea;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;

public class VerizonDeviceSessionBeginEndInfoPostProcessor implements Processor {

	Logger log = Logger
			.getLogger(VerizonDeviceSessionBeginEndInfoPostProcessor.class
					.getName());

	Environment newEnv;

	public VerizonDeviceSessionBeginEndInfoPostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {

		log.info("Begin:VerizonDeviceSessionBeginEndInfoPostProcessor");

		SessionBeginEndResponse businessResponse = new SessionBeginEndResponse();
		SessionBeginEndResponseDataArea sessionBeginEndResponseDataArea = new SessionBeginEndResponseDataArea();
		Header responseheader = new Header();
		Response response = new Response();

		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));

		Date date = new Date();

		log.info("exchange.getIn().getBody().toString()***************************************"
				+ exchange.getIn().getBody().toString());

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			Map map = exchange.getIn().getBody(Map.class);
			ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			ConnectionInformationResponse connectionResponse = mapper
					.convertValue(map, ConnectionInformationResponse.class);

			int totalConnectionHistory = connectionResponse
					.getConnectionHistory().length;

			int deviceArraySize = ((totalConnectionHistory % 2) == 0) ? (totalConnectionHistory / 2)
					: ((totalConnectionHistory / 2) + 1);
			ArrayList<DeviceSession> deviceSessions = new ArrayList<DeviceSession>();
			// DeviceSession[] deviceSession = new
			// DeviceSession[deviceArraySize];
			if (totalConnectionHistory > 0) {

				int newSession = 0;
				DeviceSession deviceSession = new DeviceSession();
				// deviceSession[newSession] = new DeviceSession();
				int eventStatus = 0;
				for (int i = 0; i < totalConnectionHistory; i++) {

					int totalConnectionHistoryEvents = connectionResponse
							.getConnectionHistory()[i]
							.getConnectionEventAttributes().length;

					for (int j = 0; j < totalConnectionHistoryEvents; j++) {

						if (connectionResponse.getConnectionHistory()[i]
								.getConnectionEventAttributes()[j].getKey()
								.equalsIgnoreCase("Event")
								&& connectionResponse.getConnectionHistory()[i]
										.getConnectionEventAttributes()[j]
										.getValue().equalsIgnoreCase(
												IConstant.EVENT_STOP) && i == 0) {

							deviceSession.setEnd(connectionResponse
									.getConnectionHistory()[i].getOccurredAt());
							deviceSession.setBegin(null);
							deviceSessions.add(deviceSession);
							deviceSession = new DeviceSession();
							break;

						} else if (connectionResponse.getConnectionHistory()[i]
								.getConnectionEventAttributes()[j].getKey()
								.equalsIgnoreCase("Event")
								&& connectionResponse.getConnectionHistory()[i]
										.getConnectionEventAttributes()[j]
										.getValue().equalsIgnoreCase(
												IConstant.EVENT_START)
								&& i == (totalConnectionHistory - 1)) {

							deviceSession.setEnd(null);
							deviceSession.setBegin(connectionResponse
									.getConnectionHistory()[i].getOccurredAt());
							deviceSessions.add(deviceSession);
							break;

						} else if (connectionResponse.getConnectionHistory()[i]
								.getConnectionEventAttributes()[j].getKey()
								.equalsIgnoreCase("Event")
								&& connectionResponse.getConnectionHistory()[i]
										.getConnectionEventAttributes()[j]
										.getValue().equalsIgnoreCase(
												IConstant.EVENT_START)) {

							log.info("Exception on " + i);
							if (eventStatus == 0) {
								eventStatus = 1;
								deviceSession.setBegin(connectionResponse
										.getConnectionHistory()[i]
										.getOccurredAt());
								break;
							} else {
								break;
							}
						} else if (connectionResponse.getConnectionHistory()[i]
								.getConnectionEventAttributes()[j].getKey()
								.equalsIgnoreCase("Event")
								&& connectionResponse.getConnectionHistory()[i]
										.getConnectionEventAttributes()[j]
										.getValue().equalsIgnoreCase(
												IConstant.EVENT_STOP)) {

							if (eventStatus == 1) {
								eventStatus = 0;
								deviceSession.setEnd(connectionResponse
										.getConnectionHistory()[i]
										.getOccurredAt());
								deviceSessions.add(deviceSession);
								if (i != (totalConnectionHistory - 1))
									deviceSession = new DeviceSession();

								break;
							} else {
								break;
							}
						}
					}

				}

				log.info("RequestID::" + exchange.getIn().getBody().toString());
				response.setResponseCode(IResponse.SUCCESS_CODE);
				response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
				response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_CONNECTION_STATUS);

				sessionBeginEndResponseDataArea.setDeviceSession(deviceSessions
						.toArray(new DeviceSession[deviceSessions.size()]));

			}
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

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());

		businessResponse.setHeader(responseheader);
		businessResponse.setResponse(response);

		businessResponse.setDataArea(sessionBeginEndResponseDataArea);

		exchange.getIn().setBody(businessResponse);

		log.info("End:VerizonDeviceSessionBeginEndInfoPostProcessor");

	}

}
