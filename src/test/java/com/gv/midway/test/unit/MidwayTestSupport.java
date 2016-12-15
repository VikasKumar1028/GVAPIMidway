package com.gv.midway.test.unit;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.service.IAuditService;
import com.gv.midway.service.IDeviceService;
import com.gv.midway.service.ISessionService;
import com.gv.midway.service.ITransactionalService;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletContext;

/**
 * Created by ryan.tracy on 9/26/2016.
 */

public class MidwayTestSupport extends CamelSpringTestSupport {

    protected IAuditService iAuditService;

    protected ITransactionalService iTransactionalService;

    protected ISessionService iSessionService;

    protected IDeviceService iDeviceService;

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-config.xml");
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        MockServletContext sc = new MockServletContext("camel-config.xml");
        sc.setAttribute(IConstant.VZ_SESSION_TOKEN, "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
        iTransactionalService = (ITransactionalService) applicationContext.getBean("iTransactionalService");
        iAuditService = (IAuditService) applicationContext.getBean("iAuditService");
        iDeviceService = (IDeviceService) applicationContext.getBean("iDeviceService");
        iSessionService = (ISessionService) applicationContext.getBean("iSessionService");
        iSessionService.setServletContext(sc);
    }

//    @Test
//    public void testVerizonTokenGeneration() throws Exception {
//        RouteDefinition route = context.getRouteDefinition("tokenGeneration");
//        route.adviceWith(context, new AdviceWithRouteBuilder() {
//
//            @Override
//            public void configure() throws Exception {
//                this.interceptSendToEndpoint(IEndPoints.URI_REST_VERIZON_TOKEN_ENDPOINT)
//                        .skipSendToOriginalEndpoint()
//                        .process(exchange -> {
//                            throw new ConnectException();
////                            throw new HTTPException(
////                                    Status.UNAUTHORIZED.getStatusCode(),
////                                    Status.UNAUTHORIZED.getReasonPhrase(),
////                                    new URL(IEndPoints.URI_REST_VERIZON_TOKEN_ENDPOINT));
//                        });
//                mockEndpoints(IEndPoints.URI_REST_VERIZON_TOKEN_ENDPOINT);
//            }
//
//        });
//        context.start();
//
//        MockEndpoint mock = getMockEndpoint("mock:" + IEndPoints.URI_REST_VERIZON_TOKEN_ENDPOINT);
//        mock.expectedBodyReceived();
//
//
//        mock.assertIsSatisfied();
//    }

//    @Test
//    public void testMockEndPoints() throws Exception {
//        assertNotNull(context.hasEndpoint("direct:startTransactionFailureJob"));
//
//    }

    protected Header getHeader() {
        Header header = new Header();
        header.setBsCarrier("Kore");
        header.setSourceName("Tests");
        header.setRegion("USA");
        header.setOrganization("Grant Victor");
        header.setTransactionId("kjshafksdjahfks");
        header.setTimestamp("2016-10-10T11:11:11");
        return header;
    }

    protected void setExchangeProperties(Exchange exchange, BaseRequest request) {
        exchange.getIn().setBody(request);
        exchange.setProperty(IConstant.BSCARRIER, request.getHeader().getBsCarrier());
        exchange.setProperty(IConstant.SOURCE_NAME, request.getHeader().getSourceName());
        exchange.setProperty(IConstant.REGION, request.getHeader().getRegion());
        exchange.setProperty(IConstant.ORGANIZATION, request.getHeader().getOrganization());
        exchange.setProperty(IConstant.GV_TRANSACTION_ID, request.getHeader().getTransactionId());
        exchange.setProperty(IConstant.DATE_FORMAT, request.getHeader().getTimestamp());
        //exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, request.getDataArea().getNetSuiteId());
        exchange.setPattern(ExchangePattern.InOut);
    }

    protected void testHeader(MockEndpoint mock, String derivedCarrierName) {
        mock.allMessages().header(IConstant.MIDWAY_TRANSACTION_ID).isNotNull();
        mock.allMessages().exchangeProperty(IConstant.MIDWAY_TRANSACTION_ID).isNotNull();
        mock.expectedHeaderReceived(IConstant.MIDWAY_DERIVED_CARRIER_NAME, derivedCarrierName);
        mock.expectedPropertyReceived(IConstant.MIDWAY_DERIVED_CARRIER_NAME, derivedCarrierName);
    }
}
