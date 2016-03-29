package com.gv.midway.controller;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import com.gv.midway.device.request.pojo.Device;
import com.gv.midway.device.request.pojo.Devices;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.verizon.generic.callback.VerizonActivateCallBackRequest;


@SuppressWarnings("all")
public class AdaptationLayerServiceImpl implements IAdaptaionLayerService {

	@EndpointInject(uri = "")
	ProducerTemplate producer;

	public String activateDevice() {
		producer.requestBody("direct:verizon");

		return null;

	}

	public Object insertDeviceDetails(Device device) {
		// TODO Auto-generated method stub

		System.out.println("device is...." + device.toString());

		Object response = producer.requestBody("direct:insertDeviceDetails",
				device);

		System.out.println(" insertDeviceDetails respsone is ........"
				+ response);

		return response;
	}

	public Object updateDeviceDetails(String id, Device device) {
		// TODO Auto-generated method stub
		System.out.println("device id is...." + id);

		System.out.println("device info to update is...." + device.toString());

		Object response = producer.requestBodyAndHeader(
				"direct:updateDeviceDetails", device, "id", id);

		System.out.println("updateDeviceDetails respsone is ........"
				+ response);

		return response;
	}

	public Object getDeviceInfo(String id) {
		// TODO Auto-generated method stub

		System.out.println("device id is...." + id);

		Object response = producer.requestBodyAndHeader(
				"direct:getDeviceDetails", null, "id", id);

		System.out.println("direct:getDeviceDetails respsone is ........"
				+ response);

		return response;
	}

	public Object getDeviceInfoBsId(String bs_id) {
		// TODO Auto-generated method stub
		System.out.println("device bs_id is...." + bs_id);

		Object response = producer.requestBodyAndHeader(
				"direct:getDeviceDetailsBsId", null, "bs_id", bs_id);

		System.out.println("direct:getDeviceDetailsBs_Id respsone is ........"
				+ response);

		return response;
	}

	public Object insertDevicesDetailsInBatch(Devices devices) {
		// TODO Auto-generated method stub
		System.out.println("devices info is...." + devices.toString());

		Object response = producer.requestBody(
				"direct:insertDeviceDetailsinBatch", devices);

		System.out.println(" insertDeviceDetails in Batch respsone is ........"
				+ response);

		return response;
	}

	public DeviceInformationResponse deviceInformationDevice(
			DeviceInformationRequest deviceInformationRequest) {

		return (DeviceInformationResponse) producer.requestBody(
				"direct:deviceInformation", deviceInformationRequest);
	}

	public DeactivateDeviceResponse deactivateDevice(
			DeactivateDeviceRequest deactivateDeviceRequest) {
		
		return (DeactivateDeviceResponse) producer.requestBody(
				"direct:deactivateDevice", deactivateDeviceRequest);
	}
	
	public ActivateDeviceResponse activateDevice(
			ActivateDeviceRequest activateDeviceRequest) {
		
		return (ActivateDeviceResponse) producer.requestBody(
				"direct:activateDevice", activateDeviceRequest);
	}
	
	public VerizonActivateCallBackRequest activateCallback(VerizonActivateCallBackRequest callbackRequest) {
		return (VerizonActivateCallBackRequest) producer.requestBody("direct:callbacks", callbackRequest);
	}
}