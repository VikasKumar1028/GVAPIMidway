
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Roaming Zone name like "Mexico" or "Canada" or "UK" or "Asia" etc..
 *         Data service details in roaming zone.
 *         Voice service details in roaming zone.
 *         SMS service details in roaming zone.
 *         MMS service details in roaming zone.
 *         Voice Mail service details in roaming zone.
 *     
 * 
 * <p>Java class for RoamingZoneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoamingZoneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zoneName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataServices" type="{http://api.jasperwireless.com/ws/schema}RoamDataServiceType" minOccurs="0"/>
 *         &lt;element name="voiceServices" type="{http://api.jasperwireless.com/ws/schema}VoiceServiceType" minOccurs="0"/>
 *         &lt;element name="smsServices" type="{http://api.jasperwireless.com/ws/schema}SmsServiceType" minOccurs="0"/>
 *         &lt;element name="voiceMailServices" type="{http://api.jasperwireless.com/ws/schema}VoiceMailServiceType" minOccurs="0"/>
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
@XmlType(name = "RoamingZoneType", propOrder = {
    "zoneName",
    "dataServices",
    "voiceServices",
    "smsServices",
    "voiceMailServices",
    "mmsServices"
})
public class RoamingZoneType {

    @XmlElement(required = true)
    protected String zoneName;
    protected RoamDataServiceType dataServices;
    protected VoiceServiceType voiceServices;
    protected SmsServiceType smsServices;
    protected VoiceMailServiceType voiceMailServices;
    protected MmsServiceType mmsServices;

    /**
     * Gets the value of the zoneName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * Sets the value of the zoneName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneName(String value) {
        this.zoneName = value;
    }

    /**
     * Gets the value of the dataServices property.
     * 
     * @return
     *     possible object is
     *     {@link RoamDataServiceType }
     *     
     */
    public RoamDataServiceType getDataServices() {
        return dataServices;
    }

    /**
     * Sets the value of the dataServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoamDataServiceType }
     *     
     */
    public void setDataServices(RoamDataServiceType value) {
        this.dataServices = value;
    }

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
     * Gets the value of the voiceMailServices property.
     * 
     * @return
     *     possible object is
     *     {@link VoiceMailServiceType }
     *     
     */
    public VoiceMailServiceType getVoiceMailServices() {
        return voiceMailServices;
    }

    /**
     * Sets the value of the voiceMailServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoiceMailServiceType }
     *     
     */
    public void setVoiceMailServices(VoiceMailServiceType value) {
        this.voiceMailServices = value;
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
