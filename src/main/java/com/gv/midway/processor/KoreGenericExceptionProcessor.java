package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;

public class KoreGenericExceptionProcessor implements Processor {

	Logger log = Logger
			.getLogger(KoreGenericExceptionProcessor.class.getName());

	Environment newEnv;

	public KoreGenericExceptionProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public KoreGenericExceptionProcessor() {
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		log.info("----KoreGenericExceptionProcessor----------"
				+ exception.getResponseBody());
		log.info("----.getStatusCode()----------" + exception.getStatusCode());

		

		ObjectMapper mapper = new ObjectMapper();

		KoreErrorResponse responsePayload = mapper.readValue(
				exception.getResponseBody(), KoreErrorResponse.class);

		log.info("----response payload is----------"
				+ responsePayload.toString());

		Response response = new Response();

	
		response.setResponseCode(IResponse.INVALID_PAYLOAD);

		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(responsePayload.getErrorMessage());

		Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

		if ("Endpoint[direct://deviceInformationCarrier]".equals(exchange
				.getFromEndpoint().toString())) {

			DeviceInformationResponse responseObject = new DeviceInformationResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

		if ("Endpoint[direct://deactivateDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			DeactivateDeviceResponse responseObject = new DeactivateDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

		if ("Endpoint[direct://activateDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			ActivateDeviceResponse responseObject = new ActivateDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

		if ("Endpoint[direct://suspendDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			SuspendDeviceResponse responseObject = new SuspendDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

		if ("Endpoint[direct://reactivateDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			ReactivateDeviceResponse responseObject = new ReactivateDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

		if ("Endpoint[direct://customeFields]".equals(exchange
				.getFromEndpoint().toString())) {

			CustomFieldsDeviceResponse responseObject = new CustomFieldsDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

		if ("Endpoint[direct://changeDeviceServicePlans]".equals(exchange
				.getFromEndpoint().toString())) {
			ChangeDeviceServicePlansResponse responseObject = new ChangeDeviceServicePlansResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);

		}

		if ("Endpoint[direct://restoreDevice]".equals(exchange
				.getFromEndpoint().toString())) {
			RestoreDeviceResponse responseObject = new RestoreDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);

		}

		if ("Endpoint[direct://reactivateDevice]".equals(exchange
				.getFromEndpoint().toString())) {
			ReactivateDeviceResponse responseObject = new ReactivateDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);

		}
	
	}
}
