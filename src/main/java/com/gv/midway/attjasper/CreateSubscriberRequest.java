
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
 *         &lt;element name="requestKey" type="{http://api.jasperwireless.com/ws/schema}RequestKeyType"/>
 *         &lt;element name="targetState" type="{http://api.jasperwireless.com/ws/schema}TargetStateType"/>
 *         &lt;element name="subscriberIdentifiers" type="{http://api.jasperwireless.com/ws/schema}SubscriberIdentifierType"/>
 *         &lt;element name="deviceFeatures" type="{http://api.jasperwireless.com/ws/schema}DeviceFeatureType"/>
 *         &lt;element name="consumerDetails" type="{http://api.jasperwireless.com/ws/schema}ConsumerDetailType"/>
 *         &lt;element name="serviceDetails" type="{http://api.jasperwireless.com/ws/schema}ServiceDetailsType"/>
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
    "requestKey",
    "targetState",
    "subscriberIdentifiers",
    "deviceFeatures",
    "consumerDetails",
    "serviceDetails",
    "successSmsMsg"
})
@XmlRootElement(name = "CreateSubscriberRequest")
public class CreateSubscriberRequest
    extends SubscriberRequestType
{

    @XmlElement(required = true)
    protected RequestKeyType requestKey;
    @XmlElement(required = true)
    protected TargetStateType targetState;
    @XmlElement(required = true)
    protected SubscriberIdentifierType subscriberIdentifiers;
    @XmlElement(required = true)
    protected DeviceFeatureType deviceFeatures;
    @XmlElement(required = true)
    protected ConsumerDetailType consumerDetails;
    @XmlElement(required = true)
    protected ServiceDetailsType serviceDetails;
    protected SuccessMessageType successSmsMsg;

    /**
     * Gets the value of the requestKey property.
     * 
     * @return
     *     possible object is
     *     {@link RequestKeyType }
     *     
     */
    public RequestKeyType getRequestKey() {
        return requestKey;
    }

    /**
     * Sets the value of the requestKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestKeyType }
     *     
     */
    public void setRequestKey(RequestKeyType value) {
        this.requestKey = value;
    }

    /**
     * Gets the value of the targetState property.
     * 
     * @return
     *     possible object is
     *     {@link TargetStateType }
     *     
     */
    public TargetStateType getTargetState() {
        return targetState;
    }

    /**
     * Sets the value of the targetState property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetStateType }
     *     
     */
    public void setTargetState(TargetStateType value) {
        this.targetState = value;
    }

    /**
     * Gets the value of the subscriberIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberIdentifierType }
     *     
     */
    public SubscriberIdentifierType getSubscriberIdentifiers() {
        return subscriberIdentifiers;
    }

    /**
     * Sets the value of the subscriberIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberIdentifierType }
     *     
     */
    public void setSubscriberIdentifiers(SubscriberIdentifierType value) {
        this.subscriberIdentifiers = value;
    }

    /**
     * Gets the value of the deviceFeatures property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceFeatureType }
     *     
     */
    public DeviceFeatureType getDeviceFeatures() {
        return deviceFeatures;
    }

    /**
     * Sets the value of the deviceFeatures property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceFeatureType }
     *     
     */
    public void setDeviceFeatures(DeviceFeatureType value) {
        this.deviceFeatures = value;
    }

    /**
     * Gets the value of the consumerDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ConsumerDetailType }
     *     
     */
    public ConsumerDetailType getConsumerDetails() {
        return consumerDetails;
    }

    /**
     * Sets the value of the consumerDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsumerDetailType }
     *     
     */
    public void setConsumerDetails(ConsumerDetailType value) {
        this.consumerDetails = value;
    }

    /**
     * Gets the value of the serviceDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceDetailsType }
     *     
     */
    public ServiceDetailsType getServiceDetails() {
        return serviceDetails;
    }

    /**
     * Sets the value of the serviceDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceDetailsType }
     *     
     */
    public void setServiceDetails(ServiceDetailsType value) {
        this.serviceDetails = value;
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
