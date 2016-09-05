
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         The detail information about Global SIM transfer
 *     
 * 
 * <p>Java class for GlobalSimTransferResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlobalSimTransferResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="primaryIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://api.jasperwireless.com/ws/schema}GlobalSimTransferStatusType"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSimSubscriptionInfo" type="{http://api.jasperwireless.com/ws/schema}GlobalSimSubscriptionInfoType"/>
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
@XmlType(name = "GlobalSimTransferResponseType", propOrder = {
    "primaryIccid",
    "status",
    "errorCode",
    "errorDetails",
    "targetSimSubscriptionInfo"
})
@XmlSeeAlso({
    GlobalSimTransferCallbackResponse.class
})
public class GlobalSimTransferResponseType
    extends ResponseType
{

    @XmlElement(required = true)
    protected String primaryIccid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GlobalSimTransferStatusType status;
    protected String errorCode;
    protected String errorDetails;
    @XmlElement(required = true)
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
