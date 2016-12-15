package com.gv.midway.processor.jobScheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.usageInformation.kore.response.Usage;
import com.gv.midway.pojo.usageInformation.kore.response.UsageInformationKoreResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreDeviceUsageHistoryPostProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(KoreDeviceUsageHistoryPostProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:KoreDeviceUsageHistoryPostProcessor");
		LOGGER.debug("exchange::::" + exchange.getIn().getBody());
		LOGGER.debug("jobDetailDate ------" + exchange.getProperty(IConstant.JOB_DETAIL_DATE));

		final UsageInformationKoreResponse usageResponse = exchange.getIn().getBody(UsageInformationKoreResponse.class);

		long totalBytesUsed = 0L;

		LOGGER.debug("usageInformationKoreResponse:::::::::" + usageResponse);

		if (usageResponse.getD().getUsage() != null && !usageResponse.getD().getUsage().isEmpty()) {

			for (Usage usage : usageResponse.getD().getUsage()) {

				final String usageDateValue = getKoreDeviceUsageDate(usage);
				LOGGER.debug("usageDateValue: " + usageDateValue);
				LOGGER.debug("IConstant.JOB_DETAIL_DATE: " + exchange.getProperty(IConstant.JOB_DETAIL_DATE));

				if (usageDateValue != null && exchange.getProperty(IConstant.JOB_DETAIL_DATE).equals(usageDateValue)) {
					totalBytesUsed = usage.getDataInBytes().longValue() + totalBytesUsed;
					LOGGER.debug("totalBytesUsed:" + totalBytesUsed);
					break;
				}
			}
		}

		LOGGER.debug("End of Loop totalBytesUsed:::::::::" + totalBytesUsed);

		final JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

		final DeviceUsage deviceUsage = new DeviceUsage();
		deviceUsage.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
		deviceUsage.setDataUsed(totalBytesUsed);
		deviceUsage.setDate(jobDetail.getDate()); // The Day for which Job Ran
		deviceUsage.setTransactionErrorReason(null);
		deviceUsage.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		deviceUsage.setNetSuiteId((Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID));
		deviceUsage.setIsValid(true);
		deviceUsage.setJobId( jobDetail.getJobId());

		exchange.getIn().setBody(deviceUsage);

		LOGGER.debug("End:KoreDeviceUsageHistoryPostProcessor");
	}

	private String getKoreDeviceUsageDate(Usage usage) {

		LOGGER.debug("Begin:KoreUsageDateFormat()");

		final String koreUsageDate = usage.getUsageDate();
		final String longValueOfDate = koreUsageDate.substring(koreUsageDate.indexOf("(") + 1, koreUsageDate.indexOf("-"));

		LOGGER.debug("long value of Date is........." + longValueOfDate);

		final Long valueOfDateInLong = Long.valueOf(longValueOfDate);
		final String timeZoneOfDate = koreUsageDate.substring(koreUsageDate.indexOf("-"), koreUsageDate.indexOf(")"));

		LOGGER.debug("time zone is......." + timeZoneOfDate);

		final DateTimeZone timeZone = DateTimeZone.forID(timeZoneOfDate);

		LOGGER.debug("****************timeZone ID is" + timeZone);

		final DateTime dateTime = new DateTime(valueOfDateInLong, timeZone);

		LOGGER.debug("****************" + dateTime.toString());

		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final String finalUsageDateFormat = df.format(dateTime.toDate());

		LOGGER.debug("finalUsageDateFormat :::::::" + finalUsageDateFormat);

		return finalUsageDateFormat;
	}
}