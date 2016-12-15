package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;


public class StubVerizonRestoreDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubVerizonRestoreDeviceProcessor.class
            .getName());

    // method for processing the message exchange for Verizon Stub
    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Begin:StubVerizonRestoreDeviceProcessor");
        CarrierProvisioningDeviceResponse restoreDeviceResponse = new CarrierProvisioningDeviceResponse();

        CarrierProvisioningDeviceResponseDataArea restoreDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();

        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is restored successfully");
        response.setResponseStatus("SUCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("VERIZON");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("VERIZON");

        restoreDeviceResponse.setHeader(responseheader);
        restoreDeviceResponse.setResponse(response);
        restoreDeviceResponseDataArea.setOrderNumber("VZ0123312313");
        restoreDeviceResponse.setDataArea(restoreDeviceResponseDataArea);

        exchange.getIn().setBody(restoreDeviceResponse);
        LOGGER.debug("End:StubVerizonRestoreDeviceProcessor");
    }
}