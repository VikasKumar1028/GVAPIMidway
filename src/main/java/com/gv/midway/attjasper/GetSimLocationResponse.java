
package com.gv.midway.attjasper;

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
 *         &lt;element name="location" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="accuracy" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "location",
    "status",
    "errorCode",
    "errorDetails"
})
@XmlRootElement(name = "GetSimLocationResponse")
public class GetSimLocationResponse
    extends ResponseType
{

    protected GetSimLocationResponse.Location location;
    protected boolean status;
    protected String errorCode;
    protected String errorDetails;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link GetSimLocationResponse.Location }
     *     
     */
    public GetSimLocationResponse.Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSimLocationResponse.Location }
     *     
     */
    public void setLocation(GetSimLocationResponse.Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * Sets the value of the errorDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDetails(String value) {
        this.errorDetails = value;
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
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="accuracy" type="{http://www.w3.org/2001/XMLSchema}short"/>
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
        "iccid",
        "msisdn",
        "longitude",
        "latitude",
        "address",
        "time",
        "accuracy"
    })
    public static class Location {

        @XmlElement(required = true)
        protected String iccid;
        protected long msisdn;
        protected float longitude;
        protected float latitude;
        @XmlElement(required = true)
        protected String address;
        @XmlElement(required = true)
        protected String time;
        protected short accuracy;

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
         * Gets the value of the msisdn property.
         * 
         */
        public long getMsisdn() {
            return msisdn;
        }

        /**
         * Sets the value of the msisdn property.
         * 
         */
        public void setMsisdn(long value) {
            this.msisdn = value;
        }

        /**
         * Gets the value of the longitude property.
         * 
         */
        public float getLongitude() {
            return longitude;
        }

        /**
         * Sets the value of the longitude property.
         * 
         */
        public void setLongitude(float value) {
            this.longitude = value;
        }

        /**
         * Gets the value of the latitude property.
         * 
         */
        public float getLatitude() {
            return latitude;
        }

        /**
         * Sets the value of the latitude property.
         * 
         */
        public void setLatitude(float value) {
            this.latitude = value;
        }

        /**
         * Gets the value of the address property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAddress() {
            return address;
        }

        /**
         * Sets the value of the address property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddress(String value) {
            this.address = value;
        }

        /**
         * Gets the value of the time property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTime() {
            return time;
        }

        /**
         * Sets the value of the time property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTime(String value) {
            this.time = value;
        }

        /**
         * Gets the value of the accuracy property.
         * 
         */
        public short getAccuracy() {
            return accuracy;
        }

        /**
         * Sets the value of the accuracy property.
         * 
         */
        public void setAccuracy(short value) {
            this.accuracy = value;
        }

    }

}
