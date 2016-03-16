package com.gv.midway.processor.token;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class VerizonAuthorizationTokenProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonAuthorizationTokenProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonAuthorizationTokenProcessor");
		exchange.getIn().setBody("grant_type=client_credentials");
		
		Message message = exchange.getIn();
		message.setHeader("Authorization","Basic Zl9EcHVyaXVpWHczckVhdktTV19yb1ByRmFFYTpCdFFqb2xzR0ZuUFlEYjRKQWw1czRZN2FrUklh");
		message.setHeader(Exchange.CONTENT_TYPE, "application/x-www-form-urlencoded");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/ts/v1/oauth2/token");

		
	}

}
