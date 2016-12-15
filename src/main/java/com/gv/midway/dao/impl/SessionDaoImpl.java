package com.gv.midway.dao.impl;

import com.gv.midway.dao.ISessionDao;
import com.gv.midway.pojo.session.SessionBean;
import com.gv.midway.utility.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SessionDaoImpl implements ISessionDao {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Method fetches the existing valid session
     */
    @Override
    public SessionBean getSessionBean() {

        // Ip Address of the Machine
        final String ipAddress = CommonUtil.getIpAddress();

        final Query searchUserQuery = new Query(Criteria.where("isValid").is("0"))
                .addCriteria(Criteria.where("ipAddress").is(ipAddress));
        return mongoTemplate.findOne(searchUserQuery, SessionBean.class);
    }

    /**
     * Method Invalidates the previous session tokens and saves the new token
     * value
     */
    @Override
    public SessionBean saveSessionBean(SessionBean sessionBean) {

        // Setting the Ip Address of the machine
        final String ipAddress = CommonUtil.getIpAddress();
        sessionBean.setIpAddress(ipAddress);

        final Query searchUserQuery = new Query(Criteria.where("isValid").is("0"))
                .addCriteria(Criteria.where("ipAddress").is(ipAddress));
        final SessionBean previousSessionBean = mongoTemplate.findOne(searchUserQuery, SessionBean.class);

        if (previousSessionBean != null) {
            previousSessionBean.setIsValid("1");
            mongoTemplate.save(previousSessionBean);
        }

        mongoTemplate.save(sessionBean);
        return sessionBean;
    }
}