package com.gv.midway.constant;

public interface IConstant {

    static String STUB_ENVIRONMENT = "stub.environment";
    static String VZ_SEESION_TOKEN = "vzSessionToken";
    static String VZ_AUTHORIZATION_TOKEN = "vzAuthorizationToken";
    static String KORE_AUTHENTICATION = "kore.authentication";
    static String VERIZON_AUTHENTICATION = "verizon.authentication";
    static String RESPONSE_CODE = "ResponseCode";
    static String RESPONSE_STATUS = "ResponseStatus";
    static String ERROR_MESSAGE = "ERROR_MESSAGE";
    static String RESPONSE_DESCRIPTION = "ResponseDescription";

    static String APPLICATION_NAME = "application.name";

    static String REGION = "region";
    static String ORGANIZATION = "organization";
    static String SOURCE_NAME_VERIZON = "source.name.verizon";
    static String SOURCE_NAME_KORE = "source.name.kore";
    static String RESPONSE_DESCRIPTION_DEVICE_INFORMATION = "response.desc.device.information";
    static String BSCARRIER_VERIZON = "bsCarrier.verizon.name";
    static String BSCARRIER_KORE = "bsCarrier.kore.name";
    static String EXCHANEGE_PROPERTY = "exchange.property";
    static String DATE_FORMAT = "simple.date.format";
    static String SOURCE_NAME = "sourceName";
    static String BSCARRIER = "bsCarrier";
    static String AUDIT_TRANSACTION_ID = "com.gv.midway.audit.TransactionID";
    static String MIDWAY_TRANSACTION_ID = "MidwayTransactionId";
    static String CARRIER_TRANSACTION_ID = "CarrierTransactionId";
    static String MIDWAY_TRANSACTION_REQUEST_TYPE = "midWayTransactionRequestType";
    static String MIDWAY_TRANSACTION_REQUEST_HEADER = "midWayTransactionRequestHeader";
    static String MIDWAY_TRANSACTION_PAYLOAD = "midWayTransactionPayload";
    static String MIDWAY_CARRIER_ERROR_DESC = "midWayCarrierErrorDescription";
    static String MIDWAY_DEVICE_ID="midWayDeviceId";

    static String MIDWAY_CALLBACK_CARRIER_STATUS_SUCCESS = "Success";
    static String MIDWAY_CALLBACK_DELIVERED_FAILED = "Fail";

    static String MIDWAY_TRANSACTION_DEVICE_NUMBER = "TransactionDeviceNumber";
    static String MIDWAY_CONNECTION_ERROR = "ConnectionError";
    public static String MIDWAY_NETSUITE_ID = "midwayNetsuiteId";
    public static String MIDWAY_DEVICEINFO_DB = "midwayDeviceInfoDB";
    static String GV_TRANSACTION_ID = "transactionId";
    static String GV_HOSTNAME = "hostName";
    static String MIDWAY_DERIVED_CARRIER_NAME = "derivedCarrierName";

    public static String BULK_SUCCESS_LIST = "bulkSuccessList";
    public static String BULK_ERROR_LIST = "bulkErrorList";
    public static String BULK_SYNC_OPERATION = "bulkInsertOperation";

    static String MIDWAY_TRANSACTION_STATUS_PENDING = "Pending";
    static String MIDWAY_TRANSACTION_STATUS_ERROR = "Error";
    static String MIDWAY_TRANSACTION_STATUS_SUCCESS = "Success";

    static String CARRIER_TRANSACTION_STATUS_PENDING = "Pending";
    static String CARRIER_TRANSACTION_STATUS_ERROR = "Error";
    static String CARRIER_TRANSACTION_STATUS_SUCCESS = "Success";

    static String DEVICE_IN_SESSION = "Device In Session";
    static String DEVICE_NOT_IN_SESSION = "Device Not In Session";
    static String NO_RECORD = "No Record Found";
    static String EVENT_START = "Start";
    static String EVENT_STOP = "Stop";
    static String EVENT = "Event";

    static String KORE_CHECKSTATUS_PENDING = "Pending";
    static String KORE_CHECKSTATUS_COMPLETED = "Completed";
    static String KORE_CHECKSTATUS_CONNECTION_ERROR = "KoreCheckStausConnection Error";
    static String KORE_SIM_NUMBER = "koreSimNumber";

    static String VERIZON_CALLBACK_RESPONE = "VerizonCallBackResponse";

    static String BSCARRIER_SERVICE_VERIZON = "VERIZON";
    static String BSCARRIER_SERVICE_KORE = "KORE";

    // Job is scheduled to run every day at 2 in the morning.
    static String JOB_TIME_CONFIGURATION = "0+0+02+*+*+?";

    static String JOB_STARTED = "Started";
    static String JOB_COMPLETED = "Completed";

    static String KAFKA_TOPIC_NAME = "topicName";
    static String KAFKA_OBJECT = "kafkaObject";

    static String HEADER = "header";

    static int DURATION = -24;

    static String JOB_INITIALIZED_MESSAGE = "Job Started Successfully";

    static String JOB_TYPE_ODD = "ODD";

    static String JOB_TYPE_EVEN = "EVEN";

    static String KORE_CHECK_STATUS = "KoreCheckStatusFlow";

    static String KORE_PROVISIONING_REQUEST_STATUS = "KoreProvisioningRequestStatus";
    static String VERIZON_BATCH_SESSION_TOKENERROR = "VerizonBatchSessionTokenError";
    static String KORE_MISSING_SIM_ERROR="SIM is missing in deviceIds";
    
    static String TIMEOUT_DEVICE_LIST="timeOutDeviceList";
    
    static String ATTJASPER_SOAP_FAULT_ERRORMESSAGE="attJasperSOAPFaultErrorMessage";
    
    static String JOB_ID="JOBID";

}
