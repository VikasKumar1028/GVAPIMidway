
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
 *         &lt;group ref="{http://api.jasperwireless.com/ws/schema}TerminalEventRatePlansResponseParamGroup"/>
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
    "terminalEvents",
    "any"
})
@XmlRootElement(name = "GetTerminalEventsResponse")
public class GetTerminalEventsResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetTerminalEventsResponse.TerminalEvents terminalEvents;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the terminalEvents property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalEventsResponse.TerminalEvents }
     *     
     */
    public GetTerminalEventsResponse.TerminalEvents getTerminalEvents() {
        return terminalEvents;
    }

    /**
     * Sets the value of the terminalEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalEventsResponse.TerminalEvents }
     *     
     */
    public void setTerminalEvents(GetTerminalEventsResponse.TerminalEvents value) {
        this.terminalEvents = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
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
     *         &lt;element name="terminalEvent" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="eventInstanceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="activeStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                   &lt;element name="totalPrimaryIncludedData" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="primaryDataRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="totalPrimaryIncludedSMS" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="primarySMSRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "terminalEvent"
    })
    public static class TerminalEvents {

        protected List<GetTerminalEventsResponse.TerminalEvents.TerminalEvent> terminalEvent;

        /**
         * Gets the value of the terminalEvent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the terminalEvent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTerminalEvent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GetTerminalEventsResponse.TerminalEvents.TerminalEvent }
         * 
         * 
         */
        public List<GetTerminalEventsResponse.TerminalEvents.TerminalEvent> getTerminalEvent() {
            if (terminalEvent == null) {
                terminalEvent = new ArrayList<GetTerminalEventsResponse.TerminalEvents.TerminalEvent>();
            }
            return this.terminalEvent;
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
         *         &lt;element name="eventInstanceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="activeStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *         &lt;element name="totalPrimaryIncludedData" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="primaryDataRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="totalPrimaryIncludedSMS" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="primarySMSRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
            "eventInstanceId",
            "iccid",
            "eventName",
            "createdDate",
            "activeStatus",
            "startDate",
            "endDate",
            "price",
            "totalPrimaryIncludedData",
            "primaryDataRemaining",
            "totalPrimaryIncludedSMS",
            "primarySMSRemaining"
        })
        public static class TerminalEvent {

            protected long eventInstanceId;
            @XmlElement(required = true)
            protected String iccid;
            @XmlElement(required = true)
            protected String eventName;
            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar createdDate;
            @XmlElement(required = true)
            protected String activeStatus;
            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar startDate;
            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar endDate;
            @XmlElement(required = true)
            protected BigDecimal price;
            protected BigDecimal totalPrimaryIncludedData;
            protected BigDecimal primaryDataRemaining;
            protected BigDecimal totalPrimaryIncludedSMS;
            protected BigDecimal primarySMSRemaining;

            /**
             * Gets the value of the eventInstanceId property.
             * 
             */
            public long getEventInstanceId() {
                return eventInstanceId;
            }

            /**
             * Sets the value of the eventInstanceId property.
             * 
             */
            public void setEventInstanceId(long value) {
                this.eventInstanceId = value;
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
             * Gets the value of the eventName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEventName() {
                return eventName;
            }

            /**
             * Sets the value of the eventName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEventName(String value) {
                this.eventName = value;
            }

            /**
             * Gets the value of the createdDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getCreatedDate() {
                return createdDate;
            }

            /**
             * Sets the value of the createdDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setCreatedDate(XMLGregorianCalendar value) {
                this.createdDate = value;
            }

            /**
             * Gets the value of the activeStatus property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getActiveStatus() {
                return activeStatus;
            }

            /**
             * Sets the value of the activeStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setActiveStatus(String value) {
                this.activeStatus = value;
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

            /**
             * Gets the value of the price property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPrice() {
                return price;
            }

            /**
             * Sets the value of the price property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPrice(BigDecimal value) {
                this.price = value;
            }

            /**
             * Gets the value of the totalPrimaryIncludedData property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalPrimaryIncludedData() {
                return totalPrimaryIncludedData;
            }

            /**
             * Sets the value of the totalPrimaryIncludedData property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalPrimaryIncludedData(BigDecimal value) {
                this.totalPrimaryIncludedData = value;
            }

            /**
             * Gets the value of the primaryDataRemaining property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPrimaryDataRemaining() {
                return primaryDataRemaining;
            }

            /**
             * Sets the value of the primaryDataRemaining property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPrimaryDataRemaining(BigDecimal value) {
                this.primaryDataRemaining = value;
            }

            /**
             * Gets the value of the totalPrimaryIncludedSMS property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotalPrimaryIncludedSMS() {
                return totalPrimaryIncludedSMS;
            }

            /**
             * Sets the value of the totalPrimaryIncludedSMS property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotalPrimaryIncludedSMS(BigDecimal value) {
                this.totalPrimaryIncludedSMS = value;
            }

            /**
             * Gets the value of the primarySMSRemaining property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPrimarySMSRemaining() {
                return primarySMSRemaining;
            }

            /**
             * Sets the value of the primarySMSRemaining property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPrimarySMSRemaining(BigDecimal value) {
                this.primarySMSRemaining = value;
            }

        }

    }

}
