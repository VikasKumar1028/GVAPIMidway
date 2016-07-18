package com.gv.midway.processor.token;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;

public class VerizonAuthorizationTokenProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(VerizonAuthorizationTokenProcessor.class
            .getName());

    Environment newEnv;

    public VerizonAuthorizationTokenProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    public VerizonAuthorizationTokenProcessor() {
        // Empty Constructor
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:VerizonAuthorizationTokenProcessor----------"
                + exchange.getPattern());
        LOGGER.info("Authorization:::"
                + newEnv.getProperty(IConstant.VERIZON_AUTHENTICATION));
        Message message = exchange.getIn();
        message.setHeader("Authorization",
                newEnv.getProperty(IConstant.VERIZON_AUTHENTICATION));
        message.setHeader(Exchange.CONTENT_TYPE,
                "application/x-www-form-urlencoded");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader(Exchange.HTTP_PATH, "/ts/v1/oauth2/token");

        exchange.getIn().setHeader(Exchange.HTTP_QUERY,
                "grant_type=client_credentials");

        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("End:VerizonAuthorizationTokenProcessor");

    }

}
