
package com.gv.midway.attjasper;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         SMS Service Type holds MT Quota, MO Quota and Combined Quota.
 *         MTQuota should be valued to "-1", when it is Unlimited
 *         MTQuota should be valued to "-1", when it is Unlimited
 *         CombinedQuota should be valued to "-1", when it is Unlimited
 *     
 * 
 * <p>Java class for SmsServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SmsServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsMTQuota" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="smsMOQuota" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="smsCombinedQuota" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmsServiceType", propOrder = {
    "smsMTQuota",
    "smsMOQuota",
    "smsCombinedQuota"
})
public class SmsServiceType {

    protected BigInteger smsMTQuota;
    protected BigInteger smsMOQuota;
    protected BigInteger smsCombinedQuota;

    /**
     * Gets the value of the smsMTQuota property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSmsMTQuota() {
        return smsMTQuota;
    }

    /**
     * Sets the value of the smsMTQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSmsMTQuota(BigInteger value) {
        this.smsMTQuota = value;
    }

    /**
     * Gets the value of the smsMOQuota property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSmsMOQuota() {
        return smsMOQuota;
    }

    /**
     * Sets the value of the smsMOQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSmsMOQuota(BigInteger value) {
        this.smsMOQuota = value;
    }

    /**
     * Gets the value of the smsCombinedQuota property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSmsCombinedQuota() {
        return smsCombinedQuota;
    }

    /**
     * Sets the value of the smsCombinedQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSmsCombinedQuota(BigInteger value) {
        this.smsCombinedQuota = value;
    }

}
