package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.verizon.request.ActivateDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.Address;
import com.gv.midway.pojo.verizon.CustomerName;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.pojo.verizon.PrimaryPlaceOfUse;

public class VerizonActivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonActivateDevicePreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Begin:VerizonActivateDevicePreProcessor");

		log.info("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		log.info("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

		ActivateDeviceRequestVerizon businessRequest = new ActivateDeviceRequestVerizon();
		ActivateDeviceRequest proxyRequest = (ActivateDeviceRequest) exchange
				.getIn().getBody();
		businessRequest.setAccountName(proxyRequest.getDataArea()
				.getAccountName());
		businessRequest.setCarrierIpPoolName(proxyRequest.getDataArea()
				.getCarrierIpPoolName());
		businessRequest.setCarrierName(proxyRequest.getDataArea()
				.getCarrierName());
		businessRequest.setCostCenterCode(proxyRequest.getDataArea()
				.getCostCenterCode());
		
		businessRequest.setCustomFields(proxyRequest.getDataArea().getDevices().getCustomFields());
		businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
		businessRequest.setLeadId(proxyRequest.getDataArea().getLeadId());
		businessRequest.setMdnZipCode(proxyRequest.getDataArea()
				.getMdnZipCode());
		
		String macAddress=proxyRequest.getDataArea().getDevices().getMacAddress();
		String serialNumber=proxyRequest.getDataArea().getDevices().getSerialNumber();
		String middleName=proxyRequest.getDataArea().getDevices().getMiddleName();
		String title=proxyRequest.getDataArea().getDevices().getTitle();
		Address address=proxyRequest.getDataArea().getDevices().getAddress();
		if(serialNumber==null&&title==null&&middleName==null&&macAddress==null&&address==null)
		{
			
			businessRequest.setPrimaryPlaceOfUse(null);
		}
		else
		{
		PrimaryPlaceOfUse primaryPlaceOfUse=new PrimaryPlaceOfUse();
		if(address!=null)
		{
		primaryPlaceOfUse.setAddress(address);
		}
		if(serialNumber!=null||title!=null||middleName!=null||macAddress!=null){
			CustomerName customerName=new CustomerName();
			if(middleName!=null)
			{
			customerName.setMiddleName(middleName);
			}
			if(title!=null)
			{
			customerName.setTitle(title);
			}
			if(serialNumber!=null){
				customerName.setFirstName(serialNumber);
			}
			if(macAddress!=null)
			{
			customerName.setLastName(macAddress);
			}
			
			primaryPlaceOfUse.setCustomerName(customerName);
		}
		
		businessRequest.setPrimaryPlaceOfUse(primaryPlaceOfUse);
		}
		
		businessRequest.setPublicIpRestriction(proxyRequest.getDataArea()
				.getPublicIpRestriction());
		
		businessRequest.setServicePlan(proxyRequest.getDataArea().getDevices().getServicePlan());
		businessRequest.setSkuNumber(proxyRequest.getDataArea().getSkuNumber());

		
		
		ActivateDevices proxyDevices = proxyRequest.getDataArea()
				.getDevices();
		
		Devices[] businessDevicesArray = new Devices[1];
		
		DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevices
				.getDeviceIds().length];
		
		Devices businessDevice = new Devices();
		
		for (int i = 0; i < proxyDevices.getDeviceIds().length; i++) {
			ActivateDeviceId proxyDeviceId = proxyDevices.getDeviceIds()[i];

			DeviceId businessDeviceId = new DeviceId();
			businessDeviceId.setId(proxyDeviceId.getId());
			businessDeviceId.setKind(proxyDeviceId.getKind());

			log.info(proxyDeviceId.getId());

			businessDeviceIdArray[i] = businessDeviceId;

		}
		
		businessDevice.setDeviceIds(businessDeviceIdArray);
		
		businessDevicesArray[0]=businessDevice;
				
		businessRequest.setDevices(businessDevicesArray);

		ObjectMapper objectMapper = new ObjectMapper();

		String strRequestBody = objectMapper
				.writeValueAsString(businessRequest);

		exchange.getIn().setBody(strRequestBody);

		Message message = exchange.getIn();
		String sessionToken = "";
		String authorizationToken = "";

		if (exchange.getProperty(IConstant.VZ_SEESION_TOKEN) != null
				&& exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
			sessionToken = exchange.getProperty(IConstant.VZ_SEESION_TOKEN)
					.toString();
			authorizationToken = exchange.getProperty(
					IConstant.VZ_AUTHORIZATION_TOKEN).toString();
		}

		

		message.setHeader("VZ-M2M-Token", sessionToken);
		message.setHeader("Authorization", "Bearer " + authorizationToken);
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/activate");

	}

}
