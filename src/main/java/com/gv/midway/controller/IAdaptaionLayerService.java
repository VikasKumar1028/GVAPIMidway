package com.gv.midway.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.device.request.pojo.Device;
import com.gv.midway.device.request.pojo.Devices;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@SuppressWarnings("all")
@Path("/v1")
@Api(value = "/v1", description = "MidWay API Integration")
public interface IAdaptaionLayerService {

	@POST
	@Path("/device/activate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Activate devices")
	String activateDevice();

	@POST
	@Path("/cell")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Insert Device Details")
	Object insertDeviceDetails(Device device);

	@PUT
	@Path("/cell/{id}")
	@Produces("application/json")
	@ApiOperation(value = "Update Device Details")
	Object updateDeviceDetails(@PathParam("id") final String id, Device device);

	@GET
	@Path("/cell/info/{id}")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details")
	Object getDeviceInfo(@PathParam("id") final String id);

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
	Object insertDevicesDetailsInBatch(Devices device);

	@POST
	@Path("/device/information")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "DeviceInformationService")
	DeviceInformationResponse deviceInformationDevice(
			DeviceInformationRequest deviceInformationRequest);
	
	@POST
	@Path("/device/deactivate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "DeactivateDeviceService")
	DeactivateDeviceResponse deactivateDevice(
			DeactivateDeviceRequest deactivateDeviceRequest);


}
