package com.gv.midway.processor.checkstatus;

import java.util.Date;

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
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;

public class AttCallBackErrorPostProcessor implements Processor {

    /**
     * Call back the Netsuite endPoint
     */

    private static final Logger LOGGER = Logger
            .getLogger(AttCallBackErrorPostProcessor.class.getName());

    private Environment newEnv;

    public AttCallBackErrorPostProcessor() {
        // Empty Constructor
    }

    public AttCallBackErrorPostProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

    	  LOGGER.info("Begin:AttCallBackErrorPostProcessor");

        Message message = exchange.getIn();

        String midWayTransactionDeviceNumber = (String) exchange
                .getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);

        String midWayTransactionId = (String) exchange
                .getProperty(IConstant.MIDWAY_TRANSACTION_ID);

        Integer netSuiteID = (Integer) exchange
                .getProperty(IConstant.MIDWAY_NETSUITE_ID);

        RequestType requestType = (RequestType) exchange
                .getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);

        String errorDescription = (String) exchange
                .getProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC);

        LOGGER.info("cxf operation not caught before is...........");



        KafkaNetSuiteCallBackError netSuiteCallBackError = new KafkaNetSuiteCallBackError();

        netSuiteCallBackError.setApp("Midway");
        netSuiteCallBackError.setCategory("ATT Jasper Call Back Error");
        netSuiteCallBackError.setId(requestType.toString());
        netSuiteCallBackError.setLevel("Error");
        netSuiteCallBackError.setTimestamp(new Date().getTime());
        netSuiteCallBackError.setVersion("1");
        if (requestType.toString().equals(RequestType.CHANGECUSTOMFIELDS.toString()))
        {
            netSuiteCallBackError.setException( exchange.getProperty(IConstant.ATTJASPER_CUSTOM_FIELD_DEC).toString() ); 
        }else{            
        netSuiteCallBackError.setException(errorDescription);
        }
        netSuiteCallBackError.setMsg("Error in Call Back from ATT Jasper.");

        String desc = "Error in callBack from ATT Jasper For "
                + midWayTransactionDeviceNumber + ", transactionId "
                + midWayTransactionId + "and request Type is " + requestType;

        netSuiteCallBackError.setDesc(desc);

        Object body = exchange
                .getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD);

        netSuiteCallBackError.setBody(body);

        BaseRequest baseRequest = (BaseRequest) body;

        Header header = baseRequest.getHeader();

        KeyValues keyValues1 = new KeyValues();

        keyValues1.setK("transactionId");
        keyValues1.setV(header.getTransactionId());

        KeyValues keyValues2 = new KeyValues();

        keyValues2.setK("orderNumber");
        keyValues2.setV(midWayTransactionId);

        KeyValues keyValues3 = new KeyValues();

        keyValues3.setK("deviceIds");
        keyValues3.setV(midWayTransactionDeviceNumber.replace("'\'", ""));

        KeyValues keyValues4 = new KeyValues();

        keyValues4.setK("netSuiteID");
        keyValues4.setV("" + netSuiteID);

        KeyValues[] keyValuesArr = new KeyValues[4];

        keyValuesArr[0] = keyValues1;
        keyValuesArr[1] = keyValues2;
        keyValuesArr[2] = keyValues3;
        keyValuesArr[3] = keyValues4;

        netSuiteCallBackError.setKeyValues(keyValuesArr);

        exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);

        NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = new NetSuiteCallBackProvisioningRequest();

        netSuiteCallBackProvisioningRequest.setStatus("fail");
        
        if (requestType.equals(RequestType.CHANGECUSTOMFIELDS))
        {
            netSuiteCallBackProvisioningRequest.setResponse( exchange.getProperty(IConstant.ATTJASPER_CUSTOM_FIELD_DEC).toString() ); 
        }else{            
            netSuiteCallBackProvisioningRequest.setResponse(errorDescription);
        }       
        
        //netSuiteCallBackProvisioningRequest.setResponse(errorDescription);
        netSuiteCallBackProvisioningRequest
                .setCarrierOrderNumber(midWayTransactionId);
        netSuiteCallBackProvisioningRequest.setNetSuiteID("" + netSuiteID);

        StringBuffer deviceIdsArr = new StringBuffer("{\"deviceIds\":");

        deviceIdsArr.append(midWayTransactionDeviceNumber);

        String str = "}";

        deviceIdsArr.append(str);

        ObjectMapper mapper = new ObjectMapper();

        Devices devices = mapper.readValue(deviceIdsArr.toString(),
                Devices.class);

        DeviceId[] deviceIds = devices.getDeviceIds();

        netSuiteCallBackProvisioningRequest.setDeviceIds(deviceIds);

        LOGGER.info("body set for netSuiteCallBack........."
                + exchange.getIn().getBody());

        String oauthConsumerKey = newEnv
                .getProperty("netSuite.oauthConsumerKey");
        String oauthTokenId = newEnv.getProperty("netSuite.oauthTokenId");
        String oauthTokenSecret = newEnv
                .getProperty("netSuite.oauthTokenSecret");
        String oauthConsumerSecret = newEnv
                .getProperty("netSuite.oauthConsumerSecret");
        String realm = newEnv.getProperty("netSuite.realm");
        String endPoint = newEnv.getProperty("netSuite.endPoint");

        String script;
        String oauthHeader = null;

        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");

        LOGGER.info("request type for NetSuite CallBack error...." + requestType);

        LOGGER.info("oauth info is....." + oauthConsumerKey + " " + oauthTokenId
                + " " + endPoint + " " + oauthTokenSecret + " "
                + oauthConsumerSecret + " " + realm);

        script = "539";
        exchange.setProperty("script", script);

        switch (requestType) {
        case ACTIVATION:
            netSuiteCallBackProvisioningRequest
                    .setRequestType(NetSuiteRequestType.ACTIVATION);
       
            break;
        case DEACTIVATION:
            netSuiteCallBackProvisioningRequest
                    .setRequestType(NetSuiteRequestType.DEACTIVATION);
          
            break;
        case REACTIVATION:
            netSuiteCallBackProvisioningRequest
                    .setRequestType(NetSuiteRequestType.REACTIVATION);
          
            break;
        case RESTORE:
            netSuiteCallBackProvisioningRequest
                    .setRequestType(NetSuiteRequestType.RESTORATION);
         
            break;
        case SUSPEND:
            netSuiteCallBackProvisioningRequest
                    .setRequestType(NetSuiteRequestType.SUSPENSION);
          
            break;
        case CHANGESERVICEPLAN:
            ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest = (ChangeDeviceServicePlansRequest) body;
            LOGGER.info("change devcie servcie plan data area...."
                    + changeDeviceServicePlansRequest.getDataArea().toString());
            String oldServicePlan = changeDeviceServicePlansRequest
                    .getDataArea().getCurrentServicePlan();
            String newServicePlan = changeDeviceServicePlansRequest
                    .getDataArea().getServicePlan();
            LOGGER.info("service plan new is..." + newServicePlan
                    + " old service plan is....." + oldServicePlan);
            netSuiteCallBackProvisioningRequest
                    .setRequestType(NetSuiteRequestType.SERVICE_PLAN);
            netSuiteCallBackProvisioningRequest
                    .setOldServicePlan(oldServicePlan);
            netSuiteCallBackProvisioningRequest
                    .setNewServicePlan(newServicePlan);
          
            break;
        case CHANGECUSTOMFIELDS:
            netSuiteCallBackProvisioningRequest
                    .setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);
           
            break;
        default:
            break;
        }

        oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint,
                oauthConsumerKey, oauthTokenId, oauthTokenSecret,
                oauthConsumerSecret, realm, script);
        
        message.setHeader("Authorization", oauthHeader);
        exchange.setProperty("script", script);
        message.setHeader(Exchange.HTTP_PATH, null);
        message.setBody(netSuiteCallBackProvisioningRequest);
        exchange.setPattern(ExchangePattern.InOut);
        LOGGER.info("error callback resposne for ATT Jasper..."
                + exchange.getIn().getBody());
        
        LOGGER.info("End:AttCallBackErrorPostProcessor");
    }

}
