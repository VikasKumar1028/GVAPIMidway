package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.kore.request.ActivateDeviceRequestKore;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.verizon.request.ActivateDeviceRequestVerizon;
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
		log.info("*************Testing**************************************"
				+ exchange.getIn().getBody());

		log.info("Start:KoreActivateDevicePreProcessor");
		Message message = exchange.getIn();

	
		Transaction transaction = exchange.getIn().getBody(Transaction.class);
	
		ActivateDeviceRequest activateDeviceRequest = (ActivateDeviceRequest)
				transaction.getDevicePayload();
	
				
		String deviceId = activateDeviceRequest.getDataArea().getDevices()[0]
				.getDeviceIds()[0].getId();
/*		String eAPCode = activateDeviceRequest.getDataArea().getDevices()[0]
				.getDeviceIds()[0].geteAPCode();*/
		String eAPCode = activateDeviceRequest.getDataArea().geteAPCode();
		
		
		ActivateDeviceRequestKore acticationDeviceRequestKore = new ActivateDeviceRequestKore();
		acticationDeviceRequestKore.setDeviceNumber(deviceId);
		acticationDeviceRequestKore.setEapCode(eAPCode);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/activateDevice");

		message.setBody(acticationDeviceRequestKore);

		log.info("End:KoreActivateDevicePreProcessor");
	}

}
