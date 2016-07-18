package com.gv.midway.processor.suspendDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponseDataArea;

public class VerizonSuspendDevicePostProcessor implements Processor {

    static int i = 0;

    private static final Logger LOGGER = Logger.getLogger(VerizonSuspendDevicePostProcessor.class
            .getName());

    Environment newEnv;

    public VerizonSuspendDevicePostProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
     */
    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:VerizonSuspendDevicePostProcessor");

        SuspendDeviceResponse suspendDeviceResponse = new SuspendDeviceResponse();
        SuspendDeviceResponseDataArea suspendDeviceResponseDataArea = new SuspendDeviceResponseDataArea();
        Response response = new Response();

        LOGGER.info("exchange.getIn().getBody().toString()***************************************"
                + exchange.getIn().getBody().toString());

        if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

            LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
            response.setResponseCode(IResponse.SUCCESS_CODE);
            response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
            response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_SUSPEND_MIDWAY);
            suspendDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
                    IConstant.MIDWAY_TRANSACTION_ID).toString());

        } else {

            response.setResponseCode(400);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            response.setResponseDescription(exchange.getIn().getBody()
                    .toString());

        }

        Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

        suspendDeviceResponse.setHeader(responseheader);
        suspendDeviceResponse.setResponse(response);

        suspendDeviceResponse.setDataArea(suspendDeviceResponseDataArea);

        exchange.getIn().setBody(suspendDeviceResponse);

        LOGGER.info("End:VerizonSuspendDevicePostProcessor");

    }

}
