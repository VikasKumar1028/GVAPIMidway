package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.w3c.dom.NodeList;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.ATTJasperDetailException;
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

		LOGGER.info("------------------------**********------------"
				+ exchange.getProperty(Exchange.EXCEPTION_CAUGHT).getClass());
		SoapFault exception = (SoapFault) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

		NodeList nodelist = exception.getDetail().getChildNodes();
		ATTJasperDetailException detailException = null;
	
		for (int i = 0; i < nodelist.getLength(); i++) {

			org.w3c.dom.Node node = nodelist.item(i);
			detailException = new ATTJasperDetailException();
			detailException.setError(node.getTextContent());
			detailException.setException(node.getTextContent());
			detailException.setMessage(node.getTextContent());
			detailException.setRequestId(node.getTextContent());

			LOGGER.info("detailException" + detailException.toString());
			LOGGER.info("-----*************--Node------1------"
					+ node.getNodeName());
			LOGGER.info("-----*************--Node-------3-----"
					+ node.getTextContent());

		}

		LOGGER.info("-----*************------++++-----------2--"
				+ exception.getDetail().getFirstChild().getTextContent());

		LOGGER.info("EXCP ------------------"
				+ exchange.getProperty(Exchange.EXCEPTION_CAUGHT,
						Exception.class));
		SoapFault faultex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT,
				SoapFault.class);
		;
		LOGGER.info("MSG    ------------------" + faultex.getMessage());
		LOGGER.info("DETAIL ------------------" + faultex.getDetail());

		Integer errorCode = Integer.valueOf(faultex.getMessage());

		LOGGER.info("-----*************------++++-----------2--"
				+ exception.getDetail().getFirstChild().getTextContent());

		Response response = new Response();

		response.setResponseCode(errorCode);
		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(detailException.getMessage());

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
