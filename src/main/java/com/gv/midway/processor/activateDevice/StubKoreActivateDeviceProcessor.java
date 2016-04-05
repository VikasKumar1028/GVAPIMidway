package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;

public class StubKoreActivateDeviceProcessor implements Processor {

	Logger log = Logger.getLogger(StubKoreActivateDeviceProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:StubKoreDeviceActivateProcessor");

		ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();

		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();
	    Header responseheader = new Header();

		Response response = new Response();
		response.setResponseCode("200");
		response.setResponseDescription("Device is Activated successfully");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("KORE");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("KORE");// baseResponse.setHeader(header);

		activateDeviceResponse.setHeader(responseheader);
		activateDeviceResponse.setResponse(response);

		//activateDeviceResponseDataArea.setTrackingNumber("TR001");
		//activateDeviceResponseDataArea.setRequestId("requestId");
		activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

		exchange.getIn().setBody(activateDeviceResponse);

		log.info("End:StubKoreDeviceActivateProcessor");
	}
}
