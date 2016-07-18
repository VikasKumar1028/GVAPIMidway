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

public class KoreChangeDeviceServicePlansPostProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(KoreChangeDeviceServicePlansPostProcessor.class
                    .getName());

    Environment newEnv;

    public KoreChangeDeviceServicePlansPostProcessor() {
        // Empty Constructor
    }

    public KoreChangeDeviceServicePlansPostProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("KoreChangeDeviceServicePlansPostProcessor");

        ChangeDeviceServicePlansResponse changeDeviceServicePlansResponse = new ChangeDeviceServicePlansResponse();

        ChangeDeviceServicePlansResponseDataArea changeDeviceServicePlansResponseDataArea = new ChangeDeviceServicePlansResponseDataArea();

        // Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);
        response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
        response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

        /*
         * responseheader.setApplicationName(exchange.getProperty(IConstant.
         * APPLICATION_NAME).toString());
         * responseheader.setRegion(exchange.getProperty
         * (IConstant.REGION).toString());
         * 
         * responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT
         * ).toString());
         * responseheader.setOrganization(exchange.getProperty(IConstant
         * .ORGANIZATION).toString());
         * 
         * responseheader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME
         * ).toString());
         * responseheader.setBsCarrier(exchange.getProperty(IConstant
         * .BSCARRIER).toString());
         * responseheader.setTransactionId(exchange.getProperty
         * (IConstant.GV_TRANSACTION_ID).toString());
         */

        Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

        changeDeviceServicePlansResponse.setHeader(responseheader);
        changeDeviceServicePlansResponse.setResponse(response);
        changeDeviceServicePlansResponseDataArea.setOrderNumber(exchange
                .getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

        changeDeviceServicePlansResponse
                .setDataArea(changeDeviceServicePlansResponseDataArea);

        exchange.getIn().setBody(changeDeviceServicePlansResponse);

    }

}
