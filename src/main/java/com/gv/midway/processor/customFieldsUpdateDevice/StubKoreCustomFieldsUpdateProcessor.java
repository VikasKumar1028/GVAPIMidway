package com.gv.midway.processor.customFieldsUpdateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import com.gv.midway.pojo.customFieldsUpdateDevice.response.CustomFieldsUpdateDeviceResponse;
import com.gv.midway.pojo.customFieldsUpdateDevice.response.CustomFieldsUpdateDeviceResponseDataArea;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePostProcessor;

public class StubKoreCustomFieldsUpdateProcessor implements Processor {

	Logger log = Logger.getLogger(StubKoreCustomFieldsUpdateProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		log.info("Start::StubKoreUpdateCustomeFieldDeviceProcessor");

		CustomFieldsUpdateDeviceResponse updateCustomeFieldDeviceResponse = new CustomFieldsUpdateDeviceResponse();

		CustomFieldsUpdateDeviceResponseDataArea updateCustomeFieldDeviceResponseDataArea = new CustomFieldsUpdateDeviceResponseDataArea();
		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("UpdateCustomeFieldDevice");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("KORE");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("KORE");

		updateCustomeFieldDeviceResponse.setHeader(responseheader);
		updateCustomeFieldDeviceResponse.setResponse(response);
		updateCustomeFieldDeviceResponseDataArea.setOrderNumber("StubKoreUpdateCustomeFieldDeviceProcessor");

		updateCustomeFieldDeviceResponse
				.setDataArea(updateCustomeFieldDeviceResponseDataArea);

		exchange.getIn().setBody(updateCustomeFieldDeviceResponse);
		log.info("End::StubKoreUpdateCustomeFieldDeviceProcessor");

	}

}
