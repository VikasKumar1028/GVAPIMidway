package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreActivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreActivateDevicePreProcessor.class
			.getName());

	Environment newEnv;

	public KoreActivateDevicePreProcessor() {

	}

	public KoreActivateDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}
	
	
	public void process(Exchange exchange) throws Exception {
		System.out.println("*************Testing**************************************"+ exchange.getIn().getBody());
		
		
		log.info("Start:KoreDeactivateDevicePreProcessor");
		Message message = exchange.getIn();

		Transaction transaction= exchange.getIn().getBody(Transaction.class);
		
		ObjectMapper mapper = new ObjectMapper();
	
		ActivateDeviceRequest activateDeviceRequest=mapper.readValue(transaction.getDevicePayload(),ActivateDeviceRequest.class);
	
			
	
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/activateDevice");

		message.setBody(activateDeviceRequest);
		
		log.info("End:KoreDeactivateDevicePreProcessor");
	}

	/*public void process(Exchange exchange) throws Exception {

		log.info("Start:KoreActivateDevicePreProcessor.java");
		
		
		System.out.println("**********************************************************************");

		ActivateDeviceRequest activateDeviceRequest = exchange.getIn().getBody(
				ActivateDeviceRequest.class);
		System.out.println("activateDeviceRequest"
				+ activateDeviceRequest.toString());

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		ObjectMapper mapper = new ObjectMapper();

		ActivateDeviceRequest activateDeviceRequest = mapper.readValue(
				transaction.getPayload(), ActivateDeviceRequest.class);

		//activateDeviceRequest.getDataArea().getDeviceId();
		
		 * String deviceIds =
		 * activateDeviceRequest.getDataArea().getDeviceId()[0].getId();
		 * //String deviceIds ="89148000000800139708"; String eAPCode
		 * =activateDeviceRequest.getDataArea().geteAPCode();
		 * 
		 * net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		 * obj.put("deviceNumber", deviceIds);
		 
		// obj.put("EAPCode", eAPCode);

		Message message = exchange.getIn();

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/activateDevice");
		message.setBody(activateDeviceRequest);

		log.info("End:KoreActivateDevicePreProcessor.java");

	}
*/
}
