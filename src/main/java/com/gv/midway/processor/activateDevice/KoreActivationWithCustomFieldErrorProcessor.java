package com.gv.midway.processor.activateDevice;

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
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;

public class KoreActivationWithCustomFieldErrorProcessor implements Processor {

    /**
     * Call back the Netsuite endPoint
     */
    private static final Logger LOGGER = Logger.getLogger(KoreActivationWithCustomFieldErrorProcessor.class.getName());

    private Environment newEnv;

    public KoreActivationWithCustomFieldErrorProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:KoreActivationWithCustomFieldErrorProcessor");

        final Message message = exchange.getIn();

        final String midWayTransactionDeviceNumber = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);
        final String midWayTransactionId = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID);
        final Integer netSuiteID = (Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID);
        final Object object = exchange.getProperty(IConstant.KAFKA_OBJECT);
        final Object body = exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD);

        String errorDescription = null;

        // exception for the activation request so no need to call the custom fields and send the error call back for them
        if (object instanceof KafkaNetSuiteCallBackError) {
            final KafkaNetSuiteCallBackError netSuiteCallBackError = (KafkaNetSuiteCallBackError) object;

            errorDescription = netSuiteCallBackError.getException();

            final String desc = "Error in callBack from Kore For "
                    + midWayTransactionDeviceNumber + ", transactionId "
                    + midWayTransactionId + "and request Type is " + RequestType.CHANGECUSTOMFIELDS;

            netSuiteCallBackError.setId(RequestType.CHANGECUSTOMFIELDS.toString());
            netSuiteCallBackError.setTimestamp(new Date().getTime());
            netSuiteCallBackError.setDesc(desc);
            netSuiteCallBackError.setBody(body);

            exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);
        }
        else {
            // exception comes while calling the change custom fields send the error call back for them
            final Exception errorObj = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

            if (errorObj instanceof CxfOperationException) {
                LOGGER.info("cxf exception calling the changeCustomFields");
                final CxfOperationException exception = (CxfOperationException) errorObj;
                final String errorResponseBody = exception.getResponseBody();
                final ObjectMapper mapper = new ObjectMapper();

                try {
                    final KoreErrorResponse errorResponsePayload = mapper.readValue(errorResponseBody, KoreErrorResponse.class);
                    errorDescription = errorResponsePayload.getErrorMessage();

                    exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERRORPAYLOAD, errorResponsePayload);
                } catch (Exception e) {
                    LOGGER.error("Error ::" + e);
                }
            } else {
                errorDescription = errorObj.getMessage();
            }

            final String desc = "Error in callBack from Kore For "
                    + midWayTransactionDeviceNumber + ", transactionId "
                    + midWayTransactionId + "and request Type is " + RequestType.CHANGECUSTOMFIELDS;

            final KafkaNetSuiteCallBackError netSuiteCallBackError = new KafkaNetSuiteCallBackError();
            netSuiteCallBackError.setApp("Midway");
            netSuiteCallBackError.setCategory("Kore Call Back Error");
            netSuiteCallBackError.setId(RequestType.CHANGECUSTOMFIELDS.toString());
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

            netSuiteCallBackError.setKeyValues(new KeyValues[]{keyValues1, keyValues2, keyValues3, keyValues4});

            exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERROR_DESCRIPTION, errorDescription);
            exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);
        }

        final String deviceIdsJson = "{\"deviceIds\":" + midWayTransactionDeviceNumber + "}";
        final ObjectMapper mapper = new ObjectMapper();
        final Devices devices = mapper.readValue(deviceIdsJson, Devices.class);
        final DeviceId[] deviceIds = devices.getDeviceIds();

        final NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = new NetSuiteCallBackProvisioningRequest();
        netSuiteCallBackProvisioningRequest.setResponse(errorDescription);
        netSuiteCallBackProvisioningRequest.setStatus("fail");
        netSuiteCallBackProvisioningRequest.setCarrierOrderNumber(midWayTransactionId);
        netSuiteCallBackProvisioningRequest.setNetSuiteID("" + netSuiteID);
        netSuiteCallBackProvisioningRequest.setDeviceIds(deviceIds);
        netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);

        final NetSuiteOAuthHeaderProperties properties = EnvironmentParser.getNetSuiteOAuthHeaderProperties(newEnv);

        LOGGER.info("request type for NetSuite CallBack error...." + RequestType.CHANGECUSTOMFIELDS);
        LOGGER.info("oauth info is....." + properties);

        final String script = "539";
        final String oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(properties, script);

        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", oauthHeader);
        message.setHeader(Exchange.HTTP_PATH, null);
        message.setBody(netSuiteCallBackProvisioningRequest);

        exchange.setProperty("script", script);
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("error callback response to Kore for activation with Custom Field..." + exchange.getIn().getBody());
        LOGGER.info("End:KoreActivationWithCustomFieldErrorProcessor");
    }
}