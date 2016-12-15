package com.gv.midway.router;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;
import com.gv.midway.pojo.job.JobCompletionCallBacktoNetSuite;
import com.gv.midway.pojo.usageInformation.kore.response.UsageInformationKoreResponse;
import com.gv.midway.pojo.usageInformation.verizon.response.VerizonUsageInformationResponse;
import com.gv.midway.processor.*;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeTimedOutException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.camel.model.TryDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gv.midway.constant.CarrierType;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.exception.KoreSimMissingException;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.checkstatus.kore.KoreCheckStatusResponse;
import com.gv.midway.pojo.deviceInformation.kore.response.DeviceInformationResponseKore;
import com.gv.midway.pojo.deviceInformation.verizon.response.DeviceInformationResponseVerizon;
import com.gv.midway.pojo.kore.DKoreResponseCode;
import com.gv.midway.pojo.kore.KoreProvisioningResponse;
import com.gv.midway.pojo.token.VerizonAuthorizationResponse;
import com.gv.midway.pojo.token.VerizonSessionLoginResponse;
import com.gv.midway.processor.activateDevice.ATTJasperActivateDevicePreProcessor;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePreProcessor;
import com.gv.midway.processor.activateDevice.KoreActivationWithCustomFieldErrorProcessor;
import com.gv.midway.processor.activateDevice.KoreActivationWithCustomFieldPreProcessor;
import com.gv.midway.processor.activateDevice.KoreActivationWithCustomFieldProcessor;
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
import com.gv.midway.processor.checkstatus.AttCallBackErrorPostProcessor;
import com.gv.midway.processor.checkstatus.AttCallBackPreProcessor;
import com.gv.midway.processor.checkstatus.AttCallBackSuccessPostProcessor;
import com.gv.midway.processor.checkstatus.KoreCheckStatusErrorProcessor;
import com.gv.midway.processor.checkstatus.KoreCheckStatusPostProcessor;
import com.gv.midway.processor.checkstatus.KoreCheckStatusPreProcessor;
import com.gv.midway.processor.connectionInformation.VerizonDeviceConnectionInformationPreProcessor;
import com.gv.midway.processor.connectionInformation.deviceConnectionStatus.ATTJasperDeviceConnectionStatusPostProcessor;
import com.gv.midway.processor.connectionInformation.deviceConnectionStatus.StubATTJasperDeviceConnectionStatusProcessor;
import com.gv.midway.processor.connectionInformation.deviceConnectionStatus.StubVerizonDeviceConnectionStatusProcessor;
import com.gv.midway.processor.connectionInformation.deviceConnectionStatus.VerizonDeviceConnectionStatusPostProcessor;
import com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo.ATTJasperDeviceSessionBeginEndInfoPostProcessor;
import com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo.ATTJasperDeviceSessionBeginEndInfoPreProcessor;
import com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo.StubATTJasperDeviceSessionBeginEndInfoProcessor;
import com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo.StubVerizonDeviceSessionBeginEndInfoProcessor;
import com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo.VerizonDeviceSessionBeginEndInfoPostProcessor;
import com.gv.midway.processor.customFieldsDevice.ATTJasperCustomFieldDevicePreProcessor;
import com.gv.midway.processor.customFieldsDevice.KoreCustomFieldsPreProcessor;
import com.gv.midway.processor.customFieldsDevice.StubATTJasperCustomFieldsProcessor;
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
import com.gv.midway.processor.deviceInformation.StubATTJasperInformationProcessor;
import com.gv.midway.processor.deviceInformation.StubKoreDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.StubVerizonDeviceInformationProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPostProcessor;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPreProcessor;
import com.gv.midway.processor.jobScheduler.ATTJasperDeviceUsageHistoryPostProcessor;
import com.gv.midway.processor.jobScheduler.ATTJasperDeviceUsageHistoryPreProcessor;
import com.gv.midway.processor.jobScheduler.JobCallBackProcessor;
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
                .bean(iTransactionalService, "populateConnectionErrorResponse(${exchange},CONNECTION_ERROR)")
                .bean(iAuditService, "auditExternalConnectionExceptionResponseCall")
                .process(new GenericErrorProcessor());

        onException(VerizonSessionTokenExpirationException.class)
                .routeId("ConnectionLoginExceptionRoute").handled(true)
                .log(LoggingLevel.ERROR, "Connection Error")
                .onRedelivery(new TokenProcessor()).maximumRedeliveries(2)
                .redeliveryDelay(1000)
                // The control will come to this processor when all attempts
                // have been failed
                .process(new GenericErrorProcessor());

        onException(InvalidParameterException.class).handled(true)
                .log(LoggingLevel.ERROR, "Invalid Parameter Passed Error")
                .process(new GenericErrorProcessor());

        onException(MissingParameterException.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "Pass all the required header parameters")
                .process(new GenericErrorProcessor());

        ((TryDefinition) from("direct:tokenGeneration").doTry()
                .bean(iSessionService, "checkToken").choice()
                .when(body().contains("true"))
                .log(LoggingLevel.DEBUG, "MATCH -REFETCHING ")
                .process(new VerizonAuthorizationTokenProcessor(env))
                .to(IEndPoints.URI_REST_VERIZON_TOKEN_ENDPOINT).unmarshal()
                .json(JsonLibrary.Jackson, VerizonAuthorizationResponse.class)
                .process(new VerizonSessionTokenProcessor(env))
                .to(IEndPoints.URI_REST_VERIZON_TOKEN_ENDPOINT).unmarshal()
                .json(JsonLibrary.Jackson, VerizonSessionLoginResponse.class)
                .process(new VerizonSessionAttributeProcessor())
                .bean(iSessionService, "setVzToken")
                . // saved this token in the DB
                endChoice().otherwise().log(LoggingLevel.DEBUG, "NOT MATCH")
                .to("log:input").end()
                // sync DB and session token in the ServletContext
                .bean(iSessionService, "synchronizeDBContextToken"))
                .doCatch(Exception.class)
                .log(LoggingLevel.ERROR, "Exception while token generation")
                .doFinally().bean(iSessionService, "setTokenRequired").end();

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

        // Calling the KeyValuePair Device Flow
        customFields();

        // Calling the change Device ServicePlans Flow
        changeDeviceServicePlans();

        // Calling the Device Connection Status Flow
        deviceConnectionStatus();

        // Calling the Device in Session Flow
        deviceSessionBeginEndInfo();

        // Calling the getDeviceInformationFromCarrier
        deviceInformationCarrier();

        // Calling the getDeviceInformationDB
        getDeviceInformationDB();

        // Insert or Update Single Device Detail
        updateDeviceDetails();

        // Insert or Update Device Details in Bulk
        updateDevicesDetailsBulk();

        // Retrieve Device Usage History from Carrier
        retrieveDeviceUsageHistoryCarrier();

        // Execution of scheduled jobs scheduledJobs

        deviceConnectionHistoryVerizonJob24();

        deviceUsageHistoryVerizonJob24();

        deviceUsageHistoryKoreJob24();
        
        deviceUsageHistoryATTJasperJob24();

        deviceUsageHistoryVerizonJob48();
        
        deviceUsageHistoryKoreJob48();

        deviceUsageHistoryVerizonJob72();

        deviceUsageHistoryKoreJob72();

        deviceUsageHistoryKoreJob96();

        deviceUsageHistoryKoreJob120();

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

        //ATT Timer for invoking Callback to NetSuite
        attCallBackTimer();

        //New device session usage
        deviceSessionUsage();

         //New device session information
         deviceSessionInformation();

         netSuiteCallback();

    }

    private void netSuiteCallback() {
        from("direct:netSuiteCallback").routeId("netSuiteCallback")
                .choice()
                .when(body().isInstanceOf(JobCompletionCallBacktoNetSuite.class))
                .log(LoggingLevel.INFO, "==> NetSuiteCallback[${exchangeProperty[script]}] JobId: ${exchangeProperty[jobId]} JobName: ${exchangeProperty[jobName]} Carrier: ${exchangeProperty[jobCarrier]} Type: ${exchangeProperty[jobType]}")
                .to(IEndPoints.URI_REST_NETSUITE_ENDPOINT)
                .log(LoggingLevel.INFO, "<== NetSuiteCallback[${exchangeProperty[script]}] JobId: ${exchangeProperty[jobId]} JobName: ${exchangeProperty[jobName]} Carrier: ${exchangeProperty[jobCarrier]} Type: ${exchangeProperty[jobType]}")
                .otherwise()
                .log(LoggingLevel.INFO, "==> NetSuiteCallback[${exchangeProperty[script]}] NetSuiteId: ${exchangeProperty[" + IConstant.MIDWAY_NETSUITE_ID + "]} RequestType: ${exchangeProperty[" + IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE + "]}")
                .to(IEndPoints.URI_REST_NETSUITE_ENDPOINT)
                .log(LoggingLevel.INFO, "<== NetSuiteCallback[${exchangeProperty[script]}] NetSuiteId: ${exchangeProperty[" + IConstant.MIDWAY_NETSUITE_ID + "]} RequestType: ${exchangeProperty[" + IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE + "]}")
                .endChoice();
    }

    public void deviceSessionUsage() {
        from("direct:deviceSessionUsage").routeId("deviceSessionUsage")
                .bean(iDeviceService, "getDeviceSessionUsage").id("getDeviceSessionUsage")
                .to("direct:retrieveDeviceUsageHistoryCarrier");
    }

    public void deviceSessionInformation() {
        from("direct:deviceSessionInfo").routeId("deviceSessionInfo")
                .bean(iDeviceService, "getDeviceSessionInfo").id("getDeviceSessionInfo")
                .to("direct:deviceSessionBeginEndInfo");
    }

    /**
     * Activation Flow for Verizon and Kore
     * 
     */
    public void activateDevice() {

        // Begin:Activate Devices

        from("direct:activateDevice").routeId("activateDevice")
                .process(new HeaderProcessor())
                .choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))

                .choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .log(LoggingLevel.DEBUG, "message" + header("derivedSourceName"))
                .process(new StubKoreActivateDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonActivateDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperActivateDeviceProcessor())
                .to("log:input")
                .endChoice()

                .otherwise()
                .choice()

                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processActivateKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateActivateDBPayload")
                // will store only one time in Audit even on connection failure
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonActivationFlow1").endChoice()

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processActivateATTJasperTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()
                .end()
                .endChoice().end();

        // Verizon Flow-1
        from("direct:VerizonActivationFlow1").routeId("VerizonActivationFlow1")
                .doTry()
                .to("direct:VerizonActivationFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService, "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor())
                .endDoTry()
                .end();

        // Verizon Flow-2
        from("direct:VerizonActivationFlow2").routeId("VerizonActivationFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonActivateDevicePreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal().json(JsonLibrary.Jackson)
                .bean(iTransactionalService, "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor());

        // Kore Flow-1
        from("direct:processActivateKoreTransaction").routeId("processActivateKoreTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread activation")
                .bean(iTransactionalService, "populateActivateDBPayload").id("populateActivateDBPayloadKore")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW
        from("seda:koreSedaActivation?concurrentConsumers=5").routeId("koreSedaActivationKore")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService, "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall").id("auditExternalExceptionResponseCall")
                .end()
                .process(new KoreActivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall").id("auditExternalRequestCallKoreActivate")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal().json(JsonLibrary.Jackson, KoreProvisioningResponse.class)
                .to("direct:postActivateDeviceKoreRest");

        from("direct:postActivateDeviceKoreRest").routeId("postActivateDeviceKoreRest")
                .bean(iTransactionalService, "populateKoreTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall");

        // ATTJasper Flow-1
        from("direct:processActivateATTJasperTransaction").routeId("processActivateATTJasperTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread activation")
                .bean(iTransactionalService, "populateActivateDBPayload")
                .split().method("deviceSplitter")
                .recipientList().method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW
        from("seda:attJasperSedaActivation?concurrentConsumers=5").routeId("attJasperSedaActivation")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperActivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService,
                        "populateATTJasperTransactionalResponse")
                //sending the request for custom field & Service Plan
               .multicast().parallelProcessing().to("direct:activationWithCustomFieldFlow","direct:activationWithServicePlanFlow");
 
        
        from("direct:activationWithCustomFieldFlow").routeId("activationWithCustomFieldFlow")
                .bean(iTransactionalService, "setActivateCustomFieldListInExchange").split()
                .method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");
                
        from("direct:activationWithServicePlanFlow").routeId("activationWithServicePlanFlow")
               .bean(iTransactionalService, "setActivateServicePlanListInExchange").split()
                .method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // End:Activate Devices
    }

    /**
     * Deactivate Flow for Verizon and Kore
     * 
     */

    public void deactivateDevice() {

        // Begin:Deactivate Devices

        from("direct:deactivateDevice").routeId("deactivateDevice")
                .process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .process(new StubKoreDeactivateDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperDeactivateDeviceProcessor())
                .to("log:ATTJASPER")

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonDeactivateDeviceProcessor())
                .to("log:input").endChoice().otherwise().choice()

                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processDeactivateKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processDeactivateATTJasperTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateDeactivateDBPayload")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonDeactivationFlow1").endChoice().end()
                .to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonDeactivationFlow1").routeId("VerizonDeactivationFlow1")
                .doTry()
                .to("direct:VerizonDeactivationFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:VerizonDeactivationFlow2").routeId("VerizonDeactivationFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeactivateDevicePreProcessor())
                // Audit will store record 3 times in case of failure (see
                // onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService, "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor());

        // Kore Flow-1

        from("direct:processDeactivateKoreTransaction").routeId("processDeactivateKoreTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateDeactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaDeactivation?concurrentConsumers=5").routeId("koreSedaDeactivation")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService, "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()
                .process(new KoreDeactivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, KoreProvisioningResponse.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iTransactionalService, "populateKoreTransactionalResponse");

        // ATTJASPER Flow-1

        from("direct:processDeactivateATTJasperTransaction").routeId("processDeactivateATTJasperTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateDeactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW

        from("seda:attJasperSedaDeactivation?concurrentConsumers=5").routeId("attJasperSedaDeactivation")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperDeactivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)

                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalResponse");

        // End:Deactivate Devices
    }

    /**
     * Restore Flow for Verizon and Kore
     */
    public void restoreDevice() {

        // Begin:Restore Devices

        from("direct:restoreDevice").routeId("restoreDevice")
                .process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .log(LoggingLevel.DEBUG, "message" + header("derivedSourceName"))
                .process(new StubKoreRestoreDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonRestoreDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperRestoreDeviceProcessor())
                .to("log:ATTJASPER")

                .endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processRestoreKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processRestoreDeviceATTJasperTansaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateRestoreDBPayload")
                // will store only one time in Audit even on connection failure
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonRestoreFlow1").endChoice().end()
                .to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonRestoreFlow1").routeId("VerizonRestoreFlow1")
                .doTry()
                .to("direct:VerizonRestoreFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService, "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:VerizonRestoreFlow2").routeId("VerizonRestoreFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonRestoreDevicePreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService, "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor());

        // Kore Flow-1

        from("direct:processRestoreKoreTransaction").routeId("processRestoreKoreTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread restore")
                .bean(iTransactionalService, "populateRestoreDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaRestore?concurrentConsumers=5").routeId("koreSedaRestore")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService,
                        "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()
                .process(new KoreRestoreDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, KoreProvisioningResponse.class)
                .bean(iTransactionalService, "populateKoreTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall");

        // ATTJASPER Flow-1

        from("direct:processRestoreDeviceATTJasperTansaction").routeId("processRestoreDeviceATTJasperTansaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateRestoreDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW

        from("seda:attJasperSedaRestore?concurrentConsumers=5").routeId("attJasperSedaRestore")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperRestoreDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalResponse");

        // End:Restore Devices
    }

    /**
     * Suspend Flow for Verizon and Kore
     */

    public void suspendDevice() {

        // Begin:Suspend Devices

        from("direct:suspendDevice").routeId("suspendDevice")
                .process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .process(new StubKoreSuspendDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonSuspendDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperSuspendDeviceProcessor())
                .to("log:ATTJASPER")

                .endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processSuspendKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice().

                when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processSuspendDeviceATTJasperTansaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateSuspendDBPayload")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonSuspendFlow1")

                .endChoice().end().to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonSuspendFlow1").routeId("VerizonSuspendFlow1")
                .doTry()
                .to("direct:VerizonSuspendFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService,
                        "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:VerizonSuspendFlow2").routeId("VerizonSuspendFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonSuspendDevicePreProcessor())
                // Audit will store record 3 times in case of failure (see
                // onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService, "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor());

        // Kore Flow-1

        from("direct:processSuspendKoreTransaction").routeId("processSuspendKoreTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread suspension")
                .bean(iTransactionalService, "populateSuspendDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaSuspend?concurrentConsumers=5").routeId("koreSedaSuspend")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService, "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()
                .process(new KoreSuspendDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal().json(JsonLibrary.Jackson, KoreProvisioningResponse.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iTransactionalService, "populateKoreTransactionalResponse");

        // ATTJASPER Flow-1

        from("direct:processSuspendDeviceATTJasperTansaction").routeId("processSuspendDeviceATTJasperTansaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateSuspendDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW

        from("seda:attJasperSedaSuspend?concurrentConsumers=5").routeId("attJasperSedaSuspend")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperSuspendDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalResponse");

        // End:Suspend Devices

    }

    /**
     * Reactivate Flow for Kore
     */
    public void reactivateDevice() {

        // Begin:Reactivate Devices

        from("direct:reactivateDevice").routeId("reactivateDevice")
                .process(new HeaderProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice().when(header("derivedCarrierName").isEqualTo("KORE"))
                .log(LoggingLevel.DEBUG, "message" + header("derivedSourceName"))
                .process(new StubKoreReactivateDeviceProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperReactivateDeviceProcessor())
                .to("log:ATTJASPER")

                .endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processReactivateKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processReactivateDeviceATTJasperTansaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .end().to("log:input").endChoice().end();

        // Kore Flow-1

        from("direct:processReactivateKoreTransaction").routeId("processReactivateKoreTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread reactivation")
                .bean(iTransactionalService, "populateReactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaReactivation?concurrentConsumers=5").routeId("koreSedaReactivation")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService, "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()
                .process(new KoreReactivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal().json(JsonLibrary.Jackson, KoreProvisioningResponse.class)
                .bean(iTransactionalService, "populateKoreTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall");

        // ATTJASPER Flow-1

        from("direct:processReactivateDeviceATTJasperTansaction").routeId("processReactivateDeviceATTJasperTansaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread reactivation")
                .bean(iTransactionalService, "populateReactivateDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW

        from("seda:attJasperSedaReactivation?concurrentConsumers=5").routeId("attJasperSedaReactivation")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperReactivateDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalResponse");

        // End:Reactivate Devices

    }

    /**
     * Custom Fields Flow for Verizon and Kore
     */
    public void customFields() {

        // Begin:CustomFields Devices

        from("direct:customFields").routeId("customFields")
                .process(new HeaderProcessor()).choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice().when(header("derivedCarrierName").isEqualTo("KORE"))
				.process(new StubKoreCustomFieldsProcessor())
                .to("log:input")

				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.process(new StubVerizonCustomFieldsProcessor())
				.to("log:input")

				.when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.process(new StubATTJasperCustomFieldsProcessor())
				.to("log:input")

				.endChoice().otherwise().choice()

                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .wireTap("direct:processCustomFieldsKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())

                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processCustomFieldATTJasperTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateCustomeFieldsDBPayload")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonCustomFieldsFlow1").endChoice().end()
                .to("log:input").endChoice().end();

        // Verizon Flow-1
        from("direct:VerizonCustomFieldsFlow1").routeId("VerizonCustomFieldsFlow1")
                .doTry()
                .to("direct:VerizonCustomFieldsFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService, "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // Verizon Flow-2
        from("direct:VerizonCustomFieldsFlow2").routeId("VerizonCustomFieldsFlow2")
                .errorHandler(noErrorHandler())

                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonCustomFieldsPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService, "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor());

        // Kore Flow-1

        from("direct:processCustomFieldsKoreTransaction").routeId("processCustomFieldsKoreTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread customField")
                .bean(iTransactionalService, "populateCustomeFieldsDBPayload")
                .split().method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaCustomFields?concurrentConsumers=5").routeId("koreSedaCustomFields")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService, "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()
                .process(new KoreCustomFieldsPreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, DKoreResponseCode.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iTransactionalService, "populateKoreTransactionalResponse");

        // ATTJASPER Flow-1

        from("direct:processCustomFieldATTJasperTransaction").routeId("processCustomFieldATTJasperTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateATTCustomeFieldsDBPayload").split()
                .method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW

        from("seda:attJasperSedaCustomFields?concurrentConsumers=5").routeId("attJasperSedaCustomFields")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperCustomFieldDevicePreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)

                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalResponse");

        // End:CustomFields Devices

    }

    /**
     * Change Service Plan Flow for Verizon and Kore
     */

    public void changeDeviceServicePlans() {

        // Change Device ServicePlans

        from("direct:changeDeviceServicePlans").routeId("changeDeviceServicePlans")
                .process(new HeaderProcessor())
                .process(new ChangeDeviceServicePlanValidatorProcessor())
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
                .wireTap("direct:processChangeDeviceServicePlansKoreTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .wireTap("direct:processChangeDeviceServicePlansATTJasperTransaction")
                .process(new CarrierProvisioningDevicePostProcessor())
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iTransactionalService, "populateChangeDeviceServicePlansDBPayload")
                .bean(iAuditService, "auditExternalRequestCall")

                .to("direct:VerizonChangeDeviceServicePlansFlow1").endChoice()
                .end().to("log:input").endChoice().end();

        // Verizon Flow-1

        from("direct:VerizonChangeDeviceServicePlansFlow1").routeId("VerizonChangeDeviceServicePlansFlow1")
                .doTry()
                .to("direct:VerizonChangeDeviceServicePlansFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iTransactionalService, "populateVerizonTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:VerizonChangeDeviceServicePlansFlow2").routeId("VerizonChangeDeviceServicePlansFlow2")
                .errorHandler(noErrorHandler())
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonChangeDeviceServicePlansPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .bean(iTransactionalService, "populateVerizonTransactionalResponse")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new CarrierProvisioningDevicePostProcessor());

        // Kore Flow-1

        from("direct:processChangeDeviceServicePlansKoreTransaction").routeId("processChangeDeviceServicePlansKoreTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread ChangeDeviceServicePlans")
                .bean(iTransactionalService, "populateChangeDeviceServicePlansDBPayload").split()
                .method("deviceSplitter").recipientList()
                .method("koreDeviceServiceRouter");

        // Kore SEDA FLOW

        from("seda:koreSedaChangeDeviceServicePlans?concurrentConsumers=5").routeId("koreSedaChangeDeviceServicePlans")
                .onException(CxfOperationException.class)
                .handled(true)
                .bean(iTransactionalService, "populateKoreTransactionalErrorResponse")
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .end()

                .process(new KoreChangeDeviceServicePlansPreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, DKoreResponseCode.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iTransactionalService, "populateKoreTransactionalResponse");

        // ATTJASPER Flow-1

        from("direct:processChangeDeviceServicePlansATTJasperTransaction").routeId("processChangeDeviceServicePlansATTJasperTransaction")
                .log(LoggingLevel.DEBUG, "Wire Tap Thread deactivation")
                .bean(iTransactionalService, "populateChangeDeviceServicePlansDBPayload").split()
                .method("deviceSplitter").recipientList()
                .method("attJasperDeviceServiceRouter");

        // ATTJASPER SEDA FLOW

        from("seda:attJasperSedaChangeDeviceServicePlans?concurrentConsumers=5").routeId("attJasperSedaChangeDeviceServicePlans")
                .onException(SoapFault.class)
                .handled(true)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalErrorResponse")
                .end()
                .process(new ATTJasperChangeDeviceServicePlansPreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)

                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iTransactionalService, "populateATTJasperTransactionalResponse");

        // End: Change Device ServicePlans

    }

    /**
     * deviceConnectionStatus Flow for Verizon
     */

    public void deviceConnectionStatus() {

        // Begin:Device Connection Status

        from("direct:deviceConnectionStatus").routeId("deviceConnectionStatus")
                .process(new HeaderProcessor())
                .process(new DateValidationProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice()
               	.when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonDeviceConnectionStatusProcessor())
                
                .to("log:input")
                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperDeviceConnectionStatusProcessor())
				.to("log:input")
                .endChoice().otherwise().choice()
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:DeviceConnectionStatusFlow1").endChoice()
                
                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.bean(iAuditService, "auditExternalRequestCall")
				.to("direct:ATTJasperDeviceConnectionStatusFlow1")
				.endChoice()           
                .end();

        // Verizon Flow-1

        from("direct:DeviceConnectionStatusFlow1").routeId("DeviceConnectionStatusFlow1")
                .doTry()
                .to("direct:DeviceConnectionStatusFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // Verizon Flow-2

        from("direct:DeviceConnectionStatusFlow2").routeId("DeviceConnectionStatusFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceConnectionInformationPreProcessor())
                // Audit will store record 3 times in case of failure (see
                // onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT).unmarshal()
                .json(JsonLibrary.Jackson, ConnectionInformationResponse.class)
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new VerizonDeviceConnectionStatusPostProcessor(env));
        
		// ATTJasper Flow-1

		from("direct:ATTJasperDeviceConnectionStatusFlow1").routeId("ATTJasperDeviceConnectionStatusFlow1")
                .doTry()
				.to("direct:ATTJasperDeviceConnectionStatusFlow2")
				.doCatch(SoapFault.class)
				.bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
				.process(new ATTJasperGenericExceptionProcessor())
				.endDoTry().end();

		// ATTJasper Flow-2

		from("direct:ATTJasperDeviceConnectionStatusFlow2").routeId("ATTJasperDeviceConnectionStatusFlow2")
				.errorHandler(noErrorHandler())
				.process(new ATTJasperDeviceSessionBeginEndInfoPreProcessor(env))
				.to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
				.bean(iAuditService, "auditExternalSOAPResponseCall")
				.process(new ATTJasperDeviceConnectionStatusPostProcessor(env));

		// End:Device Connection Status

    }

    /**
     * deviceConnectionStatus Flow for Verizon
     */
    public void deviceSessionBeginEndInfo() {

        // Begin: Device Session Begin End

		from("direct:deviceSessionBeginEndInfo").routeId("deviceSessionBeginEndInfo")
                .process(new HeaderProcessor())
				.process(new DateValidationProcessor()).choice()
				.when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
				.choice()
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.process(new StubVerizonDeviceSessionBeginEndInfoProcessor())
				.to("log:input")

				.when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.process(new StubATTJasperDeviceSessionBeginEndInfoProcessor())
				.to("log:input")

                .endChoice().otherwise().choice()
				.when(header("derivedCarrierName").isEqualTo("VERIZON"))
				.bean(iSessionService, "setContextTokenInExchange")
				.bean(iAuditService, "auditExternalRequestCall")
				.to("direct:DeviceSessionBeginEndInfoFlow1").endChoice()

				.when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
				.bean(iAuditService, "auditExternalRequestCall")
				.to("direct:ATTJasperDeviceSessionBeginEndInfoFlow1")
				.endChoice().end();

		// Verizon Flow-1
		from("direct:DeviceSessionBeginEndInfoFlow1").routeId("DeviceSessionBeginEndInfoFlow1")
                .doTry()
				.to("direct:DeviceSessionBeginEndInfoFlow2")
				.doCatch(CxfOperationException.class)
				.bean(iAuditService, "auditExternalExceptionResponseCall")
				.process(new VerizonGenericExceptionProcessor()).endDoTry()
				.end();
		// Verizon Flow-2
		from("direct:DeviceSessionBeginEndInfoFlow2").routeId("DeviceSessionBeginEndInfoFlow2")
				.errorHandler(noErrorHandler())
				// REMOVED Audit will store record 3 times in case of failure
				// (see onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.bean(iSessionService, "setContextTokenInExchange")
				.process(new VerizonDeviceConnectionInformationPreProcessor())
				// Audit will store record 3 times in case of failure (see
				// onException for connection.class above)
				// .bean(iAuditService, "auditExternalRequestCall")
				.to(IEndPoints.URI_REST_VERIZON_ENDPOINT).unmarshal()
                .json(JsonLibrary.Jackson, ConnectionInformationResponse.class)
                .to("direct:verizonDeviceSessionBeginEndInfoPostProcessor");

        from("direct:verizonDeviceSessionBeginEndInfoPostProcessor").routeId("verizonDeviceSessionBeginEndInfoPostProcessor")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new VerizonDeviceSessionBeginEndInfoPostProcessor(env));

		// ATTJASPER Flow-1
		from("direct:ATTJasperDeviceSessionBeginEndInfoFlow1").routeId("ATTJasperDeviceSessionBeginEndInfoFlow1")
                .doTry()
				.to("direct:ATTJasperDeviceSessionBeginEndInfoFlow2")
				.doCatch(SoapFault.class)
				.bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
				.process(new ATTJasperGenericExceptionProcessor())
				.endDoTry().end();
		// ATTJASPER Flow-2
		from("direct:ATTJasperDeviceSessionBeginEndInfoFlow2").routeId("ATTJasperDeviceSessionBeginEndInfoFlow2")
				.errorHandler(noErrorHandler())
				.process(new ATTJasperDeviceSessionBeginEndInfoPreProcessor(env))
				.to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                .to("direct:attJasperDeviceSessionBeginEndInfoPostProcessor");

        from("direct:attJasperDeviceSessionBeginEndInfoPostProcessor").routeId("attJasperDeviceSessionBeginEndInfoPostProcessor")
                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .process(new ATTJasperDeviceSessionBeginEndInfoPostProcessor(env));

		// End: Device Session Begin End
    }

    /**
     * Get DeviceInformation from Carrier and update in MasterDB and return back
     * to Calling System.
     **/
    public void deviceInformationCarrier() {

        from("direct:deviceInformationCarrier").routeId("deviceInformationCarrier")
                .process(new HeaderProcessor())
                .process(new NetSuiteIdValidationProcessor()).choice()

                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT))).choice()

                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .process(new StubKoreDeviceInformationProcessor())
                .to("log:input")

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonDeviceInformationProcessor())
                .to("log:input")
                
                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .process(new StubATTJasperInformationProcessor())
                .to("log:input")

                .endChoice().otherwise().choice()

                .when(header("derivedCarrierName").isEqualTo("KORE"))
                .doTry()
                .process(new KoreDeviceInformationPreProcessor(env))
                //.to("bean:iAuditService?method=auditExternalRequestCall")
                .bean(iAuditService, "auditExternalRequestCall").id("auditExternalRequestCall")
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal().json(JsonLibrary.Jackson, DeviceInformationResponseKore.class)
                .to("direct:postDeviceInformationCarrierKoreRest")
                .doCatch(CxfOperationException.class)
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new KoreGenericExceptionProcessor())
                .endDoTry()
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("ATTJASPER"))
                .doTry()
                .process(new ATTJasperDeviceInformationPreProcessor(env))
                .bean(iAuditService, "auditExternalRequestCall")
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                .bean(iAuditService, "auditExternalSOAPResponseCall")
                .bean(iDeviceService, "setDeviceInformationDB")
                .process(new ATTJasperDeviceInformationPostProcessor())
                .bean(iDeviceService, "updateDeviceInformationDB")
                .doCatch(SoapFault.class)
                .bean(iAuditService, "auditExternalSOAPExceptionResponseCall")
                .process(new ATTJasperGenericExceptionProcessor())
                .endDoTry()
                .endChoice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                // will store only one time in Audit even on connection failure
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonDeviceInformationCarrierSubProcess")

                .endChoice().end().endChoice().end();

        from("direct:postDeviceInformationCarrierKoreRest").routeId("postDeviceInformationCarrierKoreRest")
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iDeviceService, "setDeviceInformationDB")
                .process(new KoreDeviceInformationPostProcessor(env))
                .bean(iDeviceService, "updateDeviceInformationDB");

        from("direct:VerizonDeviceInformationCarrierSubProcess").routeId("VerizonDeviceInformationCarrierSubProcess")
                .doTry()
                .to("direct:VerizonDeviceInformationCarrierSubProcessFlow")
                .doCatch(CxfOperationException.class)
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // SubFlow: Verizon Device Information
        from("direct:VerizonDeviceInformationCarrierSubProcessFlow").routeId("VerizonDeviceInformationCarrierSubProcessFlow")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceInformationPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, DeviceInformationResponseVerizon.class)
                .to("direct:postDeviceInformationCarrierVerizonRest");

        from("direct:postDeviceInformationCarrierVerizonRest").routeId("postDeviceInformationCarrierVerizonRest")
                .bean(iAuditService, "auditExternalResponseCall")
                .bean(iDeviceService, "setDeviceInformationDB")
                .process(new VerizonDeviceInformationPostProcessor(env))
                .bean(iDeviceService, "updateDeviceInformationDB");
    }

    /**
     * Get DeviceInformation from MasterDB and return back to Calling System.
     **/
    public void getDeviceInformationDB() {
        from("direct:getDeviceInformationDB").routeId("getDeviceInformationDB")
                .process(new HeaderProcessor())
                .to("direct:preDeviceService")
                .end();

        from("direct:preDeviceService").routeId("preDeviceService")
                .bean(iDeviceService, "getDeviceInformationDB")
                .to("log:input")
                .end();

    }

    /** Insert or Update Single Device details in MasterDB **/

    public void updateDeviceDetails() {
        from("direct:updateDeviceDetails").routeId("updateDeviceDetails")
                .process(new HeaderProcessor())
                .choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .process(new StubCellUploadProcessor()).endChoice().otherwise()
                .bean(iDeviceService, "updateDeviceDetails").end();
    }

    /** Insert or Upload Batch Device details in MasterDB. **/
    public void updateDevicesDetailsBulk() {

        from("direct:updateDevicesDetailsBulk").routeId("updateDevicesDetailsBulk")
                .process(new HeaderProcessor())
                .choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .process(new StubCellBulkUploadProcessor()).endChoice()
                .otherwise().to("direct:updateDevicesDetailsBulkActual").end();

        from("direct:updateDevicesDetailsBulkActual").routeId("updateDevicesDetailsBulkActual")
                .onCompletion()
                .modeBeforeConsumer().setBody().body()
                .process(new BulkDeviceProcessor()).end()
                .bean(iDeviceService, "updateDevicesDetailsBulk").split()
                .method("bulkOperationSplitter").parallelProcessing()
                .recipientList().method("bulkOperationServiceRouter");

        /**
         * Bulk Insert or Update the device in MasterDB using Seda
         */
        from("seda:bulkOperationDeviceSyncInDB?concurrentConsumers=5").routeId("bulkOperationDeviceSyncInDB")
                .bean(iDeviceService, "bulkOperationDeviceSyncInDB");
    }

    /**
     * Method to Schedule the Verizon Device Connection History Job
     */
    public void deviceConnectionHistoryVerizonJob24() {

       from(env.getProperty(IConstant.VERIZON_CONNECTION_TIMER24))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.VERIZON.toString() + ", "
                                + JobName.VERIZON_CONNECTION_HISTORY + ","+IConstant.DURATION_24+","+JobType.NEW+","+"24"+")")
                .bean(iJobService, "scheduleJob").end();

    }

    /**
     * Method to Schedule the Verizon Device Usage Job for previous day usage
     */
    public void deviceUsageHistoryVerizonJob24() {

        from(
                env.getProperty(IConstant.VERIZON_USAGE_TIMER24))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.VERIZON.toString() + ", "
                                + JobName.VERIZON_DEVICE_USAGE + ","+IConstant.DURATION_24+","+JobType.NEW+","+"24"+")")
                .bean(iJobService, "scheduleJob").end();

    }

    /**
     * Method to Schedule the Kore Device Usage Job for previous day usage
     */
    public void deviceUsageHistoryKoreJob24() {

        from(env.getProperty(IConstant.KORE_USAGE_TIMER24)).bean(iJobService, "checkKoreJobScheduling(${exchange},"
                                + IConstant.DURATION_24+")").choice()
                                .when(simple("${exchangeProperty[IsKoreJobScheduling]} == 'true'"))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.KORE.toString() + ", "
                                + JobName.KORE_DEVICE_USAGE + ","+IConstant.DURATION_24+","+JobType.NEW+","+"24"+")")
                .bean(iJobService, "scheduleJob").endChoice().end();

    }

    /**
     * Method to Schedule the ATTJasper Device Usage Job for previous day usage
     */
    public void deviceUsageHistoryATTJasperJob24() {

        from(env.getProperty(IConstant.ATTJASPER_USAGE_TIMER24))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.ATTJASPER.toString() + ", "
                                + JobName.ATTJASPER_DEVICE_USAGE + ","+IConstant.DURATION_24+","+JobType.NEW+","+"24"+")")
                .bean(iJobService, "scheduleJob").end();

    }
    
    

    /**
     * Method to Schedule the Verizon Device Usage Job for 2 days back to get the updated data usage of roaming devices.
     */
    public void deviceUsageHistoryVerizonJob48() {

        from(
                env.getProperty(IConstant.VERIZON_USAGE_TIMER48))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.VERIZON.toString() + ", "
                                + JobName.VERIZON_DEVICE_USAGE + ","+IConstant.DURATION_48+","+JobType.RERUN+","+"48"+")")
                .bean(iJobService, "scheduleJob").end();

    }

    /**
     * Method to Schedule the Kore Device Usage Job for 2 days back to get the updated data usage of roaming devices.
     */
    public void deviceUsageHistoryKoreJob48() {

        from(env.getProperty(IConstant.KORE_USAGE_TIMER48)).bean(iJobService, "checkKoreJobScheduling(${exchange},"
                + IConstant.DURATION_48+")").choice()
                .when(simple("${exchangeProperty[IsKoreJobScheduling]} == 'true'"))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.KORE.toString() + ", "
                                + JobName.KORE_DEVICE_USAGE + ","+IConstant.DURATION_48+","+JobType.RERUN+","+"48"+")")
                .bean(iJobService, "scheduleJob").endChoice().end();

    }

    /**
     * Method to Schedule the Verizon Device Usage Job for 3 days back to get the updated data usage of roaming devices.
     */
    public void deviceUsageHistoryVerizonJob72() {

        from(
                env.getProperty(IConstant.VERIZON_USAGE_TIMER72))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.VERIZON.toString() + ", "
                                + JobName.VERIZON_DEVICE_USAGE + ","+IConstant.DURATION_72+","+JobType.RERUN+","+"72"+")")
                .bean(iJobService, "scheduleJob").end();

    }

    /**
     * Method to Schedule the Kore Device Usage Job for 3 days back to get the updated data usage of roaming devices.
     */
    public void deviceUsageHistoryKoreJob72() {

        from(env.getProperty(IConstant.KORE_USAGE_TIMER72)).bean(iJobService, "checkKoreJobScheduling(${exchange},"
                + IConstant.DURATION_72+")").choice()
                .when(simple("${exchangeProperty[IsKoreJobScheduling]} == 'true'"))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.KORE.toString() + ", "
                                + JobName.KORE_DEVICE_USAGE + ","+IConstant.DURATION_72+","+JobType.RERUN+","+"72"+")")
                .bean(iJobService, "scheduleJob").endChoice().end();

    }

    /**
     * Method to Schedule the Kore Device Usage Job for 3 days back to get the updated data usage of roaming devices.
     */
    public void deviceUsageHistoryKoreJob96() {

        from(env.getProperty(IConstant.KORE_USAGE_TIMER96)).bean(iJobService, "checkKoreJobScheduling(${exchange},"
                + IConstant.DURATION_96+")").choice()
                .when(simple("${exchangeProperty[IsKoreJobScheduling]} == 'true'"))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.KORE.toString() + ", "
                                + JobName.KORE_DEVICE_USAGE + ","+IConstant.DURATION_96+","+JobType.RERUN+","+"96"+")")
                .bean(iJobService, "scheduleJob").endChoice().end();

    }

    /**
     * Method to Schedule the Kore Device Usage Job for 3 days back to get the updated data usage of roaming devices.
     */
    public void deviceUsageHistoryKoreJob120() {

        from(env.getProperty(IConstant.KORE_USAGE_TIMER120)).bean(iJobService, "checkKoreJobScheduling(${exchange},"
                + IConstant.DURATION_120+")").choice()
                .when(simple("${exchangeProperty[IsKoreJobScheduling]} == 'true'"))
                .bean(iJobService,
                        "setJobDetails(${exchange},"
                                + CarrierType.KORE.toString() + ", "
                                + JobName.KORE_DEVICE_USAGE + ","+IConstant.DURATION_120+","+JobType.RERUN+","+"120"+")")
                .bean(iJobService, "scheduleJob").endChoice().end();

    }

    /**
     * Method contains flow of the batch jobs
     */

    public void startJob() {

        from("direct:startJob").to("direct:processJob");

        // Job Flow-1

        from("direct:processJob").routeId("processJob")
                .onException(ExchangeTimedOutException.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "TimeOut Exception for Batch Job")
                .process(new TimeOutErrorProcessor())
                .end()
                .onCompletion()
                .bean(iJobService, "checkTimeOutDevices")
                .bean(iJobService, "updateJobDetails")
                // CallBack to NetSuite on Job Completion
                .doTry()
                .process(new JobCallBackProcessor(env)).
                setHeader(Exchange.HTTP_QUERY).
                simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                .doCatch(Exception.class)
                .bean(iJobService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iJobService, "updateNetSuiteCallBackResponse")
                .log(LoggingLevel.DEBUG, "Completed")
                // Notification Not required.
                /*
                 * // Run the Notification Job for Self triggered Batch job of
                 * Device Usage choice() .when(simple(
                 * "${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE' || ${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE' && ${exchangeProperty[jobType]} == 'NEW'"
                 * ) ). bean(iJobService,
                 * "setJobDetails(${exchange},${exchangeProperty[carrierName]},"
                 * +JobName.DEVICE_USAGE_NOTIFICATION+")")
                 * .to("direct:notificationJob").endChoice().end().
                 */
                .end()
                .end()
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
                .when(simple("${exchangeProperty[jobName]} == 'ATTJASPER_DEVICE_USAGE'"))
                .bean(iJobService, "deleteDeviceUsageRecords")
                .endChoice()
                .endChoice()
                .end()

                // Fetch List Forwarded to Respective SEDA

                .choice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_CONNECTION_HISTORY'"))
                .split()
                .method("jobSplitter")
                .parallelProcessing()
                .to("seda:processVerizonConnectionHistoryJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE'"))
                .split()
                .method("jobSplitter")
                .parallelProcessing()
                .to("seda:processKoreDeviceUsageJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE'"))
                .split().method("jobSplitter").parallelProcessing()
                .to("seda:processVerizonDeviceUsageJob").endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'ATTJASPER_DEVICE_USAGE'"))
				.bean(iJobService,"fetchPreviousDeviceUsageData")
				.split().method("jobSplitter").parallelProcessing()
				.to("seda:processATTJasperDeviceUsageJob").endChoice();
                
               

        // KORE Job-DEVICE USAGE
        from("seda:processKoreDeviceUsageJob?concurrentConsumers=10").routeId("processKoreDeviceUsageJob")
                .log("KOREJob-DEVICE USAGE")

                .doTry()
                .process(new KoreDeviceUsageHistoryPreProcessor(env))
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, UsageInformationKoreResponse.class)

                .process(new KoreDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,
                        SocketTimeoutException.class,
                        NoRouteToHostException.class,
                        KoreSimMissingException.class)
                .process(new KoreBatchExceptionProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job-DEVICE USAGE
        from("seda:processVerizonDeviceUsageJob?concurrentConsumers=10").routeId("processVerizonDeviceUsageJob")
                .log("VERIZONJob-DEVICE USAGE")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceUsageHistoryPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, VerizonUsageInformationResponse.class)
                .process(new VerizonDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,
                        SocketTimeoutException.class,
                        NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job CONNECTION HISTORY
        from("seda:processVerizonConnectionHistoryJob?concurrentConsumers=10").routeId("processVerizonConnectionHistoryJob")
                .log("VERIZONJob CONNECTION HISTORY")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonDeviceConnectionHistoryPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, ConnectionInformationResponse.class)
                .process(new VerizonDeviceConnectionHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,
                        SocketTimeoutException.class,
                        NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor())
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .endDoTry();
        
        
        // ATT JASPER Job-DEVICE USAGE
        from("seda:processATTJasperDeviceUsageJob?concurrentConsumers=10").routeId("processATTJasperDeviceUsageJob")
                .log("ATTJASPER-Job-DEVICE USAGE")
                .doTry()
                .process(new ATTJasperDeviceUsageHistoryPreProcessor(env))
                .to(IEndPoints.URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT)
                 .process(new ATTJasperDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(SoapFault.class, UnknownHostException.class,ConnectException.class,SocketTimeoutException.class,
                        NoRouteToHostException.class)
                .process(new ATTJasperBatchExceptionProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

    }

    /**
     * Method contains flow of the transaction failure job
     */

    public void transactionFailureJob() {

        from("direct:startTransactionFailureJob").to(
                "direct:processTransactionFailureJob");

        // Job Flow-1

        from("direct:processTransactionFailureJob").routeId("processTransactionFailureJob")
                .onException(ExchangeTimedOutException.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "TimeOut Exception for Batch Job")
                .process(new TimeOutErrorProcessor())
                .end()
                .onCompletion()
                .bean(iJobService, "checkTimeOutDevicesTransactionFailure")
                .bean(iJobService, "updateJobDetails")
              // CallBack to NetSuite on Job Completion
                 .doTry()
                .process(new JobCallBackProcessor(env)).
                setHeader(Exchange.HTTP_QUERY).
                simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                 .doCatch(Exception.class)
                .bean(iJobService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iJobService, "updateNetSuiteCallBackResponse")
                .log(LoggingLevel.DEBUG, "Completed")
                .end()
                .end()
                .bean(iJobService, "insertJobDetails")
                .bean(iJobService, "setJobStartandEndTime")
                .bean(iJobService, "fetchTransactionFailureDevices")

                // Deleting Transaction Failure Existing Records
                .choice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_CONNECTION_HISTORY'"))
                .bean(iJobService, "deleteTransactionFailureDeviceConnectionHistoryRecords")
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
                .method("jobSplitter")
                .parallelProcessing()
                .to("seda:processTransactionFailureVerizonConnectionHistoryJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'KORE_DEVICE_USAGE'"))
                .split()
                .method("jobSplitter")
                .parallelProcessing()
                .to("seda:processTransactionFailureKoreDeviceUsageJob")
                .endChoice()
                .when(simple("${exchangeProperty[jobName]} == 'VERIZON_DEVICE_USAGE'"))
                .split().method("jobSplitter").parallelProcessing()
                .to("seda:processTransactionFailureVerizonDeviceUsageJob")
                .endChoice();

        // KORE Job-DEVICE USAGE
        from("seda:processTransactionFailureKoreDeviceUsageJob?concurrentConsumers=10").routeId("processTransactionFailureKoreDeviceUsageJob")
                .log("KOREJob-DEVICE USAGE")
                .doTry()
                .process(new KoreTransactionFailureDeviceUsageHistoryPreProcessor(env))
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, UsageInformationKoreResponse.class)
                .process(new KoreDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,
                        SocketTimeoutException.class,
                        NoRouteToHostException.class,
                        KoreSimMissingException.class)
                .process(new KoreBatchExceptionProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job-DEVICE USAGE
        from("seda:processTransactionFailureVerizonDeviceUsageJob?concurrentConsumers=10").routeId("processTransactionFailureVerizonDeviceUsageJob")
                .log("VERIZONJob-DEVICE USAGE")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonTransactionFailureDeviceUsageHistoryPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, VerizonUsageInformationResponse.class)
                .process(new VerizonDeviceUsageHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,
                        SocketTimeoutException.class,
                        NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor())
                .bean(iSchedulerService, "saveDeviceUsageHistory").endDoTry();

        // VERIZON Job CONNECTION HISTORY
        from("seda:processTransactionFailureVerizonConnectionHistoryJob?concurrentConsumers=10").routeId("processTransactionFailureVerizonConnectionHistoryJob")
                .log("VERIZONJob CONNECTION HISTORY")
                .doTry()
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new VerizonTransactionFailureDeviceConnectionHistoryPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, ConnectionInformationResponse.class)
                .process(new VerizonDeviceConnectionHistoryPostProcessor())
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .doCatch(CxfOperationException.class,
                        UnknownHostException.class, ConnectException.class,
                        SocketTimeoutException.class,
                        NoRouteToHostException.class)
                .process(new VerizonBatchExceptionProcessor())
                .bean(iSchedulerService, "saveDeviceConnectionHistory")
                .endDoTry();

    }

    /**
     * This Method is used to retrieve device Usage History
     */
    public void retrieveDeviceUsageHistoryCarrier() {
        from("direct:retrieveDeviceUsageHistoryCarrier").routeId("retrieveDeviceUsageHistoryCarrier")
                .process(new HeaderProcessor())
                .process(new DateValidationProcessor()).choice()
                .when(simple(env.getProperty(IConstant.STUB_ENVIRONMENT)))
                .choice()
                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .process(new StubVerizonRetrieveDeviceUsageHistoryProcessor())
                .endChoice().otherwise().choice()

                .when(header("derivedCarrierName").isEqualTo("VERIZON"))
                .bean(iSessionService, "setContextTokenInExchange")
                // will store only one time in Audit even on connection failure
                .bean(iAuditService, "auditExternalRequestCall")
                .to("direct:VerizonRetrieveDeviceUsageHistoryFlow1")
                .endChoice().end();

        // Verizon Flow-1
        from("direct:VerizonRetrieveDeviceUsageHistoryFlow1").routeId("VerizonRetrieveDeviceUsageHistoryFlow1")
                .doTry()
                .to("direct:VerizonRetrieveDeviceUsageHistoryFlow2")
                .doCatch(CxfOperationException.class)
                .bean(iAuditService, "auditExternalExceptionResponseCall")
                .process(new VerizonGenericExceptionProcessor()).endDoTry()
                .end();

        // Verizon Flow-2
        from("direct:VerizonRetrieveDeviceUsageHistoryFlow2").routeId("VerizonRetrieveDeviceUsageHistoryFlow2")
                .errorHandler(noErrorHandler())
                // REMOVED Audit will store record 3 times in case of failure
                // (see onException for connection.class above)
                // .bean(iAuditService, "auditExternalRequestCall")
                .bean(iSessionService, "setContextTokenInExchange")
                .process(new RetrieveDeviceUsageHistoryPreProcessor())
                .to(IEndPoints.URI_REST_VERIZON_ENDPOINT).unmarshal()
                .json(JsonLibrary.Jackson, VerizonUsageInformationResponse.class)
                /*
                 * .bean(iTransactionalService,
                 * "populateRetrieveDeviceUsageHistoryDBPayload")
                 */
                .to("direct:retrieveDeviceUsageHistoryPostProcessor");

        from("direct:retrieveDeviceUsageHistoryPostProcessor").routeId("retrieveDeviceUsageHistoryPostProcessor")
                .bean(iAuditService, "auditExternalResponseCall")
                .process(new RetrieveDeviceUsageHistoryPostProcessor(env));
        
     }

    // Not in Use.
    /**
     * Notification Job
     */

    /*
     * public void notificationJob() {
     * 
     * // Job Flow-1
     * 
     * from("direct:notificationJob").bean(iJobService, "insertJobDetails")
     * .bean(iJobService, "fetchDevices") .bean(iJobService,
     * "addNotificationList").split() .method("jobSplitter")
     * .to("direct:processDeviceUsageNotification");
     * 
     * from("direct:processDeviceUsageNotification") .onCompletion() //
     * .onWhen(property("CamelSplitComplete").isEqualTo("true"))
     * .onWhen(simple("${exchangeProperty[CamelSplitComplete]} == 'true'"))
     * .bean(iJobService, "updateJobDetails") .bean(iJobService,
     * "checkNotificationList").end()
     * .to("seda:processDeviceUsageNotificationJob");
     * 
     * // NOTIFICATION Job-DEVICE USAGE
     * from("seda:processDeviceUsageNotificationJob?concurrentConsumers=5")
     * .doTry().bean(iJobService, "processDeviceNotification")
     * .doCatch(CxfOperationException.class).endDoTry();
     * 
     * }
     */

    public void jobResponse() {
        from("direct:jobResponse").routeId("jobResponse")
                .log(LoggingLevel.DEBUG, "Inside the Job response")
                .process(new JobInitializedPostProcessor());
    }

    // Get Device Usage from Midway by start date to end date .
    public void getDeviceUsageInfoDB() {
        from("direct:getDeviceUsageInfoDB").routeId("getDeviceUsageInfoDB")
                .log(LoggingLevel.DEBUG, "Inside the getDeviceUsageInfoDB")
                .process(new HeaderProcessor())
                .bean(iDeviceService, "getDeviceUsageInfoDB").end();

    }

    // Get Device Connection from Midway by start date to end date .
    public void getDeviceConnectionHistoryInfoDB() {

        from("direct:getDeviceConnectionHistoryInfoDB").routeId("getDeviceConnectionHistoryInfoDB")
                .process(new HeaderProcessor())
                .bean(iDeviceService, "getDeviceConnectionHistoryInfoDB").end();
    }

    // Get Device usage of all the devices by date and carrier from Midway.
    public void getDevicesUsageByDayAndCarrierInfoDB() {
        from("direct:getDevicesUsageByDayAndCarrierInfoDB").routeId("getDevicesUsageByDayAndCarrierInfoDB")
                .log(LoggingLevel.DEBUG, "Inside the getDevicesUsageByDayAndCarrierInfoDB")
                .process(new HeaderProcessor())
                .bean(iDeviceService, "getDevicesUsageByDayAndCarrierInfoDB")
                .end();

    }

    /**
     * saving callbacks from verizon into MongoDB and and sending it to NetSuite
     * and Kafka
     * 
     * */
    public void callbacks() {
        from("direct:callbacks").routeId("callbacks")
                .bean(iTransactionalService, "populateCallbackDBPayload")
                .process(new CallbackPreProcessor(env))
                .bean(iTransactionalService, "findMidwayTransactionId")
                .doTry()
                .process(new CallbackPostProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .log(LoggingLevel.DEBUG, "kafka topic message"
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
        from("quartz2://koreCheckStatusTimer?"+env.getProperty(IConstant.NETSUITE_CALLBACK_TIMER)).routeId("koreCheckStatusTimer")
                .bean(iTransactionalService, "populatePendingKoreCheckStatus")
                .split().method("checkStatusSplitter").parallelProcessing()
                .recipientList().method("koreCheckStatusServiceRouter");

        /**
         * Check status of all the Kore devices with carrier status pending or
         * error and updated it in TransactionDB if it is completed or error
         * from Kore and call the netsuiteEndpoint for them.
         */

        from("seda:koreSedaCheckStatus?concurrentConsumers=5").routeId("koreSedaCheckStatus")
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
                .bean(iTransactionalService, "populateKoreCheckStatusConnectionResponse")
                .end()

                .process(new KoreCheckStatusPreProcessor(env))
                .choice()
                /**
                 * Now call the netsuite end point for error and write in Kafka
                 * Queue.
                 */
                .when(header(IConstant.KORE_CHECK_STATUS).isEqualTo("error"))
                .to("direct:koreCheckStatusErrorSubProcess")

                /** now call the netsuite end point for changeServicePlan and
                 * changeCustomeFields.
                 */
                .when(header(IConstant.KORE_CHECK_STATUS).isEqualTo("change"))
                .to("direct:koreCustomChangeSubProcess")
                /**
                 * Call the Kore API to check the status of device
                 */
                .when(header(IConstant.KORE_CHECK_STATUS).isEqualTo("forward"))
                .to(IEndPoints.URI_REST_KORE_ENDPOINT).unmarshal()
                .json(JsonLibrary.Jackson, KoreCheckStatusResponse.class)
                .to("direct:koreCheckStatusSubProcess").endChoice();

        from("direct:koreCheckStatusErrorSubProcess").routeId("koreCheckStatusErrorSubProcess")
                .bean(iTransactionalService, "populateKoreCheckStatusErrorResponse")
                .doTry()
                .process(new KoreCheckStatusErrorProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-app-errors")
                .
                // Activation with custom fields error scenario
                choice()
                .when(simple("${exchangeProperty[koreActivationWithCustomField]} == 'true'"))
                .to("direct:koreActivationCustomFieldsError").endChoice().end();

        from("direct:koreCustomChangeSubProcess").routeId("koreCustomChangeSubProcess")
                .bean(iTransactionalService, "populateKoreCustomChangeResponse")
                .doTry()
                .process(new KoreCheckStatusPostProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-alerts").end();

        from("direct:koreCheckStatusSubProcess").routeId("koreCheckStatusSubProcess")
                .bean(iTransactionalService, "populateKoreCheckStatusResponse")
                .choice()
                .when(header(IConstant.KORE_PROVISIONING_REQUEST_STATUS)
                        .isEqualTo(IConstant.KORE_CHECKSTATUS_COMPLETED))
                .doTry()
                .process(new KoreCheckStatusPostProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-alerts")

                // Activation with custom fields success scenario
                .choice()
                .when(simple("${exchangeProperty[koreActivationWithCustomField]} == 'true'"))

                // call the change customField for Kore activation request
                .doTry()
                .process(new KoreActivationWithCustomFieldPreProcessor(env))
                .to(IEndPoints.URI_REST_KORE_ENDPOINT)
                .unmarshal()
                .json(JsonLibrary.Jackson, DKoreResponseCode.class)
                .bean(iTransactionalService, "updateKoreActivationCustomeFieldsDBPayload")
                .to("direct:koreActivationCustomFieldsSuccess")
                .doCatch(Exception.class)
                .to("direct:koreActivationCustomFieldsError").endDoTry().end();

        // Kore Activation Custom Fields Error Scenario
        from("direct:koreActivationCustomFieldsError").routeId("koreActivationCustomFieldsError")
                .doTry()
                .process(new KoreActivationWithCustomFieldErrorProcessor(env))
                .bean(iTransactionalService, "updateKoreActivationCustomeFieldsDBPayloadError")
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-app-errors").end();

        // Kore Activation Custom Fields Success Scenario
        from("direct:koreActivationCustomFieldsSuccess").routeId("koreActivationCustomFieldsSuccess")
                .doTry()
                .process(new KoreActivationWithCustomFieldProcessor(env))
                .bean(iTransactionalService, "updateNetSuiteCallBackRequest")
                .setHeader(Exchange.HTTP_QUERY)
                .simple("script=${exchangeProperty[script]}&deploy=1")
                .to("direct:netSuiteCallback")
                .doCatch(Exception.class)
                .bean(iTransactionalService, "updateNetSuiteCallBackError")
                .doFinally()
                .bean(iTransactionalService, "updateNetSuiteCallBackResponse")
                .process(new KafkaProcessor(env))
                .to("kafka:" + env.getProperty("kafka.endpoint")
                        + ",?topic=midway-alerts").end();
    }
    
  /*  
    * Get all the Att devices with carrier status pending or error and Midway
    * status as Pending from TransactionDB
    */
 
    public void attCallBackTimer() {

       from("quartz2://attCallBackTimer?"+env.getProperty(IConstant.NETSUITE_CALLBACK_TIMER)).routeId("attCallBackTimer")
               .bean(iTransactionalService, "updateCallBackStatusOfSecondaryField")
               .bean(iTransactionalService, "fetchAttPendingCallback")
               .split()
               .method("attCallBackSplitter").parallelProcessing()
               .recipientList().method("attCallBackRouter");

       /**
        * Calling the Kafka and Netsuite end point based on the actual response
        * received ATT Jasper
        */

       from("seda:attSedaCallBack?concurrentConsumers=5").routeId("attSedaCallBack")

               .process(new AttCallBackPreProcessor(env))
               .choice()
               
               /**
                * Now call the netsuite end point for error and write in Kafka
                * Queue.
                */
               .when(header(IConstant.ATT_CALLBACK_STATUS).isEqualTo(IConstant.MIDWAY_TRANSACTION_STATUS_ERROR))
               .to("direct:attCallBackErrorSubProcess")
               .when(header(IConstant.ATT_CALLBACK_STATUS).isEqualTo(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS))
               .to("direct:attCallBackSuccessSubProcess").
               endChoice();

       from("direct:attCallBackErrorSubProcess").routeId("attCallBackErrorSubProcess")
               .doTry()
               .process(new AttCallBackErrorPostProcessor(env))
               .bean(iTransactionalService, "updateAttNetSuiteCallBackRequest")
               .setHeader(Exchange.HTTP_QUERY)
               .simple("script=${exchangeProperty[script]}&deploy=1")
               .to("direct:netSuiteCallback")
               .doCatch(Exception.class)
               .bean(iTransactionalService, "updateAttNetSuiteCallBackError")
               .doFinally()
               .bean(iTransactionalService, "updateAttNetSuiteCallBackResponse")
               .process(new KafkaProcessor(env))
               .to("kafka:" + env.getProperty("kafka.endpoint")
                       + ",?topic=midway-app-errors");

       from("direct:attCallBackSuccessSubProcess").routeId("attCallBackSuccessSubProcess")
               .doTry()
               .process(new AttCallBackSuccessPostProcessor(env))
               .bean(iTransactionalService, "updateAttNetSuiteCallBackRequest")
               .setHeader(Exchange.HTTP_QUERY)
               .simple("script=${exchangeProperty[script]}&deploy=1")
               .to("direct:netSuiteCallback")
               .doCatch(Exception.class)
               .bean(iTransactionalService, "updateAttNetSuiteCallBackError")
               .doFinally()
               .bean(iTransactionalService, "updateAttNetSuiteCallBackResponse")
               .process(new KafkaProcessor(env))
               .to("kafka:" + env.getProperty("kafka.endpoint")
                       + ",?topic=midway-alerts").end();

     
   }


}
