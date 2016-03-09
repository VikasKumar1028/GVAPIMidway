package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.DeviceInformationRequest;

public class HeaderProcessor implements Processor {

	Logger log = Logger.getLogger(HeaderProcessor.class.getName());


	public void process(Exchange exchange) throws Exception {

		log.info("Start:HeaderProcessor");
		DeviceInformationRequest deviceInformationRequest = exchange.getIn()
				.getBody(DeviceInformationRequest.class);

		System.out.println("SourceName ::"
				+ deviceInformationRequest.getHeader().getSourceName());

		exchange.setProperty("sourceName", deviceInformationRequest.getHeader()
				.getSourceName());

		System.out.println("exchange::" + exchange.getProperties());
		exchange.getIn().setHeader("sourceName",
				deviceInformationRequest.getHeader().getSourceName());

	}

}
