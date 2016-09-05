
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * 
 *         The detail information about a Terminal.
 *     
 * 
 * <p>Java class for TerminalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TerminalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endConsumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suspended" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratePlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monthToDateUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="overageLimitReached" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="overageLimitOverride" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateActivated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dateModified" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dateShipped" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="monthToDateDataUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="monthToDateSMSUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="monthToDateVoiceUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="secureSimId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rating">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="termStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="termEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="renewalPolicy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="renewalRatePlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="totalPrimaryIncludedData" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="primaryDataRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="totalPrimaryIncludedSMS" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                   &lt;element name="primarySMSRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="secureSimUsernameCopyRule" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="secureSimPasswordCopyRule" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fixedIpAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ctdSessionCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="customerCustom1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerCustom2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerCustom3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerCustom4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerCustom5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorCustom1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorCustom2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorCustom3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorCustom4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorCustom5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryICCID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="globalSimType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="simNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custom4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom6" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom7" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom8" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom9" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="custom10" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TerminalType", propOrder = {
    "iccid",
    "terminalId",
    "modemId",
    "customer",
    "endConsumerId",
    "suspended",
    "ratePlan",
    "status",
    "monthToDateUsage",
    "overageLimitReached",
    "overageLimitOverride",
    "dateActivated",
    "dateAdded",
    "dateModified",
    "dateShipped",
    "monthToDateDataUsage",
    "monthToDateSMSUsage",
    "monthToDateVoiceUsage",
    "secureSimId",
    "custom1",
    "custom2",
    "custom3",
    "rating",
    "secureSimUsernameCopyRule",
    "secureSimPasswordCopyRule",
    "accountId",
    "fixedIpAddress",
    "ctdSessionCount",
    "customerCustom1",
    "customerCustom2",
    "customerCustom3",
    "customerCustom4",
    "customerCustom5",
    "operatorCustom1",
    "operatorCustom2",
    "operatorCustom3",
    "operatorCustom4",
    "operatorCustom5",
    "imsi",
    "primaryICCID",
    "imei",
    "globalSimType",
    "simNotes",
    "version",
    "custom4",
    "custom5",
    "custom6",
    "custom7",
    "custom8",
    "custom9",
    "custom10",
    "any"
})
public class TerminalType {

