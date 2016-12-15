package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.job.JobinitializedResponse;

public class JobInitializedPostProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(JobInitializedPostProcessor.class.getName());

    public JobInitializedPostProcessor() {
        // Empty Constructor
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Begin::JobInitializedPostProcessor");

        final JobinitializedResponse jobinitializedResponse = new JobinitializedResponse();
        jobinitializedResponse.setMessage(IResponse.SUCCESS_DESCRIPTION_JOB_INITIALIZED);

        exchange.getIn().setBody(jobinitializedResponse);
        LOGGER.debug("End::JobInitializedPostProcessor");
    }
}
