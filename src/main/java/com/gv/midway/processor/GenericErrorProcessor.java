package com.gv.midway.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;

public class GenericErrorProcessor implements Processor {

	Environment newEnv;

	public GenericErrorProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public GenericErrorProcessor() {
		// TODO Auto-generated constructor stub
	}

	public void process(Exchange exchange) throws Exception {

		ResponseHeader responseheader = new ResponseHeader();
		Response response = new Response();
		responseheader.setApplicationName(newEnv
				.getProperty(IConstant.APPLICATION_NAME));
		responseheader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setOrganization(newEnv
				.getProperty(IConstant.ORGANIZATION));
		responseheader.setSourceName(exchange.getIn().getHeader("sourceName")
				.toString());

		responseheader.setTransactionId("");
		
		if (exchange.getProperty(IConstant.RESPONSE_CODE) != null) {

			response.setResponseCode(exchange.getProperty(
					IConstant.RESPONSE_CODE).toString());
			response.setResponseStatus(exchange.getProperty(
					IConstant.RESPONSE_STATUS).toString());
			response.setResponseDescription(exchange.getProperty(
					IConstant.RESPONSE_DESCRIPTION).toString());
		} else {

			response.setResponseCode("Conenction Error");
			response.setResponseStatus("Conenction Error");
			response.setResponseDescription("Conenction Error");
		}

		String TransactionId = (String) exchange.getProperty(newEnv
				.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(TransactionId);

		if ("Endpoint[direct://deviceInformation]".equals(exchange
				.getFromEndpoint().toString())) {

			DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
			deviceInformationResponse.setHeader(responseheader);
			deviceInformationResponse.setResponse(response);
			exchange.getIn().setBody(deviceInformationResponse);

		}

		if ("Endpoint[direct://activateDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();
			activateDeviceResponse.setHeader(responseheader);
			activateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(activateDeviceResponse);

		}

	}

}
