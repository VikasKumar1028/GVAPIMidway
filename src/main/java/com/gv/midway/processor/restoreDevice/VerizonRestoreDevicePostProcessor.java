package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponseDataArea;

public class VerizonRestoreDevicePostProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(VerizonRestoreDevicePostProcessor.class
            .getName());

    Environment newEnv;

    // constructor with one argument
    public VerizonRestoreDevicePostProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
     */
    // method for processing the message exchange for Verizon
    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:VerizonRestoreDevicePostProcessor");

        RestoreDeviceResponse restoreDeviceResponse = new RestoreDeviceResponse();
        RestoreDeviceResponseDataArea restoreDeviceResponseDataArea = new RestoreDeviceResponseDataArea();
        Response response = new Response();

        LOGGER.info("exchange.getIn().getBody().toString()***************************************"
                + exchange.getIn().getBody().toString());

        if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

            LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
            response.setResponseCode(IResponse.SUCCESS_CODE);
            response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
            response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
            restoreDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
                    IConstant.MIDWAY_TRANSACTION_ID).toString());

        } else {

            response.setResponseCode(400);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            response.setResponseDescription(exchange.getIn().getBody()
                    .toString());
        }

        Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

        restoreDeviceResponse.setHeader(responseheader);
        restoreDeviceResponse.setResponse(response);

        restoreDeviceResponse.setDataArea(restoreDeviceResponseDataArea);

        exchange.getIn().setBody(restoreDeviceResponse);

        LOGGER.info("End:VerizonRestoreDevicePostProcessor");

    }

}
