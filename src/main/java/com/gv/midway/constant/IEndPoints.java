package com.gv.midway.constant;

public interface IEndPoints {

    public final String ACTIVATION_ENDPOINT = "activateDevice";

    public final String DEACTIVATION_ENDPOINT = "deactivateDevice";

    public final String RESTORE_ENDPOINT = "restoreDevice";

    public final String SUSPENSION_ENDPOINT = "suspendDevice";

    public final String ACTIVATION_SEDA_KORE_ENDPOINT = "koreSedaActivation";

    public final String DEACTIVATION_SEDA_KORE_ENDPOINT = "koreSedaDeactivation";

    public final String REACTIVATION_SEDA_KORE_ENDPOINT = "koreSedaReactivation";

    public final String RESTORE_SEDA_KORE_ENDPOINT = "koreSedaRestore";

    public final String SUSPENSION_SEDA_KORE_ENDPOINT = "koreSedaSuspend";

    public final String CHANGE_CUSTOMFIELD_ENDPOINT = "customeFields";

    public final String CHANGE_SERVICEPLAN_ENDPOINT = "changeDeviceServicePlans";

    public final String CHANGE_SERVICEPLAN_SEDA_KORE_ENDPOINT = "koreSedachangeDeviceServicePlans";

    public final String CHANGE_CUSTOMFIELD_SEDA_KORE_ENDPOINT = "koreSedacustomeFields";
    
    public final String ACTIVATION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaActivation";
    
    public final String DEACTIVATION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaDeactivation";
    
    public final String CHANGE_SERVICEPLAN_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaChangeDeviceServicePlans";

    public final String REACTIVATION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaReactivation";
    
    public final String CHANGE_CUSTOMFIELD_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaCustomeFields";
    
	public final String RESTORE_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaRestore";

	public final String SUSPENSION_SEDA_ATTJASPER_ENDPOINT = "attJasperSedaSuspend";

    public final String URI_REST_VERIZON_ENDPOINT = "cxfrs://bean://rsVerizonClient";
    public final String URI_REST_VERIZON_TOKEN_ENDPOINT = "cxfrs://bean://rsVerizonTokenClient";
    public final String URI_REST_KORE_ENDPOINT = "cxfrs://bean://rsKoreClient";
    public final String URI_REST_NETSUITE_ENDPOINT = "cxfrs://bean://rsNetsuitClient";

    public final String URI_SOAP_ATTJASPER_TERMINAL_ENDPOINT = "cxf://bean://attJasperTerminalEndPoint";

}
