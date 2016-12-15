package com.gv.midway.processor.token;

import com.gv.midway.utility.MessageStuffer;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;

public class VerizonAuthorizationTokenProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(VerizonAuthorizationTokenProcessor.class.getName());

    private Environment newEnv;

    public VerizonAuthorizationTokenProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Begin:VerizonAuthorizationTokenProcessor----------" + exchange.getPattern());
        LOGGER.debug("Authorization:::" + newEnv.getProperty(IConstant.VERIZON_AUTHENTICATION));

        final Message message = exchange.getIn();
        MessageStuffer.setVerizonPOSTRequest(message, newEnv, "/ts/v1/oauth2/token");

        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End:VerizonAuthorizationTokenProcessor");
    }
}