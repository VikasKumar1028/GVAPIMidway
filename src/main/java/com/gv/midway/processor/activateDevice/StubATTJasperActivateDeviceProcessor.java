package com.gv.midway.processor.activateDevice;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class StubATTJasperActivateDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubATTJasperActivateDeviceProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:StubATTJasperActivateDeviceProcessor");

        ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();

        ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();
        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is activated successfully");
        response.setResponseStatus("SUCCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-05-09T11:47:15");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("ATTJasper");
        responseheader.setTransactionId("b4e2231krdd");
        responseheader.setBsCarrier("ATTJASPER");

        activateDeviceResponse.setHeader(responseheader);
        activateDeviceResponse.setResponse(response);
        activateDeviceResponseDataArea.setOrderNumber("AT44436712");

        activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

        exchange.getIn().setBody(activateDeviceResponse);

        LOGGER.info("End:StubATTJasperActivateDeviceProcessor");
    }
}
