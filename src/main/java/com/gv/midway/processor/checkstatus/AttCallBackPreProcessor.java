package com.gv.midway.processor.checkstatus;

import java.util.ArrayList;
import java.util.List;

import com.gv.midway.pojo.KeyValuePair;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.ITransaction;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.transaction.Transaction;

public class AttCallBackPreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(AttCallBackPreProcessor.class.getName());

    private Environment newEnv;

    public AttCallBackPreProcessor() {
        // Empty Constructor
    }

    public AttCallBackPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
    	
    	LOGGER.debug("Begin:AttCallBackPreProcessor");

        LOGGER.debug("*******************" + exchange.getIn().getBody());

        Message message = exchange.getIn();

        Transaction transaction = exchange.getIn().getBody(Transaction.class);

        String carrierStatus = transaction.getCarrierStatus();

        RequestType requestType = transaction.getRequestType();

        Object payload = transaction.getDevicePayload();
        
        String customFieldDetails="";
        if (transaction.getRequestType().equals(RequestType.CHANGECUSTOMFIELDS)) {
            List<KeyValuePair> list = (ArrayList<KeyValuePair>) transaction.getCallBackPayload();

            for (int i = 0; i < list.size(); i++) {
                KeyValuePair custField = list.get(i);
                customFieldDetails = customFieldDetails + custField.getKey() + "-" + custField.getValue() + ",";
            }
            exchange.setProperty(IConstant.ATTJASPER_CUSTOM_FIELD_DEC, customFieldDetails);

        }

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD, payload);
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID, transaction.getMidwayTransactionId());
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE, requestType);
        exchange.setProperty(ITransaction.CARRIER_STATUS, carrierStatus);
        
        exchange.setProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC, transaction.getCarrierErrorDescription());

        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, transaction.getNetSuiteId());
        
      
   
        if (carrierStatus.equals(IConstant.CARRIER_TRANSACTION_STATUS_ERROR)) {
            LOGGER.error("carrier status error is........." + carrierStatus);
            message.setHeader(IConstant.ATT_CALLBACK_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);

        }

        // carrier status as Success 
        else if (carrierStatus.equals(IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS)) {

            message.setHeader(IConstant.ATT_CALLBACK_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);

        }

        LOGGER.debug("End:AttCallBackPreProcessor");
    }

}
