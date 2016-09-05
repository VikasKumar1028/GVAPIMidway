
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
 *         &lt;element name="bandwidthruleid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roamBandwidthRuleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "requestKey",
    "bandwidthruleid",
    "roamBandwidthRuleId",
    "reason",
    "successSmsMsg"
})
@XmlRootElement(name = "ChangeServicePolicyRequest")
public class ChangeServicePolicyRequest
    extends SubscriberRequestType
{

    @XmlElement(required = true)
    protected RequestKeyType requestKey;
    @XmlElement(required = true)
    protected String bandwidthruleid;
    protected String roamBandwidthRuleId;
    @XmlElement(required = true)
    protected String reason;
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
     * Gets the value of the bandwidthruleid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBandwidthruleid() {
        return bandwidthruleid;
    }

    /**
     * Sets the value of the bandwidthruleid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBandwidthruleid(String value) {
        this.bandwidthruleid = value;
    }

    /**
     * Gets the value of the roamBandwidthRuleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoamBandwidthRuleId() {
        return roamBandwidthRuleId;
    }

    /**
     * Sets the value of the roamBandwidthRuleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoamBandwidthRuleId(String value) {
        this.roamBandwidthRuleId = value;
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
