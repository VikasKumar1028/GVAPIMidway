package com.gv.midway.test.unit;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponseDataArea;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.verizon.response.VerizonUsageInformationResponse;
import com.gv.midway.test.mock.processor.MockVerizonUsageInformationRequestProcessor;
import com.gv.midway.utility.CommonUtil;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.junit.Test;

/**
 * Created by ryan.tracy on 10/27/2016.
 */
public class DeviceSessionUsageTests extends DeviceSessionTestSupport {

    @Test
    public void test_deviceSessionUsageVerizon() throws Exception {

        RouteDefinition route = context.getRouteDefinition("deviceSessionUsage");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveById("getDeviceSessionUsage")
                        .replace()
                        .process(new MockVerizonUsageInformationRequestProcessor());

                interceptSendToEndpoint("direct:retrieveDeviceUsageHistoryCarrier")
                        .to("log:input")
                        .to("mock:direct:retrieveDeviceUsageHistoryCarrier");

                interceptSendToEndpoint(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                        .to("log:input")
                        .process(exchange -> {
                            Header header = new Header();
//                            header.setRegion(exchange.getProperty(IConstant.REGION).toString());
//                            header.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER).toString());
//                            header.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME).toString());
//                            header.setOrganization(exchange.getProperty(IConstant.ORGANIZATION).toString());
//                            header.setApplicationName(exchange.getProperty(IConstant.APPLICATION_NAME).toString());
//                            header.setTransactionId(exchange.getProperty(IConstant.));
//                            header.setTimestamp("");
                            CarrierProvisioningDeviceResponseDataArea dataArea = new CarrierProvisioningDeviceResponseDataArea();
                            dataArea.setOrderNumber("12345678");
                            CarrierProvisioningDeviceResponse response = new CarrierProvisioningDeviceResponse();
                            response.setHeader(header);
                            response.setDataArea(dataArea);
                            exchange.getIn().setBody(CommonUtil.toJsonString(response));
                        })
                        .to("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT)
                        .to("log:input")
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:retrieveDeviceUsageHistoryPostProcessor")
                        .to("log:input")
                        .to("mock:direct:retrieveDeviceUsageHistoryPostProcessor");
            }

        });
        context.start();

        template.request("direct:deviceSessionUsage", (Exchange exchange) -> setExchangeProperties(exchange, getSessionRequest()));

        MockEndpoint mock = getMockEndpoint("mock:direct:retrieveDeviceUsageHistoryCarrier");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(UsageInformationRequest.class);
        //testHeader(mock, "VERIZON"); //Cannot test for this here. It gets set later in the route.

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT);

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(String.class);
        testHeader(mock, "VERIZON");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:direct:retrieveDeviceUsageHistoryPostProcessor");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(VerizonUsageInformationResponse.class);

        mock.assertIsSatisfied();
    }
}
