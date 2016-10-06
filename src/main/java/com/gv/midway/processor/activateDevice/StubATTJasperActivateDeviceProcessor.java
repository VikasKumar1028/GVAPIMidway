package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;

public class StubATTJasperActivateDeviceProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(StubATTJasperActivateDeviceProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:StubATTJasperActivateDeviceProcessor");

		CarrierProvisioningDeviceResponse carrierProvisioningDeviceResponse = new CarrierProvisioningDeviceResponse();
		CarrierProvisioningDeviceResponseDataArea carrierProvisioningDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();
		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is activated successfully");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-05-09T11:47:15");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("NetSuit");
		responseheader.setTransactionId("b4e2231krdd");
		responseheader.setBsCarrier("ATTJASPER");

		carrierProvisioningDeviceResponse.setHeader(responseheader);
		carrierProvisioningDeviceResponse.setResponse(response);
		carrierProvisioningDeviceResponseDataArea.setOrderNumber("AT44436712257");

		carrierProvisioningDeviceResponse
				.setDataArea(carrierProvisioningDeviceResponseDataArea);

		exchange.getIn().setBody(carrierProvisioningDeviceResponse);

		LOGGER.info("End:StubATTJasperActivateDeviceProcessor");
	}
}
