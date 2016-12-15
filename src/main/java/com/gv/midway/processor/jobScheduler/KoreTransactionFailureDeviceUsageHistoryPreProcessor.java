package com.gv.midway.processor.jobScheduler;

import com.gv.midway.utility.MessageStuffer;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.usageInformation.kore.request.UsageInformationKoreRequest;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreTransactionFailureDeviceUsageHistoryPreProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(KoreDeviceUsageHistoryPreProcessor.class.getName());

	private Environment newEnv;

	public KoreTransactionFailureDeviceUsageHistoryPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:KoreTransactionFailureDeviceUsageHistoryPreProcessor");
		final Message message = exchange.getIn();
		final DeviceUsage deviceInfo = (DeviceUsage) message.getBody();
		final DeviceId deviceId = deviceInfo.getDeviceId();
		final String simNumber = deviceId.getId();

		final UsageInformationKoreRequest usageInformationKoreRequest = new UsageInformationKoreRequest();
		usageInformationKoreRequest.setSimNumber(simNumber);

		final ObjectMapper objectMapper = new ObjectMapper();
		final String strRequestBody = objectMapper.writeValueAsString(usageInformationKoreRequest);

		LOGGER.debug("strRequestBody::" + strRequestBody);

		MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/queryDeviceUsageBySimNumber", strRequestBody);

		exchange.setProperty("DeviceId", deviceId);
		exchange.setProperty("CarrierName", deviceInfo.getCarrierName());
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.debug("End:KoreTransactionFailureDeviceUsageHistoryPreProcessor");
	}
}