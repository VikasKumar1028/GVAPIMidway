package com.gv.midway.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
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
		
		JobParameter jobParameter = (JobParameter) exchange.getIn().getBody();
	
		List<DeviceInformation> deviceInformationList = null;
		List<DeviceInformation> list=null;

		try {
			
			String carrierName=jobParameter.getCarrierName().toString();
		
			//We have to check number of bs_carrier
			Query searchDeviceQuery = new Query(Criteria.where("bs_carrier")
					.is(carrierName));
			
			deviceInformationList = mongoTemplate.find(searchDeviceQuery,
					DeviceInformation.class);
			
			list = new ArrayList<DeviceInformation>(Collections.nCopies(10000,deviceInformationList.get(0) ));

			System.out.println("deviceInformationList"+deviceInformationList);
		}

		catch (Exception e) {
			System.out.println("e");
		}

		//return deviceInformationList;
		return list;
	}

	@Override
	public void insertJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub

		JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();
		
		mongoTemplate.insert(jobDetail);

	}

	@Override
	public void updateJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub

	}
}
