
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Subscriber Service Details
 *         Data Services: provide the data provision details
 *         Voice Services:   provide the voice provision details
 *         SMS Services:  provide the SMS provision details
 *         Voicemail Services:  provide the voice mail provision details
 *         Call Forward Services:  provide the Call Forwarding provision details
 *         MMS Services:       provide the MMS provision details
 *         International Services:  provide the International services that include Voice International, SMS International and MMS International provision details.
 *         Roaming Zones: Roaming zone services that include Roaming Voice services, Roaming SMS services, Roaming Data services and Roaming MMS provision details.
 *         Extended Services:
 *     
 * 
 * <p>Java class for ServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataServices" type="{http://api.jasperwireless.com/ws/schema}DataServiceType" minOccurs="0"/>
 *         &lt;element name="voiceServices" type="{http://api.jasperwireless.com/ws/schema}VoiceServiceType" minOccurs="0"/>
 *         &lt;element name="smsServices" type="{http://api.jasperwireless.com/ws/schema}SmsServiceType" minOccurs="0"/>
 *         &lt;element name="voiceMailServices" type="{http://api.jasperwireless.com/ws/schema}VoiceMailServiceType" minOccurs="0"/>
 *         &lt;element name="mmsServices" type="{http://api.jasperwireless.com/ws/schema}MmsServiceType" minOccurs="0"/>
 *         &lt;element name="internationalServices" type="{http://api.jasperwireless.com/ws/schema}InternationalServiceType" minOccurs="0"/>
 *         &lt;element name="roamingZones" type="{http://api.jasperwireless.com/ws/schema}RoamingZonesType" minOccurs="0"/>
 *         &lt;element name="extendedServices" type="{http://api.jasperwireless.com/ws/schema}ExtendedServiceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceType", propOrder = {
    "dataServices",
    "voiceServices",
    "smsServices",
    "voiceMailServices",
    "mmsServices",
    "internationalServices",
    "roamingZones",
    "extendedServices"
})
public class ServiceType {

    protected DataServiceType dataServices;
    protected VoiceServiceType voiceServices;
    protected SmsServiceType smsServices;
    protected VoiceMailServiceType voiceMailServices;
    protected MmsServiceType mmsServices;
    protected InternationalServiceType internationalServices;
    protected RoamingZonesType roamingZones;
    protected ExtendedServiceType extendedServices;

    /**
     * Gets the value of the dataServices property.
     * 
     * @return
     *     possible object is
     *     {@link DataServiceType }
     *     
     */
    public DataServiceType getDataServices() {
        return dataServices;
    }

    /**
     * Sets the value of the dataServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataServiceType }
     *     
     */
    public void setDataServices(DataServiceType value) {
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

    /**
     * Gets the value of the internationalServices property.
     * 
     * @return
     *     possible object is
     *     {@link InternationalServiceType }
     *     
     */
    public InternationalServiceType getInternationalServices() {
        return internationalServices;
    }

    /**
     * Sets the value of the internationalServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternationalServiceType }
     *     
     */
    public void setInternationalServices(InternationalServiceType value) {
        this.internationalServices = value;
    }

    /**
     * Gets the value of the roamingZones property.
     * 
     * @return
     *     possible object is
     *     {@link RoamingZonesType }
     *     
     */
    public RoamingZonesType getRoamingZones() {
        return roamingZones;
    }

    /**
     * Sets the value of the roamingZones property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoamingZonesType }
     *     
     */
    public void setRoamingZones(RoamingZonesType value) {
        this.roamingZones = value;
    }

    /**
     * Gets the value of the extendedServices property.
     * 
     * @return
     *     possible object is
     *     {@link ExtendedServiceType }
     *     
     */
    public ExtendedServiceType getExtendedServices() {
        return extendedServices;
    }

    /**
     * Sets the value of the extendedServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedServiceType }
     *     
     */
    public void setExtendedServices(ExtendedServiceType value) {
        this.extendedServices = value;
    }

}
