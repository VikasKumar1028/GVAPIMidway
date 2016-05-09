package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.verizon.request.ActivateDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class VerizonActivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonActivateDevicePreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Begin:VerizonActivateDevicePreProcessor");

		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		ActivateDeviceRequestVerizon businessRequest = new ActivateDeviceRequestVerizon();
		ActivateDeviceRequest proxyRequest = (ActivateDeviceRequest) exchange
				.getIn().getBody();
		businessRequest.setAccountName(proxyRequest.getDataArea()
				.getAccountName());
		businessRequest.setCarrierIpPoolName(proxyRequest.getDataArea()
				.getCarrierIpPoolName());
		businessRequest.setCarrierName(proxyRequest.getDataArea()
				.getCarrierName());
		businessRequest.setCostCenterCode(proxyRequest.getDataArea()
				.getCostCenterCode());
		businessRequest.setCustomFields(proxyRequest.getDataArea()
				.getCustomFields());
		businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
		businessRequest.setLeadId(proxyRequest.getDataArea().getLeadId());
		businessRequest.setMdnZipCode(proxyRequest.getDataArea()
				.getMdnZipCode());
		businessRequest.setPrimaryPlaceOfUse(proxyRequest.getDataArea()
				.getPrimaryPlaceOfUse());
		businessRequest.setPublicIpRestriction(proxyRequest.getDataArea()
				.getPublicIpRestriction());
		businessRequest.setServicePlan(proxyRequest.getDataArea()
				.getServicePlan());
		businessRequest.setSkuNumber(proxyRequest.getDataArea().getSkuNumber());

		ActivateDevices[] proxyDevicesArray = proxyRequest.getDataArea()
				.getDevices();
		Devices[] businessDevicesArray = new Devices[proxyDevicesArray.length];

		for (int j = 0; j < proxyDevicesArray.length; j++) {

			DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevicesArray[j]
					.getDeviceIds().length];
			ActivateDevices proxyDevices = proxyDevicesArray[j];
			Devices businessDevice = new Devices();

			for (int i = 0; i < proxyDevices.getDeviceIds().length; i++) {
				ActivateDeviceId proxyDeviceId = proxyDevices.getDeviceIds()[i];

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

		/*
		 * message.setHeader("VZ-M2M-Token",
		 * "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		 * message.setHeader("Authorization",
		 * "Bearer 89ba225e1438e95bd05c3cc288d3591");
		 */

		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/activate");

	}

}
