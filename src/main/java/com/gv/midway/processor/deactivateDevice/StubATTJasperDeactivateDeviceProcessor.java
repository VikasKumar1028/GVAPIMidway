package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;

public class StubATTJasperDeactivateDeviceProcessor implements Processor {
	private static final Logger LOGGER = Logger
			.getLogger(StubATTJasperDeactivateDeviceProcessor.class.getName());

	Environment newEnv;

	public StubATTJasperDeactivateDeviceProcessor() {
		// Empty ConstructorATT_JasperDeviceInformationPostProcessor
	}

	public StubATTJasperDeactivateDeviceProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		LOGGER.info("Begin::StubATTJasperDeactivateDeviceProcessor");

		CarrierProvisioningDeviceResponse carrierProvisioningDeviceResponse = new CarrierProvisioningDeviceResponse();
		CarrierProvisioningDeviceResponseDataArea carrierProvisioningDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is deactivate successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("NetSuit");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("ATTJASPER");

		carrierProvisioningDeviceResponse.setHeader(responseheader);
		carrierProvisioningDeviceResponse.setResponse(response);
		carrierProvisioningDeviceResponseDataArea
				.setOrderNumber("ATTJASPER36718236");

		carrierProvisioningDeviceResponse
				.setDataArea(carrierProvisioningDeviceResponseDataArea);
		exchange.getIn().setBody(carrierProvisioningDeviceResponse);

		LOGGER.info("End:StubATTJasperDeactivateDeviceProcessor");
	}

}
