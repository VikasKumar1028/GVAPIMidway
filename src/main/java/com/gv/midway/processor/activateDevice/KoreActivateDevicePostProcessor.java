package com.gv.midway.processor.activateDevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;

public class KoreActivateDevicePostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreActivateDevicePostProcessor.class
			.getName());

	public KoreActivateDevicePostProcessor() {

	}

	Environment newEnv;

	public KoreActivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {

		log.info("Start::KoreActivateDevicePostProcessor");

		ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();

		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();


		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

		responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());

		
	

		responseheader.setTimestamp(exchange.getProperty(
				IConstant.DATE_FORMAT).toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());

		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());

		activateDeviceResponse.setHeader(responseheader);
		activateDeviceResponse.setResponse(response);
		activateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
				IConstant.MIDWAY_TRANSACTION_ID).toString());

		activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

		exchange.getIn().setBody(activateDeviceResponse);
		log.info("End::KoreActivateDevicePostProcessor");
	}
}
