package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.gv.midway.pojo.verizon.VerizonErrorResponse;

public class VerizonGenericExceptionProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonGenericExceptionProcessor.class
			.getName());

	Environment newEnv;

	public VerizonGenericExceptionProcessor(Environment env) {
		super();

		this.newEnv = env;

	}

	public VerizonGenericExceptionProcessor() {
		// TODO Auto-generated constructor stub
	}

	public void process(Exchange exchange) throws Exception {

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		log.info("----VerizonGenericExceptionProcessor----------"
				+ exception.getResponseBody());
		log.info("----.getStatusCode()----------" + exception.getStatusCode());

		
		log.info("----.exchange----------"+exchange.getFromEndpoint().toString());

		

		Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);
		
		Response response = new Response();
		// TODO SAME Functionality
		if (exception.getStatusCode() == 401
				|| exception
						.getResponseBody()
						.contains(
								"UnifiedWebService.REQUEST_FAILED.SessionToken.Expired")) {
			exchange.setProperty(IConstant.RESPONSE_CODE, "401");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Token");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
					"Not able to retrieve  valid authentication token");
			throw new VerizonSessionTokenExpirationException("401", "401");
		}
		// TODO SAME Functionality
		else {

			ObjectMapper mapper = new ObjectMapper();

			VerizonErrorResponse responsePayload = mapper.readValue(
					exception.getResponseBody(), VerizonErrorResponse.class);

			log.info("----response payload is----------"
					+ responsePayload.toString());

			response.setResponseCode(IResponse.INVALID_PAYLOAD);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(responsePayload.getErrorMessage());

		}

		log.info("exchange endpoint of error........."
				+ exchange.getFromEndpoint().toString());
		
		switch (exchange.getFromEndpoint().toString()) 
		{
		
		case "Endpoint[direct://deviceInformationCarrier]":
			DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
			deviceInformationResponse.setHeader(responseHeader);
			deviceInformationResponse.setResponse(response);
			exchange.getIn().setBody(deviceInformationResponse);

			break;


		case "Endpoint[direct://activateDevice]":

			ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();
			activateDeviceResponse.setHeader(responseHeader);
			activateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(activateDeviceResponse);

			break;

		case "Endpoint[direct://deactivateDevice]":

			DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
			deactivateDeviceResponse.setHeader(responseHeader);
			deactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(deactivateDeviceResponse);

			break;

		case "Endpoint[direct://suspendDevice]":

			SuspendDeviceResponse suspendDeviceResponse = new SuspendDeviceResponse();
			suspendDeviceResponse.setHeader(responseHeader);
			suspendDeviceResponse.setResponse(response);
			exchange.getIn().setBody(suspendDeviceResponse);

			break;

		case "Endpoint[direct://deviceConnectionStatus]":
			ConnectionStatusResponse connectionStatusResponse = new ConnectionStatusResponse();
			connectionStatusResponse.setHeader(responseHeader);
			connectionStatusResponse.setResponse(response);
			exchange.getIn().setBody(connectionStatusResponse);

			break;

		case "Endpoint[direct://deviceSessionBeginEndInfo]":
			SessionBeginEndResponse sessionBeginEndResponse = new SessionBeginEndResponse();
			sessionBeginEndResponse.setHeader(responseHeader);
			sessionBeginEndResponse.setResponse(response);
			exchange.getIn().setBody(sessionBeginEndResponse);

			break;

		case "Endpoint[direct://customeFields]":
			CustomFieldsDeviceResponse customFieldsDeviceResponse = new CustomFieldsDeviceResponse();
			customFieldsDeviceResponse.setHeader(responseHeader);
			customFieldsDeviceResponse.setResponse(response);
			exchange.getIn().setBody(customFieldsDeviceResponse);

			break;

		case "Endpoint[direct://changeDeviceServicePlans]":
			ChangeDeviceServicePlansResponse changeDeviceServicePlansResponse = new ChangeDeviceServicePlansResponse();
			changeDeviceServicePlansResponse.setHeader(responseHeader);
			changeDeviceServicePlansResponse.setResponse(response);
			exchange.getIn().setBody(changeDeviceServicePlansResponse);

			break;

		case "Endpoint[direct://reactivateDevice]":
			ReactivateDeviceResponse reactivateDeviceResponse = new ReactivateDeviceResponse();
			reactivateDeviceResponse.setHeader(responseHeader);
			reactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(reactivateDeviceResponse);

			break;

		case "Endpoint[direct://restoreDevice]":
			RestoreDeviceResponse restoreDeviceResponse = new RestoreDeviceResponse();
			restoreDeviceResponse.setHeader(responseHeader);
			restoreDeviceResponse.setResponse(response);
			exchange.getIn().setBody(restoreDeviceResponse);

			break;

		case "Endpoint[direct://retrieveDeviceUsageHistoryCarrier]":
			UsageInformationResponse usageInformationResponse = new UsageInformationResponse();
			usageInformationResponse.setHeader(responseHeader);
			usageInformationResponse.setResponse(response);
			exchange.getIn().setBody(usageInformationResponse);

			break;

		
		default:
			break;
		}
		
	
		
	}
}
