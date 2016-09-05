
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="primaryICCID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryIMSI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryMSISDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryOperatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryAcctName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primarySimProfileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primarySimState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryGlobalSimType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetOperatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSimProfileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transferType" type="{http://api.jasperwireless.com/ws/schema}GlobalSimTransferType"/>
 *         &lt;element name="callbackNotificationURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="isSpecialTargetMSISDNAssignment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="otaAlgorithmType" type="{http://api.jasperwireless.com/ws/schema}GlobalSimOtaAlgoType" minOccurs="0"/>
 *         &lt;element name="euiccID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "primaryICCID",
    "primaryIMSI",
    "primaryMSISDN",
    "primaryOperatorName",
    "primaryAcctName",
    "primarySimProfileId",
    "primarySimState",
    "primaryGlobalSimType",
    "targetOperatorName",
    "targetAccountName",
    "targetSimProfileId",
    "transferType",
    "callbackNotificationURL",
    "isSpecialTargetMSISDNAssignment",
    "otaAlgorithmType",
    "euiccID"
})
@XmlRootElement(name = "GlobalTransferSimRequest")
public class GlobalTransferSimRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String primaryICCID;
    protected String primaryIMSI;
    protected String primaryMSISDN;
    protected String primaryOperatorName;
    protected String primaryAcctName;
    protected String primarySimProfileId;
    protected String primarySimState;
    protected String primaryGlobalSimType;
    protected String targetOperatorName;
    protected String targetAccountName;
    protected String targetSimProfileId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GlobalSimTransferType transferType;
    @XmlSchemaType(name = "anyURI")
    protected String callbackNotificationURL;
    protected Boolean isSpecialTargetMSISDNAssignment;
    @XmlSchemaType(name = "string")
    protected GlobalSimOtaAlgoType otaAlgorithmType;
    protected String euiccID;

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
     * Gets the value of the primaryIMSI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryIMSI() {
        return primaryIMSI;
    }

    /**
     * Sets the value of the primaryIMSI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryIMSI(String value) {
        this.primaryIMSI = value;
    }

    /**
     * Gets the value of the primaryMSISDN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryMSISDN() {
        return primaryMSISDN;
    }

    /**
     * Sets the value of the primaryMSISDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryMSISDN(String value) {
        this.primaryMSISDN = value;
    }

    /**
     * Gets the value of the primaryOperatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryOperatorName() {
        return primaryOperatorName;
    }

    /**
     * Sets the value of the primaryOperatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryOperatorName(String value) {
        this.primaryOperatorName = value;
    }

    /**
     * Gets the value of the primaryAcctName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryAcctName() {
        return primaryAcctName;
    }

    /**
     * Sets the value of the primaryAcctName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryAcctName(String value) {
        this.primaryAcctName = value;
    }

    /**
     * Gets the value of the primarySimProfileId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimarySimProfileId() {
        return primarySimProfileId;
    }

    /**
     * Sets the value of the primarySimProfileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimarySimProfileId(String value) {
        this.primarySimProfileId = value;
    }

    /**
     * Gets the value of the primarySimState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimarySimState() {
        return primarySimState;
    }

    /**
     * Sets the value of the primarySimState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimarySimState(String value) {
        this.primarySimState = value;
    }

    /**
     * Gets the value of the primaryGlobalSimType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryGlobalSimType() {
        return primaryGlobalSimType;
    }

    /**
     * Sets the value of the primaryGlobalSimType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryGlobalSimType(String value) {
        this.primaryGlobalSimType = value;
    }

    /**
     * Gets the value of the targetOperatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetOperatorName() {
        return targetOperatorName;
    }

    /**
     * Sets the value of the targetOperatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetOperatorName(String value) {
        this.targetOperatorName = value;
    }

    /**
     * Gets the value of the targetAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetAccountName() {
        return targetAccountName;
    }

    /**
     * Sets the value of the targetAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetAccountName(String value) {
        this.targetAccountName = value;
    }

    /**
     * Gets the value of the targetSimProfileId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSimProfileId() {
        return targetSimProfileId;
    }

    /**
     * Sets the value of the targetSimProfileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSimProfileId(String value) {
        this.targetSimProfileId = value;
    }

    /**
     * Gets the value of the transferType property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimTransferType }
     *     
     */
    public GlobalSimTransferType getTransferType() {
        return transferType;
    }

    /**
     * Sets the value of the transferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimTransferType }
     *     
     */
    public void setTransferType(GlobalSimTransferType value) {
        this.transferType = value;
    }

    /**
     * Gets the value of the callbackNotificationURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallbackNotificationURL() {
        return callbackNotificationURL;
    }

    /**
     * Sets the value of the callbackNotificationURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallbackNotificationURL(String value) {
        this.callbackNotificationURL = value;
    }

    /**
     * Gets the value of the isSpecialTargetMSISDNAssignment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSpecialTargetMSISDNAssignment() {
        return isSpecialTargetMSISDNAssignment;
    }

    /**
     * Sets the value of the isSpecialTargetMSISDNAssignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSpecialTargetMSISDNAssignment(Boolean value) {
        this.isSpecialTargetMSISDNAssignment = value;
    }

    /**
     * Gets the value of the otaAlgorithmType property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimOtaAlgoType }
     *     
     */
    public GlobalSimOtaAlgoType getOtaAlgorithmType() {
        return otaAlgorithmType;
    }

    /**
     * Sets the value of the otaAlgorithmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimOtaAlgoType }
     *     
     */
    public void setOtaAlgorithmType(GlobalSimOtaAlgoType value) {
        this.otaAlgorithmType = value;
    }

    /**
     * Gets the value of the euiccID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEuiccID() {
        return euiccID;
    }

    /**
     * Sets the value of the euiccID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEuiccID(String value) {
        this.euiccID = value;
    }

}
