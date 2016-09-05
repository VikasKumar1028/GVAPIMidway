
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
 *         &lt;element name="primaryIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetPartnerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="globalAllianceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="globalSimTransferType" type="{http://api.jasperwireless.com/ws/schema}GlobalSimTransferType"/>
 *         &lt;element name="callbackNotificationURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
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
    "targetPartnerName",
    "globalAllianceName",
    "globalSimTransferType",
    "callbackNotificationURL"
})
@XmlRootElement(name = "TransferGlobalSimRequest")
public class TransferGlobalSimRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String primaryIccid;
    @XmlElement(required = true)
    protected String targetPartnerName;
    @XmlElement(required = true)
    protected String globalAllianceName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GlobalSimTransferType globalSimTransferType;
    @XmlSchemaType(name = "anyURI")
    protected String callbackNotificationURL;

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
     * Gets the value of the targetPartnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetPartnerName() {
        return targetPartnerName;
    }

    /**
     * Sets the value of the targetPartnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetPartnerName(String value) {
        this.targetPartnerName = value;
    }

    /**
     * Gets the value of the globalAllianceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalAllianceName() {
        return globalAllianceName;
    }

    /**
     * Sets the value of the globalAllianceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalAllianceName(String value) {
        this.globalAllianceName = value;
    }

    /**
     * Gets the value of the globalSimTransferType property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimTransferType }
     *     
     */
    public GlobalSimTransferType getGlobalSimTransferType() {
        return globalSimTransferType;
    }

    /**
     * Sets the value of the globalSimTransferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimTransferType }
     *     
     */
    public void setGlobalSimTransferType(GlobalSimTransferType value) {
        this.globalSimTransferType = value;
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

}
