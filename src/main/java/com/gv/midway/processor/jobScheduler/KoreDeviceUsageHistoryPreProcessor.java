package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.usageInformation.kore.request.UsageInformationKoreRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class KoreDeviceUsageHistoryPreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceUsageHistoryPreProcessor.class
			.getName());

	Environment newEnv;

	public KoreDeviceUsageHistoryPreProcessor() {
		//Empty Constructor
	}

	public KoreDeviceUsageHistoryPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("*************Testing**************************************"
				+ exchange.getIn().getBody());

		log.info("Begin:KoreDeviceUsageHistoryPreProcessor");
		Message message = exchange.getIn();
		
	
		log.info("jobDetailDate ------" + exchange.getProperty("jobDetailDate"));

		ObjectMapper objectMapper = new ObjectMapper();

		DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
				.getBody();

		UsageInformationKoreRequest usageInformationKoreRequest = new UsageInformationKoreRequest();



		DeviceId deviceId=CommonUtil.getSimNumber(deviceInfo.getDeviceIds());
		
		String simNumber=deviceId.getId();
		
		
		usageInformationKoreRequest.setSimNumber(simNumber);

	

		String strRequestBody = objectMapper
				.writeValueAsString(usageInformationKoreRequest);

		log.info("strRequestBody::" + strRequestBody);
		
		exchange.setProperty("DeviceId", deviceId);
		exchange.setProperty("CarrierName", deviceInfo.getBs_carrier());
		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));

		message.setHeader(Exchange.HTTP_PATH,
				"/json/queryDeviceUsageBySimNumber");

		message.setBody(strRequestBody);

		exchange.setPattern(ExchangePattern.InOut);

		log.info("message::" + message.toString());

		log.info("End:KoreDeviceUsageHistoryPreProcessor");

	}
}
