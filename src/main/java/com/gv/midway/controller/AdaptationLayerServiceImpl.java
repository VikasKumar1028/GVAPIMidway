package com.gv.midway.controller;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gv.midway.constant.CarrierType;
import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestMidwayDataArea;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
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
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestMidwayDataArea;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

@SuppressWarnings("all")
public class AdaptationLayerServiceImpl implements IAdaptaionLayerService {

	private static final Logger logger = LoggerFactory
			.getLogger(AdaptationLayerServiceImpl.class);

	@EndpointInject(uri = "")
	ProducerTemplate producer;

	public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {
		// TODO Auto-generated method stub

		logger.info("device info to update is...." + device.toString());

		UpdateDeviceResponse response = (UpdateDeviceResponse) producer
				.requestBody("direct:updateDeviceDetails", device);

		logger.info("updateDeviceDetails respsone is ........" + response);

		return response;
	}

	public DeviceInformationResponse getDeviceInfoDB(String region,
			String timestamp, String organization, String transactionId,
			String sourceName, String applicationName, String bsCarrier,
			Integer netSuiteId) {
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

		DeviceInformationResponse response = (DeviceInformationResponse) producer
				.requestBody("direct:deviceInformationCarrier",
						deviceInformationRequest);

		return response;
	}

	public BatchDeviceResponse updateDevicesDetailsBulk(BulkDevices devices) {
		// TODO Auto-generated method stub
		logger.info("devices info is...." + devices.toString());

		Object responseActual = producer.requestBody(
				"direct:updateDevicesDetailsBulk", devices);

		logger.info("resposne actual is........" + responseActual.toString());

		BatchDeviceResponse response = (BatchDeviceResponse) responseActual;

		logger.info(" direct:updateDevicesDetails in Batch respsone is ........"
				+ response);

		return response;
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

	public ReactivateDeviceResponse reactivateDevice(
			ReactivateDeviceRequest reActivateDeviceRequest) {
		return (ReactivateDeviceResponse) producer.requestBody(
				"direct:reactivateDevice", reActivateDeviceRequest);
	}

	public SuspendDeviceResponse suspendDevice(
			SuspendDeviceRequest suspendDeviceRequest) {

		return (SuspendDeviceResponse) producer.requestBody(
				"direct:suspendDevice", suspendDeviceRequest);
	}

	public CustomFieldsDeviceResponse customFieldsUpdateRequest(
			CustomFieldsDeviceRequest customeFieldDeviceRequest) {

		return (CustomFieldsDeviceResponse) producer.requestBody(
				"direct:customeFields", customeFieldDeviceRequest);
	}

	public void callbacks(CallBackVerizonRequest callbackRequest) {
		producer.requestBody("direct:callbacks", callbackRequest);
	}

	public RestoreDeviceResponse restoreDevice(
			RestoreDeviceRequest restoreDeviceRequest) {
		return (RestoreDeviceResponse) producer.requestBody(
				"direct:restoreDevice", restoreDeviceRequest);
	}

	/*public SessionBeginEndResponse deviceSessionBeginEndResponse(
			ConnectionInformationRequest connectionInformationRequest) {
		return (SessionBeginEndResponse) producer.requestBody(
				"direct:deviceSessionBeginEndInfo",
				connectionInformationRequest);
	}*/

	public ChangeDeviceServicePlansResponse changeDeviceServicePlans(
			ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest) {

		return (ChangeDeviceServicePlansResponse) producer.requestBody(
				"direct:changeDeviceServicePlans",
				changeDeviceServicePlansRequest);
	}

	@Override
	public JobinitializedResponse transactionFailureDeviceUsageJob(
			JobParameter jobParameter) {

		JobinitializedResponse jobinitializedResponse=CommonUtil.validateJobParameterForDeviceUsage(jobParameter);
		if(jobinitializedResponse!=null){
			
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

		JobinitializedResponse jobinitializedResponse=CommonUtil.validateJobParameterForDeviceConnection(jobParameter);
		if(jobinitializedResponse!=null){
			
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

		JobinitializedResponse jobinitializedResponse=CommonUtil.validateJobParameterForDeviceUsage(jobParameter);
		if(jobinitializedResponse!=null){
			
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

		JobinitializedResponse jobinitializedResponse=CommonUtil.validateJobParameterForDeviceConnection(jobParameter);
		if(jobinitializedResponse!=null){
			
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

		Header header = new Header();
		header.setRegion(region);
		header.setApplicationName(applicationName);
		header.setBsCarrier(bsCarrier);
		header.setSourceName(sourceName);
		header.setTimestamp(timestamp);
		header.setTransactionId(transactionId);
		header.setOrganization(organization);

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

		Header header = new Header();
		header.setRegion(region);
		header.setApplicationName(applicationName);
		header.setBsCarrier(bsCarrier);
		header.setSourceName(sourceName);
		header.setTimestamp(timestamp);
		header.setTransactionId(transactionId);
		header.setOrganization(organization);

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

		Header header = new Header();
		header.setRegion(region);
		header.setApplicationName(applicationName);
		header.setBsCarrier(bsCarrier);
		header.setSourceName(sourceName);
		header.setTimestamp(timestamp);
		header.setTransactionId(transactionId);
		header.setOrganization(organization);

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

		Header header = new Header();
		header.setRegion(region);
		header.setApplicationName(applicationName);
		header.setBsCarrier(bsCarrier);
		header.setSourceName(sourceName);
		header.setTimestamp(timestamp);
		header.setTransactionId(transactionId);
		header.setOrganization(organization);

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

		Header header = new Header();
		header.setRegion(region);
		header.setApplicationName(applicationName);
		header.setBsCarrier(bsCarrier);
		header.setSourceName(sourceName);
		header.setTimestamp(timestamp);
		header.setTransactionId(transactionId);
		header.setOrganization(organization);

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

}