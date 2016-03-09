package com.gv.midway.router;

import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gv.midway.constant.IConstant;
import com.gv.midway.processor.HeaderProcessor;
import com.gv.midway.processor.KoreDeviceInformationProcessor;
import com.gv.midway.processor.StubKoreDeviceInformationProcessor;
import com.gv.midway.processor.StubVerizonDeviceInformationProcessor;
import com.gv.midway.processor.VerizonDeviceInformationProcessor;
import com.gv.midway.processor.VerizonErrorProcessor;
import com.gv.midway.processor.VerizonPostProcessor1;
import com.gv.midway.service.IDeviceService;
// this static import is needed for older versions of Camel than 2.5
// import static org.apache.camel.language.simple.SimpleLanguage.simple;

/**
 * The Camel route
 * 
 * @version
 */
@PropertySource({ "classpath:stub.properties" })
@Component
public class CamelRoute extends RouteBuilder {

	/*
	 * @Autowired private ActivateService activateService;
	 */

	@Autowired
	private IDeviceService iDeviceService;

	private String uriRestVerizonEndPoint = "cxfrs://bean://rsVerizonClient";
	private String uriRestKoreEndPoint = "cxfrs://bean://rsKoreClient";
	
	Logger log = Logger.getLogger(CamelRoute.class.getName());
	@Autowired
	Environment env;

	@Override
	public void configure() throws Exception {
		
		
		 onException(UnknownHostException.class,ConnectException.class)
		    .routeId("ConnectionExceptionRoute")
		    .handled(true)
		    .log(LoggingLevel.ERROR, "Connection Error")
		    .maximumRedeliveries(2)
		    .redeliveryDelay(1000).process(new VerizonErrorProcessor());
		    
		    
		    ;
		   // .backOffMultiplier(2)
		   //.useExponentialBackOff()
		   // .maximumRedeliveryDelay(60000)
		   // .log(LoggingLevel.DEBUG, "Rolling back!")
		   // .rollback();


		log.info("CamelRoute");
		from("direct:deviceInformation").process(new HeaderProcessor())
				.choice().when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice().when(header("sourceName").isEqualTo("KORE"))
				.process(new StubKoreDeviceInformationProcessor())
				.to("log:input")
				.when(header("sourceName").isEqualTo("VERIZON"))
				.process(new StubVerizonDeviceInformationProcessor())
				.to("log:input").endChoice().otherwise().choice()
				.when(header("sourceName").isEqualTo("KORE"))
				.process(new KoreDeviceInformationProcessor()).to("log:input")
				.when(header("sourceName").isEqualTo("VERIZON"))
				.process(new VerizonDeviceInformationProcessor())
				.to(uriRestVerizonEndPoint)				
				.process(new VerizonPostProcessor1())
				.endChoice().end()
				
				
				
				
				/*.doCatch(Exception.class)
				.process(new VerizonPostProcessor()).end().choice()
				//Failure flow
				.when(header("CamelHttpResponseCode").isNotEqualTo("200"))
				// setting the original message
				.process(new VerizonPostProcessor1())
				// recursive calling
				.to("direct:deviceInformation")
				.endChoice()*/
				
				//.process(new VerizonPostProcessor()).end()
				.to("log:input").endChoice().end();

		from("direct:Kore").process(new KoreDeviceInformationProcessor())
				.to(uriRestKoreEndPoint).to("log:input");

		from("direct:insertDeviceDetails").bean(iDeviceService,
				"insertDeviceDetails").to("log:input").end();
		

		from("direct:updateDeviceDetails").bean(iDeviceService,
				"updateDeviceDetails").to("log:input").end();
		
		from("direct:getDeviceDetails").bean(iDeviceService,
				"getDeviceDetails").to("log:input").end();
		
		
		from("direct:getDeviceDetailsBsId").bean(iDeviceService,
				"getDeviceDetailsBsId").to("log:input").end();
		
		from("direct:insertDeviceDetailsinBatch").bean(iDeviceService,
				"insertDevicesDetailsInBatch").to("log:input").end();
	}
}
