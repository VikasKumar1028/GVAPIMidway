
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesLeadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesLeadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="industry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="launchTimeframe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="device" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numberOfDevices" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="referralCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deviceDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesLeadType", propOrder = {
    "company",
    "industry",
    "launchTimeframe",
    "device",
    "numberOfDevices",
    "referralCode",
    "deviceDetails"
})
public class SalesLeadType {

    @XmlElement(required = true)
    protected String company;
    @XmlElement(required = true)
    protected String industry;
    @XmlElement(required = true)
    protected String launchTimeframe;
    @XmlElement(required = true)
    protected String device;
    protected int numberOfDevices;
    @XmlElement(required = true)
    protected String referralCode;
    protected String deviceDetails;

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the industry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Sets the value of the industry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustry(String value) {
        this.industry = value;
    }

    /**
     * Gets the value of the launchTimeframe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaunchTimeframe() {
        return launchTimeframe;
    }

    /**
     * Sets the value of the launchTimeframe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaunchTimeframe(String value) {
        this.launchTimeframe = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevice(String value) {
        this.device = value;
    }

    /**
     * Gets the value of the numberOfDevices property.
     * 
     */
    public int getNumberOfDevices() {
        return numberOfDevices;
    }

    /**
     * Sets the value of the numberOfDevices property.
     * 
     */
    public void setNumberOfDevices(int value) {
        this.numberOfDevices = value;
    }

    /**
     * Gets the value of the referralCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralCode() {
        return referralCode;
    }

    /**
     * Sets the value of the referralCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralCode(String value) {
        this.referralCode = value;
    }

    /**
     * Gets the value of the deviceDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceDetails() {
        return deviceDetails;
    }

    /**
     * Sets the value of the deviceDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceDetails(String value) {
        this.deviceDetails = value;
    }

}
