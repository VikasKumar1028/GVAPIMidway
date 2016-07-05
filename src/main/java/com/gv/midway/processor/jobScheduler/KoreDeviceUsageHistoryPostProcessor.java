package com.gv.midway.processor.jobScheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	Logger log = Logger.getLogger(KoreDeviceUsageHistoryPostProcessor.class
			.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin:KoreDeviceUsageHistoryPostProcessor");
		log.info("exchange::::" + exchange.getIn().getBody());
		log.info("jobDetailDate ------" + exchange.getProperty("jobDetailDate"));

		Map map = exchange.getIn().getBody(Map.class);
		ObjectMapper mapper = new ObjectMapper();

		UsageInformationKoreResponse usageResponse = mapper.convertValue(map,
				UsageInformationKoreResponse.class);

		long totalBytesUsed = 0L;

		String usageDatevalue = null;

		log.info("usageInformationKoreResponse:::::::::"
				+ usageResponse.toString());

		if (usageResponse.getD().getUsage() != null && usageResponse.getD().getUsage().size()>0) 
		{

			for (Usage usage : usageResponse.getD().getUsage()) {

				usageDatevalue = getKoreDeviceUsageDate(usage);
				log.info("jobDetailDate::"+exchange.getProperty("jobDetailDate"));
				
				
				if (usageDatevalue!=null && exchange.getProperty("jobDetailDate").equals(usageDatevalue))
				{

					totalBytesUsed = usage.getDataInBytes().longValue()
							+ totalBytesUsed;
					log.info("totalBytesUsed:" + totalBytesUsed);
					
					break;
				}

			}
		}

		log.info("End of Loop totalBytesUsed:::::::::" + totalBytesUsed);
		DeviceUsage deviceUsage = new DeviceUsage();
		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

		deviceUsage
				.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
		deviceUsage.setDataUsed(totalBytesUsed);
		// The Day for which Job Ran
/*		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = (Date) formatter.parse(jobDetail.getDate());*/
		deviceUsage.setDate(jobDetail.getDate());
		deviceUsage.setTransactionErrorReason(null);
		deviceUsage
				.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		deviceUsage.setNetSuiteId((String) exchange.getProperty("NetSuiteId"));
		deviceUsage.setIsValid(true);
		deviceUsage.setBillCycleComplete(false);

		exchange.getIn().setBody(deviceUsage);

		log.info("End:KoreDeviceUsageHistoryPostProcessor");
	}

	private String getKoreDeviceUsageDate(Usage usage) {

		log.info("Begin:KoreUsageDateFormate()");

		String koreUsageDate=usage.getUsageDate();
			
        String longValueOfDate=koreUsageDate.substring(koreUsageDate.indexOf("(")+1,koreUsageDate.indexOf("-"));
		
        log.info("long value of Date is........."+longValueOfDate);
		
		Long valueOfDateInLong = Long.valueOf(longValueOfDate);
		
        String timeZoneofDate=koreUsageDate.substring(koreUsageDate.indexOf("-"),koreUsageDate.indexOf(")"));
		
		log.info("time zone is......."+timeZoneofDate);
		
		DateTimeZone timeZone = DateTimeZone.forID(timeZoneofDate);
		
		log.info("****************timeZone ID is" + timeZone);
		
		DateTime dateTime = new DateTime(valueOfDateInLong, timeZone);

		log.info("****************" + dateTime.toString());
		
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
        String finalUsageDateformat = df.format(dateTime.toDate());

		log.info("finalUsageDateformat :::::::" + finalUsageDateformat);

		return finalUsageDateformat;

	}
}
