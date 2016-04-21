package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.callback.TargetResponse;

public class CallbackKafkaPostProcessor implements Processor {

	Logger log = Logger.getLogger(CallbackKafkaPostProcessor.class);

	/*
	 * converting target resoponse which is in to bytes in TargetResponse object
	 */
	public void process(Exchange exchange) throws Exception {

		log.info("Inside CallbackKafkaPostProcessor process " + exchange.getIn().getBody());
		log.info("Exchange inside ");

		byte[] body = (byte[]) exchange.getIn().getBody();
		ObjectMapper mapper = new ObjectMapper();
		TargetResponse targetResponse = mapper.readValue(body, TargetResponse.class);

		exchange.getIn().setBody(targetResponse);
	}

}
