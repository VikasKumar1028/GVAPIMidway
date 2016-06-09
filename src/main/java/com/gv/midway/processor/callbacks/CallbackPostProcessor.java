package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningResponse;


public class CallbackPostProcessor implements Processor {
	Logger log = Logger.getLogger(CallbackPostProcessor.class);

	public void process(Exchange exchange) throws Exception {
		log.info("Inside CallbackPostProcessor process " + exchange.getIn().getBody());
		
		
		NetSuiteCallBackProvisioningResponse netSuiteCallBackProvisioningResponse= (NetSuiteCallBackProvisioningResponse)exchange.getIn().getBody();
		
		
		Object kafkaObject=exchange.getProperty(IConstant.KAFKA_OBJECT);
		
		// Send the error Payload to NetSuite in callback.
		if(kafkaObject instanceof KafkaNetSuiteCallBackError){
			
			
			exchange.setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-app-errors");
			
			
		}
		
		// Send the Successful CallBack Payload to NetSuite in callback.
		else{
			
			
			
			exchange.setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-alerts");
		}
	}

}
