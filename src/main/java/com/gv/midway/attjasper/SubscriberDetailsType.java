
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubscriberDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscriberStatus" type="{http://api.jasperwireless.com/ws/schema}TargetStateType"/>
 *         &lt;element name="subscriberIdentifiers" type="{http://api.jasperwireless.com/ws/schema}SubscriberIdentifierType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberDetailsType", propOrder = {
    "subscriberStatus",
    "subscriberIdentifiers"
})
public class SubscriberDetailsType {

    @XmlElement(required = true)
    protected TargetStateType subscriberStatus;
    @XmlElement(required = true)
    protected SubscriberIdentifierType subscriberIdentifiers;

    /**
     * Gets the value of the subscriberStatus property.
     * 
     * @return
     *     possible object is
     *     {@link TargetStateType }
     *     
     */
    public TargetStateType getSubscriberStatus() {
        return subscriberStatus;
    }

    /**
     * Sets the value of the subscriberStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetStateType }
     *     
     */
    public void setSubscriberStatus(TargetStateType value) {
        this.subscriberStatus = value;
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

}
