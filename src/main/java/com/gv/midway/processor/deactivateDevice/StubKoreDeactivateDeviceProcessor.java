package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponseDataArea;

public class StubKoreDeactivateDeviceProcessor implements Processor {

	Logger log = Logger.getLogger(StubKoreDeactivateDeviceProcessor.class
			.getName());

	public StubKoreDeactivateDeviceProcessor() {

	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Start::StubKoreDeactivateDeviceProcessor");
		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		ResponseHeader responseheader = new ResponseHeader();
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		Response response = new Response();
		response.setResponseCode("200");
		response.setResponseDescription("Device Information is fetched successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("Region_Value");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("KORE");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("KORE");

		deactivateDeviceResponse.setHeader(responseheader);
		deactivateDeviceResponse.setResponse(response);
	
		deactivateDeviceResponseDataArea.setRequestId("requestId(");
		deactivateDeviceResponseDataArea.setTrackingNumber("trackingNumbe");
		
		deactivateDeviceResponse.setDataArea(deactivateDeviceResponseDataArea);
		exchange.getIn().setBody(deactivateDeviceResponse);
		log.info("End::StubKoreDeactivateDeviceProcessor");
	}

}
