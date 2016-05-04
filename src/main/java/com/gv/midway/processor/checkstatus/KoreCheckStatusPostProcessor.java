package com.gv.midway.processor.checkstatus;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class KoreCheckStatusPostProcessor implements Processor {

	/**
	 * Write the message to Kafka Queue and CallBAck the netsuite endpoint.
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
