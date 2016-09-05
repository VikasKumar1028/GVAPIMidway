
package com.gv.midway.attjasper;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * 
 *         The base type of all responses. It contains some generic information about the response, including
 *         timestamp, correlation id, version etc.
 *     
 * 
 * <p>Java class for ResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="correlationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="build" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
@XmlType(name = "ResponseType", propOrder = {
    "correlationId",
    "version",
    "build",
    "timestamp"
})
@XmlSeeAlso({
    CancelGlobalTransferSimResponse.class,
    GetTerminalsByImsiResponse.class,
    GetTrackedTerminalsResponse.class,
    ActivateTargetSimResponse.class,
    DeleteTerminalEventResponse.class,
    AddCustomSubscriberChargeResponse.class,
    GetSessionInfoResponse.class,
    GlobalGetTerminalDetailsResponse.class,
    GetAccountPeerStatusResponse.class,
    DeleteUserForPartnersResponse.class,
    MBBMessageToVehicleResponse.class,
    CreateUserForPartnersResponse.class,
    GetInvoiceResponse.class,
    DeactivateSimResponse.class,
    EditTerminalRatingResponse.class,
    UndoPrepareTargetSimForReverseTransferResponse.class,
    ATTBountyEventNotificationResponse.class,
    GetAccountsResponse.class,
    GetRoamingSteeringProfileInfoResponse.class,
    OTANADResetResponse.class,
    PortOutMsisdnResponse.class,
    EchoResponse.class,
    AssociateSrcSimWithTargetSimResponse.class,
    GlobalEditTerminalResponse.class,
    UpdateOrderStatusResponse.class,
    GetModifiedTerminalsResponse.class,
    GetLineInformationResponse.class,
    SendBulkSMSToMsisdnResponse.class,
    TransferGlobalSimResponse.class,
    GetGlobalSimSubscriptionMappingResponse.class,
    SendSMSResponse.class,
    TransferSimsToAccountResponse.class,
    SetAccountPeerStatusResponse.class,
    InterSPMoveTargetSimResponse.class,
    GetOrCreateSimFromVirtualSubscriptionResponse.class,
    EditLinePayStatusPerAcctResponse.class,
    GetAvailableEventsResponse.class,
    UpdateOrderShipmentInformationResponse.class,
    ManageSubscriberProfileResponse.class,
    SendSMSToMsisdnResponse.class,
    OTAMsisdnResponse.class,
    GetAccountIdByAcctNameResponse.class,
    EditUserForPartnersResponse.class,
    GlobalGetTerminalEventRatePlansResponse.class,
    EditTerminalResponse.class,
    GetTerminalLatestRegistrationResponse.class,
    GetTerminalDetailsResponse.class,
    ResetUserPasswordResponse.class,
    GetNetworkAccessConfigResponse.class,
    GetExtendedTerminalDetailsResponse.class,
    PurgeTargetSimResponse.class,
    GetSimMigrationStatusResponse.class,
    GetTerminalUsageVoiceDetailsResponse.class,
    SendCancelLocationResponse.class,
    GetAlertDetailsResponse.class,
    MBBVehicleRegistrationResponse.class,
    GetSMSDetailsResponse.class,
    GetTerminalUsageSmsDetailsResponse.class,
    CancelSimMigrationResponse.class,
    CancelGlobalSimTransferResponse.class,
    OTAPlmnRefreshResponse.class,
    InterSPPrepareSrcSimResponse.class,
    UndoPrepareSrcSimResponse.class,
    EditLinePayStatusResponse.class,
    ActivateTerminalEventResponse.class,
    GlobalSimTransferResponseType.class,
    ManageDialableMSISDNResponse.class,
    IsNewUsernameValidResponse.class,
    CreateAccountResponse.class,
    TransferTrialSimsToAccountResponse.class,
    ActivateAuthTokenResponse.class,
    GetAlertsResponse.class,
    GetModifiedSMSResponse.class,
    GetTerminalEventsResponse.class,
    SimLocationAlertResponse.class,
    WorkflowAsyncActionResponseCallbackResponse.class,
    GetAuthTokenResponse.class,
    GetCoverageByLatLongResponse.class,
    ActivateSimPPUResponse.class,
    PrepareTargetSimResponse.class,
    GlobalActivateTerminalEventRatePlanResponse.class,
    GetNetworkAccessConfigDetailsResponse.class,
    GetSimStatusResponse.class,
    GetSimLocationResponse.class,
    GetAccountDetailsResponse.class,
    GetTerminalRatingResponse.class,
    GetAllNetworkAccessConfigsResponse.class,
    MigrateSimResponse.class,
    TransferTerminalAccountNoSimStatusResponse.class,
    PrepareSrcSimResponse.class,
    GlobalGetAvailableEventRatePlansResponse.class,
    VIVOMsisdnCallbackResponse.class,
    GetCoverageMapResponse.class,
    SendBulkSMSResponse.class,
    InterSPCreateTargetSimResponse.class,
    DeactivateRemoteAuthTokenResponse.class,
    GetTerminalsByMsisdnResponse.class,
    SimLocationChargeResponse.class,
    SendIpMessageResponse.class,
    DeactivateAuthTokenResponse.class,
    GetLinePayStatusByOpAcctIdResponse.class,
    GetCoverageMapByLatLongResponse.class,
    EditTerminalLocationTrackingResponse.class,
    GetGlobalSimTransferStatusResponse.class,
    GetTerminalsBySecureSimIdResponse.class,
    UpdateSecureSimCredentialsResponse.class,
    CreateTrialUserAccountResponse.class,
    UpdateMsisdnResponse.class,
    AdvanceShipmentNotificationResponse.class,
    GetTerminalLatestNetworkRegistrationResponse.class,
    GetTerminalUsageResponse.class,
    EditAccountResponse.class,
    AddTerminalResponse.class,
    SetSubscriberPolicyStateResponse.class,
    GetPeeredAccountsResponse.class,
    GetCoverageResponse.class,
    AccountTransferResponse.class,
    GetTerminalAuditTrailResponse.class,
    QueueTerminalRatePlanResponse.class,
    AddRoutableMSISDNResponse.class,
    UpdateImsiDetailsResponse.class,
    RemoveRatePlanFromQueueResponse.class,
    GetSubscriberHistoryByMsisdnResponse.class,
    GlobalGetTerminalLatestRegistrationResponse.class,
    GetAccountIdByOpAcctIdResponse.class,
    IsPasswordValidResponse.class,
    GlobalSendSMSResponse.class,
    GetAccountDetailsTelkomselLBSResponse.class,
    DeleteUserResponse.class,
    ChangeImeiResponse.class,
    GetUserSessionResponse.class,
    GetTerminalUsageDataDetailsResponse.class,
    DeactivateSrcSimResponse.class,
    MBBVehicleDeregistrationResponse.class,
    MBBVehicleInfoResponse.class,
    GetGlobalTransferSimStatusResponse.class,
    AssignOrUpdateIPAddressResponse.class,
    SelectVirtualSubAndAssignMSISDNResponse.class,
    EditNetworkAccessConfigResponse.class,
    SubscriberResponseType.class,
    UpdateSubscriberPolicyResponse.class,
    GetPolicyQuotaInfoResponse.class,
    DeactivateTerminalResponse.class,
    SimRmaExchangeResponse.class,
    CreateUserResponse.class,
    HLRCallbackStatusResponse.class,
    GlobalTransferSimResponse.class,
    EndUserSessionResponse.class
})
public abstract class ResponseType {

    @XmlElement(required = true)
    protected String correlationId;
    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected String build;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the correlationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the value of the correlationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
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
     * Gets the value of the build property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuild() {
        return build;
    }

    /**
     * Sets the value of the build property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuild(String value) {
        this.build = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
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
