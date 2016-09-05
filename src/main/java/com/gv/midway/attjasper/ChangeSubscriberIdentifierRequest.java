
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}SubscriberRequestType">
 *       &lt;sequence>
 *         &lt;element name="changeIdentifierKey" type="{http://api.jasperwireless.com/ws/schema}ChangeIdentifierKeyType"/>
 *         &lt;element name="oldSubscriberIdentifiers" type="{http://api.jasperwireless.com/ws/schema}ChangeSubscriberIdentifierType"/>
 *         &lt;element name="newSubscriberIdentifiers" type="{http://api.jasperwireless.com/ws/schema}ChangeSubscriberIdentifierType"/>
 *         &lt;element name="oldDeviceFeatures" type="{http://api.jasperwireless.com/ws/schema}DeviceFeatureType"/>
 *         &lt;element name="newDeviceFeatures" type="{http://api.jasperwireless.com/ws/schema}DeviceFeatureType"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="successSmsMsg" type="{http://api.jasperwireless.com/ws/schema}successMessageType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "changeIdentifierKey",
    "oldSubscriberIdentifiers",
    "newSubscriberIdentifiers",
    "oldDeviceFeatures",
    "newDeviceFeatures",
    "reason",
    "successSmsMsg"
})
@XmlRootElement(name = "ChangeSubscriberIdentifierRequest")
public class ChangeSubscriberIdentifierRequest
    extends SubscriberRequestType
{

    @XmlElement(required = true)
    protected ChangeIdentifierKeyType changeIdentifierKey;
    @XmlElement(required = true)
    protected ChangeSubscriberIdentifierType oldSubscriberIdentifiers;
    @XmlElement(required = true)
    protected ChangeSubscriberIdentifierType newSubscriberIdentifiers;
    @XmlElement(required = true)
    protected DeviceFeatureType oldDeviceFeatures;
    @XmlElement(required = true)
    protected DeviceFeatureType newDeviceFeatures;
    @XmlElement(required = true)
    protected String reason;
    protected SuccessMessageType successSmsMsg;

    /**
     * Gets the value of the changeIdentifierKey property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeIdentifierKeyType }
     *     
     */
    public ChangeIdentifierKeyType getChangeIdentifierKey() {
        return changeIdentifierKey;
    }

    /**
     * Sets the value of the changeIdentifierKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeIdentifierKeyType }
     *     
     */
    public void setChangeIdentifierKey(ChangeIdentifierKeyType value) {
        this.changeIdentifierKey = value;
    }

    /**
     * Gets the value of the oldSubscriberIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeSubscriberIdentifierType }
     *     
     */
    public ChangeSubscriberIdentifierType getOldSubscriberIdentifiers() {
        return oldSubscriberIdentifiers;
    }

    /**
     * Sets the value of the oldSubscriberIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeSubscriberIdentifierType }
     *     
     */
    public void setOldSubscriberIdentifiers(ChangeSubscriberIdentifierType value) {
        this.oldSubscriberIdentifiers = value;
    }

    /**
     * Gets the value of the newSubscriberIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeSubscriberIdentifierType }
     *     
     */
    public ChangeSubscriberIdentifierType getNewSubscriberIdentifiers() {
        return newSubscriberIdentifiers;
    }

    /**
     * Sets the value of the newSubscriberIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeSubscriberIdentifierType }
     *     
     */
    public void setNewSubscriberIdentifiers(ChangeSubscriberIdentifierType value) {
        this.newSubscriberIdentifiers = value;
    }

    /**
     * Gets the value of the oldDeviceFeatures property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceFeatureType }
     *     
     */
    public DeviceFeatureType getOldDeviceFeatures() {
        return oldDeviceFeatures;
    }

    /**
     * Sets the value of the oldDeviceFeatures property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceFeatureType }
     *     
     */
    public void setOldDeviceFeatures(DeviceFeatureType value) {
        this.oldDeviceFeatures = value;
    }

    /**
     * Gets the value of the newDeviceFeatures property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceFeatureType }
     *     
     */
    public DeviceFeatureType getNewDeviceFeatures() {
        return newDeviceFeatures;
    }

    /**
     * Sets the value of the newDeviceFeatures property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceFeatureType }
     *     
     */
    public void setNewDeviceFeatures(DeviceFeatureType value) {
        this.newDeviceFeatures = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the successSmsMsg property.
     * 
     * @return
     *     possible object is
     *     {@link SuccessMessageType }
     *     
     */
    public SuccessMessageType getSuccessSmsMsg() {
        return successSmsMsg;
    }

    /**
     * Sets the value of the successSmsMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuccessMessageType }
     *     
     */
    public void setSuccessSmsMsg(SuccessMessageType value) {
        this.successSmsMsg = value;
    }

}
