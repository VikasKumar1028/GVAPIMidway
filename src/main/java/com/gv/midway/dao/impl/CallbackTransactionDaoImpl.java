package com.gv.midway.dao.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.dao.GVCallbackTransactionalDao;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.pojo.verizon.generic.callback.VerizonActivateCallBackRequest;
import com.gv.midway.service.callbacks.impl.GVCallbacksImpl;

@Service
public class CallbackTransactionDaoImpl implements GVCallbackTransactionalDao {
	@Autowired
	MongoTemplate mongoTemplate;
	Logger log = Logger.getLogger(GVCallbacksImpl.class.getName());

	public void populateCallbackDBPayload(Exchange exchange) {
		log.info("CallbackTransactionDaoImpl-populateCallbackDBPayload");
		log.info("Exchange inside" + exchange.getIn().getBody().toString());
		VerizonActivateCallBackRequest req = (VerizonActivateCallBackRequest) exchange.getIn().getBody();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String msgBody = mapper.writeValueAsString(req);
			Transaction transaction = new Transaction();
			transaction.setDevicePayload(msgBody);
			mongoTemplate.save(transaction);
			log.info("CallbackTransactionDaoImpl-populateCallbackDBPayload Saved ...");
		} catch (Exception ex) {
			log.fatal("CallbackTransactionDaoImpl-populateCallbackDBPayload || Exception : " + req.getDeviceResponse().toString());
		}
	}
}
