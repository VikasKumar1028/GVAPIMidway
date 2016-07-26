package com.gv.midway.processor;

import java.net.ConnectException;
import java.net.UnknownHostException;
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

    private static final Logger LOGGER = Logger.getLogger(KoreBatchExceptionProcessor.class.getName());

    Environment newEnv;

    public KoreBatchExceptionProcessor(Environment env) {
        super();

        this.newEnv = env;

    }

    public KoreBatchExceptionProcessor() {
        // Empty Constructor
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        Exception ex = (Exception) exchange
                .getProperty(Exchange.EXCEPTION_CAUGHT);

        String errorType;

        // If Connection Exception
        if (ex.getCause() instanceof UnknownHostException
                || ex.getCause() instanceof ConnectException) {
            errorType = IConstant.MIDWAY_CONNECTION_ERROR;

        }
        
        // CXF Exception
        else if(ex.getCause() instanceof CxfOperationException){
            CxfOperationException exception = (CxfOperationException) exchange
                    .getProperty(Exchange.EXCEPTION_CAUGHT);
            // Token Expiration Exception

            errorType = exception.getResponseBody();

        }
        
        //SIM is missing in DeviceIds , KoreSimMissingException 
        else
        {
        	 errorType = IConstant.KORE_MISSING_SIM_ERROR;
        	
        }

        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

        DeviceUsage deviceUsage = new DeviceUsage();

        deviceUsage
                .setCarrierName((String) exchange.getProperty("CarrierName"));
        deviceUsage.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
        deviceUsage.setDataUsed(0);

        String date = jobDetail.getDate();
        LOGGER.info("----------------------D----A-----T-------E-------" + date);
        deviceUsage.setDate(date);
        deviceUsage.setTransactionErrorReason(errorType);
        deviceUsage
                .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
        deviceUsage.setNetSuiteId((Integer) exchange
                .getProperty(IConstant.MIDWAY_NETSUITE_ID));
        deviceUsage.setIsValid(true);

        exchange.getIn().setBody(deviceUsage);

    }
}
