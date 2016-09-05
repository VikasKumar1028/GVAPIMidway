
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
 *         &lt;element name="sourceIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetMsisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetImsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetOperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "sourceIccid",
    "targetIccid",
    "targetMsisdn",
    "targetImsi",
    "targetAccount",
    "targetOperator",
    "globalSimTransferType"
})
@XmlRootElement(name = "AssociateSrcSimWithTargetSimRequest")
public class AssociateSrcSimWithTargetSimRequest
    extends RequestType
{

    protected long globalSimTransferId;
    @XmlElement(required = true)
    protected String sourceIccid;
    @XmlElement(required = true)
    protected String targetIccid;
    @XmlElement(required = true)
    protected String targetMsisdn;
    @XmlElement(required = true)
    protected String targetImsi;
    @XmlElement(required = true)
    protected String targetAccount;
    @XmlElement(required = true)
    protected String targetOperator;
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
