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
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;


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
		
        NetSuiteCallBackProvisioningResponse netSuiteCallBackProvisioningResponse =new NetSuiteCallBackProvisioningResponse();
		
		netSuiteCallBackProvisioningResponse.setRequestType(requestType);
		netSuiteCallBackProvisioningResponse.setStatus("success");
		 
		
		netSuiteCallBackProvisioningResponse.setCarrierOrderNumber(midWayTransactionId);
		netSuiteCallBackProvisioningResponse.setNetSuiteID(netSuiteID);
		
		
	    StringBuffer deviceIdsArr= new StringBuffer("{\"deviceIds\":");
		
	    deviceIdsArr.append(midWayTransactionDeviceNumber);
		
		String str="}";
		
		deviceIdsArr.append(str);
		
		ObjectMapper mapper=new ObjectMapper();
		
        Devices devices = mapper.readValue(deviceIdsArr.toString(),Devices.class);
		
		DeviceId[] deviceIds=devices.getDeviceIds();
		
		netSuiteCallBackProvisioningResponse.setDeviceIds(deviceIds);
		
		switch (requestType) {
		case ACTIVATION:

			netSuiteCallBackProvisioningResponse.setResponse("Device successfully activated.");
			break;

		case DEACTIVATION:

			netSuiteCallBackProvisioningResponse.setResponse("Device successfully DeActivated.");

			break;

		case REACTIVATION:

			netSuiteCallBackProvisioningResponse.setResponse("Device successfully ReActivated.");

			break;

		case RESTORE:

			netSuiteCallBackProvisioningResponse.setResponse("Device successfully ReStored.");

			break;

		case SUSPEND:

			netSuiteCallBackProvisioningResponse.setResponse("Device successfully Suspended.");

			break;

		case CHANGESERVICEPLAN:

			netSuiteCallBackProvisioningResponse.setResponse("Device Service Plan Changed successfully.");

			break;

		case CHANGECUSTOMFIELDS:

			netSuiteCallBackProvisioningResponse.setResponse("Device Custom Fields Changed successfully.");

			break;

		default:
			break;
		}
		
		message.setBody(netSuiteCallBackProvisioningResponse);
		
		log.info("success callback resposne for Kore..."+exchange.getIn().getBody());
		

	}

}
