package com.gv.midway.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
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

		Header responseheader = new Header();
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

			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

		} else {

			response.setResponseCode(IResponse.CONNECTION_ERROR_CODE);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_CONNECTION_MIDWAYDB);
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

		if ("Endpoint[direct://deactivateDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
			deactivateDeviceResponse.setHeader(responseheader);
			deactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(deactivateDeviceResponse);

		}

	}

}
