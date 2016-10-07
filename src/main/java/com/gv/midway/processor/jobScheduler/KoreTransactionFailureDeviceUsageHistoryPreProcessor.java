package com.gv.midway.processor.jobScheduler;

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

public class KoreTransactionFailureDeviceUsageHistoryPreProcessor implements
		Processor {

	private static final Logger LOGGER = Logger
			.getLogger(KoreTransactionFailureDeviceUsageHistoryPreProcessor.class
					.getName());

	Environment newEnv;

	public KoreTransactionFailureDeviceUsageHistoryPreProcessor() {
		// Empty Constructor
	}

	public KoreTransactionFailureDeviceUsageHistoryPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:KoreTransactionFailureDeviceUsageHistoryPreProcessor");
		Message message = exchange.getIn();

		DeviceUsage deviceInfo = (DeviceUsage) exchange.getIn().getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		UsageInformationKoreRequest usageInformationKoreRequest = new UsageInformationKoreRequest();

		DeviceId deviceId = deviceInfo.getDeviceId();

		String simNumber = deviceId.getId();

		usageInformationKoreRequest.setSimNumber(simNumber);

		String strRequestBody = objectMapper
				.writeValueAsString(usageInformationKoreRequest);

		LOGGER.info("strRequestBody::" + strRequestBody);

		exchange.setProperty("DeviceId", deviceId);
		exchange.setProperty("CarrierName", deviceInfo.getCarrierName());
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID,
				deviceInfo.getNetSuiteId());

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));

		message.setHeader(Exchange.HTTP_PATH,
				"/json/queryDeviceUsageBySimNumber");
		message.setBody(strRequestBody);

		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:KoreTransactionFailureDeviceUsageHistoryPreProcessor");

	}

}
