package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.callback.TargetResponse;

public class CallbackPostProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		System.out.println("Inside CallbackPostProcessor process " + exchange.getIn().getBody());
		System.out.println("Exchange inside" + exchange.getIn().getBody().toString());
		TargetResponse req = (TargetResponse) exchange.getIn().getBody(TargetResponse.class);
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] bytes = null;
		try {
			bytes = objectMapper.writeValueAsString(req).getBytes();
		} catch (JsonProcessingException e) {
		}
		
		exchange.getIn().setBody(bytes);
	}

}
