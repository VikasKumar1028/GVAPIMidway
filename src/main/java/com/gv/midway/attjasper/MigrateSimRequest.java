
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
 *         &lt;element name="newServiceProvider" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newAccountId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ratePlanId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="communicationPlanId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
    "newServiceProvider",
    "newAccountId",
    "ratePlanId",
    "communicationPlanId"
})
@XmlRootElement(name = "MigrateSimRequest")
public class MigrateSimRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String iccid;
    @XmlElement(required = true)
    protected String newServiceProvider;
    protected long newAccountId;
    protected Long ratePlanId;
    protected Long communicationPlanId;

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
     * Gets the value of the newServiceProvider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewServiceProvider() {
        return newServiceProvider;
    }

    /**
     * Sets the value of the newServiceProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewServiceProvider(String value) {
        this.newServiceProvider = value;
    }

    /**
     * Gets the value of the newAccountId property.
     * 
     */
    public long getNewAccountId() {
        return newAccountId;
    }

    /**
     * Sets the value of the newAccountId property.
     * 
     */
    public void setNewAccountId(long value) {
        this.newAccountId = value;
    }

    /**
     * Gets the value of the ratePlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRatePlanId() {
        return ratePlanId;
    }

    /**
     * Sets the value of the ratePlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRatePlanId(Long value) {
        this.ratePlanId = value;
    }

    /**
     * Gets the value of the communicationPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCommunicationPlanId() {
        return communicationPlanId;
    }

    /**
     * Sets the value of the communicationPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCommunicationPlanId(Long value) {
        this.communicationPlanId = value;
    }

}
