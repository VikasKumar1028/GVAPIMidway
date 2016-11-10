package com.gv.midway.processor.token;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.token.VerizonAuthorizationResponse;
import org.springframework.core.env.Environment;

public class VerizonSessionTokenProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(VerizonSessionTokenProcessor.class.getName());

	Environment newEnv;

	public VerizonSessionTokenProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:VerizonSessionTokenProcessor");

		VerizonAuthorizationResponse authResponse = (VerizonAuthorizationResponse) exchange
				.getIn().getBody();

		String username = newEnv.getProperty(IConstant.VERIZON_API_USERNAME);
		String password = newEnv.getProperty(IConstant.VERIZON_API_PASSWORD);

		String json = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

		exchange.getIn().setBody(json);
		Message message = exchange.getIn();

		// Dynamic Values
		exchange.setProperty(IConstant.VZ_AUTHORIZATION_TOKEN, authResponse.getAccess_token());
		message.setHeader("Authorization", "Bearer " + authResponse.getAccess_token());

		LOGGER.info("-----------------authResponse.getAccess_token()---------------"
				+ authResponse.getAccess_token());

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/m2m/v1/session/login");

		LOGGER.info("End:VerizonSessionTokenProcessor");

	}

}
