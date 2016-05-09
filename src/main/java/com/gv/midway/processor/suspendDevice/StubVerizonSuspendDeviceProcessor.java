package com.gv.midway.processor.suspendDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponseDataArea;

public class StubVerizonSuspendDeviceProcessor implements Processor {

	Logger log = Logger.getLogger(StubVerizonSuspendDeviceProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Begin::StubVerizonSuspendDeviceProcessor");
		SuspendDeviceResponse suspendDeviceResponse = new SuspendDeviceResponse();

		SuspendDeviceResponseDataArea suspendDeviceResponseDataArea = new SuspendDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is suspended successfully");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		suspendDeviceResponse.setHeader(responseheader);
		suspendDeviceResponse.setResponse(response);
		suspendDeviceResponseDataArea.setOrderNumber("KR012313512");
		suspendDeviceResponse.setDataArea(suspendDeviceResponseDataArea);

		exchange.getIn().setBody(suspendDeviceResponse);
		log.info("End::StubVerizonSuspendDeviceProcessor");
	}
}