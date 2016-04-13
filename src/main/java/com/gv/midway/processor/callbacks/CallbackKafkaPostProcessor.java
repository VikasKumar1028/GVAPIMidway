package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.callback.NetsuitDeviceResponseDataArea;
import com.gv.midway.pojo.callback.NetsuitGenericResponse;

public class CallbackKafkaPostProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		
		System.out.println("Inside CallbackKafkaPostProcessor process " + exchange.getIn().getBody());
		System.out.println("Exchange inside" + exchange.getIn().getBody().toString());

		CallBackVerizonRequest req = (CallBackVerizonRequest) exchange.getIn().getBody(CallBackVerizonRequest.class);

		NetsuitGenericResponse netsuitResponse = new NetsuitGenericResponse();

		NetsuitDeviceResponseDataArea netsuitDeviceResponseDataArea = new NetsuitDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		/*
		 * responseheader.setApplicationName(exchange.getProperty(IConstant.
		 * APPLICATION_NAME).toString());
		 * responseheader.setRegion(exchange.getProperty
		 * (IConstant.REGION).toString());
		 * 
		 * responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT
		 * ).toString());
		 * responseheader.setOrganization(exchange.getProperty(IConstant
		 * .ORGANIZATION).toString());
		 * 
		 * responseheader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME
		 * ).toString());
		 * responseheader.setBsCarrier(exchange.getProperty(IConstant
		 * .BSCARRIER).toString());
		 * responseheader.setTransactionId(exchange.getProperty
		 * (IConstant.GV_TRANSACTION_ID).toString());
		 */
		String midwayTransactionId = (String) exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID);

		netsuitDeviceResponseDataArea.setOrderNumber(midwayTransactionId);

		netsuitResponse.setResponse(response);
		netsuitResponse.setHeader(responseheader);
		netsuitResponse.setDataArea(netsuitDeviceResponseDataArea);

		exchange.getIn().setBody(netsuitResponse);
	}

}
