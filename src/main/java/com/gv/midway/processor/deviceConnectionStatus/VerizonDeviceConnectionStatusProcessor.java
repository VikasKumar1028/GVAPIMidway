package com.gv.midway.processor.deviceConnectionStatus;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class VerizonDeviceConnectionStatusProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonDeviceConnectionStatusProcessor.class
			.getName());

	Environment newEnv;

	public VerizonDeviceConnectionStatusProcessor(Environment env) {
		super();
		this.newEnv = env;

	}
	public void process(Exchange exchange) throws Exception {
		System.out.println("End of Device In Session or not: "
				+ exchange.getOut());

	}

}
