package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponseDataArea;

public class StubVerizonDeactivateDeviceProcessor implements Processor {

	Logger log = Logger.getLogger(StubVerizonDeactivateDeviceProcessor.class
			.getName());

	public StubVerizonDeactivateDeviceProcessor() {

	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		log.info("Start::StubVerizonDeactivateDeviceProcessor");
		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();
		response.setResponseCode("200");
		response.setResponseDescription("Device Information is fetched successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("KORE");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("KORE");

		deactivateDeviceResponse.setHeader(responseheader);
		deactivateDeviceResponse.setResponse(response);

		deactivateDeviceResponseDataArea.setRequestId("requestId");
		deactivateDeviceResponseDataArea.setTrackingNumber("null");
		deactivateDeviceResponse.setDataArea(deactivateDeviceResponseDataArea);
		exchange.getIn().setBody(deactivateDeviceResponse);
		log.info("End::StubVerizonDeactivateDeviceProcessor");
	}
}
