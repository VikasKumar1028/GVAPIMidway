package com.gv.midway.processor.deviceConnectionStatus;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;

public class VerizonDeviceConnectionStatusProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonDeviceConnectionStatusProcessor.class
			.getName());

	Environment newEnv;

	public VerizonDeviceConnectionStatusProcessor(Environment env) {
		super();
		this.newEnv = env;

	}
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("In Post Processor");
		 Map map = exchange.getIn().getBody(Map.class);
		 ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		 ConnectionInformationResponse businessResponse = mapper.convertValue(map, ConnectionInformationResponse.class);
		
		System.out.println("businessResponse>>>> " + businessResponse.getHasMoreData());
		
		

	}

}
