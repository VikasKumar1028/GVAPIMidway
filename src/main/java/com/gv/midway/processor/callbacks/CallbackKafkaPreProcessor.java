package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.utility.CommonUtil;

public class CallbackKafkaPreProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		/*
		 * Converting bytes message which was sent to kafka, back to callback
		 * pojo
		 */
		ObjectMapper mapper = new ObjectMapper();
		byte[] body = (byte[]) exchange.getIn().getBody();

		CallBackVerizonRequest req = mapper.readValue(body, CallBackVerizonRequest.class);
		
		exchange.getIn().setBody(req);
	}

}
