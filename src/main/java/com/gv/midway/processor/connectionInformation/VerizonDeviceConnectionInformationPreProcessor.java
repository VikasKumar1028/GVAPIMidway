package com.gv.midway.processor.connectionInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonDeviceConnectionInformationPreProcessor implements
        Processor {

    private static final Logger LOGGER = Logger
            .getLogger(VerizonDeviceConnectionInformationPreProcessor.class
                    .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Start:VerizonDeviceConnectionInformationPreProcessor");
        LOGGER.debug("Session Parameters  VZSessionToken"
                + exchange.getProperty(IConstant.VZ_SESSION_TOKEN));
        LOGGER.debug("Session Parameters  VZAuthorization"
                + exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

        ConnectionInformationRequestDataArea businessRequest = new ConnectionInformationRequestDataArea();
        ConnectionInformationRequest proxyRequest = (ConnectionInformationRequest) exchange
                .getIn().getBody();
        businessRequest.setEarliest(proxyRequest.getDataArea().getEarliest());
        businessRequest.setLatest(proxyRequest.getDataArea().getLatest());
        DeviceId deviceId = new DeviceId();
        deviceId.setId(proxyRequest.getDataArea().getDeviceId().getId());
        deviceId.setKind(proxyRequest.getDataArea().getDeviceId().getKind());
        businessRequest.setDeviceId(deviceId);

        ObjectMapper objectMapper = new ObjectMapper();

        String strRequestBody = objectMapper
                .writeValueAsString(businessRequest);

        exchange.getIn().setBody(strRequestBody);

        Message message = CommonUtil.setMessageHeader(exchange);
        message.setHeader(Exchange.HTTP_PATH,
                "/devices/connections/actions/listHistory");

        LOGGER.debug("End:VerizonDeviceConnectionStatusPreProcessor");

    }

}
