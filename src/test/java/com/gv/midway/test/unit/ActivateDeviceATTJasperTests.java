package com.gv.midway.test.unit;

import com.gv.midway.attjasper.EditTerminalRequest;
import com.gv.midway.attjasper.EditTerminalResponse;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.utility.CommonUtil;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by ryan.tracy on 11/7/2016.
 */
public class ActivateDeviceATTJasperTests extends MidwayTestSupport {

    @Test
    public void test_activateDeviceATTJasper() throws Exception {

        RouteDefinition route = context.getRouteDefinition("seda:attJasperSedaActivation");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                        .process(exchange -> {
                            EditTerminalResponse response = new EditTerminalResponse();
                            response.setIccid("");
                            response.setVersion("");
                            response.setEffectiveDate(CommonUtil.getCurrentXMLGregorianDateTimeUTC());
                            exchange.getIn().setBody(response);
                        })
                        .to("log:input")
                        .to("mock:" + IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:activationWithCustomFieldFlow")
                        .to("log:input")
                        .to("mock:direct:activationWithCustomFieldFlow")
                        .skipSendToOriginalEndpoint();

                interceptSendToEndpoint("direct:activationWithServicePlanFlow")
                        .to("log:input")
                        .to("mock:direct:activationWithServicePlanFlow")
                        .skipSendToOriginalEndpoint();
            }

        });
        context.start();

        ActivateDeviceId activateDeviceId = new ActivateDeviceId("89011702272013902587", "ICCID");

        CustomFields customFields = new CustomFields();
        customFields.setKey("");
        customFields.setValue("");

        ActivateDevices activateDevices = new ActivateDevices();
        activateDevices.setDeviceIds(new ActivateDeviceId[] {activateDeviceId});
        activateDevices.setNetSuiteId(123456);
        activateDevices.setCustomFields(new CustomFields[] {customFields});
        activateDevices.setServicePlan("");

        ActivateDeviceRequestDataArea activateDeviceRequestDataArea = new ActivateDeviceRequestDataArea();
        activateDeviceRequestDataArea.setAccountName("");
        activateDeviceRequestDataArea.setCarrierName("ATT");
        activateDeviceRequestDataArea.setDevices(activateDevices);
        activateDeviceRequestDataArea.setGroupName("");

        ActivateDeviceRequest activateDeviceRequest = new ActivateDeviceRequest();
        activateDeviceRequest.setHeader(getHeader());
        activateDeviceRequest.setDataArea(activateDeviceRequestDataArea);

        Transaction transaction = new Transaction();
        transaction.setDevicePayload(activateDeviceRequest);
        transaction.setDeviceNumber("89011702272013902587");
        transaction.setNetSuiteId(187527);
        transaction.setCarrierName("ATTJASPER");

        //set by populateActivateDBPayload
        transaction.setRequestType(RequestType.ACTIVATION);

//        EditTerminalRequest editTerminalRequest = new EditTerminalRequest();
//        editTerminalRequest.setIccid(activateDeviceId.getId());
//        editTerminalRequest.setChangeType(IConstant.ATTJASPER_SIM_CHANGETYPE);
//        editTerminalRequest.setTargetValue(IConstant.ATTJASPER_ACTIVATED);
//        editTerminalRequest.setLicenseKey("a56f59ce-5ec7-4001-8cf3-cc493a3efb5e");
//        editTerminalRequest.setMessageId("" + new Date().getTime());
//        editTerminalRequest.setVersion("5.90");

        template.request("seda:attJasperSedaActivation", (Exchange exchange) -> {
            Message message = exchange.getIn();

            final List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders("username", "password");

            message.setHeader(CxfConstants.OPERATION_NAME, "EditTerminal");
            message.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema");
            message.setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/EditTerminal");
            message.setHeader(Header.HEADER_LIST, soapHeaders);

            exchange.getIn().setBody(transaction);

            exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
            exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, transaction.getNetSuiteId());
            exchange.setProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME, "ATTJASPER");
            exchange.setPattern(ExchangePattern.InOut);
        });

        MockEndpoint mock = getMockEndpoint("mock:" + IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT);

        mock.expectedMessageCount(1);
        mock.allMessages().body().isInstanceOf(EditTerminalResponse.class);

        mock.assertIsSatisfied();
        mock.reset();
    }
}
