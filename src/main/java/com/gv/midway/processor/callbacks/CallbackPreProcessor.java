package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;

public class CallbackPreProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		CallBackVerizonRequest req = (CallBackVerizonRequest) exchange.getIn().getBody(CallBackVerizonRequest.class);

		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("req", req);

		exchange.getIn().setBody(req);
		/*
		 * Message message = exchange.getIn();
		 * 
		 * message.setHeader("VZ-M2M-Token",
		 * "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		 * message.setHeader("Authorization",
		 * "Bearer 89ba225e1438e95bd05c3cc288d3591");
		 * message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		 * message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		 * message.setHeader(Exchange.HTTP_METHOD, "POST");
		 * message.setHeader(Exchange.HTTP_PATH, "/device/activate/callback");
		 */

	}

}
