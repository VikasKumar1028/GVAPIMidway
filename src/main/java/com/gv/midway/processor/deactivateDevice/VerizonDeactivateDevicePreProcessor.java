package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDevices;
import com.gv.midway.pojo.deactivateDevice.verizon.request.DeactivateDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.CommonUtil;

public class VerizonDeactivateDevicePreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(VerizonDeactivateDevicePreProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:VerizonDeactivateDevicePreProcessor");

        DeactivateDeviceRequestVerizon businessRequest = new DeactivateDeviceRequestVerizon();
        DeactivateDeviceRequest proxyRequest = (DeactivateDeviceRequest) exchange
                .getIn().getBody();

        businessRequest.setAccountName(proxyRequest.getDataArea()
                .getAccountName());
        businessRequest.setCustomFields(proxyRequest.getDataArea()
                .getCustomFields());
        businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
        businessRequest.setServicePlan(proxyRequest.getDataArea()
                .getServicePlan());
        businessRequest.setEtfWaiver(proxyRequest.getDataArea().getEtfWaiver());
        businessRequest.setReasonCode(proxyRequest.getDataArea()
                .getReasonCode());

        DeactivateDevices[] proxyDevicesArray = proxyRequest.getDataArea()
                .getDevices();
        Devices[] businessDevicesArray = new Devices[proxyDevicesArray.length];

        for (int j = 0; j < proxyDevicesArray.length; j++) {

            DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevicesArray[j]
                    .getDeviceIds().length];
            DeactivateDevices proxyDevices = proxyDevicesArray[j];
            Devices businessDevice = new Devices();

            for (int i = 0; i < proxyDevices.getDeviceIds().length; i++) {
                DeactivateDeviceId proxyDeviceId = proxyDevices.getDeviceIds()[i];

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

        Message message = CommonUtil.setMessageHeader(exchange);

        message.setHeader(Exchange.HTTP_PATH, "/devices/actions/deactivate");

        LOGGER.info("End:VerizonDeactivateDevicePreProcessor");

    }
}
