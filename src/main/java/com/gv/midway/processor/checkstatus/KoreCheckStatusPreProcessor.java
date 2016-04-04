package com.gv.midway.processor.checkstatus;

import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreCheckStatusPreProcessor implements Processor {
	
	Logger log = Logger.getLogger(KoreCheckStatusPreProcessor.class
			.getName());

	private Environment newEnv;
	
	public KoreCheckStatusPreProcessor() {

	}

	
	public KoreCheckStatusPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		
       System.out.println("*************Testing**************************************"+ exchange.getIn().getBody());
		
		Message message = exchange.getIn();

		Transaction transaction= exchange.getIn().getBody(Transaction.class);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String carrierTransationID=mapper.readValue(transaction.getCarrierTransationID(),String.class);
		
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("trackingNumber", carrierTransationID);
	
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,transaction.getDeviceNumber());
	
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/queryProvisioningRequest");

		message.setBody(obj);
		
		
		
		
		
		
	}

}
