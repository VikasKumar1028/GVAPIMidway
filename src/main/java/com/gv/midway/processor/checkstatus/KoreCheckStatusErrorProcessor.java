package com.gv.midway.processor.checkstatus;

import java.util.Date;

import com.gv.midway.environment.EnvironmentParser;
import com.gv.midway.environment.NetSuiteOAuthHeaderProperties;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.NetSuiteRequestType;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;

public class KoreCheckStatusErrorProcessor implements Processor {

    /**
     * Call back the Netsuite endPoint
     */
    private static final Logger LOGGER = Logger.getLogger(KoreCheckStatusErrorProcessor.class.getName());

    private Environment newEnv;

    public KoreCheckStatusErrorProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:KoreCheckStatusErrorProcessor");
        LOGGER.info("kore check status error processor");

        final Message message = exchange.getIn();
        final String midWayTransactionDeviceNumber = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);
        final String midWayTransactionId = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID);
        final Integer netSuiteID = (Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID);
        final RequestType requestType = (RequestType) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);
        String errorDescription = (String) exchange.getProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC);
        final CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        final Object body = exchange.getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD);

        if (exception != null) {
            LOGGER.info("cxf exception while checking the status of Kore Provisioning request");
            final String errorResponseBody = exception.getResponseBody();
            final ObjectMapper mapper = new ObjectMapper();

            try {
                final KoreErrorResponse errorResponsePayload = mapper.readValue(errorResponseBody, KoreErrorResponse.class);
                errorDescription = errorResponsePayload.getErrorMessage();
                exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERROR_DESCRIPTION, errorDescription);
                exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERRORPAYLOAD, errorResponsePayload);
            } catch (Exception e) {
                LOGGER.error("Error ::" + e);
            }
        }

        final String desc = "Error in callBack from Kore For "
                + midWayTransactionDeviceNumber + ", transactionId "
                + midWayTransactionId + "and request Type is " + requestType;

        final KafkaNetSuiteCallBackError netSuiteCallBackError = new KafkaNetSuiteCallBackError();
        netSuiteCallBackError.setApp("Midway");
        netSuiteCallBackError.setCategory("Kore Call Back Error");
        netSuiteCallBackError.setId(requestType.toString());
        netSuiteCallBackError.setLevel("Error");
        netSuiteCallBackError.setTimestamp(new Date().getTime());
        netSuiteCallBackError.setVersion("1");
        netSuiteCallBackError.setException(errorDescription);
        netSuiteCallBackError.setMsg("Error in Call Back from Kore.");
        netSuiteCallBackError.setDesc(desc);
        netSuiteCallBackError.setBody(body);

        final BaseRequest baseRequest = (BaseRequest) body;
        final Header header = baseRequest.getHeader();

        final KeyValues keyValues1 = new KeyValues("transactionId", header.getTransactionId());
        final KeyValues keyValues2 = new KeyValues("orderNumber", midWayTransactionId);
        final KeyValues keyValues3 = new KeyValues("deviceIds", midWayTransactionDeviceNumber.replace("'\'", ""));
        final KeyValues keyValues4 = new KeyValues("netSuiteID", "" + netSuiteID);
        final KeyValues[] keyValuesArr = new KeyValues[]{keyValues1, keyValues2, keyValues3, keyValues4};

        netSuiteCallBackError.setKeyValues(keyValuesArr);

        final NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = new NetSuiteCallBackProvisioningRequest();
        netSuiteCallBackProvisioningRequest.setStatus("fail");
        netSuiteCallBackProvisioningRequest.setResponse(errorDescription);
        netSuiteCallBackProvisioningRequest.setCarrierOrderNumber(midWayTransactionId);
        netSuiteCallBackProvisioningRequest.setNetSuiteID("" + netSuiteID);

        final String deviceIdsArr = "{\"deviceIds\":" + midWayTransactionDeviceNumber + "}";
        final ObjectMapper mapper = new ObjectMapper();
        final Devices devices = mapper.readValue(deviceIdsArr, Devices.class);
        final DeviceId[] deviceIds = devices.getDeviceIds();

        netSuiteCallBackProvisioningRequest.setDeviceIds(deviceIds);

        LOGGER.info("body set for netSuiteCallBack........." + exchange.getIn().getBody());
        LOGGER.info("request type for NetSuite CallBack error...." + requestType);

        //TODO Duplicated in AttCallBackErrorPostProcessor
        switch (requestType) {
            case ACTIVATION:
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.ACTIVATION);
                break;
            case DEACTIVATION:
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.DEACTIVATION);
                break;
            case REACTIVATION:
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.REACTIVATION);
                break;
            case RESTORE:
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.RESTORATION);
                break;
            case SUSPEND:
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SUSPENSION);
                break;
            case CHANGESERVICEPLAN:
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
                netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);
                break;
            default:
                break;
        }


        final NetSuiteOAuthHeaderProperties properties = EnvironmentParser.getNetSuiteOAuthHeaderProperties(newEnv);
        final String script = "539";

        LOGGER.info("oauth info is....." + properties);

        final String oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(properties, script);

        message.setBody(netSuiteCallBackProvisioningRequest);
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", oauthHeader);
        message.setHeader(Exchange.HTTP_PATH, null);

        exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);
        exchange.setProperty("script", script);
        exchange.setPattern(ExchangePattern.InOut);
        LOGGER.info("error callback response for Kore..." + exchange.getIn().getBody());
        LOGGER.info("End:KoreCheckStatusErrorProcessor");
    }
}