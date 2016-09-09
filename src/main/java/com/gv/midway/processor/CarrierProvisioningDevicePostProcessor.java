package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;


public class CarrierProvisioningDevicePostProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(CarrierProvisioningDevicePostProcessor.class.getName());

	Environment newEnv;
	
	public CarrierProvisioningDevicePostProcessor() {
		super();
		

	}

	public CarrierProvisioningDevicePostProcessor(Environment env) {
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

		LOGGER.info("Begin:CarrierProvisioningDevicePostProcessor");

		CarrierProvisioningDeviceResponse carrierProvisioningDeviceResponse = new CarrierProvisioningDeviceResponse();
		CarrierProvisioningDeviceResponseDataArea carrierProvisioningDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();

		Response response = new Response();

		LOGGER.info("exchange.getIn().getBody().toString()***************************************"
				+ exchange.getIn().getBody().toString());

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_PROVISIONING_MIDWAY);
			carrierProvisioningDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
					IConstant.MIDWAY_TRANSACTION_ID).toString());

		} else {

			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(exchange.getIn().getBody()
					.toString());

		}

		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		carrierProvisioningDeviceResponse.setHeader(responseheader);
		carrierProvisioningDeviceResponse.setResponse(response);

		carrierProvisioningDeviceResponse.setDataArea(carrierProvisioningDeviceResponseDataArea);

		exchange.getIn().setBody(carrierProvisioningDeviceResponse);

		LOGGER.info("End:CarrierProvisioningDevicePostProcessor");

	}

}
