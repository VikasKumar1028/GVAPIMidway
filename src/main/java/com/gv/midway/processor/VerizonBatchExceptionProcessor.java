package com.gv.midway.processor;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonBatchExceptionProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(VerizonBatchExceptionProcessor.class.getName());

	public VerizonBatchExceptionProcessor() {
		// Empty Constructor
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:VerizonBatchExceptionProcessor");
		final Exception ex = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

		final String errorType;

		// If Connection Exception
		if (ex.getCause() instanceof UnknownHostException
				|| ex.getCause() instanceof ConnectException
				|| ex.getCause() instanceof NoRouteToHostException
				|| ex.getCause() instanceof SocketTimeoutException) {

			LOGGER.warn("reason of connection error is......." + CommonUtil.getStackTrace(ex));
			errorType = IConstant.MIDWAY_CONNECTION_ERROR;
		}
		// CXF Exception
		else {
			final CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
			// Token Expiration Exception
			if (exception.getStatusCode() == 401
					|| exception.getResponseBody().contains("UnifiedWebService.REQUEST_FAILED.SessionToken.Expired")) {

				exchange.setProperty(IConstant.RESPONSE_CODE, "401");
				exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Token");
				exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, "Not able to retrieve valid authentication token");

				if (exchange.getProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR) == null) {
					exchange.setProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR, 1);
				} else {
					Integer sessionErrorCount = (Integer) exchange.getProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR);
					sessionErrorCount = sessionErrorCount + 1;
					exchange.setProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR, sessionErrorCount);
				}

				LOGGER.info("count of retry is........." + exchange.getProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR));

				if ((Integer) exchange.getProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR) <= 2) {
					CommonUtil.setTokenGenerationRequired();
					throw new VerizonSessionTokenExpirationException("401", "401");
				} else {
					errorType = "Not able to retrieve valid authentication token";
				}
			} // Other Cxf Exception
			else {
				errorType = exception.getResponseBody();
			}
		}

		final JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

		if (jobDetail.getName().equals(JobName.KORE_DEVICE_USAGE)
				|| jobDetail.getName().equals(JobName.VERIZON_DEVICE_USAGE)) {

			final String date = jobDetail.getDate();
			LOGGER.debug("----------------------D----A-----T-------E-------" + date);

			final DeviceUsage deviceUsage = new DeviceUsage();
			deviceUsage.setCarrierName((String) exchange.getProperty("CarrierName"));
			deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
			deviceUsage.setDataUsed(0);
			deviceUsage.setDate(date);
			deviceUsage.setTransactionErrorReason(errorType);
			deviceUsage.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
			deviceUsage.setNetSuiteId((Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID));
			deviceUsage.setIsValid(true);
			deviceUsage.setJobId(jobDetail.getJobId());

			exchange.getIn().setBody(deviceUsage);
		} else {

			final String date = jobDetail.getDate();
			LOGGER.debug("----------------------D----A-----T-------E-------" + date);

			final DeviceConnection deviceConnection = new DeviceConnection();
			deviceConnection.setCarrierName((String) exchange.getProperty("CarrierName"));
			deviceConnection.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
			deviceConnection.setDate(date);
			deviceConnection.setTransactionErrorReason(errorType);
			deviceConnection.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
			deviceConnection.setNetSuiteId((Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID));
			deviceConnection.setIsValid(true);
			deviceConnection.setEvent(null);
			deviceConnection.setJobId(jobDetail.getJobId());

			exchange.getIn().setBody(deviceConnection);
			LOGGER.debug("End:VerizonBatchExceptionProcessor");
		}
	}
}