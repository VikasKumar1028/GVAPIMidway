package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.verizon.request.RestoreDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class VerizonRestoreDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonRestoreDevicePreProcessor.class
			.getName());

	// method for processing the message exchange for Verizon
	public void process(Exchange exchange) throws Exception {

		log.info("Begin:VerizonRestoreDevicePreProcessor");

		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		RestoreDeviceRequestVerizon businessRequest = new RestoreDeviceRequestVerizon();
		RestoreDeviceRequest proxyRequest = (RestoreDeviceRequest) exchange
				.getIn().getBody();
		businessRequest.setAccountName(proxyRequest.getDataArea()
				.getAccountName());
		businessRequest.setCustomFields(proxyRequest.getDataArea()
				.getCustomFields());
		businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
		businessRequest.setServicePlan(proxyRequest.getDataArea()
				.getServicePlan());

		Devices[] proxyDevicesArray = proxyRequest.getDataArea().getDevices();
		Devices[] businessDevicesArray = new Devices[proxyDevicesArray.length];

		for (int j = 0; j < proxyDevicesArray.length; j++) {

			DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevicesArray[j]
					.getDeviceIds().length];
			Devices proxyDevices = proxyDevicesArray[j];
			Devices businessDevice = new Devices();

			for (int i = 0; i < proxyDevices.getDeviceIds().length; i++) {
				DeviceId proxyDeviceId = proxyDevices.getDeviceIds()[i];

				DeviceId businessDeviceId = new DeviceId();
				businessDeviceId.setId(proxyDeviceId.getId());
				businessDeviceId.setKind(proxyDeviceId.getKind());

				log.info(proxyDeviceId.getId());

				businessDeviceIdArray[i] = businessDeviceId;

			}
			businessDevicesArray[j] = businessDevice;

			businessDevicesArray[j].setDeviceIds(businessDeviceIdArray);
		}
		businessRequest.setDevices(businessDevicesArray);

		ObjectMapper objectMapper = new ObjectMapper();
		// objectMapper.getSerializationConfig().withSerializationInclusion(Include.NON_EMPTY);

		String strRequestBody = objectMapper
				.writeValueAsString(businessRequest);

		exchange.getIn().setBody(strRequestBody);

		Message message = exchange.getIn();
		String sessionToken = "";
		String authorizationToken = "";

		if (exchange.getProperty(IConstant.VZ_SEESION_TOKEN) != null
				&& exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
			sessionToken = exchange.getProperty(IConstant.VZ_SEESION_TOKEN)
					.toString();
			authorizationToken = exchange.getProperty(
					IConstant.VZ_AUTHORIZATION_TOKEN).toString();
		}

		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/restore");

	}

}
