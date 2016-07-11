package com.gv.midway.processor.cell;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.device.request.DevicesDataArea;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.BatchDeviceResponseDataArea;
import com.gv.midway.pojo.deviceInformation.response.Bs_plan;
import com.gv.midway.pojo.deviceInformation.response.Cell;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.deviceInformation.response.Features;
import com.gv.midway.pojo.deviceInformation.verizon.response.ExtendedAttributes;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;

public class StubCellBulkUploadProcessor implements Processor {

	Logger log = Logger.getLogger(StubCellBulkUploadProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("StubCellBulkUploadProcessor");
		log.info("StubCellBulkUploadProcessor Called......................");

		DevicesDataArea deviceDataArea = new DevicesDataArea();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation1 = new DeviceInformation();
		DeviceInformation deviceInformation2 = new DeviceInformation();
		DeviceInformation[] DeviceInformationArray = { deviceInformation1,
				deviceInformation2 };
		Header responseheader = new Header();

		Response response = new Response();

		Cell cell = new Cell();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Record succesfully uploaded in Midway");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("setRegion");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		BatchDeviceResponse batchDeviceResponse = new BatchDeviceResponse();
		batchDeviceResponse.setResponse(response);
		batchDeviceResponse.setHeader(responseheader);

		BatchDeviceResponseDataArea dataArea = new BatchDeviceResponseDataArea();
		BatchDeviceId failedDevices = new BatchDeviceId();
		failedDevices.setNetSuiteId("NS001");
		failedDevices.setErrorMessage("Data not Inserted");

		BatchDeviceId successDevices = new BatchDeviceId();
		successDevices.setNetSuiteId("NS002");
		successDevices.setErrorMessage("Data Inserted Successfully");

		BatchDeviceId[] deviceArray = { successDevices, failedDevices };
		dataArea.setFailedDevices(deviceArray);
		dataArea.setFailedCount(1);
		dataArea.setSuccessCount(1);
		batchDeviceResponse.setDataArea(dataArea);

		deviceInformation1.setNetSuiteId(00001);
		cell.setEsn("ESN001");
		cell.setMdn("MDN001");
		cell.setSim("SIM001");
		deviceInformation1.setCell(cell);
		deviceInformation1.setBs_id("BSID01");
		deviceInformation1.setSerial_num("SR001");
		deviceInformation1.setMac("MAC01");
		deviceInformation1.setBs_carrier("VERIZON");
		Bs_plan bs_plan = new Bs_plan();
		bs_plan.setBill_day("2016-06-06");
		bs_plan.setData_amt("20GB");
		bs_plan.setData_measure("10K");

		Features feature1 = new Features();
		Features feature2 = new Features();

		feature1.setId("F001");
		feature1.setName("FNAME01");

		feature2.setId("F001");
		feature2.setName("FNAME01");

		Features[] featureArray = { feature1, feature2 };
		bs_plan.setFeatures(featureArray);

		deviceInformation1.setBs_plan(bs_plan);
		deviceInformation1.setConnected(true);
		deviceInformation1.setCurrentServicePlan("PLAN_A");
		deviceInformation1.setCurrentSMSPlan("SMSPLAN_A");
		deviceInformation1.setDailyDataThreshold(10);
		deviceInformation1.setMidwayMasterDeviceId("MMD001");
		deviceInformation1.setAccountName("TestAccount-1");
		deviceInformation1.setBillingCycleEndDate("2015-10-13T20:00:00Z");

		deviceInformation1.setCreatedAt("2014-09-10T14:32:09Z");
		deviceInformation1.setIpAddress("0.0.0.0");
		deviceInformation1.setLastActivationBy("admingca43hpn");

		deviceInformation1.setLastActivationDate("2014-09-18T13:01:02Z");
		deviceInformation1.setLastConnectionDate("2014-07-15T15:34:31Z");
		deviceInformation1.setCurrentSMSPlan("null");
		deviceInformation1.setFutureDataPlan("null");
		deviceInformation1.setFutureSMSPlan("null");

		deviceInformation1.setDailyDataThreshold(0);
		deviceInformation1.setDailySMSThreshold(0);
		deviceInformation1.setMonthlyDataThreshold(0);
		deviceInformation1.setMonthlySMSThreshold(0);

		DeviceId deviceIds = new DeviceId();
		DeviceId deviceIds1 = new DeviceId();
		DeviceId deviceIds2 = new DeviceId();
		deviceIds.setId("2827264285");
		deviceIds.setKind("mdn");
		deviceIds1.setId("DAD20141201400");
		deviceIds1.setKind("meid");
		deviceIds2.setId("7725783367");
		deviceIds2.setKind("min");
		DeviceId[] deviceIdsArray = { deviceIds, deviceIds1, deviceIds2 };
		deviceInformation1.setDeviceIds(deviceIdsArray);

		ExtendedAttributes[] extendedAttributes = new ExtendedAttributes[4];

		extendedAttributes[0] = new ExtendedAttributes("key1", "SkuNumber");
		extendedAttributes[1] = new ExtendedAttributes("key2", "CostCenterCode");
		extendedAttributes[2] = new ExtendedAttributes("key3", "PreIMEI");
		extendedAttributes[3] = new ExtendedAttributes("key4", "PreSKU");

		deviceInformation1.setExtendedAttributes(extendedAttributes);

		CustomFields customFields = new CustomFields();
		CustomFields customFields1 = new CustomFields();
		CustomFields customFields2 = new CustomFields();
		CustomFields customFields3 = new CustomFields();
		CustomFields customFields4 = new CustomFields();
		CustomFields customFields5 = new CustomFields();

		customFields.setKey("customField1");
		customFields.setValue("customField1");
		customFields1.setKey("customField2");
		customFields1.setValue("customField2");
		customFields2.setKey("customField3");
		customFields2.setValue("customField3");

		customFields3.setKey("customField4");
		customFields3.setValue("customField4");

		customFields4.setKey("customField5");
		customFields4.setValue("customField5");

		customFields5.setKey("customField6");
		customFields5.setValue("customField6");

		CustomFields[] arr = { customFields, customFields1, customFields2,
				customFields3, customFields4, customFields5 };

		deviceInformation1.setCustomFields(arr);
		deviceInformation1.setGroupName("GRP01");
		deviceInformation1.setVoiceDispatchNumber("null");

		String[] lstFeatures = { null };
		deviceInformation1.setLstExtFeatures(lstFeatures);

		String[] lstExtFeatures = { null };
		deviceInformation1.setLstExtFeatures(lstExtFeatures);

		String[] lstHistoryOverLastYear = { null };
		deviceInformation1.setLstHistoryOverLastYear(lstHistoryOverLastYear);

		deviceInformationResponseDataArea.setDevices(deviceInformation1);

		// ........................start

		deviceInformation2.setNetSuiteId(0001);
		cell.setEsn("ESN001");
		cell.setMdn("MDN001");
		cell.setSim("SIM001");
		deviceInformation2.setCell(cell);
		deviceInformation2.setBs_id("BSID01");
		deviceInformation2.setSerial_num("SR001");
		deviceInformation2.setMac("MAC01");
		deviceInformation2.setBs_carrier("VERIZON");
		Bs_plan bs_plan1 = new Bs_plan();
		bs_plan1.setBill_day("2016-06-06");
		bs_plan1.setData_amt("20GB");
		bs_plan1.setData_measure("10K");

		Features feature3 = new Features();
		Features feature4 = new Features();

		feature3.setId("F001");
		feature3.setName("FNAME01");

		feature4.setId("F001");
		feature4.setName("FNAME01");

		Features[] featureArray1 = { feature3, feature4 };
		bs_plan1.setFeatures(featureArray1);

		deviceInformation2.setBs_plan(bs_plan1);
		deviceInformation2.setConnected(true);
		deviceInformation2.setCurrentServicePlan("PLAN_A");
		deviceInformation2.setCurrentSMSPlan("SMSPLAN_A");
		deviceInformation2.setDailyDataThreshold(10);
		deviceInformation2.setMidwayMasterDeviceId("MMD001");
		deviceInformation2.setAccountName("TestAccount-1");
		deviceInformation2.setBillingCycleEndDate("2015-10-13T20:00:00Z");

		deviceInformation2.setCreatedAt("2014-09-10T14:32:09Z");
		deviceInformation2.setIpAddress("0.0.0.0");
		deviceInformation2.setLastActivationBy("admingca43hpn");

		deviceInformation2.setLastActivationDate("2014-09-18T13:01:02Z");
		deviceInformation2.setLastConnectionDate("2014-07-15T15:34:31Z");
		deviceInformation2.setCurrentSMSPlan("null");
		deviceInformation2.setFutureDataPlan("null");
		deviceInformation2.setFutureSMSPlan("null");

		deviceInformation2.setDailyDataThreshold(0);
		deviceInformation2.setDailySMSThreshold(0);
		deviceInformation2.setMonthlyDataThreshold(0);
		deviceInformation2.setMonthlySMSThreshold(0);

		DeviceId deviceIds3 = new DeviceId();
		DeviceId deviceIds4 = new DeviceId();

		deviceIds3.setId("DAD20141201400");
		deviceIds3.setKind("meid");
		deviceIds4.setId("7725783367");
		deviceIds4.setKind("min");
		DeviceId[] deviceIdsArray1 = { deviceIds3, deviceIds4 };
		deviceInformation1.setDeviceIds(deviceIdsArray1);

		ExtendedAttributes[] extendedAttributes1 = new ExtendedAttributes[4];

		extendedAttributes1[0] = new ExtendedAttributes("key1", "SkuNumber");
		extendedAttributes1[1] = new ExtendedAttributes("key2",
				"CostCenterCode");
		extendedAttributes1[2] = new ExtendedAttributes("key3", "PreIMEI");
		extendedAttributes1[3] = new ExtendedAttributes("key4", "PreSKU");

		deviceInformation1.setExtendedAttributes(extendedAttributes1);

		CustomFields customFields11 = new CustomFields();
		CustomFields customFields21 = new CustomFields();

		customFields11.setKey("customField2");
		customFields11.setValue("customField2");

		customFields21.setKey("customField3");
		customFields21.setValue("customField3");

		CustomFields[] arr1 = { customFields11, customFields21 };

		deviceInformation1.setCustomFields(arr1);
		deviceInformation1.setGroupName("GRP01");
		deviceInformation1.setVoiceDispatchNumber("null");

		String[] lstFeatures1 = { null };
		deviceInformation1.setLstExtFeatures(lstFeatures1);

		String[] lstExtFeatures1 = { null };
		deviceInformation1.setLstExtFeatures(lstExtFeatures1);

		String[] lstHistoryOverLastYear1 = { null };
		deviceInformation1.setLstHistoryOverLastYear(lstHistoryOverLastYear1);

		// ........end..
		deviceInformationResponseDataArea.setDevices(deviceInformation2);
		batchDeviceResponse.setDataArea(dataArea);

		deviceDataArea.setDevices(DeviceInformationArray);
		exchange.getIn().setBody(batchDeviceResponse);
		log.info("Exchange Result is..........."
				+ (batchDeviceResponse));
	}

}
