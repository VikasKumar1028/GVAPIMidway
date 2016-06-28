package com.gv.midway.processor.jobScheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.usageInformation.kore.request.UsageInformationKoreRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreTransactionFailureDeviceUsageHistoryPreProcessor implements
		Processor {

	Logger log = Logger.getLogger(KoreDeviceUsageHistoryPreProcessor.class
			.getName());

	Environment newEnv;

	public KoreTransactionFailureDeviceUsageHistoryPreProcessor() {

	}

	public KoreTransactionFailureDeviceUsageHistoryPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("*************Testing**************************************"
				+ exchange.getIn().getBody());

		log.info("Begin:KoreTransactionFailureDeviceUsageHistoryPreProcessor");
		Message message = exchange.getIn();

		ObjectMapper objectMapper = new ObjectMapper();

		UsageInformationRequest proxyRequest = new UsageInformationRequest();

		UsageInformationKoreRequest usageInformationKoreRequest = new UsageInformationKoreRequest();

		usageInformationKoreRequest.setSimNumber(proxyRequest.getDataArea()
				.getDeviceId().getId());

		String strRequestBody = objectMapper
				.writeValueAsString(usageInformationKoreRequest);

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));

		message.setHeader(Exchange.HTTP_PATH,
				"/json/queryDeviceUsageBySimNumber");
		message.setBody(strRequestBody);

		exchange.setPattern(ExchangePattern.InOut);

		log.info("End:KoreTransactionFailureDeviceUsageHistoryPreProcessor");

	}

}
