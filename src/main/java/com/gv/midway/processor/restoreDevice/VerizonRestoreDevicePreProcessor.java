package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.verizon.request.RestoreDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.CommonUtil;

public class VerizonRestoreDevicePreProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(VerizonRestoreDevicePreProcessor.class.getName());

	// method for processing the message exchange for Verizon
	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:VerizonRestoreDevicePreProcessor");

		LOGGER.debug("Session Parameters  VZSessionToken" + exchange.getProperty(IConstant.VZ_SESSION_TOKEN));
		LOGGER.debug("Session Parameters  VZAuthorization" + exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		RestoreDeviceRequestVerizon businessRequest = new RestoreDeviceRequestVerizon();
		RestoreDeviceRequest proxyRequest = (RestoreDeviceRequest) exchange.getIn().getBody();
		businessRequest.setAccountName(proxyRequest.getDataArea().getAccountName());
		businessRequest.setCustomFields(proxyRequest.getDataArea().getCustomFields());
		businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
		businessRequest.setServicePlan(proxyRequest.getDataArea().getServicePlan());

		MidWayDevices[] proxyDevicesArray = proxyRequest.getDataArea()
				.getDevices();
		Devices[] businessDevicesArray = new Devices[proxyDevicesArray.length];

		for (int j = 0; j < proxyDevicesArray.length; j++) {

			DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevicesArray[j].getDeviceIds().length];
			MidWayDevices proxyDevices = proxyDevicesArray[j];
			Devices businessDevice = new Devices();

			for (int i = 0; i < proxyDevices.getDeviceIds().length; i++) {
				MidWayDeviceId proxyDeviceId = proxyDevices.getDeviceIds()[i];

				DeviceId businessDeviceId = new DeviceId();
				businessDeviceId.setId(proxyDeviceId.getId());
				businessDeviceId.setKind(proxyDeviceId.getKind());

				LOGGER.debug(proxyDeviceId.getId());

				businessDeviceIdArray[i] = businessDeviceId;

			}
			businessDevicesArray[j] = businessDevice;

			businessDevicesArray[j].setDeviceIds(businessDeviceIdArray);
		}
		businessRequest.setDevices(businessDevicesArray);

		ObjectMapper objectMapper = new ObjectMapper();

		String strRequestBody = objectMapper.writeValueAsString(businessRequest);

		exchange.getIn().setBody(strRequestBody);
		Message message = CommonUtil.setMessageHeader(exchange);
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/restore");

		LOGGER.debug("End:VerizonRestoreDevicePreProcessor");

	}

}
