
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="globalSimTransferId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="remoteRequestId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="targetAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetOperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryOperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceImsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceMsisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetSimProfileId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="globalSimTransferType" type="{http://api.jasperwireless.com/ws/schema}GlobalSimTransferType"/>
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
    "remoteRequestId",
    "targetAccount",
    "targetOperator",
    "primaryAccount",
    "primaryOperator",
    "sourceIccid",
    "sourceImsi",
    "sourceMsisdn",
    "targetSimProfileId",
    "globalSimTransferType"
})
@XmlRootElement(name = "SelectVirtualSubAndAssignMSISDNRequest")
public class SelectVirtualSubAndAssignMSISDNRequest
    extends RequestType
{

    protected long globalSimTransferId;
    protected long remoteRequestId;
    @XmlElement(required = true)
    protected String targetAccount;
    @XmlElement(required = true)
    protected String targetOperator;
    protected String primaryAccount;
    protected String primaryOperator;
    @XmlElement(required = true)
    protected String sourceIccid;
    @XmlElement(required = true)
    protected String sourceImsi;
    @XmlElement(required = true)
    protected String sourceMsisdn;
    @XmlElement(required = true)
    protected String targetSimProfileId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GlobalSimTransferType globalSimTransferType;

    /**
     * Gets the value of the globalSimTransferId property.
     * 
     */
    public long getGlobalSimTransferId() {
        return globalSimTransferId;
    }

    /**
     * Sets the value of the globalSimTransferId property.
     * 
     */
    public void setGlobalSimTransferId(long value) {
        this.globalSimTransferId = value;
    }

    /**
     * Gets the value of the remoteRequestId property.
     * 
     */
    public long getRemoteRequestId() {
        return remoteRequestId;
    }

    /**
     * Sets the value of the remoteRequestId property.
     * 
     */
    public void setRemoteRequestId(long value) {
        this.remoteRequestId = value;
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
     * Gets the value of the primaryAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryAccount() {
        return primaryAccount;
    }

    /**
     * Sets the value of the primaryAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryAccount(String value) {
        this.primaryAccount = value;
    }

    /**
     * Gets the value of the primaryOperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryOperator() {
        return primaryOperator;
    }

    /**
     * Sets the value of the primaryOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryOperator(String value) {
        this.primaryOperator = value;
    }

    /**
     * Gets the value of the sourceIccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceIccid() {
        return sourceIccid;
    }

    /**
     * Sets the value of the sourceIccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceIccid(String value) {
        this.sourceIccid = value;
    }

    /**
     * Gets the value of the sourceImsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceImsi() {
        return sourceImsi;
    }

    /**
     * Sets the value of the sourceImsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceImsi(String value) {
        this.sourceImsi = value;
    }

    /**
     * Gets the value of the sourceMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceMsisdn() {
        return sourceMsisdn;
    }

    /**
     * Sets the value of the sourceMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceMsisdn(String value) {
        this.sourceMsisdn = value;
    }

    /**
     * Gets the value of the targetSimProfileId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSimProfileId() {
        return targetSimProfileId;
    }

    /**
     * Sets the value of the targetSimProfileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSimProfileId(String value) {
        this.targetSimProfileId = value;
    }

    /**
     * Gets the value of the globalSimTransferType property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalSimTransferType }
     *     
     */
    public GlobalSimTransferType getGlobalSimTransferType() {
        return globalSimTransferType;
    }

    /**
     * Sets the value of the globalSimTransferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalSimTransferType }
     *     
     */
    public void setGlobalSimTransferType(GlobalSimTransferType value) {
        this.globalSimTransferType = value;
    }

}
