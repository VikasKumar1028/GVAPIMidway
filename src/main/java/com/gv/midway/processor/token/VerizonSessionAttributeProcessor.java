package com.gv.midway.processor.token;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.token.VerizonSessionLoginResponse;

public class VerizonSessionAttributeProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(VerizonSessionAttributeProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        VerizonSessionLoginResponse resp = (VerizonSessionLoginResponse) exchange.getIn().getBody();

        LOGGER.debug("*********************resp.getSessionToken()*********************" + resp.getSessionToken());
        exchange.setProperty(IConstant.VZ_SESSION_TOKEN, resp.getSessionToken());

    }
}
