package com.gv.midway.processor.suspendDevice;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.suspendDevice.kore.request.SuspendDeviceRequestKore;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreSuspendDevicePreProcessor implements Processor {

	Logger log = Logger
			.getLogger(KoreSuspendDevicePreProcessor.class.getName());

	Environment newEnv;

	public KoreSuspendDevicePreProcessor() {

	}

	public KoreSuspendDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		log.info("*************Testing**************************************"
				+ exchange.getIn().getBody());

		log.info("Begin:KoreSuspendDevicePreProcessor");
		Message message = exchange.getIn();

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		SuspendDeviceRequest suspendDeviceRequest = (SuspendDeviceRequest) transaction
				.getDevicePayload();

		String deviceId = suspendDeviceRequest.getDataArea().getDevices()[0]
				.getDeviceIds()[0].getId();

		SuspendDeviceRequestKore suspendDeviceRequestKore = new SuspendDeviceRequestKore();
		suspendDeviceRequestKore.setDeviceNumber(deviceId);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/suspendDevice");

		message.setBody(suspendDeviceRequestKore);

		exchange.setPattern(ExchangePattern.InOut);
		log.info("End:KoreSuspendDevicePreProcessor");
	}

}
