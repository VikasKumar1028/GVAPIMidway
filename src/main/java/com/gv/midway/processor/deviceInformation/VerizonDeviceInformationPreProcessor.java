package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonDeviceInformationPreProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(VerizonDeviceInformationPreProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:VerizonDeviceInformationPreProcessor");

		LOGGER.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		LOGGER.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		DeviceInformationRequest req = (DeviceInformationRequest) exchange
				.getIn().getBody();
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, req.getDataArea()
				.getNetSuiteId());
		DeviceId deviceId = req.getDataArea().getDeviceId();

		exchange.setProperty(IConstant.MIDWAY_DEVICE_ID, deviceId);

		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("deviceId", deviceId);

		exchange.getIn().setBody(obj);
		Message message = CommonUtil.setMessageHeader(exchange);

		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/list");

		LOGGER.info("End:VerizonDeviceInformationPreProcessor");

	}

}
