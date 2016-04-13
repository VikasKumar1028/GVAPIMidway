package com.gv.midway.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.callback.NetsuitGenericResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.InsertDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@SuppressWarnings("all")
@Path("/v1")
@Api(value = "/v1", description = "MidWay API Integration")
public interface IAdaptaionLayerService {
/*
	@POST
	@Path("/device/activate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Activate devices")
	String activateDevice();
*/
	@POST
	@Path("/cell")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Insert Device Details")
	InsertDeviceResponse insertDeviceDetails(SingleDevice device);

	@PUT
	@Path("/cell/update")
	@Produces("application/json")
	@ApiOperation(value = "Update Device Details")
	Object updateDeviceDetails(SingleDevice device);

	@GET
	@Path("/cell/info/midway")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details from Midway DB")
	DeviceInformationResponse getDeviceInfoDB(@QueryParam("region") final String region,@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,@QueryParam("transactionId") final String transactionId,@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,@QueryParam("bsCarrier") final String bsCarrier,@QueryParam("netSuiteId") final String netSuiteId);
	
	@GET
	@Path("/cell/info/carrier")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details from Carrier")
	DeviceInformationResponse getDeviceInfoCarrier(@QueryParam("region") final String region,@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,@QueryParam("transactionId") final String transactionId,@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,@QueryParam("bsCarrier") final String bsCarrier,@QueryParam("netSuiteId") final String netSuiteId,
			@QueryParam("deviceId") final String deviceId,@QueryParam("kind") final String kind);

	@GET
	@Path("/cell/info/bs_id/{bs_id}")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details By bsId")
	Object getDeviceInfoBsId(@PathParam("bs_id") final String bs_id);

	@POST
	@Path("/cells")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Insert Device Details in Bulk")
	BatchDeviceResponse insertDevicesDetailsInBatch(BulkDevices device);

	/*@POST
	@Path("/device/information")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "DeviceInformationService")
	DeviceInformationResponse deviceInformationDevice(
			DeviceInformationRequest deviceInformationRequest);*/
	
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
	@Path("/device/callback")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "ActivateDeviceCallbackService")
	NetsuitGenericResponse activateCallback(CallBackVerizonRequest activateDeviceRequest);
}
