
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Voice Mail Features are Key/Value pairs.
 *     
 * 
 * <p>Java class for VoiceMailFeatureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoiceMailFeatureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="voiceMailFeature" type="{http://api.jasperwireless.com/ws/schema}FeatureType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoiceMailFeatureType", propOrder = {
    "voiceMailFeature"
})
public class VoiceMailFeatureType {

    @XmlElement(required = true)
    protected FeatureType voiceMailFeature;

    /**
     * Gets the value of the voiceMailFeature property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureType }
     *     
     */
    public FeatureType getVoiceMailFeature() {
        return voiceMailFeature;
    }

    /**
     * Sets the value of the voiceMailFeature property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureType }
     *     
     */
    public void setVoiceMailFeature(FeatureType value) {
        this.voiceMailFeature = value;
    }

}
