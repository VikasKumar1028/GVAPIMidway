package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gv.midway.pojo.callback.TargetDeviceResponseDataArea;
import com.gv.midway.pojo.callback.TargetResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;

public class CallbackPreProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

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
