package com.gv.midway.processor.jobScheduler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.attjasper.GetTerminalDetailsResponse;
import com.gv.midway.attjasper.GetTerminalDetailsResponse.Terminals;
import com.gv.midway.attjasper.TerminalType;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.verizon.DeviceId;

public class ATTJasperDeviceUsageHistoryPostProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeviceUsageHistoryPostProcessor.class.getName());

	Environment newEnv;

	public ATTJasperDeviceUsageHistoryPostProcessor() {
		// Empty Constructor
	}

	public ATTJasperDeviceUsageHistoryPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("Begin:ATTJasperDeviceUsageHistoryPostProcessor");

		GetTerminalDetailsResponse getTerminalDetailsResponse = (GetTerminalDetailsResponse) exchange
				.getIn().getBody(GetTerminalDetailsResponse.class);

		Terminals terminals = getTerminalDetailsResponse.getTerminals();
		List<TerminalType> terminalType = terminals.getTerminal();
		DeviceUsage deviceUsage = new DeviceUsage();
		long lastMTDvalue = 0;
		long updateLastMTDValue = 0;
		BigDecimal monthtoDateUsagevalue = terminalType.get(0)
				.getMonthToDateUsage();

		LOGGER.info("monthtoDateUsagevalue:::::first" + monthtoDateUsagevalue);	
	
		// convert from MB to bytes usages
		long monthtoDateUsageBytes = (long) (1048576 * monthtoDateUsagevalue
				.doubleValue()); // 444
			
		LOGGER.info("after convert to Bytes" + monthtoDateUsageBytes);

		Map<Integer, Long> map = null;

		map = (Map<Integer, Long>) exchange
				.getProperty("saveLastUpadtedDataUsed");
		if (map != null
				&& map.get((Integer) exchange
						.getProperty(IConstant.MIDWAY_NETSUITE_ID)) != null) {

			// Setting the Values as Map does contains data usage value for
			// previous dates

			lastMTDvalue = map.get((Integer) exchange
					.getProperty(IConstant.MIDWAY_NETSUITE_ID));

			LOGGER.info("valueofLastMTD::::::::" + lastMTDvalue);

			updateLastMTDValue = monthtoDateUsageBytes - lastMTDvalue;
			deviceUsage.setDataUsed(updateLastMTDValue);

			LOGGER.info("updateLastMTDValue::::::::" + updateLastMTDValue);

			LOGGER.info("----exchange_Body- Post Processor-===++++++++++++---------"
					+ getTerminalDetailsResponse.toString());

		} else {
			// Setting the Original values as Map does not contain any datausage
			// value for previous dates

			deviceUsage.setDataUsed(monthtoDateUsageBytes);
		}
		JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

		deviceUsage
				.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));

		// The Day for which Job Ran

		deviceUsage.setDate(jobDetail.getDate());
		deviceUsage.setTransactionErrorReason(null);
		deviceUsage
				.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		deviceUsage.setNetSuiteId((Integer) exchange
				.getProperty(IConstant.MIDWAY_NETSUITE_ID));
		deviceUsage.setIsValid(true);
		deviceUsage.setJobId(jobDetail.getJobId());
		deviceUsage.setMonthToDateUsage(monthtoDateUsageBytes);

		exchange.getIn().setBody(deviceUsage);
		LOGGER.info("End:ATTJasperDeviceUsageHistoryPostProcessor");

	}

}
