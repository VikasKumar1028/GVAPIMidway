package com.gv.midway.processor.usageDevice;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class RetrieveDeviceUsageHistoryPreProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(RetrieveDeviceUsageHistoryPreProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Begin::RetrieveDeviceUsageHistoryPreProcessor");

        UsageInformationRequest proxyRequest = (UsageInformationRequest) exchange
                .getIn().getBody();

        UsageInformationRequestDataArea businessRequest = new UsageInformationRequestDataArea();

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

        message.setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list");

        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End::RetrieveDeviceUsageHistoryPreProcessor");
    }
}
