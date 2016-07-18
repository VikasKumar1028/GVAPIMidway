package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponseDataArea;

public class VerizonChangeDeviceServicePlansPostProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(VerizonChangeDeviceServicePlansPostProcessor.class
                    .getName());

    Environment newEnv;

    public VerizonChangeDeviceServicePlansPostProcessor() {
        // Empty Constructor
    }

    public VerizonChangeDeviceServicePlansPostProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Begin::VerizonChangeDeviceServicePlansPostProcessor");

        ChangeDeviceServicePlansResponse changeDeviceServicePlansResponse = new ChangeDeviceServicePlansResponse();

        ChangeDeviceServicePlansResponseDataArea changeDeviceServicePlansResponseDataArea = new ChangeDeviceServicePlansResponseDataArea();

        Response response = new Response();

        if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

            LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
            response.setResponseCode(IResponse.SUCCESS_CODE);
            response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
            response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
            changeDeviceServicePlansResponseDataArea.setOrderNumber(exchange
                    .getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

        } else {

            response.setResponseCode(400);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            response.setResponseDescription(exchange.getIn().getBody()
                    .toString());

        }

        response.setResponseCode(IResponse.SUCCESS_CODE);
        response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
        response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

        Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

        changeDeviceServicePlansResponse.setHeader(responseheader);
        changeDeviceServicePlansResponse.setResponse(response);
        changeDeviceServicePlansResponseDataArea.setOrderNumber(exchange
                .getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

        changeDeviceServicePlansResponse
                .setDataArea(changeDeviceServicePlansResponseDataArea);

        exchange.getIn().setBody(changeDeviceServicePlansResponse);

        LOGGER.info("End::VerizonChangeDeviceServicePlansPostProcessor");

    }

}
