package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.environment.EnvironmentParser;
import com.gv.midway.environment.NetSuiteOAuthHeaderProperties;
import com.gv.midway.pojo.job.JobCompletionCallBacktoNetSuite;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.utility.NetSuiteOAuthUtil;

public class JobCallBackProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(JobCallBackProcessor.class.getName());

	private Environment newEnv;

	public JobCallBackProcessor() {
		// Empty Constructor
	}

	public JobCallBackProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin::JobCallBackProcessor");

		Message message = exchange.getIn();

		JobDetail jobDetail = (JobDetail) exchange
				.getProperty(IConstant.JOB_DETAIL);

		String successCount = (String) exchange
				.getProperty(IConstant.JOB_SUCCESS_COUNT);

		String errorCount = (String) exchange
				.getProperty(IConstant.JOB_ERROR_COUNT);

		JobCompletionCallBacktoNetSuite jobCompletionCallBacktoNetSuite = new JobCompletionCallBacktoNetSuite();

		jobCompletionCallBacktoNetSuite.setPeriod(jobDetail.getPeriod());
		jobCompletionCallBacktoNetSuite
				.setBsCarrier(jobDetail.getCarrierName());
		jobCompletionCallBacktoNetSuite.setJobDate(jobDetail.getDate());

		JobName jobName = jobDetail.getName();

		if (jobName.equals(JobName.VERIZON_CONNECTION_HISTORY)) {
			jobCompletionCallBacktoNetSuite.setJobType("connectionHistory");
		}

		else {
			jobCompletionCallBacktoNetSuite.setJobType("deviceUsage");
		}
		if (successCount != null) {
			jobCompletionCallBacktoNetSuite.setSuccessCount(successCount);
		} else {
			jobCompletionCallBacktoNetSuite.setSuccessCount("0");
		}
		if (errorCount != null) {
			jobCompletionCallBacktoNetSuite.setErrorCount(errorCount);
		} else {
			jobCompletionCallBacktoNetSuite.setErrorCount("0");
		}

		final NetSuiteOAuthHeaderProperties properties = EnvironmentParser.getNetSuiteOAuthHeaderProperties(newEnv);
	    final String script = "569";
	    
	    LOGGER.info("oauth info is....." + properties);

        final String oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(properties, script);
        
		

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", oauthHeader);
		message.setHeader(Exchange.HTTP_PATH, null);
		message.setBody(jobCompletionCallBacktoNetSuite);
		
		exchange.setProperty("script", script);
		exchange.setPattern(ExchangePattern.InOut);
		
		LOGGER.info("Job CallBack to netSuite is ..."
				+ exchange.getIn().getBody());

		LOGGER.info("End::JobCallBackProcessor");
	}

}
