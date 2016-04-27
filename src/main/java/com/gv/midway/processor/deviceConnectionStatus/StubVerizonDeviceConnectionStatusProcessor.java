package com.gv.midway.processor.deviceConnectionStatus;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponseDataArea;
import com.gv.midway.processor.suspendDevice.StubVerizonSuspendDeviceProcessor;

public class StubVerizonDeviceConnectionStatusProcessor implements Processor {

	Logger log = Logger.getLogger(StubVerizonDeviceConnectionStatusProcessor.class
			.getName());
	
	public void process(Exchange exchange) throws Exception {

		log.info("Start::StubVerizonDeviceConnectionStatusProcessor");
		
		ConnectionStatusResponse connectionStatusReponse = new ConnectionStatusResponse();
		
		ConnectionStatusResponseDataArea dataArea =  new ConnectionStatusResponseDataArea();
		Response response = new Response();
		
		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseDescription("Request processed successfully");
		response.setResponseStatus("SUCCESS");
		
		Header responseheader = new Header();
		
		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");
		
		connectionStatusReponse.setHeader(responseheader);
		connectionStatusReponse.setResponse(response);
		dataArea.setConnectionStatus("Device In Session");
		connectionStatusReponse.setDataArea(dataArea);
		

		exchange.getIn().setBody(connectionStatusReponse);
		log.info("End::StubVerizonDeviceConnectionStatusProcessor");
	}

}
