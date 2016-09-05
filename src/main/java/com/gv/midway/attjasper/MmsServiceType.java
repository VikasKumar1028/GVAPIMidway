
package com.gv.midway.attjasper;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Provide the MMS Service details like MMS Quota and MMQ Quality Of Service.
 *     
 * 
 * <p>Java class for MmsServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MmsServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mmsQuota" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="mmsQOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MmsServiceType", propOrder = {
    "mmsQuota",
    "mmsQOS"
})
public class MmsServiceType {

    @XmlElement(required = true)
    protected BigInteger mmsQuota;
    protected String mmsQOS;

    /**
     * Gets the value of the mmsQuota property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMmsQuota() {
        return mmsQuota;
    }

    /**
     * Sets the value of the mmsQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMmsQuota(BigInteger value) {
        this.mmsQuota = value;
    }

    /**
     * Gets the value of the mmsQOS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMmsQOS() {
        return mmsQOS;
    }

    /**
     * Sets the value of the mmsQOS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMmsQOS(String value) {
        this.mmsQOS = value;
    }

}
