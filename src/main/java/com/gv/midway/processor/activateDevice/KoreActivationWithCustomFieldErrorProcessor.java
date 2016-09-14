package com.gv.midway.processor.activateDevice;

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
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;

public class KoreActivationWithCustomFieldErrorProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */

	private static final Logger LOGGER = Logger
			.getLogger(KoreActivationWithCustomFieldErrorProcessor.class
					.getName());

	private Environment newEnv;

	public KoreActivationWithCustomFieldErrorProcessor() {
		// Empty Constructor
	}

	public KoreActivationWithCustomFieldErrorProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		 LOGGER.info("KoreActivationWithCustomFieldErrorProcessor..........");
		 
		 Message message = exchange.getIn();
		 
		 String midWayTransactionDeviceNumber = (String) exchange
	                .getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);

	     String midWayTransactionId = (String) exchange
	                .getProperty(IConstant.MIDWAY_TRANSACTION_ID);

	     Integer netSuiteID = (Integer) exchange
	                .getProperty(IConstant.MIDWAY_NETSUITE_ID);

		 
		KafkaNetSuiteCallBackError netSuiteCallBackError=(KafkaNetSuiteCallBackError) exchange.getProperty(IConstant.KAFKA_OBJECT);
		
		netSuiteCallBackError.setId(RequestType.CHANGECUSTOMFIELDS.toString());
		netSuiteCallBackError.setTimestamp(new Date().getTime());
		
		String errorDescription = netSuiteCallBackError.getException();
		
		String desc = "Error in callBack from Kore For "
	                + midWayTransactionDeviceNumber + ", transactionId "
	                + midWayTransactionId + "and request Type is " + RequestType.CHANGECUSTOMFIELDS;

	    netSuiteCallBackError.setDesc(desc);

	    Object body = exchange
	                .getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD);

	    netSuiteCallBackError.setBody(body);
	    
	    exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);
		
		NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = new NetSuiteCallBackProvisioningRequest();

		netSuiteCallBackProvisioningRequest.setStatus("fail");
		netSuiteCallBackProvisioningRequest.setResponse(errorDescription);
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

		netSuiteCallBackProvisioningRequest
				.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);
		
		
		String oauthConsumerKey = newEnv
				.getProperty("netSuite.oauthConsumerKey");
		String oauthTokenId = newEnv.getProperty("netSuite.oauthTokenId");
		String oauthTokenSecret = newEnv
				.getProperty("netSuite.oauthTokenSecret");
		String oauthConsumerSecret = newEnv
				.getProperty("netSuite.oauthConsumerSecret");
		String relam = newEnv.getProperty("netSuite.Relam");
		String endPoint = newEnv.getProperty("netSuite.endPoint");

		String script = "539";
		
		LOGGER.info("request type for NetSuite CallBack error...."
				+RequestType.CHANGECUSTOMFIELDS);

		LOGGER.info("oauth info is....." + oauthConsumerKey + " "
				+ oauthTokenId + " " + endPoint + " " + oauthTokenSecret + " "
				+ oauthConsumerSecret + " " + relam);
		
		script = "539";
		
		String oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint,
                 oauthConsumerKey, oauthTokenId, oauthTokenSecret,
                 oauthConsumerSecret, relam, script);

		
		exchange.setProperty("script", script);

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		
		message.setHeader("Authorization", oauthHeader);
		exchange.setProperty("script", script);
		message.setHeader(Exchange.HTTP_PATH, null);
		message.setBody(netSuiteCallBackProvisioningRequest);
		exchange.setPattern(ExchangePattern.InOut);
		
	    LOGGER.info("error callback resposne to Kore for activation with Custom Field..."
	                + exchange.getIn().getBody());
	}
}
