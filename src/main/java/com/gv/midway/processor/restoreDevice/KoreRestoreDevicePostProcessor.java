package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponseDataArea;

public class KoreRestoreDevicePostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreRestoreDevicePostProcessor.class
			.getName());
	Environment newEnv;

	// default constructor
	public KoreRestoreDevicePostProcessor() {

	}

	// constructor with one parameter
	public KoreRestoreDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	// method for Processing the message exchange for Kore
	public void process(Exchange exchange) throws Exception {

		log.info("Begin::KoreRestoreDevicePostProcessor");

		RestoreDeviceResponse restoreDeviceResponse = new RestoreDeviceResponse();

		RestoreDeviceResponseDataArea restoreDeviceResponseDataArea = new RestoreDeviceResponseDataArea();

		//Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

		/*responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());

		responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT)
				.toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());

		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());*/
		
		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		restoreDeviceResponse.setHeader(responseheader);
		restoreDeviceResponse.setResponse(response);
		restoreDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
				IConstant.MIDWAY_TRANSACTION_ID).toString());

		restoreDeviceResponse.setDataArea(restoreDeviceResponseDataArea);

		exchange.getIn().setBody(restoreDeviceResponse);
		log.info("End::KoreRestoreDevicePostProcessor");
	}
}