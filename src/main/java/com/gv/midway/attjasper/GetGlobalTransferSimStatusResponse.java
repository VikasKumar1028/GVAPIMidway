
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="primaryIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transferType" type="{http://api.jasperwireless.com/ws/schema}GlobalSimTransferType"/>
 *         &lt;element name="status" type="{http://api.jasperwireless.com/ws/schema}GlobalSimTransferStatusType"/>
 *         &lt;element name="statusDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceOperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetOperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceSimSubscriptionInfo" type="{http://api.jasperwireless.com/ws/schema}GlobalSimSourceSubscriptionInfoType"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSimSubscriptionInfo" type="{http://api.jasperwireless.com/ws/schema}GlobalSimSubscriptionInfoType" minOccurs="0"/>
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
    "primaryIccid",
    "transferType",
    "status",
    "statusDetails",
    "sourceOperatorName",
    "sourceAccountName",
    "targetOperatorName",
    "targetAccountName",
    "sourceSimSubscriptionInfo",
    "errorCode",
    "errorDetails",
    "targetSimSubscriptionInfo"
})
@XmlRootElement(name = "GetGlobalTransferSimStatusResponse")
public class GetGlobalTransferSimStatusResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected String primaryIccid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GlobalSimTransferType transferType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GlobalSimTransferStatusType status;
    @XmlElement(required = true)
    protected String statusDetails;
    @XmlElement(required = true)
    protected String sourceOperatorName;
    protected String sourceAccountName;
    @XmlElement(required = true)
    protected String targetOperatorName;
    protected String targetAccountName;
    @XmlElement(required = true)
    protected GlobalSimSourceSubscriptionInfoType sourceSimSubscriptionInfo;
    protected String errorCode;
    protected String errorDetails;
    protected GlobalSimSubscriptionInfoType targetSimSubscriptionInfo;

    /**
     * Gets the value of the primaryIccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryIccid() {
        return primaryIccid;
    }

    /**
     * Sets the value of the primaryIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryIccid(String value) {
        this.primaryIccid = value;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimTransferStatusType }
     *     
     */
    public GlobalSimTransferStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimTransferStatusType }
     *     
     */
    public void setStatus(GlobalSimTransferStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDetails() {
        return statusDetails;
    }

    /**
     * Sets the value of the statusDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDetails(String value) {
        this.statusDetails = value;
    }

    /**
     * Gets the value of the sourceOperatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceOperatorName() {
        return sourceOperatorName;
    }

    /**
     * Sets the value of the sourceOperatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceOperatorName(String value) {
        this.sourceOperatorName = value;
    }

    /**
     * Gets the value of the sourceAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceAccountName() {
        return sourceAccountName;
    }

    /**
     * Sets the value of the sourceAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceAccountName(String value) {
        this.sourceAccountName = value;
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
     * Gets the value of the sourceSimSubscriptionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimSourceSubscriptionInfoType }
     *     
     */
    public GlobalSimSourceSubscriptionInfoType getSourceSimSubscriptionInfo() {
        return sourceSimSubscriptionInfo;
    }

    /**
     * Sets the value of the sourceSimSubscriptionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimSourceSubscriptionInfoType }
     *     
     */
    public void setSourceSimSubscriptionInfo(GlobalSimSourceSubscriptionInfoType value) {
        this.sourceSimSubscriptionInfo = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * Sets the value of the errorDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDetails(String value) {
        this.errorDetails = value;
    }

    /**
     * Gets the value of the targetSimSubscriptionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimSubscriptionInfoType }
     *     
     */
    public GlobalSimSubscriptionInfoType getTargetSimSubscriptionInfo() {
        return targetSimSubscriptionInfo;
    }

    /**
     * Sets the value of the targetSimSubscriptionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimSubscriptionInfoType }
     *     
     */
    public void setTargetSimSubscriptionInfo(GlobalSimSubscriptionInfoType value) {
        this.targetSimSubscriptionInfo = value;
    }

}
