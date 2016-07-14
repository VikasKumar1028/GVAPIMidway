package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponseDataArea;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponseDataArea;

public class StubVerizonChangeDeviceServicePlansProcessor  implements Processor{

	Logger log = Logger.getLogger(StubVerizonChangeDeviceServicePlansProcessor.class
			.getName());
	@Override
	public void process(Exchange exchange) throws Exception {
		
		log.info("Start::StubVerizonChangeDeviceServicePlansProcessor");

		ChangeDeviceServicePlansResponse changeDeviceServicePlansResponse = new ChangeDeviceServicePlansResponse();

		ChangeDeviceServicePlansResponseDataArea changeDeviceServicePlansResponseDataArea = new ChangeDeviceServicePlansResponseDataArea();
		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("ChangeDeviceServicePlans");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("Verizon");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("Verizon");

		changeDeviceServicePlansResponse.setHeader(responseheader);
		changeDeviceServicePlansResponse.setResponse(response);
		changeDeviceServicePlansResponseDataArea.setOrderNumber("StubVerizonChangeDeviceServicePlansProcessor");

		changeDeviceServicePlansResponse
				.setDataArea(changeDeviceServicePlansResponseDataArea);

		exchange.getIn().setBody(changeDeviceServicePlansResponse);

		log.info("End::StubVerizonChangeDeviceServicePlansProcessor");


	}

}
