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
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.deviceInformation.kore.response.DeviceInformationResponseKore;
import com.gv.midway.pojo.deviceInformation.verizon.response.DeviceInformationResponseVerizon;
import com.gv.midway.pojo.kore.KoreProvisoningResponse;
import com.gv.midway.pojo.token.VerizonAuthorizationResponse;
import com.gv.midway.pojo.token.VerizonSessionLoginResponse;
import com.gv.midway.processor.BulkDeviceProcessor;
import com.gv.midway.processor.GenericErrorProcessor;
import com.gv.midway.processor.HeaderProcessor;
import com.gv.midway.processor.KoreGenericExceptionProcessor;
import com.gv.midway.processor.VerizonGenericExceptionProcessor;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePostProcessor;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePreProcessor;
import com.gv.midway.processor.activateDevice.StubKoreActivateDeviceProcessor;
import com.gv.midway.processor.activateDevice.StubVerizonActivateDeviceProcessor;
import com.gv.midway.processor.activateDevice.VerizonActivateDevicePostProcessor;
import com.gv.midway.processor.activateDevice.VerizonActivateDevicePreProcessor;
import com.gv.midway.processor.callbacks.CallbackKafkaPostProcessor;
import com.gv.midway.processor.callbacks.CallbackPostProcessor;
import com.gv.midway.processor.callbacks.CallbackPreProcessor;
import com.gv.midway.processor.cell.StubCellBulkUploadProcessor;
import com.gv.midway.processor.cell.StubCellUploadProcessor;
import com.gv.midway.processor.customFieldsUpdateDevice.StubKoreCustomFieldsUpdateProcessor;
import com.gv.midway.processor.customFieldsUpdateDevice.StubVerizonCustomFieldsUpdateProcessor;
import com.gv.midway.processor.deactivateDevice.KoreDeactivateDevicePostProcessor;
import com.gv.midway.processor.deactivateDevice.KoreDeactivateDevicePreProcessor;
import com.gv.midway.processor.deactivateDevice.StubKoreDeactivateDeviceProcessor;
import com.gv.midway.processor.deactivateDevice.StubVerizonDeactivateDeviceProcessor;
import com.gv.midway.processor.deactivateDevice.VerizonDeactivateDevicePostProcessor;
import com.gv.midway.processor.deactivateDevice.VerizonDeactivateDevicePreProcessor;
import com.gv.midway.processor.deviceInformation.KoreDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.KoreDeviceInformationPreProcessor;
import com.gv.midway.processor.deviceInformation.StubKoreDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.StubVerizonDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPreProcessor;
import com.gv.midway.processor.suspendDevice.KoreSuspendDevicePostProcessor;
import com.gv.midway.processor.suspendDevice.KoreSuspendDevicePreProcessor;
import com.gv.midway.processor.suspendDevice.StubKoreSuspendDeviceProcessor;
import com.gv.midway.processor.suspendDevice.StubVerizonSuspendDeviceProcessor;
import com.gv.midway.processor.suspendDevice.VerizonSuspendDevicePostProcessor;
import com.gv.midway.processor.suspendDevice.VerizonSuspendDevicePreProcessor;
import com.gv.midway.processor.token.TokenProcessor;
import com.gv.midway.processor.token.VerizonAuthorizationTokenProcessor;
import com.gv.midway.processor.token.VerizonSessionAttributeProcessor;
import com.gv.midway.processor.token.VerizonSessionTokenProcessor;
import com.gv.midway.service.IAuditService;
import com.gv.midway.service.IDeviceService;
// this static import is needed for older versions of Camel than 2.5
// import static org.apache.camel.language.simple.SimpleLanguage.simple;
import com.gv.midway.service.ISessionService;
import com.gv.midway.service.ITransactionalService;

/**
 * The Camel route
 * 
 * @version
 */
