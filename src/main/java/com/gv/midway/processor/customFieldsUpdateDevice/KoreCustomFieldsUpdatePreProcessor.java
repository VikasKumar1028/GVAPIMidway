package com.gv.midway.processor.customFieldsUpdateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.processor.activateDevice.KoreActivateDevicePostProcessor;

public class KoreCustomFieldsUpdatePreProcessor implements Processor {

	Logger log = Logger.getLogger(KoreCustomFieldsUpdatePreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("Start::KoreUpdateCustomeFieldDevicePreProcessor");

		log.info("End::KoreUpdateCustomeFieldDevicePreProcessor");
	}

}
