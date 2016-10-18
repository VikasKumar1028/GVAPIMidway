package com.gv.midway.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

@SuppressWarnings("unchecked")
public class TimeOutErrorProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(TimeOutErrorProcessor.class.getName());

	public TimeOutErrorProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:TimeOutErrorProcessor");
		LOGGER.info("TimeOut exception occurred................" + exchange.getIn().getBody().toString());

		switch (exchange.getFromEndpoint().toString()) {
			case "Endpoint[direct://startJob]":

				final List<DeviceInformation> timeOutList = (List<DeviceInformation>) exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
				final DeviceInformation deviceInformation = (DeviceInformation) exchange.getIn().getBody();

				LOGGER.info("NetSuite Id of timeOut Device is................" + deviceInformation.getNetSuiteId());

				timeOutList.add(deviceInformation);
				exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutList);
				break;
			case "Endpoint[direct://startTransactionFailureJob]":

				final String jobName = (String) exchange.getProperty("jobName");
				// check for usage and connection History Job in TransactionFailure
				if (jobName.endsWith("DeviceUsageJob")) {
					LOGGER.info("timeOut device for Usage TransactionFailure Job  :::::::::::::::::: ");
					final List<DeviceUsage> timeOutListTransactionFailure = (List<DeviceUsage>) exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
					final DeviceUsage deviceUsage = (DeviceUsage) exchange.getIn().getBody();
					LOGGER.info("NetSuite Id of timeOut Device is................" + deviceUsage.getNetSuiteId());
					timeOutListTransactionFailure.add(deviceUsage);
					exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutListTransactionFailure);
				}
				// Connection History Job
				else {
					LOGGER.info("timeOut device for Connection TransactionFailure Job   :::::::::::::::::: ");

					final List<DeviceConnection> timeOutListTransactionFailure = (List<DeviceConnection>) exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
					final DeviceConnection deviceConnection = (DeviceConnection) exchange.getIn().getBody();
					LOGGER.info("NetSuite Id of timeOut Device is................" + deviceConnection.getNetSuiteId());
					timeOutListTransactionFailure.add(deviceConnection);
					exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutListTransactionFailure);
				}
				break;
			default:
				break;
		}
		LOGGER.info("End:TimeOutErrorProcessor");
	}
}