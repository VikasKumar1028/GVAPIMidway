package com.gv.midway.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;

public class CallbackNewPostProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		/*
		 * Converting bytes message which was sent to kafka, back to callback
		 * pojo
		 */
		ObjectMapper mapper = new ObjectMapper();
		byte[] body = (byte[]) exchange.getIn().getBody();

		CallBackVerizonRequest req = mapper.readValue(body, CallBackVerizonRequest.class);
		System.out.println("CallBackVerizonRequest req" + req.getComment());
		
		exchange.getIn().setBody(req);
	}

}
