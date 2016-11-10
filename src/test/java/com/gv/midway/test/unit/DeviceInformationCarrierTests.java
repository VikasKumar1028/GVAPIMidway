package com.gv.midway.test.unit;

import com.gv.midway.attjasper.GetTerminalDetailsResponse;
import static com.gv.midway.attjasper.GetTerminalDetailsResponse.Terminals;

import com.gv.midway.attjasper.TerminalType;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.pojo.deviceInformation.kore.response.D;
import com.gv.midway.pojo.deviceInformation.kore.response.DeviceInformationResponseKore;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.verizon.response.CarrierInformations;
import com.gv.midway.pojo.deviceInformation.verizon.response.DeviceInformationDevices;
import com.gv.midway.pojo.deviceInformation.verizon.response.DeviceInformationResponseVerizon;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by ryan.tracy on 10/20/2016.
 */



public class DeviceInformationCarrierTests extends DeviceInformationTestSupport {

    @Test
    public void test_deviceInformationCarrierKore() throws Exception {

        RouteDefinition route = context.getRouteDefinition("deviceInformationCarrier");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(IEndPoints.URI_REST_KORE_ENDPOINT)
                        .process(exchange -> {
                            D d = new D();
                            d.setCurrentDataPlan("currentDataPlan");
                            d.setCurrentSMSPlan("none");
                            d.setCustomField1("cust1");
                            d.setCustomField2("cust2");
                            d.setCustomField3("cust3");
                            d.setCustomField4("cust4");
                            d.setCustomField5("cust5");
                            d.setCustomField6("cust6");
                            d.setDailyDataThreshold(10000000);
                            d.setDailySMSThreshold(1000);
                            d.setStatus("active");
                            d.set__type("DeviceInformation:https://PRiSMProAPI.koretelematics.com/types");
                            d.setIMSIOrMIN("310410740594709");
                            d.setMonthlyDataThreshold(1000000000);
                            d.setMonthlySMSThreshold(100000);
                            d.setMSISDNOrMDN("5663057005");
                            d.setRequestStatus("0");

                            DeviceInformationResponseKore response = new DeviceInformationResponseKore();
                            response.setD(d);
                            exchange.setPattern(ExchangePattern.InOut);
                            exchange.getIn().setBody(CommonUtil.toJsonString(response));

                        })
                        .to("mock:" + IEndPoints.URI_REST_KORE_ENDPOINT)
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:postDeviceInformationCarrierKoreRest")
                        .to("log:input")
                        .to("mock:direct:postDeviceInformationCarrierKoreRest")
                        .skipSendToOriginalEndpoint();
            }

        });
        context.start();

        template.request("direct:deviceInformationCarrier", (Exchange exchange) -> setExchangeProperties(exchange, getDeviceInformationRequest()));

        MockEndpoint mock = getMockEndpoint("mock:" + IEndPoints.URI_REST_KORE_ENDPOINT);

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(String.class);
        testHeader(mock, "KORE");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:direct:postDeviceInformationCarrierKoreRest");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(DeviceInformationResponseKore.class);
        testHeader(mock, "KORE");

        mock.assertIsSatisfied();
    }

    @Test
    public void test_deviceInformationCarrierVerizon() throws Exception {
        RouteDefinition route = context.getRouteDefinition("deviceInformationCarrier");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(IEndPoints.URI_REST_VERIZON_ENDPOINT).inheritErrorHandler(false)

                        .process(exchange -> {
                            DeviceInformationDevices devices = new DeviceInformationDevices();
                            devices.setAccountName("0442090022-00001");
                            devices.setBillingCycleEndDate("2016-10-21T19:00:00-05:00");
                            devices.setConnected(true);
                            devices.setCreatedAt("2015-12-17T13:53:21-05:00");
                            CustomFields cf = new CustomFields();
                            cf.setKey("CustomField1");
                            cf.setValue("myValue");
                            devices.setCustomFields(new CustomFields[]{cf});
                            DeviceId deviceId = new DeviceId();
                            deviceId.setKind("imei");
                            deviceId.setId("990083420535573");
                            devices.setDeviceIds(new DeviceId[]{deviceId});
                            //devices.setExtendedAttributes();
                            devices.setGroupNames(new String[] {"groupName"});
                            CarrierInformations carrierInformations = new CarrierInformations();
                            carrierInformations.setCarrierName("Verizon");
                            carrierInformations.setState("Utah");
                            carrierInformations.setServicePlan("myServicePlan");
                            devices.setCarrierInformations(new CarrierInformations[]{carrierInformations});
                            devices.setIpAddress("100.127.22.52");
                            devices.setLastActivationBy("Grant Victor");
                            devices.setLastActivationDate("2016-06-14T19:00:00-05:00");
                            devices.setLastConnectionDate("2016-10-21T17:00:00-05:00");
                            DeviceInformationResponseVerizon response = new DeviceInformationResponseVerizon();

                            DeviceInformationDevices[] devicesArr = new DeviceInformationDevices[]{devices};
                            response.setDevices(devicesArr);
                            response.setHasMoreData("false");
                            exchange.setPattern(ExchangePattern.InOut);
                            exchange.getIn().setBody(CommonUtil.toJsonString(response));

                        })
                        .to("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT)
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:postDeviceInformationCarrierVerizonRest")
                        .to("log:input")
                        .to("mock:direct:postDeviceInformationCarrierVerizonRest")
                        .skipSendToOriginalEndpoint();

            }

        });
        context.start();

        DeviceInformationRequest request = getDeviceInformationRequest();
        request.getHeader().setBsCarrier("Verizon");

        template.request("direct:deviceInformationCarrier", (Exchange exchange) -> setExchangeProperties(exchange, request));

        MockEndpoint mock = getMockEndpoint("mock:" + IEndPoints.URI_REST_VERIZON_ENDPOINT);

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(String.class);
        testHeader(mock, "VERIZON");

        mock.assertIsSatisfied();
        mock.reset();

        mock = getMockEndpoint("mock:direct:postDeviceInformationCarrierVerizonRest");

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(DeviceInformationResponseVerizon.class);
        testHeader(mock, "VERIZON");

        mock.assertIsSatisfied();
    }

    @Test
    public void test_deviceInformationCarrierAttJasper() throws Exception {
        RouteDefinition route = context.getRouteDefinition("deviceInformationCarrier");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                        .process(exchange -> {
                            GetTerminalDetailsResponse response = new GetTerminalDetailsResponse();
                            Terminals terminals = new Terminals();
                            TerminalType terminalType = new TerminalType();
                            terminalType.setIccid("89011702272013902603");
                            terminalType.setAccountId(123456L);
                            terminalType.setCustom1("cust1");
                            terminalType.setCustom2("cust2");
                            terminalType.setCustom3("cust3");
                            terminalType.setCustom4("cust4");
                            terminalType.setCustom5("cust5");
                            terminalType.setCustom6("cust6");
                            terminalType.setCustom7("cust7");
                            terminalType.setCustom8("cust8");
                            terminalType.setCustom9("cust9");
                            terminalType.setCustom10("cust10");
                            terminalType.setCtdSessionCount(123456L);
                            terminalType.setCustomer("");
                            terminalType.setDateActivated(CommonUtil.getCurrentXMLGregorianDateTimeUTC());
                            terminalType.setDateAdded(CommonUtil.getCurrentXMLGregorianDateTimeUTC());
                            terminalType.setDateModified(CommonUtil.getCurrentXMLGregorianDateTimeUTC());
                            terminalType.setFixedIpAddress("192.168.0.188");
                            terminalType.setMonthToDateDataUsage(new BigDecimal(123456.0));
                            terminalType.setMonthToDateVoiceUsage(new BigDecimal(123456.0));
                            terminalType.setRatePlan("");
                            terminalType.setStatus("");
                            terminalType.setSuspended("");
                            terminalType.setTerminalId("");
                            terminalType.setVersion(123456);
                            terminals.getTerminal().add(terminalType);
                            response.setTerminals(terminals);
                            response.setBuild("");
                            response.setCorrelationId("");
                            //response.setTimestamp();
                            response.setVersion("");
                            exchange.setPattern(ExchangePattern.InOut);
                            exchange.getIn().setBody(response);

                        })
                        .to("log:input")
                        .to("mock:" + IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                        .skipSendToOriginalEndpoint();

            }

        });
        context.start();

        DeviceInformationRequest request = getDeviceInformationRequest();
        request.getHeader().setBsCarrier("AttJasper");

        template.request("direct:deviceInformationCarrier", (Exchange exchange) -> setExchangeProperties(exchange, request));

        MockEndpoint mock = getMockEndpoint("mock:" + IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT);

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(GetTerminalDetailsResponse.class);
        testHeader(mock, "ATTJASPER");

        mock.assertIsSatisfied();
    }
}
