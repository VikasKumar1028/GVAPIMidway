package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class StubATTJasperDeactivateDeviceProcessor implements Processor {
	private static final Logger LOGGER = Logger
			.getLogger(StubATTJasperDeactivateDeviceProcessor.class.getName());

	Environment newEnv;

	public StubATTJasperDeactivateDeviceProcessor() {
		// Empty ConstructorATT_JasperDeviceInformationPostProcessor
	}

	public StubATTJasperDeactivateDeviceProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Begin:StubATTJasperDeactivateDeviceProcessor");

		LOGGER.info("End:StubATTJasperDeactivateDeviceProcessor");
	}

}
