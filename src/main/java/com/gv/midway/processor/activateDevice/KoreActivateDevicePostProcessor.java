package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;

public class KoreActivateDevicePostProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(KoreActivateDevicePostProcessor.class
            .getName());

    public KoreActivateDevicePostProcessor() {
        // Empty Constructor

    }

    Environment newEnv;

    public KoreActivateDevicePostProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin::KoreActivateDevicePostProcessor");

        ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();

        ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);
        response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
        response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

        Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

        activateDeviceResponse.setHeader(responseheader);
        activateDeviceResponse.setResponse(response);
        activateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
                IConstant.MIDWAY_TRANSACTION_ID).toString());

        activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

        exchange.getIn().setBody(activateDeviceResponse);
        LOGGER.info("End::KoreActivateDevicePostProcessor");
    }
}
