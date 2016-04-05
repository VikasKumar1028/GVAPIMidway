package com.gv.midway.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.device.request.pojo.SingleDevice;
import com.gv.midway.device.request.pojo.DeviceInformation;
import com.gv.midway.device.request.pojo.BulkDevices;
import com.gv.midway.device.response.pojo.InsertDeviceResponse;




@Service
public class DeviceDaoImpl implements IDeviceDao 
{
	/*
	 * @Autowired MongoDb grandVictorDB;
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	public InsertDeviceResponse insertDeviceDetails(SingleDevice device) {

		// Simple way using template
		DeviceInformation deviceInformation=null;
       try{
        //device.setStatus("activate");
        device.getDataArea().getDevice().setState("activate");
        //device.setIpAddress("127.0.0.1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        device.getDataArea().getDevice().setLastUpdated(sdf.format(new Date()));
        
       deviceInformation= device.getDataArea().getDevice();
		mongoTemplate.insert(deviceInformation);
       }
       
       catch(Exception e)
       {
    	   System.out.println("error in insert is..."+e.getMessage());
    	   InsertDeviceResponse insertDeviceResponse= new InsertDeviceResponse();
  	   	   insertDeviceResponse.setMessage("failed to insert record in midway layer ");
    	  
    	   
    	   return insertDeviceResponse;
       }
		//System.out.println(  mongoTemplate.getDb().toString()+"-----"+"----------xcxc-----"+device.toString());
		System.out.println("device data is...."+device.toString());
		
		 InsertDeviceResponse insertDeviceResponse= new InsertDeviceResponse();
		 insertDeviceResponse.setId(deviceInformation.getMidwayMasterDeviceId());
	   	 insertDeviceResponse.setMessage("Success");
   	    
   	 return insertDeviceResponse;

	}

	public Object updateDeviceDetails(String deviceId, SingleDevice device) {
		// TODO Auto-generated method stub
		
		//ResponseMessage responseMessage= new ResponseMessage();
		
		/*try{
			
			Device deviceExist=mongoTemplate.findById(deviceId, Device.class);
			
			
			
			if(deviceExist==null){
				
		    	responseMessage.setMessage("no device Id found for update");
		    	return Response.status(404).entity(responseMessage).build(); 
				
				
			}
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	      //  device.setLastUpdated(sdf.format(new Date()));
	       // device.setId(deviceId);
			mongoTemplate.save(device);
	       }
	       
	       catch(Exception e)
	       {
	    	   System.out.println("error in update is..."+e.getMessage());
	    	 //  responseMessage.setMessage("failed to update record in midway layer");
		       return Response.status(500).entity(responseMessage).build(); 
	    	 
	       }
			//System.out.println(  mongoTemplate.getDb().toString()+"-----"+"----------xcxc-----"+device.toString());
			System.out.println("device data is...."+device.toString());
			//responseMessage.setMessage("device record updated successfully in midway layer");
		    return Response.status(200).entity(responseMessage).build(); */
		
		return null;
			
		
	}

	public Object getDeviceDetails(String deviceId) {
		// TODO Auto-generated method stub
		
		
		
		/*Device device=mongoTemplate.findById(deviceId, Device.class);
		if(device==null){
			
			ResponseMessage responseMessage= new ResponseMessage();
			responseMessage.setMessage("no record found for this device Id");
			return Response.status(404).entity(responseMessage).build(); 
			
		
		}
		
	    return Response.status(200).entity(device).build(); */
		
		
	return null;
		
	}


	public Object getDeviceDetailsBsId(String bsId) {
		// TODO Auto-generated method stub
		/*
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
		
		return Response.status(200).entity(deviceList).build(); */
		
		return null;
	}


	public Object insertDevicesDetailsInBatch(BulkDevices devices) {
		// TODO Auto-generated method stub
	/*	BatchTask batchTask= new BatchTask(mongoTemplate,"insert" , devices);
		
		return batchTask.doBatchJob();*/
		
		return null;
	}

	public Object updateDeviceDetails(SingleDevice device) {
		// TODO Auto-generated method stub
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
