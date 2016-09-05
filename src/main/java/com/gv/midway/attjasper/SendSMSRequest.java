
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
 *         &lt;group ref="{http://api.jasperwireless.com/ws/schema}SendSMSRequestParamGroup"/>
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
    "sentToIccid",
    "messageText",
    "tpvp"
})
@XmlRootElement(name = "SendSMSRequest")
public class SendSMSRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String sentToIccid;
    @XmlElement(required = true)
    protected String messageText;
    @XmlSchemaType(name = "unsignedByte")
    protected Short tpvp;
    @XmlAttribute(name = "messageTextEncoding")
    protected String messageTextEncoding;

    /**
     * Gets the value of the sentToIccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSentToIccid() {
        return sentToIccid;
    }

    /**
     * Sets the value of the sentToIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSentToIccid(String value) {
        this.sentToIccid = value;
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
