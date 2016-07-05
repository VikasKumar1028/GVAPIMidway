package com.gv.midway.processor;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreBatchExceptionProcessor implements Processor {

	Logger log = Logger.getLogger(KoreBatchExceptionProcessor.class.getName());

	Environment newEnv;

	public KoreBatchExceptionProcessor(Environment env) {
		super();

		this.newEnv = env;

	}

	public KoreBatchExceptionProcessor() {

	}

	public void process(Exchange exchange) throws Exception {

		Exception ex = (Exception) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		String errorType = "";

		// If Connection Exception
		if (ex.getCause() instanceof UnknownHostException
				|| ex.getCause() instanceof ConnectException) {
			errorType = IConstant.MIDWAY_CONNECTION_ERROR;

		}
		// CXF Exception
		else {
			CxfOperationException exception = (CxfOperationException) exchange
					.getProperty(Exchange.EXCEPTION_CAUGHT);
			// Token Expiration Exception

			errorType = exception.getResponseBody();

		}

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

		DeviceUsage deviceUsage = new DeviceUsage();

		deviceUsage
				.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
		deviceUsage.setDataUsed(0);
	
        String date=jobDetail.getDate();
		log.info("----------------------D----A-----T-------E-------" + date);
		deviceUsage.setDate(date);
		deviceUsage.setTransactionErrorReason(errorType);
		deviceUsage
				.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
		deviceUsage.setNetSuiteId((String) exchange.getProperty("NetSuiteId"));
		deviceUsage.setIsValid(true);

		exchange.getIn().setBody(deviceUsage);

	}
}
