
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SimInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="simProfileId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="acctId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="live" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ratePlanId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="activationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="modemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overAgeLimitToOverrideMode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="overAgeLimitReached" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="overAgeLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="endConsumerIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commPlanId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lastStateChange" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="testReadyDataLimit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="monthToDateSmsUsage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="monthToDateVoiceUsage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthToDateDataUsage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="oneBox" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateShipped" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="testReadyDataState" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="testReadyTimeLimit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="testReadyTimeState" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="custom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="operatorId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="networkBlocked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="consumerUserId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateOneBoxModified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="linePayStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imsi1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryDevice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nickName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="migratedSim" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="saleDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimInfo", propOrder = {

})
public class SimInfo {

    @XmlElement(required = true)
    protected String iccid;
    protected String msisdn;
    protected String deviceId;
    protected Long simProfileId;
    protected long acctId;
    protected boolean live;
    protected Long ratePlanId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationDate;
    protected Long status;
    protected String modemId;
    protected String customer;
    protected Long overAgeLimitToOverrideMode;
    protected Boolean overAgeLimitReached;
    protected BigDecimal overAgeLimit;
    protected Long customerId;
    protected String endConsumerIdentity;
    protected Long commPlanId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastStateChange;
    protected Long testReadyDataLimit;
    protected BigDecimal monthToDateSmsUsage;
    protected BigDecimal monthToDateVoiceUsage;
    protected String userName;
    protected String password;
    protected BigDecimal monthToDateDataUsage;
    protected String oneBox;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateShipped;
    protected Long testReadyDataState;
    protected Long testReadyTimeLimit;
    protected Long testReadyTimeState;
    protected String custom1;
    protected String custom2;
    protected String custom3;
    protected long version;
    protected long operatorId;
    protected String notes;
    protected boolean networkBlocked;
    protected Long consumerUserId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOneBoxModified;
    protected String linePayStatus;
    protected String imsi1;
    protected Boolean primaryDevice;
    protected String nickName;
    protected String areaCode;
    protected String countryCode;
    protected Boolean migratedSim;
    protected String saleDate;

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
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the simProfileId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSimProfileId() {
        return simProfileId;
    }

    /**
     * Sets the value of the simProfileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSimProfileId(Long value) {
        this.simProfileId = value;
    }

    /**
     * Gets the value of the acctId property.
     * 
     */
    public long getAcctId() {
        return acctId;
    }

    /**
     * Sets the value of the acctId property.
     * 
     */
    public void setAcctId(long value) {
        this.acctId = value;
    }

    /**
     * Gets the value of the live property.
     * 
     */
    public boolean isLive() {
        return live;
    }

    /**
     * Sets the value of the live property.
     * 
     */
    public void setLive(boolean value) {
        this.live = value;
    }

