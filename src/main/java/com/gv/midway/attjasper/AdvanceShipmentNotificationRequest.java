
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


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
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusUpdateNote" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="orderLineItems">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="orderLineItem" type="{http://api.jasperwireless.com/ws/schema}orderLineItemType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
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
    "orderId",
    "statusUpdateNote",
    "orderLineItems",
    "any"
})
@XmlRootElement(name = "AdvanceShipmentNotificationRequest")
public class AdvanceShipmentNotificationRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String orderId;
    protected String statusUpdateNote;
    @XmlElement(required = true)
    protected AdvanceShipmentNotificationRequest.OrderLineItems orderLineItems;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the statusUpdateNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusUpdateNote() {
        return statusUpdateNote;
    }

    /**
     * Sets the value of the statusUpdateNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusUpdateNote(String value) {
        this.statusUpdateNote = value;
    }

    /**
     * Gets the value of the orderLineItems property.
     * 
     * @return
     *     possible object is
     *     {@link AdvanceShipmentNotificationRequest.OrderLineItems }
     *     
     */
    public AdvanceShipmentNotificationRequest.OrderLineItems getOrderLineItems() {
        return orderLineItems;
    }

    /**
     * Sets the value of the orderLineItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdvanceShipmentNotificationRequest.OrderLineItems }
     *     
     */
    public void setOrderLineItems(AdvanceShipmentNotificationRequest.OrderLineItems value) {
        this.orderLineItems = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
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
     *         &lt;element name="orderLineItem" type="{http://api.jasperwireless.com/ws/schema}orderLineItemType"/>
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
        "orderLineItem"
    })
    public static class OrderLineItems {

        @XmlElement(required = true)
        protected OrderLineItemType orderLineItem;

        /**
         * Gets the value of the orderLineItem property.
         * 
         * @return
         *     possible object is
         *     {@link OrderLineItemType }
         *     
         */
        public OrderLineItemType getOrderLineItem() {
            return orderLineItem;
        }

        /**
         * Sets the value of the orderLineItem property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrderLineItemType }
         *     
         */
        public void setOrderLineItem(OrderLineItemType value) {
            this.orderLineItem = value;
        }

    }

}
