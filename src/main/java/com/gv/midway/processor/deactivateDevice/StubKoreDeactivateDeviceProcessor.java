package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
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
		Header responseheader = new Header();
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is deactivate successfully");
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
		deactivateDeviceResponseDataArea.setOrderNumber("KR023335545");

		deactivateDeviceResponse.setDataArea(deactivateDeviceResponseDataArea);
		exchange.getIn().setBody(deactivateDeviceResponse);
		log.info("End::StubKoreDeactivateDeviceProcessor");
	}

}
