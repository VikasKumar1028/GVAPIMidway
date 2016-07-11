package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;

public class NetSuiteIdValidationProcessor implements Processor {

	Logger log = Logger.getLogger(HeaderErrorProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		DeviceInformationRequest request = (DeviceInformationRequest) exchange
				.getIn().getBody(DeviceInformationRequest.class);
		
		DeviceInformationRequestDataArea deviceInformationRequestDataArea=request.getDataArea();
		
		if(deviceInformationRequestDataArea==null)
		
		{
			
			
		  missingNetSuiteId(exchange);
		
		}
		
		else{
			
			Integer netSuiteId=deviceInformationRequestDataArea.getNetSuiteId();
			
			if(netSuiteId==null/*||netSuiteId.trim().equals("")*/)
			{
				
				 missingNetSuiteId(exchange);
			}
		}
	}
	
	private void missingNetSuiteId(Exchange exchange) throws MissingParameterException
	{
		
		exchange.setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD);
		exchange.setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
				IResponse.ERROR_MESSAGE);
		throw new MissingParameterException(IResponse.INVALID_PAYLOAD.toString(),
				IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
	}

}
