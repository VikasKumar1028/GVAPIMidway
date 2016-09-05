
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="chargetype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SocOrderID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SocCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TLGBan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlanActivationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
    "chargetype",
    "socOrderID",
    "sessionID",
    "socCode",
    "tlgBan",
    "planActivationDate"
})
@XmlRootElement(name = "AddCustomSubscriberChargeRequest")
public class AddCustomSubscriberChargeRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String iccid;
    @XmlElement(required = true)
    protected String chargetype;
    @XmlElement(name = "SocOrderID")
    protected String socOrderID;
    @XmlElement(name = "SessionID")
    protected String sessionID;
    @XmlElement(name = "SocCode")
    protected String socCode;
    @XmlElement(name = "TLGBan")
    protected String tlgBan;
    @XmlElement(name = "PlanActivationDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar planActivationDate;

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
     * Gets the value of the chargetype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargetype() {
        return chargetype;
    }

    /**
     * Sets the value of the chargetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargetype(String value) {
        this.chargetype = value;
    }

    /**
     * Gets the value of the socOrderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocOrderID() {
        return socOrderID;
    }

    /**
     * Sets the value of the socOrderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocOrderID(String value) {
        this.socOrderID = value;
    }

    /**
     * Gets the value of the sessionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionID(String value) {
        this.sessionID = value;
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
     * Gets the value of the tlgBan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTLGBan() {
        return tlgBan;
    }

    /**
     * Sets the value of the tlgBan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTLGBan(String value) {
        this.tlgBan = value;
    }

    /**
     * Gets the value of the planActivationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlanActivationDate() {
        return planActivationDate;
    }

    /**
     * Sets the value of the planActivationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlanActivationDate(XMLGregorianCalendar value) {
        this.planActivationDate = value;
    }

}
