
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}SubscriberRequestType">
 *       &lt;sequence>
 *         &lt;element name="requestKey" type="{http://api.jasperwireless.com/ws/schema}RequestKeyType"/>
 *         &lt;element name="sentToMsisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="messageText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="messageTextEncoding" type="{http://api.jasperwireless.com/ws/schema}messageTextEncodingType"/>
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
    "requestKey",
    "sentToMsisdn",
    "messageText",
    "messageTextEncoding"
})
@XmlRootElement(name = "SubscriberSendSMSByMsisdnRequest")
public class SubscriberSendSMSByMsisdnRequest
    extends SubscriberRequestType
{

    @XmlElement(required = true)
    protected RequestKeyType requestKey;
    @XmlElement(required = true)
    protected String sentToMsisdn;
    @XmlElement(required = true)
    protected String messageText;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected MessageTextEncodingType messageTextEncoding;

    /**
     * Gets the value of the requestKey property.
     * 
     * @return
     *     possible object is
     *     {@link RequestKeyType }
     *     
     */
    public RequestKeyType getRequestKey() {
        return requestKey;
    }

    /**
     * Sets the value of the requestKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestKeyType }
     *     
     */
    public void setRequestKey(RequestKeyType value) {
        this.requestKey = value;
    }

    /**
     * Gets the value of the sentToMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSentToMsisdn() {
        return sentToMsisdn;
    }

    /**
     * Sets the value of the sentToMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSentToMsisdn(String value) {
        this.sentToMsisdn = value;
    }

    /**
     * Gets the value of the messageText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Sets the value of the messageText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageText(String value) {
        this.messageText = value;
    }

    /**
     * Gets the value of the messageTextEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link MessageTextEncodingType }
     *     
     */
    public MessageTextEncodingType getMessageTextEncoding() {
        return messageTextEncoding;
    }

    /**
     * Sets the value of the messageTextEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageTextEncodingType }
     *     
     */
    public void setMessageTextEncoding(MessageTextEncodingType value) {
        this.messageTextEncoding = value;
    }

}
