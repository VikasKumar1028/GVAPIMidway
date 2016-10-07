package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonDeviceUsageHistoryPreProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(VerizonDeviceUsageHistoryPreProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {


		LOGGER.info("Begin:VerizonDeviceUsageHistoryPreProcessor");
        LOGGER.info("Session Parameters  VZSessionToken"
                + exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
        LOGGER.info("Session Parameters  VZAuthorization"
                + exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

        DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
                .getBody();

        ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
        DeviceId device = new DeviceId();

        // Fetching Recommended device Identifiers
        DeviceId recommendedDeviceId = CommonUtil
                .getRecommendedDeviceIdentifier(deviceInfo.getDeviceIds());

        device.setId(recommendedDeviceId.getId());
        device.setKind(recommendedDeviceId.getKind());
        dataArea.setDeviceId(device);

        exchange.setProperty("DeviceId", device);
        exchange.setProperty("CarrierName", deviceInfo.getBs_carrier());
        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID,
                deviceInfo.getNetSuiteId());
        exchange.setProperty("ServicePlan", deviceInfo.getCurrentServicePlan());

        dataArea.setLatest(exchange.getProperty("jobEndTime").toString());
        dataArea.setEarliest(exchange.getProperty("jobStartTime").toString());

        ObjectMapper objectMapper = new ObjectMapper();

        String strRequestBody = objectMapper.writeValueAsString(dataArea);

        exchange.getIn().setBody(strRequestBody);

        Message message = CommonUtil.setMessageHeader(exchange);

        message.setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list");

        exchange.setPattern(ExchangePattern.InOut);
        
		LOGGER.info("End:VerizonDeviceUsageHistoryPreProcessor");

    }
}
