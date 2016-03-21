package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;

public class HeaderProcessor implements Processor {

	Logger log = Logger.getLogger(HeaderProcessor.class.getName());


	public void process(Exchange exchange) throws Exception {

		log.info("Start:HeaderProcessor");
		DeviceInformationRequest deviceInformationRequest = exchange.getIn()
				.getBody(DeviceInformationRequest.class);

		exchange.getIn().setHeader(IConstant.SOURCE_NAME,
				deviceInformationRequest.getHeader().getSourceName());
		
		exchange.setProperty(IConstant.BSCARRIER,
				deviceInformationRequest.getHeader().getBsCarrier());
		exchange.setProperty(IConstant.SOURCE_NAME, deviceInformationRequest.getHeader()
				.getSourceName());

		
		
		
	}

}
