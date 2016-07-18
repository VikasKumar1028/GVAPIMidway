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

    private static final Logger LOGGER = Logger.getLogger(KafkaProcessor.class.getName());

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

        Object kafkaObject = exchange.getProperty(IConstant.KAFKA_OBJECT);

        LOGGER.info(kafkaObject.getClass().getName());

        LOGGER.info("Data to write in Kafka Queue is......."
                + kafkaObject.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = null;
        try {
            bytes = objectMapper.writeValueAsString(kafkaObject).getBytes();
            LOGGER.info(" data converted to bytes...");
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception in wrting the Kafka Queue is......."
                    + e+ "for the paylaod.."
                    + kafkaObject.toString());
        }

        exchange.getIn().setBody(bytes);
    }

    public Environment getNewEnv() {
        return newEnv;
    }

    public void setNewEnv(Environment newEnv) {
        this.newEnv = newEnv;
    }

}
