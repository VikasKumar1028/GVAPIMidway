package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class VerizonDeviceInformationProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonDeviceInformationProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonDeviceInformationProcessor");
		
		 System.out.println("-----Main Thread--------"+  Thread.currentThread().getName());
			
		System.out.println("------IN VERIZON PROCESSOR--------"
				+ exchange.getIn().getBody());

		String json = "{\"accountName\":\"TestAccount-1\"}";

		exchange.getIn().setBody(json);
		Message message = exchange.getIn();
		//message.setHeader("VZ-M2M-Token","1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		message.setHeader("VZ-M2M-Token","1d1f8e7a");
		//message.setHeader("Authorization","Bearer 12");
		message.setHeader("Authorization","Bearer 89ba225e1438e95bd05c3cc288d3591");
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/list");
		
	}

}
