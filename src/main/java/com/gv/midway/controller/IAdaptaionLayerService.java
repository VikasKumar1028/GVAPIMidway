package com.gv.midway.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

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
    		@ApiParam(value = "Region for the request.") 
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true) 
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true) 
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true) 
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true) 
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.") 
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true) 
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "netSuiteId of the device.", required = true) 
            @QueryParam("netSuiteId") final Integer netSuiteId);

    @GET
    @Path("/cell/info/carrier")
    @Produces("application/json")
    @ApiOperation(value = "Get Device Details from Carrier")
    DeviceInformationResponse getDeviceInfoCarrier(
    		@ApiParam(value = "Region for the request.") 
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true) 
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true) 
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true) 
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true) 
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.") 
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true) 
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "netSuiteId of the device.", required = true) 
            @QueryParam("netSuiteId") final Integer netSuiteId,
            @ApiParam(value = "value of the device identifier.", required = true) 
            @QueryParam("deviceId") final String deviceId,
            @ApiParam(value = "kind of device identifier like meid,iccid, mdn,esn,msisdn,imei.", required = true) 
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
    CarrierProvisioningDeviceResponse deactivateDevice(
            DeactivateDeviceRequest deactivateDeviceRequest);

    @POST
    @Path("/device/activate")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "ActivateDeviceService")
    CarrierProvisioningDeviceResponse activateDevice(
            ActivateDeviceRequest activateDeviceRequest);

    @POST
    @Path("/device/reactivate")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "ReactivateDeviceService")
    CarrierProvisioningDeviceResponse reactivateDevice(
            ReactivateDeviceRequest reActivateDeviceRequest);

    @POST
    @Path("/device/suspend")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "SuspendDeviceService")
    CarrierProvisioningDeviceResponse suspendDevice(
            SuspendDeviceRequest suspendDeviceRequest);

    @POST
    @Path("/device/customFields")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "CustomFieldsUpdateService")
    CarrierProvisioningDeviceResponse customFieldsUpdateRequest(
            CustomFieldsDeviceRequest customeFieldDeviceRequest);

    @POST
    @Path("/device/changeServicePlan")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "ChangeDeviceServicePlans")
    CarrierProvisioningDeviceResponse changeDeviceServicePlans(
            ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest);

    @POST
    @Path("/device/callback")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Service for Receiving Callback from Verizon")
    void callbacks(CallBackVerizonRequest activateDeviceRequest);

    @GET
    @Path("/devices/connections/getStatus")
    @Produces("application/json")
    @ApiOperation(value = "Service to check Device in Session for Verizon and AT&T Jasper")
    ConnectionStatusResponse deviceConnectionStatusRequest(
    		@ApiParam(value = "Region for the request.") 
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true) 
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true) 
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true) 
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true) 
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.") 
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true) 
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "value of the device identifier.", required = true) 
            @QueryParam("deviceId") final String deviceId,
            @ApiParam(value = "kind of device identifier like meid,iccid, mdn,esn,msisdn,imei.", required = true) 
            @QueryParam("kind") final String kind,
            @ApiParam(value = "The earliest date and time for which you want to see device in session or not. In yyyy-MM-dd'T'HH:mm:ss format GMT time zone", required = true) 
            @QueryParam("earliest") final String earliest,
            @ApiParam(value = "The latest date and time for which you want device in session or not. In yyyy-MM-dd'T'HH:mm:ss format GMT time zone", required = true) 
            @QueryParam("latest") final String latest);

    @POST
    @Path("/device/restore")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "RestoreDeviceService")
    CarrierProvisioningDeviceResponse restoreDevice(
            RestoreDeviceRequest restoreDeviceRequest);

    @GET
    @Path("/device/usage/session")
    @Produces("application/json")
    @ApiOperation(value = "Retrieve Device data Usage by start and end Date from Carrier for Verizon Devices.")
    UsageInformationResponse retrieveDeviceUsageHistoryCarrier(
            @ApiParam(value = "Region for the request.")
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true)
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true)
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true)
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true)
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.")
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true)
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "value of the device identifier.", required = true)
            @QueryParam("deviceId") final String deviceId,
            @ApiParam(value = "kind of device identifier like meid,iccid, mdn,esn,msisdn,imei.", required = true)
            @QueryParam("kind") final String kind,
            @ApiParam(value = "The earliest date and time for which you want usage data. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("earliest") final String earliest,
            @ApiParam(value = "The latest date and time for which you want usage data. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("latest") final String latest);

    @GET
    @Path("/devices/connections/session/info")
    @Produces("application/json")
    @ApiOperation(value = "Service to check Device Session Begin and End information for Verizon and AT&T Jasper")
    SessionBeginEndResponse deviceSessionBeginEndResponse(
            @ApiParam(value = "Region for the request.")
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true)
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true)
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true)
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true)
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.")
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true)
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "value of the device identifier.", required = true)
            @QueryParam("deviceId") final String deviceId,
            @ApiParam(value = "kind of device identifier like meid,iccid, mdn,esn,msisdn,imei.", required = true)
            @QueryParam("kind") final String kind,
            @ApiParam(value = "The earliest date and time for which you want session begin and end information. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("earliest") final String earliest,
            @ApiParam(value = "The latest date and time for which you want session begin and end information. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("latest") final String latest);

    @GET
    @Path("/device/session/usage")
    @Produces("application/json")
    @ApiOperation(value = "Retrieve Device data Usage by start and end Date from Carrier for Verizon Devices.")
    UsageInformationResponse deviceSessionUsage(
            @ApiParam(value = "Region for the request.")
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true)
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true)
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true)
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true)
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.")
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true)
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "NetSuiteId for identifying the device to query for session usage.", required = true)
            @QueryParam("netSuiteId") final Integer netSuiteId,
            @ApiParam(value = "The earliest date and time for which you want usage data. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("earliest") final String earliest,
            @ApiParam(value = "The latest date and time for which you want usage data. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("latest") final String latest);

    @GET
    @Path("/devices/session/info")
    @Produces("application/json")
    @ApiOperation(value = "Service to check Device Session Begin and End information for Verizon and AT&T Jasper")
    SessionBeginEndResponse deviceSessionInfo(
            @ApiParam(value = "Region for the request.")
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true)
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true)
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true)
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true)
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.")
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true)
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "NetSuiteId for identifying the device to query for session usage.", required = true)
            @QueryParam("netSuiteId") final Integer netSuiteId,
            @ApiParam(value = "The earliest date and time for which you want session begin and end ifnormation. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("earliest") final String earliest,
            @ApiParam(value = "The latest date and time for which you want session begin and end ifnormation. In yyyy-MM-dd'T'HH:mm:ss'Z' format GMT time zone", required = true)
            @QueryParam("latest") final String latest);

    @POST
    @Path("/devices/job/usage/transactionFailure")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Transactional Failure Device Usage Job")
    JobinitializedResponse transactionFailureDeviceUsageJob(
            JobParameter jobParameter);

    @POST
    @Path("/devices/job/connectionHistory/transactionFailure")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Transactional Failure Device Connection History Job")
    JobinitializedResponse transactionFailureConnectionHistoryJob(
            JobParameter jobParameter);

    @POST
    @Path("/devices/job/usage/reRun")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Rerun Device Usage Job")
    JobinitializedResponse reRunDeviceUsageJob(JobParameter jobParameter);

    @POST
    @Path("/devices/job/connectionHistory/reRun")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Rerun Device Connection History Job")
    JobinitializedResponse reRunConnectionHistoryJob(JobParameter jobParameter);

    @GET
    @Path("/device/usage")
    @Produces("application/json")
    @ApiOperation(value = "Get Device Usage by start and end Date from Midway")
    UsageInformationMidwayResponse getDeviceUsageInfoDB(
    		@ApiParam(value = "Region for the request.") 
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true) 
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true) 
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true) 
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true) 
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.") 
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true) 
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "netSuiteId of the device.", required = true) 
            @QueryParam("netSuiteId") final Integer netSuiteId,
            @ApiParam(value = "start date for which you want device data usage form Midway in yyyy-MM-dd.", required = true) 
            @QueryParam("startDate") final String startDate,
            @ApiParam(value = "end date for which you want device data usage form Midwayin yyyy-MM-dd.", required = true) 
            @QueryParam("endDate") final String endDate);

    @GET
    @Path("/device/connectionHistory")
    @Produces("application/json")
    @ApiOperation(value = "Get Device Connection History by start and end Date from Midway for Verizon Devices.")
    ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
    		@ApiParam(value = "Region for the request.") 
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true) 
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true) 
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true) 
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true) 
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.") 
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true) 
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "netSuiteId of the device.", required = true) 
            @QueryParam("netSuiteId") final Integer netSuiteId,
            @ApiParam(value = "start date for which you want connection history records form Midway.In yyyy-MM-dd format.", required = true) 
            @QueryParam("startDate") final String startDate,
            @ApiParam(value = "end date for which you want connection history records form  Midway.In yyyy-MM-dd format.", required = true) 
            @QueryParam("endDate") final String endDate);
    
    @GET
    @Path("/devices/usageByDay")
    @Produces("application/json")
    @ApiOperation(value = "Get Device usage of all the devices by date and carrier from Midway.")
    DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(
    		@ApiParam(value = "Region for the request.") 
            @QueryParam("region") final String region,
            @ApiParam(value = "Date and time of the request.Format will be yyyy-MM-dd'T'HH:mm:ss", required = true) 
            @QueryParam("timestamp") final String timestamp,
            @ApiParam(value = "Organization name of the request.", required = true) 
            @QueryParam("organization") final String organization,
            @ApiParam(value = "Unique id of the entire flow for the request.", required = true) 
            @QueryParam("transactionId") final String transactionId,
            @ApiParam(value = "Name of the source from where the request is triggered.", required = true) 
            @QueryParam("sourceName") final String sourceName,
            @ApiParam(value = "Mode of the request triggered.") 
            @QueryParam("applicationName") final String applicationName,
            @ApiParam(value = "Target System of the request.", required = true) 
            @QueryParam("bsCarrier") final String bsCarrier,
            @ApiParam(value = "date for which you want usage records form Midway.In yyyy-MM-dd format.", required = true) 
            @QueryParam("startDate") final String startDate);

}




