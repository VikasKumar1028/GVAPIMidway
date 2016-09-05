
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="status" type="{http://api.jasperwireless.com/ws/schema}SelectVirtualSubAndAssignMSISDNStatus"/>
 *         &lt;element name="msisdnAssignStatus" type="{http://api.jasperwireless.com/ws/schema}msisdnAssignmentStatus" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSimSubscriptionInfo" type="{http://api.jasperwireless.com/ws/schema}GlobalSimSubscriptionInfoType" minOccurs="0"/>
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
    "status",
    "msisdnAssignStatus",
    "transactionId",
    "targetSimSubscriptionInfo"
})
@XmlRootElement(name = "SelectVirtualSubAndAssignMSISDNResponse")
public class SelectVirtualSubAndAssignMSISDNResponse
    extends ResponseType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected SelectVirtualSubAndAssignMSISDNStatus status;
    @XmlSchemaType(name = "string")
    protected MsisdnAssignmentStatus msisdnAssignStatus;
    protected String transactionId;
    protected GlobalSimSubscriptionInfoType targetSimSubscriptionInfo;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link SelectVirtualSubAndAssignMSISDNStatus }
     *     
     */
    public SelectVirtualSubAndAssignMSISDNStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectVirtualSubAndAssignMSISDNStatus }
     *     
     */
    public void setStatus(SelectVirtualSubAndAssignMSISDNStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the msisdnAssignStatus property.
     * 
     * @return
     *     possible object is
     *     {@link MsisdnAssignmentStatus }
     *     
     */
    public MsisdnAssignmentStatus getMsisdnAssignStatus() {
        return msisdnAssignStatus;
    }

    /**
     * Sets the value of the msisdnAssignStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsisdnAssignmentStatus }
     *     
     */
    public void setMsisdnAssignStatus(MsisdnAssignmentStatus value) {
        this.msisdnAssignStatus = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
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
