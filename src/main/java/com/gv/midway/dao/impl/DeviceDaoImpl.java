package com.gv.midway.dao.impl;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
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
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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
import com.gv.midway.pojo.usageInformation.request.DevicesUsageByDayAndCarrierRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.response.DeviceUsages;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrier;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponse;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponseDataArea;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponseMidwayDataArea;
import com.gv.midway.utility.CommonUtil;

@Service
public class DeviceDaoImpl implements IDeviceDao {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final Logger LOGGER = Logger.getLogger(DeviceDaoImpl.class
			.getName());

	@Override
	public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {

		DeviceInformation deviceInformation = null;
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

			deviceInformation = mongoTemplate.findOne(searchDeviceQuery, DeviceInformation.class);

			deviceInformationToUpdate.setLastUpdated(new Date());

			Header header = device.getHeader();
			Response response = new Response();

			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);

			UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
			updateDeviceResponse.setHeader(header);
			updateDeviceResponse.setResponse(response);

			if (deviceInformation == null) {

				mongoTemplate.insert(deviceInformationToUpdate);

			}

			else {
				deviceInformationToUpdate
						.setMidwayMasterDeviceId(deviceInformation
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
			LOGGER.info("Not able to fetch the data from DB....."
					+ e.getMessage());
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
		UsageInformationResponseMidwayDataArea usageInformationResponseMidwayDataArea = new UsageInformationResponseMidwayDataArea();

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
					.is(netSuiteId)).addCriteria(
					Criteria.where("date").gte(startDate)
							.orOperator(Criteria.where("date").lte(endDate)))
					.addCriteria(Criteria.where("isValid").is(true));

			LOGGER.info("searchDeviceQuery::::::::::::::" + searchDeviceQuery);

			List<DeviceUsage> deviceUsage = mongoTemplate.find(
					searchDeviceQuery, DeviceUsage.class);
			
			int deviceUsageSize=0;
			
			if (deviceUsage != null) {
				
				deviceUsageSize = deviceUsage.size();

				LOGGER.info("deviceUsage size is....."
						+ deviceUsageSize);	
				
			}

			if (deviceUsageSize == 0) {

				response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
				response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB);
				response.setResponseStatus(IResponse.ERROR_MESSAGE);
				usageInformationMidwayResponse.setResponse(response);
			} else {

				response.setResponseCode(IResponse.SUCCESS_CODE);
				response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB);
				response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
				usageInformationMidwayResponse.setResponse(response);

				LOGGER.info("deviceUsage        " + deviceUsage.size());
				List<DeviceUsages> deviceDateBasedUsageList = new ArrayList<DeviceUsages>();

				for (DeviceUsage deviceUsageValue : deviceUsage) {

					DeviceUsages deviceDateBasedUsage = new DeviceUsages();
					deviceDateBasedUsage.setDataUsed(deviceUsageValue
							.getDataUsed());
					deviceDateBasedUsage.setDate(deviceUsageValue.getDate());

					deviceDateBasedUsageList.add(deviceDateBasedUsage);
				}
				
				
				// Sort the deviceEventsList on the basis of time event occurred
				// at

				Collections.sort(deviceDateBasedUsageList,
						new Comparator<DeviceUsages>() {
							@Override
							public int compare(DeviceUsages a, DeviceUsages b) {

								return a.getDate().compareTo(
										b.getDate());
							}
						});

