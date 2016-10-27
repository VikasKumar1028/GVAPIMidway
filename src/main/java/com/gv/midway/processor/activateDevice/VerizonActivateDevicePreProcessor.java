package com.gv.midway.processor.activateDevice;

import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.verizon.request.ActivateDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.CommonUtil;

public class VerizonActivateDevicePreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(VerizonActivateDevicePreProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:VerizonActivateDevicePreProcessor");
        LOGGER.info("Session Parameters  VZSessionToken" + exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
        LOGGER.info("Session Parameters  VZAuthorization" + exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

        final ActivateDeviceRequest proxyRequest = (ActivateDeviceRequest) exchange.getIn().getBody();
        final ActivateDeviceRequestDataArea dataArea = proxyRequest.getDataArea();
        final ActivateDevices proxyDevices = dataArea.getDevices();

        final ActivateDeviceRequestVerizon businessRequest = new ActivateDeviceRequestVerizon();
        businessRequest.setAccountName(dataArea.getAccountName());
        businessRequest.setCarrierIpPoolName(dataArea.getCarrierIpPoolName());
        businessRequest.setCarrierName(dataArea.getCarrierName());
        businessRequest.setCostCenterCode(dataArea.getCostCenterCode());
        businessRequest.setCustomFields(proxyDevices.getCustomFields());
        businessRequest.setGroupName(dataArea.getGroupName());
        businessRequest.setLeadId(dataArea.getLeadId());
        businessRequest.setMdnZipCode(dataArea.getMdnZipCode());
        businessRequest.setPublicIpRestriction(dataArea.getPublicIpRestriction());
        businessRequest.setServicePlan(proxyDevices.getServicePlan());
        businessRequest.setSkuNumber(dataArea.getSkuNumber());
        businessRequest.setPrimaryPlaceOfUse(proxyDevices.generatePrimaryPlaceOfUse());

        final DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevices.getDeviceIds().length];

        for (int i = 0; i < proxyDevices.getDeviceIds().length; i++) {
            final ActivateDeviceId proxyDeviceId = proxyDevices.getDeviceIds()[i];

            final DeviceId businessDeviceId = new DeviceId();
            businessDeviceId.setId(proxyDeviceId.getId());
            businessDeviceId.setKind(proxyDeviceId.getKind());

            LOGGER.info(proxyDeviceId.getId());

            businessDeviceIdArray[i] = businessDeviceId;
        }

        final Devices businessDevice = new Devices();
        businessDevice.setDeviceIds(businessDeviceIdArray);

        final Devices[] businessDevicesArray = new Devices[1];
        businessDevicesArray[0] = businessDevice;

        businessRequest.setDevices(businessDevicesArray);

        final ObjectMapper objectMapper = new ObjectMapper();
        final String strRequestBody = objectMapper.writeValueAsString(businessRequest);
        exchange.getIn().setBody(strRequestBody);

        final Message message = CommonUtil.setMessageHeader(exchange);

        message.setHeader(Exchange.HTTP_PATH, "/devices/actions/activate");
        
        LOGGER.info("End:VerizonActivateDevicePreProcessor");
    }
}