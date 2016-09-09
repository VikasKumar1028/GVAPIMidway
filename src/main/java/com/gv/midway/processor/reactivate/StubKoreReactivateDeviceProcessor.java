package com.gv.midway.processor.reactivate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;


public class StubKoreReactivateDeviceProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(KoreReactivateDevicePreProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:StubKoreReactivateDeviceProcessor");
        CarrierProvisioningDeviceResponse reactivateDeviceResponse = new CarrierProvisioningDeviceResponse();

        CarrierProvisioningDeviceResponseDataArea reactivateDeviceResponseDataArea = new CarrierProvisioningDeviceResponseDataArea();

        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device is reactivate successfully");
        response.setResponseStatus("SUCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("KORE");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("KORE");

        reactivateDeviceResponse.setHeader(responseheader);
        reactivateDeviceResponse.setResponse(response);
        reactivateDeviceResponseDataArea.setOrderNumber("KR0123312313");
        reactivateDeviceResponse.setDataArea(reactivateDeviceResponseDataArea);

        exchange.getIn().setBody(reactivateDeviceResponse);
        LOGGER.info("End:StubKoreReactivateDeviceProcessor");

    }

}