				usageInformationResponseMidwayDataArea
						.setDeviceUsages(deviceDateBasedUsageList);
				
				

			}

			usageInformationMidwayResponse
					.setDataArea(usageInformationResponseMidwayDataArea);
			return usageInformationMidwayResponse;

		} catch (Exception e) {

			LOGGER.error("Exception ex " + CommonUtil.getStackTrace(e));
			response.setResponseCode(IResponse.DB_ERROR_CODE);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);

			usageInformationMidwayResponse.setResponse(response);

			UsageInformationResponseMidwayDataArea usageInformationResponseMidwayDataAreas = new UsageInformationResponseMidwayDataArea();

			usageInformationMidwayResponse.setDataArea(usageInformationResponseMidwayDataAreas);

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
					.is(netSuiteId)).addCriteria(
					Criteria.where("date").gte(startDate)
							.orOperator(Criteria.where("date").lte(endDate)))
					.addCriteria(Criteria.where("isValid").is(true));

			LOGGER.info("searchDeviceQuery::::::::::::::" + searchDeviceQuery);

			List<DeviceConnection> deviceConnectionUsage = mongoTemplate.find(
					searchDeviceQuery, DeviceConnection.class);
			
			int deviceConnectionUsageSize=0;
			
			if(deviceConnectionUsage!=null)
			{

			deviceConnectionUsageSize = deviceConnectionUsage.size();

			LOGGER.info("deviceConnectionUsage size is....."
					+ deviceConnectionUsageSize);
			}

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
								+ deviceConnection.getDate() + " is "
								+ deviceEventArr.length);

						for (DeviceEvent deviceEvent : deviceEventArr) {
							String eventType = deviceEvent.getEventType();
							String occurredAt = deviceEvent.getOccurredAt();
							String byteUsed = deviceEvent.getBytesUsed();

							DeviceEvents deviceEvents = new DeviceEvents();
							deviceEvents.setBytesUsed(byteUsed);
							deviceEvents.setEventType(eventType);
							deviceEvents.setOccurredAt(occurredAt);

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

			LOGGER.error("Exception ex " + CommonUtil.getStackTrace(e));
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
	
	@Override
	public DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(
			DevicesUsageByDayAndCarrierRequest devicesUsageByDayAndCarrierRequest) {
		
		String date = devicesUsageByDayAndCarrierRequest.getDateArea().getDate();
		
		DevicesUsageByDayAndCarrierResponse devicesUsageByDayAndCarrierResponse = new DevicesUsageByDayAndCarrierResponse();

		devicesUsageByDayAndCarrierResponse
				.setHeader(devicesUsageByDayAndCarrierRequest.getHeader());
		Response response = new Response();
		
		if (date != null) {

			if (!(CommonUtil.isValidDateFormat(date))) {
				LOGGER.info(" Date that you provided is invalid");
				response.setResponseCode(IResponse.INVALID_PAYLOAD);
				response.setResponseDescription(IResponse.ERROR_DESCRIPTION_DATE_VALIDATE_JOB_MIDWAYDB);
				response.setResponseStatus(IResponse.ERROR_MESSAGE);
				devicesUsageByDayAndCarrierResponse.setResponse(response);
				return devicesUsageByDayAndCarrierResponse;
			}

		}
		
		try {
			
		
		
			 Aggregation agg = newAggregation(
	                    match(Criteria.where("date").is(date)
	                            .and("carrierName").regex(devicesUsageByDayAndCarrierRequest.getHeader().getBsCarrier(), "i")
	                            .and("isValid").is(true)
	                            .and("dataUsed").ne(0)),
	                    project("dataUsed").and("netSuiteId").as("netSuiteId"));
			 
			 AggregationResults<DevicesUsageByDayAndCarrier> results = mongoTemplate
	                    .aggregate(agg, DeviceUsage.class,
	                    		DevicesUsageByDayAndCarrier.class);

			
			 
			LOGGER.info("aggregation result is......" + results);

			if (results == null || results.getMappedResults().size()==0)

			{

				response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
				response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB);
				response.setResponseStatus(IResponse.ERROR_MESSAGE);
				devicesUsageByDayAndCarrierResponse.setResponse(response);
			}

			else {

				response.setResponseCode(IResponse.SUCCESS_CODE);
				response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB);
				response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
				devicesUsageByDayAndCarrierResponse.setResponse(response);
				
				LOGGER.info("size of result is     ......" + results.getMappedResults().size());
				
				
				DevicesUsageByDayAndCarrierResponseDataArea devicesUsageByDayAndCarrierResponseDataArea = new DevicesUsageByDayAndCarrierResponseDataArea();

				devicesUsageByDayAndCarrierResponseDataArea.setDevices(results.getMappedResults());
				
				devicesUsageByDayAndCarrierResponseDataArea.setDate(date);
				
				devicesUsageByDayAndCarrierResponse.setDataArea(devicesUsageByDayAndCarrierResponseDataArea);

				
			}

			return devicesUsageByDayAndCarrierResponse;

		} catch (Exception e) {

			LOGGER.error("Exception ex " + CommonUtil.getStackTrace(e));
			response.setResponseCode(IResponse.DB_ERROR_CODE);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);

			devicesUsageByDayAndCarrierResponse.setResponse(response);

		

			return devicesUsageByDayAndCarrierResponse;
		}

		
	}
}
