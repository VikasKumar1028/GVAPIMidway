package com.gv.midway.router;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeTimedOutException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gv.midway.constant.CarrierType;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.exception.KoreSimMissingException;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.checkstatus.kore.KoreCheckStatusResponse;
import com.gv.midway.pojo.deviceInformation.kore.response.DeviceInformationResponseKore;
import com.gv.midway.pojo.deviceInformation.verizon.response.DeviceInformationResponseVerizon;
import com.gv.midway.pojo.kore.DKoreResponseCode;
import com.gv.midway.pojo.kore.KoreProvisoningResponse;
import com.gv.midway.pojo.token.VerizonAuthorizationResponse;
import com.gv.midway.pojo.token.VerizonSessionLoginResponse;
import com.gv.midway.processor.ATTJasperGenericExceptionProcessor;
import com.gv.midway.processor.BulkDeviceProcessor;
import com.gv.midway.processor.CarrierProvisioningDevicePostProcessor;
import com.gv.midway.processor.ChangeDeviceServicePlanValidatorProcessor;
import com.gv.midway.processor.DateValidationProcessor;
import com.gv.midway.processor.GenericErrorProcessor;
import com.gv.midway.processor.HeaderErrorProcessor;
import com.gv.midway.processor.HeaderProcessor;
import com.gv.midway.processor.KoreBatchExceptionProcessor;
import com.gv.midway.processor.KoreGenericExceptionProcessor;
import com.gv.midway.processor.NetSuiteIdValidationProcessor;
import com.gv.midway.processor.TimeOutErrorProcessor;
import com.gv.midway.processor.VerizonBatchExceptionProcessor;
import com.gv.midway.processor.VerizonGenericExceptionProcessor;
import com.gv.midway.processor.activateDevice.ATTJasperActivateDevicePreProcessor;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePreProcessor;
import com.gv.midway.processor.activateDevice.StubATTJasperActivateDeviceProcessor;
import com.gv.midway.processor.activateDevice.StubKoreActivateDeviceProcessor;
import com.gv.midway.processor.activateDevice.StubVerizonActivateDeviceProcessor;
import com.gv.midway.processor.activateDevice.VerizonActivateDevicePreProcessor;
import com.gv.midway.processor.callbacks.CallbackPostProcessor;
import com.gv.midway.processor.callbacks.CallbackPreProcessor;
import com.gv.midway.processor.cell.StubCellBulkUploadProcessor;
import com.gv.midway.processor.cell.StubCellUploadProcessor;
import com.gv.midway.processor.changeDeviceServicePlans.ATTJasperChangeDeviceServicePlansPreProcessor;
import com.gv.midway.processor.changeDeviceServicePlans.KoreChangeDeviceServicePlansPreProcessor;
import com.gv.midway.processor.changeDeviceServicePlans.StubATTJasperChangeDeviceServicePlansProcessor;
import com.gv.midway.processor.changeDeviceServicePlans.StubKoreChangeDeviceServicePlansProcessor;
import com.gv.midway.processor.changeDeviceServicePlans.StubVerizonChangeDeviceServicePlansProcessor;
import com.gv.midway.processor.changeDeviceServicePlans.VerizonChangeDeviceServicePlansPreProcessor;
import com.gv.midway.processor.checkstatus.KoreCheckStatusErrorProcessor;
import com.gv.midway.processor.checkstatus.KoreCheckStatusPostProcessor;
import com.gv.midway.processor.checkstatus.KoreCheckStatusPreProcessor;
import com.gv.midway.processor.connectionInformation.VerizonDeviceConnectionInformationPreProcessor;
import com.gv.midway.processor.connectionInformation.deviceConnectionStatus.StubVerizonDeviceConnectionStatusProcessor;
import com.gv.midway.processor.connectionInformation.deviceConnectionStatus.VerizonDeviceConnectionStatusPostProcessor;
import com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo.StubVerizonDeviceSessionBeginEndInfoProcessor;
import com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo.VerizonDeviceSessionBeginEndInfoPostProcessor;
import com.gv.midway.processor.customFieldsDevice.ATTJasperCustomFieldDevicePreProcessor;
import com.gv.midway.processor.customFieldsDevice.KoreCustomFieldsPreProcessor;
import com.gv.midway.processor.customFieldsDevice.StubKoreCustomFieldsProcessor;
import com.gv.midway.processor.customFieldsDevice.StubVerizonCustomFieldsProcessor;
import com.gv.midway.processor.customFieldsDevice.VerizonCustomFieldsPreProcessor;
import com.gv.midway.processor.deactivateDevice.ATTJasperDeactivateDevicePreProcessor;
import com.gv.midway.processor.deactivateDevice.KoreDeactivateDevicePreProcessor;
import com.gv.midway.processor.deactivateDevice.StubATTJasperDeactivateDeviceProcessor;
import com.gv.midway.processor.deactivateDevice.StubKoreDeactivateDeviceProcessor;
import com.gv.midway.processor.deactivateDevice.StubVerizonDeactivateDeviceProcessor;
import com.gv.midway.processor.deactivateDevice.VerizonDeactivateDevicePreProcessor;
import com.gv.midway.processor.deviceInformation.ATTJasperDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.ATTJasperDeviceInformationPreProcessor;
import com.gv.midway.processor.deviceInformation.KoreDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.KoreDeviceInformationPreProcessor;
import com.gv.midway.processor.deviceInformation.StubKoreDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.StubVerizonDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPreProcessor;
import com.gv.midway.processor.jobScheduler.JobInitializedPostProcessor;
import com.gv.midway.processor.jobScheduler.KoreDeviceUsageHistoryPostProcessor;
import com.gv.midway.processor.jobScheduler.KoreDeviceUsageHistoryPreProcessor;
import com.gv.midway.processor.jobScheduler.KoreTransactionFailureDeviceUsageHistoryPreProcessor;
import com.gv.midway.processor.jobScheduler.VerizonDeviceConnectionHistoryPostProcessor;
import com.gv.midway.processor.jobScheduler.VerizonDeviceConnectionHistoryPreProcessor;
import com.gv.midway.processor.jobScheduler.VerizonDeviceUsageHistoryPostProcessor;
import com.gv.midway.processor.jobScheduler.VerizonDeviceUsageHistoryPreProcessor;
import com.gv.midway.processor.jobScheduler.VerizonTransactionFailureDeviceConnectionHistoryPreProcessor;
import com.gv.midway.processor.jobScheduler.VerizonTransactionFailureDeviceUsageHistoryPreProcessor;
import com.gv.midway.processor.kafka.KafkaProcessor;
import com.gv.midway.processor.reactivate.ATTJasperReactivateDevicePreProcessor;
import com.gv.midway.processor.reactivate.KoreReactivateDevicePreProcessor;
import com.gv.midway.processor.reactivate.StubATTJasperReactivateDeviceProcessor;
import com.gv.midway.processor.reactivate.StubKoreReactivateDeviceProcessor;
import com.gv.midway.processor.restoreDevice.ATTJasperRestoreDevicePreProcessor;
import com.gv.midway.processor.restoreDevice.KoreRestoreDevicePreProcessor;
import com.gv.midway.processor.restoreDevice.StubATTJasperRestoreDeviceProcessor;
import com.gv.midway.processor.restoreDevice.StubKoreRestoreDeviceProcessor;
import com.gv.midway.processor.restoreDevice.StubVerizonRestoreDeviceProcessor;
import com.gv.midway.processor.restoreDevice.VerizonRestoreDevicePreProcessor;
import com.gv.midway.processor.suspendDevice.ATTJasperSuspendDevicePreProcessor;
import com.gv.midway.processor.suspendDevice.KoreSuspendDevicePreProcessor;
import com.gv.midway.processor.suspendDevice.StubATTJasperSuspendDeviceProcessor;
import com.gv.midway.processor.suspendDevice.StubKoreSuspendDeviceProcessor;
import com.gv.midway.processor.suspendDevice.StubVerizonSuspendDeviceProcessor;
import com.gv.midway.processor.suspendDevice.VerizonSuspendDevicePreProcessor;
import com.gv.midway.processor.token.TokenProcessor;
import com.gv.midway.processor.token.VerizonAuthorizationTokenProcessor;
import com.gv.midway.processor.token.VerizonSessionAttributeProcessor;
import com.gv.midway.processor.token.VerizonSessionTokenProcessor;
import com.gv.midway.processor.usageDevice.RetrieveDeviceUsageHistoryPostProcessor;
import com.gv.midway.processor.usageDevice.RetrieveDeviceUsageHistoryPreProcessor;
import com.gv.midway.processor.usageDevice.StubVerizonRetrieveDeviceUsageHistoryProcessor;
import com.gv.midway.service.IAuditService;
import com.gv.midway.service.IDeviceService;
import com.gv.midway.service.IJobService;
import com.gv.midway.service.ISchedulerService;
import com.gv.midway.service.ISessionService;
import com.gv.midway.service.ITransactionalService;

