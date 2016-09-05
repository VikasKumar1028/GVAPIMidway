
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
 *         &lt;element name="voicemailKey" type="{http://api.jasperwireless.com/ws/schema}SubscriberVoicemailKey"/>
 *         &lt;element name="changeVoicemailPasswordDetails" type="{http://api.jasperwireless.com/ws/schema}ChangeVoiceMailPasswordType"/>
 *         &lt;element name="successSmsMsg" type="{http://api.jasperwireless.com/ws/schema}successMessageType" minOccurs="0"/>
 *         &lt;element name="successEmailMsg" type="{http://api.jasperwireless.com/ws/schema}successEmailMessageType" minOccurs="0"/>
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
    "voicemailKey",
    "changeVoicemailPasswordDetails",
    "successSmsMsg",
    "successEmailMsg"
})
@XmlRootElement(name = "ChangeVoicemailPasswordRequest")
public class ChangeVoicemailPasswordRequest
    extends SubscriberRequestType
{

    @XmlElement(required = true)
    protected SubscriberVoicemailKey voicemailKey;
    @XmlElement(required = true)
    protected ChangeVoiceMailPasswordType changeVoicemailPasswordDetails;
    protected SuccessMessageType successSmsMsg;
    protected SuccessEmailMessageType successEmailMsg;

    /**
     * Gets the value of the voicemailKey property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberVoicemailKey }
     *     
     */
    public SubscriberVoicemailKey getVoicemailKey() {
        return voicemailKey;
    }

    /**
     * Sets the value of the voicemailKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberVoicemailKey }
     *     
     */
    public void setVoicemailKey(SubscriberVoicemailKey value) {
        this.voicemailKey = value;
    }

    /**
     * Gets the value of the changeVoicemailPasswordDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeVoiceMailPasswordType }
     *     
     */
    public ChangeVoiceMailPasswordType getChangeVoicemailPasswordDetails() {
        return changeVoicemailPasswordDetails;
    }

    /**
     * Sets the value of the changeVoicemailPasswordDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeVoiceMailPasswordType }
     *     
     */
    public void setChangeVoicemailPasswordDetails(ChangeVoiceMailPasswordType value) {
        this.changeVoicemailPasswordDetails = value;
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

    /**
     * Gets the value of the successEmailMsg property.
     * 
     * @return
     *     possible object is
     *     {@link SuccessEmailMessageType }
     *     
     */
    public SuccessEmailMessageType getSuccessEmailMsg() {
        return successEmailMsg;
    }

    /**
     * Sets the value of the successEmailMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuccessEmailMessageType }
     *     
     */
    public void setSuccessEmailMsg(SuccessEmailMessageType value) {
        this.successEmailMsg = value;
    }

}
