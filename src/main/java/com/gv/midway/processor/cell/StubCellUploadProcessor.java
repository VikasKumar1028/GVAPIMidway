package com.gv.midway.processor.cell;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.device.request.DeviceDataArea;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.Cell;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class StubCellUploadProcessor implements Processor {

	Logger log = Logger.getLogger(StubCellUploadProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("StubCellUploadProcessor");
		log.info("StubCellUploadProcessor Called......................");
		// DeviceInformationResponse deviceInformationResponse = new
		// DeviceInformationResponse();

		UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();

		// DeviceInformationResponseDataArea deviceInformationResponseDataArea =
		// new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();

		DeviceDataArea deviceDataArea = new DeviceDataArea();
		SingleDevice singleDevice = new SingleDevice();
		deviceDataArea.setDevices(deviceInformation);
		singleDevice.setDataArea(deviceDataArea);
		Header responseheader = new Header();

		Response response = new Response();

		Cell cell = new Cell();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Record succesfully uploaded in the Midway");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("setRegion");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		updateDeviceResponse.setHeader(responseheader);
		updateDeviceResponse.setResponse(response);
		/*
		 * deviceInformation.setNetSuiteId("NS001"); cell.setEsn("ESN001");
		 * cell.setMdn("MDN001"); cell.setSim("SIM001");
		 * deviceInformation.setCell(cell);
		 * deviceInformation.setBs_id("BSID01");
		 * deviceInformation.setSerial_num("SR001");
		 * deviceInformation.setMac("MAC01");
		 * deviceInformation.setBs_carrier("VERIZON"); Bs_plan bs_plan = new
		 * Bs_plan(); bs_plan.setBill_day("2016-06-06");
		 * bs_plan.setData_amt("20GB"); bs_plan.setData_measure("10K");
		 * 
		 * Features feature1 = new Features(); Features feature2 = new
		 * Features();
		 * 
		 * feature1.setId("F001"); feature1.setName("FNAME01");
		 * 
		 * feature2.setId("F001"); feature2.setName("FNAME01");
		 * 
		 * Features[] featureArray = { feature1, feature2};
		 * bs_plan.setFeatures(featureArray);
		 * 
		 * deviceInformation.setBs_plan(bs_plan);
		 * deviceInformation.setConnected(true);
		 * deviceInformation.setCurrentServicePlan("PLAN_A");
		 * deviceInformation.setCurrentSMSPlan("SMSPLAN_A");
		 * deviceInformation.setDailyDataThreshold(10);
		 * deviceInformation.setMidwayMasterDeviceId("MMD001");
		 * deviceInformation.setAccountName("TestAccount-1");
		 * deviceInformation.setBillingCycleEndDate("2015-10-13T20:00:00Z");
		 * 
		 * deviceInformation.setCreatedAt("2014-09-10T14:32:09Z");
		 * deviceInformation.setIpAddress("0.0.0.0");
		 * deviceInformation.setLastActivationBy("admingca43hpn");
		 * 
		 * deviceInformation.setLastActivationDate("2014-09-18T13:01:02Z");
		 * deviceInformation.setLastConnectionDate("2014-07-15T15:34:31Z");
		 * deviceInformation.setCurrentSMSPlan("null");
		 * deviceInformation.setFutureDataPlan("null");
		 * deviceInformation.setFutureSMSPlan("null");
		 * 
		 * deviceInformation.setDailyDataThreshold(0);
		 * deviceInformation.setDailySMSThreshold(0);
		 * deviceInformation.setMonthlyDataThreshold(0);
		 * deviceInformation.setMonthlySMSThreshold(0);
		 * 
		 * DeviceId deviceIds = new DeviceId(); DeviceId deviceIds1 = new
		 * DeviceId(); DeviceId deviceIds2 = new DeviceId();
		 * deviceIds.setId("2827264285"); deviceIds.setKind("mdn");
		 * deviceIds1.setId("DAD20141201400"); deviceIds1.setKind("meid");
		 * deviceIds2.setId("7725783367"); deviceIds2.setKind("min"); DeviceId[]
		 * deviceIdsArray = { deviceIds, deviceIds1, deviceIds2 };
		 * deviceInformation.setDeviceIds(deviceIdsArray);
		 * 
		 * ExtendedAttributes[] extendedAttributes = new ExtendedAttributes[4];
		 * 
		 * extendedAttributes[0] = new ExtendedAttributes("key1", "SkuNumber");
		 * extendedAttributes[1] = new ExtendedAttributes("key2",
		 * "CostCenterCode"); extendedAttributes[2] = new
		 * ExtendedAttributes("key3", "PreIMEI"); extendedAttributes[3] = new
		 * ExtendedAttributes("key4", "PreSKU");
		 * 
		 * deviceInformation.setExtendedAttributes(extendedAttributes);
		 * 
		 * CustomFields customFields = new CustomFields(); CustomFields
		 * customFields1 = new CustomFields(); CustomFields customFields2 = new
		 * CustomFields(); CustomFields customFields3 = new CustomFields();
		 * CustomFields customFields4 = new CustomFields(); CustomFields
		 * customFields5 = new CustomFields();
		 * 
		 * customFields.setKey("customField1");
		 * customFields.setValue("customField1");
		 * customFields1.setKey("customField2");
		 * customFields1.setValue("customField2");
		 * customFields2.setKey("customField3");
		 * customFields2.setValue("customField3");
		 * 
		 * customFields3.setKey("customField4");
		 * customFields3.setValue("customField4");
		 * 
		 * customFields4.setKey("customField5");
		 * customFields4.setValue("customField5");
		 * 
		 * customFields5.setKey("customField6");
		 * customFields5.setValue("customField6");
		 * 
		 * CustomFields[] arr = { customFields, customFields1, customFields2,
		 * customFields3, customFields4, customFields5 };
		 * 
		 * deviceInformation.setCustomFields(arr);
		 * deviceInformation.setGroupName("GRP01");
		 * deviceInformation.setVoiceDispatchNumber("null");
		 * 
		 * String[] lstFeatures = { null };
		 * deviceInformation.setLstExtFeatures(lstFeatures);
		 * 
		 * String[] lstExtFeatures = { null };
		 * deviceInformation.setLstExtFeatures(lstExtFeatures);
		 * 
		 * String[] lstHistoryOverLastYear = { null };
		 * deviceInformation.setLstHistoryOverLastYear(lstHistoryOverLastYear);
		 * 
		 * deviceInformationResponseDataArea.setDevices(deviceInformation);
		 * 
		 * deviceInformationResponse
		 * .setDataArea(deviceInformationResponseDataArea);
		 */

		exchange.getIn().setBody(updateDeviceResponse);
	}

}
