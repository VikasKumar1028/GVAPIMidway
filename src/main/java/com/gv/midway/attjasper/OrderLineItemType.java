
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         OrderLineItem MUST be present in the Shipment Notification.
 *         Each orderlineitem MUST have one or more shipments (tracking number and optional tracking URL).
 *     
 * 
 * <p>Java class for orderLineItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderLineItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shipments">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="shipment" type="{http://api.jasperwireless.com/ws/schema}shipmentTrackingType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderLineItemType", propOrder = {
    "shipments"
})
public class OrderLineItemType {

    @XmlElement(required = true)
    protected OrderLineItemType.Shipments shipments;

    /**
     * Gets the value of the shipments property.
     * 
     * @return
     *     possible object is
     *     {@link OrderLineItemType.Shipments }
     *     
     */
    public OrderLineItemType.Shipments getShipments() {
        return shipments;
    }

    /**
     * Sets the value of the shipments property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderLineItemType.Shipments }
     *     
     */
    public void setShipments(OrderLineItemType.Shipments value) {
        this.shipments = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="shipment" type="{http://api.jasperwireless.com/ws/schema}shipmentTrackingType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "shipment"
    })
    public static class Shipments {

        @XmlElement(required = true)
        protected ShipmentTrackingType shipment;

        /**
         * Gets the value of the shipment property.
         * 
         * @return
         *     possible object is
         *     {@link ShipmentTrackingType }
         *     
         */
        public ShipmentTrackingType getShipment() {
            return shipment;
        }

        /**
         * Sets the value of the shipment property.
         * 
         * @param value
         *     allowed object is
         *     {@link ShipmentTrackingType }
         *     
         */
        public void setShipment(ShipmentTrackingType value) {
            this.shipment = value;
        }

    }

}
