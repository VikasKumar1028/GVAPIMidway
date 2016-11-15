package com.gv.midway.processor.checkstatus;

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
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;

public class AttCallBackSuccessPostProcessor implements Processor {

    /**
     * Call back the Netsuite endPoint
     */
    private static final Logger LOGGER = Logger.getLogger(AttCallBackSuccessPostProcessor.class.getName());

    private Environment newEnv;

    public AttCallBackSuccessPostProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Begin:AttCallBackSuccessPostProcessor");
        LOGGER.info("ATT Jasper CallBack Success post processor");

        final Message message = exchange.getIn();

        final String midWayTransactionDeviceNumber = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);
        final String midWayTransactionId = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID);
        final Integer netSuiteID = (Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID);
        final RequestType requestType = (RequestType) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);
        final Object body = exchange.getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD);

        final String desc = "Successful callBack from ATT Jasper For "
                + midWayTransactionDeviceNumber + ", transactionId "
                + midWayTransactionId + "and request Type is " + requestType;

        final KafkaNetSuiteCallBackEvent netSuiteCallBackEvent = new KafkaNetSuiteCallBackEvent();
        netSuiteCallBackEvent.setApp("Midway");
        netSuiteCallBackEvent.setCategory("ATT Jasper Call Back Success");
        netSuiteCallBackEvent.setId(requestType.toString());
        netSuiteCallBackEvent.setLevel("Info");
        netSuiteCallBackEvent.setTimestamp(new Date().getTime());
        netSuiteCallBackEvent.setVersion("1");
        netSuiteCallBackEvent.setMsg("Successful Call Back from ATT Jasper.");
        netSuiteCallBackEvent.setDesc(desc);
        netSuiteCallBackEvent.setBody(body);

        final BaseRequest baseRequest = (BaseRequest) body;
        final Header header = baseRequest.getHeader();

        final KeyValues keyValues1 = new KeyValues("transactionId", header.getTransactionId());
        final KeyValues keyValues2 = new KeyValues("orderNumber", midWayTransactionId);
        final KeyValues keyValues3 = new KeyValues("deviceIds", midWayTransactionDeviceNumber.replace("'\'", ""));
        final KeyValues keyValues4 = new KeyValues("netSuiteID", "" + netSuiteID);
        final KeyValues[] keyValuesArr = new KeyValues[]{keyValues1, keyValues2, keyValues3, keyValues4};

        netSuiteCallBackEvent.setKeyValues(keyValuesArr);

        final NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = new NetSuiteCallBackProvisioningRequest();
        netSuiteCallBackProvisioningRequest.setStatus("success");
        netSuiteCallBackProvisioningRequest.setCarrierOrderNumber(midWayTransactionId);
        netSuiteCallBackProvisioningRequest.setNetSuiteID("" + netSuiteID);

        final String deviceIdsArr = "{\"deviceIds\":" + midWayTransactionDeviceNumber + "}";

        final ObjectMapper mapper = new ObjectMapper();

        final Devices devices = mapper.readValue(deviceIdsArr, Devices.class);

        final DeviceId[] deviceIds = devices.getDeviceIds();

        netSuiteCallBackProvisioningRequest.setDeviceIds(deviceIds);

        LOGGER.info("request type for NetSuite CallBack error...." + requestType);

        //TODO Duplicated in KoreCheckStatusPostProcessor
        switch (requestType) {
            case ACTIVATION:
                netSuiteCallBackProvisioningRequest.setResponse("Device successfully activated.");
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.ACTIVATION);
                break;
            case DEACTIVATION:
                netSuiteCallBackProvisioningRequest.setResponse("Device successfully DeActivated.");
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.DEACTIVATION);
                break;
            case REACTIVATION:
                netSuiteCallBackProvisioningRequest.setResponse("Device successfully ReActivated.");
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.REACTIVATION);
                break;
            case RESTORE:
                netSuiteCallBackProvisioningRequest.setResponse("Device successfully ReStored.");
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.RESTORATION);
                break;
            case SUSPEND:
                netSuiteCallBackProvisioningRequest.setResponse("Device successfully Suspended.");
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SUSPENSION);
                break;
            case CHANGESERVICEPLAN:
                netSuiteCallBackProvisioningRequest.setResponse("Device Service Plan Changed successfully.");
                final ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest = (ChangeDeviceServicePlansRequest) body;
                LOGGER.info("change device service plan data area...." + changeDeviceServicePlansRequest.getDataArea().toString());
                final String oldServicePlan = changeDeviceServicePlansRequest.getDataArea().getCurrentServicePlan();
                final String newServicePlan = changeDeviceServicePlansRequest.getDataArea().getServicePlan();
                LOGGER.info("service plan new is..." + newServicePlan + " old service plan is....." + oldServicePlan);
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SERVICE_PLAN);
                netSuiteCallBackProvisioningRequest.setOldServicePlan(oldServicePlan);
                netSuiteCallBackProvisioningRequest.setNewServicePlan(newServicePlan);
                break;
            case CHANGECUSTOMFIELDS:
                netSuiteCallBackProvisioningRequest.setResponse("Device Custom Fields Changed successfully.");
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);
                break;
            default:
                break;
        }

        final NetSuiteOAuthHeaderProperties properties = EnvironmentParser.getNetSuiteOAuthHeaderProperties(newEnv);

        LOGGER.info("oauth info is....." + properties);

        //final String script = "539";
        final String script = newEnv.getProperty("netSuite.callbacks.script");

        final String oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(properties, script);

        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", oauthHeader);
        message.setHeader(Exchange.HTTP_PATH, null);
        message.setBody(netSuiteCallBackProvisioningRequest);

        exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackEvent);
        exchange.setProperty("script", script);
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("success callback response for ATT Jasper..." + exchange.getIn().getBody());
        LOGGER.info("End:AttCallBackSuccessPostProcessor");
    }
}