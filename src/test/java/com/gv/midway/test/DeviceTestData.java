package com.gv.midway.test;

import org.apache.camel.Exchange;




import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;
//import com.gv.midway.pojo.DeviceId;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;

public class DeviceTestData extends Header {
   /* protected ActivateDeviceRequest expectHeader;
    protected DeviceInformationRequest expectDeviceInfo;
    protected DeactivateDeviceRequest expectDeactiveDevice;
    Exchange exchange;
    //protected Header deliveredDrinks;
    Header header =new Header();    	
    public void setExpectedHeader(ActivateDeviceRequest activateDeviceRequest) {
        this.expectHeader = activateDeviceRequest;
    }
 
    public void verifyHeaderData() {
    	ActivateDeviceRequest req= new ActivateDeviceRequest(); 
    	  
    	   header.setApplicationName("WEB");
           header.setTimestamp("2016-03-08T21:49:45");
   	       header.setOrganization("Grant Victor");
   		   header.setSourceName("KORE");
   		   header.setTransactionId("cde2131ksjd");
   		   header.setBsCarrier("KORE"); 
    	   
    	   req.setHeader(header);   
    	  
    	    ActivateDeviceRequest request = (ActivateDeviceRequest) exchange
    				.getIn().getBody(ActivateDeviceRequest.class);
    	   
    	    
    	     System.out.println("Expected Header----------------" +expectHeader.getHeader()); 
    	     System.out.println("Delivered Header----------------" +req.getHeader()); 
    	     
    	    //check for different no. of and value of parameters
    	     if(!expectHeader.getHeader().equals(req.getHeader())){
    	    throw new AssertionError("Header Parameters are Different");
        }
             
    	     //null pointer check
    	     if(req.getHeader().getApplicationName()==null || req.getHeader().getBsCarrier()==null ||
    	    	req.getHeader().getOrganization()==null || req.getHeader().getRegion()==null ||
    	    	req.getHeader().getSourceName()==null || req.getHeader().getTimestamp()==null ||
    	    	req.getHeader().getTransactionId()==null ){
    	    	 throw new AssertionError("Header Parameters can not be Blank");
    	     }
    	    
    	    //check for  source name
    	     if(!expectHeader.getHeader().getSourceName().equals(req.getHeader().getSourceName())){
    	    	    // if (req == null || expectHeader.getHeader() != req.getHeader()) {
    	            throw new AssertionError("Source Name is Different");
    	        }
    	     
    	     //check for organization
    	     if(!expectHeader.getHeader().getOrganization().equals(req.getHeader().getOrganization())){
    	    	    // if (req == null || expectHeader.getHeader() != req.getHeader()) {
    	            throw new AssertionError("Organization is Different");
    	        }
       }

   //set expected dummy data to Device Information request
    public void setExpectedDeviceInformation(DeviceInformationRequest deviceInformationRequest) {
		// TODO Auto-generated method stub
		this.expectDeviceInfo = deviceInformationRequest;
	}

  //test  case for  device Information
    public void verifyDeviceInformationData() {
		// TODO Auto-generated method stub
		DeviceInformationRequest req=new DeviceInformationRequest();
		DeviceInformationRequestDataArea dataArea=new DeviceInformationRequestDataArea();
		DeviceId deviceIds = new DeviceId();
 		deviceIds.setId("");
 		
 		DeviceId[] deviceIdsArray = { deviceIds };

 		//dataArea.setDeviceId(deviceIdsArray);
 		
 		dataArea.setAccountName("Test");
        req.setDataArea(dataArea);
        dataArea.setAccountName("Test");
        req.setDataArea(dataArea);
       
 		header.setSourceName("KORE"); 				
 		 req.setHeader(header);
 		 
 	   System.out.println("Delivered Source Name----------------" +req.getHeader().getSourceName()); 
	     System.out.println("Delivered Account Name----------------" +req.getDataArea().getDeviceId().length); 
	      
	     //null pointer check for source name and account name
	     if(req.getHeader().getSourceName()==null || req.getDataArea().getAccountName()==null || req.getDataArea().getAccountName().equals("")  || req.getDataArea().getAccountName().isEmpty()){
	    	 throw new AssertionError("Source Name and Account Name can not be Blank");
	     }
	     
	   //null pointer check for device Id
	     if(deviceIds.getId()==null || deviceIds.getId().equals("") || deviceIds.getId().isEmpty())
	     {
	    	 throw new AssertionError("Device Id can not be Blank");	 
	     }
	}

	//set expected dummy data to Activate Device request
	public void setExpectedActivateDevice(ActivateDeviceRequest activateDeviceRequest) {
		// TODO Auto-generated method stub
		this.expectHeader = activateDeviceRequest;
	}

	//test  case for Activate device with null EAP Code
	public void verifyDeviceActivationData() {
		// TODO Auto-generated method stub
		ActivateDeviceRequest req=new ActivateDeviceRequest();
		ActivateDeviceRequestDataArea dataArea=new ActivateDeviceRequestDataArea();
	
    
		ActivateDeviceId [] deviceId = new ActivateDeviceId[1];
        deviceId[0] = new ActivateDeviceId();
    	deviceId[0].setId("89014103277405946190");
		deviceId[0].setKind("kind");
		deviceId[0].seteAPCode("eAPCode");
		
		
		dataArea.setDeviceId(deviceId);
 		
 		
         req.setDataArea(dataArea);
        
  		 header.setSourceName("KORE");
  		 req.setHeader(header);
  		 
  				
	     System.out.println("Delivered Source Name----------------" +req.getHeader().getSourceName()); 
	      
	     //null pointer check for EAP Code if Source is 'KORE'
	     if(req.getHeader().getSourceName()!=null && req.getHeader().getSourceName().equalsIgnoreCase("KORE") && req.getDataArea().getDeviceId()[0].geteAPCode().isEmpty()){
	    	 throw new AssertionError("EAP Code is Mandatory for KORE");
	     }
			     
			  
	}

	//set expected dummy data to Deactivate Device request
	public void setExpectedDeactivateDevice(DeactivateDeviceRequest deactivateDeviceRequest) {
		// TODO Auto-generated method stub
	this.expectDeactiveDevice=	deactivateDeviceRequest;
	}

	//test  case for Deactivate device with null device Id
	public void verifyDeviceDeactivationData() {
		// TODO Auto-generated method stub
		
				DeactivateDeviceRequest req=new DeactivateDeviceRequest();
				DeactivateDeviceRequestDataArea dataArea=new DeactivateDeviceRequestDataArea();
			
		   
		        DeviceId[] deviceId = new DeviceId[1];
		        deviceId[0] = new DeviceId();
		    	//deviceId[0].setId("89014103277405946190");
				deviceId[0].setKind("ghgjg");
				
				dataArea.setDeviceId(deviceId);
		 		dataArea.setAccountName("Test");
		 		
		         req.setDataArea(dataArea);
		        
		  		 header.setSourceName("KORE");
		  		 req.setHeader(header);
		  		 
		  				
			     System.out.println("Delivered Source Name----------------" +req.getHeader().getSourceName()); 
			     System.out.println("Delivered Account Name----------------" +req.getDataArea().getAccountName()); 
			      
			     //null pointer check for deviceId
			     if(req.getDataArea().getDeviceId()[0].getId()==null || req.getDataArea().getDeviceId()[0].getId().equals("") ){
			    	 throw new AssertionError("Device Id is Mandatory");
			     }
					  				
	}

	*/

}