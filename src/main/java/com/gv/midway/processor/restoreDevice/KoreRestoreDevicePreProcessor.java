package com.gv.midway.processor.restoreDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.restoreDevice.kore.request.RestoreDeviceRequestKore;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePreProcessor;

public class KoreRestoreDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreActivateDevicePreProcessor.class
			.getName());

	Environment newEnv;

	public KoreRestoreDevicePreProcessor() {

	}

	public KoreRestoreDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		log.info("Start:KoreRestoreDevicePreProcessor.."
				+ exchange.getIn().getBody());

		Message message = exchange.getIn();

	
		Transaction transaction = exchange.getIn().getBody(Transaction.class);
	
		RestoreDeviceRequest restoreDeviceRequest = (RestoreDeviceRequest)
				transaction.getDevicePayload();
	
				
		String deviceId = restoreDeviceRequest.getDataArea().getDevices()[0]
				.getDeviceIds()[0].getId();

		
		RestoreDeviceRequestKore restoreDeviceRequestKore = new RestoreDeviceRequestKore();
		restoreDeviceRequestKore.setDeviceNumber(deviceId);
		
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/restoreDevice");

		message.setBody(restoreDeviceRequestKore);

		log.info("End:KoreRestoreDevicePreProcessor");
	}

}
