package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;


public class CarrierProvisioningDevicePostProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(CarrierProvisioningDevicePostProcessor.class.getName());

	public CarrierProvisioningDevicePostProcessor() {
		super();
	}

	/*
     * (non-Javadoc)
     *
     * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
     */
	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:CarrierProvisioningDevicePostProcessor");

		final CarrierProvisioningDeviceResponseDataArea carrierProvisioningDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();
		final Response response = new Response();

		LOGGER.debug("RequestID::" + exchange.getIn().getBody().toString());

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_PROVISIONING_MIDWAY);
			carrierProvisioningDeviceResponseDataArea.setOrderNumber(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
		} else {
			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(exchange.getIn().getBody().toString());
		}

		final Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

		final CarrierProvisioningDeviceResponse carrierProvisioningDeviceResponse = new CarrierProvisioningDeviceResponse();
		carrierProvisioningDeviceResponse.setHeader(responseHeader);
		carrierProvisioningDeviceResponse.setResponse(response);
		carrierProvisioningDeviceResponse.setDataArea(carrierProvisioningDeviceResponseDataArea);

		exchange.getIn().setBody(carrierProvisioningDeviceResponse);

		LOGGER.debug("End:CarrierProvisioningDevicePostProcessor");
	}
}