package com.gv.midway.processor.checkstatus;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.callback.common.response.CallbackCommonResponse;
import com.gv.midway.pojo.callback.common.response.CallbackCommonResponseDataArea;
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreCheckStatusErrorProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */
	
	Logger log = Logger.getLogger(KoreCheckStatusErrorProcessor.class
			.getName());
	
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
		
		String midWayTransactionDeviceNumber=(String)exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);
		
		String midWayTransactionId=(String)exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID);
		
		RequestType requestType=(RequestType)exchange.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);
		
		String errorDescription=(String)exchange.getProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC);
		
		
		CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		
	
		
		if(exception!=null)
		{
			
			 log.info("cxf exception while checking the status of Kore Provisoning request");
			String errorResponseBody = exception.getResponseBody();
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				KoreErrorResponse errorResponsePayload = mapper.readValue(errorResponseBody, KoreErrorResponse.class);
				errorDescription=errorResponsePayload.getErrorMessage();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		CallbackCommonResponse callbackCommonResponse= new CallbackCommonResponse();
		
		Header header=(Header)exchange.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_HEADER);
		
		
		
		Response response=new Response();
		
		response.setResponseCode(IResponse.NETSUITE_CALLBACK_ERRORCODE);
		response.setResponseStatus(IResponse.ERROR_MESSAGE);
		response.setResponseDescription(errorDescription);
		
		CallbackCommonResponseDataArea callbackCommonResponseDataArea=new CallbackCommonResponseDataArea();
		
		callbackCommonResponseDataArea.setRequestId(midWayTransactionId);
		callbackCommonResponseDataArea.setRequestType(requestType);
		callbackCommonResponseDataArea.setRequestStatus(false);
		
		List<DeviceId> deviceIdlist = new ObjectMapper().readValue(midWayTransactionDeviceNumber,
				TypeFactory.defaultInstance().constructCollectionType(List.class,  
						DeviceId.class));
		
		DeviceId[] deviceIds = new DeviceId[deviceIdlist.size()];
		deviceIds = deviceIdlist.toArray(deviceIds);
		
		callbackCommonResponseDataArea.setDeviceIds(deviceIds);
		
		callbackCommonResponse.setHeader(header);
		callbackCommonResponse.setResponse(response);
		
	    callbackCommonResponse.setDataArea(callbackCommonResponseDataArea);
		
	  /*  message.setHeader(Exchange.CONTENT_TYPE, "application/json");
	
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		
		message.setHeader(Exchange.HTTP_PATH, "/netSuite/EndPoint");

		message.setBody(callbackCommonResponse);*/
		
	  
	}

}
