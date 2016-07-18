package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.job.JobinitializedResponse;

public class JobInitializedPostProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(JobInitializedPostProcessor.class.getName());

    public JobInitializedPostProcessor() {
        // Empty Constructor
    }

    Environment newEnv;

    public JobInitializedPostProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin::JobInitializedPostProcessor");

        JobinitializedResponse jobinitializedResponse = new JobinitializedResponse();

        jobinitializedResponse
                .setMessage(IResponse.SUCCESS_DESCRIPTION_JOB_INITIALIZED);
        exchange.getIn().setBody(jobinitializedResponse);

        LOGGER.info("End::JobInitializedPostProcessor");
    }

}
