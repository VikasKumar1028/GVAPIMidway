package com.gv.midway.dao.impl;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.session.SessionRequest;
import com.gv.midway.pojo.session.SessionRequestDataArea;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
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
import scala.Int;

@Service
public class DeviceDaoImpl implements IDeviceDao {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final Logger LOGGER = Logger.getLogger(DeviceDaoImpl.class.getName());

	@Override
	public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {

		try {

			final DeviceInformation deviceInformationToUpdate = device.getDataArea().getDevice();
			final Integer netSuiteId = device.getDataArea().getDevice().getNetSuiteId();

			if (netSuiteId == null) {
				final Response response =
						new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB, IResponse.ERROR_MESSAGE);
				return new UpdateDeviceResponse(device.getHeader(), response);
			} else {

				final Query searchDeviceQuery = new Query(Criteria.where("netSuiteId").is(device.getDataArea().getDevice().getNetSuiteId()));

				final DeviceInformation deviceInformation = mongoTemplate.findOne(searchDeviceQuery, DeviceInformation.class);

				deviceInformationToUpdate.setLastUpdated(new Date());

				if (deviceInformation == null) {
					mongoTemplate.insert(deviceInformationToUpdate);
				} else {
					deviceInformationToUpdate.setMidwayMasterDeviceId(deviceInformation.getMidwayMasterDeviceId());
					mongoTemplate.save(deviceInformationToUpdate);
				}

				final Response response =
						new Response(IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB, IResponse.SUCCESS_MESSAGE);
				return new UpdateDeviceResponse(device.getHeader(), response);
			}
		}
		catch (Exception e) {
			LOGGER.error("Exception ex " + CommonUtil.getStackTrace(e));

			final Response response = new Response(IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new UpdateDeviceResponse(device.getHeader(), response);
		}
	}

	@Override
	public DeviceInformationResponse getDeviceInformationDB(DeviceInformationRequest deviceInformationRequest) {

		final Integer netSuiteId = deviceInformationRequest.getDataArea().getNetSuiteId();
		LOGGER.info("device dao netsuite id is..." + netSuiteId);

		final Header header = deviceInformationRequest.getHeader();

		if (netSuiteId == null) {

			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB, IResponse.ERROR_MESSAGE);

			return new DeviceInformationResponse(header, response, null);
		} else {
			try {

				final Query searchDeviceQuery = new Query(Criteria.where("netSuiteId").is(netSuiteId));

				final DeviceInformation deviceInformation = mongoTemplate.findOne(searchDeviceQuery, DeviceInformation.class);

				final Response response = new Response();
				if (deviceInformation == null) {
					response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
					response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB);
					response.setResponseStatus(IResponse.ERROR_MESSAGE);
				} else {
					response.setResponseCode(IResponse.SUCCESS_CODE);
					response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB);
					response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
				}

				final DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();
				deviceInformationResponseDataArea.setDevices(deviceInformation);

