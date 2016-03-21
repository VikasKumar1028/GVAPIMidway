package com.gv.midway.router;

import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gv.midway.constant.IConstant;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.deviceInformation.kore.KoreDeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.verizon.VerizonResponse;
import com.gv.midway.pojo.token.VerizonAuthorizationResponse;
import com.gv.midway.pojo.token.VerizonSessionLoginResponse;
import com.gv.midway.processor.GenericErrorProcessor;
import com.gv.midway.processor.HeaderProcessor;
import com.gv.midway.processor.KoreGenericExceptionProcessor;
import com.gv.midway.processor.VerizonGenericExceptionProcessor;
import com.gv.midway.processor.deviceInformation.KoreDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.KoreDeviceInformationPreProcessor;
import com.gv.midway.processor.deviceInformation.StubKoreDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.StubVerizonDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPreProcessor;
import com.gv.midway.processor.token.VerizonAuthorizationTokenProcessor;
import com.gv.midway.processor.token.VerizonSessionAttributeProcessor;
import com.gv.midway.processor.token.VerizonSessionTokenProcessor;
import com.gv.midway.service.IAuditService;
import com.gv.midway.service.IDeviceService;
// this static import is needed for older versions of Camel than 2.5
// import static org.apache.camel.language.simple.SimpleLanguage.simple;
import com.gv.midway.service.ISessionService;

/**
 * The Camel route
 * 
 * @version
 */
@PropertySource({ "classpath:stub.properties" ,"classpath:midway.properties"})
@Component
public class CamelRoute extends RouteBuilder {

	/*
	 * @Autowired private ActivateService activateService;
	 */

	@Autowired
	private IDeviceService iDeviceService;

	@Autowired
	private ISessionService iSessionService;

	@Autowired
	private IAuditService iAuditService;

	/*
	 * @Autowired private SessionBean sessionBean;
	 */

	private String uriRestVerizonEndPoint = "cxfrs://bean://rsVerizonClient";
	private String uriRestVerizonTokenEndPoint = "cxfrs://bean://rsVerizonTokenClient";
	private String uriRestKoreEndPoint = "cxfrs://bean://rsKoreClient";

	Logger log = Logger.getLogger(CamelRoute.class.getName());

	@Autowired
	Environment env;

	@Override
	public void configure() throws Exception {

		System.out.println("Source Name is...."
				+ (env.getProperty(IConstant.SOURCE_NAME_KORE)));

		onException(UnknownHostException.class, ConnectException.class)
				.routeId("ConnectionExceptionRoute").handled(true)
				.log(LoggingLevel.ERROR, "Connection Error")
				.maximumRedeliveries(3).redeliveryDelay(1000)
				.process(new GenericErrorProcessor(env));

		onException(VerizonSessionTokenExpirationException.class)
				.routeId("ConnectionLoginExceptionRoute").handled(true)
				.log(LoggingLevel.INFO, "Connection Error")
				.maximumRedeliveries(1).redeliveryDelay(1000)
				.bean(iSessionService, "checkToken").choice()
				.when(body().contains("true"))
				.log(LoggingLevel.INFO, "MATCH -REFETCHING ")
				.process(new VerizonAuthorizationTokenProcessor())
				.to(uriRestVerizonTokenEndPoint).unmarshal()
				.json(JsonLibrary.Jackson, VerizonAuthorizationResponse.class)
				.process(new VerizonSessionTokenProcessor())
				.to(uriRestVerizonTokenEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson, VerizonSessionLoginResponse.class)
				.process(new VerizonSessionAttributeProcessor())
				.bean(iSessionService, "setVzToken")
				. // saved this token in the DB
				endChoice().otherwise().log(LoggingLevel.INFO, "NOT MATCH")
				.to("log:input").end()
				// sync DB and	 session token in	 the	 ServletContext
				.bean(iSessionService, "synchronizeDBContextToken") 				
				// The control will come to this processor when all attempts have been failed
				.process(new GenericErrorProcessor(env)); 

		from("direct:deviceInformation").process(new HeaderProcessor())
				.choice()
					.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
						.choice()
							.when(header("sourceName").isEqualTo("KORE"))
									.process(new StubKoreDeviceInformationProcessor())
									.to("log:input")
							.when(header("sourceName").isEqualTo("VERIZON"))
									.process(new StubVerizonDeviceInformationProcessor())
									.to("log:input").endChoice()
					.otherwise()
						.choice()
						
							.when(header("sourceName").isEqualTo("KORE"))
										.doTry()
											.process(new KoreDeviceInformationPreProcessor())
											.bean(iAuditService, "auditExternalRequestCall")
											.to(uriRestKoreEndPoint)
											.unmarshal()
											.json(JsonLibrary.Jackson, KoreDeviceInformationResponse.class)
											.bean(iAuditService, "auditExternalResponseCall")
											.process(new KoreDeviceInformationPostProcessor(env))
										.doCatch(CxfOperationException.class)
											.bean(iAuditService, "auditExternalExceptionResponseCall")
											.process(new KoreGenericExceptionProcessor(env))
										.endDoTry()
	
							.endChoice()
	
							.when(header("sourceName").isEqualTo("VERIZON"))
									.doTry()
										.bean(iSessionService, "setContextTokenInExchange")
										.process(new VerizonDeviceInformationPreProcessor())
										.bean(iAuditService, "auditExternalRequestCall")
										.to(uriRestVerizonEndPoint)
										.unmarshal()
										.json(JsonLibrary.Jackson, VerizonResponse.class)
										.bean(iAuditService, "auditExternalResponseCall")
										.process(new VerizonDeviceInformationPostProcessor(env))
									.doCatch(CxfOperationException.class)
										.bean(iAuditService, "auditExternalExceptionResponseCall")
										.process(new VerizonGenericExceptionProcessor(env))
									.endDoTry()
	
							.endChoice()
							.end()
	
					.to("log:input")
				.endChoice()
				.end();

		from("direct:insertDeviceDetails")
				.bean(iDeviceService, "insertDeviceDetails").to("log:input")
				.end();

		from("direct:updateDeviceDetails")
				.bean(iDeviceService, "updateDeviceDetails").to("log:input")
				.end();

		from("direct:getDeviceDetails")
				.bean(iDeviceService, "getDeviceDetails").to("log:input").end();

		from("direct:getDeviceDetailsBsId")
				.bean(iDeviceService, "getDeviceDetailsBsId").to("log:input")
				.end();

		from("direct:insertDeviceDetailsinBatch")
				.bean(iDeviceService, "insertDevicesDetailsInBatch")
				.to("log:input").end();
	}
}
