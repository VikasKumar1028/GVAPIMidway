package com.gv.midway.processor.deviceInformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.kore.response.DeviceInformationResponseKore;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreDeviceInformationPostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeviceInformationPostProcessor.class
			.getName());

	Environment newEnv;

	public KoreDeviceInformationPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public KoreDeviceInformationPostProcessor() {
		// TODO Auto-generated constructor stub
	}

	public void process(Exchange exchange) throws Exception {

		log.info("Begin:KoreDeviceInformationPostProcessor");
		DeviceInformationResponseKore koreDeviceInformationResponse = (DeviceInformationResponseKore) exchange
				.getIn().getBody(DeviceInformationResponseKore.class);

		log.info("----exchange_Body- Post Processor-===++++++++++++---------"
				+ koreDeviceInformationResponse.toString());

		DeviceInformation deviceInformation = (DeviceInformation) exchange
				.getProperty(IConstant.MIDWAY_DEVICEINFO_DB);

		if (deviceInformation == null) {

			deviceInformation = new DeviceInformation();
		}
       
        
        if(deviceInformation.getBs_carrier()==null||deviceInformation.getBs_carrier().trim().equals("")){
        	deviceInformation.setBs_carrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
        }
		
		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		//Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_CARRIER);

		/*responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());

		responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT)
				.toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());
		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());

		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());*/
		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		deviceInformation.setCurrentServicePlan(koreDeviceInformationResponse
				.getD().getCurrentDataPlan());
		deviceInformation.setCurrentSMSPlan(koreDeviceInformationResponse
				.getD().getCurrentSMSPlan());
		deviceInformation.setDailyDataThreshold(koreDeviceInformationResponse
				.getD().getDailyDataThreshold());
		deviceInformation.setDailySMSThreshold(koreDeviceInformationResponse
				.getD().getDailySMSThreshold());
		deviceInformation.setFutureDataPlan(koreDeviceInformationResponse
				.getD().getFutureDataPlan());
		deviceInformation.setFutureSMSPlan(koreDeviceInformationResponse.getD()
				.getFutureSMSPlan());

		/*
		 * deviceInformation.setIMSIOrMIN(koreDeviceInformationResponse.getD()
		 * .getIMSIOrMIN());
		 */

		deviceInformation.setLstExtFeatures(koreDeviceInformationResponse
				.getD().getLstExtFeatures());
		deviceInformation.setLstFeatures(koreDeviceInformationResponse.getD()
				.getLstFeatures());
		deviceInformation
				.setLstHistoryOverLastYear(koreDeviceInformationResponse.getD()
						.getLstHistoryOverLastYear());

		deviceInformation.setMonthlyDataThreshold(koreDeviceInformationResponse
				.getD().getMonthlyDataThreshold());
		deviceInformation.setMonthlySMSThreshold(koreDeviceInformationResponse
				.getD().getMonthlySMSThreshold());

		/*
		 * deviceInformation.setMostRecentAddress(koreDeviceInformationResponse
		 * .getD().getMostRecentAddress());
		 * deviceInformation.setMostRecentLatitude(koreDeviceInformationResponse
		 * .getD().getMostRecentLatitude());
		 * deviceInformation.setMostRecentLocateDate
		 * (koreDeviceInformationResponse .getD().getMostRecentLocateDate());
		 * deviceInformation.setMostRecentLocateId(koreDeviceInformationResponse
		 * .getD().getMostRecentLocateId());
		 * deviceInformation.setMostRecentLongitude
		 * (koreDeviceInformationResponse .getD().getMostRecentLongitude());
		 * deviceInformation.setMSISDNOrMDN(koreDeviceInformationResponse.getD()
		 * .getMSISDNOrMDN());
		 * deviceInformation.setPreviousAddress(koreDeviceInformationResponse
		 * .getD().getPreviousAddress());
		 * deviceInformation.setPreviousLocateDate(koreDeviceInformationResponse
		 * .getD().getPreviousLocateDate());
		 * deviceInformation.setPreviousLocateId(koreDeviceInformationResponse
		 * .getD().getPreviousLocateId());
		 * deviceInformation.setPreviousLatitude(koreDeviceInformationResponse
		 * .getD().getPreviousLongitude());
		 * deviceInformation.setStaticIP(koreDeviceInformationResponse.getD()
		 * .getStaticIP());
		 */

		deviceInformation.setVoiceDispatchNumber(koreDeviceInformationResponse
				.getD().getVoiceDispatchNumber());

		deviceInformation.setIpAddress(koreDeviceInformationResponse.getD()
				.getStaticIP());

		CustomFields[] customeFields = new CustomFields[6];

		CustomFields customFields1 = new CustomFields();
		customFields1.setKey("CustomField1");
		customFields1.setValue(koreDeviceInformationResponse.getD()
				.getCustomField1());

		CustomFields customFields2 = new CustomFields();
		customFields2.setKey("CustomField2");
		customFields2.setValue(koreDeviceInformationResponse.getD()
				.getCustomField2());

		CustomFields customFields3 = new CustomFields();
		customFields3.setKey("CustomField3");
		customFields3.setValue(koreDeviceInformationResponse.getD()
				.getCustomField3());

		CustomFields customFields4 = new CustomFields();
		customFields4.setKey("CustomField4");
		customFields4.setValue(koreDeviceInformationResponse.getD()
				.getCustomField4());

		CustomFields customFields5 = new CustomFields();
		customFields5.setKey("CustomField5");
		customFields5.setValue(koreDeviceInformationResponse.getD()
				.getCustomField5());

		CustomFields customFields6 = new CustomFields();
		customFields6.setKey("CustomField6");
		customFields6.setValue(koreDeviceInformationResponse.getD()
				.getCustomField6());

		customeFields[0] = customFields1;
		customeFields[1] = customFields2;
		customeFields[2] = customFields3;
		customeFields[3] = customFields4;
		customeFields[4] = customFields5;
		customeFields[5] = customFields6;

		String msisdnOrmdn = koreDeviceInformationResponse.getD()
				.getMSISDNOrMDN();

		String imsiOrMin = koreDeviceInformationResponse.getD().getIMSIOrMIN();

		List<DeviceId> deviceIdList = new ArrayList<DeviceId>();

		if (msisdnOrmdn != null && !msisdnOrmdn.trim().equals("")) {
			DeviceId deviceId1 = new DeviceId();
			deviceId1.setKind("mdn");
			deviceId1.setId(msisdnOrmdn);
			deviceIdList.add(deviceId1);
		}

		if (imsiOrMin != null && !imsiOrMin.trim().equals("")) {
			DeviceId deviceId2 = new DeviceId();
			deviceId2.setKind("min");
			deviceId2.setId(imsiOrMin);
			deviceIdList.add(deviceId2);
		}

		DeviceId deviceId3=new DeviceId();
		deviceId3.setId(exchange.getProperty(IConstant.KORE_SIM_NUMBER).toString());
		deviceId3.setKind("SIM");
		deviceIdList.add(deviceId3);
		
		DeviceId[] deviceIds = new DeviceId[deviceIdList.size()];
		for (int i = 0; i < deviceIds.length; i++) {
			deviceIds[i] = deviceIdList.get(i);
		}

		/* DeviceId[] deviceIds=(DeviceId[])deviceIdList.toArray(); */

		deviceInformation.setDeviceIds(deviceIds);

		deviceInformation.setCustomFields(customeFields);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		deviceInformation.setLastUpdated(sdf.format(new Date()));

		deviceInformation.setState(koreDeviceInformationResponse.getD()
				.getStatus());

		/*
		 * deviceInformation.setCustomField1(koreDeviceInformationResponse.getD()
		 * .getCustomField1());
		 * deviceInformation.setCustomField2(koreDeviceInformationResponse
		 * .getD() .getCustomField2());
		 * deviceInformation.setCustomField3(koreDeviceInformationResponse
		 * .getD() .getCustomField3());
		 * deviceInformation.setCustomField4(koreDeviceInformationResponse
		 * .getD() .getCustomField4());
		 * deviceInformation.setCustomField5(koreDeviceInformationResponse
		 * .getD() .getCustomField5());
		 * deviceInformation.setCustomField6(koreDeviceInformationResponse
		 * .getD() .getCustomField6());
		 */

		// deviceInformationArray[0] = deviceInformation;
		deviceInformationResponseDataArea.setDevices(deviceInformation);
		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
		log.info("end:KoreDeviceInformationPostProcessor..............."
				+ deviceInformation.toString());
	}

}
