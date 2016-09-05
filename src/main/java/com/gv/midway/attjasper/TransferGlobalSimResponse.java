
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="globalSimTransferId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetSimSubscriptionInfo" type="{http://api.jasperwireless.com/ws/schema}GlobalSimSubscriptionInfoType"/>
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
    "globalSimTransferId",
    "primaryIccid",
    "targetSimSubscriptionInfo"
})
@XmlRootElement(name = "TransferGlobalSimResponse")
public class TransferGlobalSimResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected String globalSimTransferId;
    @XmlElement(required = true)
    protected String primaryIccid;
    @XmlElement(required = true)
    protected GlobalSimSubscriptionInfoType targetSimSubscriptionInfo;

    /**
     * Gets the value of the globalSimTransferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalSimTransferId() {
        return globalSimTransferId;
    }

    /**
     * Sets the value of the globalSimTransferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalSimTransferId(String value) {
        this.globalSimTransferId = value;
    }

    /**
     * Gets the value of the primaryIccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryIccid() {
        return primaryIccid;
    }

    /**
     * Sets the value of the primaryIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryIccid(String value) {
        this.primaryIccid = value;
    }

    /**
     * Gets the value of the targetSimSubscriptionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimSubscriptionInfoType }
     *     
     */
    public GlobalSimSubscriptionInfoType getTargetSimSubscriptionInfo() {
        return targetSimSubscriptionInfo;
    }

    /**
     * Sets the value of the targetSimSubscriptionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimSubscriptionInfoType }
     *     
     */
    public void setTargetSimSubscriptionInfo(GlobalSimSubscriptionInfoType value) {
        this.targetSimSubscriptionInfo = value;
    }

}
