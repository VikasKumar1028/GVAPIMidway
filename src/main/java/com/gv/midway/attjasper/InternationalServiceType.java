
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         International services include voice service, SMS service and MMS service
 *     
 * 
 * <p>Java class for InternationalServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InternationalServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="voiceServices" type="{http://api.jasperwireless.com/ws/schema}VoiceServiceType" minOccurs="0"/>
 *         &lt;element name="smsServices" type="{http://api.jasperwireless.com/ws/schema}SmsServiceType" minOccurs="0"/>
 *         &lt;element name="mmsServices" type="{http://api.jasperwireless.com/ws/schema}MmsServiceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternationalServiceType", propOrder = {
    "voiceServices",
    "smsServices",
    "mmsServices"
})
public class InternationalServiceType {

    protected VoiceServiceType voiceServices;
    protected SmsServiceType smsServices;
    protected MmsServiceType mmsServices;

    /**
     * Gets the value of the voiceServices property.
     * 
     * @return
     *     possible object is
     *     {@link VoiceServiceType }
     *     
     */
    public VoiceServiceType getVoiceServices() {
        return voiceServices;
    }

    /**
     * Sets the value of the voiceServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoiceServiceType }
     *     
     */
    public void setVoiceServices(VoiceServiceType value) {
        this.voiceServices = value;
    }

    /**
     * Gets the value of the smsServices property.
     * 
     * @return
     *     possible object is
     *     {@link SmsServiceType }
     *     
     */
    public SmsServiceType getSmsServices() {
        return smsServices;
    }

    /**
     * Sets the value of the smsServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmsServiceType }
     *     
     */
    public void setSmsServices(SmsServiceType value) {
        this.smsServices = value;
    }

    /**
     * Gets the value of the mmsServices property.
     * 
     * @return
     *     possible object is
     *     {@link MmsServiceType }
     *     
     */
    public MmsServiceType getMmsServices() {
        return mmsServices;
    }

    /**
     * Sets the value of the mmsServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link MmsServiceType }
     *     
     */
    public void setMmsServices(MmsServiceType value) {
        this.mmsServices = value;
    }

}
