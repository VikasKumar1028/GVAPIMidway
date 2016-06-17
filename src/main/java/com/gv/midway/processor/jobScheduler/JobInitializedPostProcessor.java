package com.gv.midway.processor.jobScheduler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.job.JobinitializedResponseDataArea;

public class JobInitializedPostProcessor implements Processor {

	Logger log = Logger.getLogger(JobInitializedPostProcessor.class
			.getName());

	public JobInitializedPostProcessor() {

	}

	Environment newEnv;

	public JobInitializedPostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {

		log.info("Begin::JobInitializedPostProcessor");

		JobinitializedResponse jobinitializedResponse = new JobinitializedResponse();

		JobinitializedResponseDataArea jobinitializedResponseDataArea = new JobinitializedResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_JOB_INITIALIZED);

	/*	responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());

		responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT)
				.toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());

		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());

*/		//jobinitializedResponse.setHeader(responseheader);
		jobinitializedResponse.setResponse(response);
	/*	jobinitializedResponseDataArea.setJobInitializedStaus(IConstant.JOB_INITIALIZED_MESSAGE);
		jobinitializedResponse.setDataArea(jobinitializedResponseDataArea);
*/
		exchange.getIn().setBody(jobinitializedResponse);
		log.info("End::JobInitializedPostProcessor");
	}

}
