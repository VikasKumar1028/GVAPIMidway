package com.gv.midway.processor.usageDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponseDataArea;
import com.gv.midway.processor.activateDevice.StubVerizonActivateDeviceProcessor;

public class StubVerizonRetrieveDeviceUsageHistoryProcessor implements Processor {

	
	Logger log = Logger.getLogger(StubVerizonActivateDeviceProcessor.class
			.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin::StubVerizonRetrieveDeviceUsageHistoryProcessor");
		UsageInformationResponse usageInformationResponse = new UsageInformationResponse();

		UsageInformationResponseDataArea usageInformationResponseDataArea = new UsageInformationResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Retrieve Device Usage History");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		usageInformationResponse.setHeader(responseheader);
		usageInformationResponse.setResponse(response);
		usageInformationResponseDataArea.setTotalUsages(12314517L);
		usageInformationResponse.setDataArea(usageInformationResponseDataArea);
		
		
		exchange.getIn().setBody(usageInformationResponse);
		log.info("End::StubVerizonRetrieveDeviceUsageHistoryProcessor");
	}
}
