package com.gv.midway.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationResponseMidwayDataArea;
import com.gv.midway.pojo.connectionInformation.DeviceEvents;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceEvent;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponseMidwayDataArea;
import com.gv.midway.utility.CommonUtil;

@Service
public class DeviceDaoImpl implements IDeviceDao {

    @Autowired
    MongoTemplate mongoTemplate;

    private static final Logger LOGGER = Logger.getLogger(DeviceDaoImpl.class.getName());

    @Override
    public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {

        DeviceInformation deviceInfomation = null;
        try {

            DeviceInformation deviceInformationToUpdate = device.getDataArea()
                    .getDevice();
            Integer netSuiteId = device.getDataArea().getDevice()
                    .getNetSuiteId();

            if (netSuiteId == null) {

                Header header = device.getHeader();

                Response response = new Response();
                response.setResponseCode(IResponse.INVALID_PAYLOAD);
                response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
                response.setResponseStatus(IResponse.ERROR_MESSAGE);

                UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
                updateDeviceResponse.setHeader(header);
                updateDeviceResponse.setResponse(response);

                return updateDeviceResponse;
            }

            Query searchDeviceQuery = new Query(Criteria.where("netSuiteId")
                    .is(device.getDataArea().getDevice().getNetSuiteId()));

            deviceInfomation = (DeviceInformation) mongoTemplate.findOne(
                    searchDeviceQuery, DeviceInformation.class);

            deviceInformationToUpdate.setLastUpdated(new Date());

            Header header = device.getHeader();
            Response response = new Response();

            response.setResponseCode(IResponse.SUCCESS_CODE);
            response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB);
            response.setResponseStatus(IResponse.SUCCESS_MESSAGE);

            UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
            updateDeviceResponse.setHeader(header);
            updateDeviceResponse.setResponse(response);

            if (deviceInfomation == null) {

                mongoTemplate.insert(deviceInformationToUpdate);

            }

            else {
                deviceInformationToUpdate
                        .setMidwayMasterDeviceId(deviceInfomation
                                .getMidwayMasterDeviceId());

                mongoTemplate.save(deviceInformationToUpdate);

            }

            return updateDeviceResponse;

        }

        catch (Exception e) {

            LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
            Header header = device.getHeader();

            Response response = new Response();
            response.setResponseCode(IResponse.DB_ERROR_CODE);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);

            UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
            updateDeviceResponse.setHeader(header);
            updateDeviceResponse.setResponse(response);

