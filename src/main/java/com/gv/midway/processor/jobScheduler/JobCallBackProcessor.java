package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
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

		String oauthConsumerKey = newEnv
				.getProperty("netSuite.oauthConsumerKey");
		String oauthTokenId = newEnv.getProperty("netSuite.oauthTokenId");
		String oauthTokenSecret = newEnv
				.getProperty("netSuite.oauthTokenSecret");
		String oauthConsumerSecret = newEnv
				.getProperty("netSuite.oauthConsumerSecret");
		String realm = newEnv.getProperty("netSuite.realm");
		String endPoint = newEnv.getProperty("netSuite.endPoint");

		String script = "569";
		String oauthHeader = null;

		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");

		oauthHeader = NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint,
				oauthConsumerKey, oauthTokenId, oauthTokenSecret,
				oauthConsumerSecret, realm, script);

		message.setHeader("Authorization", oauthHeader);
		exchange.setProperty("script", script);
		message.setHeader(Exchange.HTTP_PATH, null);
		message.setBody(jobCompletionCallBacktoNetSuite);
		exchange.setPattern(ExchangePattern.InOut);
		LOGGER.info("Job CallBack to netSuite is ..."
				+ exchange.getIn().getBody());

		LOGGER.info("End::JobCallBackProcessor");
	}

}
