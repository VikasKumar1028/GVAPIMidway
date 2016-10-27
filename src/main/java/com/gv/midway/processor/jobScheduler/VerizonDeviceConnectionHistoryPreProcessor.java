package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonDeviceConnectionHistoryPreProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(VerizonDeviceConnectionHistoryPreProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:VerizonDeviceConnectionHistoryPreProcessor");
		LOGGER.info("Session Parameters  VZSessionToken" + exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		LOGGER.info("Session Parameters  VZAuthorization" + exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		final DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn().getBody();

		// Fetching Recommended device Identifiers
		final DeviceId recommendedDeviceId = CommonUtil.getRecommendedDeviceIdentifier(deviceInfo.getDeviceIds());

		final DeviceId device = new DeviceId();
		device.setId(recommendedDeviceId.getId());
		device.setKind(recommendedDeviceId.getKind());

		final ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
		dataArea.setDeviceId(device);
		dataArea.setLatest(exchange.getProperty(IConstant.JOB_END_TIME).toString());
		dataArea.setEarliest(exchange.getProperty(IConstant.JOB_START_TIME).toString());

		final ObjectMapper objectMapper = new ObjectMapper();

		final String strRequestBody = objectMapper.writeValueAsString(dataArea);

		final Message message = CommonUtil.setMessageHeader(exchange);
		message.setHeader(Exchange.HTTP_PATH, "/devices/connections/actions/listHistory");

		exchange.setProperty("DeviceId", device);
		exchange.setProperty("CarrierName", deviceInfo.getBs_carrier());
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());
		exchange.setPattern(ExchangePattern.InOut);
		exchange.getIn().setBody(strRequestBody);

		LOGGER.info("End:VerizonDeviceConnectionHistoryPreProcessor");
	}
}