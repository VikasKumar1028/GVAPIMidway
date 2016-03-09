package com.gv.midway.dao.impl;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IAuditDao;

@Service
public class AuditDaoImpl implements IAuditDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AuditDaoImpl.class); // Initializing

	@Autowired
	MongoTemplate mongoTemplate;

	public void auditExternalRequestCall(Exchange exchange) {
		
	/*	CamelContext camel =(CamelContext)exchange.getContext();
		camel.*/
		
		 //  HttpServletRequest req = exchange.getIn().getBody(HttpServletRequest.class);

		logger.info("saving in database");
		// mongoTemplate.save(e);
		// System.out.println(
		// mongoTemplate.getDb().toString()+"-----"+"----------xcxc-----"+device.toString());
		// return device.getId();

	}

	public void auditExternalResponseCall(Exchange exchange) {

		logger.info("saving in database");
		// mongoTemplate.save(device);
		// System.out.println(
		// mongoTemplate.getDb().toString()+"-----"+"----------xcxc-----"+device.toString());
		// return device.getId();

	}

}