/**
 * The Camel route
 * 
 * @version
 */
@PropertySource({ "classpath:stub.properties", "classpath:midway.properties",
        "classpath:env.properties" })
@Component
public class CamelRoute extends RouteBuilder {

    @Autowired
    private IDeviceService iDeviceService;

    @Autowired
    private ISessionService iSessionService;

    @Autowired
    private IAuditService iAuditService;

    @Autowired
    private ITransactionalService iTransactionalService;

    @Autowired
    private ISchedulerService iSchedulerService;

    @Autowired
    private IJobService iJobService;

    private String uriRestVerizonEndPoint = "cxfrs://bean://rsVerizonClient";
    private String uriRestVerizonTokenEndPoint = "cxfrs://bean://rsVerizonTokenClient";
    private String uriRestKoreEndPoint = "cxfrs://bean://rsKoreClient";
    private String uriRestNetsuitEndPoint = "cxfrs://bean://rsNetsuitClient";
    
    private String attJasperTerminalEndPoint = "cxf:bean:attJasperTerminalEndPoint";

    private static final Logger LOGGER = Logger.getLogger(CamelRoute.class);

    @Autowired
    Environment env;

    @Override
    public void configure() throws Exception {

        LOGGER.info("CAMEL");
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

        onException(MissingParameterException.class)
                .handled(true)
                .log(LoggingLevel.INFO,
                        "Pass all the required header parameters")
                .process(new HeaderErrorProcessor(env));
        
    
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
                .bean(iSessionService, "synchronizeDBContextToken").bean(iSessionService, "setTokenRequired");

        /** Main Change Device Service Plans Flow **/

        // Calling the Activate Device Flow
        activateDevice();

        // Calling the Deactivate Device Flow
        deactivateDevice();

        // Calling the Restore Device Flow
        restoreDevice();

        // Calling the Suspend Device Flow
        suspendDevice();

        // Calling the Reactivate Device Flow
        reactivateDevice();

        // Calling the CustomeFields Device Flow
        customeFields();

        // Calling the change Device ServicePlans Flow
        changeDeviceServicePlans();

        // Calling the Device Connection Status Flow
        deviceConnectionStatus();

        // Calling the Device in Session Flow
        deviceSessionBeginEndInfo();

        // Calling the getDeviceInfomrationFromCarrier
        deviceInformationCarrier();

        // Calling the getDeviceInformationDB
        getDeviceInformationDB();

        // Insert or Update Single Device Detail
        updateDeviceDetails();

        // Insert or Update Device Details in Bulk
        updateDevicesDetailsBulk();

        // Retrieve Device Usage History from Carrier
        retrieveDeviceUsageHistoryCarrier();

        // Execution of schduled jobs scheduledJobs

        deviceConnectionHistoryVerizonJob();

        deviceUsageHistoryVerizonJob();

        deviceUsageHistoryKoreJob();

        // Transaction Failure Job
        transactionFailureJob();

        // Get Device Data Usage from Midway DB
        getDeviceUsageInfoDB();

        // Get Connection History from Midway DB
        getDeviceConnectionHistoryInfoDB();
        
        // Get Device usage of all the devices by date and carrier from Midway
        getDevicesUsageByDayAndCarrierInfoDB();

        // Start Job
        startJob();

        // Returns the Job Response
        jobResponse();

        // device Overage Notification Job
       // notificationJob();

       // Verizon Call Back
        callbacks();

        // Kore Check Status Timer
        koreCheckStatusTimer();
    }

