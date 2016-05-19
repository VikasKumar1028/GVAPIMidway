package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.changeDeviceServicePlans.kore.request.ChangeDeviceServicePlansRequestKore;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreChangeDeviceServicePlansPreProcessor implements Processor {

	Logger log = Logger
			.getLogger(KoreChangeDeviceServicePlansPreProcessor.class.getName());
	Environment newEnv;

	public KoreChangeDeviceServicePlansPreProcessor() {

	}

	public KoreChangeDeviceServicePlansPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Begin::KoreChangeDeviceServicePlansPreProcessor");

		Message message = exchange.getIn();

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest = (ChangeDeviceServicePlansRequest) transaction
				.getDevicePayload();

		String deviceId = changeDeviceServicePlansRequest.getDataArea()
				.getDevices()[0].getDeviceIds()[0].getId();
		String planCode = changeDeviceServicePlansRequest.getDataArea()
				.getServicePlan();

		ChangeDeviceServicePlansRequestKore changeDeviceServicePlansRequestKore = new ChangeDeviceServicePlansRequestKore();
		changeDeviceServicePlansRequestKore.setDeviceNumber(deviceId);
		changeDeviceServicePlansRequestKore.setPlanCode(planCode);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");

		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH,
				"/json/modifyDevicePlanForNextPeriod");
		message.setBody(changeDeviceServicePlansRequestKore);
		exchange.setPattern(ExchangePattern.InOut);

		log.info("End::KoreChangeDeviceServicePlansPreProcessor");
	}

}
