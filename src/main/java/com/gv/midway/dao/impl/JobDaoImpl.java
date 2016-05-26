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
			
			String carrierName="";
			
			if (jobParameter.getJobName().toString().contains("KORE"))
			{
				carrierName="KORE";
			}
			
			if(jobParameter.getJobName().toString().contains("VERIZON"))
			{
				carrierName="VERIZON";
			}
			//We have to check number of bs_carrier
			Query searchDeviceQuery = new Query(Criteria.where("bs_carrier")
					.is(carrierName));
			
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
		
		
		
		//Iconstant check job Name
		//Connection History
		//DEvice Usage
		
		
		
		Date localTime = new Date();

		JobDetail jobDetail = new JobDetail();

		jobDetail.setName(jobParameter.getJobName());
		jobDetail.setType(jobParameter.getJobType());
		jobDetail.setStartTime(localTime.toString());

		mongoTemplate.insert(jobDetail);

	}

	@Override
	public void updateJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub

	}
}
