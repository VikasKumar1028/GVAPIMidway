package com.gv.midway.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.IJobDao;
import com.gv.midway.job.JobDetail;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.mongodb.WriteResult;

@Service
public class JobDaoImpl implements IJobDao {

	@Autowired
	MongoTemplate mongoTemplate;

	Logger log = Logger.getLogger(JobDaoImpl.class);

	@Override
	public List fetchDevices(Exchange exchange) {

		JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

		exchange.setProperty("jobName", jobDetail.getName().toString());

		List<DeviceInformation> deviceInformationList = null;
		List<DeviceInformation> list = null;

		try {

			String carrierName = jobDetail.getCarrierName();

			log.info("Carrier Name -----------------" + carrierName);
			// We have to check bs_carrier with possible reseller values for
			// that carrier.
			Query searchDeviceQuery = new Query(Criteria.where("bs_carrier")
					.is(carrierName));

			deviceInformationList = mongoTemplate.find(searchDeviceQuery,
					DeviceInformation.class);

			/*
			 * list = new
			 * ArrayList<DeviceInformation>(Collections.nCopies(10000,
			 * deviceInformationList.get(0)));
			 */

			list = new ArrayList<DeviceInformation>(Collections.nCopies(10,
					deviceInformationList.get(0)));

			log.info("deviceInformationList ------------------"
					+ deviceInformationList.size());
		}

		catch (Exception e) {
			System.out.println("e");
		}

		// return list;
		return deviceInformationList;
	}

	@Override
	public void insertJobDetails(Exchange exchange) {
		JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();
		jobDetail.setStartTime(new Date().toString());
		jobDetail.setStatus(IConstant.JOB_STARTED);

		// inserting in the exchange as property
		exchange.setProperty("jobDetail", jobDetail);

		// inserting in the database as property
		mongoTemplate.insert(jobDetail);
	}

	@Override
	public void updateJobDetails(Exchange exchange) {

		log.info("Inside updateJobDetails .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName()))
					.addCriteria(Criteria.where("date").is(jobDetail.getDate()))
					.addCriteria(Criteria.where("name").is(jobDetail.getName()))
					.addCriteria(Criteria.where("type").is(jobDetail.getType()))
					.addCriteria(
							Criteria.where("startTime").is(
									jobDetail.getStartTime()));

			Update update = new Update();

			update.set("endTime", new Date().toString());
			update.set("status", IConstant.JOB_COMPLETED);

			mongoTemplate.updateFirst(searchJobQuery, update, JobDetail.class);

		}

		catch (Exception e) {
			log.info("Error In Saving Job Detail-----------------------------");
		}

	}

	@Override
	public void deleteDeviceUsageRecords(Exchange exchange) {

		log.info("Inside deleteDeviceUsageRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName())).addCriteria(Criteria.where(
					"timestamp").is(jobDetail.getDate()));

			Update update = new Update();

			update.set("isValid", false);

			WriteResult result=mongoTemplate
					.updateMulti(searchJobQuery, update, DeviceUsage.class);
			
			log.info("WriteResult *********************"+ result);

		}

		catch (Exception e) {
			log.info("Error In Saving Job Detail-----------------------------");
		}

	}
}
