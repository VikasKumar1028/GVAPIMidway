package com.gv.midway.processor;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperBatchExceptionProcessor implements Processor {
	
	private static final Logger LOGGER = Logger.getLogger(ATTJasperBatchExceptionProcessor.class.getName());

	
	public ATTJasperBatchExceptionProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("Begin:ATTJasperBatchExceptionProcessor");

		final Exception ex = (Exception) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		String errorType=null;

		// If Connection Exception
		if (ex.getCause() instanceof UnknownHostException
				|| ex.getCause() instanceof ConnectException
				|| ex.getCause() instanceof NoRouteToHostException
				|| ex.getCause() instanceof SocketTimeoutException) {
			LOGGER.info("reason of connection error is......."
					+ CommonUtil.getStackTrace(ex));
			errorType = IConstant.MIDWAY_CONNECTION_ERROR;

		}
		
		 else if (ex.getCause() instanceof SoapFault){
			 
			errorType= CommonUtil.getSOAPErrorResponseFromExchange(exchange);
		 }

		final JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

		final DeviceUsage deviceUsage = new DeviceUsage();

		deviceUsage
				.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
		deviceUsage.setDataUsed(0);

		final String date = jobDetail.getDate();
		LOGGER.info("----------------------D----A-----T-------E-------" + date);
		deviceUsage.setDate(date);
		deviceUsage.setTransactionErrorReason(errorType);
		deviceUsage
				.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
		deviceUsage.setNetSuiteId((Integer) exchange
				.getProperty(IConstant.MIDWAY_NETSUITE_ID));
		deviceUsage.setIsValid(true);
		deviceUsage.setJobId(jobDetail.getJobId());

		exchange.getIn().setBody(deviceUsage);

		LOGGER.info("End:ATTJasperBatchExceptionProcessor");
	}
}
