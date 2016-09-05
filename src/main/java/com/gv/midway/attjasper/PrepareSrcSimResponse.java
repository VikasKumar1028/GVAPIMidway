
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
 *         &lt;element name="globalSimTransferId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sourceIccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="previousSourceSimState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceSimState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceSimRatePlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceSimCommPlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adm1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adm2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adm3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adm4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "previousSourceSimState",
    "sourceSimState",
    "sourceSimRatePlan",
    "sourceSimCommPlan",
    "pin1",
    "puk1",
    "pin2",
    "puk2",
    "adm1",
    "adm2",
    "adm3",
    "adm4",
    "acc"
})
@XmlRootElement(name = "PrepareSrcSimResponse")
public class PrepareSrcSimResponse
    extends ResponseType
{

    protected long globalSimTransferId;
    @XmlElement(required = true)
    protected String sourceIccid;
    protected String previousSourceSimState;
    @XmlElement(required = true)
    protected String sourceSimState;
    protected String sourceSimRatePlan;
    protected String sourceSimCommPlan;
    protected String pin1;
    protected String puk1;
    protected String pin2;
    protected String puk2;
    protected String adm1;
    protected String adm2;
    protected String adm3;
    protected String adm4;
    protected String acc;

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
     * Gets the value of the previousSourceSimState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousSourceSimState() {
        return previousSourceSimState;
    }

    /**
     * Sets the value of the previousSourceSimState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousSourceSimState(String value) {
        this.previousSourceSimState = value;
    }

    /**
     * Gets the value of the sourceSimState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSimState() {
        return sourceSimState;
    }

    /**
     * Sets the value of the sourceSimState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSimState(String value) {
        this.sourceSimState = value;
    }

    /**
     * Gets the value of the sourceSimRatePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSimRatePlan() {
        return sourceSimRatePlan;
    }

    /**
     * Sets the value of the sourceSimRatePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSimRatePlan(String value) {
        this.sourceSimRatePlan = value;
    }

    /**
     * Gets the value of the sourceSimCommPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSimCommPlan() {
        return sourceSimCommPlan;
    }

    /**
     * Sets the value of the sourceSimCommPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSimCommPlan(String value) {
        this.sourceSimCommPlan = value;
    }

    /**
     * Gets the value of the pin1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin1() {
        return pin1;
    }

    /**
     * Sets the value of the pin1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin1(String value) {
        this.pin1 = value;
    }

    /**
     * Gets the value of the puk1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk1() {
        return puk1;
    }

    /**
     * Sets the value of the puk1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk1(String value) {
        this.puk1 = value;
    }

    /**
     * Gets the value of the pin2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin2() {
        return pin2;
    }

    /**
     * Sets the value of the pin2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin2(String value) {
        this.pin2 = value;
    }

    /**
     * Gets the value of the puk2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk2() {
        return puk2;
    }

    /**
     * Sets the value of the puk2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk2(String value) {
        this.puk2 = value;
    }

    /**
     * Gets the value of the adm1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdm1() {
        return adm1;
    }

    /**
     * Sets the value of the adm1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdm1(String value) {
        this.adm1 = value;
    }

    /**
     * Gets the value of the adm2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdm2() {
        return adm2;
    }

    /**
     * Sets the value of the adm2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdm2(String value) {
        this.adm2 = value;
    }

    /**
     * Gets the value of the adm3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdm3() {
        return adm3;
    }

    /**
     * Sets the value of the adm3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdm3(String value) {
        this.adm3 = value;
    }

    /**
     * Gets the value of the adm4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdm4() {
        return adm4;
    }

    /**
     * Sets the value of the adm4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdm4(String value) {
        this.adm4 = value;
    }

    /**
     * Gets the value of the acc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcc() {
        return acc;
    }

    /**
     * Sets the value of the acc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcc(String value) {
        this.acc = value;
    }

}
