package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;

public class VerizonDeactivateDevicePostProcessor implements Processor {
	public VerizonDeactivateDevicePostProcessor()

	{

	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		ResponseHeader responseheader = new ResponseHeader();

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
		deactivateDeviceResponse.setRequestId("REALVERIZONrequestId");
		deactivateDeviceResponse.setTrackingNumber("REALVERIZONtrackingNumbe");
		exchange.getIn().setBody(deactivateDeviceResponse);
	}
}
