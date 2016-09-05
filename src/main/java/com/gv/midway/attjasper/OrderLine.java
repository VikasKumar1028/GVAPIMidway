
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderLine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderLineID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ItemID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShipQty" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="TrackingURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderLine", propOrder = {
    "orderLineID",
    "itemID",
    "shipQty",
    "trackingURL",
    "trackingNumber",
    "serialNumber"
})
public class OrderLine {

    @XmlElement(name = "OrderLineID")
    protected long orderLineID;
    @XmlElement(name = "ItemID", required = true)
    protected String itemID;
    @XmlElement(name = "ShipQty")
    protected long shipQty;
    @XmlElement(name = "TrackingURL")
    @XmlSchemaType(name = "anyURI")
    protected String trackingURL;
    @XmlElement(name = "TrackingNumber")
    protected String trackingNumber;
    @XmlElement(name = "SerialNumber")
    protected String serialNumber;

    /**
     * Gets the value of the orderLineID property.
     * 
     */
    public long getOrderLineID() {
        return orderLineID;
    }

    /**
     * Sets the value of the orderLineID property.
     * 
     */
    public void setOrderLineID(long value) {
        this.orderLineID = value;
    }

    /**
     * Gets the value of the itemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Sets the value of the itemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemID(String value) {
        this.itemID = value;
    }

    /**
     * Gets the value of the shipQty property.
     * 
     */
    public long getShipQty() {
        return shipQty;
    }

    /**
     * Sets the value of the shipQty property.
     * 
     */
    public void setShipQty(long value) {
        this.shipQty = value;
    }

    /**
     * Gets the value of the trackingURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingURL() {
        return trackingURL;
    }

    /**
     * Sets the value of the trackingURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingURL(String value) {
        this.trackingURL = value;
    }

    /**
     * Gets the value of the trackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Sets the value of the trackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNumber(String value) {
        this.trackingNumber = value;
    }

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

}
