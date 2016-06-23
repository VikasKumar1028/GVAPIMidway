package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;

public class HeaderErrorProcessor implements Processor {

	Logger log = Logger.getLogger(HeaderErrorProcessor.class.getName());

	Environment newEnv;

	public HeaderErrorProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public HeaderErrorProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		Response response = new Response();

		response.setResponseCode(Integer.parseInt(exchange.getProperty(
				IConstant.RESPONSE_CODE).toString()));
		response.setResponseStatus(exchange.getProperty(
				IConstant.RESPONSE_STATUS).toString());
		response.setResponseDescription(exchange.getProperty(
				IConstant.RESPONSE_DESCRIPTION).toString());

		Header responseHeader = new Header();

		if (exchange.getProperty(IConstant.HEADER) != null) {
			responseHeader = (Header) exchange.getProperty(IConstant.HEADER);
		}

		log.info("endpoint is......"+exchange.getFromEndpoint().toString());
		
		switch (exchange.getFromEndpoint().toString()) {
		case "Endpoint[direct://deviceInformationCarrier]":
			DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
			deviceInformationResponse.setHeader(responseHeader);
			deviceInformationResponse.setResponse(response);
			exchange.getIn().setBody(deviceInformationResponse);

			break;

		case "Endpoint[direct://getDeviceInformationDB]":
			DeviceInformationResponse deviceInformationResponseDB = new DeviceInformationResponse();
			deviceInformationResponseDB.setHeader(responseHeader);
			deviceInformationResponseDB.setResponse(response);
			exchange.getIn().setBody(deviceInformationResponseDB);
 
			break;

		case "Endpoint[direct://updateDevicesDetailsBulk]":
			BatchDeviceResponse batchDeviceResponse = new BatchDeviceResponse();
			batchDeviceResponse.setHeader(responseHeader);
			batchDeviceResponse.setResponse(response);
			exchange.getIn().setBody(batchDeviceResponse);

			break;

		case "Endpoint[direct://updateDeviceDetails]":
			UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
			updateDeviceResponse.setHeader(responseHeader);
			updateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(updateDeviceResponse);

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

		log.info("return object is......"+exchange.getIn().getBody().toString());
	}
}
