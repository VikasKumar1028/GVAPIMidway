package com.gv.midway.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IJobDao;
import com.gv.midway.job.JobDetail;
import com.gv.midway.job.JobParameter;
@Service
public class JobDaoImpl implements IJobDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List fetchDevices(Exchange exchange) {
		// TODO Auto-generated method stub

		return null;

	}

	@Override
	public void insertJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub

		
		JobParameter jobParameter =(JobParameter)exchange.getIn().getBody();
		Date localTime = new Date();

		JobDetail jobDetail = new JobDetail();

		jobDetail.setName("insertJobDetails");
		jobDetail.setStartTime(localTime.toString());

		mongoTemplate.insert(jobDetail);
		
	}

	@Override
	public void updateJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub
	
	}
}
