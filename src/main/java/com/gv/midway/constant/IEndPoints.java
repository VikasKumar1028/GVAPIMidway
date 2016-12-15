package com.gv.midway.constant;

public interface IEndPoints {

    String ACTIVATION_ENDPOINT = "activateDevice";

    String DEACTIVATION_ENDPOINT = "deactivateDevice";

    String REACTIVATION_ENDPOINT = "reactivateDevice";

    String RESTORE_ENDPOINT = "restoreDevice";

    String SUSPENSION_ENDPOINT = "suspendDevice";

    String ACTIVATION_SEDA_KORE_ENDPOINT = "koreSedaActivation";

    String DEACTIVATION_SEDA_KORE_ENDPOINT = "koreSedaDeactivation";

    String REACTIVATION_SEDA_KORE_ENDPOINT = "koreSedaReactivation";

    String RESTORE_SEDA_KORE_ENDPOINT = "koreSedaRestore";

    String SUSPENSION_SEDA_KORE_ENDPOINT = "koreSedaSuspend";

    String CHANGE_CUSTOMFIELD_ENDPOINT = "customFields";

    String CHANGE_SERVICEPLAN_ENDPOINT = "changeDeviceServicePlans";

    String CHANGE_SERVICEPLAN_SEDA_KORE_ENDPOINT = "koreSedachangeDeviceServicePlans";

    String CHANGE_CUSTOMFIELD_SEDA_KORE_ENDPOINT = "koreSedacustomeFields";
    
    String ACTIVATION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaActivation";
    
    String DEACTIVATION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaDeactivation";
    
    String CHANGE_SERVICEPLAN_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaChangeDeviceServicePlans";

    String REACTIVATION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaReactivation";
    
    String CHANGE_CUSTOMFIELD_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaCustomeFields";
    
	String RESTORE_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaRestore";

	String SUSPENSION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaSuspend";

    String URI_REST_VERIZON_ENDPOINT = "cxfrs://bean://rsVerizonClient";
    String URI_REST_VERIZON_TOKEN_ENDPOINT = "cxfrs://bean://rsVerizonTokenClient";
    String URI_REST_KORE_ENDPOINT = "cxfrs://bean://rsKoreClient";
    String URI_REST_NETSUITE_ENDPOINT = "cxfrs://bean://rsNetsuiteClient";

    String URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT = "cxf://bean://attJasperTerminalEndPoint";
}