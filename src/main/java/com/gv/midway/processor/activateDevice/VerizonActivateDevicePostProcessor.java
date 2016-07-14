package com.gv.midway.processor.activateDevice;



import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;

public class VerizonActivateDevicePostProcessor implements Processor {

	static int i = 0;

	Logger log = Logger.getLogger(VerizonActivateDevicePostProcessor.class
			.getName());

	Environment newEnv;

	public VerizonActivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin:VerizonActivateDevicePostProcessor");

		ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();
		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();
	
		Response response = new Response();

		log.info("exchange.getIn().getBody().toString()***************************************"
						+ exchange.getIn().getBody().toString());

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			log.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
			activateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
					IConstant.MIDWAY_TRANSACTION_ID).toString());

		} else {

			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(exchange.getIn().getBody()
					.toString());

		}

		

		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);
		
		activateDeviceResponse.setHeader(responseheader);
		activateDeviceResponse.setResponse(response);

		activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

		exchange.getIn().setBody(activateDeviceResponse);

		log.info("End:VerizonActivateDevicePostProcessor");

	}

}
