package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;

public class StubVerizonDeviceActivateProcessor implements Processor {

	Logger log = Logger.getLogger(StubVerizonDeviceActivateProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("StubVerizonDeviceActivateProcessor");
		ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();

		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();

		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		response.setResponseCode("200");
		response.setResponseDescription("Device Activated successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("setRegion");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");
		// baseResponse.setHeader(header);

		activateDeviceResponse.setHeader(responseheader);
		activateDeviceResponse.setResponse(response);

	
		activateDeviceResponseDataArea.setRequestId("R001");

		activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

		exchange.getIn().setBody(activateDeviceResponse);
	}
}