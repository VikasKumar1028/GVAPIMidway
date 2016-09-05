
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
 *         &lt;element name="termStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="termEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="renewalMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renewalRatePlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "termStartDate",
    "termEndDate",
    "renewalMode",
    "renewalRatePlan"
})
@XmlRootElement(name = "EditTerminalRatingRequest")
public class EditTerminalRatingRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String iccid;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar termStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar termEndDate;
    protected String renewalMode;
    protected String renewalRatePlan;

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
     * Gets the value of the termStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTermStartDate() {
        return termStartDate;
    }

    /**
     * Sets the value of the termStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTermStartDate(XMLGregorianCalendar value) {
        this.termStartDate = value;
    }

    /**
     * Gets the value of the termEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTermEndDate() {
        return termEndDate;
    }

    /**
     * Sets the value of the termEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTermEndDate(XMLGregorianCalendar value) {
        this.termEndDate = value;
    }

    /**
     * Gets the value of the renewalMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewalMode() {
        return renewalMode;
    }

    /**
     * Sets the value of the renewalMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewalMode(String value) {
        this.renewalMode = value;
    }

    /**
     * Gets the value of the renewalRatePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewalRatePlan() {
        return renewalRatePlan;
    }

    /**
     * Sets the value of the renewalRatePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewalRatePlan(String value) {
        this.renewalRatePlan = value;
    }

}
