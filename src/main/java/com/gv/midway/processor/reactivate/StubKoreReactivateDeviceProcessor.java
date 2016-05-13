package com.gv.midway.processor.reactivate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponseDataArea;

public class StubKoreReactivateDeviceProcessor implements Processor {

	Logger log = Logger.getLogger(KoreReactivateDevicePreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Begin:StubKoreReactivateDeviceProcessor");
		ReactivateDeviceResponse reactivateDeviceResponse = new ReactivateDeviceResponse();

		ReactivateDeviceResponseDataArea reactivateDeviceResponseDataArea = new ReactivateDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is reactivate successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		reactivateDeviceResponse.setHeader(responseheader);
		reactivateDeviceResponse.setResponse(response);
		reactivateDeviceResponseDataArea.setOrderNumber("StubKoreReactivateDeviceProcessor");
		reactivateDeviceResponse.setDataArea(reactivateDeviceResponseDataArea);

		exchange.getIn().setBody(reactivateDeviceResponse);
		log.info("End:StubKoreReactivateDeviceProcessor");

	}

}
