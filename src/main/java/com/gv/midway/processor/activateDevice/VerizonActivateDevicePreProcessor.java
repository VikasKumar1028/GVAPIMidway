package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;


public class VerizonActivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonActivateDevicePreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonActivateDevicePreProcessor");

		System.out.println("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		System.out.println("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		ActivateDeviceRequest req = (ActivateDeviceRequest) exchange
				.getIn().getBody();
		String accountName =req.getDataArea().getAccountName();
		System.out.println("Account Number"+req.getDataArea().getAccountName());
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("accountName", accountName);

		System.out.println("req.getDataArea()" + req.getDataArea());
		String json = "{\"accountName\":\"TestAccount-1\"}";

		exchange.getIn().setBody(obj);
		Message message = exchange.getIn();

		// message.setHeader("VZ-M2M-Token","1d1f8e7a");
		// message.setHeader("Authorization","Bearer 12");
		message.setHeader("VZ-M2M-Token",
				"1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		message.setHeader("Authorization",
				"Bearer 89ba225e1438e95bd05c3cc288d3591");
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/activate");

		
		
		
	}

}
