
package com.gv.midway.attjasper;

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
 *         &lt;group ref="{http://api.jasperwireless.com/ws/schema}GetTerminalLatestRegistrationResponseParamGroup"/>
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
    "registrationInfo",
    "any"
})
@XmlRootElement(name = "GetTerminalLatestRegistrationResponse")
public class GetTerminalLatestRegistrationResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetTerminalLatestRegistrationResponse.RegistrationInfo registrationInfo;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the registrationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalLatestRegistrationResponse.RegistrationInfo }
     *     
     */
    public GetTerminalLatestRegistrationResponse.RegistrationInfo getRegistrationInfo() {
        return registrationInfo;
    }

    /**
     * Sets the value of the registrationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalLatestRegistrationResponse.RegistrationInfo }
     *     
     */
    public void setRegistrationInfo(GetTerminalLatestRegistrationResponse.RegistrationInfo value) {
        this.registrationInfo = value;
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
     *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="gtAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sgsn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="vlr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="msc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="eventDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="carrierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "imsi",
        "gtAddr",
        "sgsn",
        "vlr",
        "msc",
        "eventDate",
        "carrierName"
    })
    public static class RegistrationInfo {

        protected String imsi;
        protected String gtAddr;
        protected Boolean sgsn;
        protected Boolean vlr;
        protected Boolean msc;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar eventDate;
        protected String carrierName;

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
         * Gets the value of the gtAddr property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGtAddr() {
            return gtAddr;
        }

        /**
         * Sets the value of the gtAddr property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGtAddr(String value) {
            this.gtAddr = value;
        }

        /**
         * Gets the value of the sgsn property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isSgsn() {
            return sgsn;
        }

        /**
         * Sets the value of the sgsn property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setSgsn(Boolean value) {
            this.sgsn = value;
        }

        /**
         * Gets the value of the vlr property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isVlr() {
            return vlr;
        }

        /**
         * Sets the value of the vlr property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setVlr(Boolean value) {
            this.vlr = value;
        }

        /**
         * Gets the value of the msc property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isMsc() {
            return msc;
        }

        /**
         * Sets the value of the msc property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setMsc(Boolean value) {
            this.msc = value;
        }

        /**
         * Gets the value of the eventDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEventDate() {
            return eventDate;
        }

        /**
         * Sets the value of the eventDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEventDate(XMLGregorianCalendar value) {
            this.eventDate = value;
        }

        /**
         * Gets the value of the carrierName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCarrierName() {
            return carrierName;
        }

        /**
         * Sets the value of the carrierName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCarrierName(String value) {
            this.carrierName = value;
        }

    }

}
