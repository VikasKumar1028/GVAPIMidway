package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponseDataArea;

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
		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		Header responseheader = new Header();
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device is deactivate successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("ATTJASPER");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("ATTJASPER");

		deactivateDeviceResponse.setHeader(responseheader);
		deactivateDeviceResponse.setResponse(response);
		deactivateDeviceResponseDataArea.setOrderNumber("ATTJASPER36718236");

		deactivateDeviceResponse.setDataArea(deactivateDeviceResponseDataArea);
		exchange.getIn().setBody(deactivateDeviceResponse);

		LOGGER.info("End:StubATTJasperDeactivateDeviceProcessor");
	}

}
