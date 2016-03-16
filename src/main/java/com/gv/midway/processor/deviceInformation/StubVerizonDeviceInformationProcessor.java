package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deviceInformation.response.CarrierInformations;
import com.gv.midway.pojo.deviceInformation.response.CustomFields;
import com.gv.midway.pojo.deviceInformation.response.DeviceIds;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.deviceInformation.response.ExtendedAttributes;

public class StubVerizonDeviceInformationProcessor implements Processor {

	Logger log = Logger.getLogger(StubVerizonDeviceInformationProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("StubVerizonDeviceInformationProcessor");
		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();
		DeviceInformation[] deviceInformationArray = new DeviceInformation[1];

		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		response.setResponseCode("200");
		response.setResponseDescription("Device Information is fetched successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("setRegion");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");
		// baseResponse.setHeader(header);

		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		// deviceInformationResponse.setResponse(response);
		deviceInformation.setNetSuiteId("NS001");
		deviceInformation.setMidwayMasterDeviceId("MMD001");
		deviceInformation.setAccountName("TestAccount-1");
		deviceInformation.setBillingCycleEndDate("2015-10-13T20:00:00Z");
		deviceInformation.setConnected("false");
		deviceInformation.setCreatedAt("2014-09-10T14:32:09Z");
		deviceInformation.setIpAddress("0.0.0.0");
		deviceInformation.setStaticIP("null");
		deviceInformation.setLastActivationBy("admingca43hpn");

		deviceInformation.setLastActivationDate("2014-09-18T13:01:02Z");
		deviceInformation.setLastConnectionDate("2014-07-15T15:34:31Z");
		deviceInformation.setCurrentSMSPlan("null");
		deviceInformation.setFutureDataPlan("null");
		deviceInformation.setFutureSMSPlan("null");

		deviceInformation.setDailyDataThreshold("null");
		deviceInformation.setDailySMSThreshold("null");
		deviceInformation.setMonthlyDataThreshold("null");
		deviceInformation.setMonthlySMSThreshold("null");

		CarrierInformations carrierInformations = new CarrierInformations();
		carrierInformations.setCarrierName("Verizon Wireless");
		carrierInformations.setCurrentDataPlan("null");
		carrierInformations.setState("null");
		carrierInformations.setStatus("Active");
		carrierInformations.setServicePlan("FakeServicePlan-1");
		deviceInformation.setCarrierInformations(carrierInformations);

		/*
		 * DeviceIds deviceIds = new DeviceIds(); deviceIds.setId("7010137536");
		 * deviceIds.setIMSIOrMIN("null"); deviceIds.setKind("mdn");
		 * deviceIds.setMSISDNOrMDN("null");
		 * 
		 * deviceInformation.setDeviceIds(deviceIds);
		 */

		DeviceIds deviceIds = new DeviceIds();
		DeviceIds deviceIds1 = new DeviceIds();
		DeviceIds deviceIds2 = new DeviceIds();
		deviceIds.setId("2827264285");
		deviceIds.setImsiOrMIN("null");
		deviceIds.setKind("mdn");
		deviceIds.setMsisdnOrMDN("null");

		deviceIds1.setId("DAD20141201400");
		deviceIds1.setImsiOrMIN("null");
		deviceIds1.setKind("meid");
		deviceIds1.setMsisdnOrMDN("null");

		deviceIds2.setId("7725783367");
		deviceIds2.setImsiOrMIN("null");
		deviceIds2.setKind("min");
		deviceIds2.setMsisdnOrMDN("null");

		DeviceIds[] deviceIdsArray = { deviceIds, deviceIds1, deviceIds2 };
		deviceInformation.setDeviceIds(deviceIdsArray);

		ExtendedAttributes extendedAttributes = new ExtendedAttributes();
		extendedAttributes.setKey1("SkuNumber");
		extendedAttributes.setKey2("CostCenterCode");
		extendedAttributes.setKey3("PreIMEI");
		extendedAttributes.setKey4("PreSKU");

		deviceInformation.setExtendedAttributes(extendedAttributes);

		/*
		 * LstFeatures lstFeatures = new LstFeatures();
		 * lstFeatures.setFEAT000601("null"); lstFeatures.setFEAT000602("null");
		 * lstFeatures.setFEAT000603("null");
		 */

		String[] lstFeatures = { "null"};

		deviceInformation.setLstFeatures(lstFeatures);

		/*
		 * deviceInformation.setLstFeatures(lstFeatures);
		 */
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
		deviceInformation.setGroupNames(groupNames);
		deviceInformation.setVoiceDispatchNumber("null");
		deviceInformation.setMostRecentLocateId("null");
		deviceInformation.setPreviousLocateId("null");
		deviceInformation.setMostRecentLocateDate("null");
		deviceInformation.setMostRecentLatitude("null");
		deviceInformation.setMostRecentLongitude("null");
		deviceInformation.setMostRecentAddress("null");
		deviceInformation.setPreviousLocateDate("null");
		deviceInformation.setPreviousLatitude("null");
		deviceInformation.setPreviousLongitude("0000");
		
				

		String[] lstExtFeatures = { null };
		deviceInformation.setLstExtFeatures(lstExtFeatures);

		String[] lstHistoryOverLastYear = { null };
		deviceInformation.setLstHistoryOverLastYear(lstHistoryOverLastYear);

		deviceInformation.setPreviousAddress("null");

		deviceInformationArray[0] = deviceInformation;
		deviceInformationResponseDataArea.setDevices(deviceInformationArray);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
	}

}
