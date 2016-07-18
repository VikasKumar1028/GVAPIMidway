package com.gv.midway.processor.customFieldsDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponseDataArea;

public class StubKoreCustomFieldsProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(StubKoreCustomFieldsProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Begin::StubKoreCustomFieldsProcessor");

        CustomFieldsDeviceResponse updateCustomeFieldDeviceResponse = new CustomFieldsDeviceResponse();

        CustomFieldsDeviceResponseDataArea updateCustomeFieldDeviceResponseDataArea = new CustomFieldsDeviceResponseDataArea();
        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("UpdateCustomeFieldDevice");
        response.setResponseStatus("SUCCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("USA");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("KORE");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("KORE");

        updateCustomeFieldDeviceResponse.setHeader(responseheader);
        updateCustomeFieldDeviceResponse.setResponse(response);
        updateCustomeFieldDeviceResponseDataArea
                .setOrderNumber("StubKoreCustomFieldsProcessor");

        updateCustomeFieldDeviceResponse
                .setDataArea(updateCustomeFieldDeviceResponseDataArea);

        exchange.getIn().setBody(updateCustomeFieldDeviceResponse);
        LOGGER.info("End::StubKoreCustomFieldsProcessor");

    }

}
