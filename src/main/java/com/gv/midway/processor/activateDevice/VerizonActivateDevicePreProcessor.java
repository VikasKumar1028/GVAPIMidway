package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.verizon.request.ActivateDeviceRequestVerizon;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class VerizonActivateDevicePreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonActivateDevicePreProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonActivateDevicePreProcessor");

		System.out.println("Session Parameters  VZSessionToken"
				+ exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
		System.out.println("Session Parameters  VZAuthorization"
				+ exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN));

			ActivateDeviceRequestVerizon businessRequest=new  ActivateDeviceRequestVerizon();	
			ActivateDeviceRequest proxyRequest = (ActivateDeviceRequest) exchange.getIn()
				.getBody();
			businessRequest.setAccountName(proxyRequest.getDataArea().getAccountName());
			businessRequest.setCarrierIpPoolName(proxyRequest.getDataArea().getCarrierIpPoolName());
			businessRequest.setCarrierName(proxyRequest.getDataArea().getCarrierName());
			businessRequest.setCostCenterCode(proxyRequest.getDataArea().getCostCenterCode());
			businessRequest.setCustomFields(proxyRequest.getDataArea().getCustomFields());
			businessRequest.setGroupName(proxyRequest.getDataArea().getGroupName());
			businessRequest.setLeadId(proxyRequest.getDataArea().getLeadId());
			businessRequest.setMdnZipCode(proxyRequest.getDataArea().getMdnZipCode());
			businessRequest.setPrimaryPlaceOfUse(proxyRequest.getDataArea().getPrimaryPlaceOfUse());
			businessRequest.setPublicIpRestriction(proxyRequest.getDataArea().getPublicIpRestriction());
			businessRequest.setServicePlan(proxyRequest.getDataArea().getServicePlan());
			businessRequest.setSkuNumber(proxyRequest.getDataArea().getSkuNumber());
		
	//copy of the device to businessRequest	
	//As the payload is broken into indivisual devices so only one Device
			
	//Need to send the complete payload	with id and Kind as device parameters	
	ActivateDevices[] proxyDevicesArray=	proxyRequest.getDataArea().getDevices();
	Devices[] businessDevicesArray= new Devices[proxyDevicesArray.length];
	
	for(int j=0; j<proxyDevicesArray.length;j++)
	{
	
	DeviceId[] businessDeviceIdArray = new DeviceId[proxyDevicesArray[j].getDeviceIds().length];
	ActivateDevices proxyDevices= proxyDevicesArray[j];
	
	for ( int i=0; i<proxyDevices.getDeviceIds().length;i++)
	{
		ActivateDeviceId proxyDeviceId=proxyDevices.getDeviceIds()[i];
		
		DeviceId businessDeviceId=new DeviceId();
		businessDeviceId.setId(proxyDeviceId.getId());
		businessDeviceId.setKind(proxyDeviceId.getKind());
		
		System.out.println(proxyDeviceId.getId() );
		
		businessDeviceIdArray[i]=businessDeviceId;
		
		System.out.println(""+i);
	}
	System.out.println(""+j); 
	businessDevicesArray[j].setDeviceIds(businessDeviceIdArray);	
	}
	businessRequest.setDevices(businessDevicesArray);
			
		
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		obj.put("req", businessRequest);

		log.info("-----------------------------------------" +obj.get("req") );
		
		exchange.getIn().setBody(obj.get("req"));
		
		
		
		Message message = exchange.getIn();

		message.setHeader("VZ-M2M-Token",
				"1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		message.setHeader("Authorization",
				"Bearer 89ba225e1438e95bd05c3cc288d3591");
		message.setHeader(Exchange.CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
		message.setHeader(Exchange.HTTP_METHOD, "POST");
		message.setHeader(Exchange.HTTP_PATH, "/devices/actions/activate");

	}

}
