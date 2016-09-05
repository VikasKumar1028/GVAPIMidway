
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
 *         &lt;element name="distributedSimTransferId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetOperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetSimRatePlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSimCommPlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "distributedSimTransferId",
    "iccid",
    "targetAccount",
    "targetOperator",
    "targetSimRatePlan",
    "targetSimCommPlan"
})
@XmlRootElement(name = "InterSPMoveTargetSimRequest")
public class InterSPMoveTargetSimRequest
    extends RequestType
{

    protected long distributedSimTransferId;
    @XmlElement(name = "Iccid", required = true)
    protected String iccid;
    @XmlElement(required = true)
    protected String targetAccount;
    @XmlElement(required = true)
    protected String targetOperator;
    protected String targetSimRatePlan;
    protected String targetSimCommPlan;

    /**
     * Gets the value of the distributedSimTransferId property.
     * 
     */
    public long getDistributedSimTransferId() {
        return distributedSimTransferId;
    }

    /**
     * Sets the value of the distributedSimTransferId property.
     * 
     */
    public void setDistributedSimTransferId(long value) {
        this.distributedSimTransferId = value;
    }

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
     * Gets the value of the targetSimRatePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSimRatePlan() {
        return targetSimRatePlan;
    }

    /**
     * Sets the value of the targetSimRatePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSimRatePlan(String value) {
        this.targetSimRatePlan = value;
    }

    /**
     * Gets the value of the targetSimCommPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSimCommPlan() {
        return targetSimCommPlan;
    }

    /**
     * Sets the value of the targetSimCommPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSimCommPlan(String value) {
        this.targetSimCommPlan = value;
    }

}
