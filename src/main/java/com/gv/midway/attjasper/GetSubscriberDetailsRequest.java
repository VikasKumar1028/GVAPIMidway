
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
    "requestKey"
})
@XmlRootElement(name = "GetSubscriberDetailsRequest")
public class GetSubscriberDetailsRequest
    extends SubscriberRequestType
{

    @XmlElement(required = true)
    protected RequestKeyType requestKey;

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

}
