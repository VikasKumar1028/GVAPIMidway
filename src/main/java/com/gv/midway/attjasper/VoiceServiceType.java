
package com.gv.midway.attjasper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Voice Service details block is used then request should contain Voice Quota and Voice Quota Unit
 *         1. Voice Quota is a Integer value. It is valued with -1 when it is unlimited voice calls.
 *         2. Voice Quota Unit is define the Voice Quota in units like "MINUTES" or "SECONDS".  If Voice is Unlimited, then should be valued to "SECONDS".
 *         3. Voice QOS about Quality of Service.
 *         4. Voice Features are Key and Value pairs. Like 3WC/CFW/Call Forward etc.
 *     
 * 
 * <p>Java class for VoiceServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoiceServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="voiceQuota" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="voiceQuotaUnit" type="{http://api.jasperwireless.com/ws/schema}VoiceQuotaUnitType"/>
 *         &lt;element name="voiceQOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voiceFeatures" type="{http://api.jasperwireless.com/ws/schema}VoiceFeatureType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoiceServiceType", propOrder = {
    "voiceQuota",
    "voiceQuotaUnit",
    "voiceQOS",
    "voiceFeatures"
})
public class VoiceServiceType {

    @XmlElement(required = true)
    protected BigInteger voiceQuota;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected VoiceQuotaUnitType voiceQuotaUnit;
    protected String voiceQOS;
    protected List<VoiceFeatureType> voiceFeatures;

    /**
     * Gets the value of the voiceQuota property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVoiceQuota() {
        return voiceQuota;
    }

    /**
     * Sets the value of the voiceQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVoiceQuota(BigInteger value) {
        this.voiceQuota = value;
    }

    /**
     * Gets the value of the voiceQuotaUnit property.
     * 
     * @return
     *     possible object is
     *     {@link VoiceQuotaUnitType }
     *     
     */
    public VoiceQuotaUnitType getVoiceQuotaUnit() {
        return voiceQuotaUnit;
    }

    /**
     * Sets the value of the voiceQuotaUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoiceQuotaUnitType }
     *     
     */
    public void setVoiceQuotaUnit(VoiceQuotaUnitType value) {
        this.voiceQuotaUnit = value;
    }

    /**
     * Gets the value of the voiceQOS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoiceQOS() {
        return voiceQOS;
    }

    /**
     * Sets the value of the voiceQOS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoiceQOS(String value) {
        this.voiceQOS = value;
    }

    /**
     * Gets the value of the voiceFeatures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiceFeatures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVoiceFeatures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VoiceFeatureType }
     * 
     * 
     */
    public List<VoiceFeatureType> getVoiceFeatures() {
        if (voiceFeatures == null) {
            voiceFeatures = new ArrayList<VoiceFeatureType>();
        }
        return this.voiceFeatures;
    }

}
