
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="msisdnList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "msisdnList",
    "messageText",
    "tpvp"
})
@XmlRootElement(name = "SendBulkSMSToMsisdnRequest")
public class SendBulkSMSToMsisdnRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected SendBulkSMSToMsisdnRequest.MsisdnList msisdnList;
    @XmlElement(required = true)
    protected String messageText;
    @XmlSchemaType(name = "unsignedByte")
    protected Short tpvp;
    @XmlAttribute(name = "messageTextEncoding")
    protected String messageTextEncoding;

    /**
     * Gets the value of the msisdnList property.
     * 
     * @return
     *     possible object is
     *     {@link SendBulkSMSToMsisdnRequest.MsisdnList }
     *     
     */
    public SendBulkSMSToMsisdnRequest.MsisdnList getMsisdnList() {
        return msisdnList;
    }

    /**
     * Sets the value of the msisdnList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SendBulkSMSToMsisdnRequest.MsisdnList }
     *     
     */
    public void setMsisdnList(SendBulkSMSToMsisdnRequest.MsisdnList value) {
        this.msisdnList = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "msisdn"
    })
    public static class MsisdnList {

        @XmlElement(required = true)
        protected List<String> msisdn;

        /**
         * Gets the value of the msisdn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the msisdn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMsisdn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getMsisdn() {
            if (msisdn == null) {
                msisdn = new ArrayList<String>();
            }
            return this.msisdn;
        }

    }

}
