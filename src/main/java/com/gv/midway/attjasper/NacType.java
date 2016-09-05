
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         The detail information about a Network Access Config.
 *     
 * 
 * <p>Java class for nacType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nacType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nacId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nacDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voiceEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="smsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="gprsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nacType", propOrder = {
    "nacId",
    "nacDescription",
    "voiceEnabled",
    "smsEnabled",
    "gprsEnabled"
})
public class NacType {

    protected long nacId;
    protected String nacDescription;
    protected boolean voiceEnabled;
    protected boolean smsEnabled;
    protected boolean gprsEnabled;

    /**
     * Gets the value of the nacId property.
     * 
     */
    public long getNacId() {
        return nacId;
    }

    /**
     * Sets the value of the nacId property.
     * 
     */
    public void setNacId(long value) {
        this.nacId = value;
    }

    /**
     * Gets the value of the nacDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacDescription() {
        return nacDescription;
    }

    /**
     * Sets the value of the nacDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacDescription(String value) {
        this.nacDescription = value;
    }

    /**
     * Gets the value of the voiceEnabled property.
     * 
     */
    public boolean isVoiceEnabled() {
        return voiceEnabled;
    }

    /**
     * Sets the value of the voiceEnabled property.
     * 
     */
    public void setVoiceEnabled(boolean value) {
        this.voiceEnabled = value;
    }

    /**
     * Gets the value of the smsEnabled property.
     * 
     */
    public boolean isSmsEnabled() {
        return smsEnabled;
    }

    /**
     * Sets the value of the smsEnabled property.
     * 
     */
    public void setSmsEnabled(boolean value) {
        this.smsEnabled = value;
    }

    /**
     * Gets the value of the gprsEnabled property.
     * 
     */
    public boolean isGprsEnabled() {
        return gprsEnabled;
    }

    /**
     * Sets the value of the gprsEnabled property.
     * 
     */
    public void setGprsEnabled(boolean value) {
        this.gprsEnabled = value;
    }

}
