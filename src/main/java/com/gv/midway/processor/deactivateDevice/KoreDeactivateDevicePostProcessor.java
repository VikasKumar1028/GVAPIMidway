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

public class KoreDeactivateDevicePostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeactivateDevicePostProcessor.class
			.getName());

	public KoreDeactivateDevicePostProcessor() {
		//Empty Constructor
	}

	Environment newEnv;

	public KoreDeactivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}
	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin::KoreDeActivateDevicePostProcessor");
		
		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
		

		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		deactivateDeviceResponse.setHeader(responseheader);

		deactivateDeviceResponse.setResponse(response);
		deactivateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
				IConstant.MIDWAY_TRANSACTION_ID).toString());
		
		deactivateDeviceResponse.setDataArea(deactivateDeviceResponseDataArea);

		exchange.getIn().setBody(deactivateDeviceResponse);
		
		log.info("End::KoreDeActivateDevicePostProcessor");
	}
}
