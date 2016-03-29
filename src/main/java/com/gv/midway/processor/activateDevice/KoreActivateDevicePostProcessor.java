package com.gv.midway.processor.activateDevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
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

		//bean exhange (deviceNumber, Midwayisd)
		
		//update status
		
		
		log.info("Start::KoreActivateDevicePostProcessor");
		System.out.println("KoreActivateDevicePostProcessor------------------------------tracking no----------");
		ActivateDeviceResponseDataArea deviceActivateResponseDataArea = new ActivateDeviceResponseDataArea();

		ActivateDeviceResponse activateDeviceResponse = exchange.getIn()
				.getBody(ActivateDeviceResponse.class);

		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();

		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		response.setResponseCode(newEnv.getProperty(IConstant.RESPONSES_CODE));
		response.setResponseStatus(newEnv
				.getProperty(IConstant.RESPONSE_STATUS_SUCCESS));

		responseheader.setApplicationName(newEnv
				.getProperty(IConstant.APPLICATION_NAME));
		responseheader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setOrganization(newEnv
				.getProperty(IConstant.ORGANIZATION));
		responseheader.setSourceName(newEnv
				.getProperty(IConstant.SOURCE_NAME_VERIZON));
		String TransactionId = (String) exchange.getProperty(newEnv
				.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(TransactionId);

		responseheader.setBsCarrier(newEnv
				.getProperty(IConstant.BSCARRIER_VERIZON));

		deviceActivateResponseDataArea.setRequestId("requestId");

		activateDeviceResponse.setHeader(responseheader);
		activateDeviceResponse.setResponse(response);
		deviceActivateResponseDataArea.setTrackingNumber("ReciptNumbergenratedByKORE");
		activateDeviceResponse.setDataArea(deviceActivateResponseDataArea);

		exchange.getIn().setBody(activateDeviceResponse);
		log.info("End::KoreActivateDevicePostProcessor");
	}
}
