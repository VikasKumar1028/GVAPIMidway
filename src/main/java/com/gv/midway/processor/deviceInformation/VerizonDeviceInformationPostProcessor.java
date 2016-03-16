package com.gv.midway.processor.deviceInformation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gv.midway.pojo.deviceInformation.response.CarrierInformations;
import com.gv.midway.pojo.deviceInformation.response.DeviceIds;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.deviceInformation.response.ExtendedAttributes;
import com.gv.midway.pojo.deviceInformation.verizon.VerizonResponse;

public class VerizonDeviceInformationPostProcessor implements Processor {

	static int i = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {

		// VerizonResponse verizonResponse=new VerizonResponse();

		System.out.println("VerizonPostProcessor1");

		VerizonResponse verizonResponse = exchange.getIn().getBody(
				VerizonResponse.class);
		
		 DeviceInformationResponse deviceInformationResponse = new
				  DeviceInformationResponse();
		
		 
		  DeviceInformation deviceInformation = new DeviceInformation();
		  
		  List <DeviceInformation> deviceInformationArray = new ArrayList<DeviceInformation>();
		  
		 // Iterator	 deviceInformationiterator  deviceInformationArray.iterator();
		  
		  if(deviceInformationArray!=null)
		  {
		  deviceInformation.setAccountName(verizonResponse.getDevices()[0].getAccountName());
		  deviceInformation.setBillingCycleEndDate(verizonResponse.getDevices()[0].getBillingCycleEndDate());
		  deviceInformation.setConnected(verizonResponse.getDevices()[0].getConnected());
		  deviceInformation.setCreatedAt(verizonResponse.getDevices()[0].getCreatedAt());
		  //deviceInformation.setCurrentSMSPlan(verizonResponse.getDevices()[0]);
		 // deviceInformation.setCustomField1(verizonResponse.getDevices()[0]);
		  //deviceInformation.setDailyDataThreshold(verizonResponse.getDevices());
		  deviceInformation.setIpAddress(verizonResponse.getDevices()[0].getIpAddress());
		  deviceInformation.setGroupNames(verizonResponse.getDevices()[0].getGroupNames());
		  deviceInformation.setLastActivationBy(verizonResponse.getDevices()[0].getLastActivationBy());
		  deviceInformation.setLastActivationDate(verizonResponse.getDevices()[0].getLastActivationDate());
		  //deviceInformation.setLastConnectionDate(verizonResponse.getDevices()[0]);
		 // deviceInformation.setLstFeatures(verizonResponse.getDevices()[0].);
		  //deviceInformation.setDailyDataThreshold(verizonResponse.getDevices().);
		  
		  CarrierInformations  carrierInformations =new CarrierInformations();
		  verizonResponse.getDevices()[0].getCarrierInformations()[0].getCarrierName();
		  verizonResponse.getDevices()[0].getCarrierInformations()[0].getServicePlan();
		  verizonResponse.getDevices()[0].getCarrierInformations()[0].getState();
		  
		  		  
		  carrierInformations.setCarrierName( verizonResponse.getDevices()[0].getCarrierInformations()[0].getCarrierName());
		  carrierInformations.setState( verizonResponse.getDevices()[0].getCarrierInformations()[0].getState());
		  carrierInformations.setCurrentDataPlan(verizonResponse.getDevices()[0].getCarrierInformations()[0].getServicePlan());
		  
		  
		  deviceInformation.setCarrierInformations(carrierInformations);
		  
		  ExtendedAttributes extendedAttributes =new ExtendedAttributes();
		  extendedAttributes.setKey1(verizonResponse.getDevices()[0].getExtendedAttributes()[0].getKey());
		  //extendedAttributes.set(verizonResponse.getDevices()[0].getExtendedAttributes()[0].getKey());
		  
		  deviceInformation.setExtendedAttributes(extendedAttributes);
		 
	  
		  deviceInformation.setLastConnectionDate(verizonResponse.getDevices()[0].getLastConnectionDate()	);
		  

		  
		  DeviceIds [] deviceIdsArray=new DeviceIds[1];
		  DeviceIds deviceIds=new DeviceIds();
		  deviceIds.setId(verizonResponse.getDevices()[0].getDeviceIds()[0].getId());
		  deviceIds.setKind(verizonResponse.getDevices()[0].getDeviceIds()[0].getKind());
		  deviceIdsArray[0]=deviceIds;
		  
		  deviceInformation.setDeviceIds(deviceIdsArray);
		  
		  //deviceInformation.setDeviceIds(deviceIds);
		  
		 /* deviceInformationArray[0]=deviceInformation;
		 
		 DeviceInformationResponseDataArea deviceInformationResponseDataArea= new DeviceInformationResponseDataArea();
		 
		 deviceInformationResponseDataArea.setDevices(deviceInformationArray);
		 
		 deviceInformationResponse.setDataArea(deviceInformationResponseDataArea);*/
		
		 
		 
		exchange.getIn().setBody(deviceInformationResponse);
		  }
	}
}
