package com.gv.midway.controller;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.callback.TargetResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.response.ConnectionInformationResponse;
import com.gv.midway.pojo.customFieldsUpdateDevice.request.CustomFieldsUpdateDeviceRequest;
import com.gv.midway.pojo.customFieldsUpdateDevice.response.CustomFieldsUpdateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.verizon.DeviceId;

@SuppressWarnings("all")
public class AdaptationLayerServiceImpl implements IAdaptaionLayerService {

	@EndpointInject(uri = "")
	ProducerTemplate producer;

	public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {
		// TODO Auto-generated method stub

		System.out.println("device info to update is...." + device.toString());

		/*
		 * UpdateDeviceResponse response =(UpdateDeviceResponse)
		 * producer.requestBodyAndHeader( "direct:updateDeviceDetails", device,
		 * "id", id);
		 */

		UpdateDeviceResponse response = (UpdateDeviceResponse) producer.requestBody("direct:updateDeviceDetails", device);

		System.out.println("updateDeviceDetails respsone is ........" + response);

		return response;
	}

	public DeviceInformationResponse getDeviceInfoDB(String region, String timestamp, String organization, String transactionId, String sourceName, String applicationName, String bsCarrier, String netSuiteId) {
		// TODO Auto-generated method stub

		DeviceInformationRequest deviceInformationRequest = new DeviceInformationRequest();

		Header header = new Header();
		header.setRegion(region);
		header.setApplicationName(applicationName);
		header.setBsCarrier(bsCarrier);
		header.setSourceName(sourceName);
		header.setTimestamp(timestamp);
		header.setTransactionId(transactionId);
		header.setOrganization(organization);

		DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
		dataArea.setNetSuiteId(netSuiteId);

		deviceInformationRequest.setHeader(header);
		deviceInformationRequest.setDataArea(dataArea);

		DeviceInformationResponse response = (DeviceInformationResponse) producer.requestBody("direct:getDeviceInformationDB", deviceInformationRequest);

		return response;
	}

	public DeviceInformationResponse getDeviceInfoCarrier(String region, String timestamp, String organization, String transactionId, String sourceName, String applicationName, String bsCarrier, String netSuiteId, String deviceId, String kind) {
		// TODO Auto-generated method stub

		DeviceInformationRequest deviceInformationRequest = new DeviceInformationRequest();

		Header header = new Header();
		header.setRegion(region);
		header.setApplicationName(applicationName);
		header.setBsCarrier(bsCarrier);
		header.setSourceName(sourceName);
		header.setTimestamp(timestamp);
		header.setTransactionId(transactionId);
		header.setOrganization(organization);

		DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
		dataArea.setNetSuiteId(netSuiteId);
		DeviceId deviceIdObj = new DeviceId();
		deviceIdObj.setId(deviceId);
		deviceIdObj.setKind(kind);
		dataArea.setDeviceId(deviceIdObj);

		deviceInformationRequest.setHeader(header);
		deviceInformationRequest.setDataArea(dataArea);

		DeviceInformationResponse response = (DeviceInformationResponse) producer.requestBody("direct:deviceInformationCarrier", deviceInformationRequest);

		return response;
	}

	public BatchDeviceResponse updateDevicesDetailsBulk(BulkDevices devices) {
		// TODO Auto-generated method stub
		System.out.println("devices info is...." + devices.toString());

		Object responseActual = producer.requestBody("direct:updateDevicesDetailsBulk", devices);

		System.out.println("resposne actual is........" + responseActual.toString());

		BatchDeviceResponse response = (BatchDeviceResponse) responseActual;

		System.out.println(" direct:updateDevicesDetails in Batch respsone is ........" + response);

		return response;
	}

	public DeactivateDeviceResponse deactivateDevice(DeactivateDeviceRequest deactivateDeviceRequest) {

		return (DeactivateDeviceResponse) producer.requestBody("direct:deactivateDevice", deactivateDeviceRequest);
	}

	public ActivateDeviceResponse activateDevice(ActivateDeviceRequest activateDeviceRequest) {

		return (ActivateDeviceResponse) producer.requestBody("direct:activateDevice", activateDeviceRequest);
	}

	public ReactivateDeviceResponse reActivateDevice(ReactivateDeviceRequest reActivateDeviceRequest) {
		return (ReactivateDeviceResponse) producer.requestBody("direct:reActivateDevice", reActivateDeviceRequest);
	}

	public SuspendDeviceResponse suspendDevice(SuspendDeviceRequest suspendDeviceRequest) {

		return (SuspendDeviceResponse) producer.requestBody("direct:suspendDevice", suspendDeviceRequest);
	}

	public CustomFieldsUpdateDeviceResponse customFieldsUpdateRequest(CustomFieldsUpdateDeviceRequest customeFieldDeviceRequest) {

		return (CustomFieldsUpdateDeviceResponse) producer.requestBody("direct:customeFields", customeFieldDeviceRequest);
	}

	public TargetResponse callbacks(CallBackVerizonRequest callbackRequest) {
		return (TargetResponse) producer.requestBody("direct:callbacks", callbackRequest);
	}

	public ConnectionInformationResponse connectionInformation(ConnectionInformationRequest connectionInformationRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}