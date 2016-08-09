package com.gv.midway.processor;

import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
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

		LOGGER.info("endpoint is........................."
				+ exchange.getFromEndpoint().toString());

		List<DeviceInformation> timeOutList = (List<DeviceInformation>) exchange
				.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
		DeviceInformation deviceInformation = (DeviceInformation) exchange
				.getIn().getBody();
		LOGGER.info("NetSuite Id of timeOut Device is................"
				+ deviceInformation.getNetSuiteId());
		timeOutList.add(deviceInformation);

		exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutList);

	}

}
