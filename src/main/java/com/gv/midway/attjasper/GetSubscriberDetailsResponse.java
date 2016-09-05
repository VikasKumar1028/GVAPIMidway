
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}SubscriberResponseType">
 *       &lt;sequence>
 *         &lt;element name="subscriberDetails" type="{http://api.jasperwireless.com/ws/schema}SubscriberDetailsType" minOccurs="0"/>
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
    "subscriberDetails"
})
@XmlRootElement(name = "GetSubscriberDetailsResponse")
public class GetSubscriberDetailsResponse
    extends SubscriberResponseType
{

    protected SubscriberDetailsType subscriberDetails;

    /**
     * Gets the value of the subscriberDetails property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberDetailsType }
     *     
     */
    public SubscriberDetailsType getSubscriberDetails() {
        return subscriberDetails;
    }

    /**
     * Sets the value of the subscriberDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberDetailsType }
     *     
     */
    public void setSubscriberDetails(SubscriberDetailsType value) {
        this.subscriberDetails = value;
    }

}
