
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SimAuxFieldInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimAuxFieldInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="OPERATORCUSTOM1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OPERATORCUSTOM2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OPERATORCUSTOM3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OPERATORCUSTOM4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OPERATORCUSTOM5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerCustom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerCustom4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerCustom5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bootstrapIccid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="policyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="policyTierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cfNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cfType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voiceMailId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="imei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msisdnFromApi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mmscProvisioned" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="globalSimType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendOTAOnFirstRegistration" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="clusterChanges" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="clusterChangeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="checkedClusterCountryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="checkedOTATime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="imeiTechType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerIMEITechType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerIMEI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ppuID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orderID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="opmNotificationSent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="settlementRatePlanID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="custom10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimAuxFieldInfo", propOrder = {

})
public class SimAuxFieldInfo {

    @XmlElement(name = "OPERATORCUSTOM1")
    protected String operatorcustom1;
    @XmlElement(name = "OPERATORCUSTOM2")
    protected String operatorcustom2;
    @XmlElement(name = "OPERATORCUSTOM3")
    protected String operatorcustom3;
    @XmlElement(name = "OPERATORCUSTOM4")
    protected String operatorcustom4;
    @XmlElement(name = "OPERATORCUSTOM5")
    protected String operatorcustom5;
    protected String customerCustom1;
    protected String customerCustom2;
    protected String customerCustom3;
    protected String customerCustom4;
    protected String customerCustom5;
    protected String bootstrapIccid;
    protected Long policyId;
    protected String policyTierName;
    protected String cfNumber;
    protected String cfType;
    protected Long voiceMailId;
    protected String imei;
    protected String pin1;
    protected String pin2;
    protected String puk1;
    protected String puk2;
    protected Boolean msisdnFromApi;
    protected Boolean mmscProvisioned;
    protected String globalSimType;
    protected String timeZone;
    protected Boolean sendOTAOnFirstRegistration;
    protected Long clusterChanges;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar clusterChangeTime;
    protected Long checkedClusterCountryId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar checkedOTATime;
    protected String imeiTechType;
    protected String customerIMEITechType;
    protected String customerIMEI;
    protected Long ppuID;
    protected Long orderID;
    protected Boolean opmNotificationSent;
    protected Long settlementRatePlanID;
    protected String custom10;
    protected String custom9;
    protected String custom8;
    protected String custom7;
    protected String custom6;
    protected String custom5;
    protected String custom4;

