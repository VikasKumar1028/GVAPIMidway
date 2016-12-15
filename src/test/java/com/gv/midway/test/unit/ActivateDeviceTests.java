package com.gv.midway.test.unit;

import com.gv.midway.attjasper.EditTerminalResponse;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.pojo.activateDevice.kore.request.ActivateDeviceRequestKore;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.kore.KoreProvisioningResponse;
import com.gv.midway.pojo.KeyValuePair;
import com.gv.midway.utility.CommonUtil;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.junit.Test;

/**
 * Created by ryan.tracy on 10/25/2016.
 */
public class ActivateDeviceTests extends MidwayTestSupport {

    @Test
    public void test_activateDeviceKore() throws Exception {

        RouteDefinition activateDeviceRoute = context.getRouteDefinition("activateDevice");
        activateDeviceRoute.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("direct:processActivateKoreTransaction").process(exchange -> {

                });
            }

        });

        RouteDefinition activateDeviceKore = context.getRouteDefinition("processActivateKoreTransaction");
        activateDeviceKore.adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {
                weaveById("populateActivateDBPayloadKore").replace().bean(iTransactionalService, "populateActivateDBPayloadMock");
            }

        });

        RouteDefinition koreSedaActivationKore = context.getRouteDefinition("koreSedaActivationKore");
        koreSedaActivationKore.adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {
                weaveById("auditExternalRequestCallKoreActivate").replace().bean(iAuditService, "auditExternalRequestCallMock");

                interceptSendToEndpoint(IEndPoints.URI_REST_KORE_ENDPOINT)
                        .process(exchange -> {
                            //init
                            ActivateDeviceRequestKore request = exchange.getIn().getBody(ActivateDeviceRequestKore.class);

                            //Tests
                            assertIsInstanceOf(ActivateDeviceRequestKore.class, exchange.getIn().getBody());
                            assertEquals("89014103277405945812", request.getDeviceNumber());
                            assertEquals("KoreServicePlan01", request.getEAPCode());

                            //Prep next leg of route
                            KoreProvisioningResponse response = new KoreProvisioningResponse();
                            exchange.getIn().setBody(CommonUtil.toJsonString(response));
                        })
                        .to("mock:" + IEndPoints.URI_REST_KORE_ENDPOINT)
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:postActivateDeviceKoreRest")
                        .to("log:input")
                        .to("mock:direct:postActivateDeviceKoreRest")
                        .skipSendToOriginalEndpoint();
            }

        });

        context.start();

        template.request("direct:activateDevice", (Exchange exchange) -> {
            exchange.setProperty("hasServicePlan", true);
            setExchangeProperties(exchange, getActivateDeviceRequest());
        });

        MockEndpoint mock = getMockEndpoint("mock:" + IEndPoints.URI_REST_KORE_ENDPOINT);

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(String.class);
        testHeader(mock, "KORE");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:direct:postActivateDeviceKoreRest");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(KoreProvisioningResponse.class);
        testHeader(mock, "KORE");

        mock.assertIsSatisfied();
    }

    @Test
    public void test_activateDeviceATTJasper() throws Exception {

        RouteDefinition route = context.getRouteDefinition("activateDevice");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("direct:processActivateATTJasperTransaction")
                        .process(exchange -> {
                            EditTerminalResponse response = new EditTerminalResponse();
                            response.setIccid("89011702272013902587");
                            exchange.getIn().setBody(response);
                        })
                        .to("mock:direct:processActivateATTJasperTransaction")
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:activationWithCustomFieldFlow")
                        .to("log:input")
                        .to("mock:direct:activationWithCustomFieldFlow")
                        .skipSendToOriginalEndpoint();
//
//                interceptSendToEndpoint("direct:activationWithServicePlanFlow")
//                        .to("log:input")
//                        .to("mock:direct:activationWithServicePlanFlow")
//                        .skipSendToOriginalEndpoint();
            }

        });
        context.start();

        ActivateDeviceId activateDeviceId = new ActivateDeviceId("89011702272013902587","ICCID");

        ActivateDevices activateDevices = new ActivateDevices();
        activateDevices.setDeviceIds(new ActivateDeviceId[]{activateDeviceId});

        ActivateDeviceRequest activateDeviceRequest = getActivateDeviceRequest();
        activateDeviceRequest.getHeader().setBsCarrier("ATTJasper");
        activateDeviceRequest.getDataArea().setDevices(activateDevices);

        template.request("direct:activateDevice", (Exchange exchange) -> setExchangeProperties(exchange, activateDeviceRequest));

        MockEndpoint mock = getMockEndpoint("mock:direct:processActivateATTJasperTransaction");

        mock.expectedMessageCount(1);
        testHeader(mock, "ATTJASPER");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:direct:activationWithCustomFieldFlow");

        mock.expectedMessageCount(1);
//        mock.allMessages().body().isInstanceOf(EditTerminalResponse.class);
        testHeader(mock, "ATTJASPER");
//
//        mock.assertIsSatisfied();
//        mock.reset();
//
//        mock = getMockEndpoint("mock:direct:activationWithServicePlanFlow");
//
//        mock.expectedMessageCount(1);
//        mock.allMessages().body().isInstanceOf(EditTerminalResponse.class);
//        testHeader(mock, "ATTJASPER");
//
//        mock.assertIsSatisfied();
    }

    public ActivateDeviceRequest getActivateDeviceRequest() {
        ActivateDeviceRequest activateDeviceRequest = new ActivateDeviceRequest();
        activateDeviceRequest.setHeader(getHeader());
        ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();
        dataArea.setCarrierName("Kore");
        dataArea.setAccountName("");
        ActivateDevices activateDevices = new ActivateDevices();
        activateDevices.setNetSuiteId(123456);
        activateDevices.setServicePlan("KoreServicePlan01");
        activateDevices.setMacAddress("");
        activateDevices.setSerialNumber("");
        ActivateDeviceId activateDeviceId = new ActivateDeviceId("89014103277405945812", "SIM");
        activateDevices.setDeviceIds(new ActivateDeviceId[]{activateDeviceId});
        KeyValuePair customField = new KeyValuePair();
        customField.setKey("cust1");
        customField.setValue("");
        activateDevices.setCustomFields(new KeyValuePair[]{customField});
        dataArea.setDevices(activateDevices);
        activateDeviceRequest.setDataArea(dataArea);
        return activateDeviceRequest;
    }
}
