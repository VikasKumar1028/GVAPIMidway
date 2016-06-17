package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningResponse;
import com.gv.midway.utility.NetSuiteOAuthUtil;


public class CallbackPostProcessor implements Processor {
	Logger log = Logger.getLogger(CallbackPostProcessor.class);
	
	private Environment newEnv;

	public CallbackPostProcessor() {

	}

	public CallbackPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		log.info("Inside CallbackPostProcessor process " + exchange.getIn().getBody());
		
		
		NetSuiteCallBackProvisioningResponse netSuiteCallBackProvisioningResponse= (NetSuiteCallBackProvisioningResponse)exchange.getIn().getBody();
		
		
		Object kafkaObject=exchange.getProperty(IConstant.KAFKA_OBJECT);
		
		// Send the error Payload to NetSuite in callback.
		if(kafkaObject instanceof KafkaNetSuiteCallBackError){
			
			
			exchange.setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-app-errors");
			
			
		}
		
		// Send the Successful CallBack Payload to NetSuite in callback.
		else{
			
			exchange.setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-alerts");
		}
		
		RequestType requestType=netSuiteCallBackProvisioningResponse.getRequestType();
		
		Message message = exchange.getIn();
		
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		
	   log.info("body set for netSuiteCallBack........."+exchange.getIn().getBody());
	 
      String oauthConsumerKey=newEnv.getProperty("netSuite.oauthConsumerKey");
      String oauthTokenId=newEnv.getProperty("netSuite.oauthTokenId");
      String oauthTokenSecret=newEnv.getProperty("netSuite.oauthTokenSecret");
      String oauthConsumerSecret=newEnv.getProperty("netSuite.oauthConsumerSecret");
      String relam=newEnv.getProperty("netSuite.Relam");
      String endPoint=newEnv.getProperty("netSuite.endPoint");
      
      log.info("request type for NetSuite CallBack error...."+requestType);
		
	  log.info("oauth info is....."+oauthConsumerKey+" "+oauthTokenId+" "+endPoint+" "+oauthTokenSecret+" "+oauthConsumerSecret+" "+relam);
	  
      String script=null;
      String oauthHeader=null;
		
		switch (requestType) 
		{
		case ACTIVATION:
			
			script="529";
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			
			
			break;
			
         case DEACTIVATION:
			
        	script="531";
 			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
        	
			break;
			
         case SUSPEND:
 			
        	 script="533";
  			 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
        	
        	 
 			break;
 			
         case RESTORE:
 			
        	 script="534";
  			 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
        	
 			break;
 			
        /* case REACTIVATION:
 			script="532";
  			 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
  			 message.setHeader("Authorization", oauthHeader);
        	message.setHeader(Exchange.HTTP_PATH, "?script=532&deploy=1");
 			break;*/
 			
         case CHANGECUSTOMFIELDS:
 			// To do need to get URL
        	 
 			break;
 			
         case CHANGESERVICEPLAN:
        	 
        	// To do need to get URL
 			
 			break;
 			
       
		default:
			break;
		}
		
		exchange.setProperty("script", script);
		message.setHeader("Authorization", oauthHeader);
	}

}
