package com.gv.midway.processor.customFieldsDevice;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.pojo.verizon.CustomFields;

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

		log.info("Begin::KoreCustomFieldsPreProcessor");

		Message message = exchange.getIn();

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		CustomFieldsDeviceRequest changeDeviceServicePlansRequest = (CustomFieldsDeviceRequest) transaction
				.getDevicePayload();

		String deviceId = changeDeviceServicePlansRequest.getDataArea()
				.getDevices()[0].getDeviceIds()[0].getId();

		CustomFieldsDeviceRequestKore customFieldsDeviceRequestKore = new CustomFieldsDeviceRequestKore();
		customFieldsDeviceRequestKore.setDeviceNumber(deviceId);

		CustomFields[] customFieldsArr = changeDeviceServicePlansRequest
				.getDataArea().getCustomFields();
		for (int i = 0; i < customFieldsArr.length; i++) {
			CustomFields customField = customFieldsArr[i];

			String key = customField.getKey();

			switch (key) {
			case "customField1":
				customFieldsDeviceRequestKore.setCustomField1(customField
						.getValue());
				break;

			case "customField2":
				customFieldsDeviceRequestKore.setCustomField2(customField
						.getValue());
				break;

			case "customField3":
				customFieldsDeviceRequestKore.setCustomField3(customField
						.getValue());
				break;

			case "customField4":
				customFieldsDeviceRequestKore.setCustomField4(customField
						.getValue());
				break;

			case "customField5":
				customFieldsDeviceRequestKore.setCustomField5(customField
						.getValue());
				break;
			case "customField6":
				customFieldsDeviceRequestKore.setCustomField6(customField
						.getValue());
				break;
			default:
				break;
			}
		}

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
		exchange.setPattern(ExchangePattern.InOut);

		log.info("End::KoreCustomFieldsPreProcessor");
	}

}
