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
import com.gv.midway.pojo.customFieldsUpdateDevice.response.CustomFieldsUpdateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;

public class KoreGenericExceptionProcessor implements Processor {

	Logger log = Logger
			.getLogger(KoreGenericExceptionProcessor.class.getName());

	Environment newEnv;

	public KoreGenericExceptionProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public KoreGenericExceptionProcessor() {
		// TODO Auto-generated constructor stub
	}

	public void process(Exchange exchange) throws Exception {

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		log.info("----KoreGenericExceptionProcessor----------"
				+ exception.getResponseBody());
		log.info("----.getStatusCode()----------"
				+ exception.getStatusCode());

		Header responseHeader = new Header();
		responseHeader.setApplicationName(exchange.getProperty(IConstant.APPLICATION_NAME).toString());
		responseHeader.setRegion(exchange.getProperty(IConstant.REGION).toString());
		
		responseHeader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT).toString());
		responseHeader.setOrganization(exchange.getProperty(IConstant.ORGANIZATION).toString());
		responseHeader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME).toString());
	
		responseHeader.setTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
		responseHeader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER).toString());
		
		
		ObjectMapper mapper= new ObjectMapper();
		
		KoreErrorResponse responsePayload = mapper.readValue(exception.getResponseBody(),
				KoreErrorResponse.class);

		log.info("----response payload is----------"
				+ responsePayload.toString());

		Response response = new Response();
	
		/*int statusCodeInt = exception.getStatusCode();
*/	
		response.setResponseCode(IResponse.INVALID_PAYLOAD);
		
		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(responsePayload.getErrorMessage());

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

			CustomFieldsUpdateDeviceResponse responseObject = new CustomFieldsUpdateDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

	}
}
