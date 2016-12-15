package com.gv.midway.processor.jobScheduler;

import com.gv.midway.utility.MessageStuffer;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.exception.KoreSimMissingException;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.usageInformation.kore.request.UsageInformationKoreRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class KoreDeviceUsageHistoryPreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(KoreDeviceUsageHistoryPreProcessor.class.getName());

    private Environment newEnv;

    public KoreDeviceUsageHistoryPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("*************Testing**************************************" + exchange.getIn().getBody());

        LOGGER.debug("Begin:KoreDeviceUsageHistoryPreProcessor");
        final Message message = exchange.getIn();

        LOGGER.debug("jobDetailDate ------" + exchange.getProperty(IConstant.JOB_DETAIL_DATE));

        final ObjectMapper objectMapper = new ObjectMapper();

        final DeviceInformation deviceInfo = (DeviceInformation) message.getBody();

        final DeviceId deviceId = CommonUtil.getSimNumber(deviceInfo.getDeviceIds());
        
       // Need to set these properties before device id check.In case of KoreSimMissingException
       // able to set the carrierName and netSuiteId in device Usage collection.
        exchange.setProperty("CarrierName", deviceInfo.getBs_carrier());
        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());

        if (deviceId == null) {
            throw new KoreSimMissingException("400", IConstant.KORE_MISSING_SIM_ERROR);
        }

        final String simNumber = deviceId.getId();

        final UsageInformationKoreRequest usageInformationKoreRequest = new UsageInformationKoreRequest();
        usageInformationKoreRequest.setSimNumber(simNumber);

        final String strRequestBody = objectMapper.writeValueAsString(usageInformationKoreRequest);

        LOGGER.debug("strRequestBody::" + strRequestBody);

        MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/queryDeviceUsageBySimNumber", strRequestBody);

        exchange.setProperty("DeviceId", deviceId);
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("message::" + message.toString());
        LOGGER.debug("End:KoreDeviceUsageHistoryPreProcessor");
    }
}