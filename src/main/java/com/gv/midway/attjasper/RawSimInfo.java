
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RawSimInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RawSimInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pin1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kienc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adm3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adm4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dlKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dualImsiCapable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dualImsiEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="simBound" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="adm1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adm2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RawSimInfo", propOrder = {

})
public class RawSimInfo {

    @XmlElement(required = true)
    protected String imsi;
    @XmlElement(required = true)
    protected String iccid;
    protected String pin1;
    protected String puk1;
    protected String pin2;
    protected String puk2;
    protected String kienc;
    protected String adm3;
    protected String adm4;
    protected String acc;
    protected String dlKey;
    protected Long msisdn;
    protected Boolean dualImsiCapable;
    protected Boolean dualImsiEnabled;
    protected Boolean simBound;
    protected String adm1;
    protected String adm2;
    protected String areaCode;
    protected String countryCode;

    /**
     * Gets the value of the imsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * Sets the value of the imsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImsi(String value) {
        this.imsi = value;
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
     * Gets the value of the kienc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKienc() {
        return kienc;
    }

    /**
     * Sets the value of the kienc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKienc(String value) {
        this.kienc = value;
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

    /**
     * Gets the value of the dlKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlKey() {
        return dlKey;
    }

    /**
     * Sets the value of the dlKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlKey(String value) {
        this.dlKey = value;
    }

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMsisdn(Long value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the dualImsiCapable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDualImsiCapable() {
        return dualImsiCapable;
    }

    /**
     * Sets the value of the dualImsiCapable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDualImsiCapable(Boolean value) {
        this.dualImsiCapable = value;
    }

    /**
     * Gets the value of the dualImsiEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDualImsiEnabled() {
        return dualImsiEnabled;
    }

    /**
     * Sets the value of the dualImsiEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDualImsiEnabled(Boolean value) {
        this.dualImsiEnabled = value;
    }

    /**
     * Gets the value of the simBound property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSimBound() {
        return simBound;
    }

    /**
     * Sets the value of the simBound property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSimBound(Boolean value) {
        this.simBound = value;
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
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

}
