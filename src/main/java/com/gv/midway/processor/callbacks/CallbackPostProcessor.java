package com.gv.midway.processor.callbacks;

import com.gv.midway.environment.EnvironmentParser;
import com.gv.midway.environment.NetSuiteOAuthHeaderProperties;
import com.gv.midway.utility.NetSuiteOAuthUtil;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.NetSuiteRequestType;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;

public class CallbackPostProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(CallbackPostProcessor.class);

    private Environment newEnv;

    public CallbackPostProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Inside CallbackPostProcessor process " + exchange.getIn().getBody());

        final NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = (NetSuiteCallBackProvisioningRequest) exchange.getIn().getBody();
        LOGGER.debug("body set for netSuiteCallBack........." + netSuiteCallBackProvisioningRequest);

        final Object kafkaObject = exchange.getProperty(IConstant.KAFKA_OBJECT);

        final String topicName = kafkaObject instanceof KafkaNetSuiteCallBackError ? "midway-app-errors" : "midway-alerts";
        exchange.setProperty(IConstant.KAFKA_TOPIC_NAME, topicName);

        final NetSuiteRequestType requestType = netSuiteCallBackProvisioningRequest.getRequestType();
        LOGGER.debug("request type for NetSuite CallBack ..." + requestType);

        final NetSuiteOAuthHeaderProperties oAuthHeaderProperties = EnvironmentParser.getNetSuiteOAuthHeaderProperties(newEnv);
        LOGGER.debug("oauth info is....." + oAuthHeaderProperties);

        final String script = newEnv.getProperty("netSuite.callbacks.script");
        final String oauthHeader;

        switch (requestType) {
            //REACTIVATION was originally included in this list but was commented out
            case ACTIVATION:
            case DEACTIVATION:
            case SUSPENSION:
            case RESTORATION:
            case SERVICE_PLAN:
            case CUSTOM_FIELDS:
                oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(oAuthHeaderProperties, script);
                break;
            default:
                oauthHeader = null;
                break;
        }

        final Message message = exchange.getIn();
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", oauthHeader);

        exchange.setProperty("script", script);
        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, netSuiteCallBackProvisioningRequest.getNetSuiteID());
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE, requestType);
        exchange.setPattern(ExchangePattern.InOut);
    }
}