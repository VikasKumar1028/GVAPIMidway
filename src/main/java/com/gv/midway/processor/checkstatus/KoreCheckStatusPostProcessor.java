package com.gv.midway.processor.checkstatus;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackEvent;


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
		
	    NetSuiteCallBackEvent netSuiteCallBackEvent =new NetSuiteCallBackEvent();
		
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
		
		keyValues2.setK("midwayTransactionId");
		keyValues2.setV(midWayTransactionId);
		
		KeyValues[] keyValuesArr=new KeyValues[2];
		
		keyValuesArr[0]=keyValues1;
		keyValuesArr[1]=keyValues2;
		
		netSuiteCallBackEvent.setKeyValues(keyValuesArr);
		
		message.setBody(netSuiteCallBackEvent);
		
		

	}

}
