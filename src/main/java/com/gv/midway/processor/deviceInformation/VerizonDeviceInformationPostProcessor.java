package com.gv.midway.processor.deviceInformation;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.deviceInformation.verizon.response.CarrierInformations;
import com.gv.midway.pojo.deviceInformation.verizon.response.DeviceInformationResponseVerizon;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;


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

		DeviceInformationResponseVerizon verizonResponse = exchange.getIn().getBody(
				DeviceInformationResponseVerizon.class);
		
		log.info("----exchange_Body- Post Processor-===++++++++++++---------"
				+ verizonResponse.toString());

		int getDevicelenth = verizonResponse.getDevices().length;

		log.info("getDevicelenth::" + getDevicelenth);
		
	   DeviceInformation deviceInformation=(DeviceInformation)exchange.getProperty(IConstant.MIDWAY_DEVICEINFO_DB);
		
		if(deviceInformation==null){
			
			deviceInformation=new DeviceInformation();
		}

		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		 if(deviceInformation.getBs_carrier()==null||deviceInformation.getBs_carrier().trim().equals("")){
	        	deviceInformation.setBs_carrier(exchange.getProperty(IConstant.BSCARRIER)
					.toString());
	     }
		
		//Header responseheader = new Header();

		Response response = new Response();
		
		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_CARRIER);

		/*responseheader.setApplicationName(exchange.getProperty(IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION).toString());
		

		responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT).toString());
		responseheader.setOrganization(exchange.getProperty(IConstant.ORGANIZATION).toString());
		responseheader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME).toString());
		//String TransactionId = (String) exchange.getProperty(newEnv.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER).toString());*/
		
		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);
		
		
		
		//DeviceInformation deviceInformation =null;

		/****
		 * There will be only one element in Array . As we are passing only one device in request.
		 */
		for (int i = 0; i < 1; i++) {
			

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
			
			String[] groupNamesArr=verizonResponse.getDevices()[i].getGroupNames();
			
			String groupName=groupNamesArr[0];
			
			deviceInformation.setGroupName(groupName);
			
			deviceInformation
					.setLastActivationBy(verizonResponse.getDevices()[i]
							.getLastActivationBy());
			deviceInformation.setLastActivationDate(verizonResponse
					.getDevices()[i].getLastActivationDate());

			//CarrierInformations carrierInformations = new CarrierInformations();
			CarrierInformations[] carrierInformationsArray = verizonResponse.getDevices()[i].getCarrierInformations();
			
			CarrierInformations carrierInformations= carrierInformationsArray[0];
			
			deviceInformation.setState(carrierInformations.getState());
			deviceInformation.setCurrentServicePlan(carrierInformations.getServicePlan());

			/*for (int j = 0; j < carrierInformationsArray.length; j++) {
				carrierInformations
						.setCarrierName(verizonResponse.getDevices()[i]
								.getCarrierInformations()[j].getCarrierName());
				carrierInformations.setState(verizonResponse.getDevices()[i]
						.getCarrierInformations()[j].getState());
				carrierInformations
						.setServicePlan(verizonResponse.getDevices()[i]
								.getCarrierInformations()[j].getServicePlan());
			}*/

			//deviceInformation.setCarrierInformations(carrierInformations);

			deviceInformation.setExtendedAttributes(verizonResponse
					.getDevices()[i].getExtendedAttributes());

			deviceInformation.setLastConnectionDate(verizonResponse
					.getDevices()[i].getLastConnectionDate());
			

			if (verizonResponse.getDevices()[i].getDeviceIds() != null) {
				DeviceId[] deviceIdsArray = new DeviceId[verizonResponse
						.getDevices()[i].getDeviceIds().length];

				for (int l = 0; l < deviceIdsArray.length; l++) {
					
					DeviceId deviceIds = new DeviceId();
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

			//deviceInformationArray[i] = deviceInformation;
		}


		/* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		 
		 deviceInformation.setLastUpdated(sdf.format(new Date()));*/
		
		deviceInformation.setLastUpdated(new Date());
		 
		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		deviceInformationResponseDataArea.setDevices(deviceInformation);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);

	}

}
