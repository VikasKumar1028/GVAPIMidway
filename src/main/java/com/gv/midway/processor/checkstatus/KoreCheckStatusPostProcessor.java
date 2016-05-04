package com.gv.midway.processor.checkstatus;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class KoreCheckStatusPostProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */
	
	Logger log = Logger.getLogger(KoreCheckStatusPostProcessor.class
			.getName());
	
	private Environment newEnv;
	
	public KoreCheckStatusPostProcessor() {

	}

	
	public KoreCheckStatusPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("kore check status post processor");
	}

}
