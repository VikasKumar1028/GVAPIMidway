package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;

public class VerizonChangeDeviceServicePlansPreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonChangeDeviceServicePlansPostProcessor.class
			.getName());
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Start::VerizonChangeDeviceServicePlansPreProcessor");
		ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest = exchange
				.getIn().getBody(ChangeDeviceServicePlansRequest.class);
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

		exchange.getIn().setBody(changeDeviceServicePlansRequest);

		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "PUT");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/plan");
		log.info("Start::VerizonChangeDeviceServicePlansPreProcessor");

	}

}
