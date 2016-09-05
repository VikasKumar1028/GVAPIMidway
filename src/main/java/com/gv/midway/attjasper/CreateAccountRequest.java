
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="serviceProvider" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="salesForceAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createSalesForceAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="defaultCommunicationPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="defaultRatePlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createDefaultRatePlan" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="billingCycleStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryContact" type="{http://api.jasperwireless.com/ws/schema}ContactType"/>
 *         &lt;element name="billingContact" type="{http://api.jasperwireless.com/ws/schema}ContactType"/>
 *         &lt;element name="billingAddress" type="{http://api.jasperwireless.com/ws/schema}AddressType"/>
 *         &lt;element name="shippingAddress" type="{http://api.jasperwireless.com/ws/schema}AddressType"/>
 *         &lt;element name="timezone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preferredLanguage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supportEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supportPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="devKitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="showJasperSupport" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="showSpSupport" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="showAcctSupport" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="regionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSegment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceProvider",
    "accountName",
    "accountType",
    "currencyCode",
    "taxId",
    "salesForceAccountName",
    "createSalesForceAccount",
    "defaultCommunicationPlan",
    "defaultRatePlan",
    "createDefaultRatePlan",
    "billingCycleStart",
    "primaryContact",
    "billingContact",
    "billingAddress",
    "shippingAddress",
    "timezone",
    "preferredLanguage",
    "notes",
    "supportEmail",
    "supportPhone",
    "devKitType",
    "showJasperSupport",
    "showSpSupport",
    "showAcctSupport",
    "regionId",
    "accountSegment"
})
@XmlRootElement(name = "CreateAccountRequest")
public class CreateAccountRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String serviceProvider;
    @XmlElement(required = true)
    protected String accountName;
    @XmlElement(required = true)
    protected String accountType;
    @XmlElement(required = true)
    protected String currencyCode;
    @XmlElement(required = true)
    protected String taxId;
    protected String salesForceAccountName;
    protected Boolean createSalesForceAccount;
    @XmlElement(required = true)
    protected String defaultCommunicationPlan;
    @XmlElement(required = true)
    protected String defaultRatePlan;
    protected Boolean createDefaultRatePlan;
    protected String billingCycleStart;
    @XmlElement(required = true)
    protected ContactType primaryContact;
    @XmlElement(required = true)
    protected ContactType billingContact;
    @XmlElement(required = true)
    protected AddressType billingAddress;
    @XmlElement(required = true)
    protected AddressType shippingAddress;
    @XmlElement(required = true)
    protected String timezone;
    @XmlElement(required = true)
    protected String preferredLanguage;
    protected String notes;
    protected String supportEmail;
    protected String supportPhone;
    protected String devKitType;
    protected Boolean showJasperSupport;
    protected Boolean showSpSupport;
    protected Boolean showAcctSupport;
    protected String regionId;
    protected String accountSegment;

    /**
     * Gets the value of the serviceProvider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceProvider() {
        return serviceProvider;
    }

    /**
     * Sets the value of the serviceProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceProvider(String value) {
        this.serviceProvider = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the taxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Sets the value of the taxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxId(String value) {
        this.taxId = value;
    }

    /**
     * Gets the value of the salesForceAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesForceAccountName() {
        return salesForceAccountName;
    }

    /**
     * Sets the value of the salesForceAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesForceAccountName(String value) {
        this.salesForceAccountName = value;
    }

    /**
     * Gets the value of the createSalesForceAccount property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCreateSalesForceAccount() {
        return createSalesForceAccount;
    }

    /**
     * Sets the value of the createSalesForceAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreateSalesForceAccount(Boolean value) {
        this.createSalesForceAccount = value;
    }

    /**
     * Gets the value of the defaultCommunicationPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultCommunicationPlan() {
        return defaultCommunicationPlan;
    }

    /**
     * Sets the value of the defaultCommunicationPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultCommunicationPlan(String value) {
        this.defaultCommunicationPlan = value;
    }

    /**
     * Gets the value of the defaultRatePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultRatePlan() {
        return defaultRatePlan;
    }

    /**
     * Sets the value of the defaultRatePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultRatePlan(String value) {
        this.defaultRatePlan = value;
    }

    /**
     * Gets the value of the createDefaultRatePlan property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCreateDefaultRatePlan() {
        return createDefaultRatePlan;
    }

    /**
     * Sets the value of the createDefaultRatePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreateDefaultRatePlan(Boolean value) {
        this.createDefaultRatePlan = value;
    }

    /**
     * Gets the value of the billingCycleStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCycleStart() {
        return billingCycleStart;
    }

    /**
     * Sets the value of the billingCycleStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCycleStart(String value) {
        this.billingCycleStart = value;
    }

    /**
     * Gets the value of the primaryContact property.
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getPrimaryContact() {
        return primaryContact;
    }

    /**
     * Sets the value of the primaryContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setPrimaryContact(ContactType value) {
        this.primaryContact = value;
    }

    /**
     * Gets the value of the billingContact property.
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getBillingContact() {
        return billingContact;
    }

    /**
     * Sets the value of the billingContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setBillingContact(ContactType value) {
        this.billingContact = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setBillingAddress(AddressType value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the shippingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Sets the value of the shippingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setShippingAddress(AddressType value) {
        this.shippingAddress = value;
    }

    /**
     * Gets the value of the timezone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezone(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the preferredLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    /**
     * Sets the value of the preferredLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredLanguage(String value) {
        this.preferredLanguage = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the supportEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportEmail() {
        return supportEmail;
    }

    /**
     * Sets the value of the supportEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportEmail(String value) {
        this.supportEmail = value;
    }

    /**
     * Gets the value of the supportPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportPhone() {
        return supportPhone;
    }

    /**
     * Sets the value of the supportPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportPhone(String value) {
        this.supportPhone = value;
    }

    /**
     * Gets the value of the devKitType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevKitType() {
        return devKitType;
    }

    /**
     * Sets the value of the devKitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevKitType(String value) {
        this.devKitType = value;
    }

    /**
     * Gets the value of the showJasperSupport property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowJasperSupport() {
        return showJasperSupport;
    }

    /**
     * Sets the value of the showJasperSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowJasperSupport(Boolean value) {
        this.showJasperSupport = value;
    }

    /**
     * Gets the value of the showSpSupport property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowSpSupport() {
        return showSpSupport;
    }

    /**
     * Sets the value of the showSpSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowSpSupport(Boolean value) {
        this.showSpSupport = value;
    }

    /**
     * Gets the value of the showAcctSupport property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowAcctSupport() {
        return showAcctSupport;
    }

    /**
     * Sets the value of the showAcctSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowAcctSupport(Boolean value) {
        this.showAcctSupport = value;
    }

    /**
     * Gets the value of the regionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * Sets the value of the regionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionId(String value) {
        this.regionId = value;
    }

    /**
     * Gets the value of the accountSegment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountSegment() {
        return accountSegment;
    }

    /**
     * Sets the value of the accountSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountSegment(String value) {
        this.accountSegment = value;
    }

}
