package com.gv.midway.controller;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import com.gv.midway.pojo.DeviceInformationRequest;
import com.gv.midway.pojo.DeviceInformationResponse;
import com.gv.midway.pojo.User1;
import com.gv.midway.pojo.request.Device;
import com.gv.midway.pojo.request.Devices;

@SuppressWarnings("all")
public class AdaptationLayerServiceImpl implements IAdaptaionLayerService {

	@EndpointInject(uri = "")
	ProducerTemplate producer;

	public String activateDevice(User1 user) {
		 producer.requestBody("direct:verizon", user);
		System.out.println("HELLO____________");
		return null;

	}

	public String insertDeviceDetails(Device device) {
		return (String) producer.requestBody("direct:insertDeviceDetails",
				device);
		// return null;
	}

	public String synchronizeDevices() {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateDeviceDetails(Device device) {
		return (String) producer.requestBody("direct:updateDeviceDetails",
				device);

	}

	public String insertDevicesDetailsInBatch(Devices devices) {
		
		return (String) producer.requestBody("direct:insertDevicesDetailsInBatch",
				devices);
		
	}

	public String  updateDevicesDetailsInBatch(Devices devices){
		return (String) producer.requestBody("direct:updateDevicesDetailsInBatch",
				devices);
	}
	
	public DeviceInformationResponse deviceInformationDevice(DeviceInformationRequest deviceInformationRequest) {
		
		return (DeviceInformationResponse) producer.requestBody("direct:verizonStub", deviceInformationRequest);
}
}