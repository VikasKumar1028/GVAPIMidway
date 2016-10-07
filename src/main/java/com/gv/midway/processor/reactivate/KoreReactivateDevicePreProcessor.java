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

public class KoreReactivateDevicePreProcessor implements Processor {
	private static final Logger LOGGER = Logger
			.getLogger(KoreReactivateDevicePreProcessor.class.getName());

	Environment newEnv;

	public KoreReactivateDevicePreProcessor() {
		// Empty Constructor
	}

	public KoreReactivateDevicePreProcessor(Environment env) {
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:KoreReActivateDevicePreProcessor");
		LOGGER.info("*************Testing********" + exchange.getIn().getBody());

		Message message = exchange.getIn();

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		ReactivateDeviceRequest reActivateDeviceRequest = (ReactivateDeviceRequest) transaction
				.getDevicePayload();

		String deviceId = reActivateDeviceRequest.getDataArea().getDevices()[0]
				.getDeviceIds()[0].getId();

		ReactivateDeviceRequestKore reActicationDeviceRequestKore = new ReactivateDeviceRequestKore();
		reActicationDeviceRequestKore.setDeviceNumber(deviceId);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/reactivateDevice");
		exchange.setPattern(ExchangePattern.InOut);

		message.setBody(reActicationDeviceRequestKore);

		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:KoreReactivateDevicePreProcessor");

	}

}
