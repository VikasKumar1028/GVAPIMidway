package com.gv.midway.processor.callbacks;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.callback.common.response.CallbackCommonResponse;
import com.gv.midway.pojo.callback.common.response.CallbackCommonResponseDataArea;
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

		CallbackCommonResponse callbackResponse = new CallbackCommonResponse();
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

		exchange.getIn().setBody(callbackResponse);
	}

}
