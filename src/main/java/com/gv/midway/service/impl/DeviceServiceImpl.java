package com.gv.midway.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.service.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService {

	@Autowired
	private IDeviceDao iDeviceDao;

	private Logger log = Logger.getLogger(DeviceServiceImpl.class.getName());



	public UpdateDeviceResponse updateDeviceDetails(Exchange exchange) {
		// TODO Auto-generated method stub

		SingleDevice device = (SingleDevice) exchange.getIn().getBody();

		return iDeviceDao.updateDeviceDetails(device);

	}

	public DeviceInformationResponse getDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub

		
		DeviceInformationRequest deviceInformationRequest = (DeviceInformationRequest) exchange
				.getIn().getBody();

		log.info("device information is.........."
				+ deviceInformationRequest.toString());

		String netSuiteId = deviceInformationRequest.getDataArea()
				.getNetSuiteId();

		return iDeviceDao.getDeviceInformationDB(deviceInformationRequest);
	}

	

	public void updateDevicesDetailsBulk(Exchange exchange) {
		// TODO Auto-generated method stub
		BulkDevices devices = (BulkDevices) exchange.getIn().getBody();

		DeviceInformation[] deviceInformationArr = devices.getDataArea()
				.getDevices();

		List<DeviceInformation> deviceInformationList = Arrays
				.asList(deviceInformationArr);

		List<BatchDeviceId> successList = new ArrayList<BatchDeviceId>();

		List<BatchDeviceId> failureList = new ArrayList<BatchDeviceId>();

		exchange.setProperty(IConstant.BULK_SUCCESS_LIST, successList);
		exchange.setProperty(IConstant.BULK_ERROR_LIST, failureList);

		log.info("******************Before doing bulk insertion using seda***************");

		exchange.getIn().setBody(deviceInformationList);

		log.info("******************In the end of bulk insert***************");

	}

	public void setDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		iDeviceDao.setDeviceInformationDB(exchange);
	}

	public void updateDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		iDeviceDao.updateDeviceInformationDB(exchange);
	}

	public void bulkOperationDeviceSyncInDB(Exchange exchange) {
		// TODO Auto-generated method stub
		iDeviceDao.bulkOperationDeviceUpload(exchange);
	}

	@Override
	public ArrayList<DeviceInformation> getAllDevices() {

		return iDeviceDao.getAllDevices();
	}


	public UsageInformationMidwayResponse getDeviceUsageInfoDB(Exchange exchange) {
		// TODO Auto-generated method stub
		UsageInformationMidwayRequest usageInformationMidwayRequest = (UsageInformationMidwayRequest) exchange
				.getIn().getBody();
		return iDeviceDao.getDeviceUsageInfoDB(usageInformationMidwayRequest);
	}


	public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
			Exchange exchange) {

		ConnectionInformationMidwayRequest connectionInformationMidwayRequest = (ConnectionInformationMidwayRequest) exchange
				.getIn().getBody();
		return iDeviceDao
				.getDeviceConnectionHistoryInfoDB(connectionInformationMidwayRequest);
	}

	

}
