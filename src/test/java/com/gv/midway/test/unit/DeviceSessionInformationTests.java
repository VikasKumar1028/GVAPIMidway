package com.gv.midway.test.unit;

import com.gv.midway.attjasper.GetSessionInfoRequest;
import com.gv.midway.attjasper.GetSessionInfoResponse;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionEvent;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionHistory;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;
import com.gv.midway.pojo.session.SessionRequest;
import com.gv.midway.test.mock.processor.MockConnectionInformationRequestProcessor;
import com.gv.midway.utility.CommonUtil;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.cxf.binding.soap.SoapFault;
import org.junit.Test;

import javax.xml.namespace.QName;

/**
 * Created by ryan.tracy on 10/27/2016.
 */
public class DeviceSessionInformationTests extends DeviceSessionTestSupport {

    @Test
    public void test_deviceSessionInformationVerizon() throws Exception {
        RouteDefinition route = context.getRouteDefinition("deviceSessionInfo");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveById("getDeviceSessionInfo")
                        .replace()
                        .process(new MockConnectionInformationRequestProcessor());

                interceptSendToEndpoint("direct:deviceSessionBeginEndInfo")
                        .to("mock:direct:deviceSessionBeginEndInfo");

                interceptSendToEndpoint(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                        .to("log:input")
                        .to("mock:preEndpointATTJasperEndpoint")
                        .process(exchange -> {
                            ConnectionEvent event = new ConnectionEvent();
                            event.setKey("BytesUsed");
                            event.setValue("0");

                            ConnectionHistory history = new ConnectionHistory();
                            history.setConnectionEventAttributes(new ConnectionEvent[]{event});
                            history.setExtendedAttributes("extended attributes");
                            history.setOccurredAt("occurred at");

                            ConnectionInformationResponse response = new ConnectionInformationResponse();
                            response.setConnectionHistory(new ConnectionHistory[]{history});
                            response.setHasMoreData(false);
                            exchange.getIn().setBody(CommonUtil.toJsonString(response));
                        })
                        .to("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT)
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:verizonDeviceSessionBeginEndInfoPostProcessor")
                        .to("mock:direct:verizonDeviceSessionBeginEndInfoPostProcessor");
            }

        });
        context.start();

        template.request("direct:deviceSessionInfo", (Exchange exchange) -> {
            exchange.getIn().setHeader("isValidRequest", true);
            setExchangeProperties(exchange, getSessionRequest());
        });

        MockEndpoint mock = getMockEndpoint("mock:direct:deviceSessionBeginEndInfo");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(ConnectionInformationRequest.class);
        //testHeader(mock, "VERIZON"); //Cannot test for this here. It gets set later in the route.

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:preEndpointATTJasperEndpoint");
        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(String.class);

        mock.assertIsSatisfied();
        mock.reset();


        mock = getMockEndpoint("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT);
        testHeader(mock, "VERIZON");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:direct:verizonDeviceSessionBeginEndInfoPostProcessor");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(ConnectionInformationResponse.class);

        mock.assertIsSatisfied();
    }

    @Test
    public void test_deviceSessionInformationATTJasper() throws Exception {
        RouteDefinition route = context.getRouteDefinition("deviceSessionInfo");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveById("getDeviceSessionInfo")
                        .replace()
                        .process(new MockConnectionInformationRequestProcessor());

                interceptSendToEndpoint("direct:deviceSessionBeginEndInfo")
                        .to("mock:direct:deviceSessionBeginEndInfo");

                interceptSendToEndpoint(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                        .to("mock:preEndpointATTJasperEndpoint")
                        .process(exchange -> {
                            if (exchange.getIn().getHeader("isValidRequest").equals(true)) {
                                GetSessionInfoResponse.SessionInfo sessionInfo = new GetSessionInfoResponse.SessionInfo();

                                GetSessionInfoResponse response = new GetSessionInfoResponse();
                                response.setVersion("version");
                                response.setTimestamp(CommonUtil.getCurrentXMLGregorianDateTimeUTC());
                                response.setCorrelationId("correlationId");
                                response.setBuild("build");
                                response.setSessionInfo(sessionInfo);
                                exchange.getIn().setBody(response);
                            } else {
                                new QName("");
                                throw new SoapFault("Terminal not found", new QName("http://schemas.xmlsoap.org/soap/envelope/", "Server"));
                            }
                        })
                        .to("mock:" + IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:attJasperDeviceSessionBeginEndInfoPostProcessor")
                        .to("log:input")
                        .to("mock:direct:attJasperDeviceSessionBeginEndInfoPostProcessor");
            }

        });
        context.start();

        SessionRequest sessionRequest = getSessionRequest();
        sessionRequest.getHeader().setBsCarrier("ATTJasper");
        sessionRequest.getDataArea().setNetSuiteId(187527); //Sandbox netSuiteId for ATTJasper

        template.request("direct:deviceSessionInfo", (Exchange exchange) -> {
            exchange.getIn().setHeader("isValidRequest", true);
            setExchangeProperties(exchange, sessionRequest);
        });

        MockEndpoint mock = getMockEndpoint("mock:direct:deviceSessionBeginEndInfo");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(ConnectionInformationRequest.class);
        //testHeader(mock, "ATTJASPER"); //Cannot test for this here. It gets set later in the route.

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:preEndpointATTJasperEndpoint");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(GetSessionInfoRequest.class);
        testHeader(mock, "ATTJASPER");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:" + IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT);

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(GetSessionInfoResponse.class);
        testHeader(mock, "ATTJASPER");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:direct:attJasperDeviceSessionBeginEndInfoPostProcessor");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(GetSessionInfoResponse.class);

        mock.assertIsSatisfied();
        mock.reset();

        template.request("direct:deviceSessionInfo", (Exchange exchange) -> {
            exchange.getIn().setHeader("isValidRequest", false);
            setExchangeProperties(exchange, sessionRequest);
        });

        //mock = getMockEndpoint("mock:output");

//        mock.expectedMessageCount(1);
//        mock.allMessages().body().isInstanceOf(GetSessionInfoResponse.class);
//        ValueBuilder builder = mock.allMessages().body(GetSessionInfoResponse.class);
//        builder = builder.method("getResponse");
//        builder = builder.method("getResponseCode");
//        boolean value = builder.equals(100100);
//        testHeader(mock, "ATTJASPER");
//
//        mock.assertIsSatisfied();
//        mock.reset();

    }
}
