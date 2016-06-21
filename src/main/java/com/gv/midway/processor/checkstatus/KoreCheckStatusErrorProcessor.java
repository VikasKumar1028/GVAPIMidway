package com.gv.midway.processor.checkstatus;

import java.util.Date;

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
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;


public class KoreCheckStatusErrorProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */

	Logger log = Logger
			.getLogger(KoreCheckStatusErrorProcessor.class.getName());

	private Environment newEnv;

	public KoreCheckStatusErrorProcessor() {

	}

	public KoreCheckStatusErrorProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("kore check status error processor");

		Message message = exchange.getIn();

		String midWayTransactionDeviceNumber = (String) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);

		String midWayTransactionId = (String) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_ID);

		String netSuiteID = (String) exchange
				.getProperty(IConstant.MIDWAY_NETSUITE_ID);
		
		RequestType requestType = (RequestType) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);

		String errorDescription = (String) exchange
				.getProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC);
		
		log.info("cxf operation not caught before is...........");

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);
		
		log.info("cxf operation caught is...........");

		if (exception != null) {

			log.info("cxf exception while checking the status of Kore Provisoning request");
			String errorResponseBody = exception.getResponseBody();
			ObjectMapper mapper = new ObjectMapper();

			try {
				KoreErrorResponse errorResponsePayload = mapper.readValue(
						errorResponseBody, KoreErrorResponse.class);
				errorDescription = errorResponsePayload.getErrorMessage();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/*CallbackCommonResponse callbackCommonResponse = new CallbackCommonResponse();

		Header header = (Header) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_HEADER);

		Response response = new Response();

		response.setResponseCode(IResponse.NETSUITE_CALLBACK_ERRORCODE);
		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(errorDescription);

		CallbackCommonResponseDataArea callbackCommonResponseDataArea = new CallbackCommonResponseDataArea();

		callbackCommonResponseDataArea.setRequestId(midWayTransactionId);
		callbackCommonResponseDataArea.setRequestType(requestType);
		callbackCommonResponseDataArea.setRequestStatus(false);

		List<DeviceId> deviceIdlist = new ObjectMapper().readValue(
				midWayTransactionDeviceNumber, TypeFactory.defaultInstance()
						.constructCollectionType(List.class, DeviceId.class));

		DeviceId[] deviceIds = new DeviceId[deviceIdlist.size()];
		deviceIds = deviceIdlist.toArray(deviceIds);

		callbackCommonResponseDataArea.setDeviceIds(deviceIds);

		callbackCommonResponse.setHeader(header);
		callbackCommonResponse.setResponse(response);

		callbackCommonResponse.setDataArea(callbackCommonResponseDataArea);*/
		
		KafkaNetSuiteCallBackError netSuiteCallBackError =new KafkaNetSuiteCallBackError();
		
		netSuiteCallBackError.setApp("Midway");
		netSuiteCallBackError.setCategory("Kore Call Back Error");
		netSuiteCallBackError.setId(requestType.toString());
		netSuiteCallBackError.setLevel("Error");
		netSuiteCallBackError.setTimestamp(new Date().getTime());
		netSuiteCallBackError.setVersion("1");
		netSuiteCallBackError.setException(errorDescription);
		netSuiteCallBackError.setMsg("Error in Call Back from Kore.");
		
        String desc="Error in callBack from Kore For "+midWayTransactionDeviceNumber +", transactionId "+midWayTransactionId +"and request Type is "+requestType;
		
		netSuiteCallBackError.setDesc(desc);
		
		Object body = exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD);
		
		netSuiteCallBackError.setBody(body);
		
		BaseRequest baseRequest=(BaseRequest) body;
		
		Header header= baseRequest.getHeader();
		
		KeyValues keyValues1=new KeyValues();
		
		keyValues1.setK("transactionId");
		keyValues1.setV(header.getTransactionId());
		
		KeyValues keyValues2=new KeyValues();
		
		keyValues2.setK("orderNumber");
		keyValues2.setV(midWayTransactionId);
		
		KeyValues keyValues3=new KeyValues();
			
	    keyValues3.setK("deviceIds");
	    keyValues3.setV(midWayTransactionDeviceNumber.replace("'\'", ""));
	    
        KeyValues keyValues4=new KeyValues();
		
	    keyValues4.setK("netSuiteID");
	    keyValues4.setV(netSuiteID);
		
		KeyValues[] keyValuesArr=new KeyValues[4];
		
		keyValuesArr[0]=keyValues1;
		keyValuesArr[1]=keyValues2;
		keyValuesArr[2]=keyValues3;
		keyValuesArr[3]=keyValues4;
		
		netSuiteCallBackError.setKeyValues(keyValuesArr);
		
		exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);
		
		NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest =new NetSuiteCallBackProvisioningRequest();
		
		//netSuiteCallBackProvisioningRequest.setRequestType(requestType);
		netSuiteCallBackProvisioningRequest.setStatus("fail");
		netSuiteCallBackProvisioningRequest.setResponse(errorDescription);
		netSuiteCallBackProvisioningRequest.setCarrierOrderNumber(midWayTransactionId);
		netSuiteCallBackProvisioningRequest.setNetSuiteID(netSuiteID);
		
		
	    StringBuffer deviceIdsArr= new StringBuffer("{\"deviceIds\":");
		
	    deviceIdsArr.append(midWayTransactionDeviceNumber);
		
		String str="}";
		
		deviceIdsArr.append(str);
		
		ObjectMapper mapper=new ObjectMapper();
		
        Devices devices = mapper.readValue(deviceIdsArr.toString(),Devices.class);
		
		DeviceId[] deviceIds=devices.getDeviceIds();
		
		netSuiteCallBackProvisioningRequest.setDeviceIds(deviceIds);
		
		message.setBody(netSuiteCallBackProvisioningRequest);
		
		log.info("body set for netSuiteCallBack........."+exchange.getIn().getBody());
		
		String oauthConsumerKey = newEnv
				.getProperty("netSuite.oauthConsumerKey");
		String oauthTokenId = newEnv.getProperty("netSuite.oauthTokenId");
		String oauthTokenSecret = newEnv
				.getProperty("netSuite.oauthTokenSecret");
		String oauthConsumerSecret = newEnv
				.getProperty("netSuite.oauthConsumerSecret");
		String relam = newEnv.getProperty("netSuite.Relam");
		String endPoint = newEnv.getProperty("netSuite.endPoint");
		
		String script=null;
	    String oauthHeader=null;
	    
	    message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		
		log.info("request type for NetSuite CallBack error...."+requestType);
		
		log.info("oauth info is....."+oauthConsumerKey+" "+oauthTokenId+" "+endPoint+" "+oauthTokenSecret+" "+oauthConsumerSecret+" "+relam);
		
		script="539";
		exchange.setProperty("script", script);
	    
	    switch (requestType) {
		case ACTIVATION:

			
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.ACTIVATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		case DEACTIVATION:

			
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.DEACTIVATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		case REACTIVATION:

			
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.REACTIVATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		case RESTORE:

			
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.RESTORATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		case SUSPEND:

			
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SUSPENSION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		/*case CHANGESERVICEPLAN:

			
			break;

		case CHANGECUSTOMFIELDS:

			

			break;*/

		default:
			break;
		}
		
	    message.setHeader("Authorization", oauthHeader);
	    exchange.setProperty("script", script);
	    message.setHeader(Exchange.HTTP_PATH, null);
	   exchange.setPattern(ExchangePattern.InOut);
		log.info("error callback resposne for Kore..."+exchange.getIn().getBody());

	}

}
