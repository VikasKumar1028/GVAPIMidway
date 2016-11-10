package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;

public class ATTJasperGenericExceptionProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(ATTJasperGenericExceptionProcessor.class.getName());

	public ATTJasperGenericExceptionProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:ATTJasperGenericExceptionProcessor");
		LOGGER.info("------------------------**********------------" + exchange.getProperty(Exchange.EXCEPTION_CAUGHT).getClass());

		final SoapFault soapFault = (SoapFault) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		final Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

		final String message = soapFault.getMessage();

		LOGGER.info("MSG    ------------------" + message);

		// fault String for the fault code.
		final Integer errorCode = Integer.valueOf(message);

		final String responseDescription = (String) exchange.getProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE);

		LOGGER.info("responseDescription ------------------" + responseDescription);

		final Response response = new Response();
		response.setResponseCode(errorCode);
		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(responseDescription);

		LOGGER.info("exchange endpoint of error........." + exchange.getFromEndpoint().toString());

		switch (exchange.getFromEndpoint().toString()) {
			case "Endpoint[direct://deviceInformationCarrier]":
				final DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
				deviceInformationResponse.setHeader(responseHeader);
				deviceInformationResponse.setResponse(response);
				exchange.getIn().setBody(deviceInformationResponse);
				break;
			case "Endpoint[direct://deactivateDevice]":
			case "Endpoint[direct://activateDevice]":
			case "Endpoint[direct://suspendDevice]":
			case "Endpoint[direct://customFields]":
			case "Endpoint[direct://changeDeviceServicePlans]":
			case "Endpoint[direct://reactivateDevice]":
			case "Endpoint[direct://restoreDevice]":
				final CarrierProvisioningDeviceResponse suspendDeviceResponse = new CarrierProvisioningDeviceResponse();
				suspendDeviceResponse.setHeader(responseHeader);
				suspendDeviceResponse.setResponse(response);
				exchange.getIn().setBody(suspendDeviceResponse);
				break;
			case "Endpoint[direct://deviceConnectionStatus]":
				final ConnectionStatusResponse connectionStatusResponse = new ConnectionStatusResponse();
				connectionStatusResponse.setHeader(responseHeader);
				connectionStatusResponse.setResponse(response);
				exchange.getIn().setBody(connectionStatusResponse);
				break;
			case "Endpoint[direct://deviceSessionBeginEndInfo]":
				final SessionBeginEndResponse sessionBeginEndResponse = new SessionBeginEndResponse();
				sessionBeginEndResponse.setHeader(responseHeader);
				sessionBeginEndResponse.setResponse(response);
				exchange.getIn().setBody(sessionBeginEndResponse);
				break;
			case "Endpoint[direct://retrieveDeviceUsageHistoryCarrier]":
				final UsageInformationResponse usageInformationResponse = new UsageInformationResponse();
				usageInformationResponse.setHeader(responseHeader);
				usageInformationResponse.setResponse(response);
				exchange.getIn().setBody(usageInformationResponse);
				break;
			default:
				break;
		}

		LOGGER.info("End:ATTJasperGenericExceptionProcessor");
	}
}