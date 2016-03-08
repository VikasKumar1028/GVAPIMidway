package com.gv.midway.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.gv.midway.pojo.DeviceInformationRequest;
import com.gv.midway.pojo.DeviceInformationResponse;
import com.gv.midway.pojo.User1;
import com.gv.midway.pojo.request.Device;
import com.gv.midway.pojo.request.Devices;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@SuppressWarnings("all")
@Path("/V1")
@Api(value = "/V1", description = "User REST for Integration Testing")
public interface IAdaptaionLayerService {

    
    @POST
    @Path("/activateDevice")   
    @Produces("application/json")  
    @Consumes("application/json")
    @ApiOperation(value = "Get By Id")
    String activateDevice(User1 user);


    @POST
    @Path("/cell")   
    @Produces("application/json")  
    @Consumes("application/json")
    @ApiOperation(value = "Insert Device Details")
    String insertDeviceDetails(Device device);
   
    @PUT
    @Path("/cell")   
    @Produces("application/json")  
    @Consumes("application/json")
    @ApiOperation(value = "Insert Device Details")
    String updateDeviceDetails(Device device);
    
    
    @POST
    @Path("/cells")   
    @Produces("application/json")  
    @Consumes("application/json")
    @ApiOperation(value = "Get By Id")
    String insertDevicesDetailsInBatch(Devices device);
  
    @PUT
    @Path("/cells")   
    @Produces("application/json")  
    @Consumes("application/json")
    @ApiOperation(value = "Get By Id")
    String updateDevicesDetailsInBatch(Devices device);
    
    @POST
    @Path("/deviceInformationDevice")   
    @Produces("application/json")  
    @Consumes("application/json")
    @ApiOperation(value = "Get By Id")
    DeviceInformationResponse deviceInformationDevice(DeviceInformationRequest deviceInformationRequest);

    
}

