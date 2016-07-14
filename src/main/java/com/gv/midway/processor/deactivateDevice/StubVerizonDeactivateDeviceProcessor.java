package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponseDataArea;

public class StubVerizonDeactivateDeviceProcessor implements Processor {

	Logger log = Logger.getLogger(StubVerizonDeactivateDeviceProcessor.class
			.getName());

	public StubVerizonDeactivateDeviceProcessor() {
		//Default Constructor
	}

	public void process(Exchange exchange) throws Exception {
		log.info("Begin::StubVerizonDeactivateDeviceProcessor");
		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		
		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is deactivate successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		deactivateDeviceResponse.setHeader(responseheader);
		deactivateDeviceResponse.setResponse(response);

		deactivateDeviceResponseDataArea.setOrderNumber("VZ012345789");
		deactivateDeviceResponse.setDataArea(deactivateDeviceResponseDataArea);
		exchange.getIn().setBody(deactivateDeviceResponse);
		log.info("End::StubVerizonDeactivateDeviceProcessor");
	}
}
