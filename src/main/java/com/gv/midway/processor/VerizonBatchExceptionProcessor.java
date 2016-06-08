package com.gv.midway.processor;

import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.job.JobDetail;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageHistory;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

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
		
		
		Exception ex =(Exception)exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);
		
		String errorType="";
		

		if (ex.getCause() instanceof UnknownHostException || ex.getCause() instanceof ConnectException )
		{
			errorType=IConstant.MIDWAY_CONNECTION_ERROR;

			log.info("----Connection Exception******************************---------"+ ex);
			
			
		}
		else{
			CxfOperationException exception = (CxfOperationException) exchange
					.getProperty(Exchange.EXCEPTION_CAUGHT);

			log.info("----Exception Body----------"
					+ exception.getResponseBody());
			log.info("----.getStatusCode()----------" + exception.getStatusCode());
			log.info("--------------exchange Body-------" + exchange.getIn().getBody());
			log.info("--------------deviceId------------" + exchange.getProperty("DeviceId"));
			
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
			}else{
				errorType=exception.getResponseBody();
				
				
			}
			
			
			
		}
		
		
		
	/*	CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		log.info("----VerizonBatchGenericExceptionProcessor----------"
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

			
			JobDetail jobDetail=(JobDetail)exchange.getProperty("jobDetail");
			
			log.info("--------------jobDetail" + jobDetail);
			
			//job run and carriet
			
			//Insert a new Object in the batch depending on device Usage and connection history
			
			

		}*/
		
		
	
		log.info("--------------deviceId" + exchange.getIn().getBody());
		log.info("--------------deviceId" + exchange.getProperty("DeviceId"));
		JobDetail jobDetail=(JobDetail)exchange.getProperty("jobDetail");
		
		if(jobDetail.getName().equals(JobName.KORE_DEVICE_USAGE)|| jobDetail.getName().equals(JobName.VERIZON_DEVICE_USAGE))
		{
			
			System.out.println("**********************************DEVICE USAGE******************************************************************");
			DeviceUsage deviceUsage = new DeviceUsage();
		
			deviceUsage
					.setCarrierName((String) exchange.getProperty("CarrierName"));
			deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
			deviceUsage.setDataUsed(0);
			deviceUsage.setTimestamp(CommonUtil.getCurrentTimeStamp());
			deviceUsage.setTransactionErrorReason(errorType);
			deviceUsage
					.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
			deviceUsage.setNetSuiteId((String) exchange.getProperty("NetSuiteId"));
			deviceUsage.setIsValid(true);
			
			exchange.getIn().setBody(deviceUsage);
			
		}else
		{
			System.out.println("**********************************CONNECTION HISTORY******************************************************************");
			
			
			
		}
		
		log.info("--------------jobDetail" + jobDetail);
		
	}
}
