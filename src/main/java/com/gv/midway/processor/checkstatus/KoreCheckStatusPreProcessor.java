package com.gv.midway.processor.checkstatus;

import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.ITransaction;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreCheckStatusPreProcessor implements Processor {
	
	Logger log = Logger.getLogger(KoreCheckStatusPreProcessor.class
			.getName());

	private Environment newEnv;
	
	public KoreCheckStatusPreProcessor() {

	}

	
	public KoreCheckStatusPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		
       System.out.println("*************Testing**************************************"+ exchange.getIn().getBody());
		
		Message message = exchange.getIn();

		Transaction transaction= exchange.getIn().getBody(Transaction.class);
		
		
		
		String carrierStatus=transaction.getCarrierStatus();
		
		RequestType requestType=transaction.getRequestType();
		
		Object payload=transaction.getDevicePayload();
		
		BaseRequest baseRequest=(BaseRequest) payload;
		
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_HEADER, baseRequest.getHeader());
		
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,transaction.getDeviceNumber());

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,transaction.getMidwayTransactionId());
		
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE,requestType);
		
		exchange.setProperty(ITransaction.CARRIER_STATUS,carrierStatus);
		
		exchange.setProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC,transaction.getCarrierErrorDescription());
		
		
		
		/***
		 * carrier status as Error . CallBack the Netsuite end point here and write it in Kafka Queue.
		 */
		
		if(carrierStatus.equals(IConstant.CARRIER_TRANSACTION_STATUS_ERROR))
		{
			log.info("carrier status error is........."+carrierStatus);
			message.setHeader("KoreCheckStatusFlow", "end");
			
			
		}
		
		//carrier status as Pending and request Type is Change CustomFileds or Change Service Plans
		else if (carrierStatus.equals(IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS))
		{
			
			message.setHeader("KoreCheckStatusFlow", "change");
			
		}
		
		/**
		 * 
		 * Check the status of Kore Device with tracking number for Activation ,DeActivation , Suspend , Restore, ReActivation
		 */
		else
		{
			log.info("carrier status not error is........."+carrierStatus);
		message.setHeader("KoreCheckStatusFlow", "forward");
		String carrierTransationID=transaction.getCarrierTransactionId();
		exchange.setProperty(IConstant.CARRIER_TRANSACTION_ID, carrierTransationID);
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("trackingNumber", carrierTransationID);
	
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader("Authorization",
				newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
		message.setHeader(Exchange.HTTP_PATH, "/json/queryProvisioningRequestStatus");

		message.setBody(obj);
		
		exchange.setPattern(ExchangePattern.InOut);
		
		}
		
		
		
		
	}

}
