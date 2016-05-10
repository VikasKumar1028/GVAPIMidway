package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.verizon.request.ChangeDeviceServicePlansRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class VerizonChangeDeviceServicePlansPreProcessor implements Processor {

	Logger log = Logger
			.getLogger(VerizonChangeDeviceServicePlansPostProcessor.class
					.getName());

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Begin::VerizonChangeDeviceServicePlansPreProcessor");
		ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest = exchange
				.getIn().getBody(ChangeDeviceServicePlansRequest.class);

		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		ChangeDeviceServicePlansRequestVerizon businessRequest = new ChangeDeviceServicePlansRequestVerizon();
		ChangeDeviceServicePlansRequest proxyRequest = (ChangeDeviceServicePlansRequest) exchange
				.getIn().getBody();
		businessRequest.setAccountName(proxyRequest.getDataArea()
				.getAccountName());
		businessRequest.setCurrentServicePlan(proxyRequest.getDataArea()
				.getCurrentServicePlan());
		businessRequest.setCustomFields(proxyRequest.getDataArea()
				.getCustomFields());
		businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
		businessRequest.setServicePlan(proxyRequest.getDataArea()
				.getServicePlan());

		// copy of the device to businessRequest
		// As the payload is broken into indivisual devices so only one Device

		// Need to send the complete payload with id and Kind as device
		// parameters
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

				System.out.println(proxyDeviceId.getId());

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

		/*
		 * message.setHeader("VZ-M2M-Token",
		 * "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		 * message.setHeader("Authorization",
		 * "Bearer 89ba225e1438e95bd05c3cc288d3591");
		 */

		
		 exchange.getIn().setBody(changeDeviceServicePlansRequest);
		 
		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "PUT");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/plan");
		log.info("Start::VerizonChangeDeviceServicePlansPreProcessor");

	}

}
