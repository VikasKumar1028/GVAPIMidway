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

public class StubVerizonDeviceInformationProcessor implements Processor {
	
	Logger log = Logger.getLogger(StubVerizonDeviceInformationProcessor.class.getName());
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

		// baseResponse.setHeader(header);

		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		// deviceInformationResponse.setResponse(response);
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
		carrierInformations
				.setCurrentDataPlan("null");
		carrierInformations.setState("deactive");
		carrierInformations.setStatus("Active");
		deviceInformation.setCarrierInformations(carrierInformations);

		DeviceIds deviceIds = new DeviceIds();
		deviceIds.setId("7010137536");
		deviceIds.setIMSIOrMIN("null");
		deviceIds.setKind("mdn");
		deviceIds.setMSISDNOrMDN("null");

		deviceInformation.setDeviceIds(deviceIds);

		ExtendedAttributes extendedAttributes = new ExtendedAttributes();
		extendedAttributes.setKey1("SkuNumber");
		extendedAttributes.setKey2("CostCenterCode");
		extendedAttributes.setKey3("PreIMEI");
		extendedAttributes.setKey4("PreSKU");

		deviceInformation.setExtendedAttributes(extendedAttributes);

		LstFeatures lstFeatures = new LstFeatures();
		lstFeatures.setFEAT000601("null");
		lstFeatures.setFEAT000602("null");
		lstFeatures.setFEAT000603("null");

		deviceInformation.setLstFeatures(lstFeatures);

		CustomFields customFields = new CustomFields();

		customFields.setKey("CustomField1Key");
		customFields.setValue("CustomField1Value");
		CustomFields[] arr = { customFields };
		deviceInformation.setCustomFields(arr);

		String[] groupNames = { "BED20141" };
		deviceInformation.setGroupNames(groupNames);
		deviceInformation.setVoiceDispatchNumber("null");
		deviceInformation.setMostRecentLocateId(0);
		deviceInformation.setPreviousLocateId(0);
		deviceInformation.setMostRecentLocateDate("");
		deviceInformation.setMostRecentLatitude(0.0);
		deviceInformation.setMostRecentLongitude(0.0);
		deviceInformation.setMostRecentAddress("null");
		deviceInformation.setPreviousLocateDate("");
		deviceInformation.setPreviousLatitude(0.0);
		deviceInformation.setPreviousLongitude(0.0);

		String[] lstExtFeatures = {null};
		deviceInformation.setLstExtFeatures(lstExtFeatures);
		
		String[] lstHistoryOverLastYear = {null};
		deviceInformation.setLstHistoryOverLastYear(lstHistoryOverLastYear);
		
		deviceInformationArray[0] = deviceInformation;
		deviceInformationResponseDataArea.setDevices(deviceInformationArray);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
	}

}
