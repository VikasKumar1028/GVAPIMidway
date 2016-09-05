
package com.gv.midway.attjasper;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *             This is base type for all subscriber request payload types. The idea is to capture attributes common to all subscriber requests
 *             (Such as priority, etc.).
 * 
 *             Subscriber Request type is derived from the Request Type.
 *             All concrete request types are derived from this abstract subscriber request type. This is the only place where polymorphism is encouraged.
 *         
 * 
 * <p>Java class for SubscriberRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "SubscriberRequestType", propOrder = {
    "priority",
    "effectiveDate"
})
@XmlSeeAlso({
    CreateSubscriberRequest.class,
    ChangeServicePolicyRequest.class,
    SendPortInCompleteForMSISDNRequest.class,
    ChangeSubscriberStatusRequest.class,
    ChangeSubscriberIdentifierRequest.class,
    GetSubscriberDetailsRequest.class,
    SubscriberSendSMSByMsisdnRequest.class,
    ChangeSubscriberServiceRequest.class,
    ChangeVoicemailPasswordRequest.class
})
public abstract class SubscriberRequestType
    extends RequestType
{

    @XmlElement(required = true)
    protected BigInteger priority;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar effectiveDate;

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPriority(BigInteger value) {
        this.priority = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

}
