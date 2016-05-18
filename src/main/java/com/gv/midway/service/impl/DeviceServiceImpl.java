package com.gv.midway.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esotericsoftware.minlog.Log;
import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.InsertDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.router.CamelRoute;
import com.gv.midway.service.IDeviceService;




@Service
public class DeviceServiceImpl implements IDeviceService {

	@Autowired
	private IDeviceDao iDeviceDao;
	
	private Logger log = Logger.getLogger(DeviceServiceImpl.class.getName());

	/*public InsertDeviceResponse insertDeviceDetails(Exchange exchange) {

		SingleDevice device = (SingleDevice) exchange.getIn().getBody();

		return  iDeviceDao.insertDeviceDetails(device);
		
		
		
	}*/

	public UpdateDeviceResponse updateDeviceDetails(Exchange exchange) {
		// TODO Auto-generated method stub
		
		                       
		/*c
		
	    String deviceId = exchange.getIn().getHeader("id", String.class);
       ;*/
		
		SingleDevice device = (SingleDevice) exchange.getIn().getBody();

		return  iDeviceDao.updateDeviceDetails(device);
		
		
		
	}

	public DeviceInformationResponse getDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub

		
	   /* String deviceId = exchange.getIn().getHeader("id", String.class);
       
		System.out.println("id is...."+deviceId);*/
		
		DeviceInformationRequest deviceInformationRequest = (DeviceInformationRequest) exchange.getIn().getBody();
		
		log.info("device information is.........."+deviceInformationRequest.toString());
		
		String netSuiteId=deviceInformationRequest.getDataArea().getNetSuiteId();
		
		
		return iDeviceDao.getDeviceInformationDB(deviceInformationRequest);
	}

	
	/*public Object getDeviceDetailsBsId(Exchange exchange) {
		// TODO Auto-generated method stub
		
		String bs_Id = exchange.getIn().getHeader("bs_id", String.class);
	       
		System.out.println("bs_Id is...."+bs_Id);
		
		
		return iDeviceDao.getDeviceDetailsBsId(bs_Id);	
		
	}
*/

	public void updateDevicesDetailsBulk(Exchange exchange) {
		// TODO Auto-generated method stub
		BulkDevices devices = (BulkDevices) exchange.getIn().getBody();

		DeviceInformation[] deviceInformationArr=devices.getDataArea().getDevices();
		
		List<DeviceInformation> deviceInformationList=Arrays.asList(deviceInformationArr);
		
		List<BatchDeviceId> successList=new ArrayList<BatchDeviceId>();
		
		List<BatchDeviceId> failureList=new ArrayList<BatchDeviceId>();
		
		
		
		exchange.setProperty(IConstant.BULK_SUCCESS_LIST, successList);
		exchange.setProperty(IConstant.BULK_ERROR_LIST,failureList);
		
		log.info("******************Before doing bulk insertion using seda***************");
		
		
		exchange.getIn().setBody(deviceInformationList);
		
		
		
		log.info("******************In the end of bulk insert***************");
		
	}

	public void setDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		 iDeviceDao.setDeviceInformationDB(exchange);
	}
	
	public void updateDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		 iDeviceDao.updateDeviceInformationDB(exchange);
	}

	public void bulkOperationDeviceSyncInDB(Exchange exchange) {
		// TODO Auto-generated method stub
		iDeviceDao.bulkOperationDeviceUpload(exchange);	
	}

	

	/**public String insertDevicesDetailsInBatch(Exchange exchange) {

		Devices devices = (Devices) exchange.getIn().getBody();
		return iDeviceDao.insertDevicesDetailsInBatch(devices);

	}
	
	public String updateDevicesDetailsInBatch(Exchange exchange) {

		Devices devices = (Devices) exchange.getIn().getBody();
		return iDeviceDao.updateDevicesDetailsInBatch(devices);

	}*/

}