				return new DeviceInformationResponse(header, response, deviceInformationResponseDataArea);
			} catch (Exception e) {
				LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));

				final Response response =
						new Response(IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE);

				final DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();
				deviceInformationResponseDataArea.setDevices(null);

				return new DeviceInformationResponse(header, response, deviceInformationResponseDataArea);
			}
		}
	}

	@Override
	public void setDeviceInformationDB(Exchange exchange) {

		final Integer netSuiteId = (Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID);
		try {
			final Query searchDeviceQuery = new Query(Criteria.where("netSuiteId").is(netSuiteId));
			final DeviceInformation deviceInformation = mongoTemplate.findOne(searchDeviceQuery, DeviceInformation.class);

			exchange.setProperty(IConstant.MIDWAY_DEVICEINFO_DB, deviceInformation);
		}
		catch (Exception e) {
			LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
			LOGGER.info("Not able to fetch the data from DB....." + e.getMessage());
		}
	}

	@Override
	public void updateDeviceInformationDB(Exchange exchange) {

		final DeviceInformationResponse deviceInformationResponse = (DeviceInformationResponse) exchange.getIn().getBody();
		final DeviceInformation deviceInformation = deviceInformationResponse.getDataArea().getDevices();

		if (exchange.getProperty(IConstant.MIDWAY_DEVICEINFO_DB) != null) {
			LOGGER.info("device info was already in master DB");
			LOGGER.info("device info carrier is........." + deviceInformation.toString());

			deviceInformation.setMidwayMasterDeviceId(deviceInformation.getMidwayMasterDeviceId());
			mongoTemplate.save(deviceInformation);
		} else {
			final Integer netSuiteId = (Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID);

			LOGGER.info("device info was not already in master DB");
			LOGGER.info("device info to insert for netSuiteId " + netSuiteId);

			deviceInformation.setNetSuiteId(netSuiteId);

			mongoTemplate.insert(deviceInformation);
		}

		final DeviceInformationResponseDataArea deviceInformationResponseDataArea = deviceInformationResponse.getDataArea();
		deviceInformationResponseDataArea.setDevices(deviceInformation);
		deviceInformationResponse.setDataArea(deviceInformationResponseDataArea);
		exchange.getIn().setBody(deviceInformationResponse);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bulkOperationDeviceUpload(Exchange exchange) {

		final DeviceInformation deviceInformationToUpdate = (DeviceInformation) exchange.getIn().getBody();
		final Integer netSuiteId = deviceInformationToUpdate.getNetSuiteId();

		try {
			if (netSuiteId == null) {
				final List<BatchDeviceId> batchDeviceList = (List<BatchDeviceId>) exchange.getProperty(IConstant.BULK_ERROR_LIST);
				final BatchDeviceId errorBatchDeviceId = new BatchDeviceId();
				errorBatchDeviceId.setErrorMessage(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
				batchDeviceList.add(errorBatchDeviceId);

				exchange.setProperty(IConstant.BULK_ERROR_LIST, batchDeviceList);
			} else {
				final Query searchDeviceQuery = new Query(Criteria.where("netSuiteId").is(netSuiteId));

				final DeviceInformation deviceInformation = mongoTemplate.findOne(searchDeviceQuery, DeviceInformation.class);
				deviceInformationToUpdate.setLastUpdated(new Date());

				if (deviceInformation == null) {
					mongoTemplate.insert(deviceInformationToUpdate);
				} else {
					deviceInformationToUpdate.setMidwayMasterDeviceId(deviceInformation.getMidwayMasterDeviceId());
					mongoTemplate.save(deviceInformationToUpdate);
				}

				final List<BatchDeviceId> batchDeviceList = (List<BatchDeviceId>) exchange.getProperty(IConstant.BULK_SUCCESS_LIST);
				final BatchDeviceId successBatchDeviceId = new BatchDeviceId();
				successBatchDeviceId.setNetSuiteId("" + netSuiteId);
				batchDeviceList.add(successBatchDeviceId);

				exchange.setProperty(IConstant.BULK_SUCCESS_LIST, batchDeviceList);
			}
		}
		catch (Exception e) {
			LOGGER.error("Exception ex" + CommonUtil.getStackTrace(e));
			final List<BatchDeviceId> batchDeviceList = (List<BatchDeviceId>) exchange.getProperty(IConstant.BULK_ERROR_LIST);
			final BatchDeviceId errorBatchDeviceId = new BatchDeviceId();
			errorBatchDeviceId.setNetSuiteId("" + netSuiteId);
			errorBatchDeviceId.setErrorMessage(IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB);
			batchDeviceList.add(errorBatchDeviceId);

			exchange.setProperty(IConstant.BULK_ERROR_LIST, batchDeviceList);
		}
	}

	@Override
	public ArrayList<DeviceInformation> getAllDevices() {
		return (ArrayList<DeviceInformation>) mongoTemplate.findAll(DeviceInformation.class);
	}

	@Override
	public UsageInformationMidwayResponse getDeviceUsageInfoDB(UsageInformationMidwayRequest usageInformationMidwayRequest) {

		LOGGER.info("Begin::getDeviceUsageInfoDB");
		final Header header = usageInformationMidwayRequest.getHeader();
		final Integer netSuiteId = usageInformationMidwayRequest.getUsageInformationRequestMidwayDataArea().getNetSuiteId();
		final String startDate = usageInformationMidwayRequest.getUsageInformationRequestMidwayDataArea().getStartDate();
		final String endDate = usageInformationMidwayRequest.getUsageInformationRequestMidwayDataArea().getEndDate();

		LOGGER.info("device dao netsuite id is..." + netSuiteId);
		LOGGER.info("device dao startDate is..." + startDate);
		LOGGER.info("device dao endDate is..." + endDate);

		if (netSuiteId == null) {
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB, IResponse.ERROR_MESSAGE);

			return new UsageInformationMidwayResponse(header, response);
		}

		if (startDate == null) {
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_START_DATE_FORMAT_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new UsageInformationMidwayResponse(header, response);
		}

		if (endDate == null) {
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_END_DATE_FORMAT_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new UsageInformationMidwayResponse(header, response);
		}

		final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		final Date startDateValue;
		final Date endDateValue;

		try {
			startDateValue = formatter.parse(startDate);
			endDateValue = formatter.parse(endDate);
			LOGGER.info("startDateValue..." + startDateValue);
			LOGGER.info("endDateValue..." + endDateValue);
		} catch (ParseException e1) {
			LOGGER.error(e1);
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new UsageInformationMidwayResponse(header, response);
		}

		if (startDateValue.after(endDateValue)) {
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_START_END_VALIDATION_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new UsageInformationMidwayResponse(header, response);
		}

		try {
			final Query searchDeviceQuery = new Query(Criteria.where("netSuiteId").is(netSuiteId))
					.addCriteria(Criteria.where("date").gte(startDate).orOperator(Criteria.where("date").lte(endDate)))
					.addCriteria(Criteria.where("isValid").is(true));

			LOGGER.info("searchDeviceQuery::::::::::::::" + searchDeviceQuery);

			final List<DeviceUsage> deviceUsage = mongoTemplate.find(searchDeviceQuery, DeviceUsage.class);

			int deviceUsageSize = 0;

			if (deviceUsage != null) {
				deviceUsageSize = deviceUsage.size();

				LOGGER.info("deviceUsage size is....." + deviceUsageSize);
			}

			if (deviceUsageSize == 0) {
				final Response response =
						new Response(IResponse.NO_DATA_FOUND_CODE, IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE);

				return new UsageInformationMidwayResponse(header, response, new UsageInformationResponseMidwayDataArea());
			} else {

				LOGGER.info("deviceUsage        " + deviceUsage.size());
				final List<DeviceUsages> deviceDateBasedUsageList = new ArrayList<>();

				for (DeviceUsage deviceUsageValue : deviceUsage) {
					final DeviceUsages deviceDateBasedUsage = new DeviceUsages();
					deviceDateBasedUsage.setDataUsed(deviceUsageValue.getDataUsed());
					deviceDateBasedUsage.setDate(deviceUsageValue.getDate());

					deviceDateBasedUsageList.add(deviceDateBasedUsage);
				}

				// Sort the deviceEventsList on the basis of time event occurred at
				Collections.sort(deviceDateBasedUsageList, (a, b) -> a.getDate().compareTo(b.getDate()));

				final Response response =
						new Response(IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB, IResponse.SUCCESS_MESSAGE);

				final UsageInformationResponseMidwayDataArea usageInformationResponseMidwayDataArea = new UsageInformationResponseMidwayDataArea();
				usageInformationResponseMidwayDataArea.setDeviceUsages(deviceDateBasedUsageList);

				return new UsageInformationMidwayResponse(header, response, usageInformationResponseMidwayDataArea);
			}
		} catch (Exception e) {
			LOGGER.error("Exception ex " + CommonUtil.getStackTrace(e));

			final Response response =
					new Response(IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE);

			return new UsageInformationMidwayResponse(header, response, new UsageInformationResponseMidwayDataArea());
		}
	}

	@Override
	public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(ConnectionInformationMidwayRequest connectionInformationMidwayRequest) {
		LOGGER.info("Begin::getDeviceConnectionHistoryInfoDB");

		final Header header = connectionInformationMidwayRequest.getHeader();
		final Integer netSuiteId = connectionInformationMidwayRequest.getDataArea().getNetSuiteId();
		final String startDate = connectionInformationMidwayRequest.getDataArea().getStartDate();
		final String endDate = connectionInformationMidwayRequest.getDataArea().getEndDate();

		LOGGER.info("device dao netsuite id is..." + netSuiteId);
		LOGGER.info("device dao startDate is..." + startDate);
		LOGGER.info("device dao endDate is..." + endDate);

		if (netSuiteId == null) {
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new ConnectionInformationMidwayResponse(header, response);
		}

		if (startDate == null) {
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_START_DATE_FORMAT_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new ConnectionInformationMidwayResponse(header, response);
		}

		if (endDate == null) {
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_END_DATE_FORMAT_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new ConnectionInformationMidwayResponse(header, response);
		}

		final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		final Date startDateValue;
		final Date endDateValue;

		try {
			startDateValue = formatter.parse(startDate);
			endDateValue = formatter.parse(endDate);
			LOGGER.info("startDateValue..." + startDateValue);
			LOGGER.info("endDateValue..." + endDateValue);
		} catch (ParseException e1) {
			LOGGER.info(" format error while parsing the date");
			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new ConnectionInformationMidwayResponse(header, response);
		}

		if (startDateValue.after(endDateValue)) {
			LOGGER.info("Earliest date should not be greater than Latest date");

			final Response response =
					new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_START_END_VALIDATION_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new ConnectionInformationMidwayResponse(header, response);
		}

		try {
			final Query searchDeviceQuery = new Query(Criteria.where("netSuiteId").is(netSuiteId)).addCriteria(
					Criteria.where("date").gte(startDate).orOperator(Criteria.where("date").lte(endDate)))
					.addCriteria(Criteria.where("isValid").is(true));

			LOGGER.info("searchDeviceQuery::::::::::::::" + searchDeviceQuery);

			final List<DeviceConnection> deviceConnectionUsage = mongoTemplate.find(searchDeviceQuery, DeviceConnection.class);

			int deviceConnectionUsageSize = 0;

			if (deviceConnectionUsage != null) {
				deviceConnectionUsageSize = deviceConnectionUsage.size();
				LOGGER.info("deviceConnectionUsage size is....." + deviceConnectionUsageSize);
			}

			if (deviceConnectionUsageSize == 0) {
				final Response response =
						new Response(IResponse.NO_DATA_FOUND_CODE, IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE);
				return new ConnectionInformationMidwayResponse(header, response);
			} else {
				final List<DeviceEvents> deviceEventsList = new ArrayList<>();

				for (DeviceConnection deviceConnection : deviceConnectionUsage) {
					if (deviceConnection.getEvent() != null) {
						final DeviceEvent[] deviceEventArr = deviceConnection.getEvent();

						LOGGER.info("device event size for Date " + deviceConnection.getDate() + " is " + deviceEventArr.length);

						for (DeviceEvent deviceEvent : deviceEventArr) {
							final String eventType = deviceEvent.getEventType();
							final String occurredAt = deviceEvent.getOccurredAt();
							final String byteUsed = deviceEvent.getBytesUsed();

							final DeviceEvents deviceEvents = new DeviceEvents();
							deviceEvents.setBytesUsed(byteUsed);
							deviceEvents.setEventType(eventType);
							deviceEvents.setOccurredAt(occurredAt);
							deviceEventsList.add(deviceEvents);
						}
					}
				}

				// Sort the deviceEventsList on the basis of time event occurred at
				Collections.sort(deviceEventsList, (a, b) -> a.getOccurredAt().compareTo(b.getOccurredAt()));

				final ConnectionInformationResponseMidwayDataArea dataArea = new ConnectionInformationResponseMidwayDataArea();
				dataArea.setEvents(deviceEventsList);

				final Response response =
						new Response(IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB, IResponse.SUCCESS_MESSAGE);

				return new ConnectionInformationMidwayResponse(header, response, dataArea);
			}
		} catch (Exception e) {
			LOGGER.error("Exception ex " + CommonUtil.getStackTrace(e));

			final Response response =
					new Response(IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new ConnectionInformationMidwayResponse(header, response, new ConnectionInformationResponseMidwayDataArea());
		}
	}

	@Override
	public DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(DevicesUsageByDayAndCarrierRequest devicesUsageByDayAndCarrierRequest) {

		final Header header = devicesUsageByDayAndCarrierRequest.getHeader();
		final String date = devicesUsageByDayAndCarrierRequest.getDateArea().getDate();

		if (date != null) {
			if (!(CommonUtil.isValidDateFormat(date))) {
				final Response response =
						new Response(IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_DATE_VALIDATE_JOB_MIDWAYDB, IResponse.ERROR_MESSAGE);
				return new DevicesUsageByDayAndCarrierResponse(header, response);
			}
		}

		try {
			final Aggregation agg = newAggregation(
					match(Criteria.where("date").is(date)
							.and(IConstant.CARRIER_NAME).regex(devicesUsageByDayAndCarrierRequest.getHeader().getBsCarrier(), "i")
							.and("isValid").is(true)
							.and("dataUsed").ne(0)),
					project("dataUsed").and("netSuiteId").as("netSuiteId"));

			final AggregationResults<DevicesUsageByDayAndCarrier> results = mongoTemplate.aggregate(agg, DeviceUsage.class, DevicesUsageByDayAndCarrier.class);

			LOGGER.info("aggregation result is......" + results);

			if (results == null || results.getMappedResults().size() == 0) {
				final Response response =
						new Response(IResponse.NO_DATA_FOUND_CODE, IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE);
				return new DevicesUsageByDayAndCarrierResponse(header, response);
			} else {
				final Response response =
						new Response(IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB, IResponse.SUCCESS_MESSAGE);

				LOGGER.info("size of result is     ......" + results.getMappedResults().size());

				final DevicesUsageByDayAndCarrierResponseDataArea devicesUsageByDayAndCarrierResponseDataArea = new DevicesUsageByDayAndCarrierResponseDataArea();
				devicesUsageByDayAndCarrierResponseDataArea.setDevices(results.getMappedResults());
				devicesUsageByDayAndCarrierResponseDataArea.setDate(date);

				return new DevicesUsageByDayAndCarrierResponse(header, response, devicesUsageByDayAndCarrierResponseDataArea);
			}
		} catch (Exception e) {
			LOGGER.error("Exception ex " + CommonUtil.getStackTrace(e));
			final Response response =
					new Response(IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE);
			return new DevicesUsageByDayAndCarrierResponse(header, response);
		}
	}

	@Override
	public UsageInformationRequest getDeviceSessionUsage(SessionRequest sessionRequest) throws InvalidParameterException {
		SessionRequestDataArea requestDataArea = sessionRequest.getDataArea();
		Integer netSuiteId = requestDataArea.getNetSuiteId();

		UsageInformationRequestDataArea dataArea = new UsageInformationRequestDataArea();
		DeviceId deviceId = getDeviceId(netSuiteId);
		dataArea.setDeviceId(deviceId);
		UsageInformationRequest request = new UsageInformationRequest();
		request.setHeader(sessionRequest.getHeader());
		dataArea.setEarliest(requestDataArea.getEarliest());
		dataArea.setLatest(requestDataArea.getLatest());
		request.setDataArea(dataArea);
		return request;
	}

	@Override
	public ConnectionInformationRequest getDeviceSessionInfo(SessionRequest sessionRequest) throws InvalidParameterException {
		SessionRequestDataArea requestDataArea = sessionRequest.getDataArea();
		Integer netSuiteId = requestDataArea.getNetSuiteId();
		ConnectionInformationRequest request = new ConnectionInformationRequest();
		request.setHeader(sessionRequest.getHeader());
		ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
		DeviceId deviceId = getDeviceId(netSuiteId);
		dataArea.setDeviceId(deviceId);
		dataArea.setEarliest(requestDataArea.getEarliest());
		dataArea.setLatest(requestDataArea.getLatest());
		request.setDataArea(dataArea);
		return request;
	}

	private DeviceId getDeviceId(Integer netSuiteId) throws InvalidParameterException {
		final Query searchDeviceQuery = new Query(Criteria.where("netSuiteId").is(netSuiteId));
		DeviceInformation deviceInformation = mongoTemplate.findOne(searchDeviceQuery, DeviceInformation.class);
		if (deviceInformation != null) {
			for (DeviceId deviceId : deviceInformation.getDeviceIds()) {
				if ("ICCID".equalsIgnoreCase(deviceId.getKind()) || "MEID".equalsIgnoreCase(deviceId.getKind())) {
					return deviceId;
				}
			}
		}
		throw new InvalidParameterException("402", "Cannot find a compatible DeviceId for the Carriers to consume for netSuitId " + netSuiteId + ".");
	}
}