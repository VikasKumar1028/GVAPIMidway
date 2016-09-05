
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
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="terminalRatings">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="terminalRating" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ratePlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="queuePosition" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="termLength" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="dataRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
 *         &lt;any maxOccurs="unbounded" minOccurs="0"/>
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
    "iccid",
    "terminalRatings",
    "any"
})
@XmlRootElement(name = "GetTerminalRatingResponse")
public class GetTerminalRatingResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected String iccid;
    @XmlElement(required = true)
    protected GetTerminalRatingResponse.TerminalRatings terminalRatings;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

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
     * Gets the value of the terminalRatings property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalRatingResponse.TerminalRatings }
     *     
     */
    public GetTerminalRatingResponse.TerminalRatings getTerminalRatings() {
        return terminalRatings;
    }

    /**
     * Sets the value of the terminalRatings property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalRatingResponse.TerminalRatings }
     *     
     */
    public void setTerminalRatings(GetTerminalRatingResponse.TerminalRatings value) {
        this.terminalRatings = value;
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
     *         &lt;element name="terminalRating" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ratePlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="queuePosition" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="termLength" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="dataRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
        "terminalRating"
    })
    public static class TerminalRatings {

        protected List<GetTerminalRatingResponse.TerminalRatings.TerminalRating> terminalRating;

        /**
         * Gets the value of the terminalRating property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the terminalRating property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTerminalRating().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GetTerminalRatingResponse.TerminalRatings.TerminalRating }
         * 
         * 
         */
        public List<GetTerminalRatingResponse.TerminalRatings.TerminalRating> getTerminalRating() {
            if (terminalRating == null) {
                terminalRating = new ArrayList<GetTerminalRatingResponse.TerminalRatings.TerminalRating>();
            }
            return this.terminalRating;
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
         *         &lt;element name="ratePlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="queuePosition" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="termLength" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="dataRemaining" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
            "ratePlanName",
            "queuePosition",
            "expirationDate",
            "termLength",
            "dataRemaining"
        })
        public static class TerminalRating {

            @XmlElement(required = true)
            protected String ratePlanName;
            protected Long queuePosition;
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar expirationDate;
            protected Long termLength;
            protected BigDecimal dataRemaining;

            /**
             * Gets the value of the ratePlanName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRatePlanName() {
                return ratePlanName;
            }

            /**
             * Sets the value of the ratePlanName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRatePlanName(String value) {
                this.ratePlanName = value;
            }

            /**
             * Gets the value of the queuePosition property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getQueuePosition() {
                return queuePosition;
            }

            /**
             * Sets the value of the queuePosition property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setQueuePosition(Long value) {
                this.queuePosition = value;
            }

            /**
             * Gets the value of the expirationDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getExpirationDate() {
                return expirationDate;
            }

            /**
             * Sets the value of the expirationDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setExpirationDate(XMLGregorianCalendar value) {
                this.expirationDate = value;
            }

            /**
             * Gets the value of the termLength property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getTermLength() {
                return termLength;
            }

            /**
             * Sets the value of the termLength property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setTermLength(Long value) {
                this.termLength = value;
            }

            /**
             * Gets the value of the dataRemaining property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDataRemaining() {
                return dataRemaining;
            }

            /**
             * Sets the value of the dataRemaining property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDataRemaining(BigDecimal value) {
                this.dataRemaining = value;
            }

        }

    }

}
