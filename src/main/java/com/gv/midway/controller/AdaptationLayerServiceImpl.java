package com.gv.midway.controller;

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

@SuppressWarnings("all")
public class AdaptationLayerServiceImpl implements IAdaptaionLayerService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdaptationLayerServiceImpl.class);

    @EndpointInject(uri = "")
    ProducerTemplate producer;

    public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {
        // TODO Auto-generated method stub

        LOGGER.info("device info to update is...." + device.toString());

        UpdateDeviceResponse response = (UpdateDeviceResponse) producer
                .requestBody("direct:updateDeviceDetails", device);

        LOGGER.info("updateDeviceDetails respsone is ........" + response);

        return response;
    }

    public DeviceInformationResponse getDeviceInfoDB(String region,
            String timestamp, String organization, String transactionId,
            String sourceName, String applicationName, String bsCarrier,
            Integer netSuiteId) {
        // TODO Auto-generated method stub

        DeviceInformationRequest deviceInformationRequest = new DeviceInformationRequest();

        Header header = createHeader(region, timestamp, organization,
                transactionId, sourceName, applicationName, bsCarrier);

        DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
        dataArea.setNetSuiteId(netSuiteId);

        deviceInformationRequest.setHeader(header);
        deviceInformationRequest.setDataArea(dataArea);

        DeviceInformationResponse response = (DeviceInformationResponse) producer
                .requestBody("direct:getDeviceInformationDB",
                        deviceInformationRequest);

        return response;
    }

    public DeviceInformationResponse getDeviceInfoCarrier(String region,
            String timestamp, String organization, String transactionId,
            String sourceName, String applicationName, String bsCarrier,
            Integer netSuiteId, String deviceId, String kind) {
        // TODO Auto-generated method stub

        DeviceInformationRequest deviceInformationRequest = new DeviceInformationRequest();

        Header header = createHeader(region, timestamp, organization,
                transactionId, sourceName, applicationName, bsCarrier);

        DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        DeviceId deviceIdObj = new DeviceId();
        deviceIdObj.setId(deviceId);
        deviceIdObj.setKind(kind);
        dataArea.setDeviceId(deviceIdObj);

        deviceInformationRequest.setHeader(header);
        deviceInformationRequest.setDataArea(dataArea);

        DeviceInformationResponse response = (DeviceInformationResponse) producer
                .requestBody("direct:deviceInformationCarrier",
                        deviceInformationRequest);

        return response;
    }

    public BatchDeviceResponse updateDevicesDetailsBulk(BulkDevices devices) {
        // TODO Auto-generated method stub
        LOGGER.info("devices info is...." + devices.toString());

        Object responseActual = producer.requestBody(
                "direct:updateDevicesDetailsBulk", devices);

        LOGGER.info("resposne actual is........" + responseActual.toString());

        BatchDeviceResponse response = (BatchDeviceResponse) responseActual;

        LOGGER.info(" direct:updateDevicesDetails in Batch respsone is ........"
                + response);

        return response;
    }

    public CarrierProvisioningDeviceResponse deactivateDevice(
            DeactivateDeviceRequest deactivateDeviceRequest) {

        return (CarrierProvisioningDeviceResponse) producer.requestBody(
                "direct:deactivateDevice", deactivateDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse activateDevice(
            ActivateDeviceRequest activateDeviceRequest) {

        return (CarrierProvisioningDeviceResponse) producer.requestBody(
                "direct:activateDevice", activateDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse reactivateDevice(
            ReactivateDeviceRequest reActivateDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody(
                "direct:reactivateDevice", reActivateDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse suspendDevice(
            SuspendDeviceRequest suspendDeviceRequest) {

        return (CarrierProvisioningDeviceResponse) producer.requestBody(
                "direct:suspendDevice", suspendDeviceRequest);
    }

    public CarrierProvisioningDeviceResponse customFieldsUpdateRequest(
            CustomFieldsDeviceRequest customeFieldDeviceRequest) {

        return (CarrierProvisioningDeviceResponse) producer.requestBody(
                "direct:customeFields", customeFieldDeviceRequest);
    }

    public void callbacks(CallBackVerizonRequest callbackRequest) {
        producer.requestBody("direct:callbacks", callbackRequest);
    }

    public CarrierProvisioningDeviceResponse restoreDevice(
            RestoreDeviceRequest restoreDeviceRequest) {
        return (CarrierProvisioningDeviceResponse) producer.requestBody(
                "direct:restoreDevice", restoreDeviceRequest);
    }

    /*
     * public SessionBeginEndResponse deviceSessionBeginEndResponse(
     * ConnectionInformationRequest connectionInformationRequest) { return
     * (SessionBeginEndResponse) producer.requestBody(
     * "direct:deviceSessionBeginEndInfo", connectionInformationRequest); }
     */

    public CarrierProvisioningDeviceResponse changeDeviceServicePlans(
            ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest) {

        return (CarrierProvisioningDeviceResponse) producer.requestBody(
                "direct:changeDeviceServicePlans",
                changeDeviceServicePlansRequest);
    }

    @Override
    public JobinitializedResponse transactionFailureDeviceUsageJob(
            JobParameter jobParameter) {

        JobinitializedResponse jobinitializedResponse = CommonUtil
                .validateJobParameterForDeviceUsage(jobParameter);
        if (jobinitializedResponse != null) {

            return jobinitializedResponse;
        }
        JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.TRANSACTION_FAILURE);
        jobDetail.setDate(jobParameter.getDate());
        if ("KORE".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.KORE_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.KORE.toString());
        } else if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.VERIZON_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());
        }

        producer.asyncRequestBody("direct:startTransactionFailureJob",
                jobDetail);
        return (JobinitializedResponse) producer.requestBody(
                "direct:jobResponse", jobDetail);

    }

    @Override
    public JobinitializedResponse transactionFailureConnectionHistoryJob(
            JobParameter jobParameter) {

        JobinitializedResponse jobinitializedResponse = CommonUtil
                .validateJobParameterForDeviceConnection(jobParameter);
        if (jobinitializedResponse != null) {

            return jobinitializedResponse;
        }

        JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.TRANSACTION_FAILURE);
        jobDetail.setDate(jobParameter.getDate());
        if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.VERIZON_CONNECTION_HISTORY);
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());
        }

        producer.asyncRequestBody("direct:startTransactionFailureJob",
                jobDetail);
        return (JobinitializedResponse) producer.requestBody(
                "direct:jobResponse", jobDetail);

    }

    @Override
    public JobinitializedResponse reRunDeviceUsageJob(JobParameter jobParameter) {

        JobinitializedResponse jobinitializedResponse = CommonUtil
                .validateJobParameterForDeviceUsage(jobParameter);
        if (jobinitializedResponse != null) {

            return jobinitializedResponse;
        }

        JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.RERUN);
        jobDetail.setDate(jobParameter.getDate());
        if ("KORE".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.KORE_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.KORE.toString());
        } else if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setName(JobName.VERIZON_DEVICE_USAGE);
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());

        }
        producer.asyncRequestBody("direct:startJob", jobDetail);
        return (JobinitializedResponse) producer.requestBody(
                "direct:jobResponse", jobDetail);
    }

    @Override
    public JobinitializedResponse reRunConnectionHistoryJob(
            JobParameter jobParameter) {

        JobinitializedResponse jobinitializedResponse = CommonUtil
                .validateJobParameterForDeviceConnection(jobParameter);
        if (jobinitializedResponse != null) {

            return jobinitializedResponse;
        }

        JobDetail jobDetail = new JobDetail();
        jobDetail.setType(JobType.RERUN);
        jobDetail.setDate(jobParameter.getDate());
        if ("VERIZON".equals(jobParameter.getCarrierName())) {
            jobDetail.setCarrierName(CarrierType.VERIZON.toString());
            jobDetail.setName(JobName.VERIZON_CONNECTION_HISTORY);
        }

        producer.asyncRequestBody("direct:startJob", jobDetail);
        return (JobinitializedResponse) producer.requestBody(
                "direct:jobResponse", jobDetail);
    }

    @Override
    public UsageInformationMidwayResponse getDeviceUsageInfoDB(String region,
            String timestamp, String organization, String transactionId,
            String sourceName, String applicationName, String bsCarrier,
            Integer netSuiteId, String startDate, String endDate) {

        UsageInformationMidwayRequest usageInformationMidwayRequest = new UsageInformationMidwayRequest();

        Header header = createHeader(region, timestamp, organization,
                transactionId, sourceName, applicationName, bsCarrier);

        UsageInformationRequestMidwayDataArea dataArea = new UsageInformationRequestMidwayDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        dataArea.setStartDate(startDate);
        dataArea.setEndDate(endDate);

        usageInformationMidwayRequest.setHeader(header);
        usageInformationMidwayRequest
                .setUsageInformationRequestMidwayDataArea(dataArea);

        UsageInformationMidwayResponse response = (UsageInformationMidwayResponse) producer
                .requestBody("direct:getDeviceUsageInfoDB",
                        usageInformationMidwayRequest);

        return response;
    }

    @Override
    public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
            String region, String timestamp, String organization,
            String transactionId, String sourceName, String applicationName,
            String bsCarrier, Integer netSuiteId, String startDate,
            String endDate) {

        ConnectionInformationMidwayRequest connectionInformationMidwayRequest = new ConnectionInformationMidwayRequest();

        Header header = createHeader(region, timestamp, organization,
                transactionId, sourceName, applicationName, bsCarrier);

        ConnectionInformationRequestMidwayDataArea dataArea = new ConnectionInformationRequestMidwayDataArea();
        dataArea.setNetSuiteId(netSuiteId);
        dataArea.setStartDate(startDate);
        dataArea.setEndDate(endDate);

        connectionInformationMidwayRequest.setHeader(header);
        connectionInformationMidwayRequest.setDataArea(dataArea);

        ConnectionInformationMidwayResponse response = (ConnectionInformationMidwayResponse) producer
                .requestBody("direct:getDeviceConnectionHistoryInfoDB",
                        connectionInformationMidwayRequest);

        return response;
    }

    @Override
    public ConnectionStatusResponse deviceConnectionStatusRequest(
            String region, String timestamp, String organization,
            String transactionId, String sourceName, String applicationName,
            String bsCarrier, String deviceId, String kind, String earliest,
            String latest) {

        ConnectionInformationRequest connectionInformationRequest = new ConnectionInformationRequest();

        Header header = createHeader(region, timestamp, organization,
                transactionId, sourceName, applicationName, bsCarrier);

        ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
        DeviceId deviceIdvalue = new DeviceId();

        deviceIdvalue.setKind(kind);
        deviceIdvalue.setId(deviceId);

        dataArea.setDeviceId(deviceIdvalue);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        connectionInformationRequest.setHeader(header);
        connectionInformationRequest.setDataArea(dataArea);

        ConnectionStatusResponse response = (ConnectionStatusResponse) producer
                .requestBody("direct:deviceConnectionStatus",
                        connectionInformationRequest);

        return response;
    }

    @Override
    public UsageInformationResponse retrieveDeviceUsageHistoryCarrier(
            String region, String timestamp, String organization,
            String transactionId, String sourceName, String applicationName,
            String bsCarrier, String deviceId, String kind, String earliest,
            String latest) {

        UsageInformationRequest UsageInformationRequest = new UsageInformationRequest();

        Header header = createHeader(region, timestamp, organization,
                transactionId, sourceName, applicationName, bsCarrier);

        UsageInformationRequestDataArea dataArea = new UsageInformationRequestDataArea();
        DeviceId deviceIdvalue = new DeviceId();

        deviceIdvalue.setKind(kind);
        deviceIdvalue.setId(deviceId);
        dataArea.setDeviceId(deviceIdvalue);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        UsageInformationRequest.setHeader(header);
        UsageInformationRequest.setDataArea(dataArea);

        UsageInformationResponse response = (UsageInformationResponse) producer
                .requestBody("direct:retrieveDeviceUsageHistoryCarrier",
                        UsageInformationRequest);

        return response;
    }

    @Override
    public SessionBeginEndResponse deviceSessionBeginEndResponse(String region,
            String timestamp, String organization, String transactionId,
            String sourceName, String applicationName, String bsCarrier,
            String deviceId, String kind, String earliest, String latest) {
        // TODO Auto-generated method stub

        ConnectionInformationRequest connectionInformationRequest = new ConnectionInformationRequest();

        Header header = createHeader(region, timestamp, organization,
                transactionId, sourceName, applicationName, bsCarrier);

        ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
        DeviceId deviceIdvalue = new DeviceId();

        deviceIdvalue.setKind(kind);
        deviceIdvalue.setId(deviceId);
        dataArea.setDeviceId(deviceIdvalue);
        dataArea.setEarliest(earliest);
        dataArea.setLatest(latest);

        connectionInformationRequest.setHeader(header);
        connectionInformationRequest.setDataArea(dataArea);

        SessionBeginEndResponse response = (SessionBeginEndResponse) producer
                .requestBody("direct:deviceSessionBeginEndInfo",
                        connectionInformationRequest);

        return response;
    }

    @Override
	public DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(
			String region, String timestamp, String organization,
			String transactionId, String sourceName, String applicationName,
			String bsCarrier, String startDate) {
		// TODO Auto-generated method stub
    	
    	 Header header = createHeader(region, timestamp, organization,
                 transactionId, sourceName, applicationName, bsCarrier);
    	 
    	 DevicesUsageByDayAndCarrierRequest devicesUsageByDayAndCarrierRequest=new DevicesUsageByDayAndCarrierRequest();
    	 
    	 devicesUsageByDayAndCarrierRequest.setHeader(header);
    	 
    	 DevicesUsageByDayAndCarrierRequestDataArea devicesUsageByDayAndCarrierRequestDataArea=new DevicesUsageByDayAndCarrierRequestDataArea();
    	 
    	 devicesUsageByDayAndCarrierRequestDataArea.setDate(startDate);
    	 
    	 devicesUsageByDayAndCarrierRequest.setDateArea(devicesUsageByDayAndCarrierRequestDataArea);
    	 DevicesUsageByDayAndCarrierResponse response = (DevicesUsageByDayAndCarrierResponse) producer
                 .requestBody("direct:getDevicesUsageByDayAndCarrierInfoDB",
                		 devicesUsageByDayAndCarrierRequest);

         return response;
	}
    
    private Header createHeader(String region, String timestamp,
            String organization, String transactionId, String sourceName,
            String applicationName, String bsCarrier) {

        Header header = new Header();
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