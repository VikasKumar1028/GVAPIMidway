package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonTransactionFailureDeviceUsageHistoryPreProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(VerizonTransactionFailureDeviceUsageHistoryPreProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:VerizonTransactionFailureDeviceUsageHistoryPreProcessor");

		final DeviceUsage deviceInfo = (DeviceUsage) exchange.getIn().getBody();
		final String jobStartTime = exchange.getProperty(IConstant.JOB_START_TIME).toString();
		final String jobEndTime = exchange.getProperty(IConstant.JOB_END_TIME).toString();

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
		message.setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list");

		exchange.setProperty("DeviceId", device);
		exchange.setProperty("CarrierName", deviceInfo.getCarrierName());
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());
		exchange.setPattern(ExchangePattern.InOut);
		exchange.getIn().setBody(strRequestBody);

		LOGGER.info("End:VerizonTransactionFailureDeviceUsageHistoryPreProcessor");
	}
}