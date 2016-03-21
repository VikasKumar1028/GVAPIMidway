package com.gv.midway.processor.deviceInformation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.deviceInformation.verizon.CarrierInformations;
import com.gv.midway.pojo.deviceInformation.verizon.CustomFields;
import com.gv.midway.pojo.deviceInformation.verizon.DeviceIds;
import com.gv.midway.pojo.deviceInformation.verizon.VerizonResponse;

@Component
public class VerizonDeviceInformationPostProcessor implements Processor {

	static int i = 0;

	Logger log = Logger.getLogger(VerizonDeviceInformationPostProcessor.class
			.getName());

	


    Environment newEnv;

	public VerizonDeviceInformationPostProcessor(Environment env) {
		super();
		this.newEnv=env;
		
	}
	public VerizonDeviceInformationPostProcessor() {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonDeviceInformationPostProcessor");

		VerizonResponse verizonResponse = exchange.getIn().getBody(
				VerizonResponse.class);

		int getDevicelenth = verizonResponse.getDevices().length;

		log.info("getDevicelenth::" + getDevicelenth);

		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformation[] deviceInformationArray = new DeviceInformation[getDevicelenth];

		log.info("deviceInformationArray::" + deviceInformationArray.length);

		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		response.setResponseCode(newEnv.getProperty(IConstant.RESPONSES_CODE));
		response.setResponseStatus(newEnv.getProperty(IConstant.RESPONSE_STATUS_SUCCESS));

		responseheader.setApplicationName(newEnv.getProperty(IConstant.APPLICATION_NAME));
		responseheader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setOrganization(newEnv.getProperty(IConstant.ORGANIZATION));
		responseheader.setSourceName(newEnv.getProperty(IConstant.SOURCE_NAME_VERIZON));
		String TransactionId = (String) exchange.getProperty(newEnv.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(TransactionId);

		responseheader.setBsCarrier(newEnv.getProperty(IConstant.BSCARRIER_VERIZON));
		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		for (int i = 0; i < getDevicelenth; i++) {
			DeviceInformation deviceInformation = new DeviceInformation();

			deviceInformation.setAccountName(verizonResponse.getDevices()[i]
					.getAccountName());
			deviceInformation.setBillingCycleEndDate(verizonResponse
					.getDevices()[i].getBillingCycleEndDate());
			deviceInformation.setConnected(verizonResponse.getDevices()[i]
					.getConnected());
			deviceInformation.setCreatedAt(verizonResponse.getDevices()[i]
					.getCreatedAt());
			deviceInformation.setIpAddress(verizonResponse.getDevices()[i]
					.getIpAddress());
			deviceInformation.setGroupNames(verizonResponse.getDevices()[i]
					.getGroupNames());
			deviceInformation
					.setLastActivationBy(verizonResponse.getDevices()[i]
							.getLastActivationBy());
			deviceInformation.setLastActivationDate(verizonResponse
					.getDevices()[i].getLastActivationDate());

			CarrierInformations carrierInformations = new CarrierInformations();
			CarrierInformations[] carrierInformationsArray = new CarrierInformations[verizonResponse
					.getDevices()[i].getCarrierInformations().length];

			for (int j = 0; j < carrierInformationsArray.length; j++) {
				carrierInformations
						.setCarrierName(verizonResponse.getDevices()[i]
								.getCarrierInformations()[j].getCarrierName());
				carrierInformations.setState(verizonResponse.getDevices()[i]
						.getCarrierInformations()[j].getState());
				carrierInformations
						.setServicePlan(verizonResponse.getDevices()[i]
								.getCarrierInformations()[j].getServicePlan());
			}

			deviceInformation.setCarrierInformations(carrierInformations);

			deviceInformation.setExtendedAttributes(verizonResponse
					.getDevices()[i].getExtendedAttributes());

			deviceInformation.setLastConnectionDate(verizonResponse
					.getDevices()[i].getLastConnectionDate());

			DeviceIds deviceIds = new DeviceIds();

			if (verizonResponse.getDevices()[i].getDeviceIds() != null) {
				DeviceIds[] deviceIdsArray = new DeviceIds[verizonResponse
						.getDevices()[i].getDeviceIds().length];

				for (int l = 0; l < deviceIdsArray.length; l++) {
					deviceIds.setId(verizonResponse.getDevices()[i]
							.getDeviceIds()[l].getId());
					deviceIds.setKind(verizonResponse.getDevices()[i]
							.getDeviceIds()[l].getKind());

					deviceIdsArray[l] = deviceIds;

				}
				deviceInformation.setDeviceIds(deviceIdsArray);

			}

			if (verizonResponse.getDevices()[i].getCustomFields() != null) {
				CustomFields[] customFieldsArray = new CustomFields[verizonResponse
						.getDevices()[i].getCustomFields().length];

				for (int m = 0; m < customFieldsArray.length; m++) {
					CustomFields customFields = new CustomFields();
					customFields.setKey(verizonResponse.getDevices()[i]
							.getCustomFields()[m].getKey());
					customFields.setValue(verizonResponse.getDevices()[i]
							.getCustomFields()[m].getValue());

					customFieldsArray[m] = customFields;
				}

				deviceInformation.setCustomFields(customFieldsArray);

			}

			deviceInformationArray[i] = deviceInformation;
		}

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		deviceInformationResponseDataArea.setDevices(deviceInformationArray);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);

	}

}
