package com.gv.midway.processor.usageDevice;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponseDataArea;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageHistory;
import com.gv.midway.pojo.usageInformation.verizon.response.VerizonUsageInformationResponse;

public class RetrieveDeviceUsageHistoryPostProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(RetrieveDeviceUsageHistoryPostProcessor.class
            .getName());

    Environment newEnv;

    public RetrieveDeviceUsageHistoryPostProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Begin::RetrieveDeviceUsageHistoryPostProcessor");

        Response response = new Response();

        long totalBytesUsed = 0L;

        UsageInformationResponse usageInformationResponse = new UsageInformationResponse();

        UsageInformationResponseDataArea usageInformationResponseDataArea = new UsageInformationResponseDataArea();

        if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

            VerizonUsageInformationResponse usageResponse = exchange.getIn().getBody(VerizonUsageInformationResponse.class);
//            ObjectMapper mapper = new ObjectMapper();
//
//            VerizonUsageInformationResponse usageResponse = mapper
//                    .convertValue(map, VerizonUsageInformationResponse.class);

            if (usageResponse.getUsageHistory() != null) {
                for (UsageHistory history : usageResponse.getUsageHistory()) {
                    totalBytesUsed = history.getBytesUsed() + totalBytesUsed;
                }
            }

            LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
            response.setResponseCode(IResponse.SUCCESS_CODE);
            response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
            response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVICE_USAGE_MIDWAY);

            usageInformationResponseDataArea.setTotalUsages(totalBytesUsed);
            usageInformationResponse.setDataArea(usageInformationResponseDataArea);

        } else {

            response.setResponseCode(400);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            response.setResponseDescription(exchange.getIn().getBody().toString());

        }

        Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

        usageInformationResponse.setHeader(responseHeader);
        usageInformationResponse.setResponse(response);

        exchange.getIn().setBody(usageInformationResponse);

        LOGGER.info("End::RetrieveDeviceUsageHistoryPostProcessor");
    }
}
