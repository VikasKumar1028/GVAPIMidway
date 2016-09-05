
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


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
 *         &lt;group ref="{http://api.jasperwireless.com/ws/schema}AvailableRatePlansResponseParamGroup"/>
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
    "availableEvents",
    "any"
})
@XmlRootElement(name = "GetAvailableEventsResponse")
public class GetAvailableEventsResponse
    extends ResponseType
{

    @XmlElement(name = "AvailableEvents", required = true)
    protected GetAvailableEventsResponse.AvailableEvents availableEvents;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the availableEvents property.
     * 
     * @return
     *     possible object is
     *     {@link GetAvailableEventsResponse.AvailableEvents }
     *     
     */
    public GetAvailableEventsResponse.AvailableEvents getAvailableEvents() {
        return availableEvents;
    }

    /**
     * Sets the value of the availableEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAvailableEventsResponse.AvailableEvents }
     *     
     */
    public void setAvailableEvents(GetAvailableEventsResponse.AvailableEvents value) {
        this.availableEvents = value;
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
     * {@link Element }
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
     *         &lt;element name="AvailableEvent" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="EventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *                   &lt;element name="Term" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                   &lt;element name="IncludedData" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *                   &lt;element name="DataOverageRate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
        "availableEvent"
    })
    public static class AvailableEvents {

        @XmlElement(name = "AvailableEvent")
        protected List<GetAvailableEventsResponse.AvailableEvents.AvailableEvent> availableEvent;

        /**
         * Gets the value of the availableEvent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the availableEvent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAvailableEvent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GetAvailableEventsResponse.AvailableEvents.AvailableEvent }
         * 
         * 
         */
        public List<GetAvailableEventsResponse.AvailableEvents.AvailableEvent> getAvailableEvent() {
            if (availableEvent == null) {
                availableEvent = new ArrayList<GetAvailableEventsResponse.AvailableEvents.AvailableEvent>();
            }
            return this.availableEvent;
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
         *         &lt;element name="EventName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}long"/>
         *         &lt;element name="Term" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *         &lt;element name="IncludedData" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
         *         &lt;element name="DataOverageRate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
            "eventName",
            "version",
            "term",
            "price",
            "includedData",
            "dataOverageRate"
        })
        public static class AvailableEvent {

            @XmlElement(name = "EventName", required = true)
            protected String eventName;
            @XmlElement(name = "Version")
            protected long version;
            @XmlElement(name = "Term")
            protected Long term;
            @XmlElement(name = "Price", required = true)
            protected BigDecimal price;
            @XmlElement(name = "IncludedData", required = true)
            protected BigDecimal includedData;
            @XmlElement(name = "DataOverageRate", required = true)
            protected BigDecimal dataOverageRate;

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
             * Gets the value of the version property.
             * 
             */
            public long getVersion() {
                return version;
            }

            /**
             * Sets the value of the version property.
             * 
             */
            public void setVersion(long value) {
                this.version = value;
            }

            /**
             * Gets the value of the term property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getTerm() {
                return term;
            }

            /**
             * Sets the value of the term property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setTerm(Long value) {
                this.term = value;
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
             * Gets the value of the includedData property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIncludedData() {
                return includedData;
            }

            /**
             * Sets the value of the includedData property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIncludedData(BigDecimal value) {
                this.includedData = value;
            }

            /**
             * Gets the value of the dataOverageRate property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDataOverageRate() {
                return dataOverageRate;
            }

            /**
             * Sets the value of the dataOverageRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDataOverageRate(BigDecimal value) {
                this.dataOverageRate = value;
            }

        }

    }

}
