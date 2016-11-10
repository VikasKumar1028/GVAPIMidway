package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.gv.midway.pojo.verizon.VerizonErrorResponse;
import com.gv.midway.utility.CommonUtil;

public class VerizonGenericExceptionProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(VerizonGenericExceptionProcessor.class.getName());

	public VerizonGenericExceptionProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:VerizonGenericExceptionProcessor");
		final CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

		LOGGER.info("----VerizonGenericExceptionProcessor----------" + exception.getResponseBody());
		LOGGER.info("----.getStatusCode()----------" + exception.getStatusCode());
		LOGGER.info("----.exchange----------" + exchange.getFromEndpoint().toString());

		final Response response = new Response();
		if (exception.getStatusCode() == 401
				|| exception.getResponseBody().contains("UnifiedWebService.REQUEST_FAILED.SessionToken.Expired")) {
			exchange.setProperty(IConstant.RESPONSE_CODE, "401");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Token");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, "Not able to retrieve  valid authentication token");
			CommonUtil.setTokenGenerationRequired();
			throw new VerizonSessionTokenExpirationException("401", "401");
		} else {
			final ObjectMapper mapper = new ObjectMapper();
			final VerizonErrorResponse responsePayload = mapper.readValue(exception.getResponseBody(), VerizonErrorResponse.class);

			LOGGER.info("----response payload is----------" + responsePayload.toString());

			response.setResponseCode(IResponse.INVALID_PAYLOAD);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(responsePayload.getErrorMessage());
		}

		LOGGER.info("exchange endpoint of error........." + exchange.getFromEndpoint().toString());

		final Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);
		//noinspection Duplicates
		switch (exchange.getFromEndpoint().toString()) {

			case "Endpoint[direct://deviceInformationCarrier]":
				final DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
				deviceInformationResponse.setHeader(responseHeader);
				deviceInformationResponse.setResponse(response);
				exchange.getIn().setBody(deviceInformationResponse);
				break;
			case "Endpoint[direct://deviceConnectionStatus]":
				final ConnectionStatusResponse connectionStatusResponse = new ConnectionStatusResponse();
				connectionStatusResponse.setHeader(responseHeader);
				connectionStatusResponse.setResponse(response);
				exchange.getIn().setBody(connectionStatusResponse);
				break;
			case "Endpoint[direct://deviceSessionBeginEndInfo]":
			case "Endpoint[direct://deviceSessionInfo]":
				final SessionBeginEndResponse sessionBeginEndResponse = new SessionBeginEndResponse();
				sessionBeginEndResponse.setHeader(responseHeader);
				sessionBeginEndResponse.setResponse(response);
				exchange.getIn().setBody(sessionBeginEndResponse);
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
			case "Endpoint[direct://retrieveDeviceUsageHistoryCarrier]":
			case "Endpoint[direct://deviceSessionUsage]":
				final UsageInformationResponse usageInformationResponse = new UsageInformationResponse();
				usageInformationResponse.setHeader(responseHeader);
				usageInformationResponse.setResponse(response);
				exchange.getIn().setBody(usageInformationResponse);
				break;
			default:
				break;
		}
		LOGGER.info("End:VerizonGenericExceptionProcessor");
	}
}