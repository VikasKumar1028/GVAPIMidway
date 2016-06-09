package com.gv.midway.processor.callbacks;


import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;

public class CallbackPreProcessor implements Processor {
	Logger log = Logger.getLogger(CallbackPreProcessor.class);
	Environment newEnv;

	public CallbackPreProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {
		/**
		 * formatting the verizon callback response to expected target response
		 * 
		 * 
		 * */
		log.info(" Inside CallbackPreProcessor ");
		CallBackVerizonRequest req = (CallBackVerizonRequest) exchange.getIn()
				.getBody(CallBackVerizonRequest.class);

	/*	CallbackCommonResponse callbackResponse = new CallbackCommonResponse();
		CallbackCommonResponseDataArea callbackResponseDataArea = new CallbackCommonResponseDataArea();

		callbackResponseDataArea.setRequestId(req.getRequestId());
		callbackResponseDataArea.setDeviceIds(req.getDeviceIds());
		Response res = new Response();

		
		if(req.getFaultResponse() != null) {

			res.setResponseCode(401);
			res.setResponseStatus(IResponse.ERROR_MESSAGE);
			res.setResponseDescription(req.getFaultResponse().getFaultstring());
			callbackResponseDataArea.setRequestStatus(false);

		} else {
			res.setResponseCode(2000);
			res.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			res.setResponseDescription(req.getComment());
			callbackResponseDataArea.setRequestStatus(true);

		}
		callbackResponse.setResponse(res);
		callbackResponse.setDataArea(callbackResponseDataArea);

		exchange.getIn().setBody(callbackResponse);*/
		
		exchange.setProperty(IConstant.VERIZON_CALLBACK_RESPONE,
				req);
		
		NetSuiteCallBackProvisioningResponse netSuiteCallBackProvisioningResponse=new NetSuiteCallBackProvisioningResponse();
		netSuiteCallBackProvisioningResponse.setDeviceIds(req.getDeviceIds());
		
		
		
		if(req.getFaultResponse() != null) {
			
			netSuiteCallBackProvisioningResponse.setStatus("fail");
			netSuiteCallBackProvisioningResponse.setResponse(req.getFaultResponse().getFaultstring());

			KafkaNetSuiteCallBackError netSuiteCallBackError =new KafkaNetSuiteCallBackError();
			
			netSuiteCallBackError.setApp("Midway");
			netSuiteCallBackError.setCategory("Verizon Call Back Error");
			//netSuiteCallBackError.setId(requestType.toString());
			netSuiteCallBackError.setLevel("Error");
			netSuiteCallBackError.setTimestamp(new Date().getTime());
			netSuiteCallBackError.setVersion("1");
			netSuiteCallBackError.setException(req.getFaultResponse().getFaultstring());
			netSuiteCallBackError.setMsg("Error in Call Back from Verizon.");
			
			exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);

		} 
		
		else {
			
			netSuiteCallBackProvisioningResponse.setStatus("success");
			
			   KafkaNetSuiteCallBackEvent netSuiteCallBackEvent =new KafkaNetSuiteCallBackEvent();
				
			    netSuiteCallBackEvent.setApp("Midway");
			    netSuiteCallBackEvent.setCategory("Verizon Call Back Success");
			   // netSuiteCallBackEvent.setId(requestType.toString());
			    netSuiteCallBackEvent.setLevel("Info");
			    netSuiteCallBackEvent.setTimestamp(new Date().getTime());
			    netSuiteCallBackEvent.setVersion("1");
				
			    netSuiteCallBackEvent.setMsg("Succesfull Call Back from Verizon.");
			    
			    exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackEvent);

		}
		
		exchange.getIn().setBody(netSuiteCallBackProvisioningResponse);
	}

}
