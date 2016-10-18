package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonTransactionFailureDeviceConnectionHistoryPreProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(VerizonTransactionFailureDeviceConnectionHistoryPreProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:VerizonTransactionFailureDeviceConnectionHistoryPreProcessor");
		LOGGER.info("Session Parameters  VZSessionToken" + exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		LOGGER.info("Session Parameters  VZAuthorization" + exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		final DeviceConnection deviceInfo = (DeviceConnection) exchange.getIn().getBody();
		final String jobStartTime = exchange.getProperty("jobStartTime").toString();
		final String jobEndTime = exchange.getProperty("jobEndTime").toString();

		final DeviceId device = new DeviceId();
		// Fetching Recommended device Identifiers
		device.setId(deviceInfo.getDeviceId().getId());
		device.setKind(deviceInfo.getDeviceId().getKind());

		final ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
		dataArea.setDeviceId(device);
		dataArea.setLatest(jobEndTime);
		dataArea.setEarliest(jobStartTime);

		final ObjectMapper objectMapper = new ObjectMapper();
		final String strRequestBody = objectMapper.writeValueAsString(dataArea);

		final Message message = CommonUtil.setMessageHeader(exchange);
		message.setHeader(Exchange.HTTP_PATH, "/devices/connections/actions/listHistory");

		exchange.setProperty("DeviceId", device);
		exchange.setProperty("CarrierName", deviceInfo.getCarrierName());
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());
		exchange.getIn().setBody(strRequestBody);
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:VerizonTransactionFailureDeviceConnectionHistoryPreProcessor");
	}
}