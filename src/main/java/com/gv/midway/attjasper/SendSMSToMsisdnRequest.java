
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="sentToMsisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="messageText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tpvp" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="messageTextEncoding" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "sentToMsisdn",
    "messageText",
    "tpvp"
})
@XmlRootElement(name = "SendSMSToMsisdnRequest")
public class SendSMSToMsisdnRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String sentToMsisdn;
    @XmlElement(required = true)
    protected String messageText;
    @XmlSchemaType(name = "unsignedByte")
    protected Short tpvp;
    @XmlAttribute(name = "messageTextEncoding")
    protected String messageTextEncoding;

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
     * Gets the value of the tpvp property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTpvp() {
        return tpvp;
    }

    /**
     * Sets the value of the tpvp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTpvp(Short value) {
        this.tpvp = value;
    }

    /**
     * Gets the value of the messageTextEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageTextEncoding() {
        return messageTextEncoding;
    }

    /**
     * Sets the value of the messageTextEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageTextEncoding(String value) {
        this.messageTextEncoding = value;
    }

}
