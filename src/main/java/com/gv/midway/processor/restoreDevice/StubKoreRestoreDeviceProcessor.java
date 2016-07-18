package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponseDataArea;

public class StubKoreRestoreDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubKoreRestoreDeviceProcessor.class
            .getName());

    // method for processing the message exchange for KORE Stub
    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:StubKoreRestoreDeviceProcessor");

        RestoreDeviceResponse restoreDeviceResponse = new RestoreDeviceResponse();

        RestoreDeviceResponseDataArea restoreDeviceResponseDataArea = new RestoreDeviceResponseDataArea();
        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is restored successfully");
        response.setResponseStatus("SUCCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("KORE");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("KORE");

        restoreDeviceResponse.setHeader(responseheader);
        restoreDeviceResponse.setResponse(response);
        restoreDeviceResponseDataArea.setOrderNumber("KR0123312313");

        restoreDeviceResponse.setDataArea(restoreDeviceResponseDataArea);

        exchange.getIn().setBody(restoreDeviceResponse);

        LOGGER.info("End:StubKoreRestoreDeviceProcessor");
    }
}
