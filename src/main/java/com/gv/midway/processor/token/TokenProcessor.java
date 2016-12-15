package com.gv.midway.processor.token;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.utility.CommonUtil;

public class TokenProcessor implements Processor {

    @EndpointInject(uri = "")
    ProducerTemplate producer;

    private static final Logger LOGGER = Logger.getLogger(TokenProcessor.class.getName());

    Environment newEnv;

    public TokenProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    public TokenProcessor() {
        // Empty Constructor
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("TokenProcessor");
        if (CommonUtil.isTokenRequired.get() && CommonUtil.isAlreadyInTokenGeneration.get()) {
            CommonUtil.setAlreadyInTokenGeneration(false);
            exchange.getContext().createProducerTemplate().sendBody("direct:tokenGeneration", exchange.getIn().getBody());
        } else {
            LOGGER.info(exchange.getExchangeId()+"   TokenProcessor exchange in waiting state......");
            Thread.sleep(4000);
        }

    }

}
