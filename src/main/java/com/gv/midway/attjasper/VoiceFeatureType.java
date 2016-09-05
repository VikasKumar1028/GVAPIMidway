
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Voice Features are Key/ Value pairs.
 *         Examples:
 *         Key - Value
 *         ===========
 *         MULTIPARTYCALLING(Three way calling - 3WC) - True/False
 *         CALLWAITING  - True/False
 *         CALLFORWARD -  Phone Number
 *         CALLHOLD - True/False
 *     
 * 
 * <p>Java class for VoiceFeatureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoiceFeatureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="voiceFeature" type="{http://api.jasperwireless.com/ws/schema}FeatureType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoiceFeatureType", propOrder = {
    "voiceFeature"
})
public class VoiceFeatureType {

    @XmlElement(required = true)
    protected FeatureType voiceFeature;

    /**
     * Gets the value of the voiceFeature property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureType }
     *     
     */
    public FeatureType getVoiceFeature() {
        return voiceFeature;
    }

    /**
     * Sets the value of the voiceFeature property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureType }
     *     
     */
    public void setVoiceFeature(FeatureType value) {
        this.voiceFeature = value;
    }

}
