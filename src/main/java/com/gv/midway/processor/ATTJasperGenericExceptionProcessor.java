package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
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
	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperGenericExceptionProcessor.class.getName());

	Environment newEnv;

	public ATTJasperGenericExceptionProcessor(Environment env) {
		super();

		this.newEnv = env;

	}

	public ATTJasperGenericExceptionProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:ATTJasperGenericExceptionProcessor");

		LOGGER.info("------------------------**********------------"
				+ exchange.getProperty(Exchange.EXCEPTION_CAUGHT).getClass());
		SoapFault soapFault = (SoapFault) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

		String message = soapFault.getMessage();

		LOGGER.info("MSG    ------------------" + message);

		// fault String for the fault code.

		Integer errorCode = Integer.valueOf(message);

		String resposneDescription = (String) exchange
				.getProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE);

		LOGGER.info("resposneDescription ------------------"
				+ resposneDescription);

		Response response = new Response();

		response.setResponseCode(errorCode);
		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(resposneDescription);

		LOGGER.info("exchange endpoint of error........."
				+ exchange.getFromEndpoint().toString());

		switch (exchange.getFromEndpoint().toString()) {

		case "Endpoint[direct://deviceInformationCarrier]":
			DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
			deviceInformationResponse.setHeader(responseHeader);
			deviceInformationResponse.setResponse(response);
			exchange.getIn().setBody(deviceInformationResponse);
			break;
		case "Endpoint[direct://activateDevice]":
			CarrierProvisioningDeviceResponse activateDeviceResponse = new CarrierProvisioningDeviceResponse();
			activateDeviceResponse.setHeader(responseHeader);
			activateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(activateDeviceResponse);
			break;
		case "Endpoint[direct://deactivateDevice]":
			CarrierProvisioningDeviceResponse deactivateDeviceResponse = new CarrierProvisioningDeviceResponse();
			deactivateDeviceResponse.setHeader(responseHeader);
			deactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(deactivateDeviceResponse);
			break;
		case "Endpoint[direct://suspendDevice]":
			CarrierProvisioningDeviceResponse suspendDeviceResponse = new CarrierProvisioningDeviceResponse();
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
			CarrierProvisioningDeviceResponse customFieldsDeviceResponse = new CarrierProvisioningDeviceResponse();
			customFieldsDeviceResponse.setHeader(responseHeader);
			customFieldsDeviceResponse.setResponse(response);
			exchange.getIn().setBody(customFieldsDeviceResponse);
			break;
		case "Endpoint[direct://changeDeviceServicePlans]":
			CarrierProvisioningDeviceResponse changeDeviceServicePlansResponse = new CarrierProvisioningDeviceResponse();
			changeDeviceServicePlansResponse.setHeader(responseHeader);
			changeDeviceServicePlansResponse.setResponse(response);
			exchange.getIn().setBody(changeDeviceServicePlansResponse);
			break;
		case "Endpoint[direct://reactivateDevice]":
			CarrierProvisioningDeviceResponse reactivateDeviceResponse = new CarrierProvisioningDeviceResponse();
			reactivateDeviceResponse.setHeader(responseHeader);
			reactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(reactivateDeviceResponse);
			break;
		case "Endpoint[direct://restoreDevice]":
			CarrierProvisioningDeviceResponse restoreDeviceResponse = new CarrierProvisioningDeviceResponse();
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

		LOGGER.info("End:ATTJasperGenericExceptionProcessor");
	}
}
