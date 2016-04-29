package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class KoreChangeDeviceServicePlansPostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreChangeDeviceServicePlansPostProcessor.class
			.getName());


	Environment newEnv;

	public KoreChangeDeviceServicePlansPostProcessor() {

	}

	public KoreChangeDeviceServicePlansPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
