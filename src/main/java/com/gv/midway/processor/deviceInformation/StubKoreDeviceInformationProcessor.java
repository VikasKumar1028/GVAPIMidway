package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.deviceInformation.verizon.response.ExtendedAttributes;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;

public class StubKoreDeviceInformationProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(StubKoreDeviceInformationProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:StubKoreDeviceInformationProcessor");
        DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

        DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

        DeviceInformation deviceInformation = new DeviceInformation();
        Header responseheader = new Header();

        Response response = new Response();

        response.setResponseCode(IResponse.SUCCESS_CODE);

        response.setResponseDescription("Device Information is fetched successfully");
        response.setResponseStatus("SUCESS");

        responseheader.setApplicationName("WEB");
        responseheader.setRegion("Region_Value");
        responseheader.setTimestamp("2016-03-08T21:49:45");
        responseheader.setOrganization("Grant Victor");
        responseheader.setSourceName("KORE");
        responseheader.setTransactionId("cde2131ksjd");
        responseheader.setBsCarrier("KORE");
        deviceInformationResponse.setHeader(responseheader);
        deviceInformationResponse.setResponse(response);

        deviceInformation.setAccountName("null");
        deviceInformation.setNetSuiteId(2);
        deviceInformation.setMidwayMasterDeviceId("MMD002");
        deviceInformation.setBillingCycleEndDate("null");
        deviceInformation.setConnected(false);
        deviceInformation.setCreatedAt("null");
        deviceInformation.setIpAddress("null");
        deviceInformation.setLastActivationBy("null");

        deviceInformation.setLastActivationDate("null");
        deviceInformation.setLastConnectionDate("null");
        deviceInformation.setCurrentSMSPlan("NA");
        deviceInformation
                .setFutureDataPlan("PLAN000191: USG GPRS 5MB Pooled Plan");
        deviceInformation.setFutureSMSPlan("NA");
        deviceInformation.setVoiceDispatchNumber("");

        deviceInformation.setDailyDataThreshold(512);
        deviceInformation.setDailySMSThreshold(4);
        deviceInformation.setMonthlyDataThreshold(5120);
        deviceInformation.setMonthlySMSThreshold(100);

        DeviceId deviceIds = new DeviceId();
        deviceIds.setId("89014103277405946190");
        deviceIds.setKind("null");

        DeviceId[] deviceIdsArray = { deviceIds };

        deviceInformation.setDeviceIds(deviceIdsArray);

        ExtendedAttributes[] extendedAttributes = new ExtendedAttributes[4];

        new ExtendedAttributes("key1", "null");
        extendedAttributes[0] = new ExtendedAttributes("key1", "null");
        extendedAttributes[1] = new ExtendedAttributes("key2", "null");
        extendedAttributes[2] = new ExtendedAttributes("key3", "null");
        extendedAttributes[3] = new ExtendedAttributes("key4", "null");

        deviceInformation.setExtendedAttributes(extendedAttributes);

        String[] lstFeatures = { "FEAT000601: USG ServiceData",
                "FEAT001690: USG GPRS Static IP A10" };

        deviceInformation.setLstFeatures(lstFeatures);

        CustomFields customFields = new CustomFields();
        CustomFields customFields1 = new CustomFields();
        CustomFields customFields2 = new CustomFields();
        CustomFields customFields3 = new CustomFields();
        CustomFields customFields4 = new CustomFields();
        CustomFields customFields5 = new CustomFields();

        customFields.setKey("customField1");
        customFields.setValue("791755");
        customFields1.setKey("customField2");
        customFields1.setValue("354283049292794");
        customFields2.setKey("customField3");
        customFields2.setValue("00804410E6A2");

        customFields3.setKey("customField4");
        customFields3.setValue("customField4");

        customFields4.setKey("customField5");
        customFields4.setValue("customField5");

        customFields5.setKey("customField6");
        customFields5.setValue("customField6");

        CustomFields[] arr = { customFields, customFields1, customFields2,
                customFields3, customFields4, customFields5 };

        deviceInformation.setCustomFields(arr);

        String[] lstExtFeatures = { "lstExtFeatures" };
        deviceInformation.setLstExtFeatures(lstExtFeatures);

        String[] lstHistoryOverLastYear = { "01/13/2016 18:46 | Activation (FEAT000601: USG Service - Data, FEAT001690: USG GPRS Static IP A10)" };
        deviceInformation.setLstHistoryOverLastYear(lstHistoryOverLastYear);

        deviceInformationResponseDataArea.setDevices(deviceInformation);
        deviceInformationResponse
                .setDataArea(deviceInformationResponseDataArea);

        exchange.getIn().setBody(deviceInformationResponse);
        
        LOGGER.info("End:StubKoreDeviceInformationProcessor");

    }
}
