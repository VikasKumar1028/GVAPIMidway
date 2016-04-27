package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;

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

		Header responseHeader = new Header();
		Response response = new Response();
		
		
		responseHeader.setApplicationName(exchange.getProperty(IConstant.APPLICATION_NAME).toString());
		responseHeader.setRegion(exchange.getProperty(IConstant.REGION).toString());
		
		responseHeader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT).toString());
		responseHeader.setOrganization(exchange.getProperty(IConstant.ORGANIZATION).toString());
		responseHeader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME).toString());
		//String TransactionId = (String) exchange.getProperty(newEnv.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseHeader.setTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
		responseHeader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER).toString());
		
		if (exchange.getProperty(IConstant.RESPONSE_CODE) != null) {

			response.setResponseCode(Integer.parseInt(exchange.getProperty(
					IConstant.RESPONSE_CODE).toString()));
			response.setResponseStatus(exchange.getProperty(
					IConstant.RESPONSE_STATUS).toString());
			response.setResponseDescription(exchange.getProperty(
					IConstant.RESPONSE_DESCRIPTION).toString());

		} else {

			response.setResponseCode(IResponse.CONNECTION_ERROR_CODE);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_CONNECTION_MIDWAYDB);

		}

		/*String TransactionId = (String) exchange.getProperty(newEnv
				.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(TransactionId);*/

		if ("Endpoint[direct://deviceInformationCarrier]".equals(exchange
				.getFromEndpoint().toString())) {

			DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
			deviceInformationResponse.setHeader(responseHeader);
			deviceInformationResponse.setResponse(response);
			exchange.getIn().setBody(deviceInformationResponse);

		}

		if ("Endpoint[direct://activateDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();
			activateDeviceResponse.setHeader(responseHeader);
			activateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(activateDeviceResponse);

		}

		if ("Endpoint[direct://deactivateDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
			deactivateDeviceResponse.setHeader(responseHeader);
			deactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(deactivateDeviceResponse);

		}

		if ("Endpoint[direct://suspendDevice]".equals(exchange
				.getFromEndpoint().toString())) {

			SuspendDeviceResponse suspendDeviceResponse = new SuspendDeviceResponse();
			suspendDeviceResponse.setHeader(responseHeader);
			suspendDeviceResponse.setResponse(response);
			exchange.getIn().setBody(suspendDeviceResponse);

		}
		
		if ("Endpoint[direct://deviceConnectionStatus]".equals(exchange
				.getFromEndpoint().toString())) {

			ConnectionStatusResponse connectionStatusResponse = new ConnectionStatusResponse();
			connectionStatusResponse.setHeader(responseHeader);
			connectionStatusResponse.setResponse(response);
			exchange.getIn().setBody(connectionStatusResponse);

		}
		
	}

}
