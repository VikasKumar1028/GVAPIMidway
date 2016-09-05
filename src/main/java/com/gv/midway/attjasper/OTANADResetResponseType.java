
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         The detail information about the OTA NAD Reset callback notification
 *     
 * 
 * <p>Java class for OTANADResetResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OTANADResetResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="otaCallbackNotificationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="otaMessageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sentTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateSent" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OTANADResetResponseType", propOrder = {
    "otaCallbackNotificationId",
    "otaMessageType",
    "status",
    "simId",
    "sentTo",
    "dateSent"
})
@XmlSeeAlso({
    OTANADResetCallbackResponse.class
})
public class OTANADResetResponseType {

    protected long otaCallbackNotificationId;
    @XmlElement(required = true)
    protected String otaMessageType;
    @XmlElement(required = true)
    protected String status;
    protected long simId;
    @XmlElement(required = true)
    protected String sentTo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateSent;

    /**
     * Gets the value of the otaCallbackNotificationId property.
     * 
     */
    public long getOtaCallbackNotificationId() {
        return otaCallbackNotificationId;
    }

    /**
     * Sets the value of the otaCallbackNotificationId property.
     * 
     */
    public void setOtaCallbackNotificationId(long value) {
        this.otaCallbackNotificationId = value;
    }

    /**
     * Gets the value of the otaMessageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtaMessageType() {
        return otaMessageType;
    }

    /**
     * Sets the value of the otaMessageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtaMessageType(String value) {
        this.otaMessageType = value;
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
     * Gets the value of the simId property.
     * 
     */
    public long getSimId() {
        return simId;
    }

    /**
     * Sets the value of the simId property.
     * 
     */
    public void setSimId(long value) {
        this.simId = value;
    }

    /**
     * Gets the value of the sentTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSentTo() {
        return sentTo;
    }

    /**
     * Sets the value of the sentTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSentTo(String value) {
        this.sentTo = value;
    }

    /**
     * Gets the value of the dateSent property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateSent() {
        return dateSent;
    }

    /**
     * Sets the value of the dateSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateSent(XMLGregorianCalendar value) {
        this.dateSent = value;
    }

}
