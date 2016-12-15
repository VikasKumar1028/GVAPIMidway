package com.gv.midway.constant;

public interface IConstant {

    String STUB_ENVIRONMENT = "stub.environment";
    String VZ_SESSION_TOKEN = "vzSessionToken";
    String VZ_AUTHORIZATION_TOKEN = "vzAuthorizationToken";
    String KORE_AUTHENTICATION = "kore.authentication";
    String VERIZON_AUTHENTICATION = "verizon.authentication";
    String VERIZON_API_USERNAME = "verizon.api.username";
    String VERIZON_API_PASSWORD = "verizon.api.password";
    String RESPONSE_CODE = "ResponseCode";
    String RESPONSE_STATUS = "ResponseStatus";
    String ERROR_MESSAGE = "ERROR_MESSAGE";
    String RESPONSE_DESCRIPTION = "ResponseDescription";

    String APPLICATION_NAME = "application.name";

    String REGION = "region";
    String ORGANIZATION = "organization";
    String SOURCE_NAME_VERIZON = "source.name.verizon";
    String SOURCE_NAME_KORE = "source.name.kore";
    String RESPONSE_DESCRIPTION_DEVICE_INFORMATION = "response.desc.device.information";
    String BSCARRIER_VERIZON = "bsCarrier.verizon.name";
    String BSCARRIER_KORE = "bsCarrier.kore.name";
    String EXCHANEGE_PROPERTY = "exchange.property";
    String DATE_FORMAT = "simple.date.format";
    String SOURCE_NAME = "sourceName";
    String BSCARRIER = "bsCarrier";
    String AUDIT_TRANSACTION_ID = "com.gv.midway.audit.TransactionID";
    String MIDWAY_TRANSACTION_ID = "MidwayTransactionId";
    String CARRIER_TRANSACTION_ID = "CarrierTransactionId";
    String MIDWAY_TRANSACTION_REQUEST_TYPE = "midWayTransactionRequestType";
    String MIDWAY_TRANSACTION_REQUEST_HEADER = "midWayTransactionRequestHeader";
    String MIDWAY_TRANSACTION_PAYLOAD = "midWayTransactionPayload";
    String MIDWAY_CARRIER_ERROR_DESC = "midWayCarrierErrorDescription";
    String MIDWAY_DEVICE_ID = "midWayDeviceId";

    String MIDWAY_CALLBACK_CARRIER_STATUS_SUCCESS = "Success";
    String MIDWAY_CALLBACK_DELIVERED_FAILED = "Fail";

    String MIDWAY_TRANSACTION_DEVICE_NUMBER = "TransactionDeviceNumber";
    String MIDWAY_CONNECTION_ERROR = "ConnectionError";
    String MIDWAY_NETSUITE_ID = "midwayNetsuiteId";
    String MIDWAY_DEVICEINFO_DB = "midwayDeviceInfoDB";
    String GV_TRANSACTION_ID = "transactionId";
    String GV_HOSTNAME = "hostName";
    String MIDWAY_DERIVED_CARRIER_NAME = "derivedCarrierName";

    String BULK_SUCCESS_LIST = "bulkSuccessList";
    String BULK_ERROR_LIST = "bulkErrorList";
    String BULK_SYNC_OPERATION = "bulkInsertOperation";

    String MIDWAY_TRANSACTION_STATUS_PENDING = "Pending";
    String MIDWAY_TRANSACTION_STATUS_ERROR = "Error";
    String MIDWAY_TRANSACTION_STATUS_SUCCESS = "Success";
    String MIDWAY_TRANSACTION_STATUS_WAIT = "Wait";

    String CARRIER_TRANSACTION_STATUS_PENDING = "Pending";
    String CARRIER_TRANSACTION_STATUS_ERROR = "Error";
    String CARRIER_TRANSACTION_STATUS_SUCCESS = "Success";

    String DEVICE_IN_SESSION = "Device In Session";
    String DEVICE_NOT_IN_SESSION = "Device Not In Session";
    String NO_RECORD = "No Record Found";
    String EVENT_START = "Start";
    String EVENT_STOP = "Stop";
    String EVENT = "Event";

    String KORE_CHECKSTATUS_PENDING = "Pending";
    String KORE_CHECKSTATUS_COMPLETED = "Completed";
    String KORE_CHECKSTATUS_CONNECTION_ERROR = "KoreCheckStausConnection Error";
    String KORE_SIM_NUMBER = "koreSimNumber";
    String KORE_ACTIVATION_CUSTOMFIELD_ERRORPAYLOAD = "koreActivationWithCustomFieldPayload";
    String KORE_ACTIVATION_CUSTOMFIELD_PAYLOAD = "koreActivationWithCustomFieldPayLoad";
    String KORE_ACTIVATION_CUSTOMFIELD_ERROR_DESCRIPTION = "koreActivationWithCustomFieldErrorDescription";
    String KORE_ACTIVATION_CUSTOMFIELD_SECONDARY = "koreActivationWithCustomFieldSecondary";

    String VERIZON_CALLBACK_RESPONSE = "VerizonCallBackResponse";

    String BSCARRIER_SERVICE_VERIZON = "VERIZON";
    String BSCARRIER_SERVICE_KORE = "KORE";
    String BSCARRIER_SERVICE_ATTJASPER = "ATTJASPER";

