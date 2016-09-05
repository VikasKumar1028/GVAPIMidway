
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
 *         &lt;element name="sourceIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetOperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="externalSimProfileId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="simInfo" type="{http://api.jasperwireless.com/ws/schema}SimInfo"/>
 *         &lt;element name="simAuxFieldInfo" type="{http://api.jasperwireless.com/ws/schema}SimAuxFieldInfo"/>
 *         &lt;element name="imsiInfo" type="{http://api.jasperwireless.com/ws/schema}ImsiInfo"/>
 *         &lt;element name="rawSimInfo" type="{http://api.jasperwireless.com/ws/schema}RawSimInfo"/>
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
    "sourceIccid",
    "targetOperator",
    "externalSimProfileId",
    "simInfo",
    "simAuxFieldInfo",
    "imsiInfo",
    "rawSimInfo"
})
@XmlRootElement(name = "InterSPCreateTargetSimRequest")
public class InterSPCreateTargetSimRequest
    extends RequestType
{

    protected long distributedSimTransferId;
    @XmlElement(required = true)
    protected String sourceIccid;
    @XmlElement(required = true)
    protected String targetOperator;
    @XmlElement(required = true)
    protected String externalSimProfileId;
    @XmlElement(required = true)
    protected SimInfo simInfo;
    @XmlElement(required = true)
    protected SimAuxFieldInfo simAuxFieldInfo;
    @XmlElement(required = true)
    protected ImsiInfo imsiInfo;
    @XmlElement(required = true)
    protected RawSimInfo rawSimInfo;

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
     * Gets the value of the externalSimProfileId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSimProfileId() {
        return externalSimProfileId;
    }

    /**
     * Sets the value of the externalSimProfileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSimProfileId(String value) {
        this.externalSimProfileId = value;
    }

    /**
     * Gets the value of the simInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SimInfo }
     *     
     */
    public SimInfo getSimInfo() {
        return simInfo;
    }

    /**
     * Sets the value of the simInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimInfo }
     *     
     */
    public void setSimInfo(SimInfo value) {
        this.simInfo = value;
    }

    /**
     * Gets the value of the simAuxFieldInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SimAuxFieldInfo }
     *     
     */
    public SimAuxFieldInfo getSimAuxFieldInfo() {
        return simAuxFieldInfo;
    }

    /**
     * Sets the value of the simAuxFieldInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimAuxFieldInfo }
     *     
     */
    public void setSimAuxFieldInfo(SimAuxFieldInfo value) {
        this.simAuxFieldInfo = value;
    }

    /**
     * Gets the value of the imsiInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ImsiInfo }
     *     
     */
    public ImsiInfo getImsiInfo() {
        return imsiInfo;
    }

    /**
     * Sets the value of the imsiInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImsiInfo }
     *     
     */
    public void setImsiInfo(ImsiInfo value) {
        this.imsiInfo = value;
    }

    /**
     * Gets the value of the rawSimInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RawSimInfo }
     *     
     */
    public RawSimInfo getRawSimInfo() {
        return rawSimInfo;
    }

    /**
     * Sets the value of the rawSimInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RawSimInfo }
     *     
     */
    public void setRawSimInfo(RawSimInfo value) {
        this.rawSimInfo = value;
    }

}
