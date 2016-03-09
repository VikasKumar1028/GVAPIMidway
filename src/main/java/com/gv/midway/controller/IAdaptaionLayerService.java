package com.gv.midway.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.gv.midway.pojo.DeviceInformationRequest;
import com.gv.midway.pojo.DeviceInformationResponse;
import com.gv.midway.pojo.request.Device;
import com.gv.midway.pojo.request.Devices;
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
	@ApiOperation(value = "Insert device details")
	String insertDeviceDetails(Device device);

	@PUT
	@Path("/cell")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Update device details")
	String updateDeviceDetails(Device device);

	@POST
	@Path("/cells")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Insert devices")
	String insertDevicesDetailsInBatch(Devices device);

	@PUT
	@Path("/cells")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Update devices")
	String updateDevicesDetailsInBatch(Devices device);

	@POST
	@Path("/device/information")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Get device information details")
	DeviceInformationResponse deviceInformationDevice(
			DeviceInformationRequest deviceInformationRequest);

}
