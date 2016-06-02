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
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackError;
import com.gv.midway.pojo.kore.KoreErrorResponse;


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

		RequestType requestType = (RequestType) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);

		String errorDescription = (String) exchange
				.getProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC);

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

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
		
		NetSuiteCallBackError netSuiteCallBackError =new NetSuiteCallBackError();
		
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
		
		keyValues2.setK("midwayTransactionId");
		keyValues2.setV(midWayTransactionId);
		
		KeyValues[] keyValuesArr=new KeyValues[2];
		
		keyValuesArr[0]=keyValues1;
		keyValuesArr[1]=keyValues2;
		
		netSuiteCallBackError.setKeyValues(keyValuesArr);
		
		
		message.setBody(netSuiteCallBackError);
		
		

	}

}