    /**
     * Gets the value of the ratePlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRatePlanId() {
        return ratePlanId;
    }

    /**
     * Sets the value of the ratePlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRatePlanId(Long value) {
        this.ratePlanId = value;
    }

    /**
     * Gets the value of the activationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActivationDate() {
        return activationDate;
    }

    /**
     * Sets the value of the activationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActivationDate(XMLGregorianCalendar value) {
        this.activationDate = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStatus(Long value) {
        this.status = value;
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
     * Gets the value of the overAgeLimitToOverrideMode property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOverAgeLimitToOverrideMode() {
        return overAgeLimitToOverrideMode;
    }

    /**
     * Sets the value of the overAgeLimitToOverrideMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOverAgeLimitToOverrideMode(Long value) {
        this.overAgeLimitToOverrideMode = value;
    }

    /**
     * Gets the value of the overAgeLimitReached property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOverAgeLimitReached() {
        return overAgeLimitReached;
    }

    /**
     * Sets the value of the overAgeLimitReached property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOverAgeLimitReached(Boolean value) {
        this.overAgeLimitReached = value;
    }

    /**
     * Gets the value of the overAgeLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOverAgeLimit() {
        return overAgeLimit;
    }

    /**
     * Sets the value of the overAgeLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOverAgeLimit(BigDecimal value) {
        this.overAgeLimit = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomerId(Long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the endConsumerIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndConsumerIdentity() {
        return endConsumerIdentity;
    }

    /**
     * Sets the value of the endConsumerIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndConsumerIdentity(String value) {
        this.endConsumerIdentity = value;
    }

    /**
     * Gets the value of the commPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCommPlanId() {
        return commPlanId;
    }

    /**
     * Sets the value of the commPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCommPlanId(Long value) {
        this.commPlanId = value;
    }

    /**
     * Gets the value of the lastStateChange property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastStateChange() {
        return lastStateChange;
    }

    /**
     * Sets the value of the lastStateChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastStateChange(XMLGregorianCalendar value) {
        this.lastStateChange = value;
    }

    /**
     * Gets the value of the testReadyDataLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestReadyDataLimit() {
        return testReadyDataLimit;
    }

    /**
     * Sets the value of the testReadyDataLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestReadyDataLimit(Long value) {
        this.testReadyDataLimit = value;
    }

    /**
     * Gets the value of the monthToDateSmsUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthToDateSmsUsage() {
        return monthToDateSmsUsage;
    }

    /**
     * Sets the value of the monthToDateSmsUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthToDateSmsUsage(BigDecimal value) {
        this.monthToDateSmsUsage = value;
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
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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
     * Gets the value of the oneBox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOneBox() {
        return oneBox;
    }

    /**
     * Sets the value of the oneBox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOneBox(String value) {
        this.oneBox = value;
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
     * Gets the value of the testReadyDataState property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestReadyDataState() {
        return testReadyDataState;
    }

    /**
     * Sets the value of the testReadyDataState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestReadyDataState(Long value) {
        this.testReadyDataState = value;
    }

    /**
     * Gets the value of the testReadyTimeLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestReadyTimeLimit() {
        return testReadyTimeLimit;
    }

    /**
     * Sets the value of the testReadyTimeLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestReadyTimeLimit(Long value) {
        this.testReadyTimeLimit = value;
    }

    /**
     * Gets the value of the testReadyTimeState property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestReadyTimeState() {
        return testReadyTimeState;
    }

    /**
     * Sets the value of the testReadyTimeState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestReadyTimeState(Long value) {
        this.testReadyTimeState = value;
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
     * Gets the value of the version property.
     * 
     */
    public long getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     */
    public void setVersion(long value) {
        this.version = value;
    }

    /**
     * Gets the value of the operatorId property.
     * 
     */
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * Sets the value of the operatorId property.
     * 
     */
    public void setOperatorId(long value) {
        this.operatorId = value;
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
     * Gets the value of the networkBlocked property.
     * 
     */
    public boolean isNetworkBlocked() {
        return networkBlocked;
    }

    /**
     * Sets the value of the networkBlocked property.
     * 
     */
    public void setNetworkBlocked(boolean value) {
        this.networkBlocked = value;
    }

    /**
     * Gets the value of the consumerUserId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConsumerUserId() {
        return consumerUserId;
    }

    /**
     * Sets the value of the consumerUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConsumerUserId(Long value) {
        this.consumerUserId = value;
    }

    /**
     * Gets the value of the dateOneBoxModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOneBoxModified() {
        return dateOneBoxModified;
    }

    /**
     * Sets the value of the dateOneBoxModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOneBoxModified(XMLGregorianCalendar value) {
        this.dateOneBoxModified = value;
    }

    /**
     * Gets the value of the linePayStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinePayStatus() {
        return linePayStatus;
    }

    /**
     * Sets the value of the linePayStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinePayStatus(String value) {
        this.linePayStatus = value;
    }

    /**
     * Gets the value of the imsi1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsi1() {
        return imsi1;
    }

    /**
     * Sets the value of the imsi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsi1(String value) {
        this.imsi1 = value;
    }

    /**
     * Gets the value of the primaryDevice property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrimaryDevice() {
        return primaryDevice;
    }

    /**
     * Sets the value of the primaryDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrimaryDevice(Boolean value) {
        this.primaryDevice = value;
    }

    /**
     * Gets the value of the nickName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the value of the nickName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the migratedSim property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMigratedSim() {
        return migratedSim;
    }

    /**
     * Sets the value of the migratedSim property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMigratedSim(Boolean value) {
        this.migratedSim = value;
    }

    /**
     * Gets the value of the saleDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaleDate() {
        return saleDate;
    }

    /**
     * Sets the value of the saleDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaleDate(String value) {
        this.saleDate = value;
    }

}
