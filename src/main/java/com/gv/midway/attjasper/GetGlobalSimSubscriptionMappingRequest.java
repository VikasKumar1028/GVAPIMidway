
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
 *         &lt;element name="primaryIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryImsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryMsisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetOperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "primaryIccid",
    "primaryImsi",
    "primaryMsisdn",
    "targetOperator",
    "targetAccount"
})
@XmlRootElement(name = "GetGlobalSimSubscriptionMappingRequest")
public class GetGlobalSimSubscriptionMappingRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String primaryIccid;
    @XmlElement(required = true)
    protected String primaryImsi;
    @XmlElement(required = true)
    protected String primaryMsisdn;
    @XmlElement(required = true)
    protected String targetOperator;
    @XmlElement(required = true)
    protected String targetAccount;

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
     * Gets the value of the primaryImsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryImsi() {
        return primaryImsi;
    }

    /**
     * Sets the value of the primaryImsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryImsi(String value) {
        this.primaryImsi = value;
    }

    /**
     * Gets the value of the primaryMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryMsisdn() {
        return primaryMsisdn;
    }

    /**
     * Sets the value of the primaryMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryMsisdn(String value) {
        this.primaryMsisdn = value;
    }

    /**
     * Gets the value of the targetOperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetOperator() {
        return targetOperator;
    }

    /**
     * Sets the value of the targetOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetOperator(String value) {
        this.targetOperator = value;
    }

    /**
     * Gets the value of the targetAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetAccount() {
        return targetAccount;
    }

    /**
     * Sets the value of the targetAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetAccount(String value) {
        this.targetAccount = value;
    }

}