            return updateDeviceResponse;

        }

    }

    @Override
    public DeviceInformationResponse getDeviceInformationDB(
            DeviceInformationRequest deviceInformationRequest) {

        Integer netSuiteId = deviceInformationRequest.getDataArea()
                .getNetSuiteId();
        LOGGER.info("device dao netsuite id is..." + netSuiteId);

        DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

        deviceInformationResponse.setHeader(deviceInformationRequest
                .getHeader());
        Response response = new Response();
        if (netSuiteId == null) {

            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);

            deviceInformationResponse.setResponse(response);

            return deviceInformationResponse;
        } else {
            try {

                Query searchDeviceQuery = new Query(Criteria
                        .where("netSuiteId").is(netSuiteId));

                DeviceInformation deviceInformation = (DeviceInformation) mongoTemplate
                        .findOne(searchDeviceQuery, DeviceInformation.class);

                if (deviceInformation == null)

                {

                    response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
                    response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB);
                    response.setResponseStatus(IResponse.ERROR_MESSAGE);

                }

                else {

                    response.setResponseCode(IResponse.SUCCESS_CODE);
                    response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB);
                    response.setResponseStatus(IResponse.SUCCESS_MESSAGE);

                }

                deviceInformationResponse.setResponse(response);

                DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

                deviceInformationResponseDataArea.setDevices(deviceInformation);

                deviceInformationResponse
                        .setDataArea(deviceInformationResponseDataArea);

                return deviceInformationResponse;
            } catch (Exception e) {
            	LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
                response.setResponseCode(IResponse.DB_ERROR_CODE);
                response.setResponseDescription(IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB);
                response.setResponseStatus(IResponse.ERROR_MESSAGE);

                deviceInformationResponse.setResponse(response);

                DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

                deviceInformationResponseDataArea.setDevices(null);

                deviceInformationResponse
                        .setDataArea(deviceInformationResponseDataArea);

                return deviceInformationResponse;
            }

        }

    }

    @Override
    public void setDeviceInformationDB(Exchange exchange) {

        Integer netSuiteId = (Integer) exchange
                .getProperty(IConstant.MIDWAY_NETSUITE_ID);
        DeviceInformation deviceInformation = null;
        try {

            Query searchDeviceQuery = new Query(Criteria.where("netSuiteId")
                    .is(netSuiteId));

            deviceInformation = (DeviceInformation) mongoTemplate.findOne(
                    searchDeviceQuery, DeviceInformation.class);

            exchange.setProperty(IConstant.MIDWAY_DEVICEINFO_DB,
                    deviceInformation);

        }

        catch (Exception e) {
        	LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
            LOGGER.info("Not able to fetch the data from DB....." + e.getMessage());
        }

    }

    @Override
    public void updateDeviceInformationDB(Exchange exchange) {
        DeviceInformationResponse deviceInformationResponse = (DeviceInformationResponse) exchange
                .getIn().getBody();

        DeviceInformation deviceInformation = deviceInformationResponse
                .getDataArea().getDevices();

        if (exchange.getProperty(IConstant.MIDWAY_DEVICEINFO_DB) != null) {

            LOGGER.info("device info was already in master DB");

            LOGGER.info("device info carrier is........."
                    + deviceInformation.toString());

            deviceInformation.setMidwayMasterDeviceId(deviceInformation
                    .getMidwayMasterDeviceId());

            mongoTemplate.save(deviceInformation);
        }

        else {
            LOGGER.info("device info was not already in master DB");

            Integer netSuiteId = (Integer) exchange
                    .getProperty(IConstant.MIDWAY_NETSUITE_ID);

            LOGGER.info("device info to insert for netsuideId " + netSuiteId);

            deviceInformation.setNetSuiteId(netSuiteId);

            mongoTemplate.insert(deviceInformation);

        }
        DeviceInformationResponseDataArea deviceInformationResponseDataArea = deviceInformationResponse
                .getDataArea();
        deviceInformationResponseDataArea.setDevices(deviceInformation);
        deviceInformationResponse
                .setDataArea(deviceInformationResponseDataArea);
        exchange.getIn().setBody(deviceInformationResponse);
    }

    @Override
    public void bulkOperationDeviceUpload(Exchange exchange) {

        DeviceInformation deviceInformationToUpdate = (DeviceInformation) exchange
                .getIn().getBody();

        Integer netSuiteId = deviceInformationToUpdate.getNetSuiteId();

        try {

            if (netSuiteId == null) {

                List<BatchDeviceId> batchDeviceList = (List<BatchDeviceId>) exchange
                        .getProperty(IConstant.BULK_ERROR_LIST);
                BatchDeviceId errorBatchDeviceId = new BatchDeviceId();
                errorBatchDeviceId
                        .setErrorMessage(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
                batchDeviceList.add(errorBatchDeviceId);

                exchange.setProperty(IConstant.BULK_ERROR_LIST, batchDeviceList);

            }

            else {
                Query searchDeviceQuery = new Query(Criteria
                        .where("netSuiteId").is(netSuiteId));

                DeviceInformation deviceInformation = (DeviceInformation) mongoTemplate
                        .findOne(searchDeviceQuery, DeviceInformation.class);

                deviceInformationToUpdate.setLastUpdated(new Date());

                if (deviceInformation == null)

                {

                    mongoTemplate.insert(deviceInformationToUpdate);

                }

                else {
                    deviceInformationToUpdate
                            .setMidwayMasterDeviceId(deviceInformation
                                    .getMidwayMasterDeviceId());

                    mongoTemplate.save(deviceInformationToUpdate);

                }

                List<BatchDeviceId> batchDeviceList = (List<BatchDeviceId>) exchange
                        .getProperty(IConstant.BULK_SUCCESS_LIST);
                BatchDeviceId successBatchDeviceId = new BatchDeviceId();
                successBatchDeviceId.setNetSuiteId("" + netSuiteId);
                batchDeviceList.add(successBatchDeviceId);

                exchange.setProperty(IConstant.BULK_SUCCESS_LIST,
                        batchDeviceList);
            }

        }

        catch (Exception e) {
        	LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
            List<BatchDeviceId> batchDeviceList = (List<BatchDeviceId>) exchange
                    .getProperty(IConstant.BULK_ERROR_LIST);
            BatchDeviceId errorBatchDeviceId = new BatchDeviceId();
            errorBatchDeviceId.setNetSuiteId("" + netSuiteId);
            errorBatchDeviceId
                    .setErrorMessage(IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB);
            batchDeviceList.add(errorBatchDeviceId);

            exchange.setProperty(IConstant.BULK_ERROR_LIST, batchDeviceList);

        }

    }

    @Override
    public ArrayList<DeviceInformation> getAllDevices() {

        return (ArrayList<DeviceInformation>) mongoTemplate
                .findAll(DeviceInformation.class);

    }

    @Override
    public UsageInformationMidwayResponse getDeviceUsageInfoDB(
            UsageInformationMidwayRequest usageInformationMidwayRequest) {

        LOGGER.info("Begin::getDeviceUsageInfoDB");
        Integer netSuiteId = usageInformationMidwayRequest
                .getUsageInformationRequestMidwayDataArea().getNetSuiteId();

        String startDate = usageInformationMidwayRequest
                .getUsageInformationRequestMidwayDataArea().getStartDate();
        String endDate = usageInformationMidwayRequest
                .getUsageInformationRequestMidwayDataArea().getEndDate();

        LOGGER.info("device dao netsuite id is..." + netSuiteId);
        LOGGER.info("device dao startDate is..." + startDate);
        LOGGER.info("device dao endDate is..." + endDate);

        Float dataUsed;
        UsageInformationMidwayResponse usageInformationMidwayResponse = new UsageInformationMidwayResponse();

        usageInformationMidwayResponse.setHeader(usageInformationMidwayRequest
                .getHeader());
        Response response = new Response();

        if (netSuiteId == null) {

            LOGGER.info("Enter netSuiteId..." + netSuiteId);
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);

            usageInformationMidwayResponse.setResponse(response);

            return usageInformationMidwayResponse;
        }

        if (startDate != null && endDate != null) {

            if (!(CommonUtil.isValidDateFormat(startDate) && CommonUtil
                    .isValidDateFormat(endDate))) {
                LOGGER.info(" Date that you provided is invalid");
                response.setResponseCode(IResponse.INVALID_PAYLOAD);
                response.setResponseDescription(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB);
                response.setResponseStatus(IResponse.ERROR_MESSAGE);
                usageInformationMidwayResponse.setResponse(response);
                return usageInformationMidwayResponse;
            }

        }

        if (startDate == null) {

            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_START_DATE_FORMAT_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            usageInformationMidwayResponse.setResponse(response);
            return usageInformationMidwayResponse;

        }
        if (endDate == null) {
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_END_DATE_FORMAT_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            usageInformationMidwayResponse.setResponse(response);
            return usageInformationMidwayResponse;
        }

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date startDateValue = null;
        Date endDateValue = null;

        try {
            startDateValue = formatter.parse(startDate);
            endDateValue = formatter.parse(endDate);
            LOGGER.info("startDateValue..." + startDateValue);
            LOGGER.info("endDateValue..." + endDateValue);
        } catch (ParseException e1) {
            LOGGER.info(" format error while parsing the date");
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            usageInformationMidwayResponse.setResponse(response);
            return usageInformationMidwayResponse;
        }

        if (startDateValue.after(endDateValue)) {

            LOGGER.info("Earliest date should not be greater than Latest date");
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_START_END_VALIDATION_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            usageInformationMidwayResponse.setResponse(response);
            return usageInformationMidwayResponse;

        }

        try {

            Query searchDeviceQuery = new Query(Criteria.where("netSuiteId")
                    .is(netSuiteId)).addCriteria(Criteria.where("date")
                    .gte(startDate)
                    .orOperator(Criteria.where("date").lte(endDate))).addCriteria(Criteria.where("isValid")
                    .is(true));

            LOGGER.info("searchDeviceQuery::::::::::::::" + searchDeviceQuery);

            DeviceUsage deviceUsage = (DeviceUsage) mongoTemplate.findOne(
                    searchDeviceQuery, DeviceUsage.class);

            if (deviceUsage == null)

            {

                response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
                response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB);
                response.setResponseStatus(IResponse.ERROR_MESSAGE);
                usageInformationMidwayResponse.setResponse(response);
            }

            else {

                response.setResponseCode(IResponse.SUCCESS_CODE);
                response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB);
                response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
                usageInformationMidwayResponse.setResponse(response);

                UsageInformationResponseMidwayDataArea usageInformationResponseMidwayDataArea = new UsageInformationResponseMidwayDataArea();
                dataUsed = deviceUsage.getDataUsed();
                LOGGER.info("deviceUsage.getDataUsed() ---------------------------------"
                        + dataUsed);
                usageInformationResponseMidwayDataArea.setTotalUsages(dataUsed
                        .longValue());
                usageInformationMidwayResponse
                        .setDataArea(usageInformationResponseMidwayDataArea);
            }

            return usageInformationMidwayResponse;

        } catch (Exception e) {

        	LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
            response.setResponseCode(IResponse.DB_ERROR_CODE);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);

            usageInformationMidwayResponse.setResponse(response);

            UsageInformationResponseMidwayDataArea usageInformationResponseMidwayDataArea = new UsageInformationResponseMidwayDataArea();

            usageInformationMidwayResponse
                    .setDataArea(usageInformationResponseMidwayDataArea);

            return usageInformationMidwayResponse;
        }

    }

    @Override
    public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
            ConnectionInformationMidwayRequest connectionInformationMidwayRequest) {
        LOGGER.info("Begin::getDeviceConnectionHistoryInfoDB");

        Integer netSuiteId = connectionInformationMidwayRequest.getDataArea()
                .getNetSuiteId();
        String startDate = connectionInformationMidwayRequest.getDataArea()
                .getStartDate();
        String endDate = connectionInformationMidwayRequest.getDataArea()
                .getEndDate();

        LOGGER.info("device dao netsuite id is..." + netSuiteId);
        LOGGER.info("device dao startDate is..." + startDate);
        LOGGER.info("device dao endDate is..." + endDate);

        ConnectionInformationMidwayResponse connectionInformationMidwayResponse = new ConnectionInformationMidwayResponse();

        connectionInformationMidwayResponse
                .setHeader(connectionInformationMidwayRequest.getHeader());
        Response response = new Response();

        if (netSuiteId == null) {

            LOGGER.info("Enter netSuiteId..." + netSuiteId);
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);

            connectionInformationMidwayResponse.setResponse(response);

            return connectionInformationMidwayResponse;
        }
        if (startDate != null && endDate != null) {

            if (!(CommonUtil.isValidDateFormat(startDate) && CommonUtil
                    .isValidDateFormat(endDate))) {
                LOGGER.info(" Date that you provided is invalid");
                response.setResponseCode(IResponse.INVALID_PAYLOAD);
                response.setResponseDescription(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB);
                response.setResponseStatus(IResponse.ERROR_MESSAGE);
                connectionInformationMidwayResponse.setResponse(response);
                return connectionInformationMidwayResponse;
            }

        }

        if (startDate == null) {

            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_START_DATE_FORMAT_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            connectionInformationMidwayResponse.setResponse(response);
            return connectionInformationMidwayResponse;

        }
        if (endDate == null) {
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_END_DATE_FORMAT_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            connectionInformationMidwayResponse.setResponse(response);
            return connectionInformationMidwayResponse;
        }

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date startDateValue = null;
        Date endDateValue = null;

        try {
            startDateValue = formatter.parse(startDate);
            endDateValue = formatter.parse(endDate);
            LOGGER.info("startDateValue..." + startDateValue);
            LOGGER.info("endDateValue..." + endDateValue);
        } catch (ParseException e1) {
            LOGGER.info(" format error while parsing the date");
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            connectionInformationMidwayResponse.setResponse(response);
            return connectionInformationMidwayResponse;
        }

        if (startDateValue.after(endDateValue)) {

            LOGGER.info("Earliest date should not be greater than Latest date");
            response.setResponseCode(IResponse.INVALID_PAYLOAD);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_START_END_VALIDATION_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            connectionInformationMidwayResponse.setResponse(response);
            return connectionInformationMidwayResponse;

        }

        try {

            Query searchDeviceQuery = new Query(Criteria.where("netSuiteId")
                    .is(netSuiteId)).addCriteria(Criteria.where("date")
                    .gte(startDate)
                    .orOperator(Criteria.where("date").lte(endDate))).addCriteria(Criteria.where("isValid")
                            .is(true));

            LOGGER.info("searchDeviceQuery::::::::::::::" + searchDeviceQuery);

            List<DeviceConnection> deviceConnectionUsage = mongoTemplate.find(
                    searchDeviceQuery, DeviceConnection.class);

            int deviceConnectionUsageSize = deviceConnectionUsage.size();

            LOGGER.info("deviceConnectionUsage szie is....."
                    + deviceConnectionUsageSize);

            if (deviceConnectionUsageSize == 0)

            {

                response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
                response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB);
                response.setResponseStatus(IResponse.ERROR_MESSAGE);
                connectionInformationMidwayResponse.setResponse(response);
            }

            else {

                response.setResponseCode(IResponse.SUCCESS_CODE);
                response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB);
                response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
                connectionInformationMidwayResponse.setResponse(response);

                ConnectionInformationResponseMidwayDataArea connectionInformationResponseMidwayDataArea = new ConnectionInformationResponseMidwayDataArea();

                List<DeviceEvents> deviceEventsList = new ArrayList<DeviceEvents>();

                for (DeviceConnection deviceConnection : deviceConnectionUsage) {

                    if (deviceConnection.getEvent() != null) {
                        DeviceEvent[] deviceEventArr = deviceConnection
                                .getEvent();

                        LOGGER.info("device event size for Date "
                                + deviceConnection.getDate() + " is"
                                + deviceEventArr.length);

                        for (DeviceEvent deviceEvent : deviceEventArr) {
                            String eventType = deviceEvent.getEventType();
                            String occuredAt = deviceEvent.getOccurredAt();
                            String byteUsed = deviceEvent.getBytesUsed();

                            DeviceEvents deviceEvents = new DeviceEvents();
                            deviceEvents.setBytesUsed(byteUsed);
                            deviceEvents.setEventType(eventType);
                            deviceEvents.setOccurredAt(occuredAt);

                            deviceEventsList.add(deviceEvents);

                        }
                    }
                }

                // Sort the deviceEventsList on the basis of time event occurred
                // at

                Collections.sort(deviceEventsList,
                        new Comparator<DeviceEvents>() {
                            @Override
                            public int compare(DeviceEvents a, DeviceEvents b) {

                                return a.getOccurredAt().compareTo(
                                        b.getOccurredAt());
                            }
                        });

                connectionInformationResponseMidwayDataArea
                        .setEvents(deviceEventsList);

                connectionInformationMidwayResponse
                        .setDataArea(connectionInformationResponseMidwayDataArea);
            }

            return connectionInformationMidwayResponse;

        } catch (Exception e) {

        	LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
            response.setResponseCode(IResponse.DB_ERROR_CODE);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);

            connectionInformationMidwayResponse.setResponse(response);

            ConnectionInformationResponseMidwayDataArea connectionInformationResponseMidwayDataArea = new ConnectionInformationResponseMidwayDataArea();

            connectionInformationMidwayResponse
                    .setDataArea(connectionInformationResponseMidwayDataArea);

            return connectionInformationMidwayResponse;
        }

    }
}
