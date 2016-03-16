package com.gv.midway.processor.deviceInformation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deviceInformation.kore.KoreDeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;

public class KoreDeviceInformationPostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceInformationPostProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		KoreDeviceInformationResponse koreDeviceInformationResponse = (KoreDeviceInformationResponse) exchange.getIn().getBody();
		log.info("----exchange_Body- Post Processor----------" + koreDeviceInformationResponse.toString());

		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();
		DeviceInformation[] deviceInformationArray = new DeviceInformation[1];

		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		response.setResponseCode("200");
		// response.setResponseDescription("Device Information is fetched successfully");
		response.setResponseStatus(koreDeviceInformationResponse.getD().getStatus());

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("Region_Value");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("KORE");
		responseheader.setTransactionId("cde2131ksjd");
		// baseResponse.setHeader(header);

		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		// Kore device information details

		//deviceInformation.setCurrentDataPlan(koreDeviceInformationResponse.getD().getCurrentDataPlan());
		deviceInformation.setCurrentSMSPlan(koreDeviceInformationResponse.getD().getCurrentSMSPlan());
		deviceInformation.setDailyDataThreshold(koreDeviceInformationResponse.getD().getDailyDataThreshold());
		deviceInformation.setDailySMSThreshold(koreDeviceInformationResponse.getD().getDailySMSThreshold());
		deviceInformation.setFutureDataPlan(koreDeviceInformationResponse.getD().getFutureDataPlan());
		deviceInformation.setFutureSMSPlan(koreDeviceInformationResponse.getD().getFutureSMSPlan());
		//deviceInformation.setiMSIOrMIN(koreDeviceInformationResponse.getD().getIMSIOrMIN());
		deviceInformation.setLstExtFeatures(koreDeviceInformationResponse.getD().getLstExtFeatures());
		deviceInformation.setLstFeatures(koreDeviceInformationResponse.getD().getLstFeatures());
		deviceInformation.setLstHistoryOverLastYear(koreDeviceInformationResponse.getD().getLstHistoryOverLastYear());
		deviceInformation.setMonthlyDataThreshold(koreDeviceInformationResponse.getD().getMonthlyDataThreshold());
		deviceInformation.setMonthlySMSThreshold(koreDeviceInformationResponse.getD().getMonthlySMSThreshold());
		deviceInformation.setMostRecentAddress(koreDeviceInformationResponse.getD().getMostRecentAddress());
		deviceInformation.setMostRecentLatitude(koreDeviceInformationResponse.getD().getMostRecentLatitude());
		deviceInformation.setMostRecentLocateDate(koreDeviceInformationResponse.getD().getMostRecentLocateDate());
		deviceInformation.setMostRecentLocateId(koreDeviceInformationResponse.getD().getMostRecentLocateId());
		deviceInformation.setMostRecentLongitude(koreDeviceInformationResponse.getD().getMostRecentLongitude());
		//deviceInformation.setMsisdnOrMDN(koreDeviceInformationResponse.getD().getMSISDNOrMDN());
		deviceInformation.setPreviousAddress(koreDeviceInformationResponse.getD().getPreviousAddress());
		deviceInformation.setPreviousLocateDate(koreDeviceInformationResponse.getD().getPreviousLocateDate());
		deviceInformation.setPreviousLocateId(koreDeviceInformationResponse.getD().getPreviousLocateId());
		deviceInformation.setPreviousLatitude(koreDeviceInformationResponse.getD().getPreviousLongitude());
		deviceInformation.setStaticIP(koreDeviceInformationResponse.getD().getStaticIP());
		deviceInformation.setVoiceDispatchNumber(koreDeviceInformationResponse.getD().getVoiceDispatchNumber());
		
		deviceInformation.setCustomField1(koreDeviceInformationResponse.getD().getCustomField1());
		deviceInformation.setCustomField2(koreDeviceInformationResponse.getD().getCustomField2());
		deviceInformation.setCustomField3(koreDeviceInformationResponse.getD().getCustomField3());
		deviceInformation.setCustomField4(koreDeviceInformationResponse.getD().getCustomField4());
		deviceInformation.setCustomField5(koreDeviceInformationResponse.getD().getCustomField5());
		deviceInformation.setCustomField6(koreDeviceInformationResponse.getD().getCustomField6());
		
		
		
		deviceInformationArray[0] = deviceInformation;
		deviceInformationResponseDataArea.setDevices(deviceInformationArray);
		deviceInformationResponse.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
	}
}
