package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.CarrierInformations;
import com.gv.midway.pojo.CustomFields;
import com.gv.midway.pojo.DeviceIds;
import com.gv.midway.pojo.DeviceInformation;
import com.gv.midway.pojo.DeviceInformationResponse;
import com.gv.midway.pojo.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.ExtendedAttributes;
import com.gv.midway.pojo.LstFeatures;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;

public class StubKoreDeviceInformationProcessor implements Processor {

	Logger log = Logger.getLogger(StubKoreDeviceInformationProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:StubKoreDeviceInformationProcessor");
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
		responseheader.setSourceName("KORE");
		responseheader.setTransactionId("cde2131ksjd");
		// baseResponse.setHeader(header);

		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		// deviceInformationResponse.setResponse(response);
		deviceInformation.setAccountName("");
		deviceInformation.setBillingCycleEndDate("null");
		deviceInformation.setConnected("null");
		deviceInformation.setCreatedAt("null");
		deviceInformation.setIpAddress("null");
		deviceInformation.setStaticIP("StaticIP");
		deviceInformation.setLastActivationBy("null");

		deviceInformation.setLastActivationDate("null");
		deviceInformation.setLastConnectionDate("null");
		deviceInformation.setCurrentSMSPlan("PLAN000060 USG SMS pay per use");
		deviceInformation.setFutureDataPlan("PLAN000067 USG GPRS pay per use");
		deviceInformation.setFutureSMSPlan("PLAN000060 USG SMS pay per use");
		deviceInformation.setVoiceDispatchNumber("");

		deviceInformation.setDailyDataThreshold("30");
		deviceInformation.setDailySMSThreshold("2");
		deviceInformation.setMonthlyDataThreshold("30");
		deviceInformation.setMonthlySMSThreshold("30");

		CarrierInformations carrierInformations = new CarrierInformations();
		carrierInformations.setStatus("Active");
		carrierInformations
				.setCurrentDataPlan("PLAN000067: USG GPRS pay per use");
		carrierInformations.setState("null");
		carrierInformations.setCarrierName("null");
		deviceInformation.setCarrierInformations(carrierInformations);

		DeviceIds deviceIds = new DeviceIds();
		deviceIds.setId("11111222223333344444");
		deviceIds.setIMSIOrMIN("999888777666555");
		deviceIds.setKind("null");
		deviceIds.setMSISDNOrMDN("1234567890");

		deviceInformation.setDeviceIds(deviceIds);

		ExtendedAttributes extendedAttributes = new ExtendedAttributes();
		extendedAttributes.setKey1("null");
		extendedAttributes.setKey2("null");
		extendedAttributes.setKey3("null");
		extendedAttributes.setKey4("null");

		deviceInformation.setExtendedAttributes(extendedAttributes);

		LstFeatures lstFeatures = new LstFeatures();
		lstFeatures.setFEAT000601("FEAT000601");
		lstFeatures.setFEAT000602("FEAT000602");
		lstFeatures.setFEAT000603("FEAT000603");

		deviceInformation.setLstFeatures(lstFeatures);

		CustomFields customFields = new CustomFields();
		CustomFields customFields1 = new CustomFields();
		customFields.setKey("CustomField1Key1");
		customFields.setValue("CustomField1Value1");
		customFields1.setKey("CustomField1Key2");
		customFields1.setValue("CustomField1Value2");
		CustomFields[] arr = { customFields,customFields1 };
		deviceInformation.setCustomFields(arr);

		String[] groupNames = { "null" };
		deviceInformation.setGroupNames(groupNames);

		deviceInformation.setVoiceDispatchNumber("voiceDispatchNumber");
		deviceInformation.setMostRecentLocateId(80);
		deviceInformation.setPreviousLocateId(23);
		deviceInformation.setMostRecentLocateDate("296913780000-0600");
		deviceInformation.setMostRecentLatitude(115.738748);
		deviceInformation.setMostRecentLongitude(87878.8989);
		deviceInformation
				.setMostRecentAddress("111 Any Street, Any Town, Any County, Any State, Any Country 12345");
		deviceInformation.setPreviousLocateDate("");
		deviceInformation.setPreviousLatitude(35.598510833333336);
		deviceInformation.setPreviousLongitude(108.25793444444444);

		String[] lstExtFeatures = {"lstExtFeatures"};
		deviceInformation.setLstExtFeatures(lstExtFeatures);
		

		String[] lstHistoryOverLastYear = {"lstHistoryOverLastYear"};
		deviceInformation.setLstHistoryOverLastYear(lstHistoryOverLastYear);

		deviceInformationArray[0] = deviceInformation;
		deviceInformationResponseDataArea.setDevices(deviceInformationArray);
		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);

	}
}
