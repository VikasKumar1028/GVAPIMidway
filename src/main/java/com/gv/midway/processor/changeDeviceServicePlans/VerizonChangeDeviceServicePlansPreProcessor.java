package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.verizon.request.ChangeDeviceServicePlansRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class VerizonChangeDeviceServicePlansPreProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(VerizonChangeDeviceServicePlansPostProcessor.class
                    .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin::VerizonChangeDeviceServicePlansPreProcessor");

        ChangeDeviceServicePlansRequestVerizon businessRequest = new ChangeDeviceServicePlansRequestVerizon();
        ChangeDeviceServicePlansRequest proxyRequest = (ChangeDeviceServicePlansRequest) exchange
                .getIn().getBody();
       
        businessRequest.setServicePlan(proxyRequest.getDataArea()
                .getServicePlan());
        
        businessRequest.setAccountName(proxyRequest.getDataArea()
                .getAccountName());

        MidWayDevices[] proxyDevicesArray = proxyRequest.getDataArea()
                .getDevices();
        // if devices are coming in the request
        if(proxyDevicesArray!=null)
        {
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
        }
        
        // If no devices in device list then group, custom fields, service plan.may be defined in the request.
        else
        {
        	
        	
             businessRequest.setCurrentServicePlan(proxyRequest.getDataArea()
                     .getCurrentServicePlan());
             businessRequest.setCustomFields(proxyRequest.getDataArea()
                     .getCustomFields());
             businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String strRequestBody = objectMapper
                .writeValueAsString(businessRequest);

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

        exchange.getIn().setBody(strRequestBody);

        message.setHeader("VZ-M2M-Token", sessionToken);
        message.setHeader("Authorization", "Bearer " + authorizationToken);
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "PUT");
        message.setHeader(Exchange.HTTP_PATH, "/devices/actions/plan");
        LOGGER.info("Start::VerizonChangeDeviceServicePlansPreProcessor");

    }

}