@PropertySource({ "classpath:stub.properties", "classpath:midway.properties",
		"classpath:koreAuthentication.properties",
		"classpath:verizonAuthentication.properties" })
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

	@Autowired
	private ITransactionalService iTransactionalService;

	/*
	 * @Autowired private SessionBean sessionBean;
	 */

	private String uriRestVerizonEndPoint = "cxfrs://bean://rsVerizonClient";
	private String uriRestVerizonTokenEndPoint = "cxfrs://bean://rsVerizonTokenClient";
	private String uriRestKoreEndPoint = "cxfrs://bean://rsKoreClient";
	private String uriRestNetsuitEndPoint = "cxfrs://bean://rsNetsuitClient";

	Logger log = Logger.getLogger(CamelRoute.class.getName());

	@Autowired
	Environment env;

	@Override
	public void configure() throws Exception {

		onException(UnknownHostException.class, ConnectException.class)
				.routeId("ConnectionExceptionRoute")
				.handled(true)
				.log(LoggingLevel.ERROR, "Connection Error")
				.maximumRedeliveries(1)
				.redeliveryDelay(100)
				.bean(iTransactionalService,
						"populateConnectionErrorResponse(${exchange},CONNECTION_ERROR)")
				.bean(iAuditService,
						"auditExternalConnectionExceptionResponseCall")
				.process(new GenericErrorProcessor(env));

		onException(VerizonSessionTokenExpirationException.class)
				.routeId("ConnectionLoginExceptionRoute").handled(true)
				.log(LoggingLevel.INFO, "Connection Error")
				.onRedelivery(new TokenProcessor()).maximumRedeliveries(2)
				.redeliveryDelay(1000)
				// The control will come to this processor when all attempts
				// have been failed
				.process(new GenericErrorProcessor(env));

		onException(InvalidParameterException.class).handled(true)
				.log(LoggingLevel.INFO, "Invalid Parameter Passed Error")
				.process(new GenericErrorProcessor(env));

		from("direct:tokenGeneration").bean(iSessionService, "checkToken")
				.choice().when(body().contains("true"))
				.log(LoggingLevel.INFO, "MATCH -REFETCHING ")
				.process(new VerizonAuthorizationTokenProcessor(env))
				.to(uriRestVerizonTokenEndPoint).unmarshal()
				.json(JsonLibrary.Jackson, VerizonAuthorizationResponse.class)
				.process(new VerizonSessionTokenProcessor())
				.to(uriRestVerizonTokenEndPoint).unmarshal()
				.json(JsonLibrary.Jackson, VerizonSessionLoginResponse.class)
				.process(new VerizonSessionAttributeProcessor())
				.bean(iSessionService, "setVzToken")
				. // saved this token in the DB
				endChoice().otherwise().log(LoggingLevel.INFO, "NOT MATCH")
				.to("log:input").end()
				// sync DB and session token in the ServletContext
				.bean(iSessionService, "synchronizeDBContextToken");

		/**
		 * Get DeviceInformation from Carrier and update in MasterDB and return
		 * back to Calling System.
		 **/
		from("direct:deviceInformationCarrier").process(new HeaderProcessor())
				.choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice().when(header("derivedCarrierName").isEqualTo("KORE"))
				.process(new StubKoreDeviceInformationProcessor())
				.to("log:input")
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.process(new StubVerizonDeviceInformationProcessor())
				.to("log:input").endChoice().otherwise().choice()

				.when(header("derivedCarrierName").isEqualTo("KORE")).doTry()
				.process(new KoreDeviceInformationPreProcessor())
				.bean(iAuditService, "auditExternalRequestCall")
				.to(uriRestKoreEndPoint).unmarshal()
				.json(JsonLibrary.Jackson, DeviceInformationResponseKore.class)
				.bean(iAuditService, "auditExternalResponseCall")
				.bean(iDeviceService, "setDeviceInformationDB")
				.process(new KoreDeviceInformationPostProcessor(env))
				.bean(iDeviceService, "updateDeviceInformationDB")
				.doCatch(CxfOperationException.class)
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.process(new KoreGenericExceptionProcessor(env)).endDoTry()

				.endChoice()

				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				// will store only one time in Audit even on connection failure
				.bean(iAuditService, "auditExternalRequestCall")
				.to("direct:VerizonDeviceInformationCarrierSubProcess")

				.endChoice().end()

				.to("log:input").endChoice().end();

		from("direct:VerizonDeviceInformationCarrierSubProcess").doTry()
				.to("direct:VerizonDeviceInformationCarrierSubProcessFlow")
				.doCatch(CxfOperationException.class)
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.process(new VerizonGenericExceptionProcessor(env)).endDoTry()
				.end();

		// SubFlow: Verizon Device Information
		from("direct:VerizonDeviceInformationCarrierSubProcessFlow")
				.errorHandler(noErrorHandler())
				// REMOVED Audit will store record 3 times in case of failure
				// (see onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.bean(iSessionService, "setContextTokenInExchange")
				.process(new VerizonDeviceInformationPreProcessor())
				.to(uriRestVerizonEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson,
						DeviceInformationResponseVerizon.class)
				.bean(iAuditService, "auditExternalResponseCall")
				.bean(iDeviceService, "setDeviceInformationDB")
				.process(new VerizonDeviceInformationPostProcessor(env))
				.bean(iDeviceService, "updateDeviceInformationDB");

		// ***** DEVICE ACTIVATION BEGIN

		// Main: Device Activation Flow

		from("direct:activateDevice").process(new HeaderProcessor()).choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice().when(header("derivedCarrierName").isEqualTo("KORE"))
				.log("message" + header("derivedSourceName"))
				.process(new StubKoreActivateDeviceProcessor()).to("log:input")
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.process(new StubVerizonActivateDeviceProcessor())
				.to("log:input").endChoice().otherwise().choice()
				.when(header("derivedCarrierName").isEqualTo("KORE"))
				.wireTap("direct:processActivateKoreTransaction")
				.process(new KoreActivateDevicePostProcessor(env))
				.endChoice()
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.bean(iSessionService, "setContextTokenInExchange")
				.bean(iTransactionalService, "populateActivateDBPayload")
				// will store only one time in Audit even on connection failure
				.bean(iAuditService, "auditExternalRequestCall")
				.to("direct:VerizonActivationFlow1").endChoice().end()
				.to("log:input").endChoice().end();

		from("direct:VerizonActivationFlow1")
				.doTry()
				.to("direct:VerizonActivationFlow2")
				.doCatch(CxfOperationException.class)
				.bean(iTransactionalService,
						"populateVerizonTransactionalErrorResponse")
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.process(new VerizonGenericExceptionProcessor(env)).endDoTry()
				.end();

		// SubFlow: Device Verizon Activation
		from("direct:VerizonActivationFlow2")
				.errorHandler(noErrorHandler())
				// REMOVED Audit will store record 3 times in case of failure
				// (see onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.bean(iSessionService, "setContextTokenInExchange")
				.process(new VerizonActivateDevicePreProcessor())
				.to(uriRestVerizonEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson)
				.bean(iTransactionalService,
						"populateVerizonTransactionalResponse")
				.bean(iAuditService, "auditExternalResponseCall")
				.process(new VerizonActivateDevicePostProcessor(env));

		// SubFlow: Device Kore Activation
		from("direct:processActivateKoreTransaction")
				.log("Wire Tap Thread activation")
				.bean(iTransactionalService, "populateActivateDBPayload")
				.split().method("deviceSplitter").recipientList()
				.method("koreDeviceServiceRouter");

		// SubFlow: Device Kore Acitvation- SEDA CALL
		from("seda:koreSedaActivation?concurrentConsumers=5")
				.onException(CxfOperationException.class)
				.handled(true)
				.bean(iTransactionalService,
						"populateKoreTransactionalErrorResponse")
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.end()
				.process(new KoreActivateDevicePreProcessor(env))
				.bean(iAuditService, "auditExternalRequestCall")
				.to(uriRestKoreEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson, KoreProvisoningResponse.class)
				.bean(iTransactionalService,
						"populateKoreTransactionalResponse")
				.bean(iAuditService, "auditExternalResponseCall");

		// ***** DEVICE ACTIVATION END

		// ***** DEVICE DEACTIVATION BEGIN

		// Main: Device DeActivation Flow

		from("direct:deactivateDevice").process(new HeaderProcessor())
				.choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice()
				.when(header("derivedCarrierName").isEqualTo("KORE"))
				.process(new StubKoreDeactivateDeviceProcessor())
				.to("log:input")
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.process(new StubVerizonDeactivateDeviceProcessor())
				.to("log:input")
				.endChoice()
				.otherwise()
				.choice()
				// santosh:
				.when(header("derivedCarrierName").isEqualTo("KORE"))
				.wireTap("direct:processDeactivateKoreTransaction")
				.process(new KoreDeactivateDevicePostProcessor(env))

				.endChoice()
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.bean(iSessionService, "setContextTokenInExchange")
				.bean(iTransactionalService, "populateDeactivateDBPayload")
				.bean(iAuditService, "auditExternalRequestCall")
				.to("direct:VerizonDeactivationFlow1")

				.endChoice().end().to("log:input").endChoice().end();

		// SubFlow: Device Verizon DeActivation

		from("direct:VerizonDeactivationFlow1")
				.doTry()
				.to("direct:VerizonDeactivationFlow2")
				.doCatch(CxfOperationException.class)
				.bean(iTransactionalService,
						"populateVerizonTransactionalErrorResponse")
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.process(new VerizonGenericExceptionProcessor(env)).endDoTry()
				.end();

		// SubFlow: Device Verizon DeActivation

		from("direct:VerizonDeactivationFlow2")
				.errorHandler(noErrorHandler())
				// REMOVED Audit will store record 3 times in case of failure
				// (see onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.bean(iSessionService, "setContextTokenInExchange")
				.process(new VerizonDeactivateDevicePreProcessor())
				// Audit will store record 3 times in case of failure (see
				// onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.to(uriRestVerizonEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson)
				.bean(iTransactionalService,
						"populateVerizonTransactionalResponse")
				.bean(iAuditService, "auditExternalResponseCall")
				.process(new VerizonDeactivateDevicePostProcessor(env));

		// SubFlow: Device Kore DeActivation

		from("direct:processDeactivateKoreTransaction")
				.log("Wire Tap Thread deactivation")
				.bean(iTransactionalService, "populateDeactivateDBPayload")
				.split().method("deviceSplitter").recipientList()
				.method("koreDeviceServiceRouter");

		// SubFlow: Device Kore DeAcitvation- SEDA CALL
		from("seda:koreSedaDeactivation?concurrentConsumers=5")
				.onException(CxfOperationException.class)
				.handled(true)
				.bean(iTransactionalService,
						"populateKoreTransactionalErrorResponse")
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.end()
				.process(new KoreDeactivateDevicePreProcessor(env))
				.bean(iAuditService, "auditExternalRequestCall")
				.to(uriRestKoreEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson, KoreProvisoningResponse.class)
				.bean(iAuditService, "auditExternalResponseCall")
				.bean(iTransactionalService,
						"populateKoreTransactionalResponse");

		// ***** DEVICE DEACTIVATION END

		// ***** DEVICE SUSPEND BEGIN

		// Main: Device Suspension Flow

		from("direct:suspendDevice").process(new HeaderProcessor()).choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice().when(header("derivedCarrierName").isEqualTo("KORE"))
				.process(new StubKoreSuspendDeviceProcessor()).to("log:input")
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.process(new StubVerizonSuspendDeviceProcessor())
				.to("log:input").endChoice().otherwise().choice()
				.when(header("derivedCarrierName").isEqualTo("KORE"))
				.wireTap("direct:processSuspendKoreTransaction")
				.process(new KoreSuspendDevicePostProcessor(env))

				.endChoice()
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.bean(iSessionService, "setContextTokenInExchange")
				.bean(iTransactionalService, "populateSuspendDBPayload")
				.bean(iAuditService, "auditExternalRequestCall")
				.to("direct:VerizonSuspendFlow1")

				.endChoice().end().to("log:input").endChoice().end();

		// SubFlow: Device Verizon Suspension

		from("direct:VerizonSuspendFlow1")
				.doTry()
				.to("direct:VerizonSuspendFlow2")
				.doCatch(CxfOperationException.class)
				.bean(iTransactionalService,
						"populateVerizonTransactionalErrorResponse")
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.process(new VerizonGenericExceptionProcessor(env)).endDoTry()
				.end();

		// SubFlow: Device Verizon Suspension

		from("direct:VerizonSuspendFlow2")
				.errorHandler(noErrorHandler())
				// REMOVED Audit will store record 3 times in case of failure
				// (see onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.bean(iSessionService, "setContextTokenInExchange")
				.process(new VerizonSuspendDevicePreProcessor())
				// Audit will store record 3 times in case of failure (see
				// onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.to(uriRestVerizonEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson)
				.bean(iTransactionalService,
						"populateVerizonTransactionalResponse")
				.bean(iAuditService, "auditExternalResponseCall")
				.process(new VerizonSuspendDevicePostProcessor(env));

		// SubFlow: Device Kore Suspension

		from("direct:processSuspendKoreTransaction")
				.log("Wire Tap Thread suspension")
				.bean(iTransactionalService, "populateSuspendDBPayload")
				.split().method("deviceSplitter").recipientList()
				.method("koreDeviceServiceRouter");

		// SubFlow: Device Kore Suspension- SEDA CALL
		from("seda:koreSedaSuspend?concurrentConsumers=5")
				.onException(CxfOperationException.class)
				.handled(true)
				.bean(iTransactionalService,
						"populateKoreTransactionalErrorResponse")
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.end()
				.process(new KoreSuspendDevicePreProcessor(env))
				.bean(iAuditService, "auditExternalRequestCall")
				.to(uriRestKoreEndPoint)
				.unmarshal()
				.json(JsonLibrary.Jackson, DeviceInformationResponseKore.class)
				.bean(iAuditService, "auditExternalResponseCall")
				.bean(iTransactionalService,
						"populateKoreTransactionalResponse")
				.process(new KoreSuspendDevicePostProcessor());

		// ***** DEVICE Suspension END

		/** Insert or Update Single Device details in MasterDB **/

		from("direct:updateDeviceDetails").choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.process(new StubCellUploadProcessor()).endChoice().otherwise()
				.bean(iDeviceService, "updateDeviceDetails").end();

		/**
		 * Get DeviceInformation from MasterDB and return back to Calling
		 * System.
		 **/
		from("direct:getDeviceInformationDB")
				.bean(iDeviceService, "getDeviceInformationDB").to("log:input")
				.end();

		/** Insert or Upload Batch Device details in MasterDB. **/
		from("direct:updateDevicesDetailsBulkActual").onCompletion()
				.modeBeforeConsumer().setBody().body()
				.process(new BulkDeviceProcessor()).end()
				.bean(iDeviceService, "updateDevicesDetailsBulk").split()
				.method("bulkOperationSplitter").recipientList()
				.method("bulkOperationServiceRouter");

		from("direct:updateDevicesDetailsBulk").choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.process(new StubCellBulkUploadProcessor()).endChoice()
				.otherwise().to("direct:updateDevicesDetailsBulkActual").end();

		/**
		 * Bulk Insert or Update the device in MasterDB using Seda
		 */
		from("seda:bulkOperationDeviceSyncInDB?concurrentConsumers=5").bean(
				iDeviceService, "bulkOperationDeviceSyncInDB");

		/**
		 * saving callbacks from verizon into MongoDB and and sending it to
		 * target the target system
		 * 
		 * */
		from("direct:callbacks")
				.bean(iTransactionalService, "populateCallbackDBPayload")
				.process(new CallbackPreProcessor(env))
				.bean(iTransactionalService, "findMidwayTransactionId")
				.process(new CallbackPostProcessor())
				.to("kafka:localhost:9092?topic=topic")
				// .to("kafka:10.10.2.190:9092,10.10.2.190:9093,10.10.2.190:9094?topic=my-replicated-topic")
				.process(new CallbackKafkaPostProcessor())
				// ******************DONOT REMOVE THIS COMMENTED CODE
				// **********************
				// .doTry()
				// .to(uriRestNetsuitEndPoint)
				// .doCatch(CxfOperationException.class)
				.end();

		/**
		 * Get all the Kore devices with carrier status pending from
		 * TransactionDB
		 */
		/*
		 * from("timer://koreCheckStatusTimer?period=5m").bean(iTransactionalService
		 * ,"populatePendingKoreCheckStatus")
		 * .split().method("checkStatusSplitter"
		 * ).recipientList().method("koreCheckStatusServiceRouter");
		 */

		/**
		 * Check status of all the Kore devices with carrier status pending and
		 * updated it in TransactionDB if it is completed from Kore and call the
		 * netsuiteEndpoint for them
		 */
		/*
		 * from("seda:koreSedaCheckStatus?concurrentConsumers=5") .doTry()
		 * .process(new KoreCheckStatusPreProcessor(env))
		 * .to(uriRestKoreEndPoint).unmarshal() .json(JsonLibrary.Jackson,
		 * KoreCheckStatusResponse.class)
		 *//** Get the status of device update carrier status in transaction DB */
		/*
		 * .bean(iTransactionalService,"populateKoreTransactionalResponse")
		 *//**
		 * Prepare the netsuite call back response
		 */
		/*
		 * .process(new KoreDeviceInformationPostProcessor())
		 *//**
		 * Send to Netsuite restlet callback end point and update midway
		 * status in transaction DB
		 */
		/*
		 * .to(uriRestNetsuitEndPoint) .doCatch(CxfOperationException.class)
		 * .bean(iTransactionalService,"populateKoreTransactionalErrorResponse")
		 * .bean(iAuditService, "auditExternalExceptionResponseCall")
		 * .process(new KoreGenericExceptionProcessor(env)) .endDoTry();
		 */

		/**  UpdateCustomeFieldDevice **/
		from("direct:customeFields").process(new HeaderProcessor())
				.choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice().when(header("derivedCarrierName").isEqualTo("KORE"))
				.process(new StubKoreCustomFieldsUpdateProcessor())
				.to("log:input")
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.process(new StubVerizonCustomFieldsUpdateProcessor())
				.to("log:input").endChoice().otherwise().choice().end();
	}
}
