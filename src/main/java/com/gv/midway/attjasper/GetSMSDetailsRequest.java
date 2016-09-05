
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="smsMsgIds">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="smsMsgId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "smsMsgIds"
})
@XmlRootElement(name = "GetSMSDetailsRequest")
public class GetSMSDetailsRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected GetSMSDetailsRequest.SmsMsgIds smsMsgIds;
    @XmlAttribute(name = "messageTextEncoding")
    protected String messageTextEncoding;

    /**
     * Gets the value of the smsMsgIds property.
     * 
     * @return
     *     possible object is
     *     {@link GetSMSDetailsRequest.SmsMsgIds }
     *     
     */
    public GetSMSDetailsRequest.SmsMsgIds getSmsMsgIds() {
        return smsMsgIds;
    }

    /**
     * Sets the value of the smsMsgIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSMSDetailsRequest.SmsMsgIds }
     *     
     */
    public void setSmsMsgIds(GetSMSDetailsRequest.SmsMsgIds value) {
        this.smsMsgIds = value;
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
     *         &lt;element name="smsMsgId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
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
        "smsMsgId"
    })
    public static class SmsMsgIds {

        @XmlElement(type = Long.class)
        protected List<Long> smsMsgId;

        /**
         * Gets the value of the smsMsgId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the smsMsgId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSmsMsgId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Long }
         * 
         * 
         */
        public List<Long> getSmsMsgId() {
            if (smsMsgId == null) {
                smsMsgId = new ArrayList<Long>();
            }
            return this.smsMsgId;
        }

    }

}
