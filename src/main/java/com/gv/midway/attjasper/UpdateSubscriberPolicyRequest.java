
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="inlineProcess" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="notificationURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberPolicies" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="subscriberPolicy" type="{http://api.jasperwireless.com/ws/schema}SubscriberPolicyType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="additionalPolicies" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="additionalPolicy" type="{http://api.jasperwireless.com/ws/schema}AdditionalPolicyType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "imsi",
    "iccid",
    "effectiveDate",
    "inlineProcess",
    "notificationURL",
    "subscriberPolicies",
    "additionalPolicies"
})
@XmlRootElement(name = "UpdateSubscriberPolicyRequest")
public class UpdateSubscriberPolicyRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String imsi;
    protected String iccid;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar effectiveDate;
    protected Boolean inlineProcess;
    protected String notificationURL;
    protected UpdateSubscriberPolicyRequest.SubscriberPolicies subscriberPolicies;
    protected UpdateSubscriberPolicyRequest.AdditionalPolicies additionalPolicies;

    /**
     * Gets the value of the imsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * Sets the value of the imsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsi(String value) {
        this.imsi = value;
    }

    /**
     * Gets the value of the iccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * Sets the value of the iccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIccid(String value) {
        this.iccid = value;
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

    /**
     * Gets the value of the inlineProcess property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInlineProcess() {
        return inlineProcess;
    }

    /**
     * Sets the value of the inlineProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInlineProcess(Boolean value) {
        this.inlineProcess = value;
    }

    /**
     * Gets the value of the notificationURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationURL() {
        return notificationURL;
    }

    /**
     * Sets the value of the notificationURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationURL(String value) {
        this.notificationURL = value;
    }

    /**
     * Gets the value of the subscriberPolicies property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateSubscriberPolicyRequest.SubscriberPolicies }
     *     
     */
    public UpdateSubscriberPolicyRequest.SubscriberPolicies getSubscriberPolicies() {
        return subscriberPolicies;
    }

    /**
     * Sets the value of the subscriberPolicies property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateSubscriberPolicyRequest.SubscriberPolicies }
     *     
     */
    public void setSubscriberPolicies(UpdateSubscriberPolicyRequest.SubscriberPolicies value) {
        this.subscriberPolicies = value;
    }

    /**
     * Gets the value of the additionalPolicies property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateSubscriberPolicyRequest.AdditionalPolicies }
     *     
     */
    public UpdateSubscriberPolicyRequest.AdditionalPolicies getAdditionalPolicies() {
        return additionalPolicies;
    }

    /**
     * Sets the value of the additionalPolicies property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateSubscriberPolicyRequest.AdditionalPolicies }
     *     
     */
    public void setAdditionalPolicies(UpdateSubscriberPolicyRequest.AdditionalPolicies value) {
        this.additionalPolicies = value;
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
     *         &lt;element name="additionalPolicy" type="{http://api.jasperwireless.com/ws/schema}AdditionalPolicyType" maxOccurs="unbounded" minOccurs="0"/>
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
        "additionalPolicy"
    })
    public static class AdditionalPolicies {

        protected List<AdditionalPolicyType> additionalPolicy;

        /**
         * Gets the value of the additionalPolicy property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the additionalPolicy property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAdditionalPolicy().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AdditionalPolicyType }
         * 
         * 
         */
        public List<AdditionalPolicyType> getAdditionalPolicy() {
            if (additionalPolicy == null) {
                additionalPolicy = new ArrayList<AdditionalPolicyType>();
            }
            return this.additionalPolicy;
        }

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
     *         &lt;element name="subscriberPolicy" type="{http://api.jasperwireless.com/ws/schema}SubscriberPolicyType" maxOccurs="unbounded"/>
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
        "subscriberPolicy"
    })
    public static class SubscriberPolicies {

        @XmlElement(required = true)
        protected List<SubscriberPolicyType> subscriberPolicy;

        /**
         * Gets the value of the subscriberPolicy property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the subscriberPolicy property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSubscriberPolicy().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SubscriberPolicyType }
         * 
         * 
         */
        public List<SubscriberPolicyType> getSubscriberPolicy() {
            if (subscriberPolicy == null) {
                subscriberPolicy = new ArrayList<SubscriberPolicyType>();
            }
            return this.subscriberPolicy;
        }

    }

}
