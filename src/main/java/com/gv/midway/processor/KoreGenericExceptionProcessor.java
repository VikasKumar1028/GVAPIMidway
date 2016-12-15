package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.kore.KoreErrorResponse;


public class KoreGenericExceptionProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(KoreGenericExceptionProcessor.class.getName());

	public KoreGenericExceptionProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:KoreGenericExceptionProcessor");
		final CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

		LOGGER.debug("----KoreGenericExceptionProcessor----------" + exception.getResponseBody());
		LOGGER.debug("----.getStatusCode()----------" + exception.getStatusCode());

		final ObjectMapper mapper = new ObjectMapper();

		final KoreErrorResponse responsePayload = mapper.readValue(exception.getResponseBody(), KoreErrorResponse.class);

		LOGGER.debug("----response payload is----------" + responsePayload.toString());

		final Response response = new Response();
		response.setResponseCode(IResponse.INVALID_PAYLOAD);
		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(responsePayload.getErrorMessage());

		final Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

		switch (exchange.getFromEndpoint().toString()) {

			case "Endpoint[direct://deviceInformationCarrier]":
				final DeviceInformationResponse responseObject = new DeviceInformationResponse();
				responseObject.setHeader(responseHeader);
				responseObject.setResponse(response);
				exchange.getIn().setBody(responseObject);
				break;
			case "Endpoint[direct://activateDevice]":
			case "Endpoint[direct://deactivateDevice]":
			case "Endpoint[direct://suspendDevice]":
			case "Endpoint[direct://customFields]":
			case "Endpoint[direct://changeDeviceServicePlans]":
			case "Endpoint[direct://reactivateDevice]":
			case "Endpoint[direct://restoreDevice]":
				final CarrierProvisioningDeviceResponse restoreDeviceResponse = new CarrierProvisioningDeviceResponse();
				restoreDeviceResponse.setHeader(responseHeader);
				restoreDeviceResponse.setResponse(response);
				exchange.getIn().setBody(restoreDeviceResponse);
				break;
			default:
				break;
		}
		LOGGER.debug("End:KoreGenericExceptionProcessor");
	}
}