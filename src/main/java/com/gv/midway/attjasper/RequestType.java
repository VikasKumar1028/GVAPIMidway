
package com.gv.midway.attjasper;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * 
 *             The base type for all request payload types. The idea is to capture attributes common to all requests
 *             (such as version, license key, message id etc.).
 * 
 *             All concrete request types are derived from this abstract request type. This is the only place where
 *             polymorphism is encouraged.
 *         
 * 
 * <p>Java class for RequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="licenseKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestType", propOrder = {
    "messageId",
    "version",
    "licenseKey"
})
@XmlSeeAlso({
    UndoPrepareTargetSimForReverseTransferRequest.class,
    UpdateOrderShipmentInformationRequest.class,
    GetNetworkAccessConfigRequest.class,
    GetGlobalTransferSimStatusRequest.class,
    TransferTerminalAccountNoSimStatusRequest.class,
    InterSPPrepareSrcSimRequest.class,
    EditUserForPartnersRequest.class,
    UpdateSubscriberPolicyRequest.class,
    GlobalActivateTerminalEventRatePlanRequest.class,
    GetTerminalUsageVoiceDetailsRequest.class,
    OTAMsisdnRequest.class,
    ManageSubscriberProfileRequest.class,
    SimLocationAlertRequest.class,
    TransferTrialSimsToAccountRequest.class,
    GetAccountIdByAcctNameRequest.class,
    GetTerminalLatestNetworkRegistrationRequest.class,
    ManageDialableMSISDNRequest.class,
    SetSubscriberPolicyStateRequest.class,
    HLRCallbackStatusRequest.class,
    GetAccountDetailsRequest.class,
    AssociateSrcSimWithTargetSimRequest.class,
    MBBVehicleDeregistrationRequest.class,
    GetSimStatusRequest.class,
    EditLinePayStatusPerAcctRequest.class,
    GetSimLocationRequest.class,
    ActivateTerminalEventRequest.class,
    CreateUserForPartnersRequest.class,
    SendSMSRequest.class,
    AccountTransferRequest.class,
    EchoRequest.class,
    EditNetworkAccessConfigRequest.class,
    EditTerminalLocationTrackingRequest.class,
    DeactivateTerminalRequest.class,
    SimRmaExchangeRequest.class,
    SendSMSToMsisdnRequest.class,
    MBBMessageToVehicleRequest.class,
    PortOutMsisdnRequest.class,
    GlobalGetTerminalLatestRegistrationRequest.class,
    GlobalSendSMSRequest.class,
    UpdateSecureSimCredentialsRequest.class,
    GetTerminalsByMsisdnRequest.class,
    MBBVehicleRegistrationRequest.class,
    GetCoverageRequest.class,
    GetOrCreateSimFromVirtualSubscriptionRequest.class,
    IsNewUsernameValidRequest.class,
    ResetUserPasswordRequest.class,
    GetTerminalUsageSmsDetailsRequest.class,
    GetAuthTokenRequest.class,
    CancelGlobalTransferSimRequest.class,
    GetTerminalDetailsRequest.class,
    GetGlobalSimTransferStatusRequest.class,
    GetUserSessionRequest.class,
    MBBVehicleInfoRequest.class,
    GetSimMigrationStatusRequest.class,
    AddTerminalRequest.class,
    SetAccountPeerStatusRequest.class,
    EditTerminalRequest.class,
    UpdateImsiDetailsRequest.class,
    PrepareSrcSimRequest.class,
    OTAPlmnRefreshRequest.class,
    GetModifiedSMSRequest.class,
    TransferSimsToAccountRequest.class,
    PurgeTargetSimRequest.class,
    ActivateAuthTokenRequest.class,
    ActivateTargetSimRequest.class,
    CreateAccountRequest.class,
    GetRoamingSteeringProfileInfoRequest.class,
    GetTerminalUsageRequest.class,
    GetCoverageMapByLatLongRequest.class,
    VIVOMsisdnCallbackRequest.class,
    ChangeImeiRequest.class,
    GetTerminalEventsRequest.class,
    DeactivateSrcSimRequest.class,
    GetSubscriberHistoryByMsisdnRequest.class,
    AddRoutableMSISDNRequest.class,
    GetInvoiceRequest.class,
    GlobalGetTerminalEventRatePlansRequest.class,
    TransferGlobalSimRequest.class,
    EditLinePayStatusRequest.class,
    GetExtendedTerminalDetailsRequest.class,
    GetAccountIdByOpAcctIdRequest.class,
    GetTerminalAuditTrailRequest.class,
    GetTerminalsBySecureSimIdRequest.class,
    GetTerminalRatingRequest.class,
    CreateUserRequest.class,
    GetLineInformationRequest.class,
    GlobalEditTerminalRequest.class,
    CreateTrialUserAccountRequest.class,
    GlobalGetAvailableEventRatePlansRequest.class,
    SendIpMessageRequest.class,
    GetSMSDetailsRequest.class,
    DeleteTerminalEventRequest.class,
    AssignOrUpdateIPAddressRequest.class,
    UndoPrepareSrcSimRequest.class,
    UpdateOrderStatusRequest.class,
    DeleteUserRequest.class,
    GetTerminalUsageDataDetailsRequest.class,
    DeleteUserForPartnersRequest.class,
    GetModifiedTerminalsRequest.class,
    GetTerminalLatestRegistrationRequest.class,
    CancelGlobalSimTransferRequest.class,
    DeactivateRemoteAuthTokenRequest.class,
    ActivateSimPPURequest.class,
    WorkflowAsyncActionResponseCallbackRequest.class,
    GetAccountPeerStatusRequest.class,
    SimLocationChargeRequest.class,
    DeactivateAuthTokenRequest.class,
    AddCustomSubscriberChargeRequest.class,
    GetAllNetworkAccessConfigsRequest.class,
    GetPolicyQuotaInfoRequest.class,
    GetAvailableEventsRequest.class,
    GetGlobalSimSubscriptionMappingRequest.class,
    InterSPMoveTargetSimRequest.class,
    EndUserSessionRequest.class,
    GetSessionInfoRequest.class,
    GetAccountDetailsTelkomselLBSRequest.class,
    SelectVirtualSubAndAssignMSISDNRequest.class,
    QueueTerminalRatePlanRequest.class,
    DeactivateSimRequest.class,
    SendBulkSMSRequest.class,
    GetTrackedTerminalsRequest.class,
    SubscriberRequestType.class,
    MigrateSimRequest.class,
    GetCoverageMapRequest.class,
    EditTerminalRatingRequest.class,
    GetAlertsRequest.class,
    GetAccountsRequest.class,
    SendBulkSMSToMsisdnRequest.class,
    GetAlertDetailsRequest.class,
    GetPeeredAccountsRequest.class,
    OTANADResetRequest.class,
    SendCancelLocationRequest.class,
    EditAccountRequest.class,
    ATTBountyEventNotificationRequest.class,
    CancelSimMigrationRequest.class,
    GlobalGetTerminalDetailsRequest.class,
    UpdateMsisdnRequest.class,
    RemoveRatePlanFromQueueRequest.class,
    GlobalTransferSimRequest.class,
    GetLinePayStatusByOpAcctIdRequest.class,
    IsPasswordValidRequest.class,
    AdvanceShipmentNotificationRequest.class,
    PrepareTargetSimRequest.class,
    InterSPCreateTargetSimRequest.class,
    GetNetworkAccessConfigDetailsRequest.class,
    GetCoverageByLatLongRequest.class,
    GetTerminalsByImsiRequest.class
})
public abstract class RequestType {

    @XmlElement(required = true)
    protected String messageId;
    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected String licenseKey;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the licenseKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseKey() {
        return licenseKey;
    }

    /**
     * Sets the value of the licenseKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseKey(String value) {
        this.licenseKey = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
