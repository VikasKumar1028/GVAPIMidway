package com.gv.midway.service.impl;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.request.Device;
import com.gv.midway.pojo.request.Devices;
import com.gv.midway.service.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService {

	@Autowired
	private IDeviceDao iDeviceDao;

	public String insertDeviceDetails(Exchange exchange) {

		Device device = (Device) exchange.getIn().getBody();
		return iDeviceDao.insertDeviceDetails(device);
	}

	public String updateDeviceDetails(Exchange exchange) {
		Device device = (Device) exchange.getIn().getBody();
		return iDeviceDao.updateDeviceDetails(device);
	}

	public String insertDevicesDetailsInBatch(Exchange exchange) {

		Devices devices = (Devices) exchange.getIn().getBody();
		return iDeviceDao.insertDevicesDetailsInBatch(devices);

	}

	public String updateDevicesDetailsInBatch(Exchange exchange) {

		Devices devices = (Devices) exchange.getIn().getBody();
		return iDeviceDao.updateDevicesDetailsInBatch(devices);

	}

}
