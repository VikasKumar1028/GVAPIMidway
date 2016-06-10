package com.gv.midway.processor.jobScheduler;


import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;


public class KoreDeviceUsageHistoryPostProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Map map = exchange.getIn().getBody(Map.class);
		ObjectMapper mapper = new ObjectMapper();
		/*UsageInformationResponse usageResponse = mapper.convertValue(map,
				UsageInformationResponse.class);
		DeviceUsage deviceUsage = new DeviceUsage();

		long totalBytesUsed = 0L;
		if (usageResponse.getUsageHistory() != null) {
			for (UsageHistory history : usageResponse.getUsageHistory()) {
				totalBytesUsed = history.getBytesUsed() + totalBytesUsed;
			}
		}
		deviceUsage
				.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
		deviceUsage.setDataUsed(totalBytesUsed);
		deviceUsage.setTimestamp(CommonUtil.getCurrentTimeStamp());
		deviceUsage.setTransactionErrorReason(null);
		deviceUsage
				.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		deviceUsage.setNetSuiteId((String) exchange.getProperty("NetSuiteId"));
		deviceUsage.setIsValid(true);
		deviceUsage.setBillCycleComplete(false);


		exchange.getIn().setBody(deviceUsage);*/
		
	}

}
