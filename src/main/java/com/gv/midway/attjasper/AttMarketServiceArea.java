
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AttMarketServiceArea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttMarketServiceArea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billingMarket" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingSubMarket" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conversionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttMarketServiceArea", propOrder = {
    "billingMarket",
    "billingSubMarket",
    "serviceArea",
    "conversionId"
})
public class AttMarketServiceArea {

    protected String billingMarket;
    protected String billingSubMarket;
    protected String serviceArea;
    protected String conversionId;

    /**
     * Gets the value of the billingMarket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingMarket() {
        return billingMarket;
    }

    /**
     * Sets the value of the billingMarket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingMarket(String value) {
        this.billingMarket = value;
    }

    /**
     * Gets the value of the billingSubMarket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingSubMarket() {
        return billingSubMarket;
    }

    /**
     * Sets the value of the billingSubMarket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingSubMarket(String value) {
        this.billingSubMarket = value;
    }

    /**
     * Gets the value of the serviceArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceArea() {
        return serviceArea;
    }

    /**
     * Sets the value of the serviceArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceArea(String value) {
        this.serviceArea = value;
    }

    /**
     * Gets the value of the conversionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConversionId() {
        return conversionId;
    }

    /**
     * Sets the value of the conversionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConversionId(String value) {
        this.conversionId = value;
    }

}
