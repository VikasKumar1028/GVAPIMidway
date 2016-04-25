package com.gv.midway.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@SuppressWarnings("all")
@Path("/v1")
@Api(value = "/v1", description = "MidWay API Integration")
public interface IAdaptaionLayerService {

	@PUT
	@Path("/cell/upload")
	@Produces("application/json")
	@ApiOperation(value = "Insert or Update Device Details")
	UpdateDeviceResponse updateDeviceDetails(SingleDevice device);

	@GET
	@Path("/cell/info/midway")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details from Midway DB")
	DeviceInformationResponse getDeviceInfoDB(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("netSuiteId") final String netSuiteId);

	@GET
	@Path("/cell/info/carrier")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details from Carrier")
	DeviceInformationResponse getDeviceInfoCarrier(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("netSuiteId") final String netSuiteId,
			@QueryParam("deviceId") final String deviceId,
			@QueryParam("kind") final String kind);

	@POST
	@Path("/cells/upload/bulk")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Insert or Update Device Details in Bulk")
	BatchDeviceResponse updateDevicesDetailsBulk(BulkDevices device);

	@POST
	@Path("/device/deactivate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "DeactivateDeviceService")
	DeactivateDeviceResponse deactivateDevice(
			DeactivateDeviceRequest deactivateDeviceRequest);

	@POST
	@Path("/device/activate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "ActivateDeviceService")
	ActivateDeviceResponse activateDevice(
			ActivateDeviceRequest activateDeviceRequest);

	@POST
	@Path("/device/suspend")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "SuspendDeviceService")
	SuspendDeviceResponse suspendDevice(
			SuspendDeviceRequest suspendDeviceRequest);

	@POST
	@Path("/device/customFields")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "CustomFieldsUpdateRequest")
	CustomFieldsUpdateDeviceResponse customFieldsUpdateRequest(
			CustomFieldsUpdateDeviceRequest customeFieldDeviceRequest);

	@POST
	@Path("/device/callback")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "CallbackService")
	TargetResponse callbacks(CallBackVerizonRequest activateDeviceRequest);

	@POST
	@Path("/devices/connections/actions/listHistory")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "CallbackService")
	ConnectionInformationResponse connectionInformation(
			ConnectionInformationRequest connectionInformationRequest);

}
