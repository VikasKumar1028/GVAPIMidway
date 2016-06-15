package com.gv.midway.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.ISessionDao;
import com.gv.midway.pojo.session.SessionBean;
import com.gv.midway.utility.CommonUtil;

@Service
public class SessionDaoImpl implements ISessionDao {
	/*
	 * @Autowired MongoDb grandVictorDB;
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	public SessionBean getSessionBean() {
		
		//Ip Address of the Machine
		String ipAddress=CommonUtil.getIpAddress();
		
		Query searchUserQuery = new Query(Criteria.where("isValid").is("0")).addCriteria(Criteria.where("ipAddress").is(ipAddress));
		SessionBean sessionbean = mongoTemplate.findOne(searchUserQuery,
				SessionBean.class);
		return sessionbean;
	}

	public SessionBean saveSesionBean(SessionBean sessionBean) {
		
		//Setting the Ip Address of the machine
		String ipAddress=CommonUtil.getIpAddress();
		sessionBean.setIpAddress(ipAddress);
		
		
		Query searchUserQuery = new Query(Criteria.where("isValid").is("0")).addCriteria(Criteria.where("ipAddress").is(ipAddress));
		SessionBean previousSessionBean = mongoTemplate.findOne(
				searchUserQuery, SessionBean.class);

		if (previousSessionBean != null) {
			previousSessionBean.setIsValid("1");
			mongoTemplate.save(previousSessionBean);
		}
		mongoTemplate.save(sessionBean);
		return sessionBean;

	}
}
