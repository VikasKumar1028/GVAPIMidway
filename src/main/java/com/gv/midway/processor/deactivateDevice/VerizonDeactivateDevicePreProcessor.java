package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;


public class VerizonDeactivateDevicePreProcessor implements Processor {

	public VerizonDeactivateDevicePreProcessor() {

	}

	public void process(Exchange exchange) throws Exception {
		/*
		 * DeactivateDeviceRequest req = (DeactivateDeviceRequest) exchange
		 * .getIn().getBody();
		 */

		DeactivateDeviceRequest req = (DeactivateDeviceRequest) exchange
				.getIn().getBody(DeactivateDeviceRequest.class);

		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		 obj.put("req", req);

		exchange.getIn().setBody(req);
		Message message = exchange.getIn();

		message.setHeader("VZ-M2M-Token",
				"1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		message.setHeader("Authorization",
				"Bearer 89ba225e1438e95bd05c3cc288d3591");
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/deactivate");

	}
}
