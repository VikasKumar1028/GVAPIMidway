package com.gv.midway.processor.activateDevice;

import java.util.Date;

import com.gv.midway.environment.EnvironmentParser;
import com.gv.midway.environment.NetSuiteOAuthHeaderProperties;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.NetSuiteRequestType;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;

public class KoreActivationWithCustomFieldProcessor implements Processor {

    /**
     * Call back the Netsuite endPoint
     */
    private static final Logger LOGGER = Logger.getLogger(KoreActivationWithCustomFieldProcessor.class.getName());

    private Environment newEnv;

    public KoreActivationWithCustomFieldProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Begin:KoreActivationWithCustomFieldProcessor");

        final String midWayTransactionDeviceNumber = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);
        final String midWayTransactionId = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID);
        final Integer netSuiteID = (Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID);
        final Object body = exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD);
        final KafkaNetSuiteCallBackEvent netSuiteCallBackEvent = (KafkaNetSuiteCallBackEvent) exchange.getProperty(IConstant.KAFKA_OBJECT);

        final String desc = "Successful callBack from Kore For "
                + midWayTransactionDeviceNumber + ", transactionId "
                + midWayTransactionId + "and request Type is "
                + RequestType.CHANGECUSTOMFIELDS;

        netSuiteCallBackEvent.setId(RequestType.CHANGECUSTOMFIELDS.toString());
        netSuiteCallBackEvent.setTimestamp(new Date().getTime());
        netSuiteCallBackEvent.setDesc(desc);
        netSuiteCallBackEvent.setBody(body);

        final String deviceIdsJson = "{\"deviceIds\":" + midWayTransactionDeviceNumber + "}";
        final ObjectMapper mapper = new ObjectMapper();
        final Devices devices = mapper.readValue(deviceIdsJson, Devices.class);
        final DeviceId[] deviceIds = devices.getDeviceIds();

        final NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = new NetSuiteCallBackProvisioningRequest();
        netSuiteCallBackProvisioningRequest.setStatus("success");
        netSuiteCallBackProvisioningRequest.setCarrierOrderNumber(midWayTransactionId);
        netSuiteCallBackProvisioningRequest.setNetSuiteID("" + netSuiteID);
        netSuiteCallBackProvisioningRequest.setDeviceIds(deviceIds);
        netSuiteCallBackProvisioningRequest.setResponse("Device Custom Fields Changed successfully.");
        netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);

        final NetSuiteOAuthHeaderProperties properties = EnvironmentParser.getNetSuiteOAuthHeaderProperties(newEnv);

        LOGGER.info("request type for NetSuite CallBack success is..." + RequestType.CHANGECUSTOMFIELDS);
        LOGGER.info("oauth info is....." + properties);

        final String script = "539";
        final String oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(properties, script);

        exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackEvent);
        exchange.setProperty("script", script);
        exchange.setPattern(ExchangePattern.InOut);

        final Message message = exchange.getIn();
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", oauthHeader);
        message.setHeader(Exchange.HTTP_PATH, null);
        message.setBody(netSuiteCallBackProvisioningRequest);

        LOGGER.info("successful callback response to Kore for activation with Custom Field..." + exchange.getIn().getBody());
        LOGGER.info("End:KoreActivationWithCustomFieldProcessor");
    }
}