    /**
     * Activation Flow for Verizon and Kore
     * 
     */
    public void activateDevice() {

        // Begin:Activate Devices

        from("direct:activateDevice").process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .log("message" + header("derivedSourceName"))
                .process(new StubKoreActivateDeviceProcessor()).to("log:input")
                
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonActivateDeviceProcessor())
                .to("log:input").
                
                 when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperActivateDeviceProcessor())
                .to("log:input").
                 endChoice().
                 
                 otherwise().choice()
                 
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processActivateKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor(env))
                .endChoice().
                
                when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateActivateDBPayload")
                // will store only one time in Audit even on connection failure
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonActivationFlow1").endChoice().
                
                 when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.wireTap("direct:processActivateATTJasperTransaction")
				.process(new CarrierProvisioningDevicePostProcessor(env))
				.endChoice().end().to("log:input").endChoice().end();
                
              

        // Verizon Flow-1
        from("direct:VerizonActivationFlow1")
                .doTry()
                .to("direct:VerizonActivationFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2
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
                .process(new CarrierProvisioningDevicePostProcessor(env));

        // Kore Flow-1
        from("direct:processActivateKoreTransaction")
                .log("Wire Tap Thread activation")
                .bean(iTransactionalService, "populateActivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW
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
        
        
     // ATTJasper Flow-1
        from("direct:processActivateATTJasperTransaction")
                .log("Wire Tap Thread activation")
                .bean(iTransactionalService, "populateActivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW
        from("seda:attJasperSedaActivation?concurrentConsumers=5")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperActivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(attJasperTerminalEndPoint)
                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalResponse");

        // End:Activate Devices
    }

    /**
     * Deactivate Flow for Verizon and Kore
     * 
     */

    public void deactivateDevice() {

        // Begin:Deactivate Devices

        from("direct:deactivateDevice").process(new HeaderProcessor()).choice()
					.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
					.choice().when(header("derivedCarrierName").isEqualTo("KORE"))
					.process(new StubKoreDeactivateDeviceProcessor())
					.to("log:input")
					
					.when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
					.process(new StubATTJasperDeactivateDeviceProcessor())
					.to("log:ATTJASPER")
					
					.when(header("derivedCarrierName").isEqualTo("VERIZON"))
					.process(new StubVerizonDeactivateDeviceProcessor())
					.to("log:input")
					.endChoice().otherwise().choice()
					
					.when(header("derivedCarrierName").isEqualTo("KORE"))
					.wireTap("direct:processDeactivateKoreTransaction")
					.process(new CarrierProvisioningDevicePostProcessor(env))
					.endChoice().
					              
					 when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
					 .wireTap("direct:processDeactivateATTJasperTansaction")
					 .process(new CarrierProvisioningDevicePostProcessor(env))
					.endChoice()	 
					      
					.when(header("derivedCarrierName").isEqualTo("VERIZON"))
					.bean(iSessionService, "setContextTokenInExchange")
					.bean(iTransactionalService, "populateDeactivateDBPayload")
					.bean(iAuditService, "auditExternalRequestCall")
					.to("direct:VerizonDeactivationFlow1").endChoice().end()
					.to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonDeactivationFlow1")
                .doTry()
                .to("direct:VerizonDeactivationFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:VerizonDeactivationFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeactivateDevicePreProcessor())
                // Audit will store record 3 times in case of failure (see
                // onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequesktCall")
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor(env));

        // Kore Flow-1

        from("direct:processDeactivateKoreTransaction")
                .log("Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateDeactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

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
        
        
       	// ATTJASPER Flow-1
        

        from("direct:processDeactivateATTJasperTansaction")
                .log("Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateDeactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");
        
        // ATTJASPER SEDA FLOW
        
        from("seda:attJasperSedaDeactivation?concurrentConsumers=5")
        .onException(SoapFault.class)
        .handled(true)
        .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
        .bean(iTransactionalService,
                "populateATTJasperTransactionalErrorResponse")
        .end()
        .process(new ATTJasperDeactivateDevicePreProcessor(env))
        .bean(iAuditService, "auditExternalRequestCall")
        .to(attJasperTerminalEndPoint)
       
        .bean(iAuditService, "auditExternalSOAPResponseCall")
        .bean(iTransactionalService,
                "populateATTJasperTransactionalResponse");



        // End:Deactivate Devices
    }

    /**
     * Restore Flow for Verizon and Kore
     */
    public void restoreDevice() {

        // Begin:Restore Devices

        from("direct:restoreDevice").process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .log("message" + header("derivedSourceName"))
                .process(new StubKoreRestoreDeviceProcessor()).to("log:input")
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonRestoreDeviceProcessor())
                .to("log:input")
                
                  
                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.process(new StubATTJasperRestoreDeviceProcessor())
				.to("log:ATTJASPER")
                
                .endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processRestoreKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor(env))
                .endChoice().
                
                  when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				 .wireTap("direct:processRestoreDeviceATTJasperTansaction")
				 .process(new CarrierProvisioningDevicePostProcessor(env))
				.endChoice()	
					
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateRestoreDBPayload")
                // will store only one time in Audit even on connection failure
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonRestoreFlow1").endChoice().end()
                .to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonRestoreFlow1")
                .doTry()
                .to("direct:VerizonRestoreFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:VerizonRestoreFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonRestoreDevicePreProcessor())
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor(env));

        // Kore Flow-1

        from("direct:processRestoreKoreTransaction")
                .log("Wire Tap Thread restore")
                .bean(iTransactionalService, "populateRestoreDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaRestore?concurrentConsumers=5")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService,
                        "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()
                .process(new KoreRestoreDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(uriRestKoreEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson, KoreProvisoningResponse.class)
                .bean(iTransactionalService,
                        "populateKoreTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall");
        
        
		// ATTJASPER Flow-1
		
	    from("direct:processRestoreDeviceATTJasperTansaction")
	                .log("Wire Tap Thread deactivation")
	                .bean(iTransactionalService, "populateRestoreDBPayload")
	                .split().method("deviceSplitter").recipientList()
	                .method("attJasperDeviceServiceRouter");
	        
	     // ATTJASPER SEDA FLOW
	        
	        from("seda:attJasperSedaRestore?concurrentConsumers=5")
	        .onException(SoapFault.class)
	        .handled(true)
	        .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
	        .bean(iTransactionalService,
	                "populateATTJasperTransactionalErrorResponse")
	        .end()
	        .process(new ATTJasperRestoreDevicePreProcessor(env))
	        .bean(iAuditService, "auditExternalRequestCall")
	        .to(attJasperTerminalEndPoint)
	       
	        .bean(iAuditService, "auditExternalSOAPResponseCall")
	        .bean(iTransactionalService,
	                "populateATTJasperTransactionalResponse");

        // End:Restore Devices
    }

    /**
     * Suspend Flow for Verizon and Kore
     */

    public void suspendDevice() {

        // Begin:Suspend Devices

        from("direct:suspendDevice").process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .process(new StubKoreSuspendDeviceProcessor()).to("log:input")
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonSuspendDeviceProcessor())
                .to("log:input")
                
                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.process(new StubATTJasperSuspendDeviceProcessor())
				.to("log:ATTJASPER")
                
                
                .endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processSuspendKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor(env))
                .endChoice().
                
                     when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
					 .wireTap("direct:processSuspendDeviceATTJasperTansaction")
					 .process(new CarrierProvisioningDevicePostProcessor(env))
					.endChoice()	 
                
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateSuspendDBPayload")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonSuspendFlow1")

                .endChoice().end().to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonSuspendFlow1")
                .doTry()
                .to("direct:VerizonSuspendFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2

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
                .process(new CarrierProvisioningDevicePostProcessor(env));

        // Kore Flow-1

        from("direct:processSuspendKoreTransaction")
                .log("Wire Tap Thread suspension")
                .bean(iTransactionalService, "populateSuspendDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

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
                .json(JsonLibrary.Jackson, KoreProvisoningResponse.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iTransactionalService,
                        "populateKoreTransactionalResponse");
        
        
				// ATTJASPER Flow-1
			        		
			    from("direct:processSuspendDeviceATTJasperTansaction")
			                .log("Wire Tap Thread deactivation")
			                .bean(iTransactionalService, "populateSuspendDBPayload")
			                .split().method("deviceSplitter").recipientList()
			                .method("attJasperDeviceServiceRouter");
			        
			     // ATTJASPER SEDA FLOW
			        
			        from("seda:attJasperSedaSuspend?concurrentConsumers=5")
			        .onException(SoapFault.class)
			        .handled(true)
			        .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
			        .bean(iTransactionalService,
			                "populateATTJasperTransactionalErrorResponse")
			        .end()
			        .process(new ATTJasperSuspendDevicePreProcessor(env))
			        .bean(iAuditService, "auditExternalRequestCall")
			        .to(attJasperTerminalEndPoint)
			       
			        .bean(iAuditService, "auditExternalSOAPResponseCall")
			        .bean(iTransactionalService,
			                "populateATTJasperTransactionalResponse");

        // End:Suspend Devices

    }

    /**
     * Reactivate Flow for Kore
     */
    public void reactivateDevice() {

        // Begin:Reactivate Devices

        from("direct:reactivateDevice").process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .log("message" + header("derivedSourceName"))
                .process(new StubKoreReactivateDeviceProcessor())
                .to("log:input")
                
                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.process(new StubATTJasperReactivateDeviceProcessor())
				.to("log:ATTJASPER")
                
                .endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processReactivateKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor(env))
                .endChoice().
                
				when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.wireTap("direct:processReactivateDeviceATTJasperTansaction")
				.process(new CarrierProvisioningDevicePostProcessor(env))
				.endChoice()

				.end().to("log:input").endChoice().end();

        // Kore Flow-1

        from("direct:processReactivateKoreTransaction")
                .log("Wire Tap Thread reactivation")
                .bean(iTransactionalService, "populateReactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaReactivation?concurrentConsumers=5")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService,
                        "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()
                .process(new KoreReactivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(uriRestKoreEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson, KoreProvisoningResponse.class)
                .bean(iTransactionalService,
                        "populateKoreTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall");
        
        
        
       	// ATTJASPER Flow-1
        

        from("direct:processReactivateDeviceATTJasperTansaction")
                .log("Wire Tap Thread reactivation")
                .bean(iTransactionalService, "populateReactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");
        
        // ATTJASPER SEDA FLOW
        
        from("seda:attJasperSedaReactivation?concurrentConsumers=5")
        .onException(SoapFault.class)
        .handled(true)
        .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
        .bean(iTransactionalService,
                "populateATTJasperTransactionalErrorResponse")
        .end()
        .process(new ATTJasperReactivateDevicePreProcessor(env))
        .bean(iAuditService, "auditExternalRequestCall")
        .to(attJasperTerminalEndPoint)
       
        .bean(iAuditService, "auditExternalSOAPResponseCall")
        .bean(iTransactionalService,
                "populateATTJasperTransactionalResponse");



        // End:Reactivate Devices

    }

    /**
     * Custom Fields Flow for Verizon and Kore
     */
    public void customeFields() {

        // Begin:CustomeFields Devices

        from("direct:customeFields").process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .process(new StubKoreCustomFieldsProcessor()).to("log:input")
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonCustomFieldsProcessor())
                .to("log:input").endChoice().otherwise().choice()

                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processcustomeFieldsKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor(env))

                .endChoice()
                
                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processCustomFieldATTJasperTansaction")
                .process(new CarrierProvisioningDevicePostProcessor(env))
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateCustomeFieldsDBPayload")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizoncustomeFieldsFlow1").endChoice().end()
                .to("log:input").endChoice().end();

        // Verizon Flow-1
        from("direct:VerizoncustomeFieldsFlow1")
                .doTry()
                .to("direct:VerizoncustomeFieldsFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2
        from("direct:VerizoncustomeFieldsFlow2")
                .errorHandler(noErrorHandler())

                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonCustomFieldsPreProcessor())
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor(env));

        // Kore Flow-1

        from("direct:processcustomeFieldsKoreTransaction")
                .log("Wire Tap Thread customeField")
                .bean(iTransactionalService, "populateCustomeFieldsDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedacustomeFields?concurrentConsumers=5")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService,
                        "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()

                .process(new KoreCustomFieldsPreProcessor(env))
                .log("KoreCustomFieldsUpdatePreProcessor-----------")
                .bean(iAuditService, "auditExternalRequestCall")
                .to(uriRestKoreEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson, DKoreResponseCode.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iTransactionalService,
                        "populateKoreTransactionalResponse");
        
        
        // ATTJASPER Flow-1       

        from("direct:processCustomFieldATTJasperTansaction")
                .log("Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateATTCustomeFieldsDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");
        
        // ATTJASPER SEDA FLOW
        
        from("seda:attJasperSedaCustomeFields?concurrentConsumers=5")
        .onException(SoapFault.class)
        .handled(true)
        .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
        .bean(iTransactionalService,
                "populateATTJasperTransactionalErrorResponse")
        .end()
        .process(new ATTJasperCustomFieldDevicePreProcessor(env))
        .bean(iAuditService, "auditExternalRequestCall")
        .to(attJasperTerminalEndPoint)
       
        .bean(iAuditService, "auditExternalSOAPResponseCall")
        .bean(iTransactionalService,
                "populateATTJasperTransactionalResponse");


        
        

        // End:CustomeFields Devices

    }

    /**
     * Change Service Plan Flow for Verizon and Kore
     */

    public void changeDeviceServicePlans() {

        // Change Device ServicePlans

        from("direct:changeDeviceServicePlans")
                .process(new HeaderProcessor()).process(new ChangeDeviceServicePlanValidatorProcessor())
                .choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .process(new StubKoreChangeDeviceServicePlansProcessor())
                .to("log:input")
                
				.when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.process(new StubATTJasperChangeDeviceServicePlansProcessor())
				.to("log:ATTJASPER")
                
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonChangeDeviceServicePlansProcessor())
                .to("log:input")
                .endChoice()
                .otherwise()
                .choice()

                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap(
                        "direct:processchangeDeviceServicePlansKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor(env))
                .endChoice()
                
				. when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				 .wireTap("direct:processChangeDeviceServicePlansATTJasperTansaction")
				 .process(new CarrierProvisioningDevicePostProcessor(env))
				.endChoice()	

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService,
                        "populateChangeDeviceServicePlansDBPayload")
                .bean(iAuditService, "auditExternalRequestCall")

                .to("direct:VerizonchangeDeviceServicePlansFlow1").endChoice()
                .end().to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonchangeDeviceServicePlansFlow1")
                .doTry()
                .to("direct:VerizonchangeDeviceServicePlansFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:VerizonchangeDeviceServicePlansFlow2")
                .errorHandler(noErrorHandler())
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonChangeDeviceServicePlansPreProcessor())
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor(env));

        // Kore Flow-1

        from("direct:processchangeDeviceServicePlansKoreTransaction")
                .log("Wire Tap Thread ChangeDeviceServicePlans")
                .bean(iTransactionalService,
                        "populateChangeDeviceServicePlansDBPayload").split()
                .method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedachangeDeviceServicePlans?concurrentConsumers=5")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService,
                        "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()

                .process(new KoreChangeDeviceServicePlansPreProcessor(env))
                .log("KoreChangeDeviceServicePlansPreProcessor-----------")
                .bean(iAuditService, "auditExternalRequestCall")
                .to(uriRestKoreEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson, DKoreResponseCode.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iTransactionalService,
                        "populateKoreTransactionalResponse");
        
        
        
		       	// ATTJASPER Flow-1
		        
		
		        from("direct:processChangeDeviceServicePlansATTJasperTansaction")
		                .log("Wire Tap Thread deactivation")
		                .bean(iTransactionalService, "populateChangeDeviceServicePlansDBPayload")
		                .split().method("deviceSplitter").recipientList()
		                .method("attJasperDeviceServiceRouter");
		        
		        // ATTJASPER SEDA FLOW
		       
		        from("seda:attJasperSedaChangeDeviceServicePlans?concurrentConsumers=5")
		        .onException(SoapFault.class)
		        .handled(true)
		        .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
		        .bean(iTransactionalService,
		                "populateATTJasperTransactionalErrorResponse")
		        .end()
		        .process(new ATTJasperChangeDeviceServicePlansPreProcessor(env))
		        .bean(iAuditService, "auditExternalRequestCall")
		        .to(attJasperTerminalEndPoint)
		       
		        .bean(iAuditService, "auditExternalSOAPResponseCall")
		        .bean(iTransactionalService,
		                "populateATTJasperTransactionalResponse");
        

        // End: Change Device ServicePlans

    }

    /**
     * deviceConnectionStatus Flow for Verizon
     */

    public void deviceConnectionStatus() {

        // Begin:Device Connection Status

        from("direct:deviceConnectionStatus").process(new HeaderProcessor())
                .process(new DateValidationProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .process(new StubVerizonDeviceConnectionStatusProcessor())
                .to("log:input").endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:DeviceConnectionStatusFlow1").endChoice().end();

        // Verizon Flow-1

        from("direct:DeviceConnectionStatusFlow1").doTry()
                .to("direct:DeviceConnectionStatusFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:DeviceConnectionStatusFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceConnectionInformationPreProcessor())
                // Audit will store record 3 times in case of failure (see
                // onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .to(uriRestVerizonEndPoint).unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new VerizonDeviceConnectionStatusPostProcessor(env));

        // End:Device Connection Status

    }

    /**
     * deviceConnectionStatus Flow for Verizon
     */
    public void deviceSessionBeginEndInfo() {

        // Begin: Device Session Begin End

        from("direct:deviceSessionBeginEndInfo").process(new HeaderProcessor())
                .process(new DateValidationProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .process(new StubVerizonDeviceSessionBeginEndInfoProcessor())
                .to("log:input").endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:DeviceSessionBeginEndInfoFlow1").endChoice().end();

        // Verizon Flow-1
        from("direct:DeviceSessionBeginEndInfoFlow1").doTry()
                .to("direct:DeviceSessionBeginEndInfoFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();
        // Verizon Flow-2
        from("direct:DeviceSessionBeginEndInfoFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceConnectionInformationPreProcessor())
                // Audit will store record 3 times in case of failure (see
                // onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new VerizonDeviceSessionBeginEndInfoPostProcessor(env));

        // End: Device Session Begin End
    }

    /**
     * Get DeviceInformation from Carrier and update in MasterDB and return back
     * to Calling System.
     **/
    public void deviceInformationCarrier() {

        from("direct:deviceInformationCarrier").process(new HeaderProcessor())
                .process(new NetSuiteIdValidationProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .process(new StubKoreDeviceInformationProcessor())
                .to("log:input")
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonDeviceInformationProcessor())
                .to("log:input").endChoice().otherwise().choice()

                .when(header("derivedCarrierName").isEqualTo("KORE")).doTry()
                .process(new KoreDeviceInformationPreProcessor(env))
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
                .endChoice().
                
                 when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .doTry().process(new ATTJasperDeviceInformationPreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall").
                 to(attJasperTerminalEndPoint)
                 .bean(iAuditService, "auditExternalSOAPResponseCall")
                 .bean(iDeviceService, "setDeviceInformationDB")
                 .process(new ATTJasperDeviceInformationPostProcessor())
                 .bean(iDeviceService, "updateDeviceInformationDB")
                 .doCatch(SoapFault.class)
                 .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                 .process(new ATTJasperGenericExceptionProcessor(env))
                  .endDoTry().endChoice()


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
    }

    /**
     * Get DeviceInformation from MasterDB and return back to Calling System.
     **/
    public void getDeviceInformationDB() {
        from("direct:getDeviceInformationDB").process(new HeaderProcessor())
                .bean(iDeviceService, "getDeviceInformationDB").to("log:input")
                .end();

    }

    /** Insert or Update Single Device details in MasterDB **/

    public void updateDeviceDetails() {
        from("direct:updateDeviceDetails").process(new HeaderProcessor())
                .choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .process(new StubCellUploadProcessor()).endChoice().otherwise()
                .bean(iDeviceService, "updateDeviceDetails").end();
    }

    /** Insert or Upload Batch Device details in MasterDB. **/
    public void updateDevicesDetailsBulk() {

        from("direct:updateDevicesDetailsBulk").process(new HeaderProcessor())
                .choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .process(new StubCellBulkUploadProcessor()).endChoice()
                .otherwise().to("direct:updateDevicesDetailsBulkActual").end();

        from("direct:updateDevicesDetailsBulkActual").onCompletion()
                .modeBeforeConsumer().setBody().body()
                .process(new BulkDeviceProcessor()).end()
                .bean(iDeviceService, "updateDevicesDetailsBulk").split()
                .method("bulkOperationSplitter").parallelProcessing().recipientList()
                .method("bulkOperationServiceRouter");

        /**
         * Bulk Insert or Update the device in MasterDB using Seda
         */
        from("seda:bulkOperationDeviceSyncInDB?concurrentConsumers=5").bean(
                iDeviceService, "bulkOperationDeviceSyncInDB");
    }

    /**
     * Method to Schedule the Veriozon Device Connection History Job
     */
    public void deviceConnectionHistoryVerizonJob() {

        from(
                "quartz2://job/deviceDetailsConnectionTimerVerizon?cron="
                        + IConstant.JOB_TIME_CONFIGURATION)
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.VERIZON.toString() + ", "
                                + JobName.VERIZON_CONNECTION_HISTORY + ")")
                .bean(iJobService, "scheduleJob").end();

    }

    /**
     * Method to Schedule the Veriozon Device Usage Job
     */
    public void deviceUsageHistoryVerizonJob() {

        from(
                "quartz2://job/deviceDetailsUsageTimerVerizon?cron="
                        + IConstant.JOB_TIME_CONFIGURATION)
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.VERIZON.toString() + ", "
                                + JobName.VERIZON_DEVICE_USAGE + ")")
                .bean(iJobService, "scheduleJob").end();

    }

    /**
     * Method to Schedule the Kore Device Usage Job
     */
    public void deviceUsageHistoryKoreJob() {

        from(
                "quartz2://job/deviceDetailsUsageTimerKore?cron="
                        + IConstant.JOB_TIME_CONFIGURATION)
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.KORE.toString() + ", "
                                + JobName.KORE_DEVICE_USAGE + ")")
                .bean(iJobService, "scheduleJob").end();

    }

    /**
     * Method contains flow of the batch jobs
     */

    public void startJob() {

        from("direct:startJob").to("direct:processJob");

        // Job Flow-1

        from("direct:processJob").
        onException(ExchangeTimedOutException.class).handled(true).log(LoggingLevel.INFO,
                "TimeOut Exception for Batch Jon").process(new TimeOutErrorProcessor(env)).end()
                .onCompletion().bean(iJobService, "checkTimeOutDevices")
                .bean(iJobService, "updateJobDetails").
                // Notification Not required.
                /*// Run the Notification Job for Self triggered Batch job of Device Usage
                 choice()
                .when(simple("${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE' || ${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE' && ${exchangeProperty[jobType]} == 'NEW'") ).
                bean(iJobService,
                        "setJobDetails(${exchange},${exchangeProperty[carrierName]},"+JobName.DEVICE_USAGE_NOTIFICATION+")")
                .to("direct:notificationJob").endChoice().end().*/
                 end()
                .bean(iJobService, "insertJobDetails")
                .bean(iJobService, "setJobStartandEndTime")
                .bean(iJobService, "fetchDevices")

                // Deleting Existing Records
                .choice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_CONNECTION_HISTORY'"))
                .bean(iJobService, "deleteDeviceConnectionHistoryRecords")
                .when(simple("${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE'"))
                .bean(iJobService, "deleteDeviceUsageRecords")
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE'"))
                .bean(iJobService, "deleteDeviceUsageRecords")
                .endChoice()
                .end()

                // Fetch List Forwarded to Respective SEDA

                .choice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_CONNECTION_HISTORY'"))
                .split()
                .method("jobSplitter").parallelProcessing()
                .to("seda:processVerizonConnectionHistoryJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE'"))
                .split()
                .method("jobSplitter").parallelProcessing()
                .to("seda:processKoreDeviceUsageJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE'"))
                .split().method("jobSplitter").parallelProcessing()
                .to("seda:processVerizonDeviceUsageJob").endChoice();

        // KORE Job-DEVICE USAGE
        from("seda:processKoreDeviceUsageJob?concurrentConsumers=10")
                .log("KOREJob-DEVICE USAGE")

                .doTry()
                .process(new KoreDeviceUsageHistoryPreProcessor(env))
                .to(uriRestKoreEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)

                .process(new KoreDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,SocketTimeoutException.class,NoRouteToHostException.class,KoreSimMissingException.class)
                .process(new KoreBatchExceptionProcessor(env))
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job-DEVICE USAGE
        from("seda:processVerizonDeviceUsageJob?concurrentConsumers=10")
                .log("VERIZONJob-DEVICE USAGE")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceUsageHistoryPreProcessor())
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .process(new VerizonDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class,ConnectException.class,SocketTimeoutException.class,NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor(env))
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job CONNECTION HISTORY
        from("seda:processVerizonConnectionHistoryJob?concurrentConsumers=10")
                .log("VERIZONJob CONNECTION HISTORY")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceConnectionHistoryPreProcessor())
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .process(new VerizonDeviceConnectionHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class,ConnectException.class,SocketTimeoutException.class,NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor(env))
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .endDoTry();

    }

    /**
     * Method contains flow of the transaction failure job
     */

    public void transactionFailureJob() {

        from("direct:startTransactionFailureJob").to(
                "direct:processTransactionFailureJob");

        // Job Flow-1

        from("direct:processTransactionFailureJob").onException(ExchangeTimedOutException.class).handled(true).log(LoggingLevel.INFO,
                "TimeOut Exception for Batch Jon").process(new TimeOutErrorProcessor(env)).end()
                .onCompletion().bean(iJobService, "checkTimeOutDevicesTransactionFailure")
                .bean(iJobService, "updateJobDetails")
                .end()
                .bean(iJobService, "insertJobDetails")
                .bean(iJobService, "setJobStartandEndTime")
                .bean(iJobService, "fetchTransactionFailureDevices")

                // Deleting Transaction Failure Existing Records
                .choice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_CONNECTION_HISTORY'"))
                .bean(iJobService,
                        "deleteTransactionFailureDeviceConnectionHistoryRecords")
                .when(simple("${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE'"))
                .bean(iJobService, "deleteTransactionFailureDeviceUsageRecords")
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE'"))
                .bean(iJobService, "deleteTransactionFailureDeviceUsageRecords")
                .endChoice()
                .end()

                // Fetch List Forwarded to Respective SEDA

                .choice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_CONNECTION_HISTORY'"))
                .split()
                .method("jobSplitter").parallelProcessing()
                .to("seda:processTransactionFailureVerizonConnectionHistoryJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE'"))
                .split()
                .method("jobSplitter").parallelProcessing()
                .to("seda:processTransactionFailureKoreDeviceUsageJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE'"))
                .split().method("jobSplitter").parallelProcessing()
                .to("seda:processTransactionFailureVerizonDeviceUsageJob")
                .endChoice();

        // KORE Job-DEVICE USAGE
        from(
                "seda:processTransactionFailureKoreDeviceUsageJob?concurrentConsumers=10")
                .log("KOREJob-DEVICE USAGE")

                .doTry()
                .process(
                        new KoreTransactionFailureDeviceUsageHistoryPreProcessor(
                                env))
                .to(uriRestKoreEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .process(new KoreDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,SocketTimeoutException.class,NoRouteToHostException.class,KoreSimMissingException.class)
                .process(new KoreBatchExceptionProcessor(env))
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job-DEVICE USAGE
        from(
                "seda:processTransactionFailureVerizonDeviceUsageJob?concurrentConsumers=10")
                .log("VERIZONJob-DEVICE USAGE")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(
                        new VerizonTransactionFailureDeviceUsageHistoryPreProcessor())
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .process(new VerizonDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class,ConnectException.class,SocketTimeoutException.class,NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor(env))
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job CONNECTION HISTORY
        from(
                "seda:processTransactionFailureVerizonConnectionHistoryJob?concurrentConsumers=10")
                .log("VERIZONJob CONNECTION HISTORY")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(
                        new VerizonTransactionFailureDeviceConnectionHistoryPreProcessor())
                .to(uriRestVerizonEndPoint)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .process(new VerizonDeviceConnectionHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class,ConnectException.class,SocketTimeoutException.class,NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor(env))
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .endDoTry();

    }

    /**
     * This Method is used to retrieve device Usage History
     */
    public void retrieveDeviceUsageHistoryCarrier() {
        from("direct:retrieveDeviceUsageHistoryCarrier")
                .process(new HeaderProcessor())
                .process(new DateValidationProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice()
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonRetrieveDeviceUsageHistoryProcessor())
                .to("log:input").endChoice().otherwise().choice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                // will store only one time in Audit even on connection failure
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonretrieveDeviceUsageHistoryFlow1")
                .endChoice().end();

        // Verizon Flow-1
        from("direct:VerizonretrieveDeviceUsageHistoryFlow1").doTry()
                .to("direct:VerizonretrieveDeviceUsageHistoryFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor(env)).endDoTry()
                .end();

        // Verizon Flow-2
        from("direct:VerizonretrieveDeviceUsageHistoryFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new RetrieveDeviceUsageHistoryPreProcessor())
                .to(uriRestVerizonEndPoint).unmarshal()
                .json(JsonLibrary.Jackson)
                /*
                 * .bean(iTransactionalService,
                 * "populateRetrieveDeviceUsageHistoryDBPayload")
                 */
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new RetrieveDeviceUsageHistoryPostProcessor(env));

    }

    

    //Not in Use.
    /**
     * Notification Job
     */

   /* public void notificationJob() {

        // Job Flow-1

        from("direct:notificationJob").bean(iJobService, "insertJobDetails")
                .bean(iJobService, "fetchDevices")
                .bean(iJobService, "addNotificationList").split()
                .method("jobSplitter")
                .to("direct:processDeviceUsageNotification");

        from("direct:processDeviceUsageNotification")
                .onCompletion()
                // .onWhen(property("CamelSplitComplete").isEqualTo("true"))
                .onWhen(simple("${exchangeProperty[CamelSplitComplete]} == 'true'"))
                .bean(iJobService, "updateJobDetails")
                .bean(iJobService, "checkNotificationList").end()
                .to("seda:processDeviceUsageNotificationJob");

        // NOTIFICATION Job-DEVICE USAGE
        from("seda:processDeviceUsageNotificationJob?concurrentConsumers=5")
                .doTry().bean(iJobService, "processDeviceNotification")
                .doCatch(CxfOperationException.class).endDoTry();

    }*/

    public void jobResponse() {
        from("direct:jobResponse").log("Inside the Job response").process(
                new JobInitializedPostProcessor());
    }

    // Get Device Usage from Midway by start date to end date .
    public void getDeviceUsageInfoDB() {
        from("direct:getDeviceUsageInfoDB")
                .log("Inside the getDeviceUsageInfoDB")
                .process(new HeaderProcessor())
                .bean(iDeviceService, "getDeviceUsageInfoDB").end();

    }

 // Get Device Connection from Midway by start date to end date .
    public void getDeviceConnectionHistoryInfoDB() {

        from("direct:getDeviceConnectionHistoryInfoDB")
                .process(new HeaderProcessor())
                .bean(iDeviceService, "getDeviceConnectionHistoryInfoDB").end();
    }
    
   // Get Device usage of all the devices by date and carrier from Midway.
    public void getDevicesUsageByDayAndCarrierInfoDB() {
        from("direct:getDevicesUsageByDayAndCarrierInfoDB")
                .log("Inside the getDevicesUsageByDayAndCarrierInfoDB")
                .process(new HeaderProcessor())
                .bean(iDeviceService, "getDevicesUsageByDayAndCarrierInfoDB").end();

    }


    /**
     * saving callbacks from verizon into MongoDB and and sending it to NetSuite
     * and Kafka
     * 
     * */
    public void callbacks() {
        from("direct:callbacks")
                .bean(iTransactionalService, "populateCallbackDBPayload")
                .process(new CallbackPreProcessor(env))
                .bean(iTransactionalService, "findMidwayTransactionId")
                .doTry()
                .process(new CallbackPostProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to(uriRestNetsuitEndPoint)
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .log("kafka topic message"
                        + simple("${exchangeProperty[topicName]}").getText())
                .onWhen(simple("${exchangeProperty[topicName]} == 'midway-alerts'"))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-alerts")
                .onWhen(simple("${exchangeProperty[topicName]} == 'midway-app-errors'"))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-app-errors").end();
    }

    /**
     * Get all the Kore devices with carrier status pending or error and Midway
     * status as Pending from TransactionDB
     */
    public void koreCheckStatusTimer() {
        // TODO Auto-generated method stub
        from("timer://koreCheckStatusTimer?period=5m")
                .bean(iTransactionalService, "populatePendingKoreCheckStatus")
                .split().method("checkStatusSplitter").parallelProcessing().recipientList()
                .method("koreCheckStatusServiceRouter");

        /**
         * Check status of all the Kore devices with carrier status pending or
         * error and updated it in TransactionDB if it is completed or error
         * from Kore and call the netsuiteEndpoint for them.
         */

        from("seda:koreSedaCheckStatus?concurrentConsumers=5")
                /**
                 * If any exception comes while calling the Kore check status
                 * then send it back to netsuite endpoint and write in Kafka
                 * Queue.
                 **/
                .onException(CxfOperationException.class)
                .handled(true)
                .to("direct:koreCheckStatusErrorSubProcess")
                .end()

                .onException(UnknownHostException.class, ConnectException.class)
                .handled(true)
                .bean(iTransactionalService,
                        "populateKoreCheckStatusConnectionResponse")
                .end()

                .process(new KoreCheckStatusPreProcessor(env))
                .choice()
                .
                /**
                 * Now call the netsuite end point for error and write in Kafka
                 * Queue.
                 */
                when(header(IConstant.KORE_CHECK_STATUS).isEqualTo("error"))
                .to("direct:koreCheckStatusErrorSubProcess")
                .

                // now call the netsuite end point for changeServicePlan and
                // changeCustomeFields.

                when(header(IConstant.KORE_CHECK_STATUS).isEqualTo("change"))
                .to("direct:koreCustomChangeSubProcess").
                /**
                 * Call the Kore API to check the status of device
                 */
                when(header(IConstant.KORE_CHECK_STATUS).isEqualTo("forward"))
                .to(uriRestKoreEndPoint).unmarshal()
                .json(JsonLibrary.Jackson, KoreCheckStatusResponse.class)
                .to("direct:koreCheckStatusSubProcess").endChoice();

        from("direct:koreCheckStatusErrorSubProcess")
                .bean(iTransactionalService,
                        "populateKoreCheckStatusErrorResponse")
                .doTry()
                .process(new KoreCheckStatusErrorProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to(uriRestNetsuitEndPoint)
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-app-errors").end();

        from("direct:koreCustomChangeSubProcess")
                .bean(iTransactionalService, "populateKoreCustomChangeResponse")
                .doTry()
                .process(new KoreCheckStatusPostProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to(uriRestNetsuitEndPoint)
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-alerts").end();

        from("direct:koreCheckStatusSubProcess")
                .bean(iTransactionalService, "populateKoreCheckStatusResponse")
                .choice()
                .when(header(IConstant.KORE_PROVISIONING_REQUEST_STATUS)
                        .isEqualTo(IConstant.KORE_CHECKSTATUS_COMPLETED))
                .doTry()
                .process(new KoreCheckStatusPostProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to(uriRestNetsuitEndPoint)
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-alerts").end();
    }

}
