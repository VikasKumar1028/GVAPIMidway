package com.gv.midway.processor.customFieldsUpdateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.customFieldsUpdateDevice.request.CustomFieldsUpdateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class VerizonCustomFieldsUpdatePreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonCustomFieldsUpdatePreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Start::VerizonUpdateCustomeFieldDevicePreProcessor");

		/*CustomFieldsUpdateDeviceRequest businessRequest = new CustomFieldsUpdateDeviceRequest();
		ActivateDeviceRequest proxyRequest = (ActivateDeviceRequest) exchange
				.getIn().getBody();*/

		CustomFieldsUpdateDeviceRequest customFieldsUpdateDeviceRequest = exchange.getIn().getBody(CustomFieldsUpdateDeviceRequest.class);
		Message message = exchange.getIn();
		String sessionToken = "";
		String authorizationToken = "";

		if (exchange.getProperty(IConstant.VZ_SEESION_TOKEN) != null
				&& exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
			sessionToken = exchange.getProperty(IConstant.VZ_SEESION_TOKEN)
					.toString();
			authorizationToken = exchange.getProperty(
					IConstant.VZ_AUTHORIZATION_TOKEN).toString();
		}

		exchange.getIn().setBody(customFieldsUpdateDeviceRequest);
		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/customFields");

		log.info("End::VerizonUpdateCustomeFieldDevicePreProcessor");
	}

}
