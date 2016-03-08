package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

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

public class DeviceInformationStubProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("----------------------"+exchange.getProperty("TransactionId"));
		
		DeviceInformationResponse deviceInformationResponse =new DeviceInformationResponse();
		
		DeviceInformationResponseDataArea deviceInformationResponseDataArea=new DeviceInformationResponseDataArea();
		
		DeviceInformation deviceInformation = new DeviceInformation();
		DeviceInformation[] deviceInformationArray = new DeviceInformation[1];
		
		
		ResponseHeader responseheader =new ResponseHeader();
		
		Response response= new Response();
		response.setResponseCode("200 OK");
		response.setResponseDescription("Device Information is fetched successfully");
		response.setResponseStatus("SUCESS");

						
		responseheader.setApplicationName("applicationName");
		responseheader.setRegion("setRegion");
		responseheader.setTimestamp("setTimestamp");
		responseheader.setOrganization("setOrganization");
		responseheader.setSourceName("setSourceName");
		
		//baseResponse.setHeader(header);
		
		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);
		
		
		//deviceInformationResponse.setResponse(response);
		deviceInformation.setAccountName("sadhana");
		deviceInformation.setBillingCycleEndDate("2015-10-13T20:00:00Z");
		deviceInformation.setConnected("false");
		deviceInformation.setCreatedAt("2014-09-10T14:32:09Z");
		deviceInformation.setIpAddress("0.0.0.0");
		deviceInformation.setLastActivationBy("admingca43hpn");

		deviceInformation.setLastActivationDate("2014-09-18T13:01:02Z");
		deviceInformation.setLastConnectionDate("2014-07-15T15:34:31Z");
		deviceInformation.setCurrentSMSPlan("PLAN000060 USG SMS pay per use");
		deviceInformation.setFutureDataPlan("PLAN000067 USG GPRS pay per use");
		deviceInformation.setFutureSMSPlan("PLAN000060 USG SMS pay per use");

		deviceInformation.setDailyDataThreshold("30");
		deviceInformation.setDailySMSThreshold("2");
		deviceInformation.setMonthlyDataThreshold("30");
		deviceInformation.setMonthlySMSThreshold("30");

		CarrierInformations carrierInformations = new CarrierInformations();
		carrierInformations.setCarrierName("Verizon Wireless");
		carrierInformations
				.setCurrentDataPlan("PLAN000067: USG GPRS pay per use");
		carrierInformations.setState("deactive");
		carrierInformations.setStatus("Active");
		deviceInformation.setCarrierInformations(carrierInformations);

		DeviceIds deviceIds = new DeviceIds();
		deviceIds.setId("BED20141201409");
		deviceIds.setIMSIOrMIN("999888777666555");
		deviceIds.setKind("meid");
		deviceIds.setMSISDNOrMDN("1234567890");

		deviceInformation.setDeviceIds(deviceIds);

		ExtendedAttributes extendedAttributes = new ExtendedAttributes();
		extendedAttributes.setKey1("SkuNumber");
		extendedAttributes.setKey2("CostCenterCode");
		extendedAttributes.setKey3("PreIMEI");
		extendedAttributes.setKey4("PreSKU");

		deviceInformation.setExtendedAttributes(extendedAttributes);

		LstFeatures lstFeatures = new LstFeatures();
		lstFeatures.setFEAT000601("FEAT000601");
		lstFeatures.setFEAT000602("FEAT000602");
		lstFeatures.setFEAT000603("FEAT000603");

		deviceInformation.setLstFeatures(lstFeatures);

		CustomFields customFields = new CustomFields();

		customFields.setKey("CustomField1Key");
		customFields.setValue("CustomField1Value");
		CustomFields[] arr = { customFields };
		deviceInformation.setCustomFields(arr);

		String[] groupNames = { "BED20141" };
		deviceInformation.setGroupNames(groupNames);
		
		deviceInformationArray[0]=deviceInformation;
		deviceInformationResponseDataArea.setDevices(deviceInformationArray);
		
		deviceInformationResponse.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
		
	}

}
