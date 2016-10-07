package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate;

public class KoreActivationWithCustomFieldPreProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */

	private static final Logger LOGGER = Logger
			.getLogger(KoreActivationWithCustomFieldPreProcessor.class
					.getName());

	private Environment newEnv;

	public KoreActivationWithCustomFieldPreProcessor() {
		// Empty Constructor
	}

	public KoreActivationWithCustomFieldPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
         LOGGER.info("Begin:KoreActivationWithCustomFieldPreProcessor.");
		 
		 Message message = exchange.getIn();
	     
	     CustomFieldsDeviceRequest proxyPayload =(CustomFieldsDeviceRequest)exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD);
	     
	     String deviceId = proxyPayload.getDataArea()
	                .getDevices()[0].getDeviceIds()[0].getId();

		CustomFieldsDeviceRequestKore customFieldsDeviceRequestKore = new CustomFieldsDeviceRequestKore();
		customFieldsDeviceRequestKore.setDeviceNumber(deviceId);

		CustomFieldsToUpdate[] customFieldsArr = proxyPayload.getDataArea()
				.getCustomFieldsToUpdate();
		
		 if (customFieldsArr != null) {
	            for (int i = 0; i < customFieldsArr.length; i++) {
	                CustomFieldsToUpdate customField = customFieldsArr[i];

	                String key = customField.getKey();

	                switch (key) {
	                case "CustomField1":
	                    customFieldsDeviceRequestKore.setCustomField1(customField
	                            .getValue());
	                    break;

	                case "CustomField2":
	                    customFieldsDeviceRequestKore.setCustomField2(customField
	                            .getValue());
	                    break;

	                case "CustomField3":
	                    customFieldsDeviceRequestKore.setCustomField3(customField
	                            .getValue());
	                    break;

	                case "CustomField4":
	                    customFieldsDeviceRequestKore.setCustomField4(customField
	                            .getValue());
	                    break;

	                case "CustomField5":
	                    customFieldsDeviceRequestKore.setCustomField5(customField
	                            .getValue());
	                    break;
	                case "CustomField6":
	                    customFieldsDeviceRequestKore.setCustomField6(customField
	                            .getValue());
	                    break;
	                default:
	                    break;
	                }
	            }

	        }

	      
	        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
	        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
	        message.setHeader(Exchange.HTTP_METHOD, "POST");

	        message.setHeader("Authorization",
	                newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
	        message.setHeader(Exchange.HTTP_PATH, "/json/modifyDeviceCustomInfo");

	        message.setBody(customFieldsDeviceRequestKore);
	        exchange.setPattern(ExchangePattern.InOut);

	        LOGGER.info("End:KoreActivationWithCustomFieldPreProcessor");

	}

}
