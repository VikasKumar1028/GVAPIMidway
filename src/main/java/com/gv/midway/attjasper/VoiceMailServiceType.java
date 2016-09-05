
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Voice Mail Services, will have voice mail features.
 *     
 * 
 * <p>Java class for VoiceMailServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoiceMailServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="voiceMailFeatures" type="{http://api.jasperwireless.com/ws/schema}VoiceMailFeatureType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoiceMailServiceType", propOrder = {
    "voiceMailFeatures"
})
public class VoiceMailServiceType {

    protected List<VoiceMailFeatureType> voiceMailFeatures;

    /**
     * Gets the value of the voiceMailFeatures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiceMailFeatures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVoiceMailFeatures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VoiceMailFeatureType }
     * 
     * 
     */
    public List<VoiceMailFeatureType> getVoiceMailFeatures() {
        if (voiceMailFeatures == null) {
            voiceMailFeatures = new ArrayList<VoiceMailFeatureType>();
        }
        return this.voiceMailFeatures;
    }

}
