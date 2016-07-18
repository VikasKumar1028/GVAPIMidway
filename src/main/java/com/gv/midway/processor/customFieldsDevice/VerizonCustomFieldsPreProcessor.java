package com.gv.midway.processor.customFieldsDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.customFieldsDevice.verizon.request.CustomFieldsDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class VerizonCustomFieldsPreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(VerizonCustomFieldsPreProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin::VerizonCustomFieldsPreProcessor");

        CustomFieldsDeviceRequestVerizon businessRequest = new CustomFieldsDeviceRequestVerizon();
        CustomFieldsDeviceRequest proxyRequest = (CustomFieldsDeviceRequest) exchange
                .getIn().getBody();
        businessRequest.setAccountName(proxyRequest.getDataArea()
                .getAccountName());
        businessRequest.setCustomFields(proxyRequest.getDataArea()
                .getCustomFields());
        businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
        businessRequest.setServicePlan(proxyRequest.getDataArea()
                .getServicePlan());
        businessRequest.setCustomFieldsToUpdate(proxyRequest.getDataArea()
                .getCustomFieldsToUpdate());

        MidWayDevices[] proxyDevicesArray = proxyRequest.getDataArea()
                .getDevices();
        Devices[] businessDevicesArray = new Devices[proxyDevicesArray.length];

        for (int j = 0; j < proxyDevicesArray.length; j++) {

            DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevicesArray[j]
                    .getDeviceIds().length];
            MidWayDevices proxyDevices = proxyDevicesArray[j];
            Devices businessDevice = new Devices();

            for (int i = 0; i < proxyDevices.getDeviceIds().length; i++) {
                MidWayDeviceId proxyDeviceId = proxyDevices.getDeviceIds()[i];

                DeviceId businessDeviceId = new DeviceId();
                businessDeviceId.setId(proxyDeviceId.getId());
                businessDeviceId.setKind(proxyDeviceId.getKind());

                LOGGER.info(proxyDeviceId.getId());

                businessDeviceIdArray[i] = businessDeviceId;

            }
            businessDevicesArray[j] = businessDevice;

            businessDevicesArray[j].setDeviceIds(businessDeviceIdArray);
        }
        businessRequest.setDevices(businessDevicesArray);

        ObjectMapper objectMapper = new ObjectMapper();

        String strRequestBody = objectMapper
                .writeValueAsString(businessRequest);

        exchange.getIn().setBody(strRequestBody);

        LOGGER.info("strRequestBody***ChangeService" + strRequestBody);

        Message message = exchange.getIn();
        String sessionToken = "";
        String authorizationToken = "";

        if (exchange.getProperty(IConstant.VZ_SEESION_TOKEN) != null
                && exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
            sessionToken = exchange.getProperty(IConstant.VZ_SEESION_TOKEN)
                    .toString();
            authorizationToken = exchange.getProperty(
                    IConstant.VZ_AUTHORIZATION_TOKEN).toString();
        }

        message.setHeader("VZ-M2M-Token", sessionToken);
        message.setHeader("Authorization", "Bearer " + authorizationToken);
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "PUT");
        message.setHeader(Exchange.HTTP_PATH, "/devices/actions/customFields");

        LOGGER.info("End::VerizonCustomFieldsPreProcessor");
    }

}
