package com.gv.midway.processor.customFieldsDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreCustomFieldsPreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreCustomFieldsPreProcessor.class.getName());

	Environment newEnv;

	public KoreCustomFieldsPreProcessor() {

	}

	public KoreCustomFieldsPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Start::KoreCustomFieldsPreProcessor");

		Message message = exchange.getIn();

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		CustomFieldsDeviceRequest changeDeviceServicePlansRequest = (CustomFieldsDeviceRequest) transaction
				.getDevicePayload();

		String deviceId = changeDeviceServicePlansRequest.getDataArea()
				.getDevices()[0].getDeviceIds()[0].getId();
		String customField1 = changeDeviceServicePlansRequest.getDataArea()
				.getCustomField1();
		String customField2 = changeDeviceServicePlansRequest.getDataArea()
				.getCustomField2();
		String customField3 = changeDeviceServicePlansRequest.getDataArea()
				.getCustomField3();
		String customField4 = changeDeviceServicePlansRequest.getDataArea()
				.getCustomField4();
		String customField5 = changeDeviceServicePlansRequest.getDataArea()
				.getCustomField5();

		CustomFieldsDeviceRequestKore customFieldsDeviceRequestKore = new CustomFieldsDeviceRequestKore();
		customFieldsDeviceRequestKore.setDeviceNumber(deviceId);
		customFieldsDeviceRequestKore.setCustomField1(customField1);
		customFieldsDeviceRequestKore.setCustomField2(customField2);
		customFieldsDeviceRequestKore.setCustomField3(customField3);
		customFieldsDeviceRequestKore.setCustomField4(customField4);
		customFieldsDeviceRequestKore.setCustomField5(customField5);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");

		/*
		 * message.setHeader("Authorization",
		 * "Basic Z3JhbnR2aWN0b3JhcGk6akx1Y1dMQ0JxakhQ");
		 */
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/modifyDeviceCustomInfo");
		
		message.setBody(customFieldsDeviceRequestKore);

		log.info("End::KoreCustomFieldsPreProcessor");
	}

}
