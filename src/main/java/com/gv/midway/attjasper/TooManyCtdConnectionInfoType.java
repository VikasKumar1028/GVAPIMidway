
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         The information about a SIM has more connections than expected in the current billing cycle.
 *     
 * 
 * <p>Java class for TooManyCtdConnectionInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TooManyCtdConnectionInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ratePlanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentSessionUsage" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sessionUsageThreshold" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TooManyCtdConnectionInfoType", propOrder = {
    "iccid",
    "accountName",
    "ratePlanName",
    "currentSessionUsage",
    "sessionUsageThreshold"
})
@XmlSeeAlso({
    TooManyCtdConnection.class
})
public class TooManyCtdConnectionInfoType {

    @XmlElement(required = true)
    protected String iccid;
    protected String accountName;
    protected String ratePlanName;
    protected long currentSessionUsage;
    protected long sessionUsageThreshold;

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
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

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
     * Gets the value of the currentSessionUsage property.
     * 
     */
    public long getCurrentSessionUsage() {
        return currentSessionUsage;
    }

    /**
     * Sets the value of the currentSessionUsage property.
     * 
     */
    public void setCurrentSessionUsage(long value) {
        this.currentSessionUsage = value;
    }

    /**
     * Gets the value of the sessionUsageThreshold property.
     * 
     */
    public long getSessionUsageThreshold() {
        return sessionUsageThreshold;
    }

    /**
     * Sets the value of the sessionUsageThreshold property.
     * 
     */
    public void setSessionUsageThreshold(long value) {
        this.sessionUsageThreshold = value;
    }

}
