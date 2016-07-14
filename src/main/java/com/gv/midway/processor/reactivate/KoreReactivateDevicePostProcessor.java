package com.gv.midway.processor.reactivate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponseDataArea;

public class KoreReactivateDevicePostProcessor implements Processor {
	Logger log = Logger.getLogger(KoreReactivateDevicePostProcessor.class.getName());

	public KoreReactivateDevicePostProcessor() {
		//Empty Constructor
	}
	public KoreReactivateDevicePostProcessor(Environment env) {
	}
	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin::KoreReactivateDevicePostProcessor");

		ReactivateDeviceResponse reActivateDeviceResponse = new ReactivateDeviceResponse();

		ReactivateDeviceResponseDataArea reActivateDeviceResponseDataArea = new ReactivateDeviceResponseDataArea();


		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

		
		
		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		reActivateDeviceResponse.setHeader(responseheader);
		reActivateDeviceResponse.setResponse(response);
		reActivateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

		reActivateDeviceResponse.setDataArea(reActivateDeviceResponseDataArea);

		exchange.getIn().setBody(reActivateDeviceResponse);
		log.info("End::KoreReactivateDevicePostProcessor");
	}
}
