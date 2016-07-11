package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.deviceInformation.verizon.response.CarrierInformations;
import com.gv.midway.pojo.deviceInformation.verizon.response.ExtendedAttributes;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;

public class StubVerizonDeviceInformationProcessor implements Processor {

	Logger log = Logger.getLogger(StubVerizonDeviceInformationProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("StubVerizonDeviceInformationProcessor");
		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();

		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device Information is fetched successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("setRegion");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		deviceInformation.setNetSuiteId(00001);
		deviceInformation.setMidwayMasterDeviceId("MMD001");
		deviceInformation.setAccountName("TestAccount-1");
		deviceInformation.setBillingCycleEndDate("2015-10-13T20:00:00Z");
		deviceInformation.setConnected(false);
		deviceInformation.setCreatedAt("2014-09-10T14:32:09Z");
		deviceInformation.setIpAddress("0.0.0.0");
		deviceInformation.setLastActivationBy("admingca43hpn");

		deviceInformation.setLastActivationDate("2014-09-18T13:01:02Z");
		deviceInformation.setLastConnectionDate("2014-07-15T15:34:31Z");
		deviceInformation.setCurrentSMSPlan("null");
		deviceInformation.setFutureDataPlan("null");
		deviceInformation.setFutureSMSPlan("null");

		CarrierInformations carrierInformations = new CarrierInformations();
		carrierInformations.setCarrierName("Verizon Wireless");

		carrierInformations.setState("null");

		carrierInformations.setServicePlan("FakeServicePlan-1");

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
		deviceInformation.setDeviceIds(deviceIdsArray);

		ExtendedAttributes[] extendedAttributes = new ExtendedAttributes[4];

		extendedAttributes[0] = new ExtendedAttributes("key1", "SkuNumber");
		extendedAttributes[1] = new ExtendedAttributes("key2", "CostCenterCode");
		extendedAttributes[2] = new ExtendedAttributes("key3", "PreIMEI");
		extendedAttributes[3] = new ExtendedAttributes("key4", "PreSKU");

		deviceInformation.setExtendedAttributes(extendedAttributes);

		String[] lstFeatures = { "null" };

		deviceInformation.setLstFeatures(lstFeatures);

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

		deviceInformation.setCustomFields(arr);

		String[] groupNames = { "BED20141" };

		deviceInformation.setVoiceDispatchNumber("null");

		String[] lstExtFeatures = { null };
		deviceInformation.setLstExtFeatures(lstExtFeatures);

		String[] lstHistoryOverLastYear = { null };
		deviceInformation.setLstHistoryOverLastYear(lstHistoryOverLastYear);

		deviceInformationResponseDataArea.setDevices(deviceInformation);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
	}

}
