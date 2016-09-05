
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="trackedTerminals">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="trackedTerminal" maxOccurs="1000" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "trackedTerminals"
})
@XmlRootElement(name = "GetTrackedTerminalsResponse")
public class GetTrackedTerminalsResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetTrackedTerminalsResponse.TrackedTerminals trackedTerminals;

    /**
     * Gets the value of the trackedTerminals property.
     * 
     * @return
     *     possible object is
     *     {@link GetTrackedTerminalsResponse.TrackedTerminals }
     *     
     */
    public GetTrackedTerminalsResponse.TrackedTerminals getTrackedTerminals() {
        return trackedTerminals;
    }

    /**
     * Sets the value of the trackedTerminals property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTrackedTerminalsResponse.TrackedTerminals }
     *     
     */
    public void setTrackedTerminals(GetTrackedTerminalsResponse.TrackedTerminals value) {
        this.trackedTerminals = value;
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
     *         &lt;element name="trackedTerminal" maxOccurs="1000" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "trackedTerminal"
    })
    public static class TrackedTerminals {

        protected List<GetTrackedTerminalsResponse.TrackedTerminals.TrackedTerminal> trackedTerminal;

        /**
         * Gets the value of the trackedTerminal property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the trackedTerminal property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTrackedTerminal().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GetTrackedTerminalsResponse.TrackedTerminals.TrackedTerminal }
         * 
         * 
         */
        public List<GetTrackedTerminalsResponse.TrackedTerminals.TrackedTerminal> getTrackedTerminal() {
            if (trackedTerminal == null) {
                trackedTerminal = new ArrayList<GetTrackedTerminalsResponse.TrackedTerminals.TrackedTerminal>();
            }
            return this.trackedTerminal;
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
         *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "msisdn",
            "imsi"
        })
        public static class TrackedTerminal {

            protected String msisdn;
            protected String imsi;

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

        }

    }

}
