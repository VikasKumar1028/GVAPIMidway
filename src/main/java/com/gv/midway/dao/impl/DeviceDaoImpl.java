package com.gv.midway.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.InsertDeviceResponse;
import com.gv.midway.pojo.device.response.InsertDeviceResponseDataArea;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;




@Service
public class DeviceDaoImpl implements IDeviceDao 
{
	/*
	 * @Autowired MongoDb grandVictorDB;
	 */
	@Autowired
	MongoTemplate mongoTemplate;
	
	private Logger log = Logger.getLogger(DeviceDaoImpl.class.getName());
	
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
    	   
    	  Header header= device.getHeader();
    	  
    	  Response response=new Response();
    	  response.setResponseCode(IResponse.DB_ERROR_CODE);
    	  response.setResponseDescription(IResponse.ERROR_DESCRIPTION_INSERTDEVICE_MIDWAYDB);
    	  response.setResponseStatus(IResponse.ERROR_MESSAGE);
    	  insertDeviceResponse.setResponse(response);
    	  insertDeviceResponse.setHeader(header);
  	   	   //insertDeviceResponse.setMessage("failed to insert record in midway layer ");
    	  
    	   
    	   return insertDeviceResponse;
       }
		//System.out.println(  mongoTemplate.getDb().toString()+"-----"+"----------xcxc-----"+device.toString());
		System.out.println("device data is...."+device.toString());
		
		 InsertDeviceResponse insertDeviceResponse= new InsertDeviceResponse();
		 
		 Header header= device.getHeader();
   	  
		Response response = new Response();
		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_INSERT_MIDWAYDB);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		insertDeviceResponse.setResponse(response);
		insertDeviceResponse.setHeader(header);
		InsertDeviceResponseDataArea insertDeviceResponseDataArea=new InsertDeviceResponseDataArea();
		insertDeviceResponseDataArea.setId(deviceInformation.getMidwayMasterDeviceId());
		
   	    
   	 return insertDeviceResponse;

	}
	
	public UpdateDeviceResponse updateDeviceDetails(SingleDevice device) {
		// TODO Auto-generated method stub
		
		DeviceInformation deviceInfomation=null;
      try{
			
			
    	  Query searchDeviceQuery = new Query(Criteria
  				.where("netSuiteId")
  				.is(device.getDataArea().getDevice().getNetSuiteId())
  				);
  		
    	  deviceInfomation=(DeviceInformation) mongoTemplate.findOne(searchDeviceQuery,DeviceInformation.class);
			
			if(deviceInfomation==null){
				
				 Header header= device.getHeader();
		    	  
		    	  Response response=new Response();
		    	  response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
		    	  response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_UPDATEDEVCIE_MIDWAYDB);
		    	  response.setResponseStatus(IResponse.ERROR_MESSAGE);
				
				  UpdateDeviceResponse updateDeviceResponse=new UpdateDeviceResponse();
				  updateDeviceResponse.setHeader(header);
				  updateDeviceResponse.setResponse(response);
				  
				  return updateDeviceResponse;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		    device.getDataArea().getDevice().setLastUpdated(sdf.format(new Date()));
		    
		    DeviceInformation deviceInformationToUpdate= device.getDataArea().getDevice();
			
			mongoTemplate.save(deviceInformationToUpdate);
	       }
	       
	       catch(Exception e)
	       {
	    	   
	    	Header header = device.getHeader();

	   		Response response = new Response();
	   		response.setResponseCode(IResponse.DB_ERROR_CODE);
	   		response.setResponseDescription(IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB);
	   		response.setResponseStatus(IResponse.ERROR_MESSAGE);

	   		UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
	   		updateDeviceResponse.setHeader(header);
	   		updateDeviceResponse.setResponse(response);
	    	
	   		return updateDeviceResponse;
	    	 
	       }
      
		Header header = device.getHeader();

		Response response = new Response();
		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);

		UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
		updateDeviceResponse.setHeader(header);
		updateDeviceResponse.setResponse(response);

		return updateDeviceResponse;
			
	}

	/*public Object updateDeviceDetails(String deviceId, SingleDevice device) {
		// TODO Auto-generated method stub
		
		//ResponseMessage responseMessage= new ResponseMessage();
		
		try{
			
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
		    return Response.status(200).entity(responseMessage).build(); 
		
		return null;
			
		
	}*/

	public DeviceInformationResponse getDeviceInformationDB(DeviceInformationRequest deviceInformationRequest) {
		// TODO Auto-generated method stub
		
		String netSuiteId=deviceInformationRequest.getDataArea().getNetSuiteId();
		System.out.println("device dao netsuite id is..."+netSuiteId);
		
        DeviceInformationResponse deviceInformationResponse=new DeviceInformationResponse();
		
		deviceInformationResponse.setHeader(deviceInformationRequest.getHeader());
		  Response response=new Response();
		try{
		
		
		
		
		Query searchDeviceQuery = new Query(Criteria
				.where("netSuiteId")
				.is(netSuiteId)
				);
		
		DeviceInformation deviceInformation=(DeviceInformation) mongoTemplate.findOne(searchDeviceQuery,DeviceInformation.class);
		
	  
	    
	    if(deviceInformation==null)
	    		
	   {

	    	response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
	    	response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB);
	    	response.setResponseStatus(IResponse.ERROR_MESSAGE);

	    	
	    }
	    
	    else
	    {

	    	response.setResponseCode(IResponse.SUCCESS_CODE);
	    	response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB);
	    	response.setResponseStatus(IResponse.SUCCESS_MESSAGE);

	    	
	    }
	    
	    deviceInformationResponse.setResponse(response);
	    
        DeviceInformationResponseDataArea deviceInformationResponseDataArea=new DeviceInformationResponseDataArea();
		
	    deviceInformationResponseDataArea.setDevices(deviceInformation);
	    
	    deviceInformationResponse.setDataArea(deviceInformationResponseDataArea);
	    
	    return deviceInformationResponse;
		}
		catch(Exception e){
			

			response.setResponseCode(IResponse.DB_ERROR_CODE);
	    	response.setResponseDescription(IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB);
	    	response.setResponseStatus(IResponse.ERROR_MESSAGE);

	    	
	    	 deviceInformationResponse.setResponse(response);
	 	    
	         DeviceInformationResponseDataArea deviceInformationResponseDataArea=new DeviceInformationResponseDataArea();
	 		
	 	     deviceInformationResponseDataArea.setDevices(null);
	 	    
	 	     deviceInformationResponse.setDataArea(deviceInformationResponseDataArea);
			
			return deviceInformationResponse;
		}
	    
	   
	    
		
		/*Device device=mongoTemplate.findById(deviceId, Device.class);
		if(device==null){
			
			ResponseMessage responseMessage= new ResponseMessage();
			responseMessage.setMessage("no record found for this device Id");
			return Response.status(404).entity(responseMessage).build(); 
			
		
		}
		
	    return Response.status(200).entity(device).build(); */
		
		
	
		
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

	public void setDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		
		String netSuiteId=(String) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID);
		DeviceInformation deviceInformation=null;
		try{
		
		
		Query searchDeviceQuery = new Query(Criteria
				.where("netSuiteId")
				.is(netSuiteId)
				);
		
		deviceInformation=(DeviceInformation) mongoTemplate.findOne(searchDeviceQuery,DeviceInformation.class);
		
		exchange.setProperty(IConstant.MIDWAY_DEVICEINFO_DB,deviceInformation);
	    
		}
		
		catch(Exception e)
		{
			
			log.info("Not able to fetch the data from DB....."+e.getMessage());
		}
	    
	}

	public void updateDeviceInformationDB(Exchange exchange) {
		// TODO Auto-generated method stub
		DeviceInformationResponse deviceInformationResponse=(DeviceInformationResponse)exchange.getIn().getBody();
		
		if(exchange.getProperty(IConstant.MIDWAY_DEVICEINFO_DB)!=null)
		{
			
			DeviceInformation deviceInformation=deviceInformationResponse.getDataArea().getDevices();
			
			log.info("device info carrier is........."+deviceInformation.toString());
			
			mongoTemplate.save(deviceInformation);
		}
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
