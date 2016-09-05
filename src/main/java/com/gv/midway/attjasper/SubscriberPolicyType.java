
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             The detail information about Subscriber Policy.
 *         
 * 
 * <p>Java class for SubscriberPolicyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberPolicyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apnName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="streamId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="policyAction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberPolicyType", propOrder = {
    "apnName",
    "streamId",
    "policyAction"
})
public class SubscriberPolicyType {

    @XmlElement(required = true)
    protected String apnName;
    protected Integer streamId;
    @XmlElement(required = true)
    protected String policyAction;

    /**
     * Gets the value of the apnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApnName() {
        return apnName;
    }

    /**
     * Sets the value of the apnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApnName(String value) {
        this.apnName = value;
    }

    /**
     * Gets the value of the streamId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStreamId() {
        return streamId;
    }

    /**
     * Sets the value of the streamId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStreamId(Integer value) {
        this.streamId = value;
    }

    /**
     * Gets the value of the policyAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyAction() {
        return policyAction;
    }

    /**
     * Sets the value of the policyAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyAction(String value) {
        this.policyAction = value;
    }

}
