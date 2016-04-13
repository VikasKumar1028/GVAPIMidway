package com.gv.midway.dao.impl;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.GVCallbacksDao;
import com.gv.midway.pojo.callback.CallbackResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.utility.CommonUtil;

@Service
public class CallbackDaoImpl implements GVCallbacksDao{

	Logger log = Logger.getLogger(CallbackDaoImpl.class.getName());

	@Autowired
	MongoTemplate mongoTemplate;
	public void callbackResponseCall(Exchange exchange) {
		log.info("Start-CallbackDaoImpl:callbackResponseCall");

		try {
			CallBackVerizonRequest requestBody=(CallBackVerizonRequest)exchange.getIn().getBody();
	/*		ObjectMapper mapper = new ObjectMapper();
			String msgBody = mapper.writeValueAsString(exchange.getIn().getBody());*/

			CallbackResponse callback = new CallbackResponse();
			callback.setPayload(requestBody);
			mongoTemplate.save(callback);

			// }
		} catch (Exception e) {
			log.info("callbackResponseCall-Exception" + e.getMessage());
		}

		log.info("End-CallbackDaoImpl:callbackResponseCall");
	}

	
	public void callbackExceptionResponseCall(Exchange exchange) {
		log.info("Start-CallbackDaoImpl:callbackExceptionResponseCall");

		CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

		String responseBody = exception.getResponseBody();

		try {

			CallbackResponse callback = new CallbackResponse();
			callback.setPayload(responseBody);
			mongoTemplate.save(callback);

		} catch (Exception e) {
			log.info("callbackExceptionResponseCall" + e.getMessage());
		}
	}

	public void callbackRequestCall(Exchange exchange) {
		log.info("Start-CallbackDaoImpl :callbackRequestCall" + exchange.getIn().getBody());
		String msgBody = "";
		try {

			/*ObjectMapper mapper = new ObjectMapper();
			msgBody = mapper.writeValueAsString(exchange.getIn().getBody());*/
			CallBackVerizonRequest requestBody=(CallBackVerizonRequest)exchange.getIn().getBody();

			log.info("callbackRequestCall-jsonInString::" + msgBody);

			CallbackResponse callback = new CallbackResponse();
			callback.setRequestType(exchange.getFromEndpoint().toString());
			callback.setCallBackDelivered(IConstant.MIDWAY_CALLBACK_CARRIER_STATUS_SUCCESS);
			callback.setCallBackPayload(requestBody);
			callback.setCarrierStatus(IConstant.MIDWAY_CALLBACK_CARRIER_STATUS_SUCCESS);
			callback.setLastTimeStampUpdated(CommonUtil.getCurrentTimeStamp());
			mongoTemplate.save(callback);

		} catch (Exception e) {
			e.printStackTrace();
			log.fatal("callbackRequestCall-Exception");
			CallbackResponse callback = new CallbackResponse();
			callback.setLastTimeStampUpdated(CommonUtil.getCurrentTimeStamp());
			callback.setRequestType(exchange.getFromEndpoint().toString());
			callback.setCallBackDelivered(IConstant.MIDWAY_CALLBACK_DELIVERED_FAILED);
			callback.setCallBackFailureToNetSuitReason(e.getMessage());
			callback.setCallBackPayload(msgBody);
			mongoTemplate.save(callback);
		}

		log.info("End-CallbackDaoImpl :callbackRequestCall");
	}

}
