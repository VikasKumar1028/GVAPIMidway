package com.gv.midway.processor.deviceInformation;


import java.util.Date;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.attjasper.GetTerminalDetailsRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperDeviceInformationPreProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeviceInformationPreProcessor.class.getName());
	
	Environment newEnv;
	
	
	public ATTJasperDeviceInformationPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public ATTJasperDeviceInformationPreProcessor() {
		// Empty ConstructorATT_JasperDeviceInformationPostProcessor
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		LOGGER.info("Begin:ATT_JasperDeviceInformationPreProcessor");
		
		 DeviceInformationRequest request = (DeviceInformationRequest)
		 exchange .getIn().getBody(DeviceInformationRequest.class);
		 
        String iccId=request.getDataArea().getDeviceId().getId();
		GetTerminalDetailsRequest getTerminalDetailsRequest = new GetTerminalDetailsRequest();

		GetTerminalDetailsRequest.Iccids iccids = new GetTerminalDetailsRequest.Iccids();
		List<String> iccIdList = iccids.getIccid();
		/*iccIdList.add("89011702272013902603");*/
			
		iccIdList.add(iccId);

		getTerminalDetailsRequest.setIccids(iccids);
		
        String version=newEnv.getProperty("attJapser.version");
		
		String licenseKey=newEnv.getProperty("attJapser.licenseKey");
		
		getTerminalDetailsRequest
				.setLicenseKey(licenseKey);
		getTerminalDetailsRequest.setMessageId(""+new Date().getTime());
		getTerminalDetailsRequest.setVersion(version);

		LOGGER.info("szie of iccId..............."
				+ getTerminalDetailsRequest.getIccids().getIccid().size());

		exchange.getIn().setBody(getTerminalDetailsRequest);

		exchange.getIn().setHeader(CxfConstants.OPERATION_NAME,
				"GetTerminalDetails");
		exchange.getIn().setHeader(CxfConstants.OPERATION_NAMESPACE,
				"http://api.jasperwireless.com/ws/schema");
		exchange.getIn()
				.setHeader("soapAction",
						"http://api.jasperwireless.com/ws/service/terminal/GetTerminalDetails");
		
		String username=newEnv.getProperty("attJapser.userName");
		
		String password=newEnv.getProperty("attJapser.password");
		
		List<SoapHeader> soapHeaders=CommonUtil.getSOAPHeaders(username, password);
	
		exchange.getIn().setHeader(Header.HEADER_LIST, soapHeaders);
	    exchange.setPattern(ExchangePattern.InOut);
	    
		LOGGER.info("End:ATT_JasperDeviceInformationPreProcessor");
	}
}
