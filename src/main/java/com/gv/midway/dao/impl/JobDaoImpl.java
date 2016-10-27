/*
 * This Class is Developed to Interact with DB for all the Operations related to Batch Jobs
 */

package com.gv.midway.dao.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;







import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobStatus;
import com.gv.midway.pojo.notification.DeviceOverageNotification;
import com.gv.midway.pojo.server.ServerDetail;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.pojo.usageInformation.request.DevicesUsageByDayAndCarrierRequest;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrier;
import com.gv.midway.pojo.usageView.DeviceUsageView;
import com.gv.midway.pojo.usageView.DeviceUsageViewElement;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;
import com.mongodb.WriteResult;

import org.apache.camel.Message;

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
    public List<DeviceInformation> fetchDevices(Exchange exchange) {

        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        exchange.setProperty(IConstant.JOB_NAME, jobDetail.getName().toString());

        exchange.setProperty(IConstant.CARRIER_NAME, jobDetail.getCarrierName());

        exchange.setProperty(IConstant.JOB_TYPE, jobDetail.getType().toString());

        List<DeviceInformation> timeOutDeviceList = new ArrayList<DeviceInformation>();

        exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList);

        List<DeviceInformation> deviceInformationList = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchDeviceQuery = new Query(Criteria.where("bs_carrier")
                    .regex(carrierName, "i"));

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
    public List<DeviceInformation> fetchOddDevices(Exchange exchange) {

        LOGGER.info("fetchOddDevices::::::::");

        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        exchange.setProperty(IConstant.JOB_NAME, jobDetail.getName().toString());

        exchange.setProperty(IConstant.CARRIER_NAME, jobDetail.getCarrierName());

        exchange.setProperty(IConstant.JOB_TYPE, jobDetail.getType().toString());

        List<DeviceInformation> timeOutDeviceList = new ArrayList<DeviceInformation>();

        exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList);

        List<DeviceInformation> deviceInformationList = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchDeviceQuery = new Query(Criteria.where("bs_carrier")
                    .regex(carrierName, "i")).addCriteria(Criteria.where(
                    "netSuiteId").mod(2, 1));

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
    public List<DeviceInformation> fetchEvenDevices(Exchange exchange) {

        LOGGER.info("fetchEvenDevices::::::::");

        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();

        exchange.setProperty(IConstant.JOB_NAME, jobDetail.getName().toString());

        exchange.setProperty(IConstant.CARRIER_NAME, jobDetail.getCarrierName());

        exchange.setProperty(IConstant.JOB_TYPE, jobDetail.getType().toString());

        List<DeviceInformation> timeOutDeviceList = new ArrayList<DeviceInformation>();

        exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST, timeOutDeviceList);

        List<DeviceInformation> deviceInformationList = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchDeviceQuery = new Query(Criteria.where("bs_carrier")
                    .regex(carrierName, "i")).addCriteria(Criteria.where(
                    "netSuiteId").mod(2, 0));
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
        exchange.setProperty(IConstant.JOB_DETAIL, jobDetail);
        exchange.setProperty(IConstant.JOB_DETAIL_DATE, jobDetail.getDate());

        // generating the job ID to recognize the job
        long timestamp = System.currentTimeMillis();
        String jobId = Long.toString(timestamp);
        jobDetail.setJobId(jobDetail.getName() + "_" + jobId);

        // inserting in the database as property
        mongoTemplate.insert(jobDetail);

    }

    /**
     * Updating the Job Details parameters
     */
    @Override
    public void updateJobDetails(Exchange exchange) {

        LOGGER.info("Inside updateJobDetails .....................");

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

        if ("VERIZON_CONNECTION_HISTORY".equals(jobDetail.getName().toString())) {

            getConnectionHistoryJobCounts(exchange);
        } else {

            getDeviceUsageJobCounts(exchange);
        }
        try {

            Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).is(
                    jobDetail.getCarrierName()))
                    .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                    .addCriteria(Criteria.where("name").is(jobDetail.getName()))
                    .addCriteria(Criteria.where("type").is(jobDetail.getType()))
                    .addCriteria(
                            Criteria.where("jobId").is(jobDetail.getJobId()))
                    .addCriteria(
                            Criteria.where("startTime").is(
                                    jobDetail.getStartTime()));

            Update update = new Update();

            update.set("endTime", new Date().toString());
            update.set("status", IConstant.JOB_COMPLETED);

            // checking total count
            update.set("transactionCount",
                    exchange.getProperty(IConstant.JOB_TOTAL_COUNT));

            // checking Error Cont
            if (exchange.getProperty(IConstant.JOB_ERROR_COUNT) != null) {
                update.set("transactionFailed",
                        exchange.getProperty(IConstant.JOB_ERROR_COUNT));
            } else {
                update.set("transactionFailed", "0");
            }

            // checking Successful count
            if (exchange.getProperty(IConstant.JOB_SUCCESS_COUNT) != null) {
                update.set("transactionPassed",
                        exchange.getProperty(IConstant.JOB_SUCCESS_COUNT));
            } else {
                update.set("transactionPassed", "0");
            }
            mongoTemplate.updateFirst(searchJobQuery, update, JobDetail.class);

        }

        catch (Exception e) {
            LOGGER.info("Error In updateJobDetails-----------------------------"
                    + e);
        }

    }

    /**
     * Updating the Job Details parameters
     */
    @Override
    public void getDeviceUsageJobCounts(Exchange exchange) {

        LOGGER.info("Inside getDeviceUsageJobCounts .....................");

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);
        try {

            Aggregation agg = newAggregation(
                    match(Criteria.where("date").is(jobDetail.getDate())
                            .and("jobId").is(jobDetail.getJobId())
                            .and("isValid").is(true)),
                    group("transactionStatus").count().as("count"),

                    project("count").and("transactionStatus")
                            .previousOperation());

            AggregationResults<JobStatus> results = mongoTemplate.aggregate(
                    agg, DeviceUsage.class, JobStatus.class);

            Iterator itr = results.iterator();
            while (itr.hasNext()) {
                JobStatus element = (JobStatus) itr.next();

                LOGGER.info(element.getTransactionStatus()
                        + "-----------------------------" + element.getCount());
                if (IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS.equals(element
                        .getTransactionStatus())) {
                    exchange.setProperty(IConstant.JOB_SUCCESS_COUNT, element.getCount());
                    LOGGER.info("Success Count-------------"
                            + element.getCount());
                }
                if (IConstant.MIDWAY_TRANSACTION_STATUS_ERROR.equals(element
                        .getTransactionStatus())) {
                    exchange.setProperty(IConstant.JOB_ERROR_COUNT, element.getCount());
                    LOGGER.info("Error Count----------" + element.getCount());
                }

            }

        }

        catch (Exception e) {
            LOGGER.info("Error In updateJobDetails-----------------------------"
                    + e);
        }

    }

    /**
     * Updating the Job Details parameters
     */
    @Override
    public void getConnectionHistoryJobCounts(Exchange exchange) {
        LOGGER.info("Inside getConnectionHistoryJobCounts ");

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

        try {

            Aggregation agg = newAggregation(
                    match(Criteria.where("date").is(jobDetail.getDate())
                            .and("jobId").is(jobDetail.getJobId())
                            .and("isValid").is(true)),
                    group("transactionStatus").count().as("count"),

                    project("count").and("transactionStatus")
                            .previousOperation());

            AggregationResults<JobStatus> results = mongoTemplate.aggregate(
                    agg, DeviceConnection.class, JobStatus.class);

            Iterator itr = results.iterator();
            while (itr.hasNext()) {
                JobStatus element = (JobStatus) itr.next();

                LOGGER.info(element.getTransactionStatus()
                        + "-----------------------------" + element.getCount());
                if (IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS.equals(element
                        .getTransactionStatus())) {
                    exchange.setProperty(IConstant.JOB_SUCCESS_COUNT, element.getCount());
                    LOGGER.info("Success Count-------------"
                            + element.getCount());
                }
                if (IConstant.MIDWAY_TRANSACTION_STATUS_ERROR.equals(element
                        .getTransactionStatus())) {
                    exchange.setProperty(IConstant.JOB_ERROR_COUNT, element.getCount());
                    LOGGER.info("Error Count----------" + element.getCount());
                }

            }

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

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

        Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).regex(
                jobDetail.getCarrierName(), "i")).addCriteria(
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

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);
        try {

            Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME)
                    .regex(jobDetail.getCarrierName(), "i")).addCriteria(
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

        exchange.setProperty(IConstant.JOB_NAME, jobDetail.getName().toString());

        List list = null;

        try {

            String carrierName = jobDetail.getCarrierName();

            LOGGER.info("Carrier Name -----------------" + carrierName);
            // We have to check bs_carrier with possible reseller values for
            // that carrier.
            Query searchQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).regex(
                    jobDetail.getCarrierName(), "i"))
                    .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                    .addCriteria(
                            Criteria.where("transactionStatus").is(
                                    IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
                    .addCriteria(Criteria.where("isValid").is(true));

            if (JobName.KORE_DEVICE_USAGE.toString().equalsIgnoreCase(
                    jobDetail.getName().toString())
                    || JobName.VERIZON_DEVICE_USAGE.toString()
                            .equalsIgnoreCase(jobDetail.getName().toString())) {

                List<DeviceUsage> timeOutDeviceList = new ArrayList<DeviceUsage>();

                exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST,
                        timeOutDeviceList);

                list = mongoTemplate.find(searchQuery, DeviceUsage.class);
            } else {
                List<DeviceConnection> timeOutDeviceList = new ArrayList<DeviceConnection>();

                exchange.setProperty(IConstant.TIMEOUT_DEVICE_LIST,
                        timeOutDeviceList);
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

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);
        try {

            Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME)
                    .regex(jobDetail.getCarrierName(), "i"))
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

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);
        try {

            Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME)
                    .regex(jobDetail.getCarrierName(), "i"))
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
            LOGGER.error("Error In Saving Job Detail-----------------------------"
                    + e);
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

            JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);
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

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

        List<DeviceInformation> timeOutDeviceList = (List<DeviceInformation>) exchange
                .getProperty(IConstant.TIMEOUT_DEVICE_LIST);

        // Add timeOut devices in a Set with NetSuiteId

        Set<Integer> timeOutDeviceSet = new HashSet<Integer>();

        for (DeviceInformation deviceInformation : timeOutDeviceList) {
            LOGGER.info("timeOut devices ..........."
                    + deviceInformation.getNetSuiteId());
            timeOutDeviceSet.add(deviceInformation.getNetSuiteId());
        }

        String carrierName = (String) exchange.getProperty(IConstant.CARRIER_NAME);

        // find the timeout devices that are in device usage Collection
        Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).regex(
                jobDetail.getCarrierName(), "i"))
                .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                .addCriteria(Criteria.where("isValid").is(true))
                .addCriteria(Criteria.where("netSuiteId").in(timeOutDeviceSet));

        List<DeviceUsage> deviceUsageList = mongoTemplate.find(searchJobQuery,
                DeviceUsage.class);

        // Add timeOut devices from DB in a Set with NetSuiteId
        Set<Integer> timeOutDeviceSetInDB = new HashSet<Integer>();

        for (DeviceUsage deviceUsage : deviceUsageList) {
            LOGGER.info("timeOut devices in usage..........."
                    + deviceUsage.getNetSuiteId());
            timeOutDeviceSetInDB.add(deviceUsage.getNetSuiteId());
        }

        // find all timeOut device not in device usage Collection
        List<DeviceUsage> timeOutDevicesNotInUsage = new ArrayList<DeviceUsage>();
        for (DeviceInformation deviceInformation : timeOutDeviceList) {

            if (!timeOutDeviceSetInDB.contains(deviceInformation
                    .getNetSuiteId())) {
                LOGGER.info("timeOut devices Not in usage..........."
                        + deviceInformation.getNetSuiteId());
                DeviceUsage deviceUsage = new DeviceUsage();
                deviceUsage.setCarrierName(carrierName);

                deviceUsage.setDataUsed(0);
                // The Day for which Job Ran

                deviceUsage.setDate(jobDetail.getDate());
                deviceUsage.setTransactionErrorReason("TimeOut Error");
                deviceUsage
                        .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
                deviceUsage.setNetSuiteId(deviceInformation.getNetSuiteId());
                deviceUsage.setIsValid(true);
                deviceUsage.setJobId(jobDetail.getJobId());

                if (carrierName.equalsIgnoreCase("KORE")) {

                    deviceUsage.setDeviceId(CommonUtil
                            .getSimNumber(deviceInformation.getDeviceIds()));
                }

                else {
                    deviceUsage.setDeviceId(CommonUtil
                            .getRecommendedDeviceIdentifier(deviceInformation
                                    .getDeviceIds()));

                }

                timeOutDevicesNotInUsage.add(deviceUsage);
            }
        }
        // Add all the timeOut Devices that are not in Device Usage Collection
        if (timeOutDevicesNotInUsage.size() > 0) {
            LOGGER.info("Adding timeOut devices Not in usage..........."
                    + timeOutDevicesNotInUsage.size());
            mongoTemplate.insertAll(timeOutDevicesNotInUsage);

        }

    }

    /**
     * Insert timeOut devices in Connection
     */
    @Override
    public void insertTimeOutConnectionRecords(Exchange exchange) {
        // TODO Auto-generated method stub
        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

        List<DeviceInformation> timeOutDeviceList = (List<DeviceInformation>) exchange
                .getProperty(IConstant.TIMEOUT_DEVICE_LIST);

        // Add timeOut devices in a Set with NetSuiteId

        Set<Integer> timeOutDeviceSet = new HashSet<Integer>();

        for (DeviceInformation deviceInformation : timeOutDeviceList) {
            LOGGER.info("timeOut devices ..........."
                    + deviceInformation.getNetSuiteId());
            timeOutDeviceSet.add(deviceInformation.getNetSuiteId());
        }

        String carrierName = (String) exchange.getProperty(IConstant.CARRIER_NAME);

        // find the timeout devices that are in device Connection Collection
        Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).regex(
                jobDetail.getCarrierName(), "i"))
                .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                .addCriteria(Criteria.where("isValid").is(true))
                .addCriteria(Criteria.where("netSuiteId").in(timeOutDeviceSet));

        List<DeviceConnection> deviceConnectionList = mongoTemplate.find(
                searchJobQuery, DeviceConnection.class);

        // Add timeOut devices from DB in a Set with NetSuiteId
        Set<Integer> timeOutDeviceSetInDB = new HashSet<Integer>();

        for (DeviceConnection deviceConnection : deviceConnectionList) {
            LOGGER.info("timeOut devices in Connection..........."
                    + deviceConnection.getNetSuiteId());
            timeOutDeviceSetInDB.add(deviceConnection.getNetSuiteId());
        }

        // find all timeOut device not in device connection Collection
        List<DeviceConnection> timeOutDevicesNotInConnection = new ArrayList<DeviceConnection>();
        for (DeviceInformation deviceInformation : timeOutDeviceList) {

            if (!timeOutDeviceSetInDB.contains(deviceInformation
                    .getNetSuiteId())) {
                LOGGER.info("timeOut devices Not in Connection..........."
                        + deviceInformation.getNetSuiteId());
                DeviceConnection deviceConnection = new DeviceConnection();
                deviceConnection.setCarrierName(carrierName);

                deviceConnection.setDate(jobDetail.getDate());
                deviceConnection.setTransactionErrorReason("TimeOut Error");
                deviceConnection
                        .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
                deviceConnection.setNetSuiteId(deviceInformation
                        .getNetSuiteId());
                deviceConnection.setIsValid(true);
                deviceConnection.setJobId(jobDetail.getJobId());
                deviceConnection.setDeviceId(CommonUtil
                        .getRecommendedDeviceIdentifier(deviceInformation
                                .getDeviceIds()));

                timeOutDevicesNotInConnection.add(deviceConnection);
            }
        }
        // Add all the timeOut Devices that are not in Device Connection
        // Collection
        if (timeOutDevicesNotInConnection.size() > 0) {
            LOGGER.info("Adding timeOut devices Not in Connection..........."
                    + timeOutDevicesNotInConnection.size());
            mongoTemplate.insertAll(timeOutDevicesNotInConnection);

        }
    }

    /**
     * Insert TransactionFailure timeOut devices in Usage
     */
    @Override
    public void insertTimeOutUsageRecordsTransactionFailure(Exchange exchange) {
        // TODO Auto-generated method stub

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

        List<DeviceUsage> timeOutDeviceListTransactionFailure = (List<DeviceUsage>) exchange
                .getProperty(IConstant.TIMEOUT_DEVICE_LIST);

        // Add timeOut devices in a Set with NetSuiteId

        Set<Integer> timeOutDeviceSetTransactionFailure = new HashSet<Integer>();

        for (DeviceUsage deviceUsage : timeOutDeviceListTransactionFailure) {
            LOGGER.info("timeOut devices TransactionFailure ..........."
                    + deviceUsage.getNetSuiteId());
            timeOutDeviceSetTransactionFailure.add(deviceUsage.getNetSuiteId());
        }

        String carrierName = (String) exchange.getProperty(IConstant.CARRIER_NAME);

        // find the TransactionFailure timeout devices that are in device usage
        // Collection
        Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).regex(
                jobDetail.getCarrierName(), "i"))
                .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                .addCriteria(Criteria.where("isValid").is(true))
                .addCriteria(
                        Criteria.where("netSuiteId").in(
                                timeOutDeviceSetTransactionFailure));

        List<DeviceUsage> deviceUsageListTransactionFailure = mongoTemplate
                .find(searchJobQuery, DeviceUsage.class);

        // Add timeOut devices from DB in a Set with NetSuiteId
        Set<Integer> timeOutDeviceSetInDB = new HashSet<Integer>();

        for (DeviceUsage deviceUsage : deviceUsageListTransactionFailure) {
            LOGGER.info("timeOut devices TransactionFailure in usage..........."
                    + deviceUsage.getNetSuiteId());
            timeOutDeviceSetInDB.add(deviceUsage.getNetSuiteId());
        }

        // find all timeOut device not in device usage Collection
        List<DeviceUsage> timeOutDevicesNotInUsage = new ArrayList<DeviceUsage>();
        for (DeviceUsage deviceUsage : timeOutDeviceListTransactionFailure) {

            if (!timeOutDeviceSetInDB.contains(deviceUsage.getNetSuiteId())) {
                LOGGER.info("timeOut devices Not in usage TransactionFailure..........."
                        + deviceUsage.getNetSuiteId());

                deviceUsage.setDataUsed(0);

                deviceUsage.setDate(jobDetail.getDate());
                deviceUsage.setTransactionErrorReason("TimeOut Error");
                deviceUsage
                        .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);

                deviceUsage.setIsValid(true);
                deviceUsage.setJobId(jobDetail.getJobId());

                timeOutDevicesNotInUsage.add(deviceUsage);
            }
        }
        // Add all the timeOut Devices that are not in Device Usage Collection
        if (timeOutDevicesNotInUsage.size() > 0) {
            LOGGER.info("Adding timeOut devices Not in usage..........."
                    + timeOutDevicesNotInUsage.size());
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

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

        List<DeviceConnection> timeOutDeviceTransactionFailureList = (List<DeviceConnection>) exchange
                .getProperty(IConstant.TIMEOUT_DEVICE_LIST);

        // Add timeOut devices in a Set with NetSuiteId

        Set<Integer> timeOutDeviceSetTransactionFailure = new HashSet<Integer>();

        for (DeviceConnection deviceConnection : timeOutDeviceTransactionFailureList) {
            LOGGER.info("timeOut devices TransactionFailure ..........."
                    + deviceConnection.getNetSuiteId());
            timeOutDeviceSetTransactionFailure.add(deviceConnection
                    .getNetSuiteId());
        }


        // find the timeout devices that are in device Connection Collection
        Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).regex(
                jobDetail.getCarrierName(), "i"))
                .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                .addCriteria(Criteria.where("isValid").is(true))
                .addCriteria(
                        Criteria.where("netSuiteId").in(
                                timeOutDeviceSetTransactionFailure));

        List<DeviceConnection> deviceConnectionListTransactionFailure = mongoTemplate
                .find(searchJobQuery, DeviceConnection.class);

        // Add timeOut devices from DB in a Set with NetSuiteId
        Set<Integer> timeOutDeviceSetInDB = new HashSet<Integer>();

        for (DeviceConnection deviceConnection : deviceConnectionListTransactionFailure) {
            LOGGER.info("timeOut devices TransactionFailure in Connection..........."
                    + deviceConnection.getNetSuiteId());
            timeOutDeviceSetInDB.add(deviceConnection.getNetSuiteId());
        }

        // find all timeOut device not in device connection Collection
        List<DeviceConnection> timeOutDevicesNotInConnection = new ArrayList<DeviceConnection>();
        for (DeviceConnection deviceConnection : timeOutDeviceTransactionFailureList) {

            if (!timeOutDeviceSetInDB
                    .contains(deviceConnection.getNetSuiteId())) {
                LOGGER.info("timeOut devices Not in Connection TransactionFailure..........."
                        + deviceConnection.getNetSuiteId());

                deviceConnection.setDate(jobDetail.getDate());
                deviceConnection.setTransactionErrorReason("TimeOut Error");
                deviceConnection
                        .setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);

                deviceConnection.setIsValid(true);
                deviceConnection.setJobId(jobDetail.getJobId());
                timeOutDevicesNotInConnection.add(deviceConnection);
            }
        }
        // Add all the timeOut Devices that are not in Device Connection
        // Collection
        if (timeOutDevicesNotInConnection.size() > 0) {
            LOGGER.info("Adding timeOut devices Not in Connection..........."
                    + timeOutDevicesNotInConnection.size());
            mongoTemplate.insertAll(timeOutDevicesNotInConnection);

        }
    }

    @Override
    public void updateDeviceUsageView(Exchange exchange) {

        LOGGER.info("Inside updateDeviceUsageView .....................");

        Map<Integer, DeviceUsageViewElement> existingRecords = fetchExistingDeviceUsageView(exchange);

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);
        try {

            Query searchDeviceUsageQuery = new Query(Criteria.where(
            		IConstant.CARRIER_NAME).regex(jobDetail.getCarrierName(), "i"))
                    .addCriteria(Criteria.where("date").is(jobDetail.getDate()))
                    .addCriteria(Criteria.where("isValid").is(true))
                    .addCriteria(
                            Criteria.where("transactionStatus").is("Success"))
                    .addCriteria(
                            Criteria.where("jobId").in(jobDetail.getJobId()));

            List<DeviceUsage> deviceUsageList = mongoTemplate.find(
                    searchDeviceUsageQuery, DeviceUsage.class);

            DeviceUsageView view = new DeviceUsageView();
            view.setDate(jobDetail.getDate());
            view.setCarrierName(jobDetail.getCarrierName());

            ArrayList<DeviceUsageViewElement> list = new ArrayList<DeviceUsageViewElement>();
            Iterator itr = deviceUsageList.iterator();

            // if the map is empty -then insert all the elements with updated as
            // false
            // if map has same value as fetched object then keep the updated as
            // false /No Change
            // if the map does not have that element then insert in map with
            // updated as true
            // if map has different value as fetched object then update map
            // object and set the updated as true

            if (existingRecords.isEmpty()) {

                while (itr.hasNext()) {
                    DeviceUsage element = (DeviceUsage) itr.next();
                    DeviceUsageViewElement viewElement = new DeviceUsageViewElement();

                    viewElement.setDeviceId(element.getDeviceId());
                    viewElement.setNetSuiteId(element.getNetSuiteId());
                    viewElement.setDataUsed(element.getDataUsed());
                    viewElement.setIsUpdatedElement(Boolean.FALSE);
                    viewElement.setJobId(jobDetail.getJobId());
                    viewElement.setLastTimeStampUpdated(new Date());
                    list.add(viewElement);

                }
                DeviceUsageViewElement[] elements = list
                        .toArray(new DeviceUsageViewElement[list.size()]);

                view.setElements(elements);

            } else {

                while (itr.hasNext()) {
                    DeviceUsage deviceUsageElement = (DeviceUsage) itr.next();
                    DeviceUsageViewElement newViewElement = new DeviceUsageViewElement();

                    DeviceUsageViewElement mapElement = existingRecords
                            .get(deviceUsageElement.getNetSuiteId());
                    // if Element not present adding the element to the hashmap

                    if (mapElement == null) {

                        newViewElement.setDeviceId(deviceUsageElement
                                .getDeviceId());
                        newViewElement.setNetSuiteId(deviceUsageElement
                                .getNetSuiteId());
                        newViewElement.setDataUsed(deviceUsageElement
                                .getDataUsed());
                        newViewElement.setIsUpdatedElement(Boolean.TRUE);
                        newViewElement.setJobId(deviceUsageElement.getJobId());
                        newViewElement.setLastTimeStampUpdated(new Date());

                        existingRecords.put(deviceUsageElement.getNetSuiteId(),
                                newViewElement);

                    }
                    // if the map Element has different value than view element
                    else if (mapElement.getDataUsed() != deviceUsageElement
                            .getDataUsed()) {
                        mapElement
                                .setDataUsed(deviceUsageElement.getDataUsed());
                        mapElement.setJobId(deviceUsageElement.getJobId());
                        mapElement.setIsUpdatedElement(Boolean.TRUE);
                        mapElement.setLastTimeStampUpdated(new Date());
                    }

                }

                DeviceUsageViewElement[] updatedElements = existingRecords
                        .values().toArray(
                                new DeviceUsageViewElement[existingRecords
                                        .values().size()]);

                view.setElements(updatedElements);

                // mongoTemplate.save(view);



            }
            Query searchDeviceUsageViewQuery = new Query(Criteria.where(
            		IConstant.CARRIER_NAME).regex(jobDetail.getCarrierName(), "i"))
                    .addCriteria(Criteria.where("date").is(
                            jobDetail.getDate()));

            Update update = new Update();

            update.set("elements", view.getElements());
            update.set(IConstant.CARRIER_NAME,jobDetail.getCarrierName() );
            update.set("date",jobDetail.getDate());

            mongoTemplate.upsert(searchDeviceUsageViewQuery, update,
                    DeviceUsageView.class);

        }

        catch (Exception e) {
            LOGGER.info("Error In updateDeviceUsageView-----------------------------"
                    + e);
        }

    }

    public Map<Integer, DeviceUsageViewElement> fetchExistingDeviceUsageView(
            Exchange exchange) {

        LOGGER.info("Inside fetchExistingDeviceUsageView .....................");

        Map<Integer, DeviceUsageViewElement> deviceUsageViewMap = new HashMap();

        JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);
        try {

            Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME)
                    .regex(jobDetail.getCarrierName(), "i"))
                    .addCriteria(Criteria.where("date").is(jobDetail.getDate()));

            List<DeviceUsageView> deviceUsageViewList = mongoTemplate.find(
                    searchJobQuery, DeviceUsageView.class);

            if (deviceUsageViewList.size() > 0) {
                ArrayList<DeviceUsageViewElement> list = new ArrayList<DeviceUsageViewElement>();

                DeviceUsageView view = deviceUsageViewList.get(0);

                DeviceUsageViewElement[] elements = view.getElements();

                deviceUsageViewMap = Arrays
                        .asList(elements)
                        .stream()
                        .collect(
                                Collectors.toMap(x -> x.getNetSuiteId(), x -> x));
            }

        }

        catch (Exception e) {
            LOGGER.info("Error In fetchExistingDeviceUsageView-----------------------------"
                    + e);
        }
        return deviceUsageViewMap;

    }

    @Override
	public List<DevicesUsageByDayAndCarrier> fetchDeviceUsageView(
			Exchange exchange) {
		// TODO Auto-generated method stub

		
		Boolean isUpdatedElementfalse = false;

		LOGGER.info("Inside fetchDeviceUsageView .....................");
		DevicesUsageByDayAndCarrier deviceUsageResponse = null;

		List<DevicesUsageByDayAndCarrier> deviceUsagelist = new ArrayList<DevicesUsageByDayAndCarrier>();

		JobParameter req = (JobParameter) exchange.getIn().getBody();

		List<DeviceUsageView> deviceUsageViewList = null;

		try {

			Query searchJobQuery = new Query(Criteria.where(IConstant.CARRIER_NAME).is(
					req.getCarrierName())).addCriteria(Criteria.where("date")
					.is(req.getDate()));

			deviceUsageViewList = mongoTemplate.find(searchJobQuery,
					DeviceUsageView.class);

			if (deviceUsageViewList.size() != 0) {

				deviceUsageResponse = new DevicesUsageByDayAndCarrier();

				ArrayList<DeviceUsageViewElement> list = new ArrayList<DeviceUsageViewElement>();

				DeviceUsageView deviceUasgesview = deviceUsageViewList.get(0);

				DeviceUsageViewElement[] elements = deviceUasgesview
						.getElements();

				LOGGER.info("elements:" + elements.length);

				for (int i = 0; i < elements.length; i++)

				{
					// request parameter is false
					// add all
					// if request paramter is true
					// fetch with updated flag as true

					if (elements[i].getIsUpdatedElement() == isUpdatedElementfalse) {

						deviceUsageResponse.setDataUsed(elements[i]
								.getDataUsed());
						deviceUsageResponse.setNetSuiteId(elements[i]
								.getNetSuiteId());
						deviceUsagelist.add(deviceUsageResponse);
					}

				}

			}

		}
		
		catch (Exception e) {
			LOGGER.info("Error In fetchDeviceUsageView-----------------------------"
					+ e);

		}
		return deviceUsagelist;

	}

	@Override
	public List fetchPreviousDeviceUsageDataUsed(Exchange exchange) {
		JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

		// fetching the last updated value for each netsuiteId in Device Usage
		// table
		AggregationResults<DeviceUsage> results = null;

		String fetchdate = jobDetail.getDate();
		String[] fetchDateString = fetchdate.split("-");
		String beginningMonthDate = fetchDateString[0] + "-"
				+ fetchDateString[1] + "-01";
		LOGGER.info("beginningMonthDate:::::" + beginningMonthDate);

		try

		{
			Aggregation agg = newAggregation(
					match(Criteria.where(IConstant.CARRIER_NAME).is("ATTJASPER")
							.and("date").gte(beginningMonthDate).and("isValid")
							.is(true).and("transactionStatus").is("Success")),
					sort(Sort.Direction.DESC, "netSuiteId", "date"),
					group("netSuiteId").first("date").as("date")
							.first("monthToDateUsage").as("monthToDateUsage")
							.first("netSuiteId").as("netSuiteId")
			// .max("date").as("date")
			);

			results = mongoTemplate.aggregate(agg, DeviceUsage.class,
					DeviceUsage.class);

			LOGGER.info("LIST KA SIZE" + results.getMappedResults().size());
		}

		catch (Exception e) {
			LOGGER.info("e::" + e.getMessage());
		}

		return results.getMappedResults();

	}

}
