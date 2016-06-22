package com.gv.midway.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponseMidwayDataArea;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponseMidwayDataArea;

@Service
public class DeviceDaoImpl implements IDeviceDao {
	/*
	 * @Autowired MongoDb grandVictorDB;
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	private Logger log = Logger.getLogger(DeviceDaoImpl.class.getName());

	public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {
		// TODO Auto-generated method stub

		DeviceInformation deviceInfomation = null;
		try {

			DeviceInformation deviceInformationToUpdate = device.getDataArea()
					.getDevice();
			String netSuiteId = device.getDataArea().getDevice()
					.getNetSuiteId();

			if (netSuiteId == null || netSuiteId.trim().equals("")) {

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

			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ssZ");

			deviceInformationToUpdate.setLastUpdated(sdf.format(new Date()));

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

	public DeviceInformationResponse getDeviceInformationDB(
			DeviceInformationRequest deviceInformationRequest) {
		// TODO Auto-generated method stub

		String netSuiteId = deviceInformationRequest.getDataArea()
				.getNetSuiteId();
		log.info("device dao netsuite id is..." + netSuiteId);

		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		deviceInformationResponse.setHeader(deviceInformationRequest
				.getHeader());
		Response response = new Response();
		if(netSuiteId==null||netSuiteId.trim().equals("")){
			
			
			response.setResponseCode(IResponse.INVALID_PAYLOAD);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			
			deviceInformationResponse.setResponse(response);
			
			return deviceInformationResponse;
		}
		else
		{
		try {

			Query searchDeviceQuery = new Query(Criteria.where("netSuiteId")
					.is(netSuiteId));

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

	public void setDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub

		String netSuiteId = (String) exchange
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

			log.info("Not able to fetch the data from DB....." + e.getMessage());
		}

	}

	public void updateDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		DeviceInformationResponse deviceInformationResponse = (DeviceInformationResponse) exchange
				.getIn().getBody();

		DeviceInformation deviceInformation = deviceInformationResponse
				.getDataArea().getDevices();

		if (exchange.getProperty(IConstant.MIDWAY_DEVICEINFO_DB) != null) {

			log.info("device info was already in master DB");

			log.info("device info carrier is........."
					+ deviceInformation.toString());

			deviceInformation.setMidwayMasterDeviceId(deviceInformation
					.getMidwayMasterDeviceId());

			mongoTemplate.save(deviceInformation);
		}

		else {
			log.info("device info was not already in master DB");

			String netSuiteId = (String) exchange
					.getProperty(IConstant.MIDWAY_NETSUITE_ID);

			log.info("device info to insert for netsuideId " + netSuiteId);

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

	public void bulkOperationDeviceUpload(Exchange exchange) {
		// TODO Auto-generated method stub

		DeviceInformation deviceInformationToUpdate = (DeviceInformation) exchange
				.getIn().getBody();

		String netSuiteId = deviceInformationToUpdate.getNetSuiteId();

		try {

			if (netSuiteId == null || netSuiteId.trim().equals("")) {

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

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ssZ");
				deviceInformationToUpdate
						.setLastUpdated(sdf.format(new Date()));

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
				successBatchDeviceId.setNetSuiteId(netSuiteId);
				batchDeviceList.add(successBatchDeviceId);

				exchange.setProperty(IConstant.BULK_SUCCESS_LIST,
						batchDeviceList);
			}

		}

		catch (Exception e) {

			List<BatchDeviceId> batchDeviceList = (List<BatchDeviceId>) exchange
					.getProperty(IConstant.BULK_ERROR_LIST);
			BatchDeviceId errorBatchDeviceId = new BatchDeviceId();
			errorBatchDeviceId.setNetSuiteId(netSuiteId);
			errorBatchDeviceId
					.setErrorMessage(IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB);
			batchDeviceList.add(errorBatchDeviceId);

			exchange.setProperty(IConstant.BULK_ERROR_LIST, batchDeviceList);

		}

	}

	@Override
	public ArrayList<DeviceInformation> getAllDevices() {

		ArrayList<DeviceInformation> deviceInfo = new ArrayList<DeviceInformation>();
		return (ArrayList<DeviceInformation>) mongoTemplate
				.findAll(DeviceInformation.class);

	}

	@Override
	public UsageInformationMidwayResponse getDeviceUsageInfoDB(
			UsageInformationMidwayRequest usageInformationMidwayRequest) {

		String netSuiteId = usageInformationMidwayRequest
				.getUsageInformationRequestMidwayDataArea().getNetSuiteId();
		log.info("device dao netsuite id is..." + netSuiteId);

		UsageInformationMidwayResponse usageInformationMidwayResponse = new UsageInformationMidwayResponse();

		usageInformationMidwayResponse.setHeader(usageInformationMidwayRequest
				.getHeader());
		Response response = new Response();
		if (netSuiteId == null || netSuiteId.trim().equals("")) {

			response.setResponseCode(IResponse.INVALID_PAYLOAD);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);

			usageInformationMidwayResponse.setResponse(response);

			return usageInformationMidwayResponse;
		} else {
			try {

				Query searchDeviceQuery = new Query(Criteria
						.where("netSuiteId").is(netSuiteId));

				DeviceUsage deviceUsage = (DeviceUsage) mongoTemplate.findOne(
						searchDeviceQuery, DeviceUsage.class);

				if (deviceUsage == null)

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

				usageInformationMidwayResponse.setResponse(response);

				UsageInformationResponseMidwayDataArea usageInformationResponseMidwayDataArea = new UsageInformationResponseMidwayDataArea();

				usageInformationMidwayResponse
						.setDataArea(usageInformationResponseMidwayDataArea);

				return usageInformationMidwayResponse;
			} catch (Exception e) {

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

	}

	@Override
	public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
			ConnectionInformationMidwayRequest connectionInformationMidwayRequest) {
		// TODO Auto-generated method stub
		String netSuiteId = connectionInformationMidwayRequest.getDataArea()
				.getNetSuiteId();

		log.info("device dao netsuite id is..." + netSuiteId);

		ConnectionInformationMidwayResponse connectionInformationMidwayResponse = new ConnectionInformationMidwayResponse();

		connectionInformationMidwayResponse
				.setHeader(connectionInformationMidwayRequest.getHeader());
		Response response = new Response();
		if (netSuiteId == null || netSuiteId.trim().equals("")) {

			response.setResponseCode(IResponse.INVALID_PAYLOAD);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);

			connectionInformationMidwayResponse.setResponse(response);

			return connectionInformationMidwayResponse;
		} else {
			try {

				Query searchDeviceQuery = new Query(Criteria
						.where("netSuiteId").is(netSuiteId));

				DeviceUsage deviceUsage = (DeviceUsage) mongoTemplate.findOne(
						searchDeviceQuery, DeviceUsage.class);

				if (deviceUsage == null)

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

				connectionInformationMidwayResponse.setResponse(response);

				ConnectionInformationResponseMidwayDataArea connectionInformationResponseMidwayDataArea = new ConnectionInformationResponseMidwayDataArea();

				connectionInformationMidwayResponse
						.setDataArea(connectionInformationResponseMidwayDataArea);

				return connectionInformationMidwayResponse;
			} catch (Exception e) {

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
}
