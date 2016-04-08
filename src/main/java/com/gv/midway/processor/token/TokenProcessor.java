package com.gv.midway.processor.token;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;

public class TokenProcessor implements Processor {

	@EndpointInject(uri = "")
	ProducerTemplate producer;
	
	
	Logger log = Logger.getLogger(TokenProcessor.class
			.getName());

	Environment newEnv;
	
	public TokenProcessor(Environment env) {
		super();
		this.newEnv = env;

	}
	
	public TokenProcessor() {

	}

	
	public void process(Exchange exchange) throws Exception {

	//	System.out.println("*****************************TOKEN************************************************" + exchange.getOut().getBody());
		exchange.getContext().createProducerTemplate().sendBody("direct:token",exchange.getIn().getBody());
		
	
	
		//producer.requestBody("direct:token");
		
	}

}