    @XmlElement(required = true)
    protected String iccid;
    protected String terminalId;
    protected String modemId;
    protected String customer;
    protected String endConsumerId;
    @XmlElement(required = true)
    protected String suspended;
    @XmlElement(required = true)
    protected String ratePlan;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected BigDecimal monthToDateUsage;
    protected boolean overageLimitReached;
    @XmlElement(required = true)
    protected String overageLimitOverride;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateActivated;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAdded;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateModified;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateShipped;
    @XmlElement(required = true)
    protected BigDecimal monthToDateDataUsage;
    @XmlElement(required = true)
    protected BigDecimal monthToDateSMSUsage;
    @XmlElement(required = true)
    protected BigDecimal monthToDateVoiceUsage;
    @XmlElement(required = true)
    protected String secureSimId;
    @XmlElement(required = true)
    protected String custom1;
    @XmlElement(required = true)
    protected String custom2;
    @XmlElement(required = true)
    protected String custom3;
    @XmlElement(required = true)
    protected TerminalType.Rating rating;
    @XmlElement(required = true)
    protected String secureSimUsernameCopyRule;
    @XmlElement(required = true)
    protected String secureSimPasswordCopyRule;
    protected long accountId;
    @XmlElement(required = true, nillable = true)
    protected String fixedIpAddress;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long ctdSessionCount;
    @XmlElement(required = true)
    protected String customerCustom1;
    @XmlElement(required = true)
    protected String customerCustom2;
    @XmlElement(required = true)
    protected String customerCustom3;
    @XmlElement(required = true)
    protected String customerCustom4;
    @XmlElement(required = true)
    protected String customerCustom5;
    @XmlElement(required = true)
    protected String operatorCustom1;
    @XmlElement(required = true)
    protected String operatorCustom2;
    @XmlElement(required = true)
    protected String operatorCustom3;
    @XmlElement(required = true)
    protected String operatorCustom4;
    @XmlElement(required = true)
    protected String operatorCustom5;
    @XmlElement(required = true)
    protected String imsi;
    protected String primaryICCID;
    protected String imei;
    protected String globalSimType;
    protected String simNotes;
    protected int version;
    @XmlElement(required = true)
    protected String custom4;
    @XmlElement(required = true)
    protected String custom5;
    @XmlElement(required = true)
    protected String custom6;
    @XmlElement(required = true)
    protected String custom7;
    @XmlElement(required = true)
    protected String custom8;
    @XmlElement(required = true)
    protected String custom9;
    @XmlElement(required = true)
    protected String custom10;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "msisdn")
    protected String msisdn;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the iccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * Sets the value of the iccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIccid(String value) {
        this.iccid = value;
    }

    /**
     * Gets the value of the terminalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Sets the value of the terminalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    /**
     * Gets the value of the modemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModemId() {
        return modemId;
    }

    /**
     * Sets the value of the modemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModemId(String value) {
        this.modemId = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomer(String value) {
        this.customer = value;
    }

    /**
     * Gets the value of the endConsumerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndConsumerId() {
        return endConsumerId;
    }

    /**
     * Sets the value of the endConsumerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndConsumerId(String value) {
        this.endConsumerId = value;
    }

    /**
     * Gets the value of the suspended property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuspended() {
        return suspended;
    }

    /**
     * Sets the value of the suspended property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuspended(String value) {
        this.suspended = value;
    }

    /**
     * Gets the value of the ratePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlan() {
        return ratePlan;
    }

    /**
     * Sets the value of the ratePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlan(String value) {
        this.ratePlan = value;
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
     * Gets the value of the monthToDateUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthToDateUsage() {
        return monthToDateUsage;
    }

    /**
     * Sets the value of the monthToDateUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthToDateUsage(BigDecimal value) {
        this.monthToDateUsage = value;
    }

    /**
     * Gets the value of the overageLimitReached property.
     * 
     */
    public boolean isOverageLimitReached() {
        return overageLimitReached;
    }

    /**
     * Sets the value of the overageLimitReached property.
     * 
     */
    public void setOverageLimitReached(boolean value) {
        this.overageLimitReached = value;
    }

    /**
     * Gets the value of the overageLimitOverride property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverageLimitOverride() {
        return overageLimitOverride;
    }

    /**
     * Sets the value of the overageLimitOverride property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverageLimitOverride(String value) {
        this.overageLimitOverride = value;
    }

    /**
     * Gets the value of the dateActivated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateActivated() {
        return dateActivated;
    }

    /**
     * Sets the value of the dateActivated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateActivated(XMLGregorianCalendar value) {
        this.dateActivated = value;
    }

    /**
     * Gets the value of the dateAdded property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets the value of the dateAdded property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAdded(XMLGregorianCalendar value) {
        this.dateAdded = value;
    }

    /**
     * Gets the value of the dateModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateModified() {
        return dateModified;
    }

    /**
     * Sets the value of the dateModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateModified(XMLGregorianCalendar value) {
        this.dateModified = value;
    }

    /**
     * Gets the value of the dateShipped property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateShipped() {
        return dateShipped;
    }

    /**
     * Sets the value of the dateShipped property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateShipped(XMLGregorianCalendar value) {
        this.dateShipped = value;
    }

    /**
     * Gets the value of the monthToDateDataUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthToDateDataUsage() {
        return monthToDateDataUsage;
    }

    /**
     * Sets the value of the monthToDateDataUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthToDateDataUsage(BigDecimal value) {
        this.monthToDateDataUsage = value;
    }

    /**
     * Gets the value of the monthToDateSMSUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthToDateSMSUsage() {
        return monthToDateSMSUsage;
    }

    /**
     * Sets the value of the monthToDateSMSUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthToDateSMSUsage(BigDecimal value) {
        this.monthToDateSMSUsage = value;
    }

    /**
     * Gets the value of the monthToDateVoiceUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthToDateVoiceUsage() {
        return monthToDateVoiceUsage;
    }

    /**
     * Sets the value of the monthToDateVoiceUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthToDateVoiceUsage(BigDecimal value) {
        this.monthToDateVoiceUsage = value;
    }

    /**
     * Gets the value of the secureSimId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureSimId() {
        return secureSimId;
    }

    /**
     * Sets the value of the secureSimId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureSimId(String value) {
        this.secureSimId = value;
    }

    /**
     * Gets the value of the custom1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom1() {
        return custom1;
    }

    /**
     * Sets the value of the custom1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom1(String value) {
        this.custom1 = value;
    }

    /**
     * Gets the value of the custom2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom2() {
        return custom2;
    }

    /**
     * Sets the value of the custom2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom2(String value) {
        this.custom2 = value;
    }

    /**
     * Gets the value of the custom3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom3() {
        return custom3;
    }

    /**
     * Sets the value of the custom3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom3(String value) {
        this.custom3 = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     * @return
     *     possible object is
     *     {@link TerminalType.Rating }
     *     
     */
    public TerminalType.Rating getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     * @param value
     *     allowed object is
     *     {@link TerminalType.Rating }
     *     
     */
    public void setRating(TerminalType.Rating value) {
        this.rating = value;
    }

    /**
     * Gets the value of the secureSimUsernameCopyRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureSimUsernameCopyRule() {
        return secureSimUsernameCopyRule;
    }

    /**
     * Sets the value of the secureSimUsernameCopyRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureSimUsernameCopyRule(String value) {
        this.secureSimUsernameCopyRule = value;
    }

    /**
     * Gets the value of the secureSimPasswordCopyRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureSimPasswordCopyRule() {
        return secureSimPasswordCopyRule;
    }

    /**
     * Sets the value of the secureSimPasswordCopyRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureSimPasswordCopyRule(String value) {
        this.secureSimPasswordCopyRule = value;
    }

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
     * Gets the value of the fixedIpAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedIpAddress() {
        return fixedIpAddress;
    }

    /**
     * Sets the value of the fixedIpAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedIpAddress(String value) {
        this.fixedIpAddress = value;
    }

    /**
     * Gets the value of the ctdSessionCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCtdSessionCount() {
        return ctdSessionCount;
    }

    /**
     * Sets the value of the ctdSessionCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCtdSessionCount(Long value) {
        this.ctdSessionCount = value;
    }

    /**
     * Gets the value of the customerCustom1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCustom1() {
        return customerCustom1;
    }

    /**
     * Sets the value of the customerCustom1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCustom1(String value) {
        this.customerCustom1 = value;
    }

    /**
     * Gets the value of the customerCustom2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCustom2() {
        return customerCustom2;
    }

    /**
     * Sets the value of the customerCustom2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCustom2(String value) {
        this.customerCustom2 = value;
    }

    /**
     * Gets the value of the customerCustom3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCustom3() {
        return customerCustom3;
    }

    /**
     * Sets the value of the customerCustom3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCustom3(String value) {
        this.customerCustom3 = value;
    }

    /**
     * Gets the value of the customerCustom4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCustom4() {
        return customerCustom4;
    }

    /**
     * Sets the value of the customerCustom4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCustom4(String value) {
        this.customerCustom4 = value;
    }

    /**
     * Gets the value of the customerCustom5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCustom5() {
        return customerCustom5;
    }

    /**
     * Sets the value of the customerCustom5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCustom5(String value) {
        this.customerCustom5 = value;
    }

    /**
     * Gets the value of the operatorCustom1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCustom1() {
        return operatorCustom1;
    }

    /**
     * Sets the value of the operatorCustom1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCustom1(String value) {
        this.operatorCustom1 = value;
    }

    /**
     * Gets the value of the operatorCustom2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCustom2() {
        return operatorCustom2;
    }

    /**
     * Sets the value of the operatorCustom2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCustom2(String value) {
        this.operatorCustom2 = value;
    }

    /**
     * Gets the value of the operatorCustom3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCustom3() {
        return operatorCustom3;
    }

    /**
     * Sets the value of the operatorCustom3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCustom3(String value) {
        this.operatorCustom3 = value;
    }

    /**
     * Gets the value of the operatorCustom4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCustom4() {
        return operatorCustom4;
    }

    /**
     * Sets the value of the operatorCustom4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCustom4(String value) {
        this.operatorCustom4 = value;
    }

    /**
     * Gets the value of the operatorCustom5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCustom5() {
        return operatorCustom5;
    }

    /**
     * Sets the value of the operatorCustom5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCustom5(String value) {
        this.operatorCustom5 = value;
    }

    /**
     * Gets the value of the imsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * Sets the value of the imsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsi(String value) {
        this.imsi = value;
    }

    /**
     * Gets the value of the primaryICCID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryICCID() {
        return primaryICCID;
    }

    /**
     * Sets the value of the primaryICCID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryICCID(String value) {
        this.primaryICCID = value;
    }

    /**
     * Gets the value of the imei property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImei() {
        return imei;
    }

    /**
     * Sets the value of the imei property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImei(String value) {
        this.imei = value;
    }

    /**
     * Gets the value of the globalSimType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalSimType() {
        return globalSimType;
    }

    /**
     * Sets the value of the globalSimType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalSimType(String value) {
        this.globalSimType = value;
    }

    /**
     * Gets the value of the simNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimNotes() {
        return simNotes;
    }

    /**
     * Sets the value of the simNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimNotes(String value) {
        this.simNotes = value;
    }

    /**
     * Gets the value of the version property.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

    /**
     * Gets the value of the custom4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom4() {
        return custom4;
    }

    /**
     * Sets the value of the custom4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom4(String value) {
        this.custom4 = value;
    }

    /**
     * Gets the value of the custom5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom5() {
        return custom5;
    }

    /**
     * Sets the value of the custom5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom5(String value) {
        this.custom5 = value;
    }

    /**
     * Gets the value of the custom6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom6() {
        return custom6;
    }

    /**
     * Sets the value of the custom6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom6(String value) {
        this.custom6 = value;
    }

    /**
     * Gets the value of the custom7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom7() {
        return custom7;
    }

    /**
     * Sets the value of the custom7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom7(String value) {
        this.custom7 = value;
    }

    /**
     * Gets the value of the custom8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom8() {
        return custom8;
    }

    /**
     * Sets the value of the custom8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom8(String value) {
        this.custom8 = value;
    }

    /**
     * Gets the value of the custom9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom9() {
        return custom9;
    }

    /**
     * Sets the value of the custom9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom9(String value) {
        this.custom9 = value;
    }

    /**
     * Gets the value of the custom10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom10() {
        return custom10;
    }

    /**
     * Sets the value of the custom10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom10(String value) {
        this.custom10 = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="termStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="termEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="renewalPolicy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="renewalRatePlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="totalPrimaryIncludedData" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="primaryDataRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="totalPrimaryIncludedSMS" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *         &lt;element name="primarySMSRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "termStartDate",
        "termEndDate",
        "renewalPolicy",
        "renewalRatePlan",
        "totalPrimaryIncludedData",
        "primaryDataRemaining",
        "totalPrimaryIncludedSMS",
        "primarySMSRemaining"
    })
    public static class Rating {

        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar termStartDate;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar termEndDate;
        protected String renewalPolicy;
        protected String renewalRatePlan;
        protected BigDecimal totalPrimaryIncludedData;
        protected BigDecimal primaryDataRemaining;
        protected BigDecimal totalPrimaryIncludedSMS;
        protected BigDecimal primarySMSRemaining;

        /**
         * Gets the value of the termStartDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTermStartDate() {
            return termStartDate;
        }

        /**
         * Sets the value of the termStartDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTermStartDate(XMLGregorianCalendar value) {
            this.termStartDate = value;
        }

        /**
         * Gets the value of the termEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTermEndDate() {
            return termEndDate;
        }

        /**
         * Sets the value of the termEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTermEndDate(XMLGregorianCalendar value) {
            this.termEndDate = value;
        }

        /**
         * Gets the value of the renewalPolicy property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRenewalPolicy() {
            return renewalPolicy;
        }

        /**
         * Sets the value of the renewalPolicy property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRenewalPolicy(String value) {
            this.renewalPolicy = value;
        }

        /**
         * Gets the value of the renewalRatePlan property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRenewalRatePlan() {
            return renewalRatePlan;
        }

        /**
         * Sets the value of the renewalRatePlan property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRenewalRatePlan(String value) {
            this.renewalRatePlan = value;
        }

        /**
         * Gets the value of the totalPrimaryIncludedData property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalPrimaryIncludedData() {
            return totalPrimaryIncludedData;
        }

        /**
         * Sets the value of the totalPrimaryIncludedData property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalPrimaryIncludedData(BigDecimal value) {
            this.totalPrimaryIncludedData = value;
        }

        /**
         * Gets the value of the primaryDataRemaining property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getPrimaryDataRemaining() {
            return primaryDataRemaining;
        }

        /**
         * Sets the value of the primaryDataRemaining property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPrimaryDataRemaining(BigDecimal value) {
            this.primaryDataRemaining = value;
        }

        /**
         * Gets the value of the totalPrimaryIncludedSMS property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalPrimaryIncludedSMS() {
            return totalPrimaryIncludedSMS;
        }

        /**
         * Sets the value of the totalPrimaryIncludedSMS property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalPrimaryIncludedSMS(BigDecimal value) {
            this.totalPrimaryIncludedSMS = value;
        }

        /**
         * Gets the value of the primarySMSRemaining property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getPrimarySMSRemaining() {
            return primarySMSRemaining;
        }

        /**
         * Sets the value of the primarySMSRemaining property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPrimarySMSRemaining(BigDecimal value) {
            this.primarySMSRemaining = value;
        }

    }

}
