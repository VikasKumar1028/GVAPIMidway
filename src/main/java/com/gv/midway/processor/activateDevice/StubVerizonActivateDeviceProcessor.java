package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;


public class StubVerizonActivateDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubVerizonActivateDeviceProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin::StubVerizonActivateDeviceProcessor");
        CarrierProvisioningDeviceResponse activateDeviceResponse = new CarrierProvisioningDeviceResponse();

        CarrierProvisioningDeviceResponseDataArea activateDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();

        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is activated successfully");
        response.setResponseStatus("SUCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("VERIZON");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("VERIZON");

        activateDeviceResponse.setHeader(responseheader);
        activateDeviceResponse.setResponse(response);
        activateDeviceResponseDataArea.setOrderNumber("KR012313512");
        activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

        exchange.getIn().setBody(activateDeviceResponse);
        LOGGER.info("End::StubVerizonActivateDeviceProcessor");
    }
}