    /**
     * Gets the value of the operatorcustom1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPERATORCUSTOM1() {
        return operatorcustom1;
    }

    /**
     * Sets the value of the operatorcustom1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPERATORCUSTOM1(String value) {
        this.operatorcustom1 = value;
    }

    /**
     * Gets the value of the operatorcustom2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPERATORCUSTOM2() {
        return operatorcustom2;
    }

    /**
     * Sets the value of the operatorcustom2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPERATORCUSTOM2(String value) {
        this.operatorcustom2 = value;
    }

    /**
     * Gets the value of the operatorcustom3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPERATORCUSTOM3() {
        return operatorcustom3;
    }

    /**
     * Sets the value of the operatorcustom3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPERATORCUSTOM3(String value) {
        this.operatorcustom3 = value;
    }

    /**
     * Gets the value of the operatorcustom4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPERATORCUSTOM4() {
        return operatorcustom4;
    }

    /**
     * Sets the value of the operatorcustom4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPERATORCUSTOM4(String value) {
        this.operatorcustom4 = value;
    }

    /**
     * Gets the value of the operatorcustom5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPERATORCUSTOM5() {
        return operatorcustom5;
    }

    /**
     * Sets the value of the operatorcustom5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPERATORCUSTOM5(String value) {
        this.operatorcustom5 = value;
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
     * Gets the value of the bootstrapIccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBootstrapIccid() {
        return bootstrapIccid;
    }

    /**
     * Sets the value of the bootstrapIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBootstrapIccid(String value) {
        this.bootstrapIccid = value;
    }

    /**
     * Gets the value of the policyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPolicyId() {
        return policyId;
    }

    /**
     * Sets the value of the policyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPolicyId(Long value) {
        this.policyId = value;
    }

    /**
     * Gets the value of the policyTierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyTierName() {
        return policyTierName;
    }

    /**
     * Sets the value of the policyTierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyTierName(String value) {
        this.policyTierName = value;
    }

    /**
     * Gets the value of the cfNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfNumber() {
        return cfNumber;
    }

    /**
     * Sets the value of the cfNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfNumber(String value) {
        this.cfNumber = value;
    }

    /**
     * Gets the value of the cfType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfType() {
        return cfType;
    }

    /**
     * Sets the value of the cfType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfType(String value) {
        this.cfType = value;
    }

    /**
     * Gets the value of the voiceMailId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVoiceMailId() {
        return voiceMailId;
    }

    /**
     * Sets the value of the voiceMailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVoiceMailId(Long value) {
        this.voiceMailId = value;
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
     * Gets the value of the pin1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin1() {
        return pin1;
    }

    /**
     * Sets the value of the pin1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin1(String value) {
        this.pin1 = value;
    }

    /**
     * Gets the value of the pin2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin2() {
        return pin2;
    }

    /**
     * Sets the value of the pin2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin2(String value) {
        this.pin2 = value;
    }

    /**
     * Gets the value of the puk1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk1() {
        return puk1;
    }

    /**
     * Sets the value of the puk1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk1(String value) {
        this.puk1 = value;
    }

    /**
     * Gets the value of the puk2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk2() {
        return puk2;
    }

    /**
     * Sets the value of the puk2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk2(String value) {
        this.puk2 = value;
    }

    /**
     * Gets the value of the msisdnFromApi property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMsisdnFromApi() {
        return msisdnFromApi;
    }

    /**
     * Sets the value of the msisdnFromApi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMsisdnFromApi(Boolean value) {
        this.msisdnFromApi = value;
    }

    /**
     * Gets the value of the mmscProvisioned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMmscProvisioned() {
        return mmscProvisioned;
    }

    /**
     * Sets the value of the mmscProvisioned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMmscProvisioned(Boolean value) {
        this.mmscProvisioned = value;
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
     * Gets the value of the timeZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the sendOTAOnFirstRegistration property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSendOTAOnFirstRegistration() {
        return sendOTAOnFirstRegistration;
    }

    /**
     * Sets the value of the sendOTAOnFirstRegistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSendOTAOnFirstRegistration(Boolean value) {
        this.sendOTAOnFirstRegistration = value;
    }

    /**
     * Gets the value of the clusterChanges property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getClusterChanges() {
        return clusterChanges;
    }

    /**
     * Sets the value of the clusterChanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setClusterChanges(Long value) {
        this.clusterChanges = value;
    }

    /**
     * Gets the value of the clusterChangeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClusterChangeTime() {
        return clusterChangeTime;
    }

    /**
     * Sets the value of the clusterChangeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClusterChangeTime(XMLGregorianCalendar value) {
        this.clusterChangeTime = value;
    }

    /**
     * Gets the value of the checkedClusterCountryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCheckedClusterCountryId() {
        return checkedClusterCountryId;
    }

    /**
     * Sets the value of the checkedClusterCountryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCheckedClusterCountryId(Long value) {
        this.checkedClusterCountryId = value;
    }

    /**
     * Gets the value of the checkedOTATime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCheckedOTATime() {
        return checkedOTATime;
    }

    /**
     * Sets the value of the checkedOTATime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckedOTATime(XMLGregorianCalendar value) {
        this.checkedOTATime = value;
    }

    /**
     * Gets the value of the imeiTechType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImeiTechType() {
        return imeiTechType;
    }

    /**
     * Sets the value of the imeiTechType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImeiTechType(String value) {
        this.imeiTechType = value;
    }

    /**
     * Gets the value of the customerIMEITechType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerIMEITechType() {
        return customerIMEITechType;
    }

    /**
     * Sets the value of the customerIMEITechType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerIMEITechType(String value) {
        this.customerIMEITechType = value;
    }

    /**
     * Gets the value of the customerIMEI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerIMEI() {
        return customerIMEI;
    }

    /**
     * Sets the value of the customerIMEI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerIMEI(String value) {
        this.customerIMEI = value;
    }

    /**
     * Gets the value of the ppuID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPpuID() {
        return ppuID;
    }

    /**
     * Sets the value of the ppuID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPpuID(Long value) {
        this.ppuID = value;
    }

    /**
     * Gets the value of the orderID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrderID() {
        return orderID;
    }

    /**
     * Sets the value of the orderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrderID(Long value) {
        this.orderID = value;
    }

    /**
     * Gets the value of the opmNotificationSent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOpmNotificationSent() {
        return opmNotificationSent;
    }

    /**
     * Sets the value of the opmNotificationSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOpmNotificationSent(Boolean value) {
        this.opmNotificationSent = value;
    }

    /**
     * Gets the value of the settlementRatePlanID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSettlementRatePlanID() {
        return settlementRatePlanID;
    }

    /**
     * Sets the value of the settlementRatePlanID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSettlementRatePlanID(Long value) {
        this.settlementRatePlanID = value;
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

}
