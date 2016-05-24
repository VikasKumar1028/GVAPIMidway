package com.gv.midway.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IJobDao;
import com.gv.midway.job.JobDetail;
import com.gv.midway.job.JobParameter;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

@Service
public class JobDaoImpl implements IJobDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List fetchDevices(Exchange exchange) {
		
		//System.out.println("*******************************************");
		JobParameter jobParameter = (JobParameter) exchange.getIn().getBody();
		
		List<DeviceInformation> deviceInformationList = null;

		try {

			Query searchDeviceQuery = new Query(Criteria.where("bs_carrier")
					.is(jobParameter.getCarrierName()));
			
			deviceInformationList = mongoTemplate.find(searchDeviceQuery,
					DeviceInformation.class);

			System.out.println("deviceInformationList"+deviceInformationList);
		}

		catch (Exception e) {
			System.out.println("e");
		}

		return deviceInformationList;
	}

	@Override
	public void insertJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub

		JobParameter jobParameter = (JobParameter) exchange.getIn().getBody();
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
