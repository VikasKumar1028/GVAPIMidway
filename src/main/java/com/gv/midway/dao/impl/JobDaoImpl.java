package com.gv.midway.dao.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.text.SimpleDateFormat;
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
import com.gv.midway.pojo.server.ServerDetail;
import com.gv.midway.utility.CommonUtil;
import com.mongodb.WriteResult;

@Service
public class JobDaoImpl implements IJobDao {

	@Autowired
	MongoTemplate mongoTemplate;

	Logger log = Logger.getLogger(JobDaoImpl.class);
	static Random r = new Random();
	
	static int numbers=0;

	// @Override
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

		//list  =	deviceInformationList.subList(0, 1000);
			
			
			
			
			/*
			 * list = new
			 * ArrayList<DeviceInformation>(Collections.nCopies(10000,
			 * deviceInformationList.get(0)));
			 */

		/*	list = new ArrayList<DeviceInformation>(Collections.nCopies(10,
					deviceInformationList.get(0)));*/

			log.info("deviceInformationList ------------------"
					+ deviceInformationList.size());
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		// return list;
		return deviceInformationList;
	}

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

			e.printStackTrace();
			System.out.println("e");
		}

		// return list;
		return deviceInformationList;
	}

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
			e.printStackTrace();
			log.info("Exception" + e);
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
		// inserting in the exchange as property
		exchange.setProperty("jobDetail", jobDetail);
		exchange.setProperty("jobDetailDate", jobDetail.getDate());

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
					jobDetail.getCarrierName())).addCriteria(
					Criteria.where("timestamp").is(jobDetail.getDate()))
					.addCriteria(Criteria.where("isValid").is(true));

			Update update = new Update();

			update.set("isValid", false);

			WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
					update, DeviceUsage.class);

			log.info("WriteResult *********************" + result);

		}

		catch (Exception e) {
			log.info("Error In Saving Job Detail-----------------------------");
		}

	}

	@Override
	public void deleteDeviceConnectionHistoryRecords(Exchange exchange) {

		log.info("Inside deleteDeviceConnectionRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName())).addCriteria(
					Criteria.where("timestamp").is(jobDetail.getDate()))
					.addCriteria(Criteria.where("isValid").is(true));

			Update update = new Update();

			update.set("isValid", false);

			WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
					update, DeviceConnection.class);

			log.info("WriteResult *********************" + result);

		}

		catch (Exception e) {
			log.info("Error In Saving Job Detail-----------------------------");
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
					.addCriteria(
							Criteria.where("timestamp").is(jobDetail.getDate()))
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

			/*
			 * list = new
			 * ArrayList<DeviceInformation>(Collections.nCopies(10000,
			 * deviceInformationList.get(0)));
			 */

		}

		catch (Exception e) {
			System.out.println("e");
		}

		// return list;
		return list;

	}

	@Override
	public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange) {
		log.info("Inside deleteTransactionFailureDeviceUsageRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName()))
					.addCriteria(
							Criteria.where("timestamp").is(jobDetail.getDate()))
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
			log.info("Error In Saving Job Detail-----------------------------");
		}

	}

	@Override
	public void deleteTransactionFailureDeviceConnectionHistoryRecords(
			Exchange exchange) {
		log.info("Inside deleteTransactionFailureDeviceConnectionHistoryRecords .....................");

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		try {

			Query searchJobQuery = new Query(Criteria.where("carrierName").is(
					jobDetail.getCarrierName()))
					.addCriteria(
							Criteria.where("timestamp").is(jobDetail.getDate()))
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
			log.info("Error In Saving Job Detail-----------------------------");
		}

	}

	@Override
	public ServerDetail fetchServerIp(String currentServerIp) {
		// TODO Auto-generated method stub

		log.info("Inside fetchServerIp.....................");

		log.info("currentServerIp:::::::" + currentServerIp);

		ServerDetail serverDetail = null;

		Query searchDeviceQuery = new Query(Criteria.where("ipAddress").is(
				currentServerIp));

		log.info("searchDeviceQuery:::::::::::" + searchDeviceQuery);

		serverDetail = mongoTemplate.findOne(searchDeviceQuery,
				ServerDetail.class);

		return serverDetail;

	}
	
	public void processDeviceNotification(Exchange exchange){
		
		System.out.println("*********processDeviceNotification*****************");
		
		DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
				.getBody();
		
		String netSuiteId=deviceInfo.getNetSuiteId();
		//String billingDay=deviceInfo.getBs_plan().getBill_day();
		String billingDay=null;
		String valuee="2016-05-30";
		Date billingStartDate =null;
	/*	try{
		
		
       billingStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").parse(valuee);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}*/
		//Date billingStartDate=new Date("30-05-2016");
		
		

	
		Aggregation agg = newAggregation(
				match(Criteria.where("netSuiteId").is(netSuiteId)),
				//.and("timestamp").gte(billingStartDate)),
				group("netSuiteId").sum("dataUsed").as("dataUsed"),
				project("dataUsed").and("netSuiteId").previousOperation()
				
				);
				
		AggregationResults<DeviceUsage> results=mongoTemplate.aggregate(agg,  DeviceUsage.class, DeviceUsage.class);
		
		Iterator itr = results.iterator();
	      while(itr.hasNext()) {
	    	  DeviceUsage element = (DeviceUsage)itr.next();
	         System.out.print(element.getDataUsed() + " ----------------------------------------" +element.getNetSuiteId());
	  
	     	mongoTemplate.save(element, "Notification");
	      }
		System.out.println("*********processDeviceNotification*****dd************");
		
		

		
		
		
		
	}
	
	
	public void insertBulkRecords(){

		

		for(int i=0;i<1220000;i++){
		DeviceUsage deviceUsage=new DeviceUsage();
		deviceUsage.setCarrierName("VERIZON");
		deviceUsage.setDataUsed(r.nextInt((100 - 10) + 1) + 10);
		deviceUsage.setNetSuiteId(new String((r.nextInt((4000 - 4) + 1) + 4)+""));
		deviceUsage.setDate(new Date(new Date().getTime()-1000*60*60*r.nextInt((120 - 90)+91 ) ));
		mongoTemplate.save(deviceUsage);
		//ystem.out.println(i);
		}
		
		/*
		for(int i=0;i<20000;i++){
		DeviceInformation deviceUsage=new DeviceInformation();
		deviceUsage.setBs_carrier("VERIZON");
		//deviceUsage.setDataUsed(r.nextInt((100 - 10) + 1) + 10);
		deviceUsage.setNetSuiteId(new String((r.nextInt((4000 - 4) + 1) + 4)+""));
		//deviceUsage(new Date(new Date().getTime()-1000*60*60*r.nextInt((30 - 10) ) ));
		mongoTemplate.save(deviceUsage);
		//ystem.out.println(i);
		}
		*/
		
		
	}
	
	
	
}
