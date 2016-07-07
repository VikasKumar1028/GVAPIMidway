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
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.utility.NetSuiteOAuthUtil;


public class KoreCheckStatusPostProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */

	Logger log = Logger.getLogger(KoreCheckStatusPostProcessor.class.getName());

	private Environment newEnv;

	public KoreCheckStatusPostProcessor() {

	}

	public KoreCheckStatusPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("kore check status post processor");

		Message message = exchange.getIn();

		String midWayTransactionDeviceNumber = (String) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);

		String midWayTransactionId = (String) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_ID);

		String netSuiteID = (String) exchange
				.getProperty(IConstant.MIDWAY_NETSUITE_ID);
		
		RequestType requestType = (RequestType) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);

		/*CallbackCommonResponse callbackCommonResponse = new CallbackCommonResponse();

		Header header = (Header) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_HEADER);

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		switch (requestType) {
		case ACTIVATION:

			response.setResponseDescription("Device Activated Successfully");
			break;

		case DEACTIVATION:

			response.setResponseDescription("Device DeActivated Successfully");

			break;

		case REACTIVATION:

			response.setResponseDescription("Device ReActivated Successfully");

			break;

		case RESTORE:

			response.setResponseDescription("Device ReStored Successfully");

			break;

		case SUSPEND:

			response.setResponseDescription("Device Suspend Successfully");

			break;

		case CHANGESERVICEPLAN:

			response.setResponseDescription("Device Service Plan Changed Successfully.");

			break;

		case CHANGECUSTOMFIELDS:

			response.setResponseDescription("Device Custom Fields Changed Successfully.");

			break;

		default:
			break;
		}

		CallbackCommonResponseDataArea callbackCommonResponseDataArea = new CallbackCommonResponseDataArea();

		callbackCommonResponseDataArea.setRequestId(midWayTransactionId);
		callbackCommonResponseDataArea.setRequestType(requestType);
		callbackCommonResponseDataArea.setRequestStatus(true);

		List<DeviceId> deviceIdlist = new ObjectMapper().readValue(
				midWayTransactionDeviceNumber, TypeFactory.defaultInstance()
						.constructCollectionType(List.class, DeviceId.class));

		DeviceId[] deviceIds = new DeviceId[deviceIdlist.size()];
		deviceIds = deviceIdlist.toArray(deviceIds);

		callbackCommonResponseDataArea.setDeviceIds(deviceIds);

		callbackCommonResponse.setHeader(header);
		callbackCommonResponse.setResponse(response);

		callbackCommonResponse.setDataArea(callbackCommonResponseDataArea);*/
		
	    KafkaNetSuiteCallBackEvent netSuiteCallBackEvent =new KafkaNetSuiteCallBackEvent();
		
	    netSuiteCallBackEvent.setApp("Midway");
	    netSuiteCallBackEvent.setCategory("Kore Call Back Success");
	    netSuiteCallBackEvent.setId(requestType.toString());
	    netSuiteCallBackEvent.setLevel("Info");
	    netSuiteCallBackEvent.setTimestamp(new Date().getTime());
	    netSuiteCallBackEvent.setVersion("1");
		
	    netSuiteCallBackEvent.setMsg("Succesfull Call Back from Kore.");
		
        String desc="Succesfull callBack from Kore For "+midWayTransactionDeviceNumber +", transactionId "+midWayTransactionId +"and request Type is "+requestType;
		
        netSuiteCallBackEvent.setDesc(desc);
		
		Object body = exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD);
		
		netSuiteCallBackEvent.setBody(body);
		
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
		
		
		netSuiteCallBackEvent.setKeyValues(keyValuesArr);
		
		exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackEvent);
		
        NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest =new NetSuiteCallBackProvisioningRequest();
		
		//netSuiteCallBackProvisioningRequest.setRequestType(requestType);
		netSuiteCallBackProvisioningRequest.setStatus("success");
		 
		
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

			netSuiteCallBackProvisioningRequest.setResponse("Device successfully activated.");
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.ACTIVATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			
			break;

		case DEACTIVATION:

			netSuiteCallBackProvisioningRequest.setResponse("Device successfully DeActivated.");
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.DEACTIVATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		case REACTIVATION:

			netSuiteCallBackProvisioningRequest.setResponse("Device successfully ReActivated.");
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.REACTIVATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		case RESTORE:

			netSuiteCallBackProvisioningRequest.setResponse("Device successfully ReStored.");
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.RESTORATION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

		case SUSPEND:

			netSuiteCallBackProvisioningRequest.setResponse("Device successfully Suspended.");
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SUSPENSION);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			

			break;

    	case CHANGESERVICEPLAN:

			netSuiteCallBackProvisioningRequest.setResponse("Device Service Plan Changed successfully.");
			
			ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest=(ChangeDeviceServicePlansRequest)body;
			log.info("change devcie servcie plan data area...."+changeDeviceServicePlansRequest.getDataArea().toString());
			String oldServicePlan=changeDeviceServicePlansRequest.getDataArea().getCurrentServicePlan();
			String newServicePlan=changeDeviceServicePlansRequest.getDataArea().getServicePlan();
			log.info("service plan new is..."+newServicePlan+" old service plan is....."+oldServicePlan);
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SERVICE_PLAN);
			netSuiteCallBackProvisioningRequest.setOldServicePlan(oldServicePlan);
			netSuiteCallBackProvisioningRequest.setNewServicePlan(newServicePlan);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);

			break;

		case CHANGECUSTOMFIELDS:

			netSuiteCallBackProvisioningRequest.setResponse("Device Custom Fields Changed successfully.");
			netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			break;

		default:
			break;
		}
		
		
		message.setHeader("Authorization", oauthHeader);
		message.setBody(netSuiteCallBackProvisioningRequest);
		message.setHeader(Exchange.HTTP_PATH, null);
		exchange.setPattern(ExchangePattern.InOut);
		log.info("success callback resposne for Kore..."+exchange.getIn().getBody());
		

	}

}
