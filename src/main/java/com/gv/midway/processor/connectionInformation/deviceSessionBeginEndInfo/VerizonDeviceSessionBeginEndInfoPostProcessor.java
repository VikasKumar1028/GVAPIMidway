package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class VerizonDeviceSessionBeginEndInfoPostProcessor implements Processor {

	Logger log = Logger
			.getLogger(VerizonDeviceSessionBeginEndInfoPostProcessor.class
					.getName());

	Environment newEnv;

	public VerizonDeviceSessionBeginEndInfoPostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {
	
		System.out.println("IN POST PROCESSOR YO!!");
		
	}

}
