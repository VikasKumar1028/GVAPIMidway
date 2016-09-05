
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Subscriber Response Status type will hold the result of the request. For all responses, it will have SUCCESS or FAILURE values.
 *     
 * 
 * <p>Java class for SubscriberResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="responseStatus" type="{http://api.jasperwireless.com/ws/schema}SubscriberResponseStatusType"/>
 *         &lt;element name="responseMessage" type="{http://api.jasperwireless.com/ws/schema}SubscriberResponseMessageType" minOccurs="0"/>
 *         &lt;element name="enhancedResponseMessages" type="{http://api.jasperwireless.com/ws/schema}SubscriberResponseEnhancedMessagesType" minOccurs="0"/>
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
@XmlType(name = "SubscriberResponseType", propOrder = {
    "responseStatus",
    "responseMessage",
    "enhancedResponseMessages"
})
@XmlSeeAlso({
    ChangeSubscriberIdentifierResponse.class,
    ChangeVoicemailPasswordResponse.class,
    CreateSubscriberResponse.class,
    SendPortInCompleteForMSISDNResponse.class,
    ChangeSubscriberServiceResponse.class,
    GetSubscriberDetailsResponse.class,
    SubscriberSendSMSByMsisdnResponse.class,
    ChangeServicePolicyResponse.class,
    ChangeSubscriberStatusResponse.class
})
public abstract class SubscriberResponseType
    extends ResponseType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected SubscriberResponseStatusType responseStatus;
    protected SubscriberResponseMessageType responseMessage;
    protected SubscriberResponseEnhancedMessagesType enhancedResponseMessages;

    /**
     * Gets the value of the responseStatus property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberResponseStatusType }
     *     
     */
    public SubscriberResponseStatusType getResponseStatus() {
        return responseStatus;
    }

    /**
     * Sets the value of the responseStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberResponseStatusType }
     *     
     */
    public void setResponseStatus(SubscriberResponseStatusType value) {
        this.responseStatus = value;
    }

    /**
     * Gets the value of the responseMessage property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberResponseMessageType }
     *     
     */
    public SubscriberResponseMessageType getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the value of the responseMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberResponseMessageType }
     *     
     */
    public void setResponseMessage(SubscriberResponseMessageType value) {
        this.responseMessage = value;
    }

    /**
     * Gets the value of the enhancedResponseMessages property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberResponseEnhancedMessagesType }
     *     
     */
    public SubscriberResponseEnhancedMessagesType getEnhancedResponseMessages() {
        return enhancedResponseMessages;
    }

    /**
     * Sets the value of the enhancedResponseMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberResponseEnhancedMessagesType }
     *     
     */
    public void setEnhancedResponseMessages(SubscriberResponseEnhancedMessagesType value) {
        this.enhancedResponseMessages = value;
    }

}
