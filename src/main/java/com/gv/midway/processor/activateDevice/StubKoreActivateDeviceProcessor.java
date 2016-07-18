package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;

public class StubKoreActivateDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubKoreActivateDeviceProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:StubKoreDeviceActivateProcessor");

        ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();

        ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();
        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is activated successfully");
        response.setResponseStatus("SUCCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("KORE");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("KORE");

        activateDeviceResponse.setHeader(responseheader);
        activateDeviceResponse.setResponse(response);
        activateDeviceResponseDataArea.setOrderNumber("KR0123312313");

        activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

        exchange.getIn().setBody(activateDeviceResponse);

        LOGGER.info("End:StubKoreDeviceActivateProcessor");
    }
}
