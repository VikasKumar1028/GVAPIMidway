package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.kore.request.ActivateDeviceRequestKore;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreActivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreActivateDevicePreProcessor.class
			.getName());

	Environment newEnv;

	public KoreActivateDevicePreProcessor() {
		//Empty Constructor
	}

	public KoreActivateDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		

		log.info("Begin:KoreActivateDevicePreProcessor");
		
		log.info("*************Testing**************************************"
				+ exchange.getIn().getBody());
		
		Message message = exchange.getIn();

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		ActivateDeviceRequest activateDeviceRequest = (ActivateDeviceRequest) transaction
				.getDevicePayload();

		String deviceId = activateDeviceRequest.getDataArea().getDevices()
				.getDeviceIds()[0].getId();

		String EAPCode = activateDeviceRequest.getDataArea().getDevices().getServicePlan();

		ActivateDeviceRequestKore acticationDeviceRequestKore = new ActivateDeviceRequestKore();
		acticationDeviceRequestKore.setDeviceNumber(deviceId);
		acticationDeviceRequestKore.setEAPCode(EAPCode);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/activateDevice");

		message.setBody(acticationDeviceRequestKore);

		exchange.setPattern(ExchangePattern.InOut);

		log.info("End:KoreActivateDevicePreProcessor");

	}

}
