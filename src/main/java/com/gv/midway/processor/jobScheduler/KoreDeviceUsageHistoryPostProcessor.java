package com.gv.midway.processor.jobScheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.usageInformation.kore.response.UsageInformationKoreResponse;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageHistory;
import com.gv.midway.pojo.usageInformation.verizon.response.VerizonUsageInformationResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreDeviceUsageHistoryPostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceUsageHistoryPreProcessor.class
			.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin:KoreDeviceUsageHistoryPostProcessor");

		System.out.println("exchange::::" + exchange.getIn().getBody());

		UsageInformationKoreResponse usageInformationKoreResponse = (UsageInformationKoreResponse) exchange
				.getIn().getBody();

		System.out.println("usageInformationKoreResponse:::::::::"				+ usageInformationKoreResponse.toString());

		DeviceUsage deviceUsage = new DeviceUsage();
		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

		long totalBytesUsed = 0L;
		/*
		 * if (usageResponse.getUsageHistory() != null) { for (UsageHistory
		 * history : usageResponse.getUsageHistory()) { totalBytesUsed =
		 * history.getBytesUsed() + totalBytesUsed; } }
		 */
		deviceUsage
				.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
		deviceUsage.setDataUsed(totalBytesUsed);
		// The Day for which Job Ran
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = (Date) formatter.parse(jobDetail.getDate());
		deviceUsage.setDate(date);
		deviceUsage.setTransactionErrorReason(null);
		deviceUsage
				.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		deviceUsage.setNetSuiteId((String) exchange.getProperty("NetSuiteId"));
		deviceUsage.setIsValid(true);
		deviceUsage.setBillCycleComplete(false);

		exchange.getIn().setBody(deviceUsage);

		log.info("Begin:KoreDeviceUsageHistoryPostProcessor");
	}

}
