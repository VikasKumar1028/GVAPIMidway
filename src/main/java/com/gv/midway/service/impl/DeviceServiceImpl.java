package com.gv.midway.service.impl;


import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.device.request.pojo.Device;
import com.gv.midway.device.request.pojo.Devices;
import com.gv.midway.service.IDeviceService;



@Service
public class DeviceServiceImpl implements IDeviceService {

	@Autowired
	private IDeviceDao iDeviceDao;

	public Object insertDeviceDetails(Exchange exchange) {

		Device device = (Device) exchange.getIn().getBody();

		return  iDeviceDao.insertDeviceDetails(device);
		
		
		
	}

	public Object updateDeviceDetails(Exchange exchange) {
		// TODO Auto-generated method stub
		
		                       
		Device device = (Device)exchange.getIn().getBody();
		
	    String deviceId = exchange.getIn().getHeader("id", String.class);
       
		System.out.println("id is...."+deviceId);
		
		
		return iDeviceDao.updateDeviceDetails(deviceId, device);
		
		
	}

	public Object getDeviceDetails(Exchange exchange) {
		// TODO Auto-generated method stub

		
	    String deviceId = exchange.getIn().getHeader("id", String.class);
       
		System.out.println("id is...."+deviceId);
		
		
		return iDeviceDao.getDeviceDetails(deviceId);
	}

	
	public Object getDeviceDetailsBsId(Exchange exchange) {
		// TODO Auto-generated method stub
		
		String bs_Id = exchange.getIn().getHeader("bs_id", String.class);
	       
		System.out.println("bs_Id is...."+bs_Id);
		
		
		return iDeviceDao.getDeviceDetailsBsId(bs_Id);	
		
	}


	public Object insertDevicesDetailsInBatch(Exchange exchange) {
		// TODO Auto-generated method stub
		Devices devices = (Devices) exchange.getIn().getBody();

		return  iDeviceDao.insertDevicesDetailsInBatch(devices);
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
