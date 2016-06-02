package com.gv.midway.processor.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class KafkaProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */

	Logger log = Logger
			.getLogger(KafkaProcessor.class.getName());

	private Environment newEnv;

	public KafkaProcessor() {

	}

	public KafkaProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		Object object=exchange.getIn().getBody();
		
		log.debug(object.getClass().getName());
		
		log.debug("Date to write in Kafka Queue is......."+object.toString());
		
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] bytes = null;
		try {
			bytes = objectMapper.writeValueAsString(object).getBytes();
			log.debug(" data converted to bytes...");
		} catch (JsonProcessingException e) {
		}

		exchange.getIn().setBody(bytes);
	}

}