    // Job is scheduled to run every day at 2 in the morning.
    String JOB_TIME_CONFIGURATION = "0+0+02+*+*+?";

    String JOB_STARTED = "Started";
    String JOB_COMPLETED = "Completed";

    String KAFKA_TOPIC_NAME = "topicName";
    String KAFKA_OBJECT = "kafkaObject";

    String HEADER = "header";

    int DURATION_24 = -24;

    int DURATION_48 = -48;

    int DURATION_72 = -72;

    static int DURATION_96 = -96;

    static int DURATION_120 = -120;

    String JOB_INITIALIZED_MESSAGE = "Job Started Successfully";

    String JOB_TYPE_ODD = "ODD";

    String JOB_TYPE_EVEN = "EVEN";

    String KORE_CHECK_STATUS = "KoreCheckStatusFlow";

    String KORE_PROVISIONING_REQUEST_STATUS = "KoreProvisioningRequestStatus";
    String VERIZON_BATCH_SESSION_TOKENERROR = "VerizonBatchSessionTokenError";
    String KORE_MISSING_SIM_ERROR = "SIM is missing in deviceIds";

    String TIMEOUT_DEVICE_LIST = "timeOutDeviceList";

    String ATTJASPER_SOAP_FAULT_ERRORMESSAGE = "attJasperSOAPFaultErrorMessage";

    String ATTJASPER_SOAP_RESPONSE_PAYLOAD = "attJasperSOAPResponsePayload";

    String ATTJASPER_SOAP_FAULT_PAYLOAD = "attJasperSOAPFaultPayload";

    String JOB_ID = "JOBID";

    String ATTJASPER_DEACTIVATED = "DEACTIVATED_NAME";

    String ATTJASPER_ACTIVATED = "ACTIVATED_NAME";

    int ATTJASPER_SIM_CHANGETYPE = 3;

    int ATTJASPER_RATE_PLAN_CHANGETYPE = 4;

    int ATTJASPER_CUSTOMFIELD_CHANGETYPE = 17;

    String ACTIVATION_WITH_CUSTOMFIELDS = "activationWithCustomFields";

    String ACTIVATION_WITH_SERVICE_PLAN = "activationWithServicePlan";

    String ATT_ACTIVATION_WITH_CUSTOMFIELDS_LIST = "attActivationWithCustomFieldsList";

    String ATT_ACTIVATION_WITH_SERVICEPLAN_LIST = "attActivationWithServicePlanList";

    String ATT_CUSTOMFIELD_TO_UPDATE = "AttCustomFieldsToUpdate";

    String ATT_SERVICEPLAN_TO_UPDATE = "AttServicePlanToUpdate";

    String KORE_USAGE_TIMER24 = "kore.usagetimer24";

    String VERIZON_USAGE_TIMER24 = "verizon.usagetimer24";

    String ATTJASPER_USAGE_TIMER24 = "attJasper.usagetimer24";

    String VERIZON_CONNECTION_TIMER24 = "verizon.connectiontimer24";

    String VERIZON_USAGE_TIMER48 = "verizon.usagetimer48";

    String KORE_USAGE_TIMER48 = "kore.usagetimer48";

    String VERIZON_USAGE_TIMER72 = "verizon.usagetimer72";

    String KORE_USAGE_TIMER72 = "kore.usagetimer72";

    String KORE_USAGE_TIMER96 = "kore.usagetimer96";

    String KORE_USAGE_TIMER120 = "kore.usagetimer120";

    String ATT_CALLBACK_STATUS = "attCallBackStatus";

    String NETSUITE_CALLBACK_TIMER = "callBack.CronTimer";

    String NETSUITE_CALLBACKS_SCRIPT = "netSuite.callbacks.script";

    String NETSUITE_PROCESSJOB_SCRIPT = "netSuite.processJob.script";

    String ATTJASPER_CUSTOM_FIELD_DEC = "attJasperCustomFieldDescription";

    String AUTHORIZATION_KEY = "authorizationKey";

    String JOB_TOTAL_COUNT = "JobTotalCount";

    String JOB_DETAIL = "jobDetail";

    String JOB_START_TIME = "jobStartTime";

    String JOB_END_TIME = "jobEndTime";

    String JOB_NAME = "jobName";

    String CARRIER_NAME = "carrierName";

    String JOB_TYPE = "jobType";

    String JOB_DETAIL_DATE = "jobDetailDate";

    String JOB_ERROR_COUNT = "JobErrorCount";

    String JOB_SUCCESS_COUNT = "JobSuccessCount";

    String ISKOREJOB_SCHEDULING = "IsKoreJobScheduling";

    String ATTJASPER_VERSION = "attJasper.version";
    String ATTJASPER_LICENSE_KEY = "attJasper.licenseKey";
    String ATTJASPER_USERNAME = "attJasper.userName";
    String ATTJASPER_PASSWORD = "attJasper.password";

    String NETSUITE_OAUTH_CONSUMER_KEY = "netSuite.oauthConsumerKey";
    String NETSUITE_OAUTH_TOKEN_ID = "netSuite.oauthTokenId";
    String NETSUITE_OAUTH_TOKEN_SECRET = "netSuite.oauthTokenSecret";
    String NETSUITE_OAUTH_CONSUMER_SECRET = "netSuite.oauthConsumerSecret";
    String NETSUITE_REALM = "netSuite.realm";
    String NETSUITE_END_POINT = "netSuite.endPoint";
}
