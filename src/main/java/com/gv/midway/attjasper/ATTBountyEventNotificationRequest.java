
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="socType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tlgban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SocOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SocCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="autoRenewStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="replacementIccid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "iccid",
    "action",
    "socType",
    "tlgban",
    "socOrderId",
    "socCode",
    "sessionId",
    "startDate",
    "autoRenewStatus",
    "replacementIccid"
})
@XmlRootElement(name = "ATTBountyEventNotificationRequest")
public class ATTBountyEventNotificationRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String iccid;
    protected int action;
    protected Integer socType;
    protected String tlgban;
    @XmlElement(name = "SocOrderId")
    protected String socOrderId;
    @XmlElement(name = "SocCode")
    protected String socCode;
    protected String sessionId;
    protected String startDate;
    protected Boolean autoRenewStatus;
    protected String replacementIccid;

    /**
     * Gets the value of the iccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * Sets the value of the iccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIccid(String value) {
        this.iccid = value;
    }

    /**
     * Gets the value of the action property.
     * 
     */
    public int getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     */
    public void setAction(int value) {
        this.action = value;
    }

    /**
     * Gets the value of the socType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSocType() {
        return socType;
    }

    /**
     * Sets the value of the socType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSocType(Integer value) {
        this.socType = value;
    }

    /**
     * Gets the value of the tlgban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlgban() {
        return tlgban;
    }

    /**
     * Sets the value of the tlgban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlgban(String value) {
        this.tlgban = value;
    }

    /**
     * Gets the value of the socOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocOrderId() {
        return socOrderId;
    }

    /**
     * Sets the value of the socOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocOrderId(String value) {
        this.socOrderId = value;
    }

    /**
     * Gets the value of the socCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocCode() {
        return socCode;
    }

    /**
     * Sets the value of the socCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocCode(String value) {
        this.socCode = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the autoRenewStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoRenewStatus() {
        return autoRenewStatus;
    }

    /**
     * Sets the value of the autoRenewStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoRenewStatus(Boolean value) {
        this.autoRenewStatus = value;
    }

    /**
     * Gets the value of the replacementIccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplacementIccid() {
        return replacementIccid;
    }

    /**
     * Sets the value of the replacementIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplacementIccid(String value) {
        this.replacementIccid = value;
    }

}
