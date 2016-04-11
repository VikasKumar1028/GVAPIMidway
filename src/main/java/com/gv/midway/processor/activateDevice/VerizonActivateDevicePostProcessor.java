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
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPostProcessor;

public class VerizonActivateDevicePostProcessor implements Processor {

	static int i = 0;

	Logger log = Logger.getLogger(VerizonDeviceInformationPostProcessor.class
			.getName());

	Environment newEnv;

	public VerizonActivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonActivateDevicePostProcessor");

		ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();
		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();
		Header responseheader = new Header();
		Response response = new Response();

		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));

		Date date = new Date();

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {


			log.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

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

		responseheader.setTimestamp(exchange.getProperty(
				IConstant.DATE_FORMAT).toString());

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

		log.info("End:VerizonActivateDevicePostProcessor");

	}

}
