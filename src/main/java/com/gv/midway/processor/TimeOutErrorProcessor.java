package com.gv.midway.processor;

import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class TimeOutErrorProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(TimeOutErrorProcessor.class.getName());

	Environment newEnv;

	public TimeOutErrorProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public TimeOutErrorProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("TimeOut exception occured................"
				+ exchange.getIn().getBody().toString());

		switch (exchange.getFromEndpoint().toString()) {

		case "Endpoint[direct:startJob]":

			List<DeviceInformation> timeOutList = (List<DeviceInformation>) exchange
					.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
			DeviceInformation deviceInformation = (DeviceInformation) exchange
					.getIn().getBody();
			LOGGER.info("NetSuite Id of timeOut Device is................"
					+ deviceInformation.getNetSuiteId());
			timeOutList.add(deviceInformation);

			exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutList);

			break;
		case "Endpoint[direct:startTransactionFailureJob]":

			String jobName = (String) exchange.getProperty("jobName");

			// check for usage and connection History Job in TransactionFailure

			if (jobName.endsWith("DeviceUsageJob")) {
				LOGGER.info("timeOut devcie for Usage TransactionFailure Job  :::::::::::::::::: ");

				List<DeviceUsage> timeOutListTransactionFailure = (List<DeviceUsage>) exchange
						.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
				DeviceUsage deviceUsage = (DeviceUsage) exchange.getIn()
						.getBody();
				LOGGER.info("NetSuite Id of timeOut Device is................"
						+ deviceUsage.getNetSuiteId());
				timeOutListTransactionFailure.add(deviceUsage);

				exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST,
						timeOutListTransactionFailure);

			}

			// Connection History Job
			else {
				LOGGER.info("timeOut devcie for Connection TransactionFailure Job   :::::::::::::::::: ");

				List<DeviceConnection> timeOutListTransactionFailure = (List<DeviceConnection>) exchange
						.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
				DeviceConnection deviceConnection = (DeviceConnection) exchange
						.getIn().getBody();
				LOGGER.info("NetSuite Id of timeOut Device is................"
						+ deviceConnection.getNetSuiteId());
				timeOutListTransactionFailure.add(deviceConnection);

				exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST,
						timeOutListTransactionFailure);

			}

			break;

		default:
			break;
		}

	}

}
