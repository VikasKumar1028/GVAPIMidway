package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponseDataArea;
import com.gv.midway.processor.activateDevice.StubKoreActivateDeviceProcessor;

public class StubKoreRestoreDeviceProcessor implements Processor {

	Logger log = Logger.getLogger(StubKoreRestoreDeviceProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:StubKoreRestoreDeviceProcessor");

		RestoreDeviceResponse restoreDeviceResponse = new RestoreDeviceResponse();

		RestoreDeviceResponseDataArea restoreDeviceResponseDataArea = new RestoreDeviceResponseDataArea();
	    Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is restored successfully");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("KORE");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("KORE");

		restoreDeviceResponse.setHeader(responseheader);
		restoreDeviceResponse.setResponse(response);
		restoreDeviceResponseDataArea.setRequestId("KR001");
		
		restoreDeviceResponse.setDataArea(restoreDeviceResponseDataArea);

		exchange.getIn().setBody(restoreDeviceResponse);

		log.info("EndStubKoreRestoreDeviceProcessor");
	}
}

