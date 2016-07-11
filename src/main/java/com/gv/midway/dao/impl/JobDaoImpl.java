/*
 * This Class is Developed to Interact with DB for all the Operations related to Batch Jobs
 */

package com.gv.midway.dao.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.dao.IJobDao;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.notification.DeviceOverageNotification;
import com.gv.midway.pojo.server.ServerDetail;
import com.gv.midway.utility.CommonUtil;
import com.mongodb.WriteResult;

@Service
public class JobDaoImpl implements IJobDao {

	@Autowired
	MongoTemplate mongoTemplate;

	Logger log = Logger.getLogger(JobDaoImpl.class);
	static Random r = new Random();


	/**
	 * Fetch all the devices 
	 */
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

			// list = deviceInformationList.subList(0, 1000);

			/*
			 * list = new
			 * ArrayList<DeviceInformation>(Collections.nCopies(10000,
			 * deviceInformationList.get(0)));
			 */

			/*
			 * list = new ArrayList<DeviceInformation>(Collections.nCopies(10,
			 * deviceInformationList.get(0)));
			 */

			log.info("deviceInformationList ------------------"
					+ deviceInformationList.size());
		}

		catch (Exception e) {
			log.error("Error in fetchDevices :"+e);
		}

		// return list;
		return deviceInformationList;
	}

	
	/**
	 * Fetch only devices with Odd Net Suited ID 
	 */
	public List fetchOddDevices(Exchange exchange) {

		log.info("fetchOddDevices::::::::");

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
					.is(carrierName)).addCriteria(Criteria.where("netSuiteId")
					.mod(2, 0));

			log.info("searchDeviceQuery::::::::::" + searchDeviceQuery);

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

			log.error("Error in fetchOddDevices :"+e);
		}

		// return list;
		return deviceInformationList;
	}

	/**
	 * Fetch only devices with Even Net Suited ID 
	 */
	public List fetchEvenDevices(Exchange exchange) {

		log.info("fetchEvenDevices::::::::");

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
					.is(carrierName)).addCriteria(Criteria.where("netSuiteId")
					.mod(2, 0));
			;

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
			
			log.error("Error in fetchEvenDevices :"+e);
		}

		// return list;
		return deviceInformationList;
	}

	/**
	 * Inserting the Job Details and setting date parameters
	 */
	@Override
	public void insertJobDetails(Exchange exchange) {
		JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

		log.info("-----------Job Details -------" + jobDetail.toString());
		jobDetail.setStartTime(new Date().toString());
		jobDetail.setStatus(IConstant.JOB_STARTED);
		jobDetail.setIpAddress(CommonUtil.getIpAddress());
		exchange.setProperty("jobDetail", jobDetail);
		exchange.setProperty("jobDetailDate", jobDetail.getDate());

		// inserting in the database as property
		mongoTemplate.insert(jobDetail);
	}

	

	/**
	 * Updating the Job Details parameters
	 */
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
			log.error("Error In updateJobDetails-----------------------------"+e);
		}

	}

	
	/**
	 * Soft Delete the Device Usage records  in Job Rerun Case
	 */
	@Override
	public void deleteDeviceUsageRecords(Exchange exchange) {

		log.info("Inside deleteDeviceUsageRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

		Query searchJobQuery = new Query(Criteria.where("carrierName").is(
				jobDetail.getCarrierName())).addCriteria(
				Criteria.where("date").is(jobDetail.getDate())).addCriteria(
				Criteria.where("isValid").is(true));

		Update update = new Update();

		update.set("isValid", false);

		WriteResult result = mongoTemplate.updateMulti(searchJobQuery, update,
				DeviceUsage.class);

		log.info("WriteResult ............." + result);

	}

	/**
	 * Soft Delete the Device Connection History records  in Job Rerun Case
	 */
	@Override
	public void deleteDeviceConnectionHistoryRecords(Exchange exchange) {

		log.info("Inside deleteDeviceConnectionRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName())).addCriteria(
					Criteria.where("date").is(jobDetail.getDate()))
					.addCriteria(Criteria.where("isValid").is(true));

			Update update = new Update();

			update.set("isValid", false);

			WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
					update, DeviceConnection.class);

			log.info("WriteResult *********************" + result);

		}

		catch (Exception e) {
			log.error("Error In deleteDeviceConnectionHistoryRecords-----------------------------");
		}

	}

	@Override
	public List fetchTransactionFailureDevices(Exchange exchange) {
		JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

		exchange.setProperty("jobName", jobDetail.getName().toString());

		List list = null;

		try {

			String carrierName = jobDetail.getCarrierName();

			log.info("Carrier Name -----------------" + carrierName);
			// We have to check bs_carrier with possible reseller values for
			// that carrier.
			Query searchQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName()))
					.addCriteria(Criteria.where("date").is(jobDetail.getDate()))
					.addCriteria(
							Criteria.where("transactionStatus").is(
									IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
					.addCriteria(Criteria.where("isValid").is(true));

			if (JobName.KORE_DEVICE_USAGE.toString().equalsIgnoreCase(
					jobDetail.getName().toString())
					|| JobName.VERIZON_DEVICE_USAGE.toString()
							.equalsIgnoreCase(jobDetail.getName().toString())) {
				list = mongoTemplate.find(searchQuery, DeviceUsage.class);
			} else {
				list = mongoTemplate.find(searchQuery, DeviceConnection.class);

			}

		}

		catch (Exception e) {
			log.error("Error In Fetching Transactioon Failure Device Usage/Device Connection Records-----------------------------"+e);
		}

		return list;

	}

	/**
	 * Soft Delete the Transaction Failure records for Device Usage
	 */

	@Override
	public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange) {
		log.info("Inside deleteTransactionFailureDeviceUsageRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName()))
					.addCriteria(Criteria.where("date").is(jobDetail.getDate()))
					.addCriteria(
							Criteria.where("transactionStatus").is(
									IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
					.addCriteria(Criteria.where("isValid").is(true));

			Update update = new Update();

			update.set("isValid", false);

			WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
					update, DeviceUsage.class);

			log.info("WriteResult *********************" + result);

		}

		catch (Exception e) {
			log.error("Error In Deleting Device Usage Records-----------------------------"+e);
		}

	}

	/**
	 * Soft Delete the Transaction Failure records for Device Connection
	 */
	@Override
	public void deleteTransactionFailureDeviceConnectionHistoryRecords(
			Exchange exchange) {
		log.info("Inside deleteTransactionFailureDeviceConnectionHistoryRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName()))
					.addCriteria(Criteria.where("date").is(jobDetail.getDate()))
					.addCriteria(
							Criteria.where("transactionStatus").is(
									IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
					.addCriteria(Criteria.where("isValid").is(true));

			Update update = new Update();

			update.set("isValid", false);

			WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
					update, DeviceConnection.class);

			log.info("WriteResult *********************" + result);

		}

		catch (Exception e) {
			log.error("Error In Saving Job Detail-----------------------------");
		}

	}

	/**
	 * Fetch the Stored server Ip Address from the Database
	 */
	@Override
	public ServerDetail fetchServerIp(String currentServerIp) {

		log.info("Inside fetchServerIp.....................");

		ServerDetail serverDetail = null;

		Query searchDeviceQuery = new Query(Criteria.where("ipAddress").is(
				currentServerIp));

		serverDetail = mongoTemplate.findOne(searchDeviceQuery,
				ServerDetail.class);

		return serverDetail;

	}

	/**
	 * This Method returns the notification if the device Usage exceed the
	 * billing plan
	 */

	public void processDeviceNotification(Exchange exchange) {

		DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
				.getBody();
		String billingDay = null;
		String billingStartDate = null;
		Integer netSuiteId = deviceInfo.getNetSuiteId();
		if (deviceInfo.getBs_plan() != null) {

			JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
			billingDay = deviceInfo.getBs_plan().getBill_day();
			billingStartDate = CommonUtil.getDeviceBillingStartDate(billingDay,
					jobDetail.getDate());

			Aggregation agg = newAggregation(
					match(Criteria.where("netSuiteId").is(netSuiteId)
							.and("date").gte(billingStartDate).and("isValid")
							.is(true)),
					group("netSuiteId").sum("dataUsed").as("dataUsed"),
					project("dataUsed").and("netSuiteId").previousOperation());

			AggregationResults<DeviceOverageNotification> results = mongoTemplate
					.aggregate(agg, DeviceUsage.class,
							DeviceOverageNotification.class);

			Iterator itr = results.iterator();
			while (itr.hasNext()) {
				DeviceOverageNotification element = (DeviceOverageNotification) itr
						.next();
				// setting Billing Date
				element.setDate(billingStartDate);

				mongoTemplate.save(element);
			}
		}

	}

	/**
	 * Inserting the Dummy Records in Device Usage and DeviceInfo Collection for
	 * Testing
	 */
	public void insertBulkRecords() {

		for (int i = 0; i < 1220000; i++) {
			DeviceUsage deviceUsage = new DeviceUsage();
			deviceUsage.setCarrierName("VERIZON");
			deviceUsage.setDataUsed(r.nextInt((100 - 10) + 1) + 10);
			deviceUsage.setNetSuiteId(new Integer(
					(r.nextInt((4000 - 4) + 1) + 4) + ""));
			deviceUsage.setDate((new Date(new Date().getTime() - 1000 * 60 * 60
					* r.nextInt((120 - 90) + 91))).toString());
			mongoTemplate.save(deviceUsage);
			// ystem.out.println(i);
		}

		/*
		 * for(int i=0;i<20000;i++){ DeviceInformation deviceUsage=new
		 * DeviceInformation(); deviceUsage.setBs_carrier("VERIZON");
		 * //deviceUsage.setDataUsed(r.nextInt((100 - 10) + 1) + 10);
		 * deviceUsage.setNetSuiteId(new String((r.nextInt((4000 - 4) + 1) +
		 * 4)+"")); //deviceUsage(new Date(new
		 * Date().getTime()-1000*60*60*r.nextInt((30 - 10) ) ));
		 * mongoTemplate.save(deviceUsage); //ystem.out.println(i); }
		 */

	}

}
