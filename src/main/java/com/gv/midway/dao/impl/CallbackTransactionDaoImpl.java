package com.gv.midway.dao.impl;

import net.sf.json.JSONObject;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.GVCallbackTransactionalDao;
import com.gv.midway.pojo.callback.CallbackResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.service.callbacks.impl.GVCallbacksImpl;
import com.gv.midway.utility.CommonUtil;

@Service
public class CallbackTransactionDaoImpl implements GVCallbackTransactionalDao {
	@Autowired
	MongoTemplate mongoTemplate;
	Logger log = Logger.getLogger(GVCallbacksImpl.class.getName());

	public void populateCallbackDBPayload(Exchange exchange) {
		
		log.info("CallbackTransactionDaoImpl-populateCallbackDBPayload");
		log.info("Exchange inside" + exchange.getIn().getBody().toString());
		
		
		CallBackVerizonRequest callBackVerizonRequest=(CallBackVerizonRequest)exchange.getIn().getBody();
		
		String requestId=callBackVerizonRequest.getRequestId();
		
		ObjectMapper objectMapper =new ObjectMapper();
		String strDeviceNumber="";
		try {
			strDeviceNumber = objectMapper.writeValueAsString(callBackVerizonRequest.getDeviceIds());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
System.out.println("********************************************"+ strDeviceNumber);
		Query searchUserQuery = new Query(Criteria.where("carrierTransationID").is(requestId).andOperator(Criteria.where("deviceNumber").is(strDeviceNumber)));


		/*
		 * String carrierTransationID;//Call Back Thread String
		 * carrierStatus;//Call Back Thread String
		 * LastTimeStampUpdated;//CallBack Thread String
		 * carrierErrorDecription;//CallBack Thread String
		 * callBackPayload;//CallBack Thread Boolean
		 * callBackDelivered;//CallBack Thread Boolean
		 * callBackReceived;//CallBack Thread String
		 * callBackFailureToNetSuitReason;//CallBack Thread
		 */
		
		Update update = new Update();
		if (exchange.getIn().getBody().toString().contains("errorMessage=")) {
			update.set("callBackPayload", exchange.getIn().getBody().toString());
			update.set("carrierErrorDecription", exchange.getIn().getBody().toString());
			update.set("carrierErrorDecription", exchange.getIn().getBody().toString());
			update.set("carrierStatus", "Error");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
			
		}else{
			
			update.set("callBackPayload", exchange.getIn().getBody().toString());
			update.set("carrierStatus", "SUCCESS");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
					
		}
		mongoTemplate.updateMulti(searchUserQuery, update, Transaction.class);
	
	}
}
