/*
 * This Class is Developed to Interact with DB for all the Operations related to Batch Jobs
 */

package com.gv.midway.dao.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

    private static final Logger LOGGER = Logger.getLogger(JobDaoImpl.class);
    static Random r = new Random();

    /**
     * Fetch all the devices
     */
    @Override
    public List fetchDevices(Exchange exchange) {

        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        exchange.setProperty("jobName", jobDetail.getName().toString());
        
        exchange.setProperty("carrierName", jobDetail.getCarrierName());
        
        exchange.setProperty("jobType", jobDetail.getType().toString());
        
        List<DeviceInformation> timeOutDeviceList=new ArrayList<DeviceInformation>();
        
        exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList );

        List<DeviceInformation> deviceInformationList = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchDeviceQuery = new Query(Criteria.where("bs_carrier").regex(carrierName, "i"));

            deviceInformationList = mongoTemplate.find(searchDeviceQuery,
                    DeviceInformation.class);

            LOGGER.info("deviceInformationList ------------------"
                    + deviceInformationList.size());
        }

        catch (Exception e) {
            LOGGER.error("Error in fetchDevices :" + e);
        }

        return deviceInformationList;
    }

    /**
     * Fetch only devices with Odd Net Suited ID
     */
    @Override
    public List fetchOddDevices(Exchange exchange) {

        LOGGER.info("fetchOddDevices::::::::");

        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        exchange.setProperty("jobName", jobDetail.getName().toString());
        
        exchange.setProperty("carrierName", jobDetail.getCarrierName());
        
        exchange.setProperty("jobType", jobDetail.getType().toString());
        
        List<DeviceInformation> timeOutDeviceList=new ArrayList<DeviceInformation>();
        
        exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList );

        List<DeviceInformation> deviceInformationList = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchDeviceQuery = new Query(Criteria.where("bs_carrier").regex(carrierName, "i"))
                    .addCriteria(Criteria.where("netSuiteId")
                    .mod(2, 1));

            LOGGER.info("searchDeviceQuery::::::::::" + searchDeviceQuery);

            deviceInformationList = mongoTemplate.find(searchDeviceQuery,
                    DeviceInformation.class);

            LOGGER.info("deviceInformationList ------------------"
                    + deviceInformationList.size());
        }

        catch (Exception e) {

            LOGGER.error("Error in fetchOddDevices :" + e);
        }

        return deviceInformationList;
    }

    /**
     * Fetch only devices with Even Net Suited ID
     */
    @Override
    public List fetchEvenDevices(Exchange exchange) {

        LOGGER.info("fetchEvenDevices::::::::");

        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        exchange.setProperty("jobName", jobDetail.getName().toString());
        
        exchange.setProperty("carrierName", jobDetail.getCarrierName());
        
        exchange.setProperty("jobType", jobDetail.getType().toString());
        
        List<DeviceInformation> timeOutDeviceList=new ArrayList<DeviceInformation>();
        
        exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList );

        List<DeviceInformation> deviceInformationList = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchDeviceQuery = new Query(Criteria.where("bs_carrier").regex(carrierName, "i"))
                    .addCriteria(Criteria.where("netSuiteId")
                    .mod(2, 0));
            ;

            deviceInformationList = mongoTemplate.find(searchDeviceQuery,
                    DeviceInformation.class);

            LOGGER.info("deviceInformationList ------------------"
                    + deviceInformationList.size());
        }

        catch (Exception e) {

            LOGGER.error("Error in fetchEvenDevices :" + e);
        }

        return deviceInformationList;
    }

    /**
     * Inserting the Job Details and setting date parameters
     */
    @Override
    public void insertJobDetails(Exchange exchange) {
        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        LOGGER.info("-----------Job Details -------" + jobDetail.toString());
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

        LOGGER.info("Inside updateJobDetails .....................");

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
            LOGGER.info("Error In updateJobDetails-----------------------------"
                    + e);
        }

    }

    /**
     * Soft Delete the Device Usage records in Job Rerun Case
     */
    @Override
    public void deleteDeviceUsageRecords(Exchange exchange) {

        LOGGER.info("Inside deleteDeviceUsageRecords .....................");

        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

        Query searchJobQuery = new Query(Criteria.where("carrierName").regex(jobDetail.getCarrierName(), "i")).addCriteria(
                Criteria.where("date").is(jobDetail.getDate())).addCriteria(
                Criteria.where("isValid").is(true));

        Update update = new Update();

        update.set("isValid", false);

        WriteResult result = mongoTemplate.updateMulti(searchJobQuery, update,
                DeviceUsage.class);

        LOGGER.info("WriteResult ............." + result);

    }

    /**
     * Soft Delete the Device Connection History records in Job Rerun Case
     */
    @Override
    public void deleteDeviceConnectionHistoryRecords(Exchange exchange) {

        LOGGER.info("Inside deleteDeviceConnectionRecords .....................");

        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
        try {

            Query searchJobQuery = new Query(Criteria.where("carrierName").regex(
                    jobDetail.getCarrierName(),"i")).addCriteria(
                    Criteria.where("date").is(jobDetail.getDate()))
                    .addCriteria(Criteria.where("isValid").is(true));

            Update update = new Update();

            update.set("isValid", false);

            WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
                    update, DeviceConnection.class);

            LOGGER.info("WriteResult *********************" + result);

        }

        catch (Exception e) {
            LOGGER.error("Error In deleteDeviceConnectionHistoryRecords-----------------------------"
                    + e);
        }

    }

    @Override
    public List fetchTransactionFailureDevices(Exchange exchange) {
        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        exchange.setProperty("jobName", jobDetail.getName().toString());

        List list = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchQuery = new Query(Criteria.where("carrierName").regex(
                    jobDetail.getCarrierName(),"i"))
                    .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                    .addCriteria(
                            Criteria.where("transactionStatus").is(
                                    IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
                    .addCriteria(Criteria.where("isValid").is(true));

            if (JobName.KORE_DEVICE_USAGE.toString().equalsIgnoreCase(
                    jobDetail.getName().toString())
                    || JobName.VERIZON_DEVICE_USAGE.toString()
                            .equalsIgnoreCase(jobDetail.getName().toString())) {
            	
            	 List<DeviceUsage> timeOutDeviceList=new ArrayList<DeviceUsage>();
                 
                 exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList );

                list = mongoTemplate.find(searchQuery, DeviceUsage.class);
            } else {
                 List<DeviceConnection> timeOutDeviceList=new ArrayList<DeviceConnection>();
                 
                 exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList );
                list = mongoTemplate.find(searchQuery, DeviceConnection.class);

            }

        }

        catch (Exception e) {
            LOGGER.error("Error In Fetching Transactioon Failure Device Usage/Device Connection Records-----------------------------"
                    + e);
        }

        return list;

    }

    /**
     * Soft Delete the Transaction Failure records for Device Usage
     */

    @Override
    public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange) {
        LOGGER.info("Inside deleteTransactionFailureDeviceUsageRecords .....................");

        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
        try {

            Query searchJobQuery = new Query(Criteria.where("carrierName").regex(
                    jobDetail.getCarrierName(),"i"))
                    .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                    .addCriteria(
                            Criteria.where("transactionStatus").is(
                                    IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
                    .addCriteria(Criteria.where("isValid").is(true));

            Update update = new Update();

            update.set("isValid", false);

            WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
                    update, DeviceUsage.class);

            LOGGER.info("WriteResult *********************" + result);

        }

        catch (Exception e) {
            LOGGER.error("Error In Deleting Device Usage Records-----------------------------"
                    + e);
        }

    }

    /**
     * Soft Delete the Transaction Failure records for Device Connection
     */
    @Override
    public void deleteTransactionFailureDeviceConnectionHistoryRecords(
            Exchange exchange) {
        LOGGER.info("Inside deleteTransactionFailureDeviceConnectionHistoryRecords .....................");

        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
        try {

            Query searchJobQuery = new Query(Criteria.where("carrierName").regex(
                    jobDetail.getCarrierName(),"i"))
                    .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                    .addCriteria(
                            Criteria.where("transactionStatus").is(
                                    IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
                    .addCriteria(Criteria.where("isValid").is(true));

            Update update = new Update();

            update.set("isValid", false);

            WriteResult result = mongoTemplate.updateMulti(searchJobQuery,
                    update, DeviceConnection.class);

            LOGGER.info("WriteResult *********************" + result);

        }

        catch (Exception e) {
            LOGGER.error("Error In Saving Job Detail-----------------------------"+e);
        }

    }

    /**
     * Fetch the Stored server Ip Address from the Database
     */
    @Override
    public ServerDetail fetchServerIp(String currentServerIp) {

        LOGGER.info("Inside fetchServerIp.....................");

        ServerDetail serverDetail;

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

    @Override
    public void processDeviceNotification(Exchange exchange) {

        DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
                .getBody();
        List<DeviceOverageNotification> notificationList = (List) exchange
                .getProperty("NotificationLsit");
        String billingDay;
        String billingStartDate;
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
                notificationList.add(element);
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

    /**
     * Insert timeOut devices in Usage
     */
	@Override
	public void insertTimeOutUsageRecords(Exchange exchange) {
		
		 JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		 
		 List<DeviceInformation> timeOutDeviceList= (List<DeviceInformation>) exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
		 
		// Add timeOut devices in a Set with NetSuiteId

		Set<Integer> timeOutDeviceSet = new HashSet<Integer>();

		for (DeviceInformation deviceInformation : timeOutDeviceList) {
			LOGGER.info("timeOut devices ..........."
					+ deviceInformation.getNetSuiteId());
			timeOutDeviceSet.add(deviceInformation.getNetSuiteId());
		}
		 
		 String carrierName= (String) exchange.getProperty("carrierName");
		
		 
		 // find  the timeout devices that are in device usage Collection
		 Query searchJobQuery = new Query(Criteria.where("carrierName").regex(jobDetail.getCarrierName(), "i")).addCriteria(
	                Criteria.where("date").is(jobDetail.getDate())).addCriteria(
	                Criteria.where("isValid").is(true)).addCriteria(
	    	                Criteria.where("netSuiteId").in(timeOutDeviceSet));
		 
		 List<DeviceUsage> deviceUsageList=mongoTemplate.find(searchJobQuery, DeviceUsage.class);
		 
		 // Add timeOut devices from DB in a Set with NetSuiteId
		 Set<Integer> timeOutDeviceSetInDB=new HashSet<Integer>();
		 
		 for (DeviceUsage deviceUsage : deviceUsageList) 
		 {
			 LOGGER.info("timeOut devices in usage..........."+deviceUsage.getNetSuiteId());
			 timeOutDeviceSetInDB.add(deviceUsage.getNetSuiteId());
		 } 
		
		 // find all timeOut device not in device usage Collection 
		 List<DeviceUsage> timeOutDevicesNotInUsage=new ArrayList<DeviceUsage>();
		 for (DeviceInformation deviceInformation : timeOutDeviceList) {
			 
			
			if(!timeOutDeviceSetInDB.contains(deviceInformation.getNetSuiteId()))
			{
		    LOGGER.info("timeOut devices Not in usage..........."+deviceInformation.getNetSuiteId());
			DeviceUsage deviceUsage=new DeviceUsage();
			deviceUsage.setCarrierName(carrierName);
	       
	        deviceUsage.setDataUsed(0);
	        // The Day for which Job Ran

	        deviceUsage.setDate(jobDetail.getDate());
	        deviceUsage.setTransactionErrorReason("TimeOut Error");
	        deviceUsage
	                .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
	        deviceUsage.setNetSuiteId(deviceInformation.getNetSuiteId());
	        deviceUsage.setIsValid(true);
	        
	        if(carrierName.equalsIgnoreCase("KORE")){
	        	
	        	
	        	deviceUsage.setDeviceId(CommonUtil.getSimNumber(deviceInformation.getDeviceIds()));
	        }
	        
	        else
	        {
	        	deviceUsage.setDeviceId(CommonUtil.getRecommendedDeviceIdentifier(deviceInformation.getDeviceIds()));
	        	
	        }
	      
	        timeOutDevicesNotInUsage.add(deviceUsage);
			}
		}
		 // Add all the timeOut Devices that are not in Device Usage Collection
		 if(timeOutDevicesNotInUsage.size()>0)
		 {
			 LOGGER.info("Adding timeOut devices Not in usage..........."+timeOutDevicesNotInUsage.size());
			 mongoTemplate.insertAll(timeOutDevicesNotInUsage); 
			 
		 }
		 
	}

	/**
     * Insert timeOut devices in Connection
     */
	@Override
	public void insertTimeOutConnectionRecords(Exchange exchange) {
		// TODO Auto-generated method stub
        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		 
		 List<DeviceInformation> timeOutDeviceList= (List<DeviceInformation>) exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
		 
		// Add timeOut devices in a Set with NetSuiteId
		 
        Set<Integer> timeOutDeviceSet=new HashSet<Integer>();
		 
		 for (DeviceInformation deviceInformation : timeOutDeviceList) 
		 {
			 LOGGER.info("timeOut devices ..........."+deviceInformation.getNetSuiteId());
			 timeOutDeviceSet.add(deviceInformation.getNetSuiteId());
		 } 
		 
		 String carrierName= (String) exchange.getProperty("carrierName");
		
		 
		 // find  the timeout devices that are in device Connection Collection
		 Query searchJobQuery = new Query(Criteria.where("carrierName").regex(jobDetail.getCarrierName(), "i")).addCriteria(
	                Criteria.where("date").is(jobDetail.getDate())).addCriteria(
	                Criteria.where("isValid").is(true)).addCriteria(
	    	                Criteria.where("netSuiteId").in(timeOutDeviceSet));
		 
		 List<DeviceConnection> deviceConnectionList=mongoTemplate.find(searchJobQuery, DeviceConnection.class);
		 
		 // Add timeOut devices from DB in a Set with NetSuiteId
		 Set<Integer> timeOutDeviceSetInDB=new HashSet<Integer>();
		 
		 for (DeviceConnection deviceConnection : deviceConnectionList) 
		 {
			 LOGGER.info("timeOut devices in Connection..........."+deviceConnection.getNetSuiteId());
			 timeOutDeviceSetInDB.add(deviceConnection.getNetSuiteId());
		 } 
		
		 // find all timeOut device not in device connection Collection 
		 List<DeviceConnection> timeOutDevicesNotInConnection=new ArrayList<DeviceConnection>();
		 for (DeviceInformation deviceInformation : timeOutDeviceList) {
			 
			
			if(!timeOutDeviceSetInDB.contains(deviceInformation.getNetSuiteId()))
			{
		    LOGGER.info("timeOut devices Not in Connection..........."+deviceInformation.getNetSuiteId());
			DeviceConnection deviceConnection=new DeviceConnection();
			deviceConnection.setCarrierName(carrierName);
	       

			deviceConnection.setDate(jobDetail.getDate());
			deviceConnection.setTransactionErrorReason("TimeOut Error");
	        deviceConnection
	                .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
	        deviceConnection.setNetSuiteId(deviceInformation.getNetSuiteId());
	        deviceConnection.setIsValid(true);
	        deviceConnection.setDeviceId(CommonUtil.getRecommendedDeviceIdentifier(deviceInformation.getDeviceIds()));
	        
	       
	      
	        timeOutDevicesNotInConnection.add(deviceConnection);
			}
		}
		 // Add all the timeOut Devices that are not in Device Connection Collection
		 if(timeOutDevicesNotInConnection.size()>0)
		 {
			 LOGGER.info("Adding timeOut devices Not in Connection..........."+timeOutDevicesNotInConnection.size());
			 mongoTemplate.insertAll(timeOutDevicesNotInConnection); 
			 
		 }
	}

	/**
     * Insert TransactionFailure timeOut devices in Usage
     */
	@Override
	public void insertTimeOutUsageRecordsTransactionFailure(Exchange exchange) {
		// TODO Auto-generated method stub
		
        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		 
		 List<DeviceUsage> timeOutDeviceListTransactionFailure= (List<DeviceUsage>) exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
		 
		// Add timeOut devices in a Set with NetSuiteId

		Set<Integer> timeOutDeviceSetTransactionFailure = new HashSet<Integer>();

		for (DeviceUsage deviceUsage : timeOutDeviceListTransactionFailure) {
			LOGGER.info("timeOut devices TransactionFailure ..........."
					+ deviceUsage.getNetSuiteId());
			timeOutDeviceSetTransactionFailure.add(deviceUsage.getNetSuiteId());
		}
		 
		 String carrierName= (String) exchange.getProperty("carrierName");
		
		 
		 // find  the TransactionFailure timeout devices that are in device usage Collection
		 Query searchJobQuery = new Query(Criteria.where("carrierName").regex(jobDetail.getCarrierName(), "i")).addCriteria(
	                Criteria.where("date").is(jobDetail.getDate())).addCriteria(
	                Criteria.where("isValid").is(true)).addCriteria(
	    	                Criteria.where("netSuiteId").in(timeOutDeviceSetTransactionFailure));
		 
		 List<DeviceUsage> deviceUsageListTransactionFailure=mongoTemplate.find(searchJobQuery, DeviceUsage.class);
		 
		 // Add timeOut devices from DB in a Set with NetSuiteId
		 Set<Integer> timeOutDeviceSetInDB=new HashSet<Integer>();
		 
		 for (DeviceUsage deviceUsage : deviceUsageListTransactionFailure) 
		 {
			 LOGGER.info("timeOut devices TransactionFailure in usage..........."+deviceUsage.getNetSuiteId());
			 timeOutDeviceSetInDB.add(deviceUsage.getNetSuiteId());
		 } 
		
		 // find all timeOut device not in device usage Collection 
		 List<DeviceUsage> timeOutDevicesNotInUsage=new ArrayList<DeviceUsage>();
		 for (DeviceUsage deviceUsage : timeOutDeviceListTransactionFailure) {
			 
			
			if(!timeOutDeviceSetInDB.contains(deviceUsage.getNetSuiteId()))
			{
		    LOGGER.info("timeOut devices Not in usage TransactionFailure..........."+deviceUsage.getNetSuiteId());
			
	       
	        deviceUsage.setDataUsed(0);
	       
	        deviceUsage.setDate(jobDetail.getDate());
	        deviceUsage.setTransactionErrorReason("TimeOut Error");
	        deviceUsage
	                .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
	       
	        deviceUsage.setIsValid(true);
	        
	      
	        timeOutDevicesNotInUsage.add(deviceUsage);
			}
		}
		 // Add all the timeOut Devices that are not in Device Usage Collection
		 if(timeOutDevicesNotInUsage.size()>0)
		 {
			 LOGGER.info("Adding timeOut devices Not in usage..........."+timeOutDevicesNotInUsage.size());
			 mongoTemplate.insertAll(timeOutDevicesNotInUsage); 
			 
		 }
	}

	/**
     * Insert TransactionFailure timeOut devices in Connection
     */
	@Override
	public void insertTimeOutConnectionRecordsTransactionFailure(
			Exchange exchange) {
		// TODO Auto-generated method stub
		
		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		 
		 List<DeviceConnection> timeOutDeviceTransactionFailureList= (List<DeviceConnection>) exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST);
		 
		// Add timeOut devices in a Set with NetSuiteId
		 
       Set<Integer> timeOutDeviceSetTransactionFailure=new HashSet<Integer>();
		 
		 for (DeviceConnection deviceConnection : timeOutDeviceTransactionFailureList) 
		 {
			 LOGGER.info("timeOut devices TransactionFailure ..........."+deviceConnection.getNetSuiteId());
			 timeOutDeviceSetTransactionFailure.add(deviceConnection.getNetSuiteId());
		 } 
		 
		 String carrierName= (String) exchange.getProperty("carrierName");
		
		 
		 // find  the timeout devices that are in device Connection Collection
		 Query searchJobQuery = new Query(Criteria.where("carrierName").regex(jobDetail.getCarrierName(), "i")).addCriteria(
	                Criteria.where("date").is(jobDetail.getDate())).addCriteria(
	                Criteria.where("isValid").is(true)).addCriteria(
	    	                Criteria.where("netSuiteId").in(timeOutDeviceSetTransactionFailure));
		 
		 List<DeviceConnection> deviceConnectionListTransactionFailure=mongoTemplate.find(searchJobQuery, DeviceConnection.class);
		 
		 // Add timeOut devices from DB in a Set with NetSuiteId
		 Set<Integer> timeOutDeviceSetInDB=new HashSet<Integer>();
		 
		 for (DeviceConnection deviceConnection : deviceConnectionListTransactionFailure) 
		 {
			 LOGGER.info("timeOut devices TransactionFailure in Connection..........."+deviceConnection.getNetSuiteId());
			 timeOutDeviceSetInDB.add(deviceConnection.getNetSuiteId());
		 } 
		
		 // find all timeOut device not in device connection Collection 
		 List<DeviceConnection> timeOutDevicesNotInConnection=new ArrayList<DeviceConnection>();
		 for (DeviceConnection deviceConnection : timeOutDeviceTransactionFailureList) {
			 
			
			if(!timeOutDeviceSetInDB.contains(deviceConnection.getNetSuiteId()))
			{
		    LOGGER.info("timeOut devices Not in Connection TransactionFailure..........."+deviceConnection.getNetSuiteId());
			
			deviceConnection.setDate(jobDetail.getDate());
			deviceConnection.setTransactionErrorReason("TimeOut Error");
	        deviceConnection
	                .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
	       
	        deviceConnection.setIsValid(true);
	       
	        timeOutDevicesNotInConnection.add(deviceConnection);
			}
		}
		 // Add all the timeOut Devices that are not in Device Connection Collection
		 if(timeOutDevicesNotInConnection.size()>0)
		 {
			 LOGGER.info("Adding timeOut devices Not in Connection..........."+timeOutDevicesNotInConnection.size());
			 mongoTemplate.insertAll(timeOutDevicesNotInConnection); 
			 
		 }
	}

}
