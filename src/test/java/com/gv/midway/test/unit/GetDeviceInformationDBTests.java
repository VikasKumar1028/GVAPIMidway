package com.gv.midway.test.unit;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.junit.Test;

/**
 * Created by ryan.tracy on 10/20/2016.
 */
public class GetDeviceInformationDBTests extends DeviceInformationTestSupport {

    @Test
    public void test_getDeviceInformationDB() throws Exception {
        RouteDefinition route = context.getRouteDefinition("getDeviceInformationDB");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("direct:preDeviceService")
                        .to("log:input")
                        .to("mock:direct:preDeviceService")
                        .skipSendToOriginalEndpoint();
            }
        });
        context.start();

        //Test Kore ****************************************************************************************************
        template.request("direct:getDeviceInformationDB", (Exchange exchange) -> setExchangeProperties(exchange, getDeviceInformationRequest()));

        MockEndpoint mock = getMockEndpoint("mock:direct:preDeviceService");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(DeviceInformationRequest.class);
        testHeader(mock, "KORE");

        mock.assertIsSatisfied();
        mock.reset();

        DeviceInformationRequest request = getDeviceInformationRequest();

        //Test Verizon *************************************************************************************************
        request.getHeader().setBsCarrier("Verizon");

        template.request("direct:getDeviceInformationDB", (Exchange exchange) -> setExchangeProperties(exchange, request));

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(DeviceInformationRequest.class);
        testHeader(mock, "VERIZON");

        mock.assertIsSatisfied();
        mock.reset();

        //Test AttJasper ***********************************************************************************************
        request.getHeader().setBsCarrier("AttJasper");

        template.request("direct:getDeviceInformationDB", (Exchange exchange) -> setExchangeProperties(exchange, request));

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(DeviceInformationRequest.class);
        testHeader(mock, "ATTJASPER");

        mock.assertIsSatisfied();
    }
}
