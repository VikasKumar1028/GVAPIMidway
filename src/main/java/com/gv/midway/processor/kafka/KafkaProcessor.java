package com.gv.midway.processor.kafka;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;

public class KafkaProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */

	private static final Logger LOGGER = Logger.getLogger(KafkaProcessor.class
			.getName());

	private Environment newEnv;

	public KafkaProcessor() {
		// Empty Constructor
	}

	public KafkaProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:KafkaProcessor");
		Object kafkaObject = exchange.getProperty(IConstant.KAFKA_OBJECT);

		LOGGER.debug(kafkaObject.getClass().getName());

		LOGGER.debug("Data to write in Kafka Queue is......." + kafkaObject.toString());

		ObjectMapper objectMapper = new ObjectMapper();
		String message = null;
		try {
			message = objectMapper.writeValueAsString(kafkaObject);
			LOGGER.debug(" data converted to bytes...");
		} catch (JsonProcessingException e) {
			LOGGER.error("Exception in writing the Kafka Queue is......." + e
					+ " for the payload.. " + kafkaObject.toString());
		}

		exchange.getIn().setBody(message, String.class);

		LOGGER.debug("End:KafkaProcessor");
	}

	public Environment getNewEnv() {
		return newEnv;
	}

	public void setNewEnv(Environment newEnv) {
		this.newEnv = newEnv;
	}

}
