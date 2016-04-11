package com.gv.midway.service.impl;


import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.InsertDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.service.IDeviceService;




@Service
public class DeviceServiceImpl implements IDeviceService {

	@Autowired
	private IDeviceDao iDeviceDao;

	public InsertDeviceResponse insertDeviceDetails(Exchange exchange) {

		SingleDevice device = (SingleDevice) exchange.getIn().getBody();

		return  iDeviceDao.insertDeviceDetails(device);
		
		
		
	}

	public UpdateDeviceResponse updateDeviceDetails(Exchange exchange) {
		// TODO Auto-generated method stub
		
		                       
		/*c
		
	    String deviceId = exchange.getIn().getHeader("id", String.class);
       ;*/
		
		SingleDevice device = (SingleDevice) exchange.getIn().getBody();

		return  iDeviceDao.updateDeviceDetails(device);
		
		
		
	}

	public DeviceInformationResponse getDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub

		
	   /* String deviceId = exchange.getIn().getHeader("id", String.class);
       
		System.out.println("id is...."+deviceId);*/
		
		DeviceInformationRequest deviceInformationRequest = (DeviceInformationRequest) exchange.getIn().getBody();
		
		System.out.println("device information is.........."+deviceInformationRequest.toString());
		
		String netSuiteId=deviceInformationRequest.getDataArea().getNetSuiteId();
		
		
		return iDeviceDao.getDeviceInformationDB(deviceInformationRequest);
	}

	
	public Object getDeviceDetailsBsId(Exchange exchange) {
		// TODO Auto-generated method stub
		
		String bs_Id = exchange.getIn().getHeader("bs_id", String.class);
	       
		System.out.println("bs_Id is...."+bs_Id);
		
		
		return iDeviceDao.getDeviceDetailsBsId(bs_Id);	
		
	}


	public Object insertDevicesDetailsInBatch(Exchange exchange) {
		// TODO Auto-generated method stub
		BulkDevices devices = (BulkDevices) exchange.getIn().getBody();

		return  iDeviceDao.insertDevicesDetailsInBatch(devices);
	}

	public void setDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		 iDeviceDao.setDeviceInformationDB(exchange);
	}
	
	public void updateDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		 iDeviceDao.updateDeviceInformationDB(exchange);
	}

	

	/**public String insertDevicesDetailsInBatch(Exchange exchange) {

		Devices devices = (Devices) exchange.getIn().getBody();
		return iDeviceDao.insertDevicesDetailsInBatch(devices);

	}
	
	public String updateDevicesDetailsInBatch(Exchange exchange) {

		Devices devices = (Devices) exchange.getIn().getBody();
		return iDeviceDao.updateDevicesDetailsInBatch(devices);

	}*/

}
