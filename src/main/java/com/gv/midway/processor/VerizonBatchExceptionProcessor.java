package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;

public class VerizonBatchExceptionProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonBatchExceptionProcessor.class
			.getName());

	Environment newEnv;

	public VerizonBatchExceptionProcessor(Environment env) {
		super();

		this.newEnv = env;

	}

	public VerizonBatchExceptionProcessor() {
		// TODO Auto-generated constructor stub
	}

	public void process(Exchange exchange) throws Exception {

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		log.info("----VerizonGenericExceptionProcessor----------"
				+ exception.getResponseBody());
		log.info("----.getStatusCode()----------" + exception.getStatusCode());
		log.info("--------------deviceId" + exchange.getIn().getBody());
		log.info("--------------deviceId" + exchange.getProperty("DeviceId"));
		
		// TODO SAME Functionality
		if (exception.getStatusCode() == 401
				|| exception
						.getResponseBody()
						.contains(
								"UnifiedWebService.REQUEST_FAILED.SessionToken.Expired")) {
			exchange.setProperty(IConstant.RESPONSE_CODE, "401");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Token");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
					"Not able to retrieve  valid authentication token");
			throw new VerizonSessionTokenExpirationException("401", "401");
		}
		// TODO SAME Functionality
		else {

			//Insert a new Object in the batch
			
			

		}

		
	}
}
