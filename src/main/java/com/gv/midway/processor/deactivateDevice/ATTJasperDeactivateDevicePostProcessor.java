package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponseDataArea;

public class ATTJasperDeactivateDevicePostProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeactivateDevicePostProcessor.class.getName());

	Environment newEnv;

	public ATTJasperDeactivateDevicePostProcessor() {
		// Empty ConstructorATT_JasperDeviceInformationPostProcessor
	}

	public ATTJasperDeactivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Begin:ATTJasperDeactivateDevicePostProcessor");
		

		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		Response response = new Response();

		LOGGER.info("exchange.getIn().getBody().toString()***************************************"
				+ exchange.getIn().getBody().toString());

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
			deactivateDeviceResponse
					.setDataArea(deactivateDeviceResponseDataArea);

		} else {

			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(exchange.getIn().getBody()
					.toString());
		}

		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);
		deactivateDeviceResponse.setHeader(responseheader);
		deactivateDeviceResponse.setResponse(response);
		deactivateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
				IConstant.MIDWAY_TRANSACTION_ID).toString());

		exchange.getIn().setBody(deactivateDeviceResponse);
		
		LOGGER.info("End:ATTJasperDeactivateDevicePostProcessor");
	}

}
