
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryContact" type="{http://api.jasperwireless.com/ws/schema}ContactType" minOccurs="0"/>
 *         &lt;element name="shippingAddress" type="{http://api.jasperwireless.com/ws/schema}AddressType" minOccurs="0"/>
 *         &lt;element name="billing" type="{http://api.jasperwireless.com/ws/schema}AccountBillingType" minOccurs="0"/>
 *         &lt;element name="support" type="{http://api.jasperwireless.com/ws/schema}AccountSupportType" minOccurs="0"/>
 *         &lt;element name="customFields" type="{http://api.jasperwireless.com/ws/schema}AccountCustomFieldsType" minOccurs="0"/>
 *         &lt;element name="locationServiceType" type="{http://api.jasperwireless.com/ws/schema}LocationServiceType" minOccurs="0"/>
 *         &lt;element name="locationServiceMim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountType", propOrder = {
    "accountId",
    "accountName",
    "billable",
    "status",
    "operatorAccountId",
    "primaryContact",
    "shippingAddress",
    "billing",
    "support",
    "customFields",
    "locationServiceType",
    "locationServiceMim"
})
public class AccountType {

    protected long accountId;
    protected String accountName;
    protected Boolean billable;
    protected String status;
    protected String operatorAccountId;
    protected ContactType primaryContact;
    protected AddressType shippingAddress;
    protected AccountBillingType billing;
    protected AccountSupportType support;
    protected AccountCustomFieldsType customFields;
    @XmlSchemaType(name = "string")
    protected LocationServiceType locationServiceType;
    protected String locationServiceMim;

    /**
     * Gets the value of the accountId property.
     * 
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     */
    public void setAccountId(long value) {
        this.accountId = value;
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
     * Gets the value of the billable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBillable() {
        return billable;
    }

    /**
     * Sets the value of the billable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBillable(Boolean value) {
        this.billable = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the operatorAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorAccountId() {
        return operatorAccountId;
    }

    /**
     * Sets the value of the operatorAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorAccountId(String value) {
        this.operatorAccountId = value;
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
     * Gets the value of the billing property.
     * 
     * @return
     *     possible object is
     *     {@link AccountBillingType }
     *     
     */
    public AccountBillingType getBilling() {
        return billing;
    }

    /**
     * Sets the value of the billing property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountBillingType }
     *     
     */
    public void setBilling(AccountBillingType value) {
        this.billing = value;
    }

    /**
     * Gets the value of the support property.
     * 
     * @return
     *     possible object is
     *     {@link AccountSupportType }
     *     
     */
    public AccountSupportType getSupport() {
        return support;
    }

    /**
     * Sets the value of the support property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSupportType }
     *     
     */
    public void setSupport(AccountSupportType value) {
        this.support = value;
    }

    /**
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link AccountCustomFieldsType }
     *     
     */
    public AccountCustomFieldsType getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountCustomFieldsType }
     *     
     */
    public void setCustomFields(AccountCustomFieldsType value) {
        this.customFields = value;
    }

    /**
     * Gets the value of the locationServiceType property.
     * 
     * @return
     *     possible object is
     *     {@link LocationServiceType }
     *     
     */
    public LocationServiceType getLocationServiceType() {
        return locationServiceType;
    }

    /**
     * Sets the value of the locationServiceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationServiceType }
     *     
     */
    public void setLocationServiceType(LocationServiceType value) {
        this.locationServiceType = value;
    }

    /**
     * Gets the value of the locationServiceMim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationServiceMim() {
        return locationServiceMim;
    }

    /**
     * Sets the value of the locationServiceMim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationServiceMim(String value) {
        this.locationServiceMim = value;
    }

}
