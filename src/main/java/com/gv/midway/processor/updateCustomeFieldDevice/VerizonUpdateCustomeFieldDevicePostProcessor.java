package com.gv.midway.processor.updateCustomeFieldDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.processor.activateDevice.KoreActivateDevicePostProcessor;

public class VerizonUpdateCustomeFieldDevicePostProcessor implements Processor{

	Logger log = Logger.getLogger(KoreActivateDevicePostProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("Start::VerizonUpdateCustomeFieldDevicePostProcessor");

		log.info("End::VerizonUpdateCustomeFieldDevicePostProcessor");
	}

}
