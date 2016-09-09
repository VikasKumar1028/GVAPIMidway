package com.gv.midway.processor.suspendDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;


public class StubVerizonSuspendDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubVerizonSuspendDeviceProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin::StubVerizonSuspendDeviceProcessor");
        CarrierProvisioningDeviceResponse suspendDeviceResponse = new CarrierProvisioningDeviceResponse();

        CarrierProvisioningDeviceResponseDataArea suspendDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();

        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is suspended successfully");
        response.setResponseStatus("SUCCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("VERIZON");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("VERIZON");

        suspendDeviceResponse.setHeader(responseheader);
        suspendDeviceResponse.setResponse(response);
        suspendDeviceResponseDataArea.setOrderNumber("KR012313512");
        suspendDeviceResponse.setDataArea(suspendDeviceResponseDataArea);

        exchange.getIn().setBody(suspendDeviceResponse);
        LOGGER.info("End::StubVerizonSuspendDeviceProcessor");
    }
}