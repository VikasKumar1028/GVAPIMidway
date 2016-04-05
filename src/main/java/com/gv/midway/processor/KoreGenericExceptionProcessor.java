package com.gv.midway.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;

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

		Header responseHeader = new Header();
		responseHeader.setApplicationName(newEnv
				.getProperty(IConstant.APPLICATION_NAME));
		responseHeader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();

		responseHeader.setTimestamp(dateFormat.format(date));
		responseHeader.setOrganization(newEnv
				.getProperty(IConstant.ORGANIZATION));
		responseHeader.setSourceName(newEnv
				.getProperty(IConstant.SOURCE_NAME_KORE));
		String TransactionId = (String) exchange.getProperty(newEnv
				.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseHeader.setTransactionId(TransactionId);
		responseHeader.setBsCarrier(newEnv
				.getProperty(IConstant.BSCARRIER_KORE));

		Response response = new Response();
		// response.setResponseCode("InValid Data");
		int statusCodeInt = exception.getStatusCode();
		String statusCode = String.valueOf(statusCodeInt);
		response.setResponseCode(statusCode);
		// response.setResponseStatus("4");
		response.setResponseStatus(exception.getStatusText());
		response.setResponseDescription(exception.getResponseBody());

		if ("Endpoint[direct://deviceInformation]".equals(exchange
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

	}
}
