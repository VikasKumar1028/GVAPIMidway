package com.gv.midway.processor.jobScheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
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

		LOGGER.info("Begin:KoreDeviceUsageHistoryPostProcessor");
		LOGGER.info("exchange::::" + exchange.getIn().getBody());
		LOGGER.info("jobDetailDate ------" + exchange.getProperty("jobDetailDate"));

		final Map map = exchange.getIn().getBody(Map.class);
		final ObjectMapper mapper = new ObjectMapper();

		final UsageInformationKoreResponse usageResponse = mapper.convertValue(map, UsageInformationKoreResponse.class);

		long totalBytesUsed = 0L;

		LOGGER.info("usageInformationKoreResponse:::::::::" + usageResponse.toString());

		if (usageResponse.getD().getUsage() != null && !usageResponse.getD().getUsage().isEmpty()) {

			for (Usage usage : usageResponse.getD().getUsage()) {

				final String usageDateValue = getKoreDeviceUsageDate(usage);
				LOGGER.info("jobDetailDate::" + exchange.getProperty("jobDetailDate"));

				if (usageDateValue != null && exchange.getProperty("jobDetailDate").equals(usageDateValue)) {
					totalBytesUsed = usage.getDataInBytes().longValue() + totalBytesUsed;
					LOGGER.info("totalBytesUsed:" + totalBytesUsed);
					break;
				}
			}
		}

		LOGGER.info("End of Loop totalBytesUsed:::::::::" + totalBytesUsed);

		final JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

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

		LOGGER.info("End:KoreDeviceUsageHistoryPostProcessor");
	}

	private String getKoreDeviceUsageDate(Usage usage) {

		LOGGER.info("Begin:KoreUsageDateFormat()");

		final String koreUsageDate = usage.getUsageDate();
		final String longValueOfDate = koreUsageDate.substring(koreUsageDate.indexOf("(") + 1, koreUsageDate.indexOf("-"));

		LOGGER.info("long value of Date is........." + longValueOfDate);

		final Long valueOfDateInLong = Long.valueOf(longValueOfDate);
		final String timeZoneOfDate = koreUsageDate.substring(koreUsageDate.indexOf("-"), koreUsageDate.indexOf(")"));

		LOGGER.info("time zone is......." + timeZoneOfDate);

		final DateTimeZone timeZone = DateTimeZone.forID(timeZoneOfDate);

		LOGGER.info("****************timeZone ID is" + timeZone);

		final DateTime dateTime = new DateTime(valueOfDateInLong, timeZone);

		LOGGER.info("****************" + dateTime.toString());

		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final String finalUsageDateFormat = df.format(dateTime.toDate());

		LOGGER.info("finalUsageDateFormat :::::::" + finalUsageDateFormat);

		return finalUsageDateFormat;
	}
}