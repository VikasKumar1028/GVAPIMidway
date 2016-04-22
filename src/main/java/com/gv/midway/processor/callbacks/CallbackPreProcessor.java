package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.callback.TargetDeviceResponseDataArea;
import com.gv.midway.pojo.callback.TargetResponse;
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
		CallBackVerizonRequest req = (CallBackVerizonRequest) exchange.getIn().getBody(CallBackVerizonRequest.class);

		TargetResponse targetResponse = new TargetResponse();

		TargetDeviceResponseDataArea targetDeviceResponseDataArea = new TargetDeviceResponseDataArea();


		targetDeviceResponseDataArea.setRequestId(req.getRequestId());
		targetDeviceResponseDataArea.setDeviceIds(req.getDeviceIds());
		targetDeviceResponseDataArea.setCallbackDeviceResponse(req.getDeviceResponse());

		targetResponse.setDataArea(targetDeviceResponseDataArea);

		exchange.getIn().setBody(targetResponse);
	}

}
