package com.gv.midway.processor.jobScheduler;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageHistory;
import com.gv.midway.pojo.usageInformation.verizon.response.VerizonUsageInformationResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class VerizonDeviceUsageHistoryPostProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(VerizonDeviceUsageHistoryPostProcessor.class
					.getName());
    @Override
    public void process(Exchange exchange) throws Exception {

    	LOGGER.info("Begin:VerizonDeviceUsageHistoryPostProcessor");
        Map map = exchange.getIn().getBody(Map.class);
        ObjectMapper mapper = new ObjectMapper();
        VerizonUsageInformationResponse usageResponse = mapper.convertValue(
                map, VerizonUsageInformationResponse.class);
        DeviceUsage deviceUsage = new DeviceUsage();
        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

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
        // The Day for which Job Ran

        deviceUsage.setDate(jobDetail.getDate());
        deviceUsage.setTransactionErrorReason(null);
        deviceUsage
                .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
        deviceUsage.setNetSuiteId((Integer) exchange
                .getProperty(IConstant.MIDWAY_NETSUITE_ID));
        deviceUsage.setIsValid(true);
        deviceUsage.setJobId(jobDetail.getJobId());

        exchange.getIn().setBody(deviceUsage);

    	LOGGER.info("End:VerizonDeviceUsageHistoryPostProcessor");
    }

}
