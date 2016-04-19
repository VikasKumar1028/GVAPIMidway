package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.callback.TargetResponse;

public class CallbackKafkaPostProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		
		System.out.println("Inside CallbackKafkaPostProcessor process " + exchange.getIn().getBody());
		System.out.println("Exchange inside" + exchange.getIn().getBody().toString());

		byte[] body = (byte[]) exchange.getIn().getBody();
		ObjectMapper mapper  = new ObjectMapper();
		TargetResponse targetResponse = mapper.readValue(body, TargetResponse.class);

		exchange.getIn().setBody(targetResponse);
	}

}
