
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         The detail information about the location info of the tracked terminal
 *     
 * 
 * <p>Java class for LocationTrackingInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationTrackingInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationEvent">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Location Update"/>
 *               &lt;enumeration value="Cancel Location"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gtAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MSC">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Yes"/>
 *               &lt;enumeration value="No"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SGSN">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Yes"/>
 *               &lt;enumeration value="No"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="operatorMccMnc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationTrackingInfoType", propOrder = {
    "msisdn",
    "imsi",
    "locationEvent",
    "networkId",
    "gtAddress",
    "msc",
    "sgsn",
    "operatorMccMnc",
    "eventDateTime"
})
@XmlSeeAlso({
    LocationTrackingInfo.class
})
public class LocationTrackingInfoType {

    protected String msisdn;
    protected String imsi;
    @XmlElement(required = true)
    protected String locationEvent;
    @XmlElement(required = true)
    protected String networkId;
    @XmlElement(required = true)
    protected String gtAddress;
    @XmlElement(name = "MSC", required = true)
    protected String msc;
    @XmlElement(name = "SGSN", required = true)
    protected String sgsn;
    protected String operatorMccMnc;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventDateTime;

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

    /**
     * Gets the value of the locationEvent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationEvent() {
        return locationEvent;
    }

    /**
     * Sets the value of the locationEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationEvent(String value) {
        this.locationEvent = value;
    }

    /**
     * Gets the value of the networkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Sets the value of the networkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkId(String value) {
        this.networkId = value;
    }

    /**
     * Gets the value of the gtAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGtAddress() {
        return gtAddress;
    }

    /**
     * Sets the value of the gtAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGtAddress(String value) {
        this.gtAddress = value;
    }

    /**
     * Gets the value of the msc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSC() {
        return msc;
    }

    /**
     * Sets the value of the msc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSC(String value) {
        this.msc = value;
    }

    /**
     * Gets the value of the sgsn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSGSN() {
        return sgsn;
    }

    /**
     * Sets the value of the sgsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSGSN(String value) {
        this.sgsn = value;
    }

    /**
     * Gets the value of the operatorMccMnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorMccMnc() {
        return operatorMccMnc;
    }

    /**
     * Sets the value of the operatorMccMnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorMccMnc(String value) {
        this.operatorMccMnc = value;
    }

    /**
     * Gets the value of the eventDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventDateTime() {
        return eventDateTime;
    }

    /**
     * Sets the value of the eventDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventDateTime(XMLGregorianCalendar value) {
        this.eventDateTime = value;
    }

}
