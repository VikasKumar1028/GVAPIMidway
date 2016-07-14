package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.NetSuiteRequestType;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
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
	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Inside CallbackPostProcessor process " + exchange.getIn().getBody());
		
		
		NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest= (NetSuiteCallBackProvisioningRequest)exchange.getIn().getBody();
		
		
		Object kafkaObject=exchange.getProperty(IConstant.KAFKA_OBJECT);
		
		// Send the error Payload to NetSuite in callback.
		if(kafkaObject instanceof KafkaNetSuiteCallBackError){
			
			
			exchange.setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-app-errors");
			
			
		}
		
		// Send the Successful CallBack Payload to NetSuite in callback.
		else{
			
			exchange.setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-alerts");
		}
		
		NetSuiteRequestType requestType=netSuiteCallBackProvisioningRequest.getRequestType();
		
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
      
      script="539";
      exchange.setProperty("script", script);
		
		switch (requestType) 
		{
		case ACTIVATION:
			
			
			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
			
			
			break;
			
         case DEACTIVATION:
			
        	
 			oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
        	
			break;
			
         case SUSPENSION:
 			
        	
  			 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
        	
        	 
 			break;
 			
         case RESTORATION:
 			
        	
  			 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
        	
 			break;
 			//not applicable for Verizon
        /* case REACTIVATION:
 			script="532";
  			 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
  			 message.setHeader("Authorization", oauthHeader);
        	message.setHeader(Exchange.HTTP_PATH, "?script=532&deploy=1");
 			break;*/
 			
        case SERVICE_PLAN:
 			// To do need to get URL
        	 
        	 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
         	
  			break;
  			
 			
         case CUSTOM_FIELDS:
        	 
        	// To do need to get URL
 			
        	 oauthHeader=NetSuiteOAuthUtil.getNetSuiteOAuthHeader(endPoint, oauthConsumerKey, oauthTokenId, oauthTokenSecret, oauthConsumerSecret, relam, script);
         	
  			break;
  			
 		
 			
       
		default:
			break;
		}
		
	
		exchange.setPattern(ExchangePattern.InOut);
		message.setHeader("Authorization", oauthHeader);
	}

}
