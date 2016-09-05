
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
 *         &lt;element name="targetIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetImsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetMsisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "targetIccid",
    "targetImsi",
    "targetMsisdn"
})
@XmlRootElement(name = "GetGlobalSimSubscriptionMappingResponse")
public class GetGlobalSimSubscriptionMappingResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected String targetIccid;
    @XmlElement(required = true)
    protected String targetImsi;
    @XmlElement(required = true)
    protected String targetMsisdn;

    /**
     * Gets the value of the targetIccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetIccid() {
        return targetIccid;
    }

    /**
     * Sets the value of the targetIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetIccid(String value) {
        this.targetIccid = value;
    }

    /**
     * Gets the value of the targetImsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetImsi() {
        return targetImsi;
    }

    /**
     * Sets the value of the targetImsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetImsi(String value) {
        this.targetImsi = value;
    }

    /**
     * Gets the value of the targetMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetMsisdn() {
        return targetMsisdn;
    }

    /**
     * Sets the value of the targetMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetMsisdn(String value) {
        this.targetMsisdn = value;
    }

}
