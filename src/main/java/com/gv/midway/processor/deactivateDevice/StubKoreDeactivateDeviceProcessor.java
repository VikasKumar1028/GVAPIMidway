package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;


public class StubKoreDeactivateDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubKoreDeactivateDeviceProcessor.class
            .getName());

    public StubKoreDeactivateDeviceProcessor() {
        // Default Constructor
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Begin::StubKoreDeactivateDeviceProcessor");
        CarrierProvisioningDeviceResponse deactivateDeviceResponse = new CarrierProvisioningDeviceResponse();
        Header responseheader = new Header();
        CarrierProvisioningDeviceResponseDataArea deactivateDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is deactivate successfully");
        response.setResponseStatus("SUCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("KORE");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("KORE");

        deactivateDeviceResponse.setHeader(responseheader);
        deactivateDeviceResponse.setResponse(response);
        deactivateDeviceResponseDataArea.setOrderNumber("KR023335545");

        deactivateDeviceResponse.setDataArea(deactivateDeviceResponseDataArea);
        exchange.getIn().setBody(deactivateDeviceResponse);
        LOGGER.debug("End::StubKoreDeactivateDeviceProcessor");
    }

}
