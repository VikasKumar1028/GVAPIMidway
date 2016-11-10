package com.gv.midway.test.unit;

/**
 * Created by ryan.tracy on 11/1/2016.
 */
public class RetrieveDeviceUsageHistoryCarrierTests extends DeviceSessionTestSupport {

//    @Test
//    public void test_deviceSessionUsageVerizon() throws Exception {
//
//        RouteDefinition route = context.getRouteDefinition("deviceSessionUsage");
//        route.adviceWith(context, new AdviceWithRouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                weaveById("getDeviceSessionUsage")
//                        .replace()
//                        .process(new MockVerizonUsageInformationRequestProcessor());
//
//                interceptSendToEndpoint("direct:retrieveDeviceUsageHistoryCarrier")
//                        //.to("log:input")
//                        .to("mock:direct:retrieveDeviceUsageHistoryCarrier");
//
//                interceptSendToEndpoint(IEndPoints.URI_REST_VERIZON_ENDPOINT)
//                        .process(exchange -> {
//                            CarrierProvisioningDeviceResponse response = new CarrierProvisioningDeviceResponse();
//                            exchange.getIn().setBody(CommonUtil.toJsonString(response));
//                        })
//                        .to("log:input")
//                        .to("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT)
//                        .skipSendToOriginalEndpoint();
//            }
//
//        });
//        context.start();
//
//        template.request("direct:deviceSessionUsage", (Exchange exchange) -> setExchangeProperties(exchange, getSessionRequest()));
//
//        MockEndpoint mock = getMockEndpoint("mock:direct:retrieveDeviceUsageHistoryCarrier");
//
//        mock.expectedMessageCount(1);
//        mock.allMessages().body().isInstanceOf(UsageInformationRequest.class);
//        //testHeader(mock, "VERIZON"); //Cannot test for this here. It gets set later in the route.
//
//        mock.assertIsSatisfied();
//        mock.reset();
//
//        mock = getMockEndpoint("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT);
//
//        mock.expectedMessageCount(1);
//        //mock.allMessages().body().isInstanceOf(String.class);
//        testHeader(mock, "VERIZON");
//
//        mock.assertIsSatisfied();
//    }
}
