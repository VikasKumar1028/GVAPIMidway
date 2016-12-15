package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.callback.TargetResponse;

public class CallbackKafkaPostProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(CallbackKafkaPostProcessor.class);

    /*
     * converting target resoponse which is in to bytes in TargetResponse object
     */
    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Inside CallbackKafkaPostProcessor process "
                + exchange.getIn().getBody());
        LOGGER.debug("Exchange inside ");

        byte[] body = (byte[]) exchange.getIn().getBody();
        ObjectMapper mapper = new ObjectMapper();
        TargetResponse targetResponse = mapper.readValue(body,
                TargetResponse.class);

        exchange.getIn().setBody(targetResponse);
    }

}
