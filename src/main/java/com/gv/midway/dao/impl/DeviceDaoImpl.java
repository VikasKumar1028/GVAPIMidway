package com.gv.midway.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import scala.collection.mutable.HashSet;

import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.device.request.pojo.Device;
import com.gv.midway.device.request.pojo.Devices;
import com.gv.midway.device.response.pojo.InsertDeviceResponse;
import com.gv.midway.device.response.pojo.ResponseMessage;



@Service
public class DeviceDaoImpl implements IDeviceDao 
{
	/*
	 * @Autowired MongoDb grandVictorDB;
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	public Object insertDeviceDetails(Device device) {

		// Simple way using template
       try{
        device.setStatus("activate");
        //device.setIpAddress("127.0.0.1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        device.setLastUpdated(sdf.format(new Date()));
        
		mongoTemplate.insert(device);
       }
       
       catch(Exception e)
       {
    	   System.out.println("error in insert is..."+e.getMessage());
    	   ResponseMessage responseMessage= new ResponseMessage();
    	   responseMessage.setMessage("failed to insert record in midway layer ");
    	   
    	   return Response.status(500).entity(responseMessage).build(); 
       }
		//System.out.println(  mongoTemplate.getDb().toString()+"-----"+"----------xcxc-----"+device.toString());
		System.out.println("device data is...."+device.toString());
		
	    InsertDeviceResponse insertDeviceResponse= new InsertDeviceResponse();
	    insertDeviceResponse.setId(device.getId());
   	    insertDeviceResponse.setMessage("Success ");
   	    
   	 return Response.status(200).entity(insertDeviceResponse).build(); 

	}

	public Object updateDeviceDetails(String deviceId, Device device) {
		// TODO Auto-generated method stub
		
		ResponseMessage responseMessage= new ResponseMessage();
		
		try{
			
			Device deviceExist=mongoTemplate.findById(deviceId, Device.class);
			
			
			
			if(deviceExist==null){
				
		    	responseMessage.setMessage("no device Id found for update");
		    	return Response.status(404).entity(responseMessage).build(); 
				
				
			}
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	        device.setLastUpdated(sdf.format(new Date()));
	        device.setId(deviceId);
			mongoTemplate.save(device);
	       }
	       
	       catch(Exception e)
	       {
	    	   System.out.println("error in update is..."+e.getMessage());
	    	   responseMessage.setMessage("failed to update record in midway layer");
		       return Response.status(500).entity(responseMessage).build(); 
	    	 
	       }
			//System.out.println(  mongoTemplate.getDb().toString()+"-----"+"----------xcxc-----"+device.toString());
			System.out.println("device data is...."+device.toString());
			responseMessage.setMessage("device record updated successfully in midway layer");
		    return Response.status(200).entity(responseMessage).build(); 
			
		
	}

	public Object getDeviceDetails(String deviceId) {
		// TODO Auto-generated method stub
		
		
		
		Device device=mongoTemplate.findById(deviceId, Device.class);
		if(device==null){
			
			ResponseMessage responseMessage= new ResponseMessage();
			responseMessage.setMessage("no record found for this device Id");
			return Response.status(404).entity(responseMessage).build(); 
			
		
		}
		
	    return Response.status(200).entity(device).build(); 
		
		
	
		
	}


	public Object getDeviceDetailsBsId(String bsId) {
		// TODO Auto-generated method stub
		
		Integer bs_id;
		try{
			
			bs_id=Integer.parseInt(bsId);
		}
		
		catch(NumberFormatException e){
			
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setMessage("bsid format is not valid");
			return Response.status(500).entity(responseMessage).build(); 
		}
		
		Query searchUserQuery = new Query(Criteria
				.where("bsId")
				.is(bs_id)
				);
		
		List<Device> deviceList=mongoTemplate.find(searchUserQuery, Device.class);
		
		if(deviceList==null || deviceList.size()==0)
		{
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setMessage("no record found for this bsId");
			return Response.status(404).entity(responseMessage).build(); 
		}
		
		return Response.status(200).entity(deviceList).build(); 
	}


	public Object insertDevicesDetailsInBatch(Devices devices) {
		// TODO Auto-generated method stub
		List<Device> deviceList=new ArrayList<Device>();
		Collections.addAll(deviceList, devices.getDevices());
		try{
	    mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		mongoTemplate.insertAll(deviceList);
		}
		catch(Exception e){
			
			System.out.println("exeption is......."+e.getMessage());
		}
		return null;
	}

	/*public String insertDevicesDetailsInBatch(Devices devices) {

		//mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		mongoTemplate.insertAll(Arrays.asList(devices.getDevices()));

		return null;
	}
*/
	/*public String updateDevicesDetailsInBatch(String deviceId) {

		// mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);

		for (Device device : devices.getDevices()) {
			Query searchUserQuery = new Query(Criteria
					.where("id")
					.is(device.getId())
					);

			Update update = new Update();
			update.set("bs_id", device.getBsId());
			mongoTemplate.updateFirst(searchUserQuery, update, Device.class);

		}

		return null;
	}

	public String updateDeviceDetails(Device device) {

		Query searchUserQuery = new Query(Criteria
				.where("cell.esn")
				.is(device.getCell().getEsn())
				.andOperator(
						Criteria.where("cell.sim")
								.is(device.getCell().getSim())));

		WriteResult wr = mongoTemplate.updateFirst(searchUserQuery,
				Update.update("bs_id", device.getBsId()), Device.class);

	
		return device.getId();

	}*/

}
