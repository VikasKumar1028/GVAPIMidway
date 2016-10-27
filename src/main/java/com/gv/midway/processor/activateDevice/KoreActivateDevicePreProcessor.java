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

	private static final Logger LOGGER = Logger.getLogger(KoreActivateDevicePreProcessor.class.getName());

	private Environment newEnv;

	public KoreActivateDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:KoreActivateDevicePreProcessor");
		LOGGER.info("*************Testing**************************************" + exchange.getIn().getBody());

		final Message message = exchange.getIn();
		final Transaction transaction = exchange.getIn().getBody(Transaction.class);

		final ActivateDeviceRequest activateDeviceRequest = (ActivateDeviceRequest) transaction.getDevicePayload();
		final String deviceId = activateDeviceRequest.getDataArea().getDevices().getDeviceIds()[0].getId();
		final String EAPCode = activateDeviceRequest.getDataArea().getDevices().getServicePlan();

		final ActivateDeviceRequestKore activationDeviceRequestKore = new ActivateDeviceRequestKore();
		activationDeviceRequestKore.setDeviceNumber(deviceId);
		activationDeviceRequestKore.setEAPCode(EAPCode);

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization", newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/activateDevice");
		message.setBody(activationDeviceRequestKore);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:KoreActivateDevicePreProcessor");
	}
}