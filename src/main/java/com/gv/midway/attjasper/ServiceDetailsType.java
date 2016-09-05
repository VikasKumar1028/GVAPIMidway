
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Service details have important Service details like Base Offer Id (Providers Plan Name)
 *         Primary MSISDN - It is valued with MSISDN of the subscriber. In case of Family Plans, it will be valued with MSISDN of primary account holder. However, it is not a Mandatory field.
 *         Subscriber services details to describe voice, data, roaming, international, MMS and SMS services.
 *     
 * 
 * <p>Java class for ServiceDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseOfferId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryMsisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="services" type="{http://api.jasperwireless.com/ws/schema}ServiceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDetailsType", propOrder = {
    "baseOfferId",
    "primaryMsisdn",
    "services"
})
public class ServiceDetailsType {

    @XmlElement(required = true)
    protected String baseOfferId;
    protected String primaryMsisdn;
    @XmlElement(required = true)
    protected ServiceType services;

    /**
     * Gets the value of the baseOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseOfferId() {
        return baseOfferId;
    }

    /**
     * Sets the value of the baseOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseOfferId(String value) {
        this.baseOfferId = value;
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
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceType }
     *     
     */
    public ServiceType getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceType }
     *     
     */
    public void setServices(ServiceType value) {
        this.services = value;
    }

}
