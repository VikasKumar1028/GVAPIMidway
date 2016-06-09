package com.gv.midway.processor.usageDevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageHistory;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageInformationResponse;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageInformationResponseDataArea;

public class RetrieveDeviceUsageHistoryPostProcessor implements Processor {

	Logger log = Logger.getLogger(RetrieveDeviceUsageHistoryPostProcessor.class
			.getName());

	Environment newEnv;

	public RetrieveDeviceUsageHistoryPostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		log.info("Begin::RetrieveDeviceUsageHistoryPostProcessor");

		Header responseheader = new Header();
		Response response = new Response();
		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));

		Date date = new Date();

		Map map = exchange.getIn().getBody(Map.class);
		ObjectMapper mapper = new ObjectMapper();

		UsageInformationResponse usageResponse = mapper.convertValue(map,
				UsageInformationResponse.class);

		UsageInformationResponseDataArea usageInformationResponseDataArea = new UsageInformationResponseDataArea();

		long totalBytesUsed = 0L;
		if (usageResponse.getUsageHistory() != null) {
			for (UsageHistory history : usageResponse.getUsageHistory()) {
				totalBytesUsed = history.getBytesUsed() + totalBytesUsed;
			}
		}

		// usageInformationResponseDataArea.setTotalUsages(totalBytesUsed);

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			log.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVICE_USAGE_MIDWAY);
			usageInformationResponseDataArea.setTotalUsages(totalBytesUsed);

		} else {

			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(exchange.getIn().getBody()
					.toString());

		}

		responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());

		usageInformationResponseDataArea.setHeader(responseheader);
		usageInformationResponseDataArea.setResponse(response);

		exchange.getIn().setBody(usageInformationResponseDataArea);

		log.info("usageInformationResponseDataArea:::::::::::::::::::"
				+ totalBytesUsed);
		log.info("End::RetrieveDeviceUsageHistoryPostProcessor");
	}
}
