
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
    "serviceDetails",
    "successSmsMsg"
})
@XmlRootElement(name = "ChangeSubscriberServiceRequest")
public class ChangeSubscriberServiceRequest
    extends SubscriberRequestType
{

    @XmlElement(required = true)
    protected RequestKeyType requestKey;
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
