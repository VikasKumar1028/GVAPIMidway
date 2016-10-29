package com.gv.midway.controller;

import com.gv.midway.pojo.session.SessionRequestDataArea;
import com.gv.midway.pojo.session.SessionRequest;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gv.midway.constant.CarrierType;
import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestMidwayDataArea;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.usageInformation.request.DevicesUsageByDayAndCarrierRequest;
import com.gv.midway.pojo.usageInformation.request.DevicesUsageByDayAndCarrierRequestDataArea;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestMidwayDataArea;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class AdaptationLayerServiceImpl implements IAdaptaionLayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdaptationLayerServiceImpl.class);

    @SuppressWarnings("DefaultAnnotationParam")
    @EndpointInject(uri = "")
    ProducerTemplate producer;

    public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {
        LOGGER.info("device info to update is...." + device.toString());

        final UpdateDeviceResponse response = (UpdateDeviceResponse) producer.requestBody("direct:updateDeviceDetails", device);

        LOGGER.info("updateDeviceDetails response is ........" + response);

        return response;
    }

    public DeviceInformationResponse getDeviceInfoDB(
            String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier
            , Integer netSuiteId) {
        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
        dataArea.setNetSuiteId(netSuiteId);

        final DeviceInformationRequest deviceInformationRequest = new DeviceInformationRequest();
        deviceInformationRequest.setHeader(header);
        deviceInformationRequest.setDataArea(dataArea);

        return (DeviceInformationResponse) producer.requestBody("direct:getDeviceInformationDB", deviceInformationRequest);
    }

    public DeviceInformationResponse getDeviceInfoCarrier(
            String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier
            , Integer netSuiteId
            , String deviceId
            , String kind) {
        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final DeviceId deviceIdObj = new DeviceId();
        deviceIdObj.setId(deviceId);
        deviceIdObj.setKind(kind);

        final DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        dataArea.setDeviceId(deviceIdObj);

        final DeviceInformationRequest deviceInformationRequest = new DeviceInformationRequest();
        deviceInformationRequest.setHeader(header);
        deviceInformationRequest.setDataArea(dataArea);

        return (DeviceInformationResponse) producer.requestBody("direct:deviceInformationCarrier", deviceInformationRequest);
    }

    public BatchDeviceResponse updateDevicesDetailsBulk(BulkDevices devices) {
        LOGGER.info("devices info is...." + devices.toString());

        final Object responseActual = producer.requestBody("direct:updateDevicesDetailsBulk", devices);

        LOGGER.info("response actual is........" + responseActual.toString());

        final BatchDeviceResponse response = (BatchDeviceResponse) responseActual;

        LOGGER.info(" direct:updateDevicesDetails in Batch response is ........" + response);

        return response;
    }

    public CarrierProvisioningDeviceResponse deactivateDevice(DeactivateDeviceRequest deactivateDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody("direct:deactivateDevice", deactivateDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse activateDevice(ActivateDeviceRequest activateDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody("direct:activateDevice", activateDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse reactivateDevice(ReactivateDeviceRequest reActivateDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody("direct:reactivateDevice", reActivateDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse suspendDevice(SuspendDeviceRequest suspendDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody("direct:suspendDevice", suspendDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse customFieldsUpdateRequest(CustomFieldsDeviceRequest customFieldDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody("direct:customeFields", customFieldDeviceRequest);
    }

    public void callbacks(CallBackVerizonRequest callbackRequest) {
        producer.requestBody("direct:callbacks", callbackRequest);
    }

    public CarrierProvisioningDeviceResponse restoreDevice(RestoreDeviceRequest restoreDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody("direct:restoreDevice", restoreDeviceRequest);
    }

    /*
     * public SessionBeginEndResponse deviceSessionBeginEndResponse(
     * ConnectionInformationRequest connectionInformationRequest) { return
     * (SessionBeginEndResponse) producer.requestBody(
     * "direct:deviceSessionBeginEndInfo", connectionInformationRequest); }
     */

    public CarrierProvisioningDeviceResponse changeDeviceServicePlans(ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody("direct:changeDeviceServicePlans", changeDeviceServicePlansRequest);
    }

    @Override
    public JobinitializedResponse transactionFailureDeviceUsageJob(JobParameter jobParameter) {

        JobinitializedResponse jobinitializedResponse = CommonUtil.validateJobParameterForDeviceUsage(jobParameter);

        if (jobinitializedResponse != null) {
            return jobinitializedResponse;
        }

        JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.TRANSACTION_FAILURE);
        jobDetail.setDate(jobParameter.getDate());
        jobDetail.setPeriod(JobType.TRANSACTION_FAILURE.toString());

        if ("KORE".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.KORE_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.KORE.toString());
        } else if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.VERIZON_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());
        }

        producer.asyncRequestBody("direct:startTransactionFailureJob", jobDetail);
        return (JobinitializedResponse) producer.requestBody("direct:jobResponse", jobDetail);
    }

    @Override
    public JobinitializedResponse transactionFailureConnectionHistoryJob(JobParameter jobParameter) {

        final JobinitializedResponse jobinitializedResponse = CommonUtil.validateJobParameterForDeviceConnection(jobParameter);
        if (jobinitializedResponse != null) {
            return jobinitializedResponse;
        }

        final JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.TRANSACTION_FAILURE);
        jobDetail.setDate(jobParameter.getDate());
        jobDetail.setPeriod(JobType.TRANSACTION_FAILURE.toString());
        
        if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.VERIZON_CONNECTION_HISTORY);
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());
        }

        producer.asyncRequestBody("direct:startTransactionFailureJob", jobDetail);
        return (JobinitializedResponse) producer.requestBody("direct:jobResponse", jobDetail);
    }

    @Override
    public JobinitializedResponse reRunDeviceUsageJob(JobParameter jobParameter) {

        final JobinitializedResponse jobinitializedResponse = CommonUtil.validateJobParameterForDeviceUsage(jobParameter);
        if (jobinitializedResponse != null) {
            return jobinitializedResponse;
        }

        final JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.RERUN);
        jobDetail.setDate(jobParameter.getDate());
        jobDetail.setPeriod(JobType.RERUN.toString());
        
        if ("KORE".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.KORE_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.KORE.toString());
        } else if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.VERIZON_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());
        }

        producer.asyncRequestBody("direct:startJob", jobDetail);
        return (JobinitializedResponse) producer.requestBody("direct:jobResponse", jobDetail);
    }

    @Override
    public JobinitializedResponse reRunConnectionHistoryJob(JobParameter jobParameter) {

        final JobinitializedResponse jobinitializedResponse = CommonUtil.validateJobParameterForDeviceConnection(jobParameter);

        if (jobinitializedResponse != null) {
            return jobinitializedResponse;
        }

        final JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.RERUN);
        jobDetail.setDate(jobParameter.getDate());
        
        jobDetail.setPeriod(JobType.RERUN.toString());
        if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());
            jobDetail.setName(JobName.VERIZON_CONNECTION_HISTORY);
        }

        producer.asyncRequestBody("direct:startJob", jobDetail);
        return (JobinitializedResponse) producer.requestBody("direct:jobResponse", jobDetail);
    }

    @Override
    public UsageInformationMidwayResponse getDeviceUsageInfoDB(
            String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier
            , Integer netSuiteId
            , String startDate
            , String endDate) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final UsageInformationRequestMidwayDataArea dataArea = new UsageInformationRequestMidwayDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        dataArea.setStartDate(startDate);
        dataArea.setEndDate(endDate);

        final UsageInformationMidwayRequest usageInformationMidwayRequest = new UsageInformationMidwayRequest();
        usageInformationMidwayRequest.setHeader(header);
        usageInformationMidwayRequest.setUsageInformationRequestMidwayDataArea(dataArea);

        return (UsageInformationMidwayResponse) producer.requestBody("direct:getDeviceUsageInfoDB", usageInformationMidwayRequest);
    }

    @Override
    public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
            String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier
            , Integer netSuiteId
            , String startDate
            , String endDate) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final ConnectionInformationRequestMidwayDataArea dataArea = new ConnectionInformationRequestMidwayDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        dataArea.setStartDate(startDate);
        dataArea.setEndDate(endDate);

        final ConnectionInformationMidwayRequest connectionInformationMidwayRequest = new ConnectionInformationMidwayRequest();
        connectionInformationMidwayRequest.setHeader(header);
        connectionInformationMidwayRequest.setDataArea(dataArea);

        return (ConnectionInformationMidwayResponse) producer.requestBody("direct:getDeviceConnectionHistoryInfoDB", connectionInformationMidwayRequest);
    }

    @Override
    public ConnectionStatusResponse deviceConnectionStatusRequest(
            String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier
            , String deviceId
            , String kind
            , String earliest
            , String latest) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final DeviceId deviceIdValue = new DeviceId();
        deviceIdValue.setKind(kind);
        deviceIdValue.setId(deviceId);

        final ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
        dataArea.setDeviceId(deviceIdValue);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        final ConnectionInformationRequest connectionInformationRequest = new ConnectionInformationRequest();
        connectionInformationRequest.setHeader(header);
        connectionInformationRequest.setDataArea(dataArea);

        return (ConnectionStatusResponse) producer.requestBody("direct:deviceConnectionStatus", connectionInformationRequest);
    }

    @Override
    public UsageInformationResponse retrieveDeviceUsageHistoryCarrier(
            String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier
            , String deviceId
            , String kind
            , String earliest
            , String latest) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final DeviceId deviceIdValue = new DeviceId();
        deviceIdValue.setKind(kind);
        deviceIdValue.setId(deviceId);

        final UsageInformationRequestDataArea dataArea = new UsageInformationRequestDataArea();
        dataArea.setDeviceId(deviceIdValue);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        final UsageInformationRequest usageInformationRequest = new UsageInformationRequest();
        usageInformationRequest.setHeader(header);
        usageInformationRequest.setDataArea(dataArea);

        return (UsageInformationResponse) producer.requestBody("direct:retrieveDeviceUsageHistoryCarrier", usageInformationRequest);
    }

    @Override
    public SessionBeginEndResponse deviceSessionBeginEndResponse(
            String region,
            String timestamp,
            String organization,
            String transactionId,
            String sourceName,
            String applicationName,
            String bsCarrier,
            String deviceId,
            String kind,
            String earliest,
            String latest) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final DeviceId deviceIdValue = new DeviceId();
        deviceIdValue.setKind(kind);
        deviceIdValue.setId(deviceId);

        final ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
        dataArea.setDeviceId(deviceIdValue);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        final ConnectionInformationRequest connectionInformationRequest = new ConnectionInformationRequest();
        connectionInformationRequest.setHeader(header);
        connectionInformationRequest.setDataArea(dataArea);

        return (SessionBeginEndResponse) producer.requestBody("direct:deviceSessionBeginEndInfo", connectionInformationRequest);
    }

    @Override
    public UsageInformationResponse deviceSessionUsage(
            String region,
            String timestamp,
            String organization,
            String transactionId,
            String sourceName,
            String applicationName,
            String bsCarrier,
            Integer netSuiteId,
            String earliest,
            String latest) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final SessionRequestDataArea dataArea = new SessionRequestDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        final SessionRequest sessionRequest = new SessionRequest();
        sessionRequest.setHeader(header);
        sessionRequest.setDataArea(dataArea);

        return (UsageInformationResponse) producer.requestBody("direct:deviceSessionUsage", sessionRequest);
    }

    @Override
    public SessionBeginEndResponse deviceSessionInfo(
            String region,
            String timestamp,
            String organization,
            String transactionId,
            String sourceName,
            String applicationName,
            String bsCarrier,
            Integer netSuiteId,
            String earliest,
            String latest) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final SessionRequestDataArea dataArea = new SessionRequestDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        final SessionRequest sessionRequest = new SessionRequest();
        sessionRequest.setHeader(header);
        sessionRequest.setDataArea(dataArea);

        return (SessionBeginEndResponse) producer.requestBody("direct:deviceSessionInfo", sessionRequest);
    }

    @Override
    public DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(
            String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier
            , String startDate) {

        final Header header = createHeader(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier);

        final DevicesUsageByDayAndCarrierRequestDataArea devicesUsageByDayAndCarrierRequestDataArea = new DevicesUsageByDayAndCarrierRequestDataArea();
        devicesUsageByDayAndCarrierRequestDataArea.setDate(startDate);

        final DevicesUsageByDayAndCarrierRequest devicesUsageByDayAndCarrierRequest = new DevicesUsageByDayAndCarrierRequest();
        devicesUsageByDayAndCarrierRequest.setHeader(header);
        devicesUsageByDayAndCarrierRequest.setDateArea(devicesUsageByDayAndCarrierRequestDataArea);

        return (DevicesUsageByDayAndCarrierResponse) producer.requestBody("direct:getDevicesUsageByDayAndCarrierInfoDB", devicesUsageByDayAndCarrierRequest);
    }

    private Header createHeader(String region
            , String timestamp
            , String organization
            , String transactionId
            , String sourceName
            , String applicationName
            , String bsCarrier) {

        final Header header = new Header();
        header.setRegion(region);
        header.setApplicationName(applicationName);
        header.setBsCarrier(bsCarrier);
        header.setSourceName(sourceName);
        header.setTimestamp(timestamp);
        header.setTransactionId(transactionId);
        header.setOrganization(organization);

        return header;
    }
}