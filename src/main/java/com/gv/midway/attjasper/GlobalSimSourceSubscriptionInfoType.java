
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Global SIM subscription information which contains ICCID, MSISDN and IMSI which the SIM has / will have.
 *     
 * 
 * <p>Java class for GlobalSimSourceSubscriptionInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlobalSimSourceSubscriptionInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sourceIccid" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="sourceMsisdn" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="sourceImsi" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlobalSimSourceSubscriptionInfoType", propOrder = {
    "sourceIccid",
    "sourceMsisdn",
    "sourceImsi"
})
public class GlobalSimSourceSubscriptionInfoType {

    @XmlElement(required = true)
    protected Object sourceIccid;
    @XmlElement(required = true)
    protected Object sourceMsisdn;
    @XmlElement(required = true)
    protected Object sourceImsi;

    /**
     * Gets the value of the sourceIccid property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSourceIccid() {
        return sourceIccid;
    }

    /**
     * Sets the value of the sourceIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSourceIccid(Object value) {
        this.sourceIccid = value;
    }

    /**
     * Gets the value of the sourceMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSourceMsisdn() {
        return sourceMsisdn;
    }

    /**
     * Sets the value of the sourceMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSourceMsisdn(Object value) {
        this.sourceMsisdn = value;
    }

    /**
     * Gets the value of the sourceImsi property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSourceImsi() {
        return sourceImsi;
    }

    /**
     * Sets the value of the sourceImsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSourceImsi(Object value) {
        this.sourceImsi = value;
    }

}
