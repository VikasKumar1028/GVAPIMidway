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
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;

public class KoreActivateDevicePostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreActivateDevicePostProcessor.class.getName());

	public KoreActivateDevicePostProcessor() {

	}

	Environment newEnv;

	public KoreActivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {

		// bean exhange (deviceNumber, Midwayisd)

		// update status

		log.info("Start::KoreActivateDevicePostProcessor");
		System.out.println("KoreActivateDevicePostProcessor------------------------------tracking no----------");
		ActivateDeviceResponseDataArea deviceActivateResponseDataArea = new ActivateDeviceResponseDataArea();

		ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();

		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();
		
		//santosh : here's null pointer
		response.setResponseCode(newEnv.getProperty(IConstant.RESPONSES_CODE));
		response.setResponseStatus(newEnv.getProperty(IConstant.RESPONSE_STATUS_SUCCESS));

		responseheader.setApplicationName(newEnv.getProperty(IConstant.APPLICATION_NAME));
		responseheader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setOrganization(newEnv.getProperty(IConstant.ORGANIZATION));
		
		
		String TransactionId = (String) exchange.getProperty(newEnv.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(TransactionId);

		responseheader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER).toString());

		// deviceActivateResponseDataArea.setRequestId("requestId");

		activateDeviceResponse.setHeader(responseheader);
		activateDeviceResponse.setResponse(response);
		// deviceActivateResponseDataArea.setTrackingNumber("ReciptNumbergenratedByKORE");
		activateDeviceResponse.setDataArea(deviceActivateResponseDataArea);

		exchange.getIn().setBody(activateDeviceResponse);
		log.info("End::KoreActivateDevicePostProcessor");
	}
}
