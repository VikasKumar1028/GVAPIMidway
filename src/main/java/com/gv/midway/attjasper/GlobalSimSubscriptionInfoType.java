
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Target Global SIM subscription information which contains Target ICCID, Target MSISDN and target IMSI which SIM will have the transfer activity is complete.
 *     
 * 
 * <p>Java class for GlobalSimSubscriptionInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlobalSimSubscriptionInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="targetIccid" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="targetMsisdn" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="targetImsi" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlobalSimSubscriptionInfoType", propOrder = {
    "targetIccid",
    "targetMsisdn",
    "targetImsi"
})
public class GlobalSimSubscriptionInfoType {

    @XmlElement(required = true)
    protected Object targetIccid;
    protected Object targetMsisdn;
    @XmlElement(required = true)
    protected Object targetImsi;

    /**
     * Gets the value of the targetIccid property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTargetIccid() {
        return targetIccid;
    }

    /**
     * Sets the value of the targetIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTargetIccid(Object value) {
        this.targetIccid = value;
    }

    /**
     * Gets the value of the targetMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTargetMsisdn() {
        return targetMsisdn;
    }

    /**
     * Sets the value of the targetMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTargetMsisdn(Object value) {
        this.targetMsisdn = value;
    }

    /**
     * Gets the value of the targetImsi property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTargetImsi() {
        return targetImsi;
    }

    /**
     * Sets the value of the targetImsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTargetImsi(Object value) {
        this.targetImsi = value;
    }

}
