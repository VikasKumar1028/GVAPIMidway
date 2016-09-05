
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="subscriber" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="acctName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="operatorAccountId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="taxId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
    "subscriber"
})
@XmlRootElement(name = "GetSubscriberHistoryByMsisdnResponse")
public class GetSubscriberHistoryByMsisdnResponse
    extends ResponseType
{

    protected List<GetSubscriberHistoryByMsisdnResponse.Subscriber> subscriber;

    /**
     * Gets the value of the subscriber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetSubscriberHistoryByMsisdnResponse.Subscriber }
     * 
     * 
     */
    public List<GetSubscriberHistoryByMsisdnResponse.Subscriber> getSubscriber() {
        if (subscriber == null) {
            subscriber = new ArrayList<GetSubscriberHistoryByMsisdnResponse.Subscriber>();
        }
        return this.subscriber;
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
     *         &lt;element name="acctName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="operatorAccountId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="taxId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
        "acctName",
        "operatorAccountId",
        "taxId",
        "iccid",
        "imsi",
        "msisdn",
        "startDate",
        "endDate"
    })
    public static class Subscriber {

        @XmlElement(required = true)
        protected String acctName;
        @XmlElement(required = true)
        protected String operatorAccountId;
        @XmlElement(required = true)
        protected String taxId;
        @XmlElement(required = true)
        protected String iccid;
        @XmlElement(required = true)
        protected String imsi;
        @XmlElement(required = true)
        protected String msisdn;
        @XmlElement(required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar startDate;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar endDate;

        /**
         * Gets the value of the acctName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcctName() {
            return acctName;
        }

        /**
         * Sets the value of the acctName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcctName(String value) {
            this.acctName = value;
        }

        /**
         * Gets the value of the operatorAccountId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperatorAccountId() {
            return operatorAccountId;
        }

        /**
         * Sets the value of the operatorAccountId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperatorAccountId(String value) {
            this.operatorAccountId = value;
        }

        /**
         * Gets the value of the taxId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTaxId() {
            return taxId;
        }

        /**
         * Sets the value of the taxId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTaxId(String value) {
            this.taxId = value;
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
         * Gets the value of the msisdn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMsisdn() {
            return msisdn;
        }

        /**
         * Sets the value of the msisdn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMsisdn(String value) {
            this.msisdn = value;
        }

        /**
         * Gets the value of the startDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getStartDate() {
            return startDate;
        }

        /**
         * Sets the value of the startDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setStartDate(XMLGregorianCalendar value) {
            this.startDate = value;
        }

        /**
         * Gets the value of the endDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEndDate() {
            return endDate;
        }

        /**
         * Sets the value of the endDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEndDate(XMLGregorianCalendar value) {
            this.endDate = value;
        }

    }

}
