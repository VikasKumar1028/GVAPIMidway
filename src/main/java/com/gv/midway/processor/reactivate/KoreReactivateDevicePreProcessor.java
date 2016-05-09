package com.gv.midway.processor.reactivate;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequestKore;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePreProcessor;

public class KoreReactivateDevicePreProcessor implements Processor {
	Logger log = Logger.getLogger(KoreActivateDevicePreProcessor.class.getName());

	Environment newEnv;

	public KoreReactivateDevicePreProcessor() {
	}

	public KoreReactivateDevicePreProcessor(Environment env) {
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		log.info("*************Testing********" + exchange.getIn().getBody());

		log.info("Start:KoreReActivateDevicePreProcessor");
		Message message = exchange.getIn();

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		ReactivateDeviceRequest reActivateDeviceRequest = (ReactivateDeviceRequest) transaction.getDevicePayload();

		String deviceId = reActivateDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()[0].getId();

		ReactivateDeviceRequestKore reActicationDeviceRequestKore = new ReactivateDeviceRequestKore();
		reActicationDeviceRequestKore.setDeviceNumber(deviceId);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
		
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization", newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/reactivateDevice");
		exchange.setPattern(ExchangePattern.InOut);


		message.setBody(reActicationDeviceRequestKore);

		exchange.setPattern(ExchangePattern.InOut);

		log.info("End:KoreReactivateDevicePreProcessor");

	}

}
