
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImsiInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImsiInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isOnHLR" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="kienc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="onSRP" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="hlrAccessMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImsiInfo", propOrder = {

})
public class ImsiInfo {

    @XmlElement(required = true)
    protected String imsi;
    protected String iccid;
    protected Boolean isOnHLR;
    protected String kienc;
    protected String transportKey;
    protected String msisdn;
    protected String subsType;
    protected Boolean onSRP;
    protected String hlrAccessMode;

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
     * Gets the value of the isOnHLR property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsOnHLR() {
        return isOnHLR;
    }

    /**
     * Sets the value of the isOnHLR property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsOnHLR(Boolean value) {
        this.isOnHLR = value;
    }

    /**
     * Gets the value of the kienc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKienc() {
        return kienc;
    }

    /**
     * Sets the value of the kienc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKienc(String value) {
        this.kienc = value;
    }

    /**
     * Gets the value of the transportKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportKey() {
        return transportKey;
    }

    /**
     * Sets the value of the transportKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportKey(String value) {
        this.transportKey = value;
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
     * Gets the value of the subsType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsType() {
        return subsType;
    }

    /**
     * Sets the value of the subsType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsType(String value) {
        this.subsType = value;
    }

    /**
     * Gets the value of the onSRP property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnSRP() {
        return onSRP;
    }

    /**
     * Sets the value of the onSRP property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnSRP(Boolean value) {
        this.onSRP = value;
    }

    /**
     * Gets the value of the hlrAccessMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHlrAccessMode() {
        return hlrAccessMode;
    }

    /**
     * Sets the value of the hlrAccessMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHlrAccessMode(String value) {
        this.hlrAccessMode = value;
    }

